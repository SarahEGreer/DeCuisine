package com.techelevator.dao;


import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {

    List<Recipe> getAllRecipes();

    List<Recipe> getCreatedRecipesByUser(int userId);

    Recipe createRecipe (Recipe recipe);

    Recipe updateRecipe (Recipe recipe);



    //meal planner (future sprint)
    List<Recipe> getRecipeByTrackedByUser (int userId);




    //Front end or backend
//    List<Recipe> getRecipeByRecipeName(String recipeName);





}
