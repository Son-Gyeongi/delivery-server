package com.unknown.deliveryserver.domain.order.application;

import com.unknown.deliveryserver.domain.order.dto.response.OrderResponse;
import com.unknown.deliveryserver.domain.order.dto.request.OrderRequest;
import com.unknown.deliveryserver.domain.order.entity.Order;

public interface OrderService {
    // 주문 하기
    OrderResponse createOrder(Long restaurantId, OrderRequest request);

    // 주문 조회
    OrderResponse getOrder(Long orderId);

    // Order 조회
    Order getFoundOrder(Long orderId);

    // 주문 수정
    OrderResponse modifyOrder(Long orderId, OrderRequest request);

    // 주문 삭제
    void deleteOrder(Long orderId);
}
