package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.WeekDaysDao;
import com.shintheo.willonhair.base.Type;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.repository.WeekDaysRepository;
import com.shintheo.willonhair.service.EmployeeService;
import com.shintheo.willonhair.repository.DaysOffRepository;
import com.shintheo.willonhair.repository.EmployeeRepository;
import com.shintheo.willonhair.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private WeekDaysRepository weekDaysRepo;
	
	@Autowired
	private DaysOffRepository daysOffRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<WeekDaysDao> getEmployeeWeekDays(UserDao employee) {
		return weekDaysRepo.findEmployeeWeekDays(employee);
	}

	@Override
	public UserDao createEmployee(UserDao employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public UserDao updateEmployee(UserDao employee, Long employeeId) {
		employee.setId(employeeId);
		return employeeRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepo.deleteById(employeeId);
	}
	
	@Override
	public List<DaysOffDao> getEmployeeDaysOff(UserDao employee) {
		return daysOffRepo.findEmployeeDaysOffDao(employee);
	}
	
	public Optional<UserDao> findEmployeeById(Long employeeId) {
		return employeeRepo.findById(employeeId);
	}

	@Override
	public Optional<UserDao> findUserById(Long userId) {
		return userRepo.findById(userId);
	}
	
	@Override
	public List<UserDao> fetchAll() {
		return employeeRepo.findAll(Type.HAIRDRESSER);
	}

	@Override
	public WeekDaysDao getWeekDaysById(Long weekDaysId) {
		return weekDaysRepo.findById(weekDaysId).orElseThrow();
	}
	
	@Override
	public WeekDaysDao createWeekDays(WeekDaysDao weekDays) {
		return weekDaysRepo.save(weekDays);
	}
	
	@Override
	public WeekDaysDao updateWeekDays(WeekDaysDao weekDays, Long whId) {
		WeekDaysDao dbWH = weekDaysRepo.findById(whId).get();
		dbWH.setName(weekDays.getName());
		dbWH.setDay(weekDays.getDay());
		dbWH.setStart(weekDays.getStart());
		dbWH.setEnd(weekDays.getEnd());
		return weekDaysRepo.save(dbWH);
	}
	
	@Override
	public void deleteWeekDays(Long woID) {
		weekDaysRepo.deleteById(woID);
	}
	
	@Override
	public DaysOffDao createDaysOff(DaysOffDao dayOff) {
		return daysOffRepo.save(dayOff);
	}
	
	@Override
	public DaysOffDao updateDaysOff(DaysOffDao dayOff, Long doId) {
		// Keep the same user and the same id
		DaysOffDao dbDayOff = daysOffRepo.findById(doId).orElseThrow();
		dayOff.setUser(dbDayOff.getUser());
		dayOff.setId(doId);
		return daysOffRepo.save(dayOff);
	}
	
	@Override
	public void deleteDaysOff(Long doID) {
		daysOffRepo.deleteById(doID);
	}
}
