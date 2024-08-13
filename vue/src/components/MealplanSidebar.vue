<template>
  <div class="mealplan-sidebar" ref="sidebarContainer">
    <div 
      v-for="mealplan in mealplans" 
      :key="mealplan.id" 
      class="mealplan-card" 
      :data-id="mealplan.id"
      draggable="true"
      @dragstart="handleDragStart(mealplan)"
    >
      {{ mealplan.name }} ({{ mealplan.days }} days)
    </div>
  </div>
</template>

<script>
import MealplanService from '@/services/MealplanService.js';

export default {
  data() {
    return {
      mealplans: [],
    };
  },
  methods: {
    handleDragStart(mealplan) {
      this.$emit('mealplan-dragged', mealplan);
    },
    async loadMealplans() {
      try {
        const response = await MealplanService.getMealplans();
        this.mealplans = response.data;
      } catch (error) {
        console.error('Error loading meal plans:', error);
      }
    },
  },
  mounted() {
    this.loadMealplans();
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




