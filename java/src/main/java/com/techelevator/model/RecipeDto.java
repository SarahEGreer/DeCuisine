package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RecipeDto {

    @NotNull
    private String recipeName;
    private String recipeDescription;
    @NotNull
    private String recipeInstructions;
    private int prepTime;
    private int cookTime;
    private int servings;

    private List<Recipe_IngredientDto> recipe_ingredientDtoList;


    public RecipeDto(String recipeName, String recipeDescription, String recipeInstructions, int prepTime, int cookTime, int servings, List<Recipe_IngredientDto> recipe_ingredientDto) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeInstructions = recipeInstructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.recipe_ingredientDtoList = recipe_ingredientDto;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public List<Recipe_IngredientDto> getRecipe_ingredientDtoList() {
        return recipe_ingredientDtoList;
    }
}
