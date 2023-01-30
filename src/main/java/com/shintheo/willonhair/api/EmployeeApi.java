package com.shintheo.willonhair.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.entity.Availability;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.WorkHoursDao;
import com.shintheo.willonhair.model.RegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "UserDao")
@RequestMapping(path = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public interface EmployeeApi {
	
	@GetMapping(path = "")
	@Operation(summary = "Employees list", description = "Returns List of employees")
	public List<UserDao> all();
	
	@PostMapping(path = "")
	public UserDao createEmployee( 
			@Valid @RequestBody RegisterRequest authRequest,
			@RequestParam(value = "image", required = false) MultipartFile file);
	
	@PutMapping(path = "/{id}")
	public UserDao updateEmployee(
			@PathVariable("id") Long employeeId, 
			@ModelAttribute UserDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException ;
	
	@DeleteMapping(path = "/{id}")
	public String deleteEmployee(@PathVariable("id") Long employeeId) ;
	
	@GetMapping(path = "/{id}/availabilities")
	public Availability availibilities(@PathVariable("id") Long employeeId) throws NotFoundException;
	
	@PostMapping(path = "/{id}/work-hours")
	public WorkHoursDao createWorkHours(@PathVariable("id") Long employeeId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException;
	
	@PutMapping(path = "/{id}/work-hours/{whId}")
	public WorkHoursDao updateWorkHours(@PathVariable("whId") Long whId, @ModelAttribute WorkHoursDao workHoursData) throws NotFoundException;
	
	@DeleteMapping(path = "/{id}/work-hours/{whId}") 
	public String deleteWorkHours(@PathVariable("whId") Long whID);
	
	@PostMapping(path = "/{id}/days-off")
	public DaysOffDao createDayOff(@PathVariable("id") Long employeeId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException ;
	
	@PutMapping(path = "/{id}/days-off/{doId}")
	public DaysOffDao updateDaysOff(@PathVariable("doId") Long doId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException;
	
	@DeleteMapping(path = "/{id}/days-off/{doId}") 
	public String deleteDaysOff(@PathVariable("doId") Long doID);
}
