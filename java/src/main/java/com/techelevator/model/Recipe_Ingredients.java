package com.techelevator.model;

public class Recipe_Ingredients {
    private int recipeId;
    private int ingredientId;
    private String name;
    private double amount;
    private String unit;
    private String systemOfMeasurement;
    public Recipe_Ingredients(){};

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
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

    public Recipe_Ingredients(int recipeId, int ingredientId, String name, double amount, String unit, String systemOfMeasurement) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.systemOfMeasurement = systemOfMeasurement;

    }
}
