package com.techelevator.model;

import java.util.List;

public class Recipe_detailDto {

    private Recipe recipe;

    public Recipe_detailDto() {}

    public Recipe_detailDto(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public static class IngredientDetail {
        private String name;
        private double amount;
        private String unit;
        private String systemOfMeasurement;

        public IngredientDetail() {}

        public IngredientDetail(String name, double amount, String unit, String systemOfMeasurement) {
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
}
