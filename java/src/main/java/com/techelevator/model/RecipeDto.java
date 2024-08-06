package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class RecipeDto {

    @NotNull
    private String recipeName;
    private String recipeDescription;
    @NotNull
    private String recipeInstructions;
    private int prepTime;
    private int cookTime;
    private int servings;


    public RecipeDto(String recipeName, String recipeDescription, String recipeInstructions, int prepTime, int cookTime, int servings) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeInstructions = recipeInstructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
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


}
