package com.urbancompany.commonservice.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonFileProperties {

	@Value("${uc.auth.enabled:true}")
	private boolean enableAuth;

	@Value("${user.token.secret}")
	private String jwtSecret;

	@Value("${user.token.validity}")
	private String tokenValidity;

	@Value("${uc.inter.service.calls.apiKey}")
	private String apiKey;

	@Value("#{'${uc.whitelisted.url}'.split(',')}")
	private List<String> whiteListedURLs;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public String getTokenValidity() {
		return tokenValidity;
	}

	public List<String> getWhiteListedURLs() {
		return whiteListedURLs;
	}

	public boolean isEnableAuth() {
		return enableAuth;
	}

	public String getApiKey() {
		return apiKey;
	}

}
