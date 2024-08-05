package com.techelevator.dao;

import com.techelevator.model.Ingredients;

import java.util.List;

public interface IngredientsDao {

    List<Ingredients> getAllIngredients ();

    List<Ingredients> getIngredientsByRecipe (int recipeId);

    Ingredients createIngredient (Ingredients ingredients);



    //future ingredient for grocery list? possibly


}
