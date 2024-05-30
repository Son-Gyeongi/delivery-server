package com.unknown.deliveryserver.domain.restaurant.menu.dto.response;

import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOptionDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "메뉴 옵션 상세 정보 반환")
public class MenuOptionDetailResponse {
    @Schema(description = "메뉴 옵션 고유 번호")
    private Long menuOptionDetailId;

    @Schema(description = "메뉴(음식) 옵션 세부 내역 이름")
    private String optionDetailName;

    @Schema(description = "메뉴(음식) 옵션 세부 내역 추가 금액")
    private BigDecimal additionalPrice;

    public static MenuOptionDetailResponse of(MenuOptionDetail menuOptionDetail) {
        return MenuOptionDetailResponse.builder()
                .menuOptionDetailId(menuOptionDetail.getId())
                .optionDetailName(menuOptionDetail.getOptionDetailName())
                .additionalPrice(menuOptionDetail.getAdditionalPrice())
                .build();
    }
}
