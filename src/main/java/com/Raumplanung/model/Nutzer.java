package com.Raumplanung.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Nutzer{
	@Id
	private int personalnummer;
	private Rolle rolle;
	
}
