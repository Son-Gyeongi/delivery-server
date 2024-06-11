package com.unknown.deliveryserver.domain.order.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unknown.deliveryserver.domain.order.entity.Order;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Order> findAllByRestaurant(Restaurant restaurant) {
        return null;
    }
}
