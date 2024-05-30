package com.unknown.deliveryserver.domain.restaurant.menu.application;

import com.unknown.deliveryserver.domain.restaurant.menu.dto.request.MenuOptionDetailRequest;
import com.unknown.deliveryserver.domain.restaurant.menu.dto.response.MenuOptionDetailResponse;
import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOptionDetail;

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
