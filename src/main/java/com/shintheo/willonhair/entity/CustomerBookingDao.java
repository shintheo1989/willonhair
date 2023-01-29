package com.shintheo.willonhair.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shintheo.willonhair.base.Status;

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
@Table(name = "t_customer_booking")
public class CustomerBookingDao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "c_status", nullable = true, length = 20)
	private Status status;
	
	@Column(name = "c_price", nullable = false, length = 11)
	private double price;
	
	@Column(name = "c_persons", nullable = false, length = 11)
	private double persons;
	
	@Column(name = "c_coupon_id", nullable = true, length = 11)
	private int couponId; // TODO("Use relation")
	
	@Column(name = "c_token", nullable = true, length = 10)
	private String token;
	
	@Column(name = "c_custom_fields", nullable = true, length = 676)
	private String customFields;
	
	@Column(name = "c_info", nullable = true, length = 676)
	private String info;
	
	@Column(name = "c_utc_offset", nullable = true, length = 3)
	private int utcOffset;
	
	@Column(name = "c_aggregated_price", nullable = true, length = 1)
	private int aggregatedPrice;
	
	@Column(name = "c_package_customer_services_id", nullable = true, length = 11)
	private int packageCustomerServiceId;
	
	@Column(name = "c_duration", nullable = true, length = 11)
	private int duration;
	
	@Column(name = "c_created", nullable = true)
	@CreatedDate
	private Timestamp created;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_appointment_id", nullable = false)
	@JsonBackReference
	private AppointmentDao appointment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_customer_id", nullable = false)
	@JsonBackReference
	private UserDao customer;
}
