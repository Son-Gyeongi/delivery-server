package com.unknown.deliveryserver.restaurant.application.menu;

import com.unknown.deliveryserver.restaurant.dao.RestaurantRepository;
import com.unknown.deliveryserver.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.restaurant.dto.response.RestaurantResponse;
import com.unknown.deliveryserver.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // 가게 등록
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

    // 가게 조회
    @Override
    public RestaurantResponse getRestaurant(Long restaurantId) {
        Restaurant foundRestaurant = getFoundRestaurant(restaurantId);

        return RestaurantResponse.of(foundRestaurant);
    }

    // Restaurant 조회
    private Restaurant getFoundRestaurant(Long restaurantId) {
        return restaurantRepository.findByIdAndDeletedAtIsNull(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 가게입니다."));
    }

    // 가게 수정
    @Override
    @Transactional
    public RestaurantResponse modifyRestaurant(Long restaurantId, RestaurantRequest request) {
        Restaurant foundRestaurant = getFoundRestaurant(restaurantId);

        // TODO @Transactional 있으니깐 더티체킹으로 저장되는지 확인하기
        foundRestaurant.modifyRequest(request);

        return RestaurantResponse.of(foundRestaurant);
    }

    // 가게 삭제
    @Override
    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        Restaurant foundRestaurant = getFoundRestaurant(restaurantId);

        // TODO 연관된 메뉴도 다 삭제 되려나
        restaurantRepository.delete(foundRestaurant);
    }
}
