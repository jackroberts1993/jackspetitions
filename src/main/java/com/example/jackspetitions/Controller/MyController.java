package com.example.jackspetitions.Controller;

import com.example.jackspetitions.Model.Petition;
import com.example.jackspetitions.Model.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class MyController {

    private HashMap<Integer,Petition> petitionMap = new HashMap<>();

    private HashMap<Integer,Petition> searchMap = new HashMap<>();
    private Search search = new Search();

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

    @GetMapping("/allpetitions")
    public String results(Model model) {
        // You can add any necessary model attributes here if needed
        model.addAttribute("petitionMap", petitionMap);
        return "allpetitions";
    }

    @PostMapping("/search")
    public String searchPetitions(Search search) {
        // Search through the petitionList for any petitions that contain the search string
        // Add the matching petitions to the model
        searchMap.clear();
        for (Map.Entry<Integer, Petition> entry: petitionMap.entrySet()) {
            if (entry.getValue().getTitle().contains(search.getSearch()) || entry.getValue().getDescription().contains(search.getSearch()) || entry.getValue().getAuthor().contains(search.getSearch())) {
                searchMap.put(entry.getKey(), entry.getValue());
                System.out.println(entry.getValue().getTitle());
                System.out.println(search.getSearch());
            }
        }
        return "redirect:/search";
    }

}

