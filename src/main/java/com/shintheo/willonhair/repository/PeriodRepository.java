package com.shintheo.willonhair.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shintheo.willonhair.entity.PeriodDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.WeekDaysDao;

public interface PeriodRepository  extends JpaRepository<PeriodDao, Long> {
	
	@Query("SELECT u FROM PeriodDao u WHERE u.weekDays.user = :employee")
	List<PeriodDao> findByEmployee(UserDao employee);
	
	List<PeriodDao> findByWeekDays(WeekDaysDao weekDays);
}
