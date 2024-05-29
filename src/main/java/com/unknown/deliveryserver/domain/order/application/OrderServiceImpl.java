package com.unknown.deliveryserver.domain.order.application;

import com.unknown.deliveryserver.domain.order.dao.OrderItemOptionsRepository;
import com.unknown.deliveryserver.domain.order.dao.OrderItemsRepository;
import com.unknown.deliveryserver.domain.order.dao.OrderRepository;
import com.unknown.deliveryserver.domain.order.dto.request.OrderItemOptionsRequest;
import com.unknown.deliveryserver.domain.order.dto.request.OrderItemsRequest;
import com.unknown.deliveryserver.domain.order.dto.request.OrderRequest;
import com.unknown.deliveryserver.domain.order.dto.response.OrderItemOptionsResponse;
import com.unknown.deliveryserver.domain.order.dto.response.OrderItemsResponse;
import com.unknown.deliveryserver.domain.order.dto.response.OrderResponse;
import com.unknown.deliveryserver.domain.order.entity.Order;
import com.unknown.deliveryserver.domain.order.entity.OrderItemOptions;
import com.unknown.deliveryserver.domain.order.entity.OrderItems;
import com.unknown.deliveryserver.domain.order.enumerated.OrderStatus;
import com.unknown.deliveryserver.domain.restaurant.application.RestaurantService;
import com.unknown.deliveryserver.domain.restaurant.dao.menu.MenuOptionDetailRepository;
import com.unknown.deliveryserver.domain.restaurant.dao.menu.MenuRepository;
import com.unknown.deliveryserver.domain.restaurant.entity.Restaurant;
import com.unknown.deliveryserver.domain.restaurant.entity.menu.Menu;
import com.unknown.deliveryserver.domain.restaurant.entity.menu.MenuOptionDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderItemOptionsRepository orderItemOptionsRepository;
    private final RestaurantService restaurantService;

    private final MenuRepository menuRepository;
    private final MenuOptionDetailRepository menuOptionDetailRepository;

    // 주문 하기
    @Override
    @Transactional
    public OrderResponse createOrder(Long restaurantId, OrderRequest request) {
        // 가게 조회
        Restaurant foundRestaurant = restaurantService.getFoundRestaurant(restaurantId);

        // 요청한 price 값을 소수점 3자리로 설정 - 비교하기 위해서
        BigDecimal requestPrice = request.getPrice().setScale(3);
        BigDecimal requestTotalPrice = request.getTotalPrice().setScale(3);

        // 주문 메뉴 금액, 주문 메뉴 총 금액(배달비 포함) 검사하기
        // 검사 할 것 주문한 메뉴들, 주문한 메뉴 옵션들의 금액이랑 request에서 온 price, totalPrice 같은 지 확인하기
        BigDecimal orderMenuPrice = getTotalMenuPrice(request);
        BigDecimal orderMenuTotalPrice = orderMenuPrice.add(foundRestaurant.getDeliveryPrice()); // 배송비 포함

        System.out.println("orderMenuPrice = " + orderMenuPrice);
        System.out.println("orderMenuTotalPrice = " + orderMenuTotalPrice);

        // Validator 유틸 만들기
        // orderMenuPrice랑 최소금액이랑도 비교하기
        if (!orderMenuPrice.equals(requestPrice)) {
            throw new IllegalArgumentException("다시 주문 확인 부탁드립니다. 주문하신 가격이 잘못 되었습니다.");
        }

        if (!(orderMenuTotalPrice).equals(requestTotalPrice)) {
            throw new IllegalArgumentException("다시 주문 확인 부탁드립니다. (배달비 포함) 주문하신 가격이 잘못 되었습니다.");
        }

        // Order 객체 만들기
        Order order = Order.builder()
                .orderStatus(OrderStatus.ORDER_RECEIVED)
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .phone(request.getPhone())
                .recipient(request.getRecipient())
                .description(request.getDescription())
                .price(orderMenuPrice) // 주문 메뉴 금액
                .totalPrice(orderMenuPrice.add(foundRestaurant.getDeliveryPrice())) // 주문 메뉴 총 금액(배달비 포함)
                .build();
        order.addRestaurant(foundRestaurant);

        Order savedOrder = orderRepository.save(order);

        // OrderItems 객체 만들고 저장
        List<OrderItemsResponse> orderItemsResponseList = request.getOrderItemsRequestList()
                .stream()
                .map(orderItem -> saveOrderItem(orderItem, savedOrder))
                .toList();

        return OrderResponse.of(savedOrder, orderItemsResponseList);
    }

    // 주문한 메뉴들 총금액
    private BigDecimal getTotalMenuPrice(OrderRequest request) {
        return request.getOrderItemsRequestList().stream()
                .map(orderItemsRequest -> {
                    Menu foundMenu = menuRepository.findByFoodName(orderItemsRequest.getFoodName())
                            .orElseThrow(() -> new IllegalArgumentException("등록된 메뉴가 아닙니다."));
                    BigDecimal menuPrice = foundMenu.getPrice();

                    // 주문한 메뉴 옵션들의 총금액
                    BigDecimal orderMenuOptionPrice = getTotalAdditionalPrice(orderItemsRequest);

                    return (menuPrice.add(orderMenuOptionPrice))
                            .multiply(BigDecimal.valueOf(orderItemsRequest.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 주문한 메뉴 옵션들의 총금액
    private BigDecimal getTotalAdditionalPrice(OrderItemsRequest orderItemsRequest) {
        return orderItemsRequest.getOrderItemOptionsRequestList().stream()
                .map(orderItemsOption -> {
                    MenuOptionDetail foundMenuOptionDetail = menuOptionDetailRepository.findByOptionDetailName(orderItemsOption.getFoodOptionName())
                            .orElseThrow(() -> new IllegalArgumentException("등록된 메뉴의 옵션이 아닙니다."));

                    return foundMenuOptionDetail.getAdditionalPrice();
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // OrderItems 저장 (메뉴 이름)
    @Transactional
    public OrderItemsResponse saveOrderItem(OrderItemsRequest request, Order order) {
        OrderItems orderItem = OrderItems.builder()
                .foodName(request.getFoodName())
                .quantity(request.getQuantity())
                .build();
        orderItem.addOrder(order);

        OrderItems savedOrderItem = orderItemsRepository.save(orderItem);

        // OrderItemOptions 객체 만들고 저장
        List<OrderItemOptionsResponse> orderItemOptionsResponseList = request.getOrderItemOptionsRequestList()
                .stream()
                .map(orderItemOption -> saveOrderItemOption(orderItemOption, savedOrderItem))
                .toList();

        return OrderItemsResponse.of(savedOrderItem, orderItemOptionsResponseList);
    }

    // OrderItemOptions 저장 (메뉴 당 옵션 저장)
    @Transactional
    public OrderItemOptionsResponse saveOrderItemOption(OrderItemOptionsRequest request, OrderItems orderItem) {
        OrderItemOptions orderItemOption = OrderItemOptions.builder()
                .foodOptionName(request.getFoodOptionName())
                .build();
        orderItemOption.addOrderItem(orderItem);

        OrderItemOptions savedOrderItemsOption = orderItemOptionsRepository.save(orderItemOption);

        return OrderItemOptionsResponse.of(savedOrderItemsOption);
    }

    // 주문 조회
    @Override
    public OrderResponse getOrder(Long orderId) {
        Order foundOrder = getFoundOrder(orderId);

        // 메뉴와 메뉴에 대한 옵션 리스트 추출
        List<OrderItemsResponse> orderItemsResponseList = foundOrder.getOrderItemsList()
                .stream()
                .map(orderItem -> {
                    List<OrderItemOptionsResponse> orderItemOptionsResponses = orderItem.getOrderItemOptionsList()
                            .stream()
                            .map(orderItemOption -> OrderItemOptionsResponse.of(orderItemOption))
                            .toList();
                    return OrderItemsResponse.of(orderItem, orderItemOptionsResponses);
                })
                .toList();

        return OrderResponse.of(foundOrder, orderItemsResponseList);
        
        // 참고        
        /*
        // OrderItemOptionsResponse 리스트 구하기 - OrderItemsResponse에 필요한 값
        List<OrderItemOptionsResponse> orderItemOptionsResponseList = foundOrder.getOrderItemsList()
                .stream()
                .map(orderItem -> OrderItemOptionsResponse.of(getFoundOrderItemOption(orderItem)))
                .toList();
        */

        /*
        // foundOrder.getOrderItemsList() 반복해서 쓰니깐 중복된 값이 나옴
        List<OrderItemsResponse> orderItemsResponseList = foundOrder.getOrderItemsList()
                .stream()
                .map(orderItem -> OrderItemsResponse.of(orderItem, foundOrder.getOrderItemsList()
                                                                    .stream()
                                                                    .map(orderItems -> OrderItemOptionsResponse.of(getFoundOrderItemOption(orderItem)))
                                                                    .toList()))
                .toList();
        */
    }

    // Order 조회 (취소된 주문 제외)
    @Override
    public Order getFoundOrder(Long orderId) {
        return orderRepository.findByIdAndDeletedAtIsNull(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));
    }

    // OrderItems 조회
    public OrderItems getFoundOrderItem(Order order) {
        return orderItemsRepository.findByOrder(order)
                .orElseThrow(() -> new IllegalArgumentException("주문한 메뉴가 없습니다."));
    }

    // OrderItemOptions 조회
    public OrderItemOptions getFoundOrderItemOption(OrderItems orderItem) {
        return orderItemOptionsRepository.findByOrderItem(orderItem)
                .orElseThrow(() -> new IllegalArgumentException("주문한 메뉴에 옵션이 없습니다."));
    }

    // 주문 수정
    @Override
    @Transactional
    public OrderResponse modifyOrder(Long orderId, OrderRequest request) {
        return null;
    }

    // 주문 삭제
    @Override
    @Transactional
    public void deleteOrder(Long orderId) {

    }
}
