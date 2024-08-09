package com.techelevator.controller;

import com.techelevator.dao.GroceryListDao;
import com.techelevator.model.GroceryListDto;
import com.techelevator.model.Recipe_IngredientDto;
import com.techelevator.model.Recipe_detailDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/grocerylist")
@PreAuthorize("isAuthenticated")
public class GroceryListController {

    private final GroceryListDao groceryListDao;

    public GroceryListController(GroceryListDao groceryListDao) {
        this.groceryListDao = groceryListDao;
    }

    @GetMapping
    public List<GroceryListDto> getGroceryList() {
        List<GroceryListDto> groceryList = new ArrayList<>();
        try {

            return groceryListDao.getGroceryListByMealPlan();
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }

}
