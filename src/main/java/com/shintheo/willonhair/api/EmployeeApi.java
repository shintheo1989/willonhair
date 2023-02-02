package com.shintheo.willonhair.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.shintheo.willonhair.entity.LocationDao;
import com.shintheo.willonhair.entity.PeriodDao;
import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.entity.WeekDaysDao;
import com.shintheo.willonhair.model.RegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "UserDao")
@RequestMapping(path = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public interface EmployeeApi {
	
	@GetMapping(path = "")
	@Operation(summary = "Employees list", description = "Returns List of employees")
	public ResponseEntity<List<UserDao>> all();
	
	@PostMapping(path = "")
	@Operation(summary = "New Employee", description = "Create a new employee")
	public  ResponseEntity<UserDao> createEmployee( 
			@Valid @RequestBody RegisterRequest authRequest,
			@RequestParam(value = "image", required = false) MultipartFile file);
	
	@PutMapping(path = "/{id}")
	@Operation(summary = "Update employee", description = "Update employee's details")
	public  ResponseEntity<UserDao> updateEmployee(
			@PathVariable("id") Long employeeId, 
			@ModelAttribute UserDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException ;
	
	@DeleteMapping(path = "/{id}")
	@Operation(summary = "Delete employee", description = "Delete a given employee (using it's id)")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) ;
	
	@GetMapping(path = "/{id}/availabilities")
	@Operation(summary = "Employee availabilities", description = "List availabilities of a given employee")
	public ResponseEntity<Availability> availibilities(@PathVariable("id") Long employeeId) throws NotFoundException;
	
	@PostMapping(path = "/{id}/week-days")
	@Operation(summary = "Create Week Days", description = "Create Week Days for an employee (Each day of the week, with start time and end time)")
	public ResponseEntity<WeekDaysDao> createWorkHours(@PathVariable("id") Long employeeId, @ModelAttribute WeekDaysDao workHoursData) throws NotFoundException;
	
	@PutMapping(path = "/{id}/week-days/{wdId}")
	@Operation(summary = "Update Week Days", description = "Edit a given Week Days for employee")
	public ResponseEntity<WeekDaysDao> updateWorkHours(@PathVariable("wdId") Long whId, @ModelAttribute WeekDaysDao workHoursData) throws NotFoundException;
	
	@DeleteMapping(path = "/{id}/week-days/{whId}") 
	@Operation(summary = "Delete Week Days", description = "Delete a given Week Days for an employee")
	public ResponseEntity<String> deleteWorkHours(@PathVariable("whId") Long whID);
	
	@PostMapping(path = "/{id}/days-off")
	@Operation(summary = "Create Days off", description = "Create *days off* for an employee (When the employee is not working, maybe for christmas or ...)")
	public ResponseEntity<DaysOffDao> createDayOff(@PathVariable("id") Long employeeId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException ;
	
	@PutMapping(path = "/{id}/days-off/{doId}")
	@Operation(summary = "Update Days off", description = "Update *days off* for an employee")
	public ResponseEntity<DaysOffDao> updateDaysOff(@PathVariable("doId") Long doId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException;
	
	@DeleteMapping(path = "/{id}/days-off/{doId}")
	@Operation(summary = "Delete Days off", description = "Delete *days off* for an employee") 
	public ResponseEntity<String> deleteDaysOff(@PathVariable("doId") Long doID);
	
	@GetMapping(path = "/{id}/week-days/{wdId}/periods")
	@Operation(summary = "List periods", description = "List differents periods of a week days of an employee") 
	public ResponseEntity<List<PeriodDao>> getWeekDaysPeriods(@PathVariable("id") Long employeeId, @PathVariable("wdId") Long wdId);
	
	@PostMapping(path = "/{id}/week-days/{weekDaysId}/periods")
	@Operation(summary = "Create period", description = "Create new period of a week days of an employee") 
	public ResponseEntity<PeriodDao> createWeekDaysPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("weekDaysId") Long weekDaysId,
			@RequestParam(required = false) Long locationId,
			@ModelAttribute PeriodDao period);
	
	@PutMapping(path = "/{id}/week-days/{weekDaysId}/periods/{periodId}")
	@Operation(summary = "Update Period", description = "Edit a period of a week days of an employee") 
	public ResponseEntity<PeriodDao> updateWeekDaysPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("weekDaysId") Long weekDaysId, 
			@PathVariable("periodId") Long periodId,
			@ModelAttribute PeriodDao period);
	
	@DeleteMapping(path = "/{id}/week-days/{weekDaysId}/periods/{pId}")
	@Operation(summary = "Delete Period", description = "Delete a period of a week days of an employee") 
	public ResponseEntity<String> deleteWeekDaysPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("weekDaysId") Long weekDaysId, 
			@PathVariable("pId") Long periodId);
	
	@GetMapping(path = "/{id}/periods/{periodId}/locations")
	@Operation(summary = "List period's location", description = "List where an employee is located during a given period") 
	public ResponseEntity<List<LocationDao>> getPeriodLocations(@PathVariable("id") Long employeeId, @PathVariable("periodId") Long periodId);
	
	@PostMapping(path = "/{id}/periods/{periodId}/locations")
	@Operation(summary = "Assign locations", description = "Assign a list of locations to a period of an employee (You should given the list containing the IDs of locations)") 
	public ResponseEntity<List<LocationDao>> assignLocationsToPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("periodId") Long periodId,
			@RequestParam Long locationIds[]);
	
	@GetMapping(path = "/{id}/periods/{periodId}/services")
	@Operation(summary = "List period's service", description = "List services of an employee during a given period") 
	public ResponseEntity<List<ServiceDao>> getPeriodServices(@PathVariable("id") Long employeeId, @PathVariable("periodId") Long periodId);
	
	@PostMapping(path = "/{id}/periods/{periodId}/services")
	@Operation(summary = "Assign services", description = "Assign a list of serices to a period of an employee (You should given the list containing the IDs of services)") 
	public ResponseEntity<List<ServiceDao>> assignServicesToPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("periodId") Long periodId,
			@RequestParam Long servicesIds[]);
	
}
