package com.unknown.deliveryserver.domain.order.dao;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unknown.deliveryserver.domain.order.dto.response.OrderItemOptionsResponse;
import com.unknown.deliveryserver.domain.order.dto.response.OrderItemsResponse;
import com.unknown.deliveryserver.domain.order.dto.response.OrderResponse;
import com.unknown.deliveryserver.domain.order.entity.*;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.QRestaurant;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    QRestaurant qRestaurant = QRestaurant.restaurant;
    QOrder qOrder = QOrder.order;
    QOrderItems qOrderItems = QOrderItems.orderItems;
    QOrderItemOptions qOrderItemOptions = QOrderItemOptions.orderItemOptions;

    @Override
    public List<OrderResponse> findAllByRestaurant(Restaurant restaurantToFind) {
        BooleanExpression conditions = qOrder.restaurant.eq(restaurantToFind)
                .and(qOrder.id.loe(1_000_000))
                .and(qOrderItems.id.loe(1_000_000))
                .and(qOrderItemOptions.id.loe(1_000_000));

        List<Tuple> orderList = jpaQueryFactory
                .select(qOrder, qOrderItems, qOrderItemOptions)
                .from(qOrder)
                .leftJoin(qOrder.restaurant, qRestaurant).fetchJoin()
                .leftJoin(qOrder.orderItemsList, qOrderItems).fetchJoin()
                .leftJoin(qOrderItems.orderItemOptionsList, qOrderItemOptions).fetchJoin()
                .where(conditions)
                .orderBy(qOrder.id.desc())
                .fetch();

        List<OrderResponse> orderResponses = convertTupleToOrderList(orderList);

        return orderResponses;
    }

    // 페이징
    /*@Override
    public Page<Order> findAllByRestaurant(Restaurant restaurantToFind, Pageable pageable) {

        List<Order> orderList = jpaQueryFactory
                .selectFrom(order)
                .where(order.restaurant.eq(restaurantToFind))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> total = jpaQueryFactory
                .select(order.count())
                .from(order)
                .where(order.restaurant.eq(restaurantToFind));

        return PageableExecutionUtils.getPage(orderList, pageable, total::fetchOne);
    }*/

    public List<OrderResponse> convertTupleToOrderList(List<Tuple> tupleList) {
        List<Order> orderList = new ArrayList<>();
        List<OrderItems> orderItemsList = new ArrayList<>();
        List<OrderItemOptions> orderItemOptionsList = new ArrayList<>();

        for (Tuple tuple : tupleList) {
            Order orders = tuple.get(qOrder);
            OrderItems orderItems = tuple.get(qOrderItems);
            OrderItemOptions orderItemOptions = tuple.get(qOrderItemOptions);

            orderList.add(orders);
            orderItemsList.add(orderItems);
            orderItemOptionsList.add(orderItemOptions);
        }

        // List<OrderResponse> 만들기
        List<OrderResponse> orderResponseList = orderList.stream()
                .map(order -> {
                    List<OrderItemsResponse> orderItemsResponseList1 = orderItemsList.stream()
                            .filter(orderItems -> orderItems.getOrder().getId().equals(order.getId()))
                            .map(orderItems -> OrderItemsResponse.of(orderItems, orderItemOptionsList.stream()
                                    .filter(orderItemOptions -> orderItemOptions.getOrderItem().getId().equals(orderItems.getId()))
                                    .map(OrderItemOptionsResponse::of)
                                    .toList()))
                            .toList();

                    return OrderResponse.of(order, orderItemsResponseList1);
                })
                .toList();

        return orderResponseList;
    }
}
