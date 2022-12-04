package com.Raumplanung.rest.controller;

import com.Raumplanung.model.Nutzer;
import com.Raumplanung.model.Rolle;
import com.Raumplanung.repositories.NutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class NutzerController {
    private NutzerRepository nutzerRepository;
    
    @Autowired
    public NutzerController(NutzerRepository nutzerRepository){
        this.nutzerRepository=nutzerRepository;
    }
    
    @GetMapping("/neuerNutzer")//TBC
    public String createNutzer(Model model){
        model.addAttribute("nutzer",new Nutzer());
        return "createNutzer";
    }
    
    @PostMapping("/neuerNutzer")
    public String saveNutzer(Nutzer nutzer){
        nutzer.setRolle(Rolle.NUTZER);
        nutzerRepository.save(nutzer);
        return "home";
    }
    
    @GetMapping("/nutzerUebersicht")
    public String showNutzer(Model model){
        model.addAttribute("nutzers",nutzerRepository.findAll());
        return "showNutzer";
    }

    @GetMapping("/entferneNutzer")
    public String deleteNutzer(Model model){
        model.addAttribute("nutzer",new Nutzer());
        return "deleteNutzer";
    }

    @PostMapping("/entferneNutzer")
    public String deletedNutzer(Nutzer nutzer){
        nutzerRepository.deleteById(nutzer.getPersonalnummer());
        return "home";
    }
}
