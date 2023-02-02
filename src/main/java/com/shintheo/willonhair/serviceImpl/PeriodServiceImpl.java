package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.PeriodDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.WeekDaysDao;
import com.shintheo.willonhair.repository.PeriodRepository;
import com.shintheo.willonhair.service.PeriodService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PeriodServiceImpl implements PeriodService {

	@Autowired
	private PeriodRepository repo;

	@Override
	public PeriodDao submitPeriod(PeriodDao period) {
		log.info("======>Period: submit period({})<======", period);
		return repo.save(period);
	}

	@Override
	public PeriodDao savePeriod(PeriodDao period) {
		log.info("======>Period: save period({})<======", period);
		return repo.save(period);
	}

	@Override
	public List<PeriodDao> getEmployeePeriods(UserDao employee) {;
		return repo.findByEmployee(employee);
	}
	
	@Override
	public List<PeriodDao> getWeekDaysPeriods(WeekDaysDao weekDays) {
		return repo.findByWeekDays(weekDays);
	}

	@Override
	public Optional<PeriodDao> findById(Long periodId) {
		log.info("======>Period: lookgin for period with id({})<======", periodId);
		return repo.findById(periodId);
	}

	@Override
	public void deletePeriodById(Long periodId) {
		log.info("======>Period: Delete period with id ({})<======", periodId);
		repo.deleteById(periodId);
	}

}
