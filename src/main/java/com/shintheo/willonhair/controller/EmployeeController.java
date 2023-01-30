package com.shintheo.willonhair.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.entity.WorkHoursDao;
import com.shintheo.willonhair.model.RegisterRequest;
import com.shintheo.willonhair.api.EmployeeApi;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.base.Type;
import com.shintheo.willonhair.entity.Availability;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.AuthenticationService;
import com.shintheo.willonhair.service.EmployeeService;
import com.shintheo.willonhair.service.storage.StorageService;

import lombok.NoArgsConstructor;

@RestController
@CrossOrigin
@NoArgsConstructor
public class EmployeeController implements EmployeeApi{
	@Autowired
	private StorageService storageService;
	
	@Autowired
    EmployeeService employeeService;	
	
	@Autowired
	AuthenticationService authService;
	
	@Override
	public List<UserDao> all() {
		return employeeService.fetchAll();
	}
	
	@Override
	public UserDao createEmployee( 
			@Valid @RequestBody RegisterRequest registerRequest,
			@RequestParam(value = "image", required = false) MultipartFile file) {
		UserDao employee = authService.register(registerRequest, Type.HAIRDRESSER, Status.VISIBLE).getUser();
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			employee.setPictureFullPath(newImageName);
			employeeService.updateEmployee(employee, employee.getId());
		}
		return employee;
	}
	
	@Override
	public UserDao updateEmployee(
			@PathVariable("id") Long employeeId, 
			@ModelAttribute UserDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			employee.setPictureFullPath(newImageName);
		}
		return employeeService.updateEmployee(employee, employeeId);
	}
	
	@Override
	public String deleteEmployee(@PathVariable("id") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		return "Employee deleted";
	}
	
	@Override
	public Availability availibilities(@PathVariable("id") Long employeeId) throws NotFoundException{
		 Optional<UserDao> opEmployee = employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 List<WorkHoursDao> workHours = employeeService.getEmployeeWorkHours(opEmployee.get());
			 List<DaysOffDao> daysOff = employeeService.getEmployeeDaysOff(opEmployee.get());
			 return Availability.builder().workHours(workHours).daysOff(daysOff).build();
		 }
	}
	
	@Override
	public WorkHoursDao createWorkHours(@PathVariable("id") Long employeeId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException {
		// Remove ID
		workHoursData.setId(null);
		Optional<UserDao> opEmployee  = employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 // Set employee
			 workHoursData.setUser(opEmployee.get());
			 // Create new WorkHoursDao 
			 return employeeService.createWorkHours(workHoursData);
		 }	
	}
	
	@Override
	public WorkHoursDao updateWorkHours(@PathVariable("whId") Long whId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException {	
		 return employeeService.updateWorkHours(workHoursData, whId);
	}
	
	@Override
	public String deleteWorkHours(@PathVariable("whId") Long whID){
		employeeService.deleteWorkHours(whID);
		return "Entry deleted!";
	}
	
	@Override
	public DaysOffDao createDayOff(@PathVariable("id") Long employeeId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException {
		// Remove ID
		daysOffData.setId(null);
		
		Optional<UserDao> opEmployee= employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 // Set user
			 daysOffData.setUser(opEmployee.get());
			 // Create new WorkHoursDao 
			 return employeeService.createDaysOff(daysOffData);
		 }	
	}
	
	@Override
	public DaysOffDao updateDaysOff(@PathVariable("doId") Long doId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException {	
		 return employeeService.updateDaysOff(daysOffData, doId);
	}
	
	@Override
	public String deleteDaysOff(@PathVariable("doId") Long doID){
		employeeService.deleteDaysOff(doID);
		return "Entry deleted!";
	}
}
