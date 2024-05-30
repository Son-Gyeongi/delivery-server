package com.unknown.deliveryserver.domain.restaurant.menu.dao;

import com.unknown.deliveryserver.domain.restaurant.menu.entity.MenuOptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuOptionDetailRepository extends JpaRepository<MenuOptionDetail, Long> {
    Optional<MenuOptionDetail> findByOptionDetailName(String optionDetailName);
}
