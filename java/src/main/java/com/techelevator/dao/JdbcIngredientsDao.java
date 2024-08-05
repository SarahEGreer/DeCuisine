package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredients;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcIngredientsDao implements IngredientsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }







}
