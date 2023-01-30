package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.base.Type;

public interface EmployeeRepository extends JpaRepository<UserDao, Long> {
	@Query(value = "SELECT u FROM UserDao u WHERE u.type =:type" )
	public List<UserDao> findAll(Type type);
}
