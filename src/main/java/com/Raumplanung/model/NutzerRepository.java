package com.Raumplanung.model;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutzerRepository{
	List<Nutzer> findByPersonalnummer(int personalnummer);
}
