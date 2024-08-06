package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeDao recipeDao;

    public RecipeController(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = null;
        try{
            recipes = recipeDao.getAllRecipes();
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
        return recipes;
    }








}
