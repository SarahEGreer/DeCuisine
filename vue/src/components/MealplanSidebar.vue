<template>
  <div class="mealplan-sidebar" ref="sidebarContainer">

    <h3>My Meal Plans</h3>

    <div class="tutorial">
      <p>Click a meal plan to see more details </p>
      <p>Drag a meal plan to the calendar to schedule it</p>
    </div>
    <div v-for="mealplan in mealplans" :key="mealplan.mealplanId" class="mealplan-card" :data-id="mealplan.mealplanId"
      :data-event="JSON.stringify({ title: mealplan.name, id: mealplan.mealplanId })"
      @mousedown="emitDraggedMealplan(mealplan)" @click="goToMealplanDetails(mealplan.mealplanId)">
      {{ mealplan.name }}
    </div>
    <button @click="goToCreateMealplan">Create New Meal Plan</button>
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
    goToMealplanDetails(mealplanId) {
      this.$router.push({ name: 'mealplan-details', params: { mealplanId } });
    },
    goToCreateMealplan() {
      this.$router.push({ name: 'mealplan-form' });
    }
  },
  mounted() {
    this.loadMealplans();

    new Draggable(this.$refs.sidebarContainer, {
      itemSelector: '.mealplan-card',
      eventData: function (eventEl) {
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
  height: 100%;

}

h3 {
  margin-top: 10px;
  margin-bottom: 10px;
}

.tutorial {
  margin-top: 0px;
  margin-bottom: 10px;
}

button {
  margin-top: auto;
  margin-bottom: 100px;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
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
  background-color: #4CAF50;
  cursor: grab;
}

.mealplan-card:active {
  background-color: #ccc;
  cursor: grabbing;
}
</style>
