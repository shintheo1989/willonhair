package com.shintheo.willonhair.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintheo.willonhair.entity.WorkHoursDao;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.EmployeeDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.repository.WorkHoursRepository;
import com.shintheo.willonhair.service.EmployeeService;
import com.shintheo.willonhair.repository.DaysOffRepository;
import com.shintheo.willonhair.repository.EmployeeRepository;
import com.shintheo.willonhair.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private WorkHoursRepository workHoursRepo;
	
	@Autowired
	private DaysOffRepository daysOffRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<WorkHoursDao> getEmployeeWorkHours(EmployeeDao employee) {
		return workHoursRepo.findEmployeeWorkHours(employee);
	}

	@Override
	public EmployeeDao createEmployee(EmployeeDao employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public EmployeeDao updateEmployee(EmployeeDao employee, Long employeeId) {
		employee.setId(employeeId);
		return employeeRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepo.deleteById(employeeId);
	}
	
	@Override
	public List<DaysOffDao> getEmployeeDaysOff(EmployeeDao employee) {
		return daysOffRepo.findEmployeeDaysOffDao(employee);
	}
	
	public Optional<EmployeeDao> findEmployeeById(Long employeeId) {
		return employeeRepo.findById(employeeId);
	}

	@Override
	public Optional<UserDao> findUserById(Long userId) {
		return userRepo.findById(userId);
	}
	
	@Override
	public List<EmployeeDao> fetchAll() {
		return employeeRepo.findAll();
	}
	
	@Override
	public WorkHoursDao createWorkHours(WorkHoursDao workHours) {
		return workHoursRepo.save(workHours);
	}
	
	@Override
	public WorkHoursDao updateWorkHours(WorkHoursDao workHours, Long whId) {
		WorkHoursDao dbWH = workHoursRepo.findById(whId).get();
		dbWH.setName(workHours.getName());
		dbWH.setDay(workHours.getDay());
		dbWH.setStart(workHours.getStart());
		dbWH.setEnd(workHours.getEnd());
		return workHoursRepo.save(dbWH);
	}
	
	@Override
	public void deleteWorkHours(Long woID) {
		workHoursRepo.deleteById(woID);
	}
	
	@Override
	public DaysOffDao createDaysOff(DaysOffDao dayOff) {
		return daysOffRepo.save(dayOff);
	}
	
	@Override
	public DaysOffDao updateDaysOff(DaysOffDao dayOff, Long whId) {
		DaysOffDao dbDO = daysOffRepo.findById(whId).get();
		dbDO.setName(dayOff.getName());
		dbDO.setStartYear(dayOff.getStartYear());
		dbDO.setStartMonth(dayOff.getStartMonth());
		dbDO.setStartDay(dayOff.getStartDay());
		
		dbDO.setEndYear(dayOff.getEndYear());
		dbDO.setEndMonth(dayOff.getEndMonth());
		dbDO.setEndDay(dayOff.getEndDay());
		
		return daysOffRepo.save(dbDO);
	}
	
	@Override
	public void deleteDaysOff(Long doID) {
		daysOffRepo.deleteById(doID);
	}
}
