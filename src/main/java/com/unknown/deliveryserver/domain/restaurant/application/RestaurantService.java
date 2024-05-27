package com.unknown.deliveryserver.domain.restaurant.application;

import com.unknown.deliveryserver.domain.restaurant.dto.response.RestaurantResponse;
import com.unknown.deliveryserver.domain.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.domain.restaurant.entity.Restaurant;

public interface RestaurantService {
    // 식당 등록
    RestaurantResponse createRestaurant(RestaurantRequest request);

    // 식당 조회
    RestaurantResponse getRestaurant(Long restaurantId);

    // Restaurant 조회
    Restaurant getFoundRestaurant(Long restaurantId);

    // 식당 수정
    RestaurantResponse modifyRestaurant(Long restaurantId, RestaurantRequest request);

    // 식당 삭제
    void deleteRestaurant(Long restaurantId);
}
