package com.unknown.deliveryserver.domain.order.dao;

import com.unknown.deliveryserver.domain.order.entity.Order;
import com.unknown.deliveryserver.domain.order.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    Optional<OrderItems> findByOrder(Order order);
}
