package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.GroceryListDto;
import com.techelevator.model.Recipe_IngredientDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class JdbcGroceryListDao implements GroceryListDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGroceryListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GroceryListDto getGroceryListByMealPlan(int mealplanId) {
        GroceryListDto groceryListDto = new GroceryListDto();
        List<Recipe_IngredientDto> groceryItems = new ArrayList<>();

        String mealplanSql = "SELECT mealplan_name FROM mealplan WHERE mealplan_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(mealplanSql, mealplanId);
            if (results.next()) {
                groceryListDto.setMealplanId(mealplanId);
                groceryListDto.setName(results.getString("mealplan_name"));

            } else {
                throw new DaoException("Mealplan with id " + mealplanId + " does not exist.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        String grocerySql = "SELECT i.ingredient_name, ri.amount, ri.unit_type\n" +
                "FROM mealplan_recipe mr\n" +
                "JOIN recipes_ingredients ri \n" +
                "ON mr.breakfast_recipe_id = ri.recipe_id\n" +
                "OR mr.lunch_recipe_id = ri.recipe_id\n" +
                "OR mr.dinner_recipe_id = ri.recipe_id\n" +
                "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id\n" +
                "WHERE mr.mealplan_id = ?" +
                "ORDER BY ingredient_name ASC;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(grocerySql, mealplanId);
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

        groceryListDto.setIngredients(groceryItems);
        return groceryListDto;
    }

}
