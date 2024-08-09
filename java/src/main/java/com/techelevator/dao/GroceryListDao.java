package com.techelevator.dao;

import com.techelevator.model.GroceryListDto;

public interface GroceryListDao {

    GroceryListDto getGroceryListByMealPlan(int mealplanId);


}
