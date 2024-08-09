package com.techelevator.model;

import java.util.List;

public class MealplanDto {


    private int mealplanId;
    private String name;
    private String description;
    private int userId;


    //list of mealplan recipes
    private List<MealplanScheduleDto> schedule;

    public MealplanDto() {}

    public MealplanDto(int mealplanId, String name, String description, int userId, List<MealplanScheduleDto> schedule) {
        this.mealplanId = mealplanId;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.schedule = schedule;
    }

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<MealplanScheduleDto> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<MealplanScheduleDto> schedule) {
        this.schedule = schedule;
    }
}
