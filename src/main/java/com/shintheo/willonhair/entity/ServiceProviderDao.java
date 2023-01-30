package com.shintheo.willonhair.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "t_service_provider")
public class ServiceProviderDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "c_price", nullable = false)
	private double price;
	
	@Column(name = "c_min_capacity", nullable = false, length = 11)
	private int minCapacity;
	
	@Column(name = "c_max_capacity", nullable = false, length = 11)
	private int maxCapacity;
	
	@Column(name = "c_custom_princing", nullable = true, length = 676)
	private String customPricing;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_user_id", nullable = false)
	@JsonManagedReference
	private UserDao user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_service_id", nullable = false)
	@JsonManagedReference
	private ServiceDao service;
}
