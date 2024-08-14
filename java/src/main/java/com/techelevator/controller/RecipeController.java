package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/recipes")
@PreAuthorize("isAuthenticated()")
public class RecipeController {
    private final RecipeDao recipeDao;
    private final UserDao userDao;

    public RecipeController(RecipeDao recipeDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;

    }

    @GetMapping
    public List<RecipeSummary> getAllRecipes() {
        List<RecipeSummary> recipes = null;
        try{
            recipes = recipeDao.getAllRecipes();
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
        return recipes;
    }

    @GetMapping(path = "/{recipeId}")
    public RecipeSummary getRecipeSummaryByRecipeId(@PathVariable int recipeId) {
        try {
            return recipeDao.getRecipeSummaryByRecipeId(recipeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }

    @GetMapping(path = "/details/{recipeId}")
    public Recipe_detailDto getRecipeDetails(@PathVariable int recipeId){
        try{
            return recipeDao.getRecipeDetailsByRecipeId(recipeId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createRecipes(@RequestBody RecipeDto recipeDto, Principal principal){

        try{
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            recipeDao.createRecipe(recipeDto, currentUser.getId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{recipeId}")
    public void updateRecipe(@RequestBody RecipeDto recipeDto, @PathVariable int recipeId, Principal principal) {
        try {
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            recipeDao.updateRecipe(recipeDto, recipeId, currentUser.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity violation", e);
        }
    }


}
