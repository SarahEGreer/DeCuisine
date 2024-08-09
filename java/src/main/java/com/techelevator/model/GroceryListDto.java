package com.techelevator.model;

import java.util.List;

public class GroceryListDto {

    private int mealplanId;
    private int startDate;
    private String name;
    private List<Recipe_IngredientDto> ingredients;
    public GroceryListDto() {}

    public GroceryListDto(int mealplanId, int startDate, String name, List<Recipe_IngredientDto> ingredients) {
        this.mealplanId = mealplanId;
        this.startDate = startDate;
        this.name = name;
        this.ingredients = ingredients;
    }

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe_IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Recipe_IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
