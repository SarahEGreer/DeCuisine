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



//    @Override
//    public List<Recipe_IngredientDto> getGroceryListByMealPlan(int mealplanId, int userId) {
//
//        String userCheckSql = "SELECT created_by_user_id FROM mealplan WHERE mealplan_id = ?";
//        Integer createdUserId = jdbcTemplate.queryForObject(userCheckSql, Integer.class, mealplanId);
//
//        if (!createdUserId.equals(userId)) {
//            throw new DaoException("User is not authorized to view this meal plan.");
//        }
//
//
//        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();
//
//        String sql = "SELECT i.ingredient_name, ri.amount, ri.unit_type\n" +
//                "FROM mealplan_recipe mr \n" +
//                "JOIN recipes_ingredients ri\n" +
//                "ON mr.breakfast_recipe_id = ri.recipe_id\n" +
//                "OR mr.lunch_recipe_id = ri.recipe_id\n" +
//                "OR mr.dinner_recipe_id = ri.recipe_id\n" +
//                "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id\n" +
//                "WHERE mr.mealplan_id = 100\n" +
//                "ORDER BY i.ingredient_name ASC;";
//
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealplanId);
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
//        return groceryItems;
//    }


    @Override
    public List<Recipe_IngredientDto> getGroceryListByUserId(int userId) {
        String sql = "SELECT i.ingredient_name, ug.amount, ug.unit_type " +
                "FROM user_grocery_list ug " +
                "JOIN ingredients i ON ug.ingredient_id = i.ingredient_id " +
                "WHERE ug.user_id = ? " +
                "ORDER BY i.ingredient_name ASC;";

        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
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

        return groceryItems;
    }


    @Override
    public void updateGroceryList(List<Recipe_IngredientDto> groceryItems, int userId) {
        // deleting existing ingredients for grocery list
        String deleteSql = "DELETE FROM user_grocery_list WHERE user_id = ?";

        try {
            jdbcTemplate.update(deleteSql, userId);

            // inserting the new ingredient list for grocery list
            String insertSql = "INSERT INTO user_grocery_list (user_id, ingredient_id, amount, unit_type) " +
                    "VALUES (?, (SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?), ?, ?)";

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
