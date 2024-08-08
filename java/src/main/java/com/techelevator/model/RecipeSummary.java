package com.techelevator.model;

public class RecipeSummary {

    private int recipeId;
    private String name;


    public RecipeSummary() {};

    public RecipeSummary(int recipeId, String name) {
        this.recipeId = recipeId;
        this.name = name;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
