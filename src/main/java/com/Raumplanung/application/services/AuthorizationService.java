package com.Raumplanung.application.services;

import com.Raumplanung.model.Rolle;

import java.util.Set;

public interface AuthorizationService {
	boolean authorise(int personalnummer, Set<Rolle> rolesAllowed);
}
