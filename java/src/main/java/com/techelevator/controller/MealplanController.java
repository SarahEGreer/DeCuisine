package com.techelevator.controller;


import com.techelevator.dao.MealplanDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Mealplan;
import com.techelevator.model.MealplanDto;
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
@RequestMapping("/mealplan")
@PreAuthorize("isAuthenticated()")

public class MealplanController {
    private final MealplanDao mealplanDao;
    private final UserDao userDao;
    //Handles api requests for the tables: mealplan, mealplan_recipe

    public MealplanController(MealplanDao mealplanDao, UserDao userDao) {
        this.mealplanDao = mealplanDao;
        this.userDao = userDao;
    }
    //Returns basic info for all mealplans in table mealplan:
    //    private int mealplanId;
    //    private String name;
    //    private String description;
    //    private int userId;
    @GetMapping
    public List<Mealplan> getAllMealplans() {
        List<Mealplan> mealplans = new ArrayList<>();
        try {
            mealplans = mealplanDao.getAllMealplans();
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
        return mealplans;
    }
    //Returns basic info for a mealplan in table mealplan:
    //    private int mealplanId;
    //    private String name;
    //    private String description;
    //    private int userId;
    @GetMapping( path = "/{mealplanId}")
    public Mealplan getMealplanByMealplanId(@PathVariable int mealplanId) {
        try{
            return mealplanDao.getMealplanByMealplanId(mealplanId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }
    //Returns all details for a mealplan based on its Id
    //References mealplan_recipe and mealplan
    //Formatted:
    //    private int mealplanId;
    //    private String name;
    //    private String description;
    //    private int userId;
    //    private List<MealplanScheduleDto> schedule;
    //      {
    //      private int day;
    //      private int breakfastId;
    //      private String breakfastName;
    //      private String breakfastPhotoUrl;
    //      private int lunchId;
    //      private String lunchName;
    //      private String lunchPhotoUrl;
    //      private int dinnerId;
    //      private String dinnerName;
    //      private String dinnerPhotoUrl;
    //      }
    @GetMapping( path = "/details/{mealplanId}")
    public MealplanDto getMealplanDetailsWithSchedule(@PathVariable int mealplanId) {
        try{
            return mealplanDao.getMealplanDetailsWithSchedule(mealplanId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }
    //create a new mealplan and adds recipe references in mealplan_recipe following the above format.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createMealplan(@RequestBody MealplanDto mealplanDto, Principal principal){
        try{
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            mealplanDao.createMealplan(mealplanDto, currentUser.getId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    // updates a mealplan in table mealplan and then deletes connections and adds new one in mealplan_recipe
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{mealplanId}")
    public  void updateMealplan(@RequestBody MealplanDto mealplanDto, @PathVariable int mealplanId, Principal principal) {
        try{
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            mealplanDao.updateMealplan(mealplanDto, mealplanId, currentUser.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity violation", e);
        }
    }
    //Deletes mealplan from mealplan and cascade deletes from mealplan_recipe
    @DeleteMapping("/{mealplanId}")
    public void deleteMealplan (@PathVariable int mealplanId, Principal principal) {
        try{
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            mealplanDao.deleteMealplan(mealplanId, currentUser.getId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity violation", e);
        }
    }
    // Returns all the mealplans a user had in their calendar. Created for testing.
    @GetMapping(path = "/tracked")
    public List<Mealplan> getMyTrackedMealplans(Principal principal) {
        try{
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            return mealplanDao.getMyTrackedMealplans(currentUser.getId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    // Returns all mealplans a user has created
    @GetMapping(path = "/created")
    public List<Mealplan> getMyCreatedMealplans(Principal principal) {
        try{
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            return mealplanDao.getMyCreatedMealplans(currentUser.getId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }








}
