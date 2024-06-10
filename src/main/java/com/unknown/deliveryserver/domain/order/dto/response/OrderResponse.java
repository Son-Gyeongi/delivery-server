package com.unknown.deliveryserver.domain.order.dto.response;

import com.unknown.deliveryserver.domain.order.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "주문 정보 반환")
public class OrderResponse {
    @Schema(description = "주문 테이블 고유 번호")
    private Long orderId;

    @Schema(description = "주문 번호")
    private String orderNo;

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
    private List<OrderItemsResponse> orderItemsResponseList;

    public static OrderResponse of(
            Order order, List<OrderItemsResponse> orderItemsResponseList) {
        return OrderResponse.builder()
                .orderNo(order.getOrderNo())
                .orderId(order.getId())
                .address(order.getAddress())
                .addressDetail(order.getAddressDetail())
                .phone(order.getPhone())
                .recipient(order.getRecipient())
                .description(order.getDescription())
                .price(order.getPrice())
                .totalPrice(order.getTotalPrice())
                .orderItemsResponseList(orderItemsResponseList)
                .build();
    }
}
