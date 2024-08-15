<template>
  <div class="tutorial">
    <p>Click a scheduled meal plan to see its details. Click and drag to change its start date. Double click to delete.
    </p>

  </div>
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
        eventTimeFormat: {},
        eventDateFormat: {},
        eventContent: function (info) {
          return {
            html: `<div class="fc-event-title">${info.event.title}</div>`
          };
        },
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
      // Prevent default context menu
      info.jsEvent.preventDefault();

      // Check if the right mouse button was clicked
      if (info.jsEvent.button === 0) { // 0 is for left-click
        if (this.clickTimeout) {
          clearTimeout(this.clickTimeout); // Clear existing timeout if it's already set
          this.clickTimeout = null; // Reset the timeout
          // This is a double-click
          this.$emit('eventClick', info.event.id); // Emit for deletion
        } else {
          // This is a single-click, set a timeout
          this.clickTimeout = setTimeout(() => {
            this.$emit('showDetails', info.event.extendedProps.mealplanId); // Emit to show details
            this.clickTimeout = null; // Reset the timeout after executing
          }, 250); // Adjust the timeout duration as needed
        }
      }
    },

  },
  created() {
    this.loadTrackedEvents();
  },
};
</script>

<style scoped></style>
