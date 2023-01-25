package com.shintheo.willonhair.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationStartupConfig implements ApplicationListener<ApplicationReadyEvent>{
	@Autowired
    private Environment env;

    public static String baseImageUrl;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        baseImageUrl = env.getProperty("spring.config.imageUrl");
    }
}
