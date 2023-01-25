package com.shintheo.willonhair.entity.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "config_settings", uniqueConstraints = {@UniqueConstraint(columnNames = {"section_name", "setting_name"})})
@Builder
public class SettingsDao {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "section_name")
	private String sectionName;
	
	@Column(name = "setting_name")
	private String settingName;
	
	@Column(name = "setting_value")
	private String settingValue;
	
	@Column(name = "setting_type")
	private int settingType;
	
	// List of sections names
	public final static String SECTION_NAME_FIDELITY_CARD = "fidelity-card";
	
	// List of settings names
	public final static String SETTING_NAME_POINT_TO_BONUS = "points-to-bonus";
	
	// List of type used for settings types;
	public final static int STRING_TYPE_VALUE = 1;
	public final static int BOOLEAN_TYPE_VALUE = 2;
	public final static int INT_TYPE_VALUE = 3;
	public final static int DECIMAL_TYPE_VALUE = 4;
	
	public static SettingsDao buildPointsToBonusSettings() {
		return SettingsDao.builder()
				.sectionName(SECTION_NAME_FIDELITY_CARD)
				.settingName(SETTING_NAME_POINT_TO_BONUS)
				.settingType(INT_TYPE_VALUE)
				.build();
	}
}
