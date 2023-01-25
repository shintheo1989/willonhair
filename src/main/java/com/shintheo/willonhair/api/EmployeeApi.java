package com.shintheo.willonhair.api;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.entity.Availability;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.EmployeeDao;
import com.shintheo.willonhair.entity.WorkHoursDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "EmployeeDao")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface EmployeeApi {
	
	@GetMapping(path = "/api/employees")
	@Operation(summary = "Employees list", description = "Returns List of employees")
	public List<EmployeeDao> all();
	
	@PostMapping(path = "/api/employees")
	public EmployeeDao createEmployee( 
			@ModelAttribute EmployeeDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file);
	
	@PutMapping(path = "/api/employees/{id}")
	public EmployeeDao updateEmployee(
			@PathVariable("id") Long employeeId, 
			@ModelAttribute EmployeeDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException ;
	
	@DeleteMapping(path = "/api/employees/{id}")
	public String deleteEmployee(@PathVariable("id") Long employeeId) ;
	
	@GetMapping(path = "/api/employees/{id}/availabilities")
	public Availability availibilities(@PathVariable("id") Long employeeId) throws NotFoundException;
	
	@PostMapping(path = "/api/employees/{id}/work-hours")
	public WorkHoursDao createWorkHours(@PathVariable("id") Long employeeId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException;
	
	@PutMapping(path = "/api/employees/{id}/work-hours/{whId}")
	public WorkHoursDao updateWorkHours(@PathVariable("whId") Long whId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException;
	
	@DeleteMapping(path = "/api/employees/{id}/work-hours/{whId}") 
	public String deleteWorkHours(@PathVariable("whId") Long whID);
	
	@PostMapping(path = "/api/employees/{id}/days-off")
	public DaysOffDao createDayOff(@PathVariable("id") Long employeeId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException ;
	
	@PutMapping(path = "/api/employees/{id}/days-off/{doId}")
	public DaysOffDao updateDaysOff(@PathVariable("doId") Long doId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException;
	
	@DeleteMapping(path = "/api/employees/{id}/days-off/{doId}") 
	public String deleteDaysOff(@PathVariable("doId") Long doID);
}
