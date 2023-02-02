package com.shintheo.willonhair.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "t_period")
public class PeriodDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "c_start_time", nullable = false)
	private Time startTime;
	
	@Column(name = "c_end_time", nullable = false)
	private Time endTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_work_hours_id", nullable = false)
	@JsonBackReference
	private WeekDaysDao weekDays;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_location_id")
	@JsonManagedReference
	private LocationDao location;

	@ManyToMany
	@JoinTable(
			name = "t_periods_locations",
			joinColumns = @JoinColumn (name = "c_period_id"),
			inverseJoinColumns = @JoinColumn (name = "c_location_id")
		)
	@JsonManagedReference
	private List<LocationDao> locations;
	
	@ManyToMany
	@JoinTable(
		name = "t_periods_services",
		joinColumns = @JoinColumn (name = "c_period_id"),
		inverseJoinColumns = @JoinColumn (name = "c_service_id")
	)
	@JsonManagedReference
	private List<ServiceDao> services;
}
