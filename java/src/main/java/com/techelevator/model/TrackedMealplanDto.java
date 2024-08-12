package com.techelevator.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
    public class TrackedMealplanDto {

        private int eventId;
        private int mealplanId;
        private String name;
        private LocalDate startDate;
        private int days;
        private int userId;

        public TrackedMealplanDto() {}

    public TrackedMealplanDto(int eventId, int mealplanId, String name, LocalDate startDate, int days, int userId) {
        this.eventId = eventId;
        this.mealplanId = mealplanId;
        this.name = name;
        this.startDate = startDate;
        this.days = days;
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
