package com.shintheo.willonhair.service;

import java.util.List;
import java.util.Optional;

import com.shintheo.willonhair.entity.WorkHoursDao;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.EmployeeDao;
import com.shintheo.willonhair.entity.UserDao;

public interface EmployeeService {
	// Employees 
	Optional<EmployeeDao> findEmployeeById(Long employeeId);

	List<EmployeeDao> fetchAll();

	EmployeeDao createEmployee(EmployeeDao employee);
	
	EmployeeDao updateEmployee(EmployeeDao employee, Long employeeId);
	
	void deleteEmployee(Long employeeId);
	
	// Work hours
	List<WorkHoursDao> getEmployeeWorkHours(EmployeeDao employee);
	
	WorkHoursDao createWorkHours(WorkHoursDao workHours);
	
	WorkHoursDao updateWorkHours(WorkHoursDao workHours, Long whId);
	
	void deleteWorkHours(Long whId);
	
	// Days off
	List<DaysOffDao> getEmployeeDaysOff(EmployeeDao employee);
	
	DaysOffDao createDaysOff(DaysOffDao dayOff);
	
	DaysOffDao updateDaysOff(DaysOffDao dayoff, Long doId);
	
	void deleteDaysOff(Long doID);
	
	// Users (Note: This Should not be here)
	
	Optional<UserDao> findUserById(Long userId); // TODO Remove this from here
}
