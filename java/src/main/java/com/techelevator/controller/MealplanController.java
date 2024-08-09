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

    public MealplanController(MealplanDao mealplanDao, UserDao userDao) {
        this.mealplanDao = mealplanDao;
        this.userDao = userDao;
    }
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
    @GetMapping( path = "/{mealplanId}")
    public Mealplan getMealplanByMealplanId(@PathVariable int mealplanId) {
        try{
            return mealplanDao.getMealplanByMealplanId(mealplanId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }

    @GetMapping( path = "/details/{mealplanId}")
    public MealplanDto getMealplanDetailsWithSchedule(@PathVariable int mealplanId) {
        try{
            return mealplanDao.getMealplanDetailsWithSchedule(mealplanId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }
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
