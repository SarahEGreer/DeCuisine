package com.techelevator.model;

public class MealplanScheduleDto {


    private int day;
    private int breakfastId;
    private String breakfastName;
    private String breakfastPhotoUrl;
    private int lunchId;
    private String lunchName;
    private String lunchPhotoUrl;
    private int dinnerId;
    private String dinnerName;
    private String dinnerPhotoUrl;

    public MealplanScheduleDto() {}

    public MealplanScheduleDto(int day, int breakfastId, String breakfastName, String breakfastPhotoUrl, int lunchId, String lunchName, String lunchPhotoUrl, int dinnerId, String dinnerName, String dinnerPhotoUrl) {
        this.day = day;
        this.breakfastId = breakfastId;
        this.breakfastName = breakfastName;
        this.breakfastPhotoUrl = breakfastPhotoUrl;
        this.lunchId = lunchId;
        this.lunchName = lunchName;
        this.lunchPhotoUrl = lunchPhotoUrl;
        this.dinnerId = dinnerId;
        this.dinnerName = dinnerName;
        this.dinnerPhotoUrl = dinnerPhotoUrl;
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

    public String getBreakfastPhotoUrl() {
        return breakfastPhotoUrl;
    }

    public void setBreakfastPhotoUrl(String breakfastPhotoUrl) {
        this.breakfastPhotoUrl = breakfastPhotoUrl;
    }

    public String getLunchPhotoUrl() {
        return lunchPhotoUrl;
    }

    public void setLunchPhotoUrl(String lunchPhotoUrl) {
        this.lunchPhotoUrl = lunchPhotoUrl;
    }

    public String getDinnerPhotoUrl() {
        return dinnerPhotoUrl;
    }

    public void setDinnerPhotoUrl(String dinnerPhotoUrl) {
        this.dinnerPhotoUrl = dinnerPhotoUrl;
    }
}
