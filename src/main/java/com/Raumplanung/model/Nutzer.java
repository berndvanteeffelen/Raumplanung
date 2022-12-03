package com.Raumplanung.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Nutzer{
	@Id
	@GeneratedValue()
	private int personalnummer;
	private Set<Rolle> roles;
	
}
