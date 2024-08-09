package com.techelevator.dao;


import com.techelevator.model.*;

import java.util.List;

public interface RecipeDao {

    List<RecipeSummary> getAllRecipes();

    RecipeSummary getRecipeSummaryByRecipeId(int recipeId);


    //get recipe details
    Recipe_detailDto getRecipeDetailsByRecipeId(int inputId);

    List<Recipe> getCreatedRecipesByUser(int userId);

     void createRecipe (RecipeDto recipeDto, int userId );

     void updateRecipe (RecipeDto recipeDto, int recipeId, int userId);

//    Recipe updateRecipe (Recipe recipe);



    //meal planner (future sprint)
//    List<Recipe> getRecipeByTrackedByUser (int userId);




    //Front end or backend
//    List<Recipe> getRecipeByRecipeName(String recipeName);





}
