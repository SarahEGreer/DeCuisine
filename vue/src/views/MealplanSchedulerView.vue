<template>
  <div class="meal-plan-scheduler-view">
    <MealplanSidebar 
      ref="sidebarContainer"
      @mealplan-dragged="prepareForDrag" 
    />
    <MealplanCalendar 
      :draggedMealPlan="draggedMealPlan" 
      @eventClick="showMealPlanDetails" 
    />
  </div>
</template>

<script>
import MealplanSidebar from '@/components/MealplanSidebar.vue';
import MealplanCalendar from '@/components/MealplanCalendar.vue';

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
    showMealPlanDetails(mealplanId) {
      this.$router.push({ name: 'mealplan-details', params: { mealplanId } });
    },
  },
};
</script>

<style scoped>
.meal-plan-scheduler-view {
  display: flex;
}
</style>
