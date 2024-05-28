package com.unknown.deliveryserver.domain.order.entity;

import com.unknown.deliveryserver.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private Order order;

    @Comment("주문한 음식 이름")
    @Column(name = "food_name", columnDefinition = "VARCHAR(200)")
    private String foodName;

    @Comment("주문한 음식 수량")
    @Column(name = "quantity", columnDefinition = "BIGINT")
    private Long quantity;

    @OneToMany(mappedBy = "orderItem")
    @Builder.Default
    private List<OrderItemOptions> orderItemOptionsList = new ArrayList<>();

    // 연관 관계 편의 메서드
    public void addOrder(Order order) {
        this.order = order;
        this.order.getOrderItemsList().add(this); // this는 OrderItems 객체를 의미
    }
}
