package com.unknown.deliveryserver.domain.order.dao;

import com.unknown.deliveryserver.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    Optional<Order> findByIdAndDeletedAtIsNull(Long orderId);

//    List<Order> findByRestaurant(Restaurant restaurant);
}
