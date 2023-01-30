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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shintheo.willonhair.base.EntityType;
import com.shintheo.willonhair.base.PaymentGateway;
import com.shintheo.willonhair.base.PaymentStatus;

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
@Table(name = "t_payment")
public class PaymentDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_status", nullable = false, length = 20)
	private PaymentStatus status;
	
	@Column(name = "c_amount", nullable = false)
	private double amount;

	@Column(name = "c_datetime")
	private Timestamp datetime;

	@Enumerated(EnumType.STRING)
	@Column(name = "c_gateway", length = 20)
	@Builder.Default
	private PaymentGateway gateway = PaymentGateway.ONSITE;

	@Column(name = "c_gateway_title") 
	private String gatewayTitle;
	
	@Column(name = "c_data", length = 767)
	private String data;
	

	@Column(name = "c_package_customer_id", length = 11)
	private int packageCustomerId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_entity", length = 20)
	private EntityType entity;

	@Column(name = "c_created")
	@CreatedDate
	private Timestamp created;

	@Column(name = "c_action_completed", length = 1)
	private int actionsCompleted;

	@Column(name = "c_wc_order_id", length = 11)
	private int wcOrderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_customer_booking_id")
	@JsonBackReference
	private CustomerBookingDao customerBooking;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_parent_id")
	@JsonManagedReference
	private PaymentDao parent;
}
