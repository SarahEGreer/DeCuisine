package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.GroceryListDto;
import com.techelevator.model.Recipe_IngredientDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcGroceryListDao implements GroceryListDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGroceryListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Recipe_IngredientDto> getGroceryListByUserId(int userId) {
        String sql = "SELECT item_name, amount, unit_type " +
                "FROM user_grocery_list " +
                "WHERE user_id = ? " +
                "ORDER BY item_name ASC;";

        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Recipe_IngredientDto item = new Recipe_IngredientDto();
                item.setName(results.getString("item_name"));
                item.setAmount(results.getDouble("amount"));
                item.setUnit(results.getString("unit_type"));
                groceryItems.add(item);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return groceryItems;
    }

    @Override
    public void addToGroceryListByMealPlan(int mealplanId, int userId) {

        //check if the user is authorized to view this meal plan
        String userCheckSql = "SELECT created_by_user_id FROM mealplan WHERE mealplan_id = ?";
        Integer createdUserId = jdbcTemplate.queryForObject(userCheckSql, Integer.class, mealplanId);

        if (!createdUserId.equals(userId)) {
            throw new DaoException("User is not authorized to view this meal plan.");
        }

        //get the aggregated ingredients
        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();

        //aggregated SQL Query to get all ingredients in meal plan
        String sql = "SELECT i.ingredient_name, SUM(ri.amount) AS total_amount, ri.unit_type \n" +
                "FROM ingredients i \n" +
                "JOIN recipes_ingredients ri ON i.ingredient_id = ri.ingredient_id \n" +
                "JOIN ( \n" +
                "     SELECT breakfast_recipe_id AS recipe_id FROM mealplan_recipe WHERE mealplan_id = ? \n" +
                "     UNION ALL \n" +
                "    SELECT lunch_recipe_id AS recipe_id FROM mealplan_recipe WHERE mealplan_id = ? \n" +
                "      UNION ALL\n" +
                "     SELECT dinner_recipe_id AS recipe_id FROM mealplan_recipe WHERE mealplan_id = ?  \n" +
                "      ) AS all_recipes ON ri.recipe_id = all_recipes.recipe_id \n" +
                "       GROUP BY i.ingredient_name, ri.unit_type \n" +
                "       ORDER BY i.ingredient_name ASC;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealplanId, mealplanId, mealplanId);
            while (results.next()) {
                Recipe_IngredientDto item = new Recipe_IngredientDto();
                item.setName(results.getString("ingredient_name"));
                item.setAmount(results.getDouble("total_amount"));
                item.setUnit(results.getString("unit_type"));
                groceryItems.add(item);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        //merge list with existing grocery list
        String selectSql = "SELECT amount FROM user_grocery_list " +
                "WHERE user_id = ? AND item_name = ? AND unit_type = ?";

        String updateSql = "UPDATE user_grocery_list SET amount = ? " +
                "WHERE user_id = ? AND item_name = ? AND unit_type = ?";

        String insertSql = "INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type) " +
                "VALUES (?, ?, ?, ?)";

        try {
            for (Recipe_IngredientDto item : groceryItems) {
                Double existingAmount = null;
                try {
                    //check if the item already exists in the grocery list
                    existingAmount = jdbcTemplate.queryForObject(
                            selectSql,
                            new Object[]{userId, item.getName(), item.getUnit()},
                            Double.class
                    );
                } catch (EmptyResultDataAccessException e) {
                    //if the ingredient doesn't exist, insert it
                    jdbcTemplate.update(insertSql, userId, item.getName(), item.getAmount(), item.getUnit());
                    continue;
                }

                if (existingAmount != null) {
                    //if the ingredient exists, add the amounts together and update
                    double newAmount = existingAmount + item.getAmount();
                    jdbcTemplate.update(updateSql, newAmount, userId, item.getName(), item.getUnit());
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void addIngredientsToGroceryListByRecipeId(int recipeId, int userId) {
        //aggregated ingredients based on recipeId
        String sql = "SELECT i.ingredient_name, SUM(ri.amount) AS total_amount, ri.unit_type " +
                "FROM ingredients i " +
                "JOIN recipes_ingredients ri ON i.ingredient_id = ri.ingredient_id " +
                "WHERE ri.recipe_id = ? " +
                "GROUP BY i.ingredient_name, ri.unit_type " +
                "ORDER BY i.ingredient_name ASC;";

        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
            while (results.next()) {
                Recipe_IngredientDto item = new Recipe_IngredientDto();
                item.setName(results.getString("ingredient_name"));
                item.setAmount(results.getDouble("total_amount"));
                item.setUnit(results.getString("unit_type"));
                groceryItems.add(item);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        //merge with existing list
        String selectSql = "SELECT amount FROM user_grocery_list " +
                "WHERE user_id = ? AND item_name = ? AND unit_type = ?";

        String updateSql = "UPDATE user_grocery_list SET amount = ? " +
                "WHERE user_id = ? AND item_name = ? AND unit_type = ?";

        String insertSql = "INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type) " +
                "VALUES (?, ?, ?, ?)";

        try {
            for (Recipe_IngredientDto item : groceryItems) {
                Double existingAmount = null;
                try {
                    //check if item exists in grocery list
                    existingAmount = jdbcTemplate.queryForObject(
                            selectSql,
                            new Object[]{userId, item.getName(), item.getUnit()},
                            Double.class
                    );
                } catch (EmptyResultDataAccessException e) {
                    //if doesn't exist insert it
                    jdbcTemplate.update(insertSql, userId, item.getName(), item.getAmount(), item.getUnit());
                    continue;
                }

                if (existingAmount != null) {
                    //if exists and item name and unit type are the same, add new amount to existing amount
                    double newAmount = existingAmount + item.getAmount();
                    jdbcTemplate.update(updateSql, newAmount, userId, item.getName(), item.getUnit());
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    //Edit view change from ingredient_id to ingredient_name- if ingredient doesn't exist you still can add it in it
    @Override
    public void updateGroceryList(List<Recipe_IngredientDto> groceryItems, int userId) {
        // deleting existing ingredients for grocery list
        String deleteSql = "DELETE FROM user_grocery_list WHERE user_id = ?";

        try {
            jdbcTemplate.update(deleteSql, userId);

            // inserting the new ingredient list for grocery list
            String insertSql = "INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type) " +
                    "VALUES (?, ?, ?, ?)";

            for (Recipe_IngredientDto item : groceryItems) {
                jdbcTemplate.update(insertSql, userId, item.getName(), item.getAmount(), item.getUnit());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


}
