package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shintheo.willonhair.entity.WorkHoursDao;
import com.shintheo.willonhair.entity.EmployeeDao;

public interface WorkHoursRepository extends JpaRepository<WorkHoursDao, Long> {
	@Query("SELECT u FROM WorkHoursDao u WHERE u.employee = :employee")
	List<WorkHoursDao> findEmployeeWorkHours(@Param("employee") EmployeeDao employee);
}
