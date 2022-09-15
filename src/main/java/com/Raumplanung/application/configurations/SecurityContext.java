package com.Raumplanung.application.configurations;

import com.Raumplanung.model.Nutzer;
import com.Raumplanung.model.Rolle;

import java.security.Principal;

public class SecurityContext implements jakarta.ws.rs.core.SecurityContext {
	private Nutzer nutzer;

	@Override
	public Principal getUserPrincipal() {
		return nutzer;
	}

	@Override
	public boolean isUserInRole(String role) {
		return nutzer != null && nutzer.getRollen().contains(Rolle.valueOf(role));
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public String getAuthenticationScheme() {
		return BASIC_AUTH;
	}

	public void setUser(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
}
