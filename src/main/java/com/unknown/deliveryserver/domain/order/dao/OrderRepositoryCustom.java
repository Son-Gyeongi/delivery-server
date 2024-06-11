package com.unknown.deliveryserver.domain.order.dao;

import com.unknown.deliveryserver.domain.order.entity.Order;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.Restaurant;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findAllByRestaurant(Restaurant restaurant);
}
