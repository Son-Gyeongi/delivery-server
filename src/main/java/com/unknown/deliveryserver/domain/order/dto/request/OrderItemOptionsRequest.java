package com.unknown.deliveryserver.domain.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "주문 저장 또는 수정 시 요청 - 주문 메뉴 옵션들")
public class OrderItemOptionsRequest {
    @Schema(description = "주문한 음식에 선택한 옵션 이름")
    private String foodOptionName;
}
