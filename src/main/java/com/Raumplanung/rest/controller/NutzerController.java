package com.Raumplanung.rest.controller;

import com.Raumplanung.model.Nutzer;
import com.Raumplanung.model.Rolle;
import com.Raumplanung.repositories.NutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "nutzer/createNutzer";
    }
    
    @PostMapping("/neuerNutzer")
    public String saveNutzer(Nutzer nutzer){
        if(nutzer.getPersonalnummer()<1){//TO DO: Error mapping
            return "home";
        }
        nutzer.setRolle(Rolle.NUTZER);
        nutzerRepository.save(nutzer);
        return "home";
    }
    
    @GetMapping("/nutzerUebersicht")
    public String showNutzer(Model model){
        model.addAttribute("nutzers",nutzerRepository.findAll());
        return "nutzer/showNutzer";
    }

    @PostMapping("/entferneNutzer/{id}")
    public String deletedNutzer(@PathVariable("id")int personalnummer){
        nutzerRepository.deleteById(personalnummer);
        return "home";
    }

    @PostMapping("/neuerAdmin/{id}")
    public String neuerAdmin(@PathVariable("id")int personalnummer){
        if(nutzerRepository.findById(personalnummer).isPresent()){
            Nutzer nutzer=nutzerRepository.findById(personalnummer).get();
            nutzer.setRolle(Rolle.ADMIN);
            nutzerRepository.save(nutzer);
        }
        return "home";
    }

    @PostMapping("/entferneAdmin/{id}")
    public String entferneAdmin(@PathVariable("id")int personalnummer){
        if(nutzerRepository.findById(personalnummer).isPresent()){
            Nutzer nutzer=nutzerRepository.findById(personalnummer).get();
            nutzer.setRolle(Rolle.NUTZER);
            nutzerRepository.save(nutzer);
        }
        return "home";
    }
}
