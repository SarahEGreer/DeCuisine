package com.techelevator.model;

public class MealplanScheduleDto {


    private int day;
    private int breakfastId;
    private String breakfastName;
    private int lunchId;
    private String lunchName;
    private int dinnerId;
    private String dinnerName;

    public MealplanScheduleDto() {}

    public MealplanScheduleDto(int day, int breakfastId, String breakfastName, int lunchId, String lunchName, int dinnerId, String dinnerName) {
        this.day = day;
        this.breakfastId = breakfastId;
        this.breakfastName = breakfastName;
        this.lunchId = lunchId;
        this.lunchName = lunchName;
        this.dinnerId = dinnerId;
        this.dinnerName = dinnerName;
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

    public String getBreakfastName() {
        return breakfastName;
    }

    public void setBreakfastName(String breakfastName) {
        this.breakfastName = breakfastName;
    }

    public String getLunchName() {
        return lunchName;
    }

    public void setLunchName(String lunchName) {
        this.lunchName = lunchName;
    }

    public String getDinnerName() {
        return dinnerName;
    }

    public void setDinnerName(String dinnerName) {
        this.dinnerName = dinnerName;
    }

}
