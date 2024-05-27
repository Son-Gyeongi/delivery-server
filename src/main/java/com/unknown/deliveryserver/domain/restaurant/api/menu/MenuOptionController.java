package com.unknown.deliveryserver.domain.restaurant.api.menu;

import com.unknown.deliveryserver.domain.restaurant.application.menu.MenuOptionService;
import com.unknown.deliveryserver.domain.restaurant.dto.request.menu.MenuOptionRequest;
import com.unknown.deliveryserver.domain.restaurant.dto.response.menu.MenuOptionResponse;
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
@RequestMapping("/v1/menu-options")
@Tag(name = "v1-menu-options", description = "식당의 메뉴 옵션 관련 API")
public class MenuOptionController {
    private final MenuOptionService menuOptionService;

    @Operation(summary = "메뉴 옵션 추가")
    @PostMapping
    public ResponseEntity<MenuOptionResponse> createMenuOption(
            @PathParam("id") Long menuId,
            @RequestBody MenuOptionRequest request) {
        MenuOptionResponse savedMenuOption = menuOptionService.createMenuOption(menuId, request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMenuOption.getMenuOptionId())
                .toUri();

        return ResponseEntity.created(uri).body(savedMenuOption);
    }

    @Operation(summary = "메뉴 옵션 조회")
    @GetMapping("{id}")
    public ResponseEntity<MenuOptionResponse> getMenuOption(@PathVariable("id") Long menuOptionId) {
        MenuOptionResponse foundMenuOption = menuOptionService.getMenuOption(menuOptionId);

        return ResponseEntity.ok().body(foundMenuOption);
    }

    @Operation(summary = "메뉴 옵션 수정")
    @PutMapping("{id}")
    public ResponseEntity<MenuOptionResponse> modifyMenuOption(
            @PathVariable("id") Long menuOptionId,
            @RequestBody MenuOptionRequest request) {
        MenuOptionResponse modifiedMenuOption = menuOptionService.modifyMenuOption(menuOptionId, request);

        return ResponseEntity.ok().body(modifiedMenuOption);
    }

    @Operation(summary = "메뉴 옵션 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMenuOption(@PathVariable("id") Long menuOptionId) {
        menuOptionService.deleteMenuOption(menuOptionId);

        return ResponseEntity.noContent().build();
    }
}
