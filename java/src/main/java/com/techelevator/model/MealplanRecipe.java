package com.techelevator.model;

public class MealplanRecipe {

    private int mealplanId;
    private int dayCount;
    private int breakfastId;
    private int lunchId;
    private int dinnerId;
    public MealplanRecipe() {}

    public MealplanRecipe(int mealplanId, int dayCount, int breakfastId, int lunchId, int dinnerId) {
        this.mealplanId = mealplanId;
        this.dayCount = dayCount;
        this.breakfastId = breakfastId;
        this.lunchId = lunchId;
        this.dinnerId = dinnerId;
    }

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(int breakfastId) {
        this.breakfastId = breakfastId;
    }

    public int getLunchId() {
        return lunchId;
    }

    public void setLunchId(int lunchId) {
        this.lunchId = lunchId;
    }

    public int getDinnerId() {
        return dinnerId;
    }

    public void setDinnerId(int dinnerId) {
        this.dinnerId = dinnerId;
    }
}
