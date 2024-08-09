package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipe_IngredientDto {
    private String name;
    private double amount;
    private String unit;
    private String systemOfMeasurement;

    public Recipe_IngredientDto() {}


    public Recipe_IngredientDto(String name, double amount, String unit, String systemOfMeasurement) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.systemOfMeasurement = systemOfMeasurement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSystemOfMeasurement() {
        return systemOfMeasurement;
    }

    public void setSystemOfMeasurement(String systemOfMeasurement) {
        this.systemOfMeasurement = systemOfMeasurement;
    }
}
