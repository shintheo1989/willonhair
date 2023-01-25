package com.shintheo.willonhair.repository.config;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintheo.willonhair.entity.config.SettingsDao;

public interface SettingsRepository extends JpaRepository<SettingsDao, Long>{	
	Optional<SettingsDao> findBySectionNameAndSettingName(String sectionName, String settingName);
}

