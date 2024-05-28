package com.unknown.deliveryserver.domain.order.dto.response;

import com.unknown.deliveryserver.domain.order.entity.OrderItems;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "주문 정보 반환 - 메뉴 이름, 수량 상세 정보")
public class OrderItemsResponse {
    @Schema(description = "주문한 메뉴 고유 번호")
    private Long orderItemId;

    @Schema(description = "주문한 음식 이름")
    private String foodName;

    @Schema(description = "주문한 음식 수량")
    private Long quantity;

    @Schema(description = "해당 메뉴에 선택한 옵션들")
    private List<OrderItemOptionsResponse> orderItemOptionsResponseList;
    // TODO 옵션들에 큰 제목이 필요할 거 같은데(OrderItems 영역)

    public static OrderItemsResponse of(
            OrderItems orderItem, List<OrderItemOptionsResponse> orderItemOptionsResponseList) {
        return OrderItemsResponse.builder()
                .orderItemId(orderItem.getId())
                .foodName(orderItem.getFoodName())
                .quantity(orderItem.getQuantity())
                .orderItemOptionsResponseList(orderItemOptionsResponseList)
                .build();
    }
}
