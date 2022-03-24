package com.example.pokemonwebdatabase2.controller;

import com.example.pokemonwebdatabase2.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PokemonController {

    PokemonRepository pokemonRepository;        // plus constructor under lader spring controller kende sit repository, lidt weird

    public PokemonController (PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/start")
    public String start(Model model){
        model.addAttribute("pokemon", pokemonRepository.getAll()); // tabel navn + liste med objekter
        return "start";
    }

}
