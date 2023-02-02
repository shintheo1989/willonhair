package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shintheo.willonhair.entity.WeekDaysDao;
import com.shintheo.willonhair.entity.UserDao;

public interface WeekDaysRepository extends JpaRepository<WeekDaysDao, Long> {
	@Query("SELECT u FROM WeekDaysDao u WHERE u.user = :employee")
	List<WeekDaysDao> findEmployeeWeekDays(@Param("employee") UserDao employee);
}
