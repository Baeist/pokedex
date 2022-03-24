package com.example.pokemonwebdatabase2.repository;

import com.example.pokemonwebdatabase2.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {

    private String DBURL = "jdbc:mysql://localhost:3306/pokedex";
    private String user = "webshop_user";
    private String passWord = "webshop";

    private Connection connection;

    public PokemonRepository(){
        setConnection();
    }

    public void setConnection() {
        try {
            connection = DriverManager.getConnection(DBURL, user, passWord);

        } catch (Exception e) {
            System.out.println("Did not connect to database" + e);
        }

    }

    public List<Pokemon> getAll() {
         List<Pokemon> pokemonList = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM pokemon"; //" WHERE id = 1 OR 1=1; --";
            ResultSet rs = statement.executeQuery(SQL_QUERY);


            //read data from resultset
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int speed = rs.getInt(3);
                int hp = rs.getInt(4);
                int specialDefence = rs.getInt(5);
                int specialAttack = rs.getInt(6);
                int defence = rs.getInt(7);
                int attack = rs.getInt(8);
                String primaryType = rs.getString(9);
                String secondaryType = rs.getString(10);
                pokemonList.add(new Pokemon(id, name, speed, hp, specialDefence, specialAttack, defence, attack, primaryType, secondaryType));

            }

            statement.close();      // god afslutning, har ingen reel betydning her
        } catch (
                SQLException e) {
            System.out.println("Could not create connection");
            e.printStackTrace();
        }
        return pokemonList;
    }
}
