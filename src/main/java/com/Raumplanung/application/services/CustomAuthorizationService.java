package com.Raumplanung.application.services;

import com.Raumplanung.model.Nutzer;
import com.Raumplanung.model.NutzerRepository;
import com.Raumplanung.model.Rolle;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

public class CustomAuthorizationService implements AuthorizationService {
	@Inject
	private NutzerRepository nutzerRepository;

	@Override
	public boolean authorise(int personalnummer, Set<Rolle> rolesAllowed) {
		Optional<Nutzer> nutzer = nutzerRepository.findByPersonalnummer(personalnummer);
		if (nutzer.isEmpty()) return false;
		return nutzer.get().getRollen().stream().anyMatch(rolesAllowed::contains);
	}
}
