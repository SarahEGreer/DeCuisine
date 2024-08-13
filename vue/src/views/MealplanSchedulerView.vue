<template>
  <div class="meal-planner">
    <!-- Pass the ref directly to the component -->
    <MealplanSidebar 
      ref="sidebarContainer"
      @prepareForDrag="prepareForDrag" 
      :mealPlans="mealPlans" 
    />
    <MealplanCalendar 
      :draggedMealPlan="draggedMealPlan" 
      @eventClick="showMealPlanDetails" 
      @eventDrop="updateEventDate"
      @eventRemove="removeEvent" 
    />
    <MealplanTrashbin @dropEvent="removeEvent" />
  </div>
</template>

<script>
import MealplanSidebar from '@/components/MealplanSidebar.vue';
import MealplanCalendar from '@/components/MealplanCalendar.vue';
import MealplanTrashbin from '@/components/MealplanTrashbin.vue';
import MealplanService from '@/services/MealplanService.js';
import { Draggable } from '@fullcalendar/interaction';

export default {
  components: {
    MealplanSidebar,
    MealplanCalendar,
    MealplanTrashbin,
  },
  data() {
    return {
      mealPlans: [], // Initially empty, will be populated from the database
      draggedMealPlan: null,
    };
  },
  methods: {
    async loadMealPlans() {
      try {
        const response = await MealplanService.getMealplans();
        this.mealPlans = response.data;
      } catch (error) {
        console.error('Error loading meal plans:', error);
      }
    },
    prepareForDrag(mealPlan) {
      this.draggedMealPlan = { ...mealPlan };
    },
    async showMealPlanDetails(mealPlanId) {
      this.$router.push({ name: 'mealplan-details', params: { mealPlanId } });
    },
    async updateEventDate(eventInfo) {
      try {
        const updatedEvent = {
          eventId: eventInfo.event.extendedProps.eventId,
          startDate: eventInfo.event.start,
        };
        await MealplanService.updateMealplanEvent(updatedEvent.eventId, updatedEvent);
      } catch (error) {
        console.error('Error updating event date:', error);
      }
    },
    async removeEvent(eventId) {
      try {
        await MealplanService.deleteMealplanEvent(eventId);
      } catch (error) {
        console.error('Error deleting event:', error);
      }
    },
    initializeDraggable() {
      const containerEl = this.$refs.sidebarContainer.$el; // Reference the sidebar element correctly
      if (containerEl) {
        new Draggable(containerEl, {
          itemSelector: '.mealplan-card',
          eventData: (eventEl) => {
            return {
              title: eventEl.innerText,
              id: eventEl.dataset.id,
            };
          },
        });
      } else {
        console.error('Sidebar container element not found.');
      }
    },
  },
  mounted() {
    this.loadMealPlans();
    this.$nextTick(() => {
      this.initializeDraggable();
    });
  },
};
</script>

<style>
.meal-planner {
  display: flex;
}
</style>