package com.unknown.deliveryserver.domain.restaurant.dto.response.menu;

import com.unknown.deliveryserver.domain.restaurant.entity.menu.MenuOption;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "메뉴 옵션 정보 반환")
public class MenuOptionResponse {
    @Schema(description = "메뉴 고유 번호")
    private Long menuId;

    @Schema(description = "메뉴 옵션 이름")
    private String optionName;

    public static MenuOptionResponse of(MenuOption menuOption) {
        return MenuOptionResponse.builder()
                .menuId(menuOption.getId())
                .optionName(menuOption.getOptionName())
                .build();
    }
}
