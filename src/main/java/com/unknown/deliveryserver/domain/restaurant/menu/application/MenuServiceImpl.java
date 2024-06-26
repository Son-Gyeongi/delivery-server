package com.unknown.deliveryserver.domain.restaurant.menu.application;

import com.unknown.deliveryserver.domain.restaurant.restaurant.application.RestaurantService;
import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.request.MenuRequest;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.response.MenuResponse;
import com.unknown.deliveryserver.domain.restaurant.restaurant.entity.Restaurant;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantService restaurantService;

    // 메뉴 등록
    @Override
    @Transactional
    public MenuResponse createMenu(Long restaurantId, MenuRequest request) {
        // 레스토랑 조회
        Restaurant foundRestaurant = restaurantService.getFoundRestaurant(restaurantId);

        Menu menu = Menu.builder()
                .isPrimary(request.getIsPrimary())
                .foodName(request.getFoodName())
                .price(request.getPrice())
                .build();
        menu.addRestaurant(foundRestaurant);

        Menu savedMenu = menuRepository.save(menu);

        return MenuResponse.of(savedMenu);
    }

    // 메뉴 조회
    @Override
    public MenuResponse getMenu(Long menuId) {
        Menu foundMenu = getFoundMenu(menuId);

        return MenuResponse.of(foundMenu);
    }

    // Menu 조회
    @Override
    public Menu getFoundMenu(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 메뉴입니다."));
    }

    // 메뉴 수정
    @Override
    @Transactional
    public MenuResponse modifyMenu(Long menuId, MenuRequest request) {
        Menu foundMenu = getFoundMenu(menuId);

        foundMenu.modifyRequest(request);

        return MenuResponse.of(foundMenu);
    }

    // 메뉴 삭제
    @Override
    @Transactional
    public void deleteMenu(Long menuId) {
        Menu foundMenu = getFoundMenu(menuId);

        // 연관된 옵션도 다 삭제 되려나 - 삭제 확인
        menuRepository.delete(foundMenu);
    }
}
