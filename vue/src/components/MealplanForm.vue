<template>
    <div class="mealplan-form-container">
        <h1>{{ isEdit ? 'Edit Mealplan' : 'Create Mealplan' }}</h1>
        <form v-on:submit.prevent="submitForm" class="mealplan-form">
            <div class="form-group">
                <label for="name" class="form-label">Meal Plan Name:</label>
                <input type="text" id="name" name="name" v-model="newMealplan.name" required class="form-input">
            </div>

            <div class="form-group">
                <label for="description" class="form-label">Description:</label>
                <textarea id="description" name="description" v-model="newMealplan.description" class="form-textarea"></textarea>
            </div>

            <h3 class="section-title">Schedule:</h3>

            <div v-for="(schedule, index) in newMealplan.schedule" :key="index" class="schedule-group">
                <h4>Day {{ calculateDayNumber(index) }}</h4>

                <div class="form-group">
                    <label for="breakfast" class="form-label">Breakfast:</label>
                    <AutoComplete name="breakfast" id="breakfast" v-model="schedule.breakfast"
                        :suggestions="filteredRecipes" @complete="searchRecipes" dropdown optionLabel="name" class="form-input"/>
                </div>

                <div class="form-group">
                    <label for="lunch" class="form-label">Lunch:</label>
                    <AutoComplete name="lunch" id="lunch" v-model="schedule.lunch"
                        :suggestions="filteredRecipes" @complete="searchRecipes" optionLabel="name" dropdown class="form-input"/>
                </div>

                <div class="form-group">
                    <label for="dinner" class="form-label">Dinner:</label>
                    <AutoComplete name="dinner" id="dinner" v-model="schedule.dinner"
                        :suggestions="filteredRecipes" @complete="searchRecipes" optionLabel="name" dropdown class="form-input"/>
                </div>

                <button type="button" v-on:click="removeDay(index)" class="remove-day-button">Remove Day</button>
            </div>

            <button type="button" v-on:click="addDay" class="add-day-button">Add Day</button>

            <div class="form-buttons">
                <button type="submit" class="submit-button">{{ isEdit ? 'Save Changes' : 'Submit Meal Plan' }}</button>
                <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
            </div>
        </form>
    </div>
</template>

<script>
import AutoComplete from 'primevue/autocomplete';
import RecipeService from '../services/RecipeService.js';
import MealplanService from '../services/MealplanService.js';

export default {

    components: {
        AutoComplete
    },

    props: {
        mealplan: {
            type: Object,
            required: true,
        },
        isEdit: {
            type: Boolean,
            default: false
        }
    },

    data() {
        return {
            newMealplan: {
                userId: this.mealplan.userId,
                mealplanId: this.mealplan.mealplanId,
                name: this.mealplan.name,
                description: this.mealplan.description,
                schedule: this.mealplan.schedule.map(day => ({
                    day: day.day,
                    breakfast: { recipeId: day.breakfastId, name: day.breakfastName },
                    lunch: { recipeId: day.lunchId, name: day.lunchName },
                    dinner: { recipeId: day.dinnerId, name: day.dinnerName }
                })),
            },
            recipeOptions: [],
            filteredRecipes: [],
        };

    },

    watch: {
    mealplan: {
        handler(newVal) {
            this.newMealplan = {
                userId: newVal.userId,
                mealplanId: newVal.mealplanId,
                name: newVal.name,
                description: newVal.description,
                schedule: newVal.schedule.map(day => ({
                    day: day.day,
                    breakfast: { recipeId: day.breakfastId, name: day.breakfastName },
                    lunch: { recipeId: day.lunchId, name: day.lunchName },
                    dinner: { recipeId: day.dinnerId, name: day.dinnerName }
                })),
            };
        },
        immediate: true
    }
    },

    methods: {

        addDay() {
            const dayIndex = this.newMealplan.schedule.length + 1;
            this.newMealplan.schedule.push({
                day: dayIndex,
                breakfast: { recipeId: null, name: '' },
                lunch: { recipeId: null, name: '' },
                dinner: { recipeId: null, name: '' }
            });
        },

        prepareSchedule() {
            return this.newMealplan.schedule.map(day => ({
                day: day.day,
                breakfastId: day.breakfast.recipeId,
                breakfastName: day.breakfast.name,
                lunchId: day.lunch.recipeId,
                lunchName: day.lunch.name,
                dinnerId: day.dinner.recipeId,
                dinnerName: day.dinner.name
            }));
        },

        removeDay(index) {
            this.newMealplan.schedule.splice(index, 1);
        },

        searchRecipes(event) {
            this.filteredRecipes = this.recipeOptions.filter((recipe) => {
                return recipe.name.toLowerCase().includes(event.query.toLowerCase());
            });
        },

        calculateDayNumber(index) {
            return index + 1;
        },

        cancelForm() {
            this.$router.back();
        },

        resetForm() {
            this.newMealplan = {
                mealplanId: 0,
                name: '',
                description: '',
                schedule: [],
            };
            this.recipeOptions = [];
            this.filteredRecipes = [];
        },

        submitForm() {
            const preparedMealplan = {
                ...this.newMealplan,
                schedule: this.prepareSchedule()
            };

            if (this.isEdit) {
                MealplanService.updateMealplan(this.newMealplan.mealplanId, preparedMealplan).then(response => {
                    this.resetForm();
                });
            } else {
                MealplanService.submitMealplan(preparedMealplan).then(response => {
                    this.resetForm();
                });
            }
        }
    },

    created() {
        RecipeService.getRecipes().then(response => {
            this.recipeOptions = response.data;
        });
    },

}
</script>



