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

  // Method to fetch all meal plans (used in the sidebar)
  getMealplans() {
    return axios.get(`/mealplan/created`); // gives user created mealplans
  },

  getMealplanDetailsById(mealplanId) {
    return http.get(`/mealplan/details/${mealplanId}`)
  },

  submitMealplan(mealplan) {
    return http.post('/mealplan', mealplan)
  },

  updateMealplan(mealplanId, mealplan) {
    return http.put(`/mealplan/${mealplanId}`, mealplan)
  },

  deleteMealplan(mealplanId) {
    return http.delete(`/mealplan/${mealplanId}`)
  },

  //EVENTS BELOW

  createMealplanEvent(newEvent) {
    return axios.post(`/tracked`, newEvent);
  },

  // Method to update an existing meal plan event's start date

  updateMealplanEvent(updatedEvent) {
    return axios.put(`http://localhost:9000/tracked`, updatedEvent);
  },

  // Method to delete an existing meal plan event
  deleteMealplanEvent(eventId) {
    return axios.delete(`/tracked/${eventId}`);
  },

  // Method to fetch tracked meal plan events to populate the calendar (those with start dates)
  getTrackedMealplanEvents() {
    return axios.get(`/tracked`);
  },


};

