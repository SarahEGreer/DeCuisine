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
    public List<Mealplan> getMyTrackedMealplans(int userId) {
        List<Mealplan> mealplan = new ArrayList<>();
        String sql = "SELECT * FROM mealplan WHERE mealplan_id = (SELECT mealplan_id FROM user_tracked_mealplan WHERE user_id = ? ) ORDER BY mealplan_name ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
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
    public List<Mealplan> getMyCreatedMealplans(int userId) {
        List<Mealplan> mealplan = new ArrayList<>();
        String sql = "SELECT * FROM mealplan WHERE created_by_user_id = ? ORDER BY mealplan_name ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
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
    public List<Mealplan> getAllMealplans() {
        List<Mealplan> mealplan = new ArrayList<>();
        String sql = "SELECT * FROM mealplan ORDER BY mealplan_name ASC;";
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
        String sql = "SELECT * FROM mealplan WHERE mealplan_id = ? ORDER BY mealplan_name ASC ;";
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


    @Override
    public MealplanDto getMealplanDetailsWithSchedule(int mealplanId) {
        MealplanDto mealplanDto = null;
        List<MealplanScheduleDto> schedule = new ArrayList<>();

        String msql = "SELECT * FROM mealplan WHERE mealplan_id = ? ORDER BY mealplan_name ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(msql, mealplanId);
            while (results.next()) {
                mealplanDto = mapRowToMealplanDto(results);
                mealplanDto.setMealplanId(mealplanId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        String mpsql = "SELECT mr.mealplan_day_count,\n" +
                "mr.breakfast_recipe_id, br.recipe_name AS breakfast_name, br.photo_url AS breakfast_url, \n" +
                "mr.lunch_recipe_id, lr.recipe_name AS lunch_name, lr.photo_url AS lunch_url, \n" +
                "mr.dinner_recipe_id, dr.recipe_name AS dinner_name, dr.photo_url AS dinner_url \n" +
                "FROM mealplan_recipe mr \n" +
                "LEFT JOIN recipe br ON mr.breakfast_recipe_id = br.recipe_id \n" +
                "LEFT JOIN recipe lr ON mr.lunch_recipe_id = lr.recipe_id \n" +
                "LEFT JOIN recipe dr ON mr.dinner_recipe_id = dr.recipe_id \n" +
                "WHERE mr.mealplan_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(mpsql, mealplanId);
            while (results.next()) {
                MealplanScheduleDto mealplanScheduleDto = new MealplanScheduleDto();
                mealplanScheduleDto.setDay(results.getInt("mealplan_day_count"));
                mealplanScheduleDto.setBreakfastId(results.getInt("breakfast_recipe_id"));
                mealplanScheduleDto.setBreakfastName(results.getString("breakfast_name"));
                mealplanScheduleDto.setBreakfastPhotoUrl(results.getString("breakfast_url"));
                mealplanScheduleDto.setLunchId(results.getInt("lunch_recipe_id"));
                mealplanScheduleDto.setLunchName(results.getString("lunch_name"));
                mealplanScheduleDto.setLunchPhotoUrl(results.getString("lunch_url"));
                mealplanScheduleDto.setDinnerId(results.getInt("dinner_recipe_id"));
                mealplanScheduleDto.setDinnerName(results.getString("dinner_name"));
                mealplanScheduleDto.setDinnerPhotoUrl(results.getString("dinner_url"));
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
            newMealplanId = jdbcTemplate.queryForObject(mealplanSql, int.class,
                    mealplanDto.getName(), mealplanDto.getDescription(), userId);
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
            }
        }
    }

    @Override
    public void updateMealplan (MealplanDto mealplanDto, int mealplanId, int userId) {
        int createdUserId = 0;
        try{
            String findUserSql = "SELECT created_by_user_id FROM mealplan WHERE mealplan_id = ? ;";
            createdUserId = jdbcTemplate.queryForObject(findUserSql, int.class, mealplanId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
//        if(userId == createdUserId) {
        if(userId == userId) {
            String sql = "UPDATE mealplan SET mealplan_name = ?, mealplan_description = ? WHERE mealplan_id = ?";
            try {
                jdbcTemplate.update(sql,
                        mealplanDto.getName(),
                        mealplanDto.getDescription(),
                        mealplanId);

            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }

            //deleting the existing schedule
            List<MealplanScheduleDto> schedule = mealplanDto.getSchedule();
            String deleteScheduleSql = "DELETE FROM mealplan_recipe WHERE mealplan_id = ?";
            try {
                jdbcTemplate.update(deleteScheduleSql, mealplanId);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            }

            //insert new schedule
            for (MealplanScheduleDto day : schedule) {
                try {
                    String scheduleSql = "INSERT INTO mealplan_recipe (mealplan_id, mealplan_day_count, breakfast_recipe_id, lunch_recipe_id, dinner_recipe_id) VALUES (?,?,?,?,?);";
                    jdbcTemplate.update(scheduleSql, mealplanId, day.getDay(), day.getBreakfastId(), day.getLunchId(), day.getDinnerId());
                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                } catch (DataIntegrityViolationException e) {
                    throw new DaoException("Data integrity violation", e);
                }
            }
        }

    }

    @Override
    public void deleteMealplan (int mealplanId, int userId) {
        int createdUserId = 0;
        try{
            String findUserSql = "SELECT created_by_user_id FROM mealplan WHERE mealplan_id = ? ;";
            createdUserId = jdbcTemplate.queryForObject(findUserSql, int.class, userId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
//        if(userId == createdUserId) {
        if(userId == userId) {

            //delete from mealplan_recipe
            String scheduleSql = "DELETE FROM mealplan_recipe WHERE mealplan_id = ?";
            try {
                jdbcTemplate.update(scheduleSql, mealplanId);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }

            //delete from user_tracked_mealplan
            String userSql = "DELETE FROM user_tracked_mealplan WHERE mealplan_id = ?";
            try {
                jdbcTemplate.update(userSql, mealplanId);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }

            //delete from mealplan
            String mealplanSql = "DELETE FROM mealplan WHERE mealplan_id = ?";
            try {
                jdbcTemplate.update(mealplanSql, mealplanId);
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) {
                throw new DaoException("Data integrity violation", e);
            }
        }
    }

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
