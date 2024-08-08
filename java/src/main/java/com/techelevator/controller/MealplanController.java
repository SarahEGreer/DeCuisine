package com.techelevator.controller;


import com.techelevator.dao.MealplanDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/mealplans")
@PreAuthorize("isAuthenticated()")

public class MealplanController {
    private final MealplanDao mealplanDao;

    public MealplanController(MealplanDao mealplanDao) {
        this.mealplanDao = mealplanDao;
    }


}
