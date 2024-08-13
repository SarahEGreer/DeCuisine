<template>
  <FullCalendar
    ref="fullCalendar"
    :options="calendarOptions"
  />
</template>

<script>
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import MealplanService from '@/services/MealplanService.js';

export default {
  components: {
    FullCalendar,
  },
  props: {
    draggedMealPlan: Object,
  },
  data() {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        editable: true,
        droppable: true,
        events: [], // This will be populated by the tracked meal plans from the database
        eventReceive: this.handleEventReceive,
        eventDrop: this.handleEventDrop,
        eventClick: this.handleEventClick,
        eventRemove: this.handleEventRemove,
      },
    };
  },
  methods: {
    async loadTrackedEvents() {
      try {
        const response = await MealplanService.getTrackedMealplanEvents();
        const events = response.data.map(event => ({
          id: event.eventId,
          title: event.name,
          start: event.startDate,
          end: new Date(new Date(event.startDate).setDate(new Date(event.startDate).getDate() + event.days)),
          extendedProps: {
            mealPlanId: event.mealplanId,
            eventId: event.eventId,
          },
        }));
        this.calendarOptions.events = events;
      } catch (error) {
        console.error('Error loading tracked events:', error);
      }
    },
    handleEventReceive(eventInfo) {
      console.log('Event received:', eventInfo);
      const mealPlanId = eventInfo.event.id; // Get ID from draggable

      const mealPlan = this.$parent.mealPlans.find(plan => plan.id == mealPlanId);

      if (mealPlan) {
        const durationInDays = mealPlan.days; // Updated to use mealPlan.days
        const startDate = eventInfo.event.start;
        const endDate = new Date(startDate);
        endDate.setDate(endDate.getDate() + durationInDays);

        eventInfo.event.setProp('title', mealPlan.name); // Updated title with just the meal plan name
        eventInfo.event.setExtendedProp('mealPlanId', mealPlanId);
        eventInfo.event.setDates(startDate, endDate);
      }
    },
    handleEventDrop(eventInfo) {
      this.$emit('eventDrop', eventInfo);
    },
    handleEventClick(info) {
      this.$emit('eventClick', info.event.extendedProps.mealPlanId);
    },
    async handleEventRemove(eventInfo) {
      try {
        await MealplanService.deleteMealplanEvent(eventInfo.event.id);
      } catch (error) {
        console.error('Error deleting event:', error);
      }
    },
  },
  created() {
    this.loadTrackedEvents();
  },
};
</script>

<style>
/* Add any necessary styling here */
</style>