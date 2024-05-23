package com.unknown.deliveryserver.restaurant.application;

import com.unknown.deliveryserver.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.restaurant.dto.response.RestaurantResponse;

public interface RestaurantService {
    // 식당 등록
    RestaurantResponse createRestaurant(RestaurantRequest request);

    // 식당 조회
    RestaurantResponse getRestaurant(Long restaurantId);

    // 식당 수정
    RestaurantResponse modifyRestaurant(Long restaurantId, RestaurantRequest request);

    // 식당 삭제
    void deleteRestaurant(Long restaurantId);
}
