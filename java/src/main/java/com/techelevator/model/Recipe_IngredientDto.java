package com.techelevator.model;

public class Recipe_IngredientDto {
    private String ingredientName;
    private double ingredientAmount;
    private String ingredientUnitType;
    private String ingredientSystemOfMeasurement;

    public Recipe_IngredientDto (String ingredientName, double ingredientAmount, String ingredientUnitType, String ingredientSystemOfMeasurement) {

        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;
        this.ingredientUnitType = ingredientUnitType;
        this.ingredientSystemOfMeasurement = ingredientSystemOfMeasurement;
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
