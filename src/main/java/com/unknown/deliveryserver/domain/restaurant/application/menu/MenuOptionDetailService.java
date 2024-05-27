package com.unknown.deliveryserver.domain.restaurant.application.menu;

import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuOptionDetailRequest;
import com.unknown.deliveryserver.domain.restaurant.dto.response.menu.MenuOptionDetailResponse;
import com.unknown.deliveryserver.domain.restaurant.entity.menu.MenuOptionDetail;

public interface MenuOptionDetailService {
    // 메뉴 옵션 디테일 등록
    MenuOptionDetailResponse createMenuOptionDetail(Long menuOptionId, MenuOptionDetailRequest request);

    // 메뉴 옵션 디테일 조회
    MenuOptionDetailResponse getMenuOptionDetail(Long menuOptionDetailId);

    // MenuOptionDetail 조회
    MenuOptionDetail getFoundMenuOptionDetail(Long menuOptionDetailId);

    // 메뉴 옵션 디테일 수정
    MenuOptionDetailResponse modifyMenuOptionDetail(Long menuOptionDetailId, MenuOptionDetailRequest request);

    // 메뉴 옵션 디테일 삭제
    void deleteMenuOptionDetail(Long menuOptionDetailId);
}
