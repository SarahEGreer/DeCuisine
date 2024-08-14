package com.techelevator.controller;

import com.techelevator.dao.GroceryListDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.GroceryListDto;
import com.techelevator.model.Recipe_IngredientDto;
import com.techelevator.model.Recipe_detailDto;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/grocerylist")
@PreAuthorize("isAuthenticated()")
public class GroceryListController {
// Handles api calls for anything related to the user_grocery_list
    private final GroceryListDao groceryListDao;
    private final UserDao userDao;

    public GroceryListController(GroceryListDao groceryListDao, UserDao userDao) {
        this.groceryListDao = groceryListDao;
        this.userDao = userDao;
    }


    // View the users grocery list
    // Returns the values:
    //      String name;
    //      double amount;
    //      String unit;
    //      String systemOfMeasurement;
    @GetMapping
    public List<Recipe_IngredientDto> viewGroceryList(Principal principal) {
        try {
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            int userId = currentUser.getId();
            return groceryListDao.getGroceryListByUserId(userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }


    //references mealplan_recipe and recipe_ingredients to find ingredients via mealplan_id from mealplan
    //and adds them in the following format:
    //      String name;
    //      double amount;
    //      String unit;
    //      String systemOfMeasurement;
    @PostMapping("/mealplan/{mealplanId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMealPlanToGroceryList(@PathVariable int mealplanId, Principal principal) {
        try {
            String username = principal.getName();
            User currentUser = userDao.getUserByUsername(username);
            int userId = currentUser.getId();
            groceryListDao.addToGroceryListByMealPlan(mealplanId, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity violation", e);
        }
    }

    //gives user ability to add recipe ingredients directly from recipe to grocery list
    @PostMapping("/addIngredients/{recipeId}")
    public void addIngredientsToGroceryListByRecipeId(@PathVariable int recipeId, Principal principal) {
        try {
            // Get the current user based on the logged-in principal
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            int userId = currentUser.getId();

            // Call the DAO method to add ingredients to the grocery list
            groceryListDao.addIngredientsToGroceryListByRecipeId(recipeId, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity violation", e);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

    // Due do timing constraints we decided to override the users current this with a new list.
    // Deletes the users current  grocery list and adds in the new grocery list.
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGroceryList(@RequestBody List<Recipe_IngredientDto> groceryItems, Principal principal) {
        try {
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            int userId = currentUser.getId();
            groceryListDao.updateGroceryList(groceryItems, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity violation", e);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

}
