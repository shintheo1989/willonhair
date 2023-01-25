package com.shintheo.willonhair.config.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "storage")
@NoArgsConstructor
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "uploads";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

