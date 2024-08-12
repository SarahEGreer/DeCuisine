package com.techelevator.dao;

import com.techelevator.model.TrackedMealplanDto;

import java.util.List;

public interface TrackedMealplanDao {

    List<TrackedMealplanDto> getTrackedMealPlans (int userId);

    void updateTrackedMealplans(TrackedMealplanDto mealplan);

    void deleteTrackedMealplan(int eventId);

    //    private TrackedMealplanDto mapRowToStartDate(SqlRowSet rs) {
    //        TrackedMealplanDto startDate = new TrackedMealplanDto();
    //        startDate.setEventId(rs.getInt("event_id"));
    //        startDate.setMealplanId(rs.getInt("mealplan_id"));
    //        startDate.setName(rs.getInt("mealplan_name"));
    //        startDate.setStartDate(rs.getString("start_date"));
    //        startDate.setDays(rs.getInt("days"));
    //        startDate.setUserId(rs.getInt("user_id"));
    //        return startDate;
    //    }
    //
    //    private TrackedMealplanDto mapRowToTrackedMealplans(SqlRowSet rs) {
    //        TrackedMealplanDto trackedMealplan = new TrackedMealplanDto();
    //        trackedMealplan.setEventId(rs.getInt("event_id"));
    //        trackedMealplan.setStartDate(rs.getString("start_date"));
    //        return trackedMealplan;
    //    }
    void createTrackedMealplan(TrackedMealplanDto mealplan, int userId);
}
