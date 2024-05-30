package com.unknown.deliveryserver.domain.restaurant.restaurant.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "식당 저장 또는 수정 시 요청")
public class RestaurantRequest {
    @Schema(description = "식당 이름")
    private String name;

    @Schema(description = "식당 전화 번호")
    private String telephone;

    @Schema(description = "주문 최소 금액")
    private BigDecimal minPrice;

    @Schema(description = "배달 금액")
    private BigDecimal deliveryPrice;
}
