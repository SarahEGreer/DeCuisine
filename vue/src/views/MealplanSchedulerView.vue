<template>
  <div class="meal-plan-scheduler-view">
    <MealplanSidebar 
      ref="sidebarContainer"
      @mealplan-dragged="prepareForDrag" 
    />
    <MealplanCalendar 
      :draggedMealPlan="draggedMealPlan" 
      @eventClick="deleteEvent" 
      @showDetails = "showMealplanDetails"
    />
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
}
</style>
