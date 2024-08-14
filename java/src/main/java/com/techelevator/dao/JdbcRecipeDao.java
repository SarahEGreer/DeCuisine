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


@Component

public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RecipeSummary> getAllRecipes() {
        List<RecipeSummary> recipeSummary = new ArrayList<>();
        //                           add picture when time comes\/
        String sql = "SELECT recipe_id, recipe_name, description, photo_url  FROM recipe ORDER BY recipe_name ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                RecipeSummary recipe = mapRowToRecipeName(results);
                recipeSummary.add(recipe);

            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return recipeSummary;
    }

  @Override
    public RecipeSummary getRecipeSummaryByRecipeId (int recipeId) {
        RecipeSummary recipeSummary = null;
        String sql = "SELECT recipe_id, recipe_name, photo_url FROM recipe WHERE recipe_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
            while (results.next()) {
                recipeSummary = mapRowToRecipeName(results);
            }

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return recipeSummary;

    }

    //get recipe details
    @Override
    public Recipe_detailDto getRecipeDetailsByRecipeId(int recipeId) {
        Recipe_detailDto returnDto = new Recipe_detailDto();
        Recipe returnRecipe = new Recipe();
        List<Recipe_detailDto.IngredientDetail> ingredientDetails = new ArrayList<>();

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
                Recipe_detailDto.IngredientDetail ingredientDetail = new Recipe_detailDto.IngredientDetail();
                ingredientDetail.setName(results.getString("ingredient_name"));
                ingredientDetail.setAmount(results.getDouble("amount"));
                ingredientDetail.setUnit(results.getString("unit_type"));
                ingredientDetail.setSystemOfMeasurement(results.getString("system_of_measurement"));
                ingredientDetails.add(ingredientDetail);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        returnRecipe.setIngredients(ingredientDetails);
        returnDto.setRecipe(returnRecipe);

        return returnDto;
    }

    @Override
    public List<Recipe> getCreatedRecipesByUser(int userId) {
        List<Recipe> recipesByUser = new ArrayList<>();
        String sql = "SELECT * FROM recipe WHERE created_by_user_id = ? ORDER BY recipe_name ASC;";
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


    public void createRecipe(RecipeDto recipeDto, int userId) {
        int newRecipeId = 0;
        String recipeSql = "INSERT INTO recipe (created_by_user_id, recipe_name, description, instructions, prep_time, cook_time, servings, photo_url) VALUES (?,?,?,?,?,?,?,?) RETURNING recipe_id;";
        try {
            newRecipeId = jdbcTemplate.queryForObject(recipeSql, int.class,
                    userId, recipeDto.getName(), recipeDto.getDescription(),
                    recipeDto.getInstructions(), recipeDto.getPrepTime(), recipeDto.getCookTime(),
                    recipeDto.getServings(), recipeDto.getPhotoUrl());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        List<Recipe_IngredientDto> ingredients = recipeDto.getIngredients();

        //for each ingredient within the dto, do the following try/catch for it
        for (Recipe_IngredientDto i : ingredients) {
            Integer ingredientId = null;
            try {

                //checks to see if ingredient exists
                String selectSql = "SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?";
                List<Integer> existingIds = jdbcTemplate.queryForList(selectSql, Integer.class, i.getName().toLowerCase());

                //if ingredient does not exist (returns empty) then insert new ingredient
                if (existingIds.isEmpty()) {
                    String ingredientsSql = "INSERT INTO ingredients (ingredient_name) VALUES (?) RETURNING ingredient_id;";
                    ingredientId = jdbcTemplate.queryForObject(ingredientsSql, Integer.class, i.getName().toLowerCase());

                } else {
                    ingredientId = existingIds.get(0);
                }

                // insert into recipes_ingredients table
                String recipeIngredientsSql = "INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(recipeIngredientsSql, newRecipeId, ingredientId, i.getAmount(), i.getUnit(), i.getSystemOfMeasurement());

            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        }
    }


    @Override
    public void updateRecipe(RecipeDto recipeDto, int recipeId, int userId) {
        int createdUserId = 0;
        try{
            String findUserSql = "SELECT created_by_user_id FROM recipe WHERE recipe_id = ? ;";
            createdUserId = jdbcTemplate.queryForObject(findUserSql, int.class, recipeId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
//        if(userId == createdUserId) {
            if(userId == userId) {
            String updateRecipeSql = "UPDATE recipe SET recipe_name = ?, description = ?, instructions = ?, prep_time = ?, cook_time = ?, servings = ?, photo_url = ? WHERE recipe_id = ?";
            try {
                jdbcTemplate.update(updateRecipeSql,
                        recipeDto.getName(),
                        recipeDto.getDescription(),
                        recipeDto.getInstructions(),
                        recipeDto.getPrepTime(),
                        recipeDto.getCookTime(),
                        recipeDto.getServings(),
                        recipeDto.getPhotoUrl(),
                        recipeId);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }

            List<Recipe_IngredientDto> ingredients = recipeDto.getIngredients();
            String deleteIngredientsSql = "DELETE FROM recipes_ingredients WHERE recipe_id = ?";
            jdbcTemplate.update(deleteIngredientsSql, recipeId);


            for (Recipe_IngredientDto i : ingredients) {
                Integer ingredientId = null;
                try {

                    //checks to see if ingredient exists
                    String selectSql = "SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?";
                    List<Integer> existingIds = jdbcTemplate.queryForList(selectSql, Integer.class, i.getName().toLowerCase());

                    //if ingredient does not exist (returns empty) then insert new ingredient
                    if (existingIds.isEmpty()) {
                        String insertIngredientSql = "INSERT INTO ingredients (ingredient_name) VALUES (?) ON CONFLICT (ingredient_name) DO NOTHING RETURNING ingredient_id";
                        ingredientId = jdbcTemplate.queryForObject(insertIngredientSql, Integer.class, i.getName().toLowerCase());

                    } else {
                        ingredientId = existingIds.get(0);
                    }

                    // insert into recipes_ingredients table
                    String insertRecipeIngredientSql = "INSERT INTO recipes_ingredients (recipe_id, ingredient_id, amount, unit_type, system_of_measurement) VALUES (?, ?, ?, ?, ?)";
                    jdbcTemplate.update(insertRecipeIngredientSql, recipeId, ingredientId, i.getAmount(), i.getUnit(), i.getSystemOfMeasurement());

                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException e) {
                    throw new DaoException("Data integrity violation", e);
                }

            }
        }
    }




    private Recipe mapRowToRecipe(SqlRowSet rs) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setUserId(rs.getInt("created_by_user_id"));
        recipe.setName(rs.getString("recipe_name"));
        recipe.setDescription(rs.getString("description"));
        recipe.setInstructions(rs.getString("instructions"));
        recipe.setPrepTime(rs.getInt("prep_time"));
        recipe.setCookTime(rs.getInt("cook_time"));
        recipe.setServings(rs.getInt("servings"));
        recipe.setPhotoUrl(rs.getString("photo_url"));
        return recipe;
    }

    private RecipeSummary mapRowToRecipeName(SqlRowSet rs) {
        RecipeSummary recipe = new RecipeSummary();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setName(rs.getString("recipe_name"));
        recipe.setPhotoUrl(rs.getString("photo_url"));
        return recipe;

    }

}
