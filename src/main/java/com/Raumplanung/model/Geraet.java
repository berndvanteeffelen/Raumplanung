package com.Raumplanung.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Geraet {
	@Id
	private String typ;
}
