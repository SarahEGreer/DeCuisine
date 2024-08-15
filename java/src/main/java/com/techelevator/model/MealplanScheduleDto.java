package com.techelevator.model;

public class MealplanScheduleDto {


    private int day;
    private Integer breakfastId;
    private String breakfastName;
    private String breakfastPhotoUrl;
    private Integer lunchId;
    private String lunchName;
    private String lunchPhotoUrl;
    private Integer dinnerId;
    private String dinnerName;
    private String dinnerPhotoUrl;

    public MealplanScheduleDto() {}

    public MealplanScheduleDto(int day, Integer breakfastId, String breakfastName, String breakfastPhotoUrl, Integer lunchId, String lunchName, String lunchPhotoUrl, Integer dinnerId, String dinnerName, String dinnerPhotoUrl) {
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

    public Integer getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(Integer breakfastId) {
        this.breakfastId = breakfastId;
    }

    public Integer getLunchId() {
        return lunchId;
    }

    public void setLunchId(Integer lunchId) {
        this.lunchId = lunchId;
    }

    public Integer getDinnerId() {
        return dinnerId;
    }

    public void setDinnerId(Integer dinnerId) {
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
