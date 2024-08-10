package com.techelevator.model;

public class RecipeSummary {

    private int recipeId;
    private String name;
    private String photoUrl;


    public RecipeSummary() {};

    public RecipeSummary(int recipeId, String name, String photoUrl) {
        this.recipeId = recipeId;
        this.name = name;
        this.photoUrl = photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
