package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.Recipe_Ingredients;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipe;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Recipe recipe = mapRowToRecipe(results);
                recipes.add(recipe);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return recipes;
    }

    @Override
    public List<Recipe> getCreatedRecipesByUser (int userId) {
        List<Recipe> recipesByUser = new ArrayList<>();
        String sql = "SELECT * FROM recipe WHERE created_by_user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Recipe recipe = mapRowToRecipe(results);
                recipesByUser.add(recipe);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return recipesByUser;
    }

    @Override
    public void createRecipe(Recipe newRecipe, int userId, List<Recipe_Ingredients> ingredients) {
        //put into recipe
        int newRecipeId = 0;
        List<Integer> ingredientsIdList = new ArrayList<>();
        String recipeSql = "INSERT INTO recipe (created_by_user_id, recipe_name, description, instructions, prep_time, cook_time, servings) VALUES (?,?,?,?,?,?,?,?);";
        try {
            newRecipeId = jdbcTemplate.queryForObject(recipeSql, int.class,
            userId, newRecipe.getRecipeName(), newRecipe.getRecipeDescription(),
            newRecipe.getRecipeInstructions(), newRecipe.getPrepTime(), newRecipe.getCookTime(),
            newRecipe.getServings());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        //put into ingredient
        int ingredientId = 0;
        for(Recipe_Ingredients i: ingredients) {
            try{
                String ingredientsSql = "INSERT INTO ingredients (ingredient_name)VALUES('?')\n" +
                        "ON CONFLICT (ingredient_name) DO UPDATE\n" +
                        "\tSET ingredient_id = (SELECT ingredient_id FROM ingredients WHERE ingredient_name = '?')\n" +
                        "RETURNING ingredient_id;";
                ingredientId = jdbcTemplate.queryForObject(ingredientsSql, int.class, i.getIngredientName());
                String recipe_ingredientsSql = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES (?,?,?,?,?)";
                int placeholder =  jdbcTemplate.queryForObject(recipe_ingredientsSql, int.class, newRecipeId, ingredientId, i.getIngredientAmount(),i.getIngredientUnitType() , i.getIngredientSystemOfMeasurement());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        }
        //put into tags
        String tagsSql = "";
    }



    private Recipe mapRowToRecipe(SqlRowSet rs) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setUserId(rs.getInt("created_by_user_id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setRecipeDescription(rs.getString("description"));
        recipe.setRecipeInstructions(rs.getString("instructions"));
        recipe.setPrepTime(rs.getString("prep_time"));
        recipe.setCookTime(rs.getString("cook_time"));
        recipe.setServings(rs.getString("servings"));
        return recipe;
    }



}
