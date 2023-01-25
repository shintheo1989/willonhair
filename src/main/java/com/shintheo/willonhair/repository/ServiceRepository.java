package com.shintheo.willonhair.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.ServiceDao;

public interface ServiceRepository extends JpaRepository<ServiceDao, Long> {
	Optional<ServiceDao> findByName(String name);
}
