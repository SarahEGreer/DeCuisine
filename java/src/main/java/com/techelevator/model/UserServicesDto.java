package com.techelevator.model;

import java.time.LocalDate;

public class UserServicesDto {
    private int mealplanId;
    private LocalDate startDate;

    public UserServicesDto(int mealplanId, LocalDate startDate) {
        this.mealplanId = mealplanId;
        this.startDate = startDate;
    }

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
