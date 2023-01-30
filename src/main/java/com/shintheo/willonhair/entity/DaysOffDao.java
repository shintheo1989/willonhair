package com.shintheo.willonhair.entity;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_days_off")
@Builder
public class DaysOffDao {
	@Id
	@Column(name = "c_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_user_id", nullable = false)
	@JsonBackReference
	private UserDao user;
	
	@Column(name = "c_name", nullable = false, length = 255)
	private String name;
	
	@Column(name = "c_start_date", nullable = false)
	private Timestamp startDate;
	
	@Column(name = "c_end_date", nullable = false)
	private Timestamp endDate;
	
	@Column(name = "c_repeat", nullable = false, length = 1)
	private int repeat;
}
