package com.shintheo.willonhair.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "days_off")
@Builder
public class DaysOffDao {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonBackReference
	private EmployeeDao employee;
	
	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "period_start_year")
	private int startYear;
	
	@Column(name = "period_start_month")
	private int startMonth;
	
	@Column(name = "period_start_day")
	private int startDay;
	
	@Column(name = "period_end_year")
	private int endYear;
	
	@Column(name = "period_end_month")
	private int endMonth;
	
	@Column(name = "period_end_day")
	private int endDay;
}
