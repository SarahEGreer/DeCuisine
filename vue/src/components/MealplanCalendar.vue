<template>
    <!-- FullCalendar component provides the UI for the calendar. -->
    <FullCalendar
      ref="fullCalendar"
      :options="calendarOptions" 
    />
  </template>
  
  <script>
  import FullCalendar from '@fullcalendar/vue3'; // Import the FullCalendar component for Vue.js
  import dayGridPlugin from '@fullcalendar/daygrid'; // Import the dayGrid plugin for month/day views
  import interactionPlugin from '@fullcalendar/interaction'; // Import the interaction plugin for drag-and-drop functionality
  import MealplanService from '@/services/MealplanService.js'; // Import the MealplanService for handling API calls
  
  export default {
    components: {
      FullCalendar, // Register FullCalendar as a component
    },
    props: {
      draggedMealPlan: Object, // Prop to receive the meal plan object being dragged from the sidebar
    },
    data() {
      return {
        calendarOptions: {
          plugins: [dayGridPlugin, interactionPlugin], // Include plugins for grid layout and interaction (drag-and-drop)
          initialView: 'dayGridMonth', // Set the initial calendar view to a month grid
          editable: true, // Allow events on the calendar to be edited (moved)
          droppable: true, // Allow external items to be dropped onto the calendar
          events: [], // Array to store calendar events fetched from the database
          eventReceive: this.handleEventReceive, // Method to handle event creation when an item is dropped onto the calendar
          eventDrop: this.handleEventDrop, // Method to handle updating the event when it is moved on the calendar
          eventClick: this.handleEventClick, // Method to handle event clicks (not implemented, placeholder)
          eventRemove: this.handleEventRemove, // Method to handle the removal of an event (e.g., dragged to a trash can)
        },
      };
    },
    methods: {
      // Method to handle the creation of a new event when a meal plan is dropped onto the calendar
      async handleEventReceive(eventInfo) {
        try {
          // Create a new event object from the dropped meal plan
          const newEvent = {
            mealplanId: eventInfo.event.id, // Use the meal plan ID as the event ID
            name: eventInfo.event.title, // The title of the event, typically the meal plan name
            startDate: eventInfo.event.start, // The start date for the event
          };
          
          // Send a POST request to create the new event in the backend database
          const response = await MealplanService.createMealplanEvent(newEvent);
  
          // Update the calendar event with the new ID returned from the backend
          eventInfo.event.setProp('id', response.data.eventId); // Set the event ID
          eventInfo.event.setExtendedProp('eventId', response.data.eventId); // Store the event ID as an extended property
        } catch (error) {
          // If an error occurs during creation, revert the event to its original state
          console.error('Error creating event:', error);
          eventInfo.revert(); // Revert the event position on the calendar
        }
      },
  
      // Method to handle updating an event when it is moved to a different date on the calendar
      async handleEventDrop(eventInfo) {
        try {
          // Prepare the updated event object with the new start date
          const updatedEvent = {
            eventId: eventInfo.event.extendedProps.eventId, // Retrieve the event ID from the extended properties
            newStartDate: eventInfo.event.start, // Set the new start date after the event is moved
          };
          
          // Send a PUT request to update the event's start date in the backend database
          await MealplanService.updateMealplanEvent(updatedEvent);
        } catch (error) {
          // If an error occurs during the update, revert the event to its original position
          console.error('Error updating event:', error);
          eventInfo.revert(); // Revert the event position on the calendar
        }
      },
  
      // Method to handle the removal of an event when it is dragged to a trash can (to unschedule the event)
      async handleEventRemove(eventInfo) {
        try {
          // Send a DELETE request to remove the event from the backend database
          await MealplanService.deleteMealplanEvent(eventInfo.event.extendedProps.eventId);
        } catch (error) {
          // If an error occurs during deletion, log the error
          console.error('Error deleting event:', error);
          // No need to revert since the event is being deleted
        }
      },
  
      // Method to load tracked events from the backend and populate the calendar with these events
      async loadTrackedEvents() {
        try {
          // Fetch the list of tracked events (meal plans with a start date) from the backend
          const response = await MealplanService.getTrackedMealplanEvents();
          
          // Map the response data to the format required by FullCalendar and set the events in the calendarOptions
          this.calendarOptions.events = response.data.map(event => ({
            id: event.mealplanId, // Use the meal plan ID as the event ID
            title: event.name, // Set the event title to the meal plan name
            start: event.startDate, // Set the start date of the event
            extendedProps: { eventId: event.eventId }, // Store the event ID as an extended property for reference
          }));
        } catch (error) {
          // If an error occurs during the fetch, log the error
          console.error('Error loading events:', error);
        }
      },
    },
    // Hook to load events when the component is first mounted
    mounted() { //change to created??
      this.loadTrackedEvents(); // Call the method to load and populate the calendar with tracked events
    },
  };
  </script>
  
  <style>
  /* Optional styling for the calendar, events, and other related elements */
  </style>