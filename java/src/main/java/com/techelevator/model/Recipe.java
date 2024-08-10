package com.techelevator.model;

import java.util.List;

public class Recipe {

    private int recipeId;
    private int userId;
    private String name;
    private String description;
    private String instructions;
    private int prepTime;
    private int cookTime;
    private int servings;
    private String photoUrl;
//    private List<String> ingredientsList;
    private List<Recipe_detailDto.IngredientDetail> ingredients;

    public Recipe(){};

    public Recipe(int recipeId, int userId, String name, String description, String instructions, int prepTime, int cookTime, int servings, String photoUrl, List<Recipe_detailDto.IngredientDetail> ingredients) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.photoUrl = photoUrl;
        this.ingredients = ingredients;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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

    public List<Recipe_detailDto.IngredientDetail> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Recipe_detailDto.IngredientDetail> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
