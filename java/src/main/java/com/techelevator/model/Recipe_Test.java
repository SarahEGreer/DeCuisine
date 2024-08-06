package com.techelevator.model;

import java.util.List;

public class Recipe_Test {

    private int recipeId;
    private int userId;
    private String recipeName;
    private String recipeDescription;
    private String recipeInstructions;
    private String prepTime;
    private String cookTime;
    private String servings;

    //come back too
    private List<Recipe_DetailDto_Test.IngredientDetail> ingredientsList;

    public Recipe_Test(){};

    public Recipe_Test(int recipeId, int userId, String recipeName, String recipeDescription, String recipeInstructions, String prepTime, String cookTime, String servings, List<Recipe_DetailDto_Test.IngredientDetail> ingredientsList) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeInstructions = recipeInstructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.ingredientsList = ingredientsList;
    }


    public List<Recipe_DetailDto_Test.IngredientDetail> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Recipe_DetailDto_Test.IngredientDetail> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }
}
