package com.unknown.deliveryserver.restaurant.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class RestaurantRequest {
    private String name;

    private String telephone;

    private BigDecimal minPrice;

    private BigDecimal deliveryPrice;
}
