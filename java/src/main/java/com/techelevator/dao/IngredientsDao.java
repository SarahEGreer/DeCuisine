package com.techelevator.dao;

import com.techelevator.model.Recipe;
import com.techelevator.model.Recipe_Ingredients;

import java.util.List;

public interface IngredientsDao {

    List<String> getAllIngredients ();

    List<Recipe_Ingredients> getIngredientsByRecipe (int recipeId);

    Recipe_Ingredients createIngredient (Recipe_Ingredients ingredients);



    //future ingredient for grocery list? possibly


}
