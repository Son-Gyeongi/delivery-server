package com.unknown.deliveryserver.domain.restaurant.dto.response;

import com.unknown.deliveryserver.domain.restaurant.entity.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "식당 정보 반환")
public class RestaurantResponse {
    @Schema(description = "식당 고유 번호")
    private Long restaurantId;

    @Schema(description = "식당 이름")
    private String name;

    @Schema(description = "식당 전화 번호")
    private String telephone;

    @Schema(description = "주문 최소 금액")
    private BigDecimal minPrice;

    @Schema(description = "배달 금액")
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
