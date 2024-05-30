package com.unknown.deliveryserver.domain.restaurant.menu.application;

import com.unknown.deliveryserver.domain.restaurant.menu.dao.MenuOptionDetailRepository;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.request.MenuOptionDetailRequest;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.response.MenuOptionDetailResponse;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOption;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOptionDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuOptionDetailServiceImpl implements MenuOptionDetailService{
    private final MenuOptionDetailRepository menuOptionDetailRepository;
    private final MenuOptionService menuOptionService;

    // 메뉴 옵션 디테일 등록
    @Override
    @Transactional
    public MenuOptionDetailResponse createMenuOptionDetail(Long menuOptionId, MenuOptionDetailRequest request) {
        MenuOption foundMenuOption = menuOptionService.getFoundMenuOption(menuOptionId);

        MenuOptionDetail menuOptionDetail = MenuOptionDetail.builder()
                .optionDetailName(request.getOptionDetailName())
                .additionalPrice(request.getAdditionalPrice())
                .build();
        menuOptionDetail.addMenuOption(foundMenuOption);

        MenuOptionDetail savedMenuOptionDetail = menuOptionDetailRepository.save(menuOptionDetail);

        return MenuOptionDetailResponse.of(savedMenuOptionDetail);
    }

    // 메뉴 옵션 디테일 조회
    @Override
    public MenuOptionDetailResponse getMenuOptionDetail(Long menuOptionDetailId) {
        MenuOptionDetail foundMenuOptionDetail = getFoundMenuOptionDetail(menuOptionDetailId);

        return MenuOptionDetailResponse.of(foundMenuOptionDetail);
    }

    // MenuOptionDetail 조회
    @Override
    public MenuOptionDetail getFoundMenuOptionDetail(Long menuOptionDetailId) {
        return menuOptionDetailRepository.findById(menuOptionDetailId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 메뉴 옵션 상세 정보입니다."));
    }

    // 메뉴 옵션 디테일 수정
    @Override
    @Transactional
    public MenuOptionDetailResponse modifyMenuOptionDetail(Long menuOptionDetailId, MenuOptionDetailRequest request) {
        MenuOptionDetail foundMenuOptionDetail = getFoundMenuOptionDetail(menuOptionDetailId);

        foundMenuOptionDetail.modifyRequest(request);

        return MenuOptionDetailResponse.of(foundMenuOptionDetail);
    }

    // 메뉴 옵션 디테일 삭제
    @Override
    @Transactional
    public void deleteMenuOptionDetail(Long menuOptionDetailId) {
        MenuOptionDetail foundMenuOptionDetail = getFoundMenuOptionDetail(menuOptionDetailId);

        menuOptionDetailRepository.delete(foundMenuOptionDetail);
    }
}
