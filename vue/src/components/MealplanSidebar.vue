<template>
    <div class="mealplan-sidebar">
      <div 
        v-for="mealplan in mealplans" 
        :key="mealplan.id" 
        class="mealplan-card" 
        draggable="true"
        @dragstart="handleDragStart(mealplan)"
      >
        {{ mealplan.name }} ({{ mealplan.days }} days)
      </div>
    </div>
  </template>
  
  <script>
  import MealplanService from '@/services/MealplanService.js'; // Import the MealplanService to fetch meal plans
  
  export default {
    data() {
      return {
        mealplans: [], // Array to store meal plans fetched from the database
      };
    },
    methods: {
      // Method to handle the start of dragging a meal plan
      handleDragStart(mealplan) {
        // Emit an event with the dragged meal plan to the parent component
        this.$emit('mealplan-dragged', mealplan);
      },
      
      // Method to load meal plans from the backend
      async loadMealplans() {
        try {
          // Fetch the list of meal plans from the backend
          const response = await MealplanService.getMealplans();
          this.mealplans = response.data; // Set the meal plans array with the fetched data
        } catch (error) {
          // Log an error if the request fails
          console.error('Error loading meal plans:', error);
        }
      },
    },
    // Hook to load meal plans when the component is mounted
    mounted() {
      this.loadMealplans(); // Call the method to load meal plans
    },
  };
  </script>
  
  <style scoped>
  .mealplan-sidebar {
    width: 300px; /* Set the width of the sidebar */
    border: 1px solid #ddd; /* Add a border for separation */
    padding: 10px; /* Add some padding inside the sidebar */
  }
  
  .mealplan-card {
    margin-bottom: 10px; /* Space between cards */
    padding: 10px; /* Padding inside each card */
    background-color: #f9f9f9; /* Background color for the cards */
    border-radius: 5px; /* Rounded corners */
    cursor: grab; /* Change cursor to indicate draggable */
  }
  </style>