package com.techelevator.model;

public class UserServicesDto {
    private int mealplanId;
    private int startDate;

    public UserServicesDto(int mealplanId, int startDate) {
        this.mealplanId = mealplanId;
        this.startDate = startDate;
    }

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
}
