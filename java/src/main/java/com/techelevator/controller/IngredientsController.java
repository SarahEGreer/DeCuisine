package com.techelevator.controller;

import com.techelevator.dao.IngredientsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Recipe_Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/ingredients")
@PreAuthorize("isAuthenticated()")

public class IngredientsController {

    private final IngredientsDao ingredientsDao;

    public IngredientsController(IngredientsDao ingredientsDao) {
        this.ingredientsDao = ingredientsDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getAllIngredients() {
        try {
            return ingredientsDao.getAllIngredients();
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }

    @RequestMapping(path = "/{recipeId}", method = RequestMethod.GET)
    public ResponseEntity<List<Recipe_Ingredients>> getIngredientsByRecipe(@PathVariable int recipeId) {
        List<Recipe_Ingredients> ingredientsByRecipe = null;
        try {
            ingredientsByRecipe = ingredientsDao.getIngredientsByRecipe(recipeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
        return new ResponseEntity<>(ingredientsByRecipe, HttpStatus.OK);
    }


//    @RequestMapping(path = "/create", method = RequestMethod.POST)
//    public ResponseEntity<Recipe_Ingredients> createIngredientByRecipe(@Valid @RequestBody Recipe_Ingredients recipeIngredients) {
//        if (recipeIngredients == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingredient is null.");
//        }
//
//        Recipe_Ingredients createdIngredient;
//        try {
//            createdIngredient = ingredientsDao.createIngredientByRecipe(recipeIngredients);
//        } catch (DaoException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ingredient creation failed.", e);
//        }
//        return new ResponseEntity<>(createdIngredient, HttpStatus.CREATED);
//    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createIngredientsForIngredientTable(@Valid @RequestBody List<Recipe_Ingredients> recipeIngredientsList) {
        if (recipeIngredientsList == null || recipeIngredientsList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingredient list is null or empty.");
        }

        try {
            ingredientsDao.createIngredientsForIngredientTable(recipeIngredientsList);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ingredient creation failed.", e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }





}
