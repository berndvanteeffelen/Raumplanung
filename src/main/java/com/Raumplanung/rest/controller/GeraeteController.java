package com.Raumplanung.rest.controller;

import com.Raumplanung.model.Geraet;
import com.Raumplanung.repositories.GeraetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GeraeteController {
	private GeraetRepository geraetRepository;

	@Autowired
	public GeraeteController(GeraetRepository geraeteRepository){
		this.geraetRepository=geraeteRepository;
	}

	@GetMapping("/neuesGeraet")//TBC
	public String createGeraete(Model model){
		model.addAttribute("geraet",new Geraet());
		return "geraet/createGeraet";
	}

	@PostMapping("/neuesGeraet")
	public String saveGeraete(Geraet geraet){
		if(geraet.getTyp()==null){//TO DO: Error mapping + Conditions
			return "home";
		}
		geraetRepository.save(geraet);
		return "home";
	}

	@GetMapping("/geraeteUebersicht")
	public String showGeraete(Model model){
		model.addAttribute("geraete",geraetRepository.findAll());
		return "geraet/showGeraet";
	}

	@PostMapping("/entferneGeraet/{id}")
	public String deletedNutzer(@PathVariable("id")String typ){
		geraetRepository.deleteById(typ);
		return "home";
	}
}
