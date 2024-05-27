package com.unknown.deliveryserver.domain.restaurant.dao.menu;

import com.unknown.deliveryserver.domain.restaurant.entity.menu.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
