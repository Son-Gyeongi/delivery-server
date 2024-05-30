package com.unknown.deliveryserver.domain.restaurant.menu.dto.response;

import com.unknown.deliveryserver.domain.restaurant.menu.entity.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "메뉴 정보 반환")
public class MenuResponse {
    @Schema(description = "메뉴 고유 번호")
    private Long menuId;

    @Schema(description = "대표 메뉴")
    private Boolean isPrimary;

    @Schema(description = "메뉴 이름")
    private String foodName;

    @Schema(description = "메뉴 가격")
    private BigDecimal price;

    public static MenuResponse of(Menu menu) {
        return MenuResponse.builder()
                .menuId(menu.getId())
                .isPrimary(menu.getIsPrimary())
                .foodName(menu.getFoodName())
                .price(menu.getPrice())
                .build();
    }
}
