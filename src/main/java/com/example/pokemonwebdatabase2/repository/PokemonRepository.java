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

    public PokemonRepository() {
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

    public void deleteById(int id) {

        try {
            //create prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM pokemon WHERE pokedex_number = ?"
            );
            //set parameter
            preparedStatement.setInt(1, id);
            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Could not delete");
            sqlException.printStackTrace();
        }
    }

    public void addNewPokemon(Pokemon pokemon) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO pokemon(pokedex_number, name, speed, hp, special_defence, special_attack, defence, attack, primary_type, secondary_type)" +
                            " VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //set attributer

            preparedStatement.setString(1, pokemon.getName());
            preparedStatement.setInt(2, pokemon.getSpeed());
            preparedStatement.setInt(3, pokemon.getHp());
            preparedStatement.setInt(4, pokemon.getSpecialDefence());
            preparedStatement.setInt(5, pokemon.getSpecialAttack());
            preparedStatement.setInt(6, pokemon.getDefence());
            preparedStatement.setInt(7, pokemon.getAttack());
            preparedStatement.setString(8, pokemon.getPrimaryType());
            preparedStatement.setString(9, pokemon.getSecondaryType());

            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Could not create");
            sqlException.printStackTrace();
        }
    }

    public void updatePokemon(int id, Pokemon updatedPokemon) {



        String sql = "UPDATE pokemon SET name = ?, speed = ?, hp = ?, special_defence = ?," +
                " special_attack = ?, defence = ?, attack = ?, primary_type = ?, secondary_type = ? WHERE pokedex_number =" + id + ";";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, updatedPokemon.getName());
            ps.setInt(2, updatedPokemon.getSpeed());
            ps.setInt(3, updatedPokemon.getHp());
            ps.setInt(4, updatedPokemon.getSpecialDefence());
            ps.setInt(5, updatedPokemon.getSpecialAttack());
            ps.setInt(6, updatedPokemon.getDefence());
            ps.setInt(7, updatedPokemon.getAttack());
            ps.setString(8, updatedPokemon.getPrimaryType());
            ps.setString(9, updatedPokemon.getSecondaryType());
            ps.executeUpdate();
        } catch (SQLException s) {
            System.out.println("Update failed " + s);
        }


    }

    public Pokemon retrievePokemonbyID(int id) {

        Pokemon currentPokemon = new Pokemon();

        try {

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM pokemon WHERE pokedex_number =" + id + ";";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt(1);
                String name = rs.getString(2);
                int speed = rs.getInt(3);
                int hp = rs.getInt(4);
                int specialDefence = rs.getInt(5);
                int specialAttack = rs.getInt(6);
                int defence = rs.getInt(7);
                int attack = rs.getInt(8);
                String primaryType = rs.getString(9);
                String secondaryType = rs.getString(10);
                currentPokemon = new Pokemon(id, name, speed, hp, specialDefence, specialAttack, defence, attack, primaryType, secondaryType);

            }
            return currentPokemon;
        } catch (SQLException s) {

        }
        return currentPokemon;
    }
}
