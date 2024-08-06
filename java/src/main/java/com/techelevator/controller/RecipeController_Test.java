package com.techelevator.controller;

import com.techelevator.dao.Recipe_Dao_Test;
import com.techelevator.model.Recipe_DetailDto_Test;
import com.techelevator.model.Recipe_detailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/recipes")
public class RecipeController_Test {

    private final Recipe_Dao_Test recipeDao;


    public RecipeController_Test(Recipe_Dao_Test recipeDao) {
        this.recipeDao = recipeDao;
    }

    @GetMapping(path = "/{recipeId}/detailsTest")
    public Recipe_DetailDto_Test getRecipeDetails(@PathVariable int recipeId){
        try{
            return recipeDao.getRecipeDetailsByRecipeIdTest(recipeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }
}
