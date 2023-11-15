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

    //Create Petition page
    @GetMapping("/create")
    public String create(Model model) {
        //Add a new Petition to the model
        model.addAttribute("petition", new Petition());
        return "create";
    }

    // Add the petition to the petitionMap
    @PostMapping("/create")
    public String createPetition(Petition petition) {
        // Add the petition to the petitionMap with a unique id based on the current size of the map
        petitionMap.put(petitionMap.size() + 1, petition);
        return "create";
    }

    // All Petitions page
    @GetMapping("/allpetitions")
    public String petitions(Model model) {
        model.addAttribute("petitionMap", petitionMap);
        return "allpetitions";
    }

    // Add the searchMap and search to the model
    @GetMapping("/search")
    public String searchbar(Model model) {
        model.addAttribute("search", search);
        return "search";
    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("searchMap", searchMap);
        return "results";
    }

    //Clear the searchMap and add the matching petitions to the searchMap, redirect to /search
    @PostMapping("/search")
    public String searchPetitions(Search search) {
        //Clear the searchMap prior to adding any petitions
        searchMap.clear();
        // Search through the petitionList for any petitions that contain the search string
        for (Map.Entry<Integer, Petition> entry: petitionMap.entrySet()) {
            if (entry.getValue().getTitle().contains(search.getSearch()) ||
                entry.getValue().getDescription().contains(search.getSearch()) ||
                entry.getValue().getAuthor().contains(search.getSearch())) {
                // Add the matching petitions to the searchMap
                searchMap.put(entry.getKey(), entry.getValue());
            }
        }
        //Redirect to the results page
        return "redirect:/results";
    }


    @GetMapping("/sign/{id}")
    public String sign(Model model, @PathVariable("id") Integer id) {
        //Using the PathVariable annotation, get the petition with the given id and add it to the model along with the id
        model.addAttribute("petition", petitionMap.get(id));
        model.addAttribute("petitionId", id);
        return "sign";
    }

    @GetMapping("/signup/{id}")
    public String addSignature(Model model, @PathVariable("id") Integer id) {
        // Increment the signatureCount to the petition with the given id
        petitionMap.get(id).setSignatureCount(petitionMap.get(id).getSignatureCount() + 1);
        return "redirect:/";
    }

}

