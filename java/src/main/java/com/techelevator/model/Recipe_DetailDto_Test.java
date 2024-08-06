package com.techelevator.model;

import java.util.List;

public class Recipe_DetailDto_Test {

    private Recipe_Test recipe;

    public Recipe_DetailDto_Test() {}

    public Recipe_DetailDto_Test(Recipe_Test recipe) {
        this.recipe = recipe;
    }

    public Recipe_Test getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe_Test recipe) {
        this.recipe = recipe;
    }

    public static class IngredientDetail {
        private String ingredientName;
        private double amount;
        private String unitType;
        private String systemOfMeasurement;

        public IngredientDetail() {}

        public IngredientDetail(String ingredientName, double amount, String unitType, String systemOfMeasurement) {
            this.ingredientName = ingredientName;
            this.amount = amount;
            this.unitType = unitType;
            this.systemOfMeasurement = systemOfMeasurement;
        }

        public String getIngredientName() {
            return ingredientName;
        }

        public void setIngredientName(String ingredientName) {
            this.ingredientName = ingredientName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getUnitType() {
            return unitType;
        }

        public void setUnitType(String unitType) {
            this.unitType = unitType;
        }

        public String getSystemOfMeasurement() {
            return systemOfMeasurement;
        }

        public void setSystemOfMeasurement(String systemOfMeasurement) {
            this.systemOfMeasurement = systemOfMeasurement;
        }
    }
}
