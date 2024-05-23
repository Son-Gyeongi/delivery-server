package com.unknown.deliveryserver.domain.order.enumerated;

/**
 * 주문 상태
 */
public enum OrderStatus {
    /**
     * 주문 접수 - 고객이 주문 접수한 상태, 식당 측에서는 아직 수락을 하지 않은 상태
     */
    ORDER_RECEIVED,
    /**
     * 준비 중 - 식당 측에서 주문 수락 후 음식 준비하는 상태
     */
    PREPARING,
    /**
     * 준비 완료 - 배달 가기 전 음식 준비가 완료된 상태
     */
    READY,
    /**
     * 배달 중 - 배달 기사님이 음식을 가져간 후 배달 중인 상태
     */
    ON_DELIVERY,
    /**
     * 배달 완료 - 배달 기사님이 음식을 주문한 주소지에 가져다 놓은 상태
     */
    DELIVERY_COMPLETED,
    /**
     * 주문 취소 - 식당 측에서 수락하기 전 고객이 취소하거나, 식당 측에서 취소한 경우
     */
    CANCELED_ORDER,
    /**
     * 문제 발생 - 주문 처리 중에 예기치 못한 문제가 발생할 경우
     */
    ISSUE_DETECTED
}
