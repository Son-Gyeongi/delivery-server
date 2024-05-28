package com.unknown.deliveryserver.domain.order.entity;

import com.unknown.deliveryserver.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemOptions extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_item_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private OrderItems orderItem;

    @Comment("주문한 음식에 선택한 옵션 이름")
    @Column(name = "food_option_name", columnDefinition = "VARCHAR(200)")
    private String foodOptionName;

    // 연관 관계 편의 메서드
    public void addOrderItem(OrderItems orderItem) {
        this.orderItem = orderItem;
        this.orderItem.getOrderItemOptionsList().add(this); // this는 OrderItemOptions 객체를 의미
    }
}
