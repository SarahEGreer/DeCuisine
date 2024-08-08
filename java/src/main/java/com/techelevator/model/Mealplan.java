package com.techelevator.model;

public class Mealplan {

    private int mealplanId;
    private String name;
    private String description;
    private int userId;
    public Mealplan() {}

    public Mealplan(int mealplanId, String name, String description, int userId) {
        this.mealplanId = mealplanId;
        this.name = name;
        this.description = description;
        this.userId = userId;
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
}
