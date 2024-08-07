package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RecipeDto {

    @NotNull
    private String name;
    private String description;
    @NotNull
    private String instructions;
    private int prepTime;
    private int cookTime;
    private int servings;

    private List<Recipe_IngredientDto> recipe_ingredientDtoList;

    public RecipeDto(String name, String description, String instructions, int prepTime, int cookTime, int servings, List<Recipe_IngredientDto> recipe_ingredientDtoList) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.recipe_ingredientDtoList = recipe_ingredientDtoList;
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

    public List<Recipe_IngredientDto> getRecipe_ingredientDtoList() {
        return recipe_ingredientDtoList;
    }

    public void setRecipe_ingredientDtoList(List<Recipe_IngredientDto> recipe_ingredientDtoList) {
        this.recipe_ingredientDtoList = recipe_ingredientDtoList;
    }
}
