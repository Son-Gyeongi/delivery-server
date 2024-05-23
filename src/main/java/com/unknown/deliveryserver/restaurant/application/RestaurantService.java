package com.unknown.deliveryserver.restaurant.application;

import com.unknown.deliveryserver.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.restaurant.dto.response.RestaurantResponse;

public interface RestaurantService {
    // 가게 등록
    RestaurantResponse createRestaurant(RestaurantRequest request);

    // 가게 조회
    RestaurantResponse getRestaurant(Long restaurantId);

    // 가게 수정
    RestaurantResponse modifyRestaurant(Long restaurantId, RestaurantRequest request);

    // 가게 삭제
    void deleteRestaurant(Long restaurantId);
}
