package com.example.jackspetitions.Controller;

import com.example.jackspetitions.Model.Petition;
import com.example.jackspetitions.Model.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class MyController {


    //Data structures to hold the petitions and search results
    private HashMap<Integer,Petition> petitionMap = new HashMap<>();
    private HashMap<Integer,Petition> searchMap = new HashMap<>();
    private Search search = new Search();

    //Home page
    @GetMapping("/")
    public String index(Model model) {
        // You can add any necessary model attributes here if needed
        return "index";
    }

    //Add a new Petition to the model
    @GetMapping("/create")
    public String create(Model model) {
        // You can add any necessary model attributes here if needed
        model.addAttribute("petition", new Petition());
        return "create";
    }

    // Add the petition to the petitionMap
    @PostMapping("/create")
    public String createPetition(Petition petition) {
        petitionMap.put(petitionMap.size() + 1, petition);
        return "create";
    }

    // Add the petitionMap to the model
    @GetMapping("/allpetitions")
    public String petitions(Model model) {
        // You can add any necessary model attributes here if needed
        model.addAttribute("petitionMap", petitionMap);
        return "allpetitions";
    }

    // Add the searchMap and search to the model
    @GetMapping("/search")
    public String searchbar(Model model) {
        model.addAttribute("search", search);
        // You can add any necessary model attributes here if needed
        return "search";
    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("searchMap", searchMap);
        // You can add any necessary model attributes here if needed
        return "results";
    }

    //Clear the searchMap and add the matching petitions to the searchMap, redirect to /search
    @PostMapping("/search")
    public String searchPetitions(Search search) {
        // Search through the petitionList for any petitions that contain the search string

        searchMap.clear();
        // Add the matching petitions to the searchMap
        for (Map.Entry<Integer, Petition> entry: petitionMap.entrySet()) {
            if (entry.getValue().getTitle().contains(search.getSearch()) || entry.getValue().getDescription().contains(search.getSearch()) || entry.getValue().getAuthor().contains(search.getSearch())) {
                searchMap.put(entry.getKey(), entry.getValue());
            }
        }
        return "redirect:/results";
    }
    //Using the PathVariable annotation, get the petition with the given id and add it to the model along with the id itself
    @GetMapping("/sign/{id}")
    public String sign(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("petition", petitionMap.get(id));
        model.addAttribute("petitionId", id);
        // You can add any necessary model attributes here if needed
        return "sign";
    }

    // Add the signatureCount to the petition with the given id
    @GetMapping("/signup/{id}")
    public String addSignature(Model model, @PathVariable("id") Integer id) {
        //Increment the signature count
        petitionMap.get(id).setSignatureCount(petitionMap.get(id).getSignatureCount() + 1);
        return "redirect:/";
    }

}

