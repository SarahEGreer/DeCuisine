<template>
  <div class="meal-plan-scheduler-view">
    <div class="sidebar-wrapper">
      <MealplanSidebar ref="sidebarContainer" @mealplan-dragged="prepareForDrag" />
    </div>
    <div class="calendar-wrapper">
      <MealplanCalendar :draggedMealPlan="draggedMealPlan" @eventClick="deleteEvent"
        @showDetails="showMealplanDetails" />
    </div>
  </div>
</template>

<script>
import MealplanSidebar from '@/components/MealplanSidebar.vue';
import MealplanCalendar from '@/components/MealplanCalendar.vue';
import MealplanService from '@/services/MealplanService.js';

export default {
  components: {
    MealplanSidebar,
    MealplanCalendar,
  },
  data() {
    return {
      draggedMealPlan: null,
    };
  },
  methods: {
    prepareForDrag(mealPlan) {
      this.draggedMealPlan = { ...mealPlan };
    },
    showMealplanDetails(mealplanId) {
      this.$router.push({ name: 'mealplan-details', params: { mealplanId } });
    },
    deleteEvent(eventId) {
      console.log('Delete event:', eventId);
      MealplanService.deleteMealplanEvent(eventId)
      location.reload();
      // .then(() => {
      //   this.$refs.calendar.loadTrackedEvents();
      // })
      // .catch(error => {
      //   console.error('Error deleting event:', error);
      // });
    },
  },
};
</script>

<style scoped>
.meal-plan-scheduler-view {
  display: flex;
  flex-direction: row;
  /* height: 100vh; */

}

.sidebar-wrapper {
  width: 20%;
  height: 100vh;
  height: calc(100vh-50px);
  background-color: #f0f0f0;
  overflow-y: auto;
  border-right: 1px solid #ccc;
}

.calendar-wrapper {
  width: 80%;
  /* height: 100vh; */
  margin-top: 1.25vh;
  margin-left: 5vh;
  margin-right: 5vh;
}
</style>
