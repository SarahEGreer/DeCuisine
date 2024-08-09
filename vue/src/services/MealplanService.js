import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

// Set default Authorization header for all requests
http.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    //const token = store.getters.authToken; // Get the token from Vuex store
    console.log(`Token being used in request: ${token}`); // Log the token
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  }, error => {
    return Promise.reject(error);
  });

export default {

    getMealplans(){
        return http.get('/mealplan')
    },

    getMealplanBasicsById(mealplanId){
        return http.get(`/mealplan/${mealplanId}`)
    },

    getMealplanDetailsById(mealplanId){
        return http.get(`/mealplan/details/${mealplanId}`)
    },

    submitMealplan(mealplan){
        return http.post('/mealplan', mealplan)
    }, 

    updateMealplan(mealplanId, mealplan){
        return http.put(`/mealplan/${mealplanId}`, mealplan)
    }, 

    deleteMealplan(mealplanId){
        return http.delete(`/mealplan/${mealplanId}`)
    }

}