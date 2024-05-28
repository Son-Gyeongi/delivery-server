package com.unknown.deliveryserver.domain.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "주문 저장 또는 수정 시 요청 - 주문 메뉴")
public class OrderItemsRequest {
    @Schema(description = "주문한 음식 이름")
    private String foodName;

    @Schema(description = "주문한 음식 수량")
    private Long quantity;

    @Schema(description = "주문한 메뉴 옵션들")
    private List<OrderItemOptionsRequest> orderItemOptionsRequestList;
}
