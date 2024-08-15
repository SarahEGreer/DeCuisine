<template>
    <h2 class="page-title">{{ isEdit ? 'Edit Mealplan' : 'Create Mealplan' }}</h2>
    <div class="mealplan-form-container form-container">
        <form v-on:submit.prevent="submitForm" class="mealplan-form">
            <div class="form-group">
                <label for="name" class="form-label">Meal Plan Name:</label>
                <input type="text" id="name" name="name" v-model="newMealplan.name" required class="form-input">
            </div>

            <div class="form-group">
                <label for="description" class="form-label">Description:</label>
                <textarea id="description" name="description" v-model="newMealplan.description"
                    class="form-textarea"></textarea>
            </div>

            <h3 class="section-title">Schedule:</h3>

            <div v-for="(schedule, index) in newMealplan.schedule" :key="index" class="schedule-group">
                <h4>Day {{ calculateDayNumber(index) }}</h4>

                <div class="form-group">
                    <label for="breakfast" class="form-label">Breakfast:</label>
                    <AutoComplete name="breakfast" id="breakfast" v-model="schedule.breakfast"
                        :suggestions="filteredRecipes" @complete="searchRecipes" dropdown optionLabel="name"
                        class="form-input" />
                </div>

                <div class="form-group">
                    <label for="lunch" class="form-label">Lunch:</label>
                    <AutoComplete name="lunch" id="lunch" v-model="schedule.lunch" :suggestions="filteredRecipes"
                        @complete="searchRecipes" optionLabel="name" dropdown class="form-input" />
                </div>

                <div class="form-group">
                    <label for="dinner" class="form-label">Dinner:</label>
                    <AutoComplete name="dinner" id="dinner" v-model="schedule.dinner" :suggestions="filteredRecipes"
                        @complete="searchRecipes" optionLabel="name" dropdown class="form-input" />
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
                    this.$router.push({ name: 'mealplan-scheduler' });

                });
            } else {
                MealplanService.submitMealplan(preparedMealplan).then(response => {
                    this.resetForm();
                    this.$router.push({ name: 'mealplan-scheduler' });
                    //  fix that

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

<style scoped>
/* .mealplan-form-container {
    max-width: 800px;
    width: 100%;
    margin: 0 auto;
    margin-top: 50px;
    margin-bottom: 50px;
    margin-top: 20px;
    background-color: #fdfdfd;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    /* height: 500px;
    overflow-y: auto; */


.form-group {
    margin-bottom: 20px;
}

.form-label {
    display: block;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 5px;
}

.form-input,
.form-textarea {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ddd;
    font-size: 14px;
}

.form-textarea {
    height: 100px;
    resize: none;
}

.section-title {
    font-size: 20px;
    font-weight: bold;
    margin: 20px 0 10px;
    text-transform: uppercase;
    color: #333;
}

.schedule-group {
    margin-bottom: 20px;
    padding: 10px;
    background-color: #f4f4f4;
    border-radius: 5px;
    position: relative;
    display: flex;
    flex-direction: column;
}

.remove-day-button {
    background-color: #e74c3c;
    color: #fff;
    border: none;
    padding: 8px 10px;
    border-radius: 5px;
    cursor: pointer;
    align-self: flex-end;
    margin-top: 10px;
}

.add-day-button {
    background-color: #27ae60;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 20px;
    display: block;
}

.form-buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.submit-button {
    background-color: #000000;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
}

.btn-cancel {
    background-color: #e74c3c;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
}
</style>
