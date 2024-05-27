package com.unknown.deliveryserver.domain.restaurant.api;

import com.unknown.deliveryserver.domain.restaurant.application.menu.MenuService;
import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuRequest;
import com.unknown.deliveryserver.domain.restaurant.dto.response.menu.MenuResponse;
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
@RequestMapping("/v1/menus")
@Tag(name = "v1-menus", description = "식당의 메뉴 관련 API")
public class MenuController {
    private final MenuService menuService;

    @Operation(summary = "메뉴 추가")
    @PostMapping
    public ResponseEntity<MenuResponse> createRestaurant(
            @PathParam("id") Long restaurantId,
            @RequestBody MenuRequest request) {
        MenuResponse savedMenu = menuService.createMenu(restaurantId, request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMenu.getMenuId())
                .toUri();

        return ResponseEntity.created(uri).body(savedMenu);
    }

    @Operation(summary = "메뉴 조회")
    @GetMapping("{id}")
    public ResponseEntity<MenuResponse> getRestaurant(@PathVariable("id") Long menuId) {
        MenuResponse foundMenu = menuService.getMenu(menuId);

        return ResponseEntity.ok().body(foundMenu);
    }

    @Operation(summary = "메뉴 수정")
    @PutMapping("{id}")
    public ResponseEntity<MenuResponse> modifyRestaurant(
            @PathVariable("id") Long menuId,
            @RequestBody MenuRequest request
    ) {
        MenuResponse modifiedMenu = menuService.modifyMenu(menuId, request);

        return ResponseEntity.ok().body(modifiedMenu);
    }

    @Operation(summary = "메뉴 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long menuId) {
        menuService.deleteMenu(menuId);

        return ResponseEntity.noContent().build();
    }
}
