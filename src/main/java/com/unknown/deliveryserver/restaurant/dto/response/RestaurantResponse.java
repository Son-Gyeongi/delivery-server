package com.unknown.deliveryserver.restaurant.dto.response;

import com.unknown.deliveryserver.restaurant.entity.Restaurant;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class RestaurantResponse {
    private Long restaurantId;

    private String name;

    private String telephone;

    private BigDecimal minPrice;

    private BigDecimal deliveryPrice;

    public static RestaurantResponse of(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .restaurantId(restaurant.getId())
                .name(restaurant.getName())
                .telephone(restaurant.getTelephone())
                .minPrice(restaurant.getMinPrice())
                .deliveryPrice(restaurant.getDeliveryPrice())
                .build();
    }
}
