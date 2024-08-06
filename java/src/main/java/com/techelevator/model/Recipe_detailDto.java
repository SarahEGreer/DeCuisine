package com.techelevator.model;

import java.util.List;

public class Recipe_detailDto {
    private List<Recipe_Ingredients> recipeIngredients;
    private Recipe recipe;
    public Recipe_detailDto(){};
    public Recipe_detailDto(List<Recipe_Ingredients> recipeIngredients, Recipe recipe) {
        this.recipeIngredients = recipeIngredients;
        this.recipe = recipe;
    }

    public List<Recipe_Ingredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<Recipe_Ingredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
