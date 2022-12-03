package com.Raumplanung.rest.controller;

import com.Raumplanung.model.Nutzer;
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
    
    @GetMapping("/")//TBC
    public String createNutzer(Model model){
        model.addAttribute("nutzer",new Nutzer());
        return "createNutzer";
    }
    
    @PostMapping("/")
    public String saveNutzer(Nutzer nutzer){
        nutzerRepository.save(nutzer);
        return "/nutzeruebersicht";
    }
    
    @GetMapping("/nutzeruebersicht")
    public String showNutzer(Model model){
        model.addAttribute("products",nutzerRepository.findAll());
        return "showNutzer";
    }
}
