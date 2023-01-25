package com.shintheo.willonhair.repository.fidelity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.fidelity.CardDao;

public interface CardRepository extends JpaRepository<CardDao, Long> {
	Optional<CardDao> findByUser(UserDao user);
}

