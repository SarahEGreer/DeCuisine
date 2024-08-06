package com.techelevator.model;

import java.util.List;

public class TestDto {

    private RecipeDto recipeDto;
    private List<Recipe_detailDto> recipe_detailDtoList;

    public TestDto(RecipeDto recipeDto, List<Recipe_detailDto> recipe_detailDtoList) {
        this.recipeDto = recipeDto;
        this.recipe_detailDtoList = recipe_detailDtoList;
    }

    public RecipeDto getRecipeDto() {
        return recipeDto;
    }

    public void setRecipeDto(RecipeDto recipeDto) {
        this.recipeDto = recipeDto;
    }

    public List<Recipe_detailDto> getRecipe_detailDtoList() {
        return recipe_detailDtoList;
    }

    public void setRecipe_detailDtoList(List<Recipe_detailDto> recipe_detailDtoList) {
        this.recipe_detailDtoList = recipe_detailDtoList;
    }
}


