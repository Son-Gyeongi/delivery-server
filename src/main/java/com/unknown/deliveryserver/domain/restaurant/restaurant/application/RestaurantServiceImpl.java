package com.unknown.deliveryserver.domain.restaurant.restaurant.application;

import com.unknown.deliveryserver.domain.restaurant.restaurant.dao.RestaurantRepository;
import com.unknown.deliveryserver.domain.restaurant.restaurant.dto.response.RestaurantResponse;
import com.unknown.deliveryserver.domain.restaurant.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // 식당 등록
    @Override
    @Transactional
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .telephone(request.getTelephone())
                .minPrice(request.getMinPrice())
                .deliveryPrice(request.getDeliveryPrice())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantResponse.of(savedRestaurant);
    }

    // 식당 조회
    @Override
    public RestaurantResponse getRestaurant(Long restaurantId) {
        Restaurant foundRestaurant = getFoundRestaurant(restaurantId);

        return RestaurantResponse.of(foundRestaurant);
    }

    // Restaurant 조회
    @Override
    public Restaurant getFoundRestaurant(Long restaurantId) {
        return restaurantRepository.findByIdAndDeletedAtIsNull(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 식당입니다."));
    }

    // 식당 수정
    @Override
    @Transactional
    public RestaurantResponse modifyRestaurant(Long restaurantId, RestaurantRequest request) {
        Restaurant foundRestaurant = getFoundRestaurant(restaurantId);

        // @Transactional 있으니깐 더티체킹으로 저장되는지 확인하기 - 저장 확인함
        foundRestaurant.modifyRequest(request);

        return RestaurantResponse.of(foundRestaurant);
    }

    // 식당 삭제
    @Override
    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        Restaurant foundRestaurant = getFoundRestaurant(restaurantId);

        // 연관된 메뉴도 다 삭제 되려나 - 같이 삭제되는 거 확인
        restaurantRepository.delete(foundRestaurant);
    }
}
