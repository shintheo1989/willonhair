package com.shintheo.willonhair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.EmployeeDao;

public interface EmployeeRepository extends JpaRepository<EmployeeDao, Long> {

}
