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

    private final GroceryListDao groceryListDao;
    private final UserDao userDao;

    public GroceryListController(GroceryListDao groceryListDao, UserDao userDao) {
        this.groceryListDao = groceryListDao;
        this.userDao = userDao;
    }


    // View the grocery list
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


    //update grocery list based on mealplan
    @PostMapping







//    @GetMapping
//    public List<Recipe_IngredientDto> getGroceryListByMealPlan(Principal principal) {
//        try {
//            String userName = principal.getName();
//            User currentUser = userDao.getUserByUsername(userName);
//            int userId = currentUser.getId();
//            return groceryListDao.getGroceryListByUserId(userId);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }

    // Update the grocery list based on ingredients
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
