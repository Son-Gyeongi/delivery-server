package com.unknown.deliveryserver.domain.order.entity;

import com.unknown.deliveryserver.domain.order.enumerated.OrderStatus;
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
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "restaurant_id", columnDefinition = "BIGINT", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ToString.Exclude
    private Restaurant restaurant;

    @Comment("주문 상태 정보") // 주문 완료, 준비중, 주문 취소, 배송 완료
    @Column(name = "status", columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Comment("배달 도착 주소")
    @Column(name = "address", columnDefinition = "VARCHAR(200)")
    private String address;

    @Comment("배달 도착 주소 상세 주소")
    @Column(name = "address_detail", columnDefinition = "VARCHAR(200)")
    private String addressDetail;

    @Comment("주문한 사람 전화번호")
    @Column(name = "phone", columnDefinition = "VARCHAR(50)")
    private String phone;

    @Comment("주문한 사람")
    @Column(name = "recipient", columnDefinition = "VARCHAR(20)")
    private String recipient;

    @Comment("주문 요청 사항")
    @Column(name = "description", columnDefinition = "VARCHAR(200)")
    private String description;

    // 소수점 이하 자릿수를 정확하게 유지하려면 BigDecimal과 같은 고정 소수점 형식을 사용
    @Comment("주문 메뉴 금액")
    @Column(name = "price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal price;

    @Comment("주문 총 금액(배달 금액 포함)")
    @Column(name = "total_price", columnDefinition = "DECIMAL(64, 3)")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderItems> orderItemsList = new ArrayList<>();

    // 연관 관계 편의 메서드
    public void addRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.restaurant.getOrderList().add(this); // this는 Order 객체를 의미
    }
}
