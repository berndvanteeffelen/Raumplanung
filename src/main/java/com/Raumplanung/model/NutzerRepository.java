package com.Raumplanung.model;

import java.util.Optional;

public interface NutzerRepository{
	Optional<Nutzer> findByPersonalnummer(int personalnummer);

	long countByPersonalnummerAndPasswort(int personalnummer,String passwort);
}
