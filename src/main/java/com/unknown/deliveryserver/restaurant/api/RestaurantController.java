package com.unknown.deliveryserver.restaurant.api;

import com.unknown.deliveryserver.restaurant.application.RestaurantService;
import com.unknown.deliveryserver.restaurant.dto.request.RestaurantRequest;
import com.unknown.deliveryserver.restaurant.dto.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    // 가게 저장
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

    // 가게 조회
    @GetMapping("{id}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable("id") Long restaurantId) {
        RestaurantResponse foundRestaurant = restaurantService.getRestaurant(restaurantId);

        return ResponseEntity.ok().body(foundRestaurant);
    }

    // 가게 수정
    @PutMapping("{id}")
    public ResponseEntity<RestaurantResponse> modifyRestaurant(
            @PathVariable("id") Long restaurantId,
            @RequestBody RestaurantRequest request
    ) {
        RestaurantResponse modifiedRestaurant = restaurantService.modifyRestaurant(restaurantId, request);

        return ResponseEntity.ok().body(modifiedRestaurant);
    }

    // 가게 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);

        return ResponseEntity.noContent().build();
    }
}
