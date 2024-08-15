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
    <button @click="goToCreateMealplan" class="create-btn">Create New Meal Plan</button>
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
  margin-left: 10px;
  margin-top: 10px;
  margin-bottom: 10px;
}

.tutorial {
  margin-left: 10px;
  margin-top: 0px;
  margin-bottom: 10px;
}

button {

  padding: 12px;
  /* background-color: #223F1F; */
  background-color: #CF901D;
  color: white;
  border: none;
  font-weight: 500;
  border-radius: 5px;
  cursor: pointer;
}

.create-btn {
  margin-top: 60px;
}

button,
.mealplan-card {
  margin: 5px 10px;
}

button:hover {
  opacity: .8;
}

.mealplan-card {
  margin-top: 15px;
  padding: 18px;
  background-color: #006272;
  color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  cursor: move;
}

.mealplan-card:nth-child(odd) {
  opacity: .8;
}

.mealplan-card:hover {
  opacity: .6;
  cursor: grab;
}

.mealplan-card:active {
  background-color: #ccc;
  cursor: grabbing;
}
</style>
