package com.shintheo.willonhair.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.config.ApplicationStartupConfig;
import com.shintheo.willonhair.entity.ServiceDao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_service")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ServiceDao {
	@Id
	@Column(name = "c_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "c_name")
	private String name;
	
	@Column(name = "c_description")
	private String description;
	
	@Column(name = "c_color")
	private String color;
	
	@Column(name = "c_status", nullable = false, length = 20)
	private Status status;
	
	@Column(name = "c_duration")
	private String duration;
	
	@Column(name = "c_image")
	@JsonIgnore
	private String imageName;
	
	@Column(name = "c_price") 
	private int price;

	@Column(name = "c_range_start") 
	private int rangeStart;
	
	@Column(name = "c_range_end") 
	private int rangeEnd;

	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CategoryDao> categories;
	
	@Bean
	public String getTest() {
		return ApplicationStartupConfig.baseImageUrl + "/" + this.imageName;
	}
}
