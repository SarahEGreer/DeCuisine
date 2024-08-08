package com.techelevator.model;

public class MealplanScheduleDto {


    private int day;
    private int breakfastId;
    private int lunchId;
    private int dinnerId;

    public MealplanScheduleDto() {}

    public MealplanScheduleDto(int day, int breakfastId, int lunchId, int dinnerId) {
        this.day = day;
        this.breakfastId = breakfastId;
        this.lunchId = lunchId;
        this.dinnerId = dinnerId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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
