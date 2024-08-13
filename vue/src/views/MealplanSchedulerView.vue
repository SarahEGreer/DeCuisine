<template>
    <div class="mealplan-view">
      <!-- Sidebar component containing meal plan cards for dragging -->
      <MealplanSidebar 
        @mealplan-dragged="handleMealplanDragged"
      />
      
      <!-- Calendar component where meal plans can be scheduled -->
      <MealplanCalendar 
        :draggedMealPlan="draggedMealPlan" 
      />
    </div>
  </template>
  
  <script>
  import MealplanSidebar from '@/components/MealplanSidebar.vue'; // Import the sidebar component
  import MealplanCalendar from '@/components/MealplanCalendar.vue'; // Import the calendar component
  
  export default {
    components: {
      MealplanSidebar, // Register the sidebar component
      MealplanCalendar, // Register the calendar component
    },
    data() {
      return {
        draggedMealPlan: null, // Stores the meal plan currently being dragged
      };
    },
    methods: {
      // Method to handle the event when a meal plan is dragged from the sidebar
      handleMealplanDragged(mealplan) {
        this.draggedMealPlan = mealplan; // Set the dragged meal plan to be passed to the calendar
      },
    },
  };
  </script>
  
  <style scoped>
  .mealplan-view {
    display: flex; /* Display the sidebar and calendar side by side */
    flex-direction: row; /* Align sidebar and calendar in a row */
    gap: 20px; /* Space between the sidebar and calendar */
  }
  </style>