package com.unknown.deliveryserver.domain.restaurant.dao;


import com.unknown.deliveryserver.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByIdAndDeletedAtIsNull(Long restaurantId);
}
