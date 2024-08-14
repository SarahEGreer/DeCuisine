package com.techelevator.dao;

import com.techelevator.model.GroceryListDto;
import com.techelevator.model.Recipe_IngredientDto;

import java.util.List;

public interface GroceryListDao {

//    List<Recipe_IngredientDto> getGroceryListByMealPlan(int mealplanId, int userId);
    List<Recipe_IngredientDto> getGroceryListByUserId(int userId);

    void addToGroceryListByMealPlan(int mealplanId, int userId);

    void addIngredientsToGroceryListByRecipeId(int recipeId, int userId);

    void updateGroceryList(List<Recipe_IngredientDto> groceryItems, int userId);



}
