package com.techelevator.dao;

import com.techelevator.model.TrackedMealplanDto;

import java.util.List;

public interface TrackedMealplanDao {

    List<TrackedMealplanDto> getTrackedMealPlans (int userId);

    void updateTrackedMealplans(TrackedMealplanDto mealplan);

    void deleteTrackedMealplan(int eventId);

}
