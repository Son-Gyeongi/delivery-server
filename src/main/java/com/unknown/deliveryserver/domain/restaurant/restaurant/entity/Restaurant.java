package com.unknown.deliveryserver.domain.restaurant.restaurant.entity;

import com.unknown.deliveryserver.domain.order.entity.Order;
import com.unknown.deliveryserver.domain.restaurant.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.Menu;
import com.unknown.deliveryserver.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Restaurant extends BaseEntity {

    @Comment("식당 이름")
    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Comment("식당 전화번호")
    @Column(name = "telephone", columnDefinition = "VARCHAR(50)")
    private String telephone;

    @Comment("최소 주문 금액")
    @Column(name = "min_price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal minPrice;

    @Comment("배달 금액")
    @Column(name = "delivery_price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal deliveryPrice;

    @OneToMany(mappedBy = "restaurant")
    @Builder.Default
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    @Builder.Default
    private List<Order> orderList = new ArrayList<>();

    public void modifyRequest(RestaurantRequest request) {
        this.name = request.getName();
        this.telephone = request.getTelephone();
        this.minPrice = request.getMinPrice();
        this.deliveryPrice = request.getDeliveryPrice();
    }
}
