package com.shintheo.willonhair.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.entity.WorkHoursDao;
import com.shintheo.willonhair.api.EmployeeApi;
import com.shintheo.willonhair.entity.Availability;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.EmployeeDao;
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
	
	@Override
	@GetMapping(path = "/api/employees")
	public List<EmployeeDao> all() {
		return employeeService.fetchAll();
	}
	
	@Override
	@PostMapping(path = "/api/employees")
	public EmployeeDao createEmployee( 
			@ModelAttribute EmployeeDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file) {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			employee.setProfileImage(newImageName);
		}
		return employeeService.createEmployee(employee);
	}
	
	@Override
	@PutMapping(path = "/api/employees/{id}")
	public EmployeeDao updateEmployee(
			@PathVariable("id") Long employeeId, 
			@ModelAttribute EmployeeDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			employee.setProfileImage(newImageName);
		}
		return employeeService.updateEmployee(employee, employeeId);
	}
	
	@Override
	@DeleteMapping(path = "/api/employees/{id}")
	public String deleteEmployee(@PathVariable("id") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		return "Employee deleted";
	}
	
	@Override
	@GetMapping(path = "/api/employees/{id}/availabilities")
	public Availability availibilities(@PathVariable("id") Long employeeId) throws NotFoundException{
		 Optional<EmployeeDao> opEmployee = employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 List<WorkHoursDao> workHours = employeeService.getEmployeeWorkHours(opEmployee.get());
			 List<DaysOffDao> daysOff = employeeService.getEmployeeDaysOff(opEmployee.get());
			 return Availability.builder().workHours(workHours).daysOff(daysOff).build();
		 }
	}
	
	@Override
	@PostMapping(path = "/api/employees/{id}/work-hours")
	public WorkHoursDao createWorkHours(@PathVariable("id") Long employeeId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException {
		// Remove ID
		workHoursData.setId(null);
		Optional<EmployeeDao> opEmployee  = employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 // Set employee
			 workHoursData.setEmployee(opEmployee.get());
			 // Create new WorkHoursDao 
			 return employeeService.createWorkHours(workHoursData);
		 }	
	}
	
	@Override
	@PutMapping(path = "/api/employees/{id}/work-hours/{whId}")
	public WorkHoursDao updateWorkHours(@PathVariable("whId") Long whId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException {	
		 return employeeService.updateWorkHours(workHoursData, whId);
	}
	
	@Override
	@DeleteMapping(path = "/api/employees/{id}/work-hours/{whId}") 
	public String deleteWorkHours(@PathVariable("whId") Long whID){
		employeeService.deleteWorkHours(whID);
		return "Entry deleted!";
	}
	
	@Override
	@PostMapping(path = "/api/employees/{id}/days-off")
	public DaysOffDao createDayOff(@PathVariable("id") Long employeeId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException {
		// Remove ID
		daysOffData.setId(null);
		
		Optional<EmployeeDao> opEmployee= employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 // Set user
			 daysOffData.setEmployee(opEmployee.get());
			 // Create new WorkHoursDao 
			 return employeeService.createDaysOff(daysOffData);
		 }	
	}
	
	@Override
	@PutMapping(path = "/api/employees/{id}/days-off/{doId}")
	public DaysOffDao updateDaysOff(@PathVariable("doId") Long doId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException {	
		 return employeeService.updateDaysOff(daysOffData, doId);
	}
	
	@Override
	@DeleteMapping(path = "/api/employees/{id}/days-off/{doId}") 
	public String deleteDaysOff(@PathVariable("doId") Long doID){
		employeeService.deleteDaysOff(doID);
		return "Entry deleted!";
	}
}
