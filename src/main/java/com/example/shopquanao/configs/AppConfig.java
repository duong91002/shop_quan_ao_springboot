package com.example.shopquanao.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Value("${app.domain}")
    private String appDomain;
	
	@Value("${app.fe}")
    private String appFE;

	public String getAppDomain() {
		return appDomain;
	}
	
	public String getAppFE() {
		return appFE;
	}
	
}
