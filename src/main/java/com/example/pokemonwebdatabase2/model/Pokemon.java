package com.example.pokemonwebdatabase2.model;

public class Pokemon {

    private int id;
    private String name;
    private int speed;
    private int hp;
    private int specialDefence;
    private int specialAttack;
    private int defence;
    private int attack;
    private String primaryType;
    private String secondaryType;

    public Pokemon(){}

    public Pokemon(int id, String name, int speed, int hp, int specialDefence, int specialAttack, int defence, int attack, String primaryType, String secondaryType) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.hp = hp;
        this.specialDefence = specialDefence;
        this.specialAttack = specialAttack;
        this.defence = defence;
        this.attack = attack;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    public int getDefence() {
        return defence;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getAttack() {
        return attack;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public String getSecondaryType() {
        return secondaryType;
    }
}
