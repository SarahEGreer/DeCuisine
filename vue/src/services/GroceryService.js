import axios from "axios";

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
});

// Set default Authorization header for all requests
http.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    //const token = store.getters.authToken; // Get the token from Vuex store
    console.log(`Token being used in request: ${token}`); // Log the token
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default {
  //backend has indicated that the grocery list is accessed by user token rather than needing to send user ID or GL id

  getGroceryList() {
    return http.get("/grocerylist");
  },

  updateGroceryList(groceryList) {
    //by userID?
    return http.put(`/grocerylist/`, groceryList);
  },

  addMealplanToGroceryList(mealplanId) {
    return http.post(`/grocerylist/mealplan/${mealplanId}`);
  },

  addRecipeToGroceryList(recipeId) {
    return http.post(`/grocerylist/addIngredients/${recipeId}`);
  },
  //^^^^^^^^^^^handles adding or removal of GL items via overwrite from front end information.
  //To add or delete items, items are removed or added to front end array,
  //and front end array overwrites the database array.

  // addIngredientsToGroceryListBasedOnMealPlanId(mealPlanId){
  //     return http.post(`/grocerylist/${mealPlanId}`)
  // },
};
