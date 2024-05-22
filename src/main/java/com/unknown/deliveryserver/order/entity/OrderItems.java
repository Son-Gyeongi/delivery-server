package com.unknown.deliveryserver.order.entity;

import com.unknown.deliveryserver.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems extends BaseEntity {

    @ManyToOne // 다대일 단방향 (외래키를 갖는 쪽이 주인 엔티티)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;

    @Comment("주문한 음식 이름")
    @Column(name = "food_name", columnDefinition = "VARCHAR(200)")
    private String foodName;

    @Comment("주문한 음식 수량")
    @Column(name = "quantity", columnDefinition = "BIGINT")
    private Long quantity;
}
