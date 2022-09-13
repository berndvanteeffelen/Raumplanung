package com.Raumplanung.application.services;

public interface AuthenticationService {
	boolean authenticate(int personalnummer, String passwort);

	String getAuthenticationScheme();
}
