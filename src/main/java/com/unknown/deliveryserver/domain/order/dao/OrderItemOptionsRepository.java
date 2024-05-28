package com.unknown.deliveryserver.domain.order.dao;

import com.unknown.deliveryserver.domain.order.entity.OrderItemOptions;
import com.unknown.deliveryserver.domain.order.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemOptionsRepository extends JpaRepository<OrderItemOptions, Long> {
    Optional<OrderItemOptions> findByOrderItem(OrderItems orderItem);
}
