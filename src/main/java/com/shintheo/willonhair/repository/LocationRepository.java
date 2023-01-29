package com.shintheo.willonhair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.LocationDao;

public interface LocationRepository  extends JpaRepository<LocationDao, Long> {
}
