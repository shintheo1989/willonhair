package com.shintheo.willonhair.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "t_review")
public class ReviewDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "c_comment", nullable = true, length = 767)
	private String comment;
	
	@Column(name = "c_mark", nullable = false)
	private int mark;
	
	@Column(name = "c_created_at", nullable = false)
	@CreatedDate
	private Timestamp createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_user_id", nullable = false)
	@JsonManagedReference
	private UserDao user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_appointment_id", nullable = false)
	@JsonBackReference
	private AppointmentDao appointment;

}
