package com.unknown.deliveryserver.domain.restaurant.dao.menu;

import com.unknown.deliveryserver.domain.restaurant.entity.menu.MenuOptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuOptionDetailRepository extends JpaRepository<MenuOptionDetail, Long> {
    Optional<MenuOptionDetail> findByOptionDetailName(String optionDetailName);
}
