<template>
  <div class="mealplan-sidebar" ref="sidebarContainer">
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
