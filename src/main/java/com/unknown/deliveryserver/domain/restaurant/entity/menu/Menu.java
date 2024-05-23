package com.unknown.deliveryserver.domain.restaurant.entity.menu;

import com.unknown.deliveryserver.global.common.entity.BaseEntity;
import com.unknown.deliveryserver.domain.restaurant.entity.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @ManyToOne // 다대일 단방향 (외래키를 갖는 쪽이 주인 엔티티)
    @JoinColumn(name = "store_id")
    @ToString.Exclude
    private Restaurant restaurant;

    @Comment("대표메뉴 여부")
    @Column(name = "is_primary", columnDefinition = "BOOLEAN")
    private String isPrimary;

    @Comment("메뉴(음식) 이름")
    @Column(name = "food_name", columnDefinition = "VARCHAR(200)")
    private String foodName;

    @Comment("메뉴(음식) 가격")
    @Column(name = "price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal price;
}
