package com.unknown.deliveryserver.global.global.init;

import com.unknown.deliveryserver.domain.order.dao.OrderRepository;
import com.unknown.deliveryserver.domain.restaurant.restaurant.dao.RestaurantRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuOptionDetailRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuOptionRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuRepository;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.Restaurant;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.Menu;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOption;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOptionDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Slf4j
@Profile("!prod & !test")
@RequiredArgsConstructor
@Configuration
public class NotProd {

    @Autowired
    @Lazy
    private NotProd self;

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final MenuOptionDetailRepository menuOptionDetailRepository;
    private final OrderRepository orderRepository;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (!restaurantRepository.findAll().isEmpty()) {
                return;
            }

            initRestaurant();
            initMenu();
            initMenuOption();
            initMenuOptionDetail();
        };
    }

    public void initRestaurant() {
        restaurantRepository.save(Restaurant.builder().name("맛나 분식").telephone("050-1234-1234").minPrice(BigDecimal.valueOf(10000)).deliveryPrice(BigDecimal.valueOf(1000)).build());
        restaurantRepository.save(Restaurant.builder().name("맛나 피자").telephone("050-5678-5678").minPrice(BigDecimal.valueOf(20000)).deliveryPrice(BigDecimal.valueOf(2000)).build());
    }

    public void initMenu() {
        // 맛나 분식
        menuRepository.save(Menu.builder().restaurant(restaurantRepository.findByIdAndDeletedAtIsNull(1L).get()).isPrimary(true).foodName("김밥").price(BigDecimal.valueOf(4000)).build());
        menuRepository.save(Menu.builder().restaurant(restaurantRepository.findByIdAndDeletedAtIsNull(1L).get()).isPrimary(false).foodName("떡볶이").price(BigDecimal.valueOf(5000)).build());
    }

    public void initMenuOption() {
        // 맛나 분식
        menuOptionRepository.save(MenuOption.builder().menu(menuRepository.findById(1L).get()).optionName("사이드").build());
        menuOptionRepository.save(MenuOption.builder().menu(menuRepository.findById(2L).get()).optionName("추가 재료").build());
    }

    public void initMenuOptionDetail() {
        // 맛나 분식 - 김밥 옵션
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(1L).get()).optionDetailName("떡볶이").additionalPrice(BigDecimal.valueOf(5000)).build());
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(1L).get()).optionDetailName("어묵탕").additionalPrice(BigDecimal.valueOf(5000)).build());

        // 맛나 분식 - 떡볶이 옵션
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(2L).get()).optionDetailName("치즈").additionalPrice(BigDecimal.valueOf(2000)).build());
        menuOptionDetailRepository.save(MenuOptionDetail.builder().menuOption(menuOptionRepository.findById(2L).get()).optionDetailName("계란").additionalPrice(BigDecimal.valueOf(2000)).build());
    }
}
