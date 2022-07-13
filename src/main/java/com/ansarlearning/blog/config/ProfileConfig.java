package com.ansarlearning.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {
	
	@Profile(value = "dev")
	@Bean
	public String createBeanForDefault() {
		
		return "default";
	}

}
