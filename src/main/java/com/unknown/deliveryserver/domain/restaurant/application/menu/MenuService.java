package com.unknown.deliveryserver.domain.restaurant.application.menu;

import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuRequest;
import com.unknown.deliveryserver.domain.restaurant.dto.response.menu.MenuResponse;
import com.unknown.deliveryserver.domain.restaurant.entity.menu.Menu;

public interface MenuService {
    // 메뉴 등록
    MenuResponse createMenu(Long restaurantId, MenuRequest request);

    // 메뉴 조회
    MenuResponse getMenu(Long menuId);

    // Menu 조회
    Menu getFoundMenu(Long menuId);

    // 메뉴 수정
    MenuResponse modifyMenu(Long menuId, MenuRequest request);

    // 메뉴 삭제
    void deleteMenu(Long menuId);
}
