package com.unknown.deliveryserver.domain.order.api;

import com.unknown.deliveryserver.domain.order.application.OrderService;
import com.unknown.deliveryserver.domain.order.dto.request.OrderRequest;
import com.unknown.deliveryserver.domain.order.dto.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
@Tag(name = "v1-orders", description = "주문 관련 API")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "주문하기")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestParam("restaurantId") Long restaurantId,
            @RequestBody OrderRequest request) {
        OrderResponse savedOrder = orderService.createOrder(restaurantId, request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getOrderId())
                .toUri();

        return ResponseEntity.created(uri).body(savedOrder);
    }

    @Operation(summary = "주문 조회")
    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long orderId) {
        OrderResponse foundOrder = orderService.getOrder(orderId);

        return ResponseEntity.ok().body(foundOrder);
    }
}
