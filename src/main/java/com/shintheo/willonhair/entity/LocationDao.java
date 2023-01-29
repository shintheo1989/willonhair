package com.shintheo.willonhair.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "t_location")
public class LocationDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_status", nullable = false, length = 20)
	@Builder.Default
	private Status status = Status.VISIBLE;
	
	@Column(name = "c_name", nullable = false)
	private String name;

	@Column(name = "c_description", nullable = true, length = 767)
	private String description;
	
	@Column(name = "c_address", nullable = false)
	private String address;
	
	@Column(name = "c_phone", nullable = false, length = 63)
	private String phone;
	
	@Column(name = "c_latitude", nullable = false)
	private double latitude;
	
	@Column(name = "c_longitude", nullable = false)
	private double longitude;
	
	@Column(name = "c_picture_full_path", nullable = true, length = 767)
	private String pictureFullPath;

	@Column(name = "c_picture_thumb_path", nullable = true, length = 767)
	private String pictureThumbPath;
	
	@Column(name = "c_pin", nullable = true, length = 767)
	private String pin;
	
	@Column(name = "c_translations", nullable = true, length = 767)
	private String translations;

}
