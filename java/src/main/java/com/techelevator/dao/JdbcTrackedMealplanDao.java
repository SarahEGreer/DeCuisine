package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.TrackedMealplanDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTrackedMealplanDao implements TrackedMealplanDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTrackedMealplanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TrackedMealplanDto> getTrackedMealPlans (int userId){
        List<TrackedMealplanDto> trackedMealplans = new ArrayList<>();
        String sql  = "SELECT utm.event_id, utm.mealplan_id, m.mealplan_name, utm.start_date, utm.user_id\n" +
                "(SELECT COUNT(*) FROM mealplan_recipe mr WHERE mr.mealplan_id = utm.mealplan_id) AS days\n" +
                "FROM user_tracked_mealplan utm\n" +
                "JOIN mealplan m ON utm.mealplan_id = m.mealplan_id\n" +
                "WHERE utm.user_id = ?\n" +
                "ORDER BY m.mealplan_name ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                TrackedMealplanDto mealplan = new TrackedMealplanDto();
                mealplan.setEventId(results.getInt("event_id"));
                mealplan.setMealplanId(results.getInt("mealplan_id"));
                mealplan.setName(results.getString("mealplan_name"));
                mealplan.setStartDate(results.getString("start_date"));
                mealplan.setUserId(results.getInt("user_id"));
                mealplan.setDays(results.getInt("days"));
                trackedMealplans.add(mealplan);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return trackedMealplans;
    }

    @Override
    public void updateTrackedMealplans(TrackedMealplanDto mealplan){
            String sql = "UPDATE user_tracked_mealplan SET start_date = ? WHERE event_id = ?";

            try {
                jdbcTemplate.update(sql, mealplan.getStartDate(), mealplan.getEventId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        }


    @Override
    public void deleteTrackedMealplan(int eventId){
        String sql = "DELETE FROM user_tracked_mealplan WHERE event_id = ?";
        try {
            jdbcTemplate.update(sql, eventId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }


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


}
