package com.shintheo.willonhair.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_appointment_transfer")
public class AppointmentTransferDao {
	@Id
	@Column(name = "c_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "c_created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	@ManyToOne
	@JoinColumn(name = "c_from_employee_id")
	@JsonManagedReference
	private UserDao fromEmployee;
	
	@ManyToOne
	@JoinColumn(name = "c_to_employee_id")
	@JsonManagedReference
	private UserDao toEmployee;
	
	@ManyToOne
	@JoinColumn(name = "c_appointment")
	@JsonManagedReference
	private AppointmentDao appointment;
}
