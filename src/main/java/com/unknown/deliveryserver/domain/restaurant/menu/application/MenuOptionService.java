package com.unknown.deliveryserver.domain.restaurant.menu.application;

import com.unknown.deliveryserver.domain.restaurant.menu.dto.request.MenuOptionRequest;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.response.MenuOptionResponse;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOption;

public interface MenuOptionService {
    // 메뉴 옵션 등록
    MenuOptionResponse createMenuOption(Long menuId, MenuOptionRequest request);

    // 메뉴 옵션 조회
    MenuOptionResponse getMenuOption(Long menuOptionId);

    // MenuOption 조회
    MenuOption getFoundMenuOption(Long menuOptionId);

    // 메뉴 옵션 수정
    MenuOptionResponse modifyMenuOption(Long menuOptionId, MenuOptionRequest request);

    // 메뉴 옵션 삭제
    void deleteMenuOption(Long menuOptionId);
}
