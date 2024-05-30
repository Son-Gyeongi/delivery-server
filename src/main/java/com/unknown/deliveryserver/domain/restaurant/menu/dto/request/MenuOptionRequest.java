package com.unknown.deliveryserver.domain.restaurant.menu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "메뉴 옵션 저장 또는 수정 시 요청")
public class MenuOptionRequest {
    @Schema(description = "메뉴 옵션 이름")
    private String optionName;
}
