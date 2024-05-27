package com.unknown.deliveryserver.domain.restaurant.api.menu;

import com.unknown.deliveryserver.domain.restaurant.application.menu.MenuOptionDetailService;
import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuOptionDetailRequest;
import com.unknown.deliveryserver.domain.restaurant.dto.response.menu.MenuOptionDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/menu-option-details")
@Tag(name = "v1-menu-option-details", description = "식당의 메뉴 옵션 상세 정보 관련 API")
public class MenuOptionDetailController {
    private final MenuOptionDetailService menuOptionDetailService;

    @Operation(summary = "메뉴 옵션 상세 추가")
    @PostMapping
    public ResponseEntity<MenuOptionDetailResponse> createMenuOptionDetail(
            @PathParam("id") Long menuOptionId,
            @RequestBody MenuOptionDetailRequest request) {
        MenuOptionDetailResponse savedMenuOptionDetail = menuOptionDetailService.createMenuOptionDetail(menuOptionId, request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMenuOptionDetail.getMenuOptionDetailId())
                .toUri();

        return ResponseEntity.created(uri).body(savedMenuOptionDetail);
    }

    @Operation(summary = "메뉴 옵션 상세 조회")
    @GetMapping("{id}")
    public ResponseEntity<MenuOptionDetailResponse> getMenuOptionDetail(@PathVariable("id") Long menuOptionDetailId) {
        MenuOptionDetailResponse foundMenuOptionDetail = menuOptionDetailService.getMenuOptionDetail(menuOptionDetailId);

        return ResponseEntity.ok().body(foundMenuOptionDetail);
    }

    @Operation(summary = "메뉴 옵션 상세 수정")
    @PutMapping("{id}")
    public ResponseEntity<MenuOptionDetailResponse> modifyMenuOptionDetail(
            @PathVariable("id") Long menuOptionDetailId,
            @RequestBody MenuOptionDetailRequest request) {
        MenuOptionDetailResponse modifiedMenuOptionDetail = menuOptionDetailService.modifyMenuOptionDetail(menuOptionDetailId, request);

        return ResponseEntity.ok().body(modifiedMenuOptionDetail);
    }

    @Operation(summary = "메뉴 옵션 상세 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity<MenuOptionDetailResponse> deleteMenuOptionDetail(@PathVariable("id") Long menuOptionDetailId) {
        menuOptionDetailService.deleteMenuOptionDetail(menuOptionDetailId);

        return ResponseEntity.noContent().build();
    }
}
