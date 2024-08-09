package com.techelevator.dao;

import com.techelevator.model.UserServicesDto;

public interface UserServicesDao {
    void trackMealplan(UserServicesDto userServicesDto, int userId);
    void untrackMealplan(int mealplanId, int userId);
}
