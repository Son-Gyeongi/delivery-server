package com.unknown.deliveryserver.domain.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "주문 저장 또는 수정 시 요청")
public class OrderRequest {
    @Schema(description = "배달 도착 주소")
    private String address;

    @Schema(description = "배달 도착 주소 상세 주소")
    private String addressDetail;

    @Schema(description = "주문한 사람 전화번호")
    private String phone;

    @Schema(description = "주문한 사람")
    private String recipient;

    @Schema(description = "주문 요청 사항")
    private String description;

    @Schema(description = "주문 메뉴 금액")
    private BigDecimal price;

    @Schema(description = "주문 총 금액(배달 금액 포함)")
    private BigDecimal totalPrice;

    @Schema(description = "주문한 메뉴들")
    private List<OrderItemsRequest> orderItemsRequestList;
}
