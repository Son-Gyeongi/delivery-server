package com.unknown.deliveryserver.domain.restaurant.menu.application;

import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuOptionRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.request.MenuOptionRequest;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.response.MenuOptionResponse;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.Menu;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuOptionServiceImpl implements MenuOptionService {
    private final MenuOptionRepository menuOptionRepository;
    private final MenuService menuService;

    // 메뉴 옵션 등록
    @Override
    @Transactional
    public MenuOptionResponse createMenuOption(Long menuId, MenuOptionRequest request) {
        // 메뉴 조회
        Menu foundMenu = menuService.getFoundMenu(menuId);

        MenuOption menuOption = MenuOption.builder()
                .optionName(request.getOptionName())
                .build();
        menuOption.addMenu(foundMenu);

        MenuOption savedMenuOption = menuOptionRepository.save(menuOption);

        return MenuOptionResponse.of(savedMenuOption);
    }

    // 메뉴 옵션 조회
    @Override
    public MenuOptionResponse getMenuOption(Long menuOptionId) {
        MenuOption foundMenuOption = getFoundMenuOption(menuOptionId);

        return MenuOptionResponse.of(foundMenuOption);
    }

    // MenuOption 조회
    @Override
    public MenuOption getFoundMenuOption(Long menuOptionId) {
        return menuOptionRepository.findById(menuOptionId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 메뉴 옵션입니다."));
    }

    // 메뉴 옵션 수정
    @Override
    @Transactional
    public MenuOptionResponse modifyMenuOption(Long menuOptionId, MenuOptionRequest request) {
        MenuOption foundMenuOption = getFoundMenuOption(menuOptionId);

        foundMenuOption.modifyRequest(request);

        return MenuOptionResponse.of(foundMenuOption);
    }

    // 메뉴 옵션 삭제
    @Override
    @Transactional
    public void deleteMenuOption(Long menuOptionId) {
        MenuOption foundMenuOption = getFoundMenuOption(menuOptionId);

        menuOptionRepository.delete(foundMenuOption);
    }
}
