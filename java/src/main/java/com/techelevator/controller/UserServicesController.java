package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserServicesDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.User;
import com.techelevator.model.UserServicesDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/userservices")
@PreAuthorize("isAuthenticated()")

public class UserServicesController {
    private final UserServicesDao userServicesDao;
    private final UserDao userDao;

    public  UserServicesController(UserServicesDao userServicesDao, UserDao userDao) {
        this.userServicesDao = userServicesDao;
        this.userDao = userDao;
    }

    @PostMapping
    public void trackMealplan (@RequestBody UserServicesDto userServicesDto, Principal principal) {
        try {
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            userServicesDao.trackMealplan(userServicesDto, currentUser.getId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
    @DeleteMapping(path = "/{mealplanId}")
    public void untrackMealplan (@PathVariable int mealplanId, Principal principal) {
        try {
            String userName = principal.getName();
            User currentUser = userDao.getUserByUsername(userName);
            userServicesDao.untrackMealplan(mealplanId, currentUser.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }






}
