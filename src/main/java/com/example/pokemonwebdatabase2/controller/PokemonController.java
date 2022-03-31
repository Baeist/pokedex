package com.example.pokemonwebdatabase2.controller;

import com.example.pokemonwebdatabase2.model.Pokemon;
import com.example.pokemonwebdatabase2.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PokemonController {

    private int pokemonIndexNumber = -1;

    PokemonRepository pokemonRepository;        // plus constructor under lader spring controller kende sit repository, lidt weird
    public PokemonController (PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/start")
    public String start(Model model){
        model.addAttribute("pokemon", pokemonRepository.getAll()); // tabel navn + liste med objekter
        return "start";
    }
    @GetMapping("/delete/{id}")
    public String deletePokemon(@PathVariable("id") int sletteId){
        pokemonRepository.deleteById(sletteId);
        return "redirect:/start";
    }
    @GetMapping("/update/{id}")
    public String updatePokemon(@PathVariable("id") int updateID, Model model){
        model.addAttribute("pokemon", pokemonRepository.retrievePokemonbyID(updateID));

        setPokemonIndexNumber(updateID);
        return "/update";
    }

    @PostMapping("/update")
    public String update(@RequestParam("pokemon_name") String name, @RequestParam("pokemon_speed") int speed,
                        @RequestParam("pokemon_HP") int HP, @RequestParam("pokemon_sDef") int sDef, @RequestParam("pokemon_sAtt") int sAtt,
                        @RequestParam("pokemon_def") int defence, @RequestParam("pokemon_att") int attack,
                        @RequestParam("pokemon_primary") String primary,@RequestParam("pokemon_secondary") String secondary, Model model) {
        model.addAttribute("pokemon_name", name);
        model.addAttribute("pokemon_speed", speed);
        model.addAttribute("pokemon_HP", HP);
        model.addAttribute("pokemon_sDef", sDef);
        model.addAttribute("pokemon_sAtt", sAtt);
        model.addAttribute("pokemon_def", defence);
        model.addAttribute("pokemon_att", attack);
        model.addAttribute("pokemon_primary", primary);
        model.addAttribute("pokemon_secondary", secondary);

        Pokemon currentPokemon = new Pokemon(getPokemonIndexNumber(), name, speed, HP, sDef, sAtt, defence, attack, primary, secondary);
        pokemonRepository.updatePokemon(getPokemonIndexNumber(), currentPokemon);

        return "redirect:/start";
    }
    @PostMapping("/start")              // virker, men ikke hvis man ikke udfylder alle felter i browser, hvis service package måske set deafult values for poke der
    public String start(@RequestParam("pokemon_name") String name, @RequestParam("pokemon_speed") int speed,
                        @RequestParam("pokemon_HP") int HP, @RequestParam("pokemon_sDef") int sDef, @RequestParam("pokemon_sAtt") int sAtt,
                        @RequestParam("pokemon_def") int defence, @RequestParam("pokemon_att") int attack,
                        @RequestParam("pokemon_primary") String primary,@RequestParam("pokemon_secondary") String secondary, Model model){
        model.addAttribute("pokemon_name", name);
        model.addAttribute("pokemon_speed", speed);
        model.addAttribute("pokemon_HP", HP);
        model.addAttribute("pokemon_sDef", sDef);
        model.addAttribute("pokemon_sAtt", sAtt);
        model.addAttribute("pokemon_def", defence);
        model.addAttribute("pokemon_att", attack);
        model.addAttribute("pokemon_primary", primary);
        model.addAttribute("pokemon_secondary", secondary);


        Pokemon currentPokemon = new Pokemon(-1, name, speed, HP, sDef, sAtt, defence, attack, primary, secondary);

        pokemonRepository.addNewPokemon(currentPokemon);

        return "redirect:/start";
    }

    public void setPokemonIndexNumber(int pokemonIndexNumber) {
        this.pokemonIndexNumber = pokemonIndexNumber;
    }

    public int getPokemonIndexNumber() {
        return pokemonIndexNumber;
    }
}
