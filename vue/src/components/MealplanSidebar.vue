<template>

  <div class="mealplan-sidebar" ref="sidebarContainer">
    <div class="tutorial">
      <p>Drag a meal plan to the calendar to schedule it.</p>      
    </div>
    <div 
      v-for="mealplan in mealplans" 
      :key="mealplan.mealplanId" 
      class="mealplan-card" 
      :data-id="mealplan.mealplanId"
      :data-event="JSON.stringify({ title: mealplan.name, id: mealplan.mealplanId })"
      @mousedown="emitDraggedMealplan(mealplan)" 
    >
      {{ mealplan.name }}
    </div>
  </div>
</template>

<script>
import { Draggable } from '@fullcalendar/interaction';
import MealplanService from '@/services/MealplanService.js';

export default {
  data() {
    return {
      mealplans: [],
    };
  },
  methods: {
    async loadMealplans() {
      try {
        const response = await MealplanService.getMealplans();
        this.mealplans = response.data;
      } catch (error) {
        console.error('Error loading meal plans:', error);
      }
    },
    emitDraggedMealplan(mealplan) {
      this.$emit('mealplan-dragged', mealplan); // Emit the meal plan being dragged
    },
  },
  mounted() {
    this.loadMealplans();
    
    new Draggable(this.$refs.sidebarContainer, {
      itemSelector: '.mealplan-card',
      eventData: function(eventEl) {
        return JSON.parse(eventEl.getAttribute('data-event'));
      }
    });
  },
};
</script>

<style scoped>
.mealplan-sidebar {
  display: flex;
  flex-direction: column;
  padding: 10px;
  /* background-color: #f0f0f0; */
  border-right: 1px solid #ccc;
  /* height: 100%; */
}
.mealplan-card {
  padding: 10px;
  margin: 5px 0;
  margin-left: 1vh;
  margin-right: 1vh;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  cursor: move;
}

.mealplan-card:hover {
  background-color: #f0f0f0;
  cursor: grab;
}

.mealplan-card:active {
  background-color: #ccc;
  cursor: grabbing;
}



</style>
