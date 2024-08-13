<template>
  <FullCalendar ref="fullCalendar" :options="calendarOptions" />
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
        events: [], // Will be populated by loadTrackedEvents
        eventReceive: this.handleEventReceive,
        eventDrop: this.handleEventDrop,
        eventClick: this.handleEventClick,
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
            mealplanId: event.mealplanId,
          },
        }));
        this.calendarOptions.events = events;
      } catch (error) {
        console.error('Error loading tracked events:', error);
      }
    },
    async handleEventReceive(eventInfo) {
      const mealplan = this.draggedMealPlan; // Use the prop draggedMealPlan

      if (!mealplan) {
        console.error('No meal plan dragged');
        return;
      }

      const startDate = eventInfo.event.start.toISOString().split('T')[0];

      try {
        const response = await MealplanService.createMealplanEvent({
          mealplanId: mealplan.mealplanId,
          startDate: startDate,
        });

        eventInfo.event.setExtendedProp('mealplanId', mealplan.mealplanId);
        eventInfo.event.setExtendedProp('eventId', response.data.eventId);
        eventInfo.event.setProp('title', mealplan.name);

        // Refresh the calendar to display the new event properly
        this.loadTrackedEvents();
      } catch (error) {
        console.error('Error creating event:', error);
        eventInfo.revert();
      }
    },
    
    async handleEventDrop(eventInfo) {
      console.log('Event dropped:', eventInfo);
      try {
        let updatedEvent = {
          // test1: eventInfo.event, 
          // test2: eventInfo.event.extendedProps.id, 
    
          
          eventId: eventInfo.event.id,
          mealplanId: eventInfo.event.extendedProps.mealplanId,
          startDate: eventInfo.event.start.toISOString().split('T')[0],
        };

        await MealplanService.updateMealplanEvent(updatedEvent);

        // Optionally, you can reload events here to reflect the change immediately
        this.loadTrackedEvents();
      } catch (error) {
        console.error('Error updating event date:', error);
        eventInfo.revert();
      }
    },

    handleEventClick(info) {
      this.$emit('eventClick', info.event.extendedProps.mealplanId);
    },
  },
  created() {
    this.loadTrackedEvents();
  },
};
</script>
