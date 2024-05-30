package com.unknown.deliveryserver.domain.restaurant.menu.dao;

import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
