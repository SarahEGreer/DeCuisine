package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Recipe;
import com.techelevator.model.Recipe_Ingredients;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcIngredientsDao implements IngredientsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe_Ingredients> getAllIngredients() {
        List<Recipe_Ingredients> ingredients = new ArrayList<>();
        String sql = "SELECT ingredient_name FROM ingredients;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Recipe_Ingredients ingredient = mapRowToIngredients(results);
                ingredients.add(ingredient);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return ingredients;
    }

    @Override
    public List<Recipe_Ingredients> getIngredientsByRecipe (int recipeId) {
        List<Recipe_Ingredients> ingredientsByRecipe = new ArrayList<>();
        String sql = "SELECT i.ingredient_id, i.ingredient_name, ri.amount, ri.unit_type, ri.system_of_measurement\n" +
                "FROM ingredients i \n" +
                "JOIN recipes_ingredients ri ON i.ingredient_id = ri.ingredients_id\n" +
                "WHERE ri.recipe_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
            while (results.next()) {
                Recipe_Ingredients ingredients = mapRowToIngredients(results);
                ingredientsByRecipe.add(ingredients);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return ingredientsByRecipe;
    }

    @Override
    public Recipe_Ingredients createIngredient(Recipe_Ingredients recipeIngredients) {

        String sql = "INSERT INTO ingredients (ingredient_name) VALUES (?);";

        int newIngredientId;
        try {
            newIngredientId = jdbcTemplate.queryForObject(sql, Integer.class, recipeIngredients.getIngredientName());
            recipeIngredients.setIngredientId(newIngredientId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return recipeIngredients;
    }

//    @Override
    public Recipe_Ingredients getIngredientByIngredientId (int ingredientId) {
        Recipe_Ingredients ingredientByIngredientId = null;
        String sql = "SELECT ingredient_id, ingredient_name FROM ingredients WHERE ingredient_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ingredientId);
            if (results.next()) {
                ingredientByIngredientId = mapRowToIngredient(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return ingredientByIngredientId;
    }



    private Recipe_Ingredients mapRowToIngredients(SqlRowSet rs) {
        Recipe_Ingredients ingredient = new Recipe_Ingredients();
        ingredient.setRecipeId(rs.getInt("recipe_id"));
        ingredient.setIngredientId(rs.getInt("ingredient_id"));
        ingredient.setIngredientAmount(rs.getDouble("amount"));
        ingredient.setIngredientUnitType(rs.getString("unit_type"));
        ingredient.setIngredientSystemOfMeasurement(rs.getString("system_of_measurement"));
        return ingredient;
    }

    private Recipe_Ingredients mapRowToIngredient(SqlRowSet rs) {
        Recipe_Ingredients ingredient = new Recipe_Ingredients();
        ingredient.setIngredientId(rs.getInt("ingredient_id"));
        ingredient.setIngredientName(rs.getString("ingredient_name"));
        return ingredient;
    }




}
