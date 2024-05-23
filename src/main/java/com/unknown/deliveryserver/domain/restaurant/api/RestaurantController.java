package com.unknown.deliveryserver.domain.restaurant.api;

import com.unknown.deliveryserver.domain.restaurant.application.RestaurantService;
import com.unknown.deliveryserver.domain.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.domain.restaurant.dto.response.RestaurantResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/restaurants")
@Tag(name = "v1-restaurants", description = "식당 관련 API")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Operation(summary = "식당 추가")
    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody RestaurantRequest request) {
        RestaurantResponse savedRestaurant = restaurantService.createRestaurant(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRestaurant.getRestaurantId())
                .toUri();

        return ResponseEntity.created(uri).body(savedRestaurant);
    }

    @Operation(summary = "식당 조회")
    @GetMapping("{id}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable("id") Long restaurantId) {
        RestaurantResponse foundRestaurant = restaurantService.getRestaurant(restaurantId);

        return ResponseEntity.ok().body(foundRestaurant);
    }

    @Operation(summary = "식당 수정")
    @PutMapping("{id}")
    public ResponseEntity<RestaurantResponse> modifyRestaurant(
            @PathVariable("id") Long restaurantId,
            @RequestBody RestaurantRequest request
    ) {
        RestaurantResponse modifiedRestaurant = restaurantService.modifyRestaurant(restaurantId, request);

        return ResponseEntity.ok().body(modifiedRestaurant);
    }

    @Operation(summary = "식당 삭제")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);

        return ResponseEntity.noContent().build();
    }
}
