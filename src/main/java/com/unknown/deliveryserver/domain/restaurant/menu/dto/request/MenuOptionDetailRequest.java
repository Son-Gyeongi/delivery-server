package com.unknown.deliveryserver.domain.restaurant.menu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "메뉴 옵션 상세 정보 저장 또는 수정 시 요청")
public class MenuOptionDetailRequest {
    @Schema(description = "메뉴(음식) 옵션 세부 내역 이름")
    private String optionDetailName;

    @Schema(description = "메뉴(음식) 옵션 세부 내역 추가 금액")
    private BigDecimal additionalPrice;
}
