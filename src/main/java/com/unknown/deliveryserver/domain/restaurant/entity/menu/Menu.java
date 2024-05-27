package com.unknown.deliveryserver.domain.restaurant.entity.menu;

import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuRequest;
import com.unknown.deliveryserver.domain.restaurant.entity.Restaurant;
import com.unknown.deliveryserver.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "store_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
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

    @OneToMany(mappedBy = "menu")
    @Builder.Default
    private List<MenuOption> menuOptionList = new ArrayList<>();

    // 연관 관계 편의 메서드
    public void addRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.restaurant.getMenuList().add(this); // this는 Menu 객체를 의미
    }

    public void modifyRequest(MenuRequest request) {
        this.isPrimary = request.getIsPrimary();
        this.foodName = request.getFoodName();
        this.price = request.getPrice();
    }
}
