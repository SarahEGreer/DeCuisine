package com.techelevator.model;

public class Recipe_Ingredients {
    private int recipeId;
    private int ingredientId;
    private String ingredientName;
    private double ingredientAmount;
    private String ingredientUnitType;
    private String ingredientSystemOfMeasurement;
    public Recipe_Ingredients(){};

    public Recipe_Ingredients(int recipeId, int ingredientId, String ingredientName, double ingredientAmount, String ingredientUnitType, String ingredientSystemOfMeasurement) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;
        this.ingredientUnitType = ingredientUnitType;
        this.ingredientSystemOfMeasurement = ingredientSystemOfMeasurement;
    }

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

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(double ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public String getIngredientUnitType() {
        return ingredientUnitType;
    }

    public void setIngredientUnitType(String ingredientUnitType) {
        this.ingredientUnitType = ingredientUnitType;
    }

    public String getIngredientSystemOfMeasurement() {
        return ingredientSystemOfMeasurement;
    }

    public void setIngredientSystemOfMeasurement(String ingredientSystemOfMeasurement) {
        this.ingredientSystemOfMeasurement = ingredientSystemOfMeasurement;
    }
}
