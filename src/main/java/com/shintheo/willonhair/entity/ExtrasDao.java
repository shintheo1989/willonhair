package com.shintheo.willonhair.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Tag(name = "t_extras")
public class ExtrasDao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "c_name", nullable = false)
	private String name;
	@Column(name = "c_description", nullable = true)
	private String description;
	@Column(name = "c_price", nullable = false)
	private double price;
	@Column(name = "c_max_quantity", nullable = false)
	private int maxQuantity;
	@Column(name = "c_duration", nullable = true)
	private int duration;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_service_id", nullable = false)
	@JsonBackReference
	private ServiceDao serviceId;
	@Column(name = "c_position", nullable = false)
	private int position;
	@Column(name = "c_aggregated_price", nullable = true)
	private int aggregatedPrice;
	@Column(name = "c_translations", nullable = true)
	private String translations;
}
