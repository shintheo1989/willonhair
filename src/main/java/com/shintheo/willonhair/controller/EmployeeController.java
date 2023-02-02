package com.shintheo.willonhair.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shintheo.willonhair.entity.WeekDaysDao;
import com.shintheo.willonhair.model.RegisterRequest;
import com.shintheo.willonhair.api.EmployeeApi;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.base.Type;
import com.shintheo.willonhair.entity.Availability;
import com.shintheo.willonhair.entity.DaysOffDao;
import com.shintheo.willonhair.entity.LocationDao;
import com.shintheo.willonhair.entity.PeriodDao;
import com.shintheo.willonhair.entity.ServiceDao;
import com.shintheo.willonhair.entity.UserDao;
import com.shintheo.willonhair.service.AuthenticationService;
import com.shintheo.willonhair.service.EmployeeService;
import com.shintheo.willonhair.service.LocationService;
import com.shintheo.willonhair.service.PeriodService;
import com.shintheo.willonhair.service.ServiceService;
import com.shintheo.willonhair.service.storage.StorageService;

import io.swagger.v3.oas.annotations.Operation;
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
    LocationService locationService;
	
	@Autowired
    ServiceService serviceService;	
	
	@Autowired
	PeriodService periodService;
	
	@Autowired
	AuthenticationService authService;
	
	@Override
	public ResponseEntity<List<UserDao>> all() {
		return ResponseEntity.ok(employeeService.fetchAll());
	}
	
	@Override
	public ResponseEntity<UserDao> createEmployee( 
			@Valid @RequestBody RegisterRequest registerRequest,
			@RequestParam(value = "image", required = false) MultipartFile file) {
		UserDao employee = authService.register(registerRequest, Type.HAIRDRESSER, Status.VISIBLE).getUser();
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			employee.setPictureFullPath(newImageName);
			employeeService.updateEmployee(employee, employee.getId());
		}
		return ResponseEntity.ok(employee);
	}
	
	@Override
	public ResponseEntity<UserDao> updateEmployee(
			@PathVariable("id") Long employeeId, 
			@ModelAttribute UserDao employee, 
			@RequestParam(value = "image", required = false) MultipartFile file) throws NotFoundException {
		if(file != null) {
			String newImageName = storageService.storeImage(file);
			employee.setPictureFullPath(newImageName);
		}
		return ResponseEntity.ok(employeeService.updateEmployee(employee, employeeId));
	}
	
	@Override
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee deleted");
	}
	
	@Override
	public ResponseEntity<Availability> availibilities(@PathVariable("id") Long employeeId) throws NotFoundException{
		 Optional<UserDao> opEmployee = employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 List<WeekDaysDao> workHours = employeeService.getEmployeeWeekDays(opEmployee.get());
			 List<DaysOffDao> daysOff = employeeService.getEmployeeDaysOff(opEmployee.get());
			 return ResponseEntity.ok(Availability.builder().weekDays(workHours).daysOff(daysOff).build());
		 }
	}
	
	@Override
	public ResponseEntity<WeekDaysDao> createWorkHours(@PathVariable("id") Long employeeId, @ModelAttribute WeekDaysDao workHoursData) throws NotFoundException {
		// Remove ID
		workHoursData.setId(null);
		Optional<UserDao> opEmployee  = employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 // Set employee
			 workHoursData.setUser(opEmployee.get());
			 // Create new WeekDaysDao 
			 return ResponseEntity.ok(employeeService.createWeekDays(workHoursData));
		 }	
	}
	
	@Override
	public ResponseEntity<WeekDaysDao> updateWorkHours(@PathVariable("whId") Long whId, @ModelAttribute WeekDaysDao workHoursData) throws NotFoundException {	
		 return ResponseEntity.ok(employeeService.updateWeekDays(workHoursData, whId));
	}
	
	@Override
	public ResponseEntity<String> deleteWorkHours(@PathVariable("whId") Long whID){
		employeeService.deleteWeekDays(whID);
		return ResponseEntity.ok("Entry deleted!");
	}
	
	@Override
	public ResponseEntity<DaysOffDao> createDayOff(@PathVariable("id") Long employeeId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException {
		// Remove ID
		daysOffData.setId(null);
		
		Optional<UserDao> opEmployee= employeeService.findEmployeeById(employeeId);
		 if(!opEmployee.isPresent()) {
			 throw new NotFoundException();
		 } else {
			 daysOffData.setUser(opEmployee.get());
			 return ResponseEntity.ok(employeeService.createDaysOff(daysOffData));
		 }	
	}
	
	@Override
	public ResponseEntity<DaysOffDao> updateDaysOff(@PathVariable("doId") Long doId, @ModelAttribute DaysOffDao daysOffData) throws NotFoundException {	
		 return ResponseEntity.ok(employeeService.updateDaysOff(daysOffData, doId));
	}
	
	@Override
	public ResponseEntity<String> deleteDaysOff(@PathVariable("doId") Long doID){
		employeeService.deleteDaysOff(doID);
		return ResponseEntity.ok("Entry deleted!");
	}
 
	public ResponseEntity<List<PeriodDao>> getWeekDaysPeriods(@PathVariable("id") Long employeeId, @PathVariable("wdId") Long wdId) {
		WeekDaysDao wd = employeeService.getWeekDaysById(wdId);
		return ResponseEntity.ok(periodService.getWeekDaysPeriods(wd));
	}
	
	public ResponseEntity<PeriodDao> createWeekDaysPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("weekDaysId") Long weekDaysId,
			@RequestParam(required = false) Long locationId,
			@ModelAttribute PeriodDao period) {
		WeekDaysDao weekDays = employeeService.getWeekDaysById(weekDaysId);
		if(locationId != null) {
			LocationDao location = locationService.findById(locationId).orElseThrow();
			period.setLocation(location);
		}
		period.setWeekDays(weekDays);
		return ResponseEntity.ok(periodService.submitPeriod(period));
	}
	
	public ResponseEntity<PeriodDao> updateWeekDaysPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("weekDaysId") Long weekDaysId, 
			@PathVariable("periodId") Long periodId,
			@ModelAttribute PeriodDao period) {
		// Keep the same week days and the same location
		PeriodDao dbPeriod = periodService.findById(periodId).orElseThrow();
		period.setId(periodId);
		period.setWeekDays(dbPeriod.getWeekDays());
		period.setLocation(dbPeriod.getLocation());
		return ResponseEntity.ok(periodService.savePeriod(period));
	}
	
	public ResponseEntity<String> deleteWeekDaysPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("weekDaysId") Long weekDaysId, 
			@PathVariable("pId") Long periodId) {
		periodService.deletePeriodById(periodId);
		return ResponseEntity.ok("Period deleted");
	}
	
	@GetMapping(path = "/{id}/periods/{periodId}/locations")
	@Operation(summary = "List period's location", description = "List where an employee is located during a given period") 
	public ResponseEntity<List<LocationDao>> getPeriodLocations(@PathVariable("id") Long employeeId, @PathVariable("periodId") Long periodId) {
		PeriodDao period = periodService.findById(periodId).orElseThrow();
		return ResponseEntity.ok(period.getLocations());
	}
	
	@PostMapping(path = "/{id}/periods/{periodId}/locations")
	@Operation(summary = "Assign locations", description = "Assign a list of locations to a period of an employee (You should given the list containing the IDs of locations)") 
	public ResponseEntity<List<LocationDao>> assignLocationsToPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("periodId") Long periodId,
			@RequestParam Long locationIds[]) {
		PeriodDao period = periodService.findById(periodId).orElseThrow();
		ArrayList<LocationDao> locations = new ArrayList<LocationDao>();
		for(Long locationId: locationIds) {
			Optional<LocationDao> opLocation = locationService.findById(locationId);
			if(opLocation.isPresent()) {
				locations.add(opLocation.get());			
			}
		}
		period.setLocations(locations);
		periodService.savePeriod(period);
		return ResponseEntity.ok(period.getLocations());
	}
	
	@GetMapping(path = "/{id}/periods/{periodId}/services")
	@Operation(summary = "List period's service", description = "List services of an employee during a given period") 
	public ResponseEntity<List<ServiceDao>> getPeriodServices(@PathVariable("id") Long employeeId, @PathVariable("periodId") Long periodId) {
		PeriodDao period = periodService.findById(periodId).orElseThrow();
		return ResponseEntity.ok(period.getServices());
	}
	
	@PostMapping(path = "/{id}/periods/{periodId}/services")
	@Operation(summary = "Assign services", description = "Assign a list of serices to a period of an employee (You should given the list containing the IDs of services)") 
	public ResponseEntity<List<ServiceDao>> assignServicesToPeriods(
			@PathVariable("id") Long employeeId, 
			@PathVariable("periodId") Long periodId,
			@RequestParam Long serviceIds[]) {
		PeriodDao period = periodService.findById(periodId).orElseThrow();
		ArrayList<ServiceDao> services = new ArrayList<ServiceDao>();
		for(Long locationId: serviceIds) {
			Optional<ServiceDao> opService = serviceService.findById(locationId);
			if(opService.isPresent()) {
				services.add(opService.get());			
			}
		}
		period.setServices(services);
		periodService.savePeriod(period);
		return ResponseEntity.ok(period.getServices());
	}
	
}
