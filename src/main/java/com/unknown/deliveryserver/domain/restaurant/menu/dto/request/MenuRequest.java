package com.unknown.deliveryserver.domain.restaurant.menu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "메뉴 저장 또는 수정 시 요청")
public class MenuRequest {
    @Schema(description = "대표 메뉴")
    private Boolean isPrimary;

    @Schema(description = "메뉴 이름")
    private String foodName;

    @Schema(description = "메뉴 가격")
    private BigDecimal price;
}
