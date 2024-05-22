package com.unknown.deliveryserver.restaurant.entity.menu;

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
public class MenuOption extends BaseEntity {

    @ManyToOne // 다대일 단방향 (외래키를 갖는 쪽이 주인 엔티티)
    @JoinColumn(name = "menu_id")
    @ToString.Exclude
    private Menu menu;

    @Comment("메뉴(음식) 옵션 큰 제목")
    @Column(name = "option_name", columnDefinition = "VARCHAR(200)")
    private String optionName;
}
