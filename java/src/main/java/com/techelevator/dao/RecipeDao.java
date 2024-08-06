package com.techelevator.dao;


import com.techelevator.model.Recipe;
import com.techelevator.model.Recipe_Ingredients;

import java.util.List;

public interface RecipeDao {

    List<Recipe> getAllRecipes();

    List<Recipe> getCreatedRecipesByUser(int userId);

     void createRecipe (Recipe recipe, int userId, List<Recipe_Ingredients> ingredients );

//    Recipe updateRecipe (Recipe recipe);



    //meal planner (future sprint)
//    List<Recipe> getRecipeByTrackedByUser (int userId);




    //Front end or backend
//    List<Recipe> getRecipeByRecipeName(String recipeName);





}
