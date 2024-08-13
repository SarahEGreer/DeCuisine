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


//    @Override
//    public GroceryListDto getGroceryListByMealPlan(int mealplanId) {
//        GroceryListDto groceryListDto = new GroceryListDto();
//        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();
//
//        String mealplanSql = "SELECT mealplan_name FROM mealplan WHERE mealplan_id = ?";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(mealplanSql, mealplanId);
//            if (results.next()) {
//                groceryListDto.setMealplanId(mealplanId);
//                groceryListDto.setName(results.getString("mealplan_name"));
//
//            } else {
//                throw new DaoException("Mealplan with id " + mealplanId + " does not exist.");
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//
//        String grocerySql = "SELECT i.ingredient_name, ri.amount, ri.unit_type\n" +
//                "FROM mealplan_recipe mr\n" +
//                "JOIN recipes_ingredients ri \n" +
//                "ON mr.breakfast_recipe_id = ri.recipe_id\n" +
//                "OR mr.lunch_recipe_id = ri.recipe_id\n" +
//                "OR mr.dinner_recipe_id = ri.recipe_id\n" +
//                "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id\n" +
//                "WHERE mr.mealplan_id = ?" +
//                "ORDER BY ingredient_name ASC;";
//
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(grocerySql, mealplanId);
//            while (results.next()) {
//                Recipe_IngredientDto item = new Recipe_IngredientDto();
//                item.setName(results.getString("ingredient_name"));
//                item.setAmount(results.getDouble("amount"));
//                item.setUnit(results.getString("unit_type"));
//                groceryItems.add(item);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//
//        groceryListDto.setIngredients(groceryItems);
//        return groceryListDto;
//    }

    @Override
    public List<Recipe_IngredientDto> getGroceryListByUserId(int userId) {
//        String sql = "SELECT i.ingredient_name, ug.amount, ug.unit_type " +
//                "FROM user_grocery_list ug " +
//                "JOIN ingredients i ON ug.ingredient_id = i.ingredient_id " +
//                "WHERE ug.user_id = ? " +
//                "ORDER BY i.ingredient_name ASC;";

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

        //checking user id matches created_by_user_id
        String userCheckSql = "SELECT created_by_user_id FROM mealplan WHERE mealplan_id = ?";
        Integer createdUserId = jdbcTemplate.queryForObject(userCheckSql, Integer.class, mealplanId);

        if (!createdUserId.equals(userId)) {
            throw new DaoException("User is not authorized to view this meal plan.");
        }

        //getting ingredients within the meal plan
        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();

        String sql = "SELECT i.ingredient_name, ri.amount, ri.unit_type " +
                "FROM mealplan_recipe mr " +
                "JOIN recipes_ingredients ri " +
                "ON mr.breakfast_recipe_id = ri.recipe_id " +
                "OR mr.lunch_recipe_id = ri.recipe_id " +
                "OR mr.dinner_recipe_id = ri.recipe_id " +
                "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id " +
                "WHERE mr.mealplan_id = ? " +
                "ORDER BY i.ingredient_name ASC;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealplanId);
            while (results.next()) {
                Recipe_IngredientDto item = new Recipe_IngredientDto();
                item.setName(results.getString("ingredient_name"));
                item.setAmount(results.getDouble("amount"));
                item.setUnit(results.getString("unit_type"));
                groceryItems.add(item);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

//        update existing ingredients in list if ingredient name and unit type are the same (add together)
        // Process and update the user's grocery list
//        String insertSql = "INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type) " +
//                "VALUES (?, ?, ?, ?);";
//
//        try {
//            for (Recipe_IngredientDto item : groceryItems) {
//                // Insert each ingredient into the user's grocery list
//                jdbcTemplate.update(insertSql, userId, item.getName(), item.getAmount(), item.getUnit());
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }

        // if item exists and the item_name and unit_type match, add additional amount to existing amount
        String selectSql = "SELECT SUM(amount) FROM user_grocery_list " +
                "WHERE user_id = ? AND item_name = ? AND unit_type = ?";

        String updateSql = "UPDATE user_grocery_list SET amount = ? " +
                "WHERE user_id = ? AND item_name = ? AND unit_type = ?";

        String insertSql = "INSERT INTO user_grocery_list (user_id, item_name, amount, unit_type) " +
                "VALUES (?, ?, ?, ?)";

        try {
            for (Recipe_IngredientDto item : groceryItems) {
                Double existingAmount = null;
                try {
                    // Check if the item already exists in the user's grocery list and sum the amounts
                    existingAmount = jdbcTemplate.queryForObject(
                            selectSql,
                            new Object[]{userId, item.getName(), item.getUnit()},
                            Double.class
                    );
                } catch (EmptyResultDataAccessException e) {
                    // Ingredient doesn't exist, insert it
                    jdbcTemplate.update(insertSql, userId, item.getName(), item.getAmount(), item.getUnit());
                    continue;
                }

                if (existingAmount != null) {
                    // If the ingredient exists, update it with the new total amount
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
