package com.Raumplanung.rest;

import com.Raumplanung.services.NutzerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import javax.ws.rs.core.Response;

@Controller
public class NutzerController {
    private DataSource dataSource;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("name","Raumplanung");
        return "home";
    }

    @GetMapping("/platz")
    public Response findPlatz(){
        NutzerService nutzerService = new NutzerService(dataSource);
        return nutzerService.findPlatz();
    }
}
