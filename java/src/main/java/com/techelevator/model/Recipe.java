package com.techelevator.model;

import java.util.List;

public class Recipe {

    private int recipeId;
    private int userId;
    private String recipeName;
    private String recipeDescription;
    private String recipeInstructions;
    private int prepTime;
    private int cookTime;
    private int servings;
//    private List<String> ingredientsList;
    private List<Recipe_detailDto.IngredientDetail> ingredientsList;

    public Recipe(){};

    public Recipe(int recipeId, int userId, String recipeName, String recipeDescription, String recipeInstructions, int prepTime, int cookTime, int servings, List<Recipe_detailDto.IngredientDetail> ingredientsList) {
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

//    public List<String> getIngredientsList() {
//        return ingredientsList;
//    }
//
//    public void setIngredientsList(List<String> ingredientsList) {
//        this.ingredientsList = ingredientsList;
//    }

    public List<Recipe_detailDto.IngredientDetail> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Recipe_detailDto.IngredientDetail> ingredientsList) {
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
