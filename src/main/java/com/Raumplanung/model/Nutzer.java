package com.Raumplanung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nutzer {

	@Id
	@Column(nullable = false,unique = true)
	private int personalnummer;

	@Column(nullable = false)
	private String passwort;
}
