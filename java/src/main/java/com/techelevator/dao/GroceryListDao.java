package com.techelevator.dao;

import com.techelevator.model.GroceryListDto;
import com.techelevator.model.Recipe_IngredientDto;

import java.util.List;

public interface GroceryListDao {

    List<Recipe_IngredientDto> getGroceryListByMealPlan(int mealplanId);

    void updateGroceryList(int mealplanId, List<Recipe_IngredientDto> ingredients);



}
