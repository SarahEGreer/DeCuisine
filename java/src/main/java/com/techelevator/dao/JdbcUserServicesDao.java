package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.UserServicesDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserServicesDao implements UserServicesDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcUserServicesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void trackMealplan(UserServicesDto userServicesDto, int userId) {
        String sql = "INSERT INTO user_tracked_mealplan (mealplan_id ,start_date, user_id) VALUES ( ?,?,? );";
        try {
            jdbcTemplate.update(sql, userServicesDto.getMealplanId(), userServicesDto.getStartDate(), userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }
    @Override
    public void untrackMealplan(int mealplanId, int userId) {
        String sql = "DELETE FROM user_tracked_mealplan WHERE mealplan_id = ? AND user_id = ? ;";
        try{
            jdbcTemplate.update(sql, mealplanId, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }
}
