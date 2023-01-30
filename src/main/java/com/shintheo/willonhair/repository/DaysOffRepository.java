package com.shintheo.willonhair.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.UserDao;

public interface DaysOffRepository extends JpaRepository<DaysOffDao, Long>{
	@Query("SELECT u FROM DaysOffDao u WHERE u.user = :employee")
	List<DaysOffDao> findEmployeeDaysOffDao(@Param("employee") UserDao employee);
}
