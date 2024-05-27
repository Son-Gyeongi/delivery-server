package com.unknown.deliveryserver.domain.restaurant.entity.menu;

import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuOptionRequest;
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
public class MenuOption extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "menu_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private Menu menu;

    @Comment("메뉴(음식) 옵션 큰 제목")
    @Column(name = "option_name", columnDefinition = "VARCHAR(200)")
    private String optionName;

    // 연관 관계 편의 메서드
    public void addMenu(Menu menu) {
        this.menu = menu;
        this.menu.getMenuOptionList().add(this); // this는 MenuOption 객체를 의미
    }

    public void modifyRequest(MenuOptionRequest request) {
        this.optionName = request.getOptionName();
    }
}
