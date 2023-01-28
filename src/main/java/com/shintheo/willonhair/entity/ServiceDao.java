package com.shintheo.willonhair.entity;


import java.io.Serializable;

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

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shintheo.willonhair.base.DispositionPayment;
import com.shintheo.willonhair.base.Priority;
import com.shintheo.willonhair.base.RecurringCycle;
import com.shintheo.willonhair.base.RecurringSub;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.config.ApplicationStartupConfig;
import com.shintheo.willonhair.entity.ServiceDao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_service")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ServiceDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "c_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "c_name", nullable = false, length = 255)
	private String name;
	
	@Column(name = "c_description")
	private String description;
	
	@Default
	@Column(name = "c_color", nullable = false, length = 255)
	private String color = "#FFFFFF";
	
	@Column(name = "c_price", nullable = false) 
	private double price;

	@Enumerated(EnumType.STRING)
	@Default
	@Column(name = "c_status", nullable = false, length = 20)
	private Status status = Status.VISIBLE;
	
	@Column(name = "c_min_capacity", nullable = true)
	private int minCapacity;
	
	@Column(name = "c_max_capacity", nullable = true)
	private int maxCapacity;
	
	@Column(name = "c_duration", nullable = false)
	private int duration;
	
	@Column(name = "c_time_before")
	private int timeBefore;
	
	@Column(name = "c_time_after")
	private int timeAfter;
	
	@Column(name = "c_bringing_anyone")
	private int bringingAnyone;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_priority", nullable = false, length = 20)
	@Default
	private Priority priority = Priority.LEAST_EXPENSIVE;
	
	@Column(name = "c_picture_full_path", nullable = true, length = 767)
	@JsonIgnore
	private String pictureFullPath;

	@Column(name = "c_picture_thumb_path", length = 767)
	@JsonIgnore
	private String pictureThumbPath;
	
	@Column(name = "c_position", nullable = true) 
	private int position;
	
	@Column(name = "c_show") 
	private int show;
	
	@Column(name = "c_aggregated_price") 
	private int aggregatedPrice;
	
	@Column(name = "c_settings") 
	private String settings;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_recurring_cycle", nullable = true, length = 20) 
	private RecurringCycle recurringCycle;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_recurring_sub", nullable = true, length = 20) 
	private RecurringSub recurringSub;

	@Column(name = "c_recurring_payment", length = 3) 
	private int recurringPayment;

	@Column(name = "c_translation") 
	private String translation;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_disposition_payment", nullable = true, length = 20) 
	private DispositionPayment dispositionPayment;
	
	@Column(name = "c_deposit_per_person") 
	private int depositPerPerson;
	
	@Column(name = "c_deposit")
	private double deposit;
	
	@Column(name = "c_full_payment") 
	private int fullPayment;
	
	@Column(name = "c_mendatory_extra") 
	private int mendatoryExtra;
	
	@Column(name = "c_min_selected_extra") 
	private int minSelectedExtra;
	
	@Column(name = "c_custom_price") 
	private String customPrice;
	
	@Column(name = "c_max_extra_people") 
	private int maxExtraPeople;
	
	@Column(name = "c_limit_per_customer") 
	private String limitPerCustomer;
	
	@Column(name = "c_range_start") 
	private int rangeStart;
	
	@Column(name = "c_range_end") 
	private int rangeEnd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_category_id", nullable = false)
	@JsonBackReference
	private CategoryDao category;
	
	@Bean
	public String getImageUrl() {
		return ApplicationStartupConfig.baseImageUrl + "/" + this.pictureFullPath;
	}
}
