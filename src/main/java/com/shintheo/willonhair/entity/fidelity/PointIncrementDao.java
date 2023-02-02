package com.shintheo.willonhair.entity.fidelity;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_fidelity_point_increment")
public class PointIncrementDao {
	@Id
	@Column(name = "c_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "c_value", nullable = false)
	@Builder.Default
	private int value = 1;
	
	@Column(name = "c_created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name = "c_last_pending_point")
	private int lastPendingPoint;
	
	@Column(name = "c_last_total_point")
	private int lastTotalPoint;
	
	@ManyToOne
	@JoinColumn(name = "c_fidelity_card_id")
	@JsonBackReference
	private CardDao fidelityCard;
}

