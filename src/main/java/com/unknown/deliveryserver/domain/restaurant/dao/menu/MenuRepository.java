package com.unknown.deliveryserver.domain.restaurant.dao.menu;

import com.unknown.deliveryserver.domain.restaurant.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
