package com.techelevator.dao;

import com.techelevator.model.Mealplan;
import com.techelevator.model.MealplanDto;

import java.util.List;

public interface MealplanDao {

    List<Mealplan> getMyTrackedMealplans(int userId);

    List<Mealplan> getMyCreatedMealplans(int userId);

    List<Mealplan> getAllMealplans();

    Mealplan getMealplanByMealplanId(int mealplanId);


    MealplanDto getMealplanDetailsWithSchedule(int mealplanId);


    void createMealplan (MealplanDto mealplanDto, int userId);

    void updateMealplan (MealplanDto mealplanDto, int mealplanId, int userId);

    void deleteMealplan (int mealplanId, int userId);



}
