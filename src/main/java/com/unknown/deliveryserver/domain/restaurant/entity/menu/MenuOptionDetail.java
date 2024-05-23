package com.unknown.deliveryserver.domain.restaurant.entity.menu;

import com.unknown.deliveryserver.global.common.entity.BaseEntity;
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
public class MenuOptionDetail extends BaseEntity {

    @ManyToOne // 다대일 단방향 (외래키를 갖는 쪽이 주인 엔티티)
    @JoinColumn(name = "menu_option_id")
    @ToString.Exclude
    private MenuOption menuOption;

    @Comment("메뉴(음식) 옵션 세부 내역")
    @Column(name = "option_detail_name", columnDefinition = "VARCHAR(200)")
    private String optionDetailName;

    @Comment("메뉴(음식) 옵션 세부 내역 추가 금액")
    @Column(name = "additional_price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal additionalPrice;
}
