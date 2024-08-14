package com.techelevator.controller;

import com.techelevator.dao.TrackedMealplanDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.TrackedMealplanDto;
import com.techelevator.model.User;
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
@RequestMapping("/tracked")
@PreAuthorize("isAuthenticated()")
public class TrackedMealplanController {
    private final UserDao userDao;
    private final TrackedMealplanDao trackedMealplanDao;

    public TrackedMealplanController(TrackedMealplanDao trackedMealplanDao, UserDao userDao) {
        this.trackedMealplanDao = trackedMealplanDao;
        this.userDao = userDao;
    }

    @GetMapping
    public List<TrackedMealplanDto> getTrackedMealPlans(Principal principal) {
        String userName = principal.getName();
        User currentUser = userDao.getUserByUsername(userName);
        try {
            return trackedMealplanDao.getTrackedMealPlans(currentUser.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }

    @PostMapping
    public void createTrackedMealplan(@RequestBody TrackedMealplanDto trackedMealplanDto, Principal principal) {
        String userName = principal.getName();
        User currentUser = userDao.getUserByUsername(userName);
        try {
            trackedMealplanDao.createTrackedMealplan(trackedMealplanDto, currentUser.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }

    @PutMapping
    public void updateTrackedMealPlan(@RequestBody TrackedMealplanDto trackedMealplanDto, Principal principal) {
        String userName = principal.getName();
        User currentUser = userDao.getUserByUsername(userName);
        try {
            trackedMealplanDao.updateTrackedMealplans(trackedMealplanDto);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        }
    }

    @DeleteMapping(path = "/{eventId}")
    public void deleteTrackedMealplan(@PathVariable int eventId) {
        try {
            trackedMealplanDao.deleteTrackedMealplan(eventId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data integrity violation", e);
        }
    }
}