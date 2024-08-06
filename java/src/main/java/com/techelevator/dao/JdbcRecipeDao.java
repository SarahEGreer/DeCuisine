package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component

public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        //                           add picture when time comes\/
        String sql = "SELECT recipe_id, recipe_name, description  FROM recipe;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Recipe recipe = mapRowToRecipe(results);
                recipes.add(recipe);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return recipes;
    }

    //get recipe details
    @Override
    public Recipe_detailDto getRecipeDetailsByRecipeId(int recipeId) {
        Recipe_detailDto returnDto = new Recipe_detailDto();
        Recipe returnRecipe = new Recipe();
        List<Recipe_DetailDto_Test.IngredientDetail> ingredientDetails = new ArrayList<>();

        String rsql = "Select * FROM recipe WHERE recipe_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(rsql, recipeId);
            while (results.next()) {
                Recipe recipe = mapRowToRecipe(results);
                returnRecipe = recipe;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        String rIsql = "SELECT i.ingredient_id, i.ingredient_name, ri.recipe_id, ri.amount, ri.unit_type, ri.system_of_measurement\n" +
                                "FROM ingredients i \n" +
                                "JOIN recipes_ingredients ri ON i.ingredient_id = ri.ingredient_id\n" +
                                "WHERE ri.recipe_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(rIsql, recipeId);
            while (results.next()) {
                Recipe_DetailDto_Test.IngredientDetail ingredientDetail = new Recipe_DetailDto_Test.IngredientDetail();
                ingredientDetail.setIngredientName(results.getString("ingredient_name"));
                ingredientDetail.setAmount(results.getDouble("amount"));
                ingredientDetail.setUnitType(results.getString("unit_type"));
                ingredientDetail.setSystemOfMeasurement(results.getString("system_of_measurement"));
                ingredientDetails.add(ingredientDetail);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        returnRecipe.setIngredientsList(ingredientDetails);
        returnDto.setRecipe(returnRecipe);

        return returnDto;
    }

    @Override
    public List<Recipe> getCreatedRecipesByUser(int userId) {
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
//    public void createRecipe(Recipe newRecipe, int userId, List<Recipe_Ingredients> ingredients) {
        public void createRecipe(RecipeDto recipeDto, int userId) {
        //put into recipe
        int newRecipeId = 0;
        List<Integer> ingredientsIdList = new ArrayList<>();
        String recipeSql = "INSERT INTO recipe (created_by_user_id, recipe_name, description, instructions, prep_time, cook_time, servings) VALUES (?,?,?,?,?,?,?,?);";
        try {
            newRecipeId = jdbcTemplate.queryForObject(recipeSql, int.class,
                    userId, recipeDto.getRecipeName(), recipeDto.getRecipeDescription(),
                    recipeDto.getRecipeInstructions(), recipeDto.getPrepTime(), recipeDto.getCookTime(),
                    recipeDto.getServings());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        List<Recipe_IngredientDto> ingredients = recipeDto.getRecipe_ingredientDtoList();
        int ingredientId = 0;
        for (Recipe_IngredientDto i : ingredients) {
            try {
                String ingredientsSql = "INSERT INTO ingredients (ingredient_name)VALUES('?')\n" +
                        "ON CONFLICT (ingredient_name) DO UPDATE\n" +
                        "\tSET ingredient_id = (SELECT ingredient_id FROM ingredients WHERE ingredient_name = '?')\n" +
                        "RETURNING ingredient_id;";
                String lowerCaseName = i.getIngredientName().toLowerCase();
                ingredientId = jdbcTemplate.queryForObject(ingredientsSql, int.class, lowerCaseName);
                String recipe_ingredientsSql = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES (?,?,?,?,?)";
                int placeholder = jdbcTemplate.queryForObject(recipe_ingredientsSql, int.class, newRecipeId, ingredientId, i.getIngredientAmount(), i.getIngredientUnitType(), i.getIngredientSystemOfMeasurement());
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
        recipe.setPrepTime(rs.getInt("prep_time"));
        recipe.setCookTime(rs.getInt("cook_time"));
        recipe.setServings(rs.getInt("servings"));
        return recipe;
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
