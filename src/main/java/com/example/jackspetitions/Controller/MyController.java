package com.example.jackspetitions.Controller;

import com.example.jackspetitions.Model.Petition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;


@Controller
public class MyController {

    private HashMap<Integer,Petition> petitionMap = new HashMap<>();

    @GetMapping("/")
    public String index(Model model) {
        // You can add any necessary model attributes here if needed
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        // You can add any necessary model attributes here if needed
        model.addAttribute("petition", new Petition());
        return "create";
    }

    @PostMapping("/create")
    public String createPetition(Petition petition) {
        petitionMap.put(petitionMap.size() + 1, petition);
        return "create";
    }

}

