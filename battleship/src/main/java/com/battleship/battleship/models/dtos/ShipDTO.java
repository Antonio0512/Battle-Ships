package com.battleship.battleship.models.dtos;


import java.time.LocalDate;

import com.battleship.battleship.models.enums.CategoryEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ShipDTO {
    @Size(min = 2, max = 10)
    @NotBlank
    private String name;

    @Positive
    private long health;

    @Positive
    private long power;
    
    @PastOrPresent
    private LocalDate created;

    @NotNull
    private CategoryEnum category;

    public ShipDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
