package com.unknown.deliveryserver.domain.restaurant.menu.dao;

import com.unknown.deliveryserver.domain.restaurant.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByFoodName(String foodName);
}
