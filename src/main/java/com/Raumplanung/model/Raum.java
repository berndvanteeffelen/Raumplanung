package com.Raumplanung.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Raum {
	@Id
	private int nummer;
}
