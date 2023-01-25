package com.shintheo.willonhair.entity.fidelity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shintheo.willonhair.entity.UserDao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "fidelity_card")
public class CardDao {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "bonus")
	private int bonus;
	
	@Column(name = "pending_points")
	private int pendingPoints;
	
	@Column(name = "total_points")
	private int totalPoints;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private UserDao user;
	
	@OneToMany(mappedBy = "fidelityCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BonusIncrementDao> bonuses;
	
	/**
	 * Add one point on pending points
	 * @return (int) value on pending points after increment
	 */
	public int incrementPoints() {
		this.pendingPoints++;
		return this.pendingPoints;
	}
	
	/**
	 * Add pending points on total and reset pending to 0
	 * @param incrementBonus (boolean) whether to increment bonus or not
	 * @return (int) value of total points after operation
	 */
	public int addPendingOnTotal(boolean incrementBonus) {
		this.totalPoints += this.pendingPoints;
		this.pendingPoints = 0;
		if(incrementBonus) {
			this.bonus++;
		}
		return this.totalPoints;
	}
}
