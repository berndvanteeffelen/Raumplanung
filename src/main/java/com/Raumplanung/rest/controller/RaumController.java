package com.Raumplanung.rest.controller;

import com.Raumplanung.model.Raum;
import com.Raumplanung.repositories.RaumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RaumController {
	private RaumRepository raumRepository;

	@Autowired
	public RaumController(RaumRepository raumRepository){
		this.raumRepository=raumRepository;
	}

	@GetMapping("/neuerRaum")//TBC
	public String createRaum(Model model){
		model.addAttribute("raum",new Raum());
		return "raum/createRaum";
	}

	@PostMapping("/neuerRaum")
	public String saveRaum(Raum raum){
		if(raum.getNummer()<1){//TO DO: Error mapping + Conditions
			return "home";
		}
		raumRepository.save(raum);
		return "home";
	}

	@GetMapping("/raumUebersicht")
	public String showRaeume(Model model){
		model.addAttribute("raeume",raumRepository.findAll());
		return "raum/showRaum";
	}

	@PostMapping("/entferneRaum/{id}")
	public String deletedRaum(@PathVariable("id")int nummer){
		raumRepository.deleteById(nummer);
		return "home";
	}
}
