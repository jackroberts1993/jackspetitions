package com.example.jackspetitions.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MyController {

    @GetMapping("/")
    public String index(Model model) {
        // You can add any necessary model attributes here if needed
        return "index";
    }

}

