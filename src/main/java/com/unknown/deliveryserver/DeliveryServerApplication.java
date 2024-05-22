package com.unknown.deliveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA 엔티티의 생성, 수정 시점을 자동으로 추적
public class DeliveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryServerApplication.class, args);
    }

}
