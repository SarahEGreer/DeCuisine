package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Mealplan;
import com.techelevator.model.MealplanDto;
import com.techelevator.model.MealplanRecipe;
import com.techelevator.model.MealplanScheduleDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealplanDao implements MealplanDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMealplanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mealplan> getAllMealplans() {
        List<Mealplan> mealplan = new ArrayList<>();
        String sql = "SELECT * FROM mealplan;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Mealplan meals = mapRowToMealplan(results);
                mealplan.add(meals);
            }
         } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return mealplan;
    }

    @Override
    public Mealplan getMealplanByMealplanId(int mealplanId) {
        Mealplan mealplans = null;
        String sql = "SELECT * FROM mealplan WHERE mealplan_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealplanId);
            while (results.next()) {
                mealplans = mapRowToMealplan(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return mealplans;
    }

    ;
    @Override
    public MealplanDto getMealplanDetailsWithSchedule(int mealplanId) {
        MealplanDto mealplanDto = null;
        List<MealplanScheduleDto> schedule = new ArrayList<>();

        String msql = "SELECT * FROM mealplan WHERE mealplan_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(msql, mealplanId);
            while (results.next()) {
                mealplanDto = mapRowToMealplanDto(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        String mpsql =  "SELECT mealplan_day_count, breakfast_recipe_id, lunch_recipe_id, dinner_recipe_id" +
                "FROM mealplan_recipe WHERE mealplan_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(mpsql, mealplanId);
            while(results.next()) {
                MealplanScheduleDto mealplanScheduleDto = new MealplanScheduleDto();
                mealplanScheduleDto.setDay(results.getInt("day"));
                mealplanScheduleDto.setBreakfastId(results.getInt("breakfastId"));
                mealplanScheduleDto.setLunchId(results.getInt("lunchId"));
                mealplanScheduleDto.setDinnerId(results.getInt("dinnerId"));
                schedule.add(mealplanScheduleDto);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        //checking to see if mealplandto object is not null, and if not then it sets the schedule
        if (mealplanDto != null) {
            mealplanDto.setSchedule(schedule);
        }
        return mealplanDto;
    }

    @Override
    public void createMealplan (MealplanDto mealplanDto, int userId) {
        int newMealplanId = 0;
        String mealplanSql = "INSERT INTO mealplan (mealplan_name, mealplan_description, created_by_user_id) VALUES (?,?,?) RETURNING mealplan_id;";
        try {
            newMealplanId = jdbcTemplate.queryForObject(mealplanSql, int.class, userId,
                    mealplanDto.getName(), mealplanDto.getDescription());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        List<MealplanScheduleDto> schedule = mealplanDto.getSchedule();

        //for each day in the schedule within the dto, do the following try/catch for it
        for (MealplanScheduleDto day : schedule) {
            try {
                String mealplanRecipeSql = "INSERT INTO mealplan_recipe (mealplan_id, mealplan_day_count, breakfast_recipe_id, lunch_recipe_id, dinner_recipe_id) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(mealplanRecipeSql, newMealplanId, day.getDay(), day.getBreakfastId(), day.getLunchId(), day.getDinnerId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        }
    }












//
//    void updateMealplan (MealplanDto mealplanDto, int userId);
//
//    void deleteMealplan (int mealplanId);

    private Mealplan mapRowToMealplan(SqlRowSet rs) {
        Mealplan mealplan = new Mealplan();
        mealplan.setMealplanId(rs.getInt("mealplan_id"));
        mealplan.setName(rs.getString("mealplan_name"));
        mealplan.setDescription(rs.getString("mealplan_description"));
        mealplan.setUserId(rs.getInt("created_by_user_id"));
        return mealplan;
    }

    private MealplanRecipe mapRowToMealplanRecipe(SqlRowSet rs) {
        MealplanRecipe mealplanRecipe = new MealplanRecipe();
        mealplanRecipe.setMealplanId(rs.getInt("mealplan_id"));
        mealplanRecipe.setDayCount(rs.getInt("mealplan_day_count"));
        mealplanRecipe.setBreakfastId(rs.getInt("breakfast_recipe_id"));
        mealplanRecipe.setLunchId(rs.getInt("lunch_recipe_id"));
        mealplanRecipe.setDinnerId(rs.getInt("dinner_recipe_id"));
        return mealplanRecipe;
    }

    private MealplanDto mapRowToMealplanDto(SqlRowSet rs) {
        MealplanDto mealplanDto = new MealplanDto();
        mealplanDto.setName(rs.getString("mealplan_name"));
        mealplanDto.setDescription(rs.getString("mealplan_description"));
        mealplanDto.setUserId(rs.getInt("created_by_user_id"));

        return mealplanDto;
    }



}
