package com.Raumplanung.repositories;

import com.Raumplanung.model.Raum;
import org.springframework.data.repository.CrudRepository;

public interface RaumRepository extends CrudRepository<Raum,Integer> {
}
