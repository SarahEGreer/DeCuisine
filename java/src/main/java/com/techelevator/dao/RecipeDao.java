package com.techelevator.dao;


import com.techelevator.model.Recipe;
import com.techelevator.model.Recipe_Ingredients;
import com.techelevator.model.Recipe_detailDto;

import java.util.List;

public interface RecipeDao {

    List<Recipe> getAllRecipes();

    //get recipe details
    Recipe_detailDto getRecipeDetailsByRecipeId(int inputId);

    List<Recipe> getCreatedRecipesByUser(int userId);

     void createRecipe (Recipe recipe, int userId, List<Recipe_Ingredients> ingredients );

//    Recipe updateRecipe (Recipe recipe);



    //meal planner (future sprint)
//    List<Recipe> getRecipeByTrackedByUser (int userId);




    //Front end or backend
//    List<Recipe> getRecipeByRecipeName(String recipeName);





}
