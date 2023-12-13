package com.battleship.battleship.models.dtos;

import com.battleship.battleship.models.Ship;

public class ShipDTO {
    private long id;

    private String name;

    private int health; 

    private int power;
    
    public ShipDTO(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.health = ship.getHealth();
        this.power = ship.getPower();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    

}
