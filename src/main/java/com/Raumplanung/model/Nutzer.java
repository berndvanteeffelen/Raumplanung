package com.Raumplanung.model;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

public class Nutzer implements Principal {

	private final int personalnummer;
	private final Set<Rolle> roles;

	public Nutzer(int personalnummer){
		this(personalnummer, Collections.emptySet());
	}

	public Nutzer(int personalnummer, Set<Rolle> roles){
		this.personalnummer=personalnummer;
		this.roles=roles;
	}

	public String getName() {
		return String.valueOf(personalnummer);
	}

	public Set<Rolle> getRollen() {
		return Collections.unmodifiableSet(roles);
	}
}
