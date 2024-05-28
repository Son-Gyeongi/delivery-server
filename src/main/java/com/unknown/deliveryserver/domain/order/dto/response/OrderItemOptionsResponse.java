package com.unknown.deliveryserver.domain.order.dto.response;

import com.unknown.deliveryserver.domain.order.entity.OrderItemOptions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "주문 정보 반환 - 메뉴 옵션 상세 정보")
public class OrderItemOptionsResponse {
    @Schema(description = "주문한 메뉴의 상세 옵션 고유 번호")
    private Long orderItemOptionId;

    @Schema(description = "주문한 음식에 선택한 옵션 이름")
    private String foodOptionName;

    public static OrderItemOptionsResponse of(OrderItemOptions orderItemOption) {
        return OrderItemOptionsResponse.builder()
                .orderItemOptionId(orderItemOption.getId())
                .foodOptionName(orderItemOption.getFoodOptionName())
                .build();
    }
}
