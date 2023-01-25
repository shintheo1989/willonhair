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
@Table(name = "fidelity_bonus_increment")
public class BonusIncrementDao {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name = "points")
	private int points;
	
	@ManyToOne
	@JoinColumn(name = "fidelity_card_id")
	@JsonBackReference
	private CardDao fidelityCard;
}
