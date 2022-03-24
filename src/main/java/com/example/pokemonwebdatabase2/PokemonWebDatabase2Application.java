package com.example.pokemonwebdatabase2;

import com.example.pokemonwebdatabase2.repository.PokemonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokemonWebDatabase2Application {

    public static void main(String[] args) {

        PokemonRepository pokemonRepository = new PokemonRepository();

        SpringApplication.run(PokemonWebDatabase2Application.class, args);
    }


}
