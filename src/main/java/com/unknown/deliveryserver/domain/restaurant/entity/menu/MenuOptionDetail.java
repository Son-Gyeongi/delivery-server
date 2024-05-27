package com.unknown.deliveryserver.domain.restaurant.entity.menu;

import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuOptionDetailRequest;
import com.unknown.deliveryserver.global.common.entity.BaseEntity;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "menu_option_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private MenuOption menuOption;

    @Comment("메뉴(음식) 옵션 세부 내역")
    @Column(name = "option_detail_name", columnDefinition = "VARCHAR(200)")
    private String optionDetailName;

    @Comment("메뉴(음식) 옵션 세부 내역 추가 금액")
    @Column(name = "additional_price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal additionalPrice;

    // 연관 관계 편의 메서드
    public void addMenuOption(MenuOption menuOption) {
        this.menuOption = menuOption;
        this.menuOption.getMenuOptionDetailList().add(this); // this는 MenuOptionDetail 객체를 의미
    }

    public void modifyRequest(MenuOptionDetailRequest request) {
        this.optionDetailName = request.getOptionDetailName();
        this.additionalPrice = request.getAdditionalPrice();
    }
}
