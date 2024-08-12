package com.techelevator.controller;

import com.techelevator.dao.GroceryListDao;
import com.techelevator.model.GroceryListDto;
import com.techelevator.model.Recipe_IngredientDto;
import com.techelevator.model.Recipe_detailDto;
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

    public GroceryListController(GroceryListDao groceryListDao) {
        this.groceryListDao = groceryListDao;
    }

//    @GetMapping (path = "/{mealplanId}")
//    public GroceryListDto getGroceryListByMealPlan(@PathVariable int mealplanId) {
//        try {
//            return groceryListDao.getGroceryListByMealPlan(mealplanId);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }

}
