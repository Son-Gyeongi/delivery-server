package com.unknown.deliveryserver.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 적용
 * 참고 https://velog.io/@gmlstjq123/SpringBoot-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%97%90-Swagger-UI-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("음식 배달 플랫폼 API") // API의 제목
                .description("음식 배달 플랫폼") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
