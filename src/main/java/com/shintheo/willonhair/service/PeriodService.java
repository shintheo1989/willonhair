package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.PeriodDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.WeekDaysDao;

public interface PeriodService {
	PeriodDao submitPeriod(PeriodDao period);

	PeriodDao savePeriod(PeriodDao period);
	
	List<PeriodDao> getEmployeePeriods(UserDao employee);
	
	List<PeriodDao> getWeekDaysPeriods(WeekDaysDao weekDays);
	
	Optional<PeriodDao> findById(Long PeriodId);
		
	void deletePeriodById(Long PeriodId);
}