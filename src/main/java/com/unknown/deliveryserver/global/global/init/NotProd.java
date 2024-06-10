package com.unknown.deliveryserver.global.global.init;

import com.unknown.deliveryserver.domain.order.dao.OrderItemOptionsRepository;
import com.unknown.deliveryserver.domain.order.dao.OrderItemsRepository;
import com.unknown.deliveryserver.domain.order.dao.OrderRepository;
import com.unknown.deliveryserver.domain.order.entity.Order;
import com.unknown.deliveryserver.domain.order.entity.OrderItemOptions;
import com.unknown.deliveryserver.domain.order.entity.OrderItems;
import com.unknown.deliveryserver.domain.order.enumerated.OrderStatus;
import com.unknown.deliveryserver.domain.order.util.OrderUtil;
import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuOptionDetailRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuOptionRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.Menu;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOption;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOptionDetail;
import com.unknown.deliveryserver.domain.restaurant.restaurant.dao.RestaurantRepository;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Slf4j
@Profile("!prod & !test")
@RequiredArgsConstructor
@Configuration
public class NotProd {

    @Autowired
    @Lazy
    private NotProd self;

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final MenuOptionDetailRepository menuOptionDetailRepository;
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderItemOptionsRepository orderItemOptionsRepository;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (!restaurantRepository.findAll().isEmpty()) {
                return;
            }

            initRestaurant();
            initMenu();
            initMenuOption();
            initMenuOptionDetail();
            initOrder();
            initOrderItems();
            initOrderItemOptions();
        };
    }

    public void initRestaurant() {
        restaurantRepository.save(Restaurant.builder().name("맛나 분식").telephone("050-1234-1234").minPrice(BigDecimal.valueOf(10000)).deliveryPrice(BigDecimal.valueOf(1000)).build());
        restaurantRepository.save(Restaurant.builder().name("맛나 피자").telephone("050-5678-5678").minPrice(BigDecimal.valueOf(20000)).deliveryPrice(BigDecimal.valueOf(2000)).build());
    }

    public void initMenu() {
        // 맛나 분식
        menuRepository.save(Menu.builder().restaurant(restaurantRepository.findByIdAndDeletedAtIsNull(1L).get()).isPrimary(true).foodName("김밥").price(BigDecimal.valueOf(4000)).build());
        menuRepository.save(Menu.builder().restaurant(restaurantRepository.findByIdAndDeletedAtIsNull(1L).get()).isPrimary(false).foodName("떡볶이").price(BigDecimal.valueOf(5000)).build());

        // 맛나 피자
        menuRepository.save(Menu.builder().restaurant(restaurantRepository.findByIdAndDeletedAtIsNull(2L).get()).isPrimary(true).foodName("치즈 피자").price(BigDecimal.valueOf(10000)).build());
        menuRepository.save(Menu.builder().restaurant(restaurantRepository.findByIdAndDeletedAtIsNull(2L).get()).isPrimary(true).foodName("불고기 피자").price(BigDecimal.valueOf(12000)).build());
    }

    public void initMenuOption() {
        // 맛나 분식
        menuOptionRepository.save(MenuOption.builder().menu(menuRepository.findById(1L).get()).optionName("사이드").build());
        menuOptionRepository.save(MenuOption.builder().menu(menuRepository.findById(2L).get()).optionName("추가 재료").build());

        // 맛나 피자
        menuOptionRepository.save(MenuOption.builder().menu(menuRepository.findById(3L).get()).optionName("사이드").build());
        menuOptionRepository.save(MenuOption.builder().menu(menuRepository.findById(4L).get()).optionName("추가 재료").build());
    }

    public void initMenuOptionDetail() {
        // 맛나 분식 - 김밥 옵션
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(1L).get()).optionDetailName("떡볶이").additionalPrice(BigDecimal.valueOf(5000)).build());
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(1L).get()).optionDetailName("어묵탕").additionalPrice(BigDecimal.valueOf(5000)).build());

        // 맛나 분식 - 떡볶이 옵션
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(2L).get()).optionDetailName("치즈").additionalPrice(BigDecimal.valueOf(2000)).build());
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(2L).get()).optionDetailName("계란").additionalPrice(BigDecimal.valueOf(2000)).build());

        // 맛나 피자 - 피자 옵션
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(3L).get()).optionDetailName("치즈 오븐 스파게티").additionalPrice(BigDecimal.valueOf(5000)).build());
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(3L).get()).optionDetailName("감자튀김").additionalPrice(BigDecimal.valueOf(5000)).build());
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(4L).get()).optionDetailName("치즈").additionalPrice(BigDecimal.valueOf(5000)).build());
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(4L).get()).optionDetailName("페퍼로니").additionalPrice(BigDecimal.valueOf(5000)).build());
    }

    public void initOrder() {
        for (int i = 1; i <= 1000; i++) {
            orderRepository.save(Order.builder()
                    .orderNo(OrderUtil.date.getDate() + "-" + OrderUtil.date.getTime() + "-" + i)
                    .orderStatus(OrderStatus.ORDER_RECEIVED)
                    .address("대구시 달서구")
                    .addressDetail("월성동")
                    .phone("010-1234-1234")
                    .recipient("주문자1")
                    .description("문 앞에 두고 벨 눌러주세요.")
                    .restaurant(restaurantRepository.findById(1L).get())
                    .price(BigDecimal.valueOf(18000))
                    .totalPrice(BigDecimal.valueOf(19000))
                    .build());
        }

        for (int i = 1001; i <= 2000; i++) {
            orderRepository.save(Order.builder()
                    .orderNo(OrderUtil.date.getDate() + "-" + OrderUtil.date.getTime() + "-" + i)
                    .orderStatus(OrderStatus.ORDER_RECEIVED)
                    .address("대구시 달서구")
                    .addressDetail("월성동")
                    .phone("010-1234-1234")
                    .recipient("주문자1")
                    .description("문 앞에 두고 벨 눌러주세요.")
                    .restaurant(restaurantRepository.findById(2L).get())
                    .price(BigDecimal.valueOf(15000))
                    .totalPrice(BigDecimal.valueOf(16000))
                    .build());
        }
    }

    public void initOrderItems() {
        for (long i = 1; i <= 1000; i++) {
            orderItemsRepository.save(OrderItems.builder()
                    .order(orderRepository.findById(i).get())
                    .foodName("김밥")
                    .quantity(2L)
                    .build());
        }

        for (long i = 1001; i <= 2000; i++) {
            orderItemsRepository.save(OrderItems.builder()
                    .order(orderRepository.findById(i).get())
                    .foodName("치즈 피자")
                    .quantity(1L)
                    .build());
        }
    }

    public void initOrderItemOptions() {
        for (long i = 1; i <= 1000; i++) {
            orderItemOptionsRepository.save(OrderItemOptions.builder()
                    .orderItem(orderItemsRepository.findById(i).get())
                    .foodOptionName("어묵탕")
                    .build());
        }

        for (long i = 1001; i <= 2000; i++) {
            orderItemOptionsRepository.save(OrderItemOptions.builder()
                    .orderItem(orderItemsRepository.findById(i).get())
                    .foodOptionName("치즈 오븐 스파게티")
                    .build());
        }
    }
}
