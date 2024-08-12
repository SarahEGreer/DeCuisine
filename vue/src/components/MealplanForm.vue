<!-- <template>
    <div>
        <h1>meal plan form has loaded</h1>
        <form v-on:submit.prevent="submitForm">
            <label for="name">Meal Plan Name: </label>
            <input type="text" id="name" name="name" v-model="newMealplan.name" required>


            <label for="description">Description: </label>
            <textarea id="description" name="description" v-model="newMealplan.description"></textarea>

            <h3>Schedule:</h3>

            <div v-for="(schedule, index) in newMealplan.schedule" :key="index">
                <h4>Day {{ calculateDayNumber(index) }}</h4>


                <label for="breakfast">Breakfast:</label>
                
                <AutoComplete name="breakfast" id="breakfast" v-model="schedule.breakfastId"
                    :suggestions="filteredRecipes" @complete="searchRecipes" dropdown optionLabel="name" />
                

                <label for="lunch">Lunch: </label>
                <AutoComplete name="lunch" id="lunch" v-model="schedule.lunchId" :suggestions="filteredRecipes"
                    @complete="searchRecipes" optionLabel="name" dropdown />


                <label for="dinner">Dinner: </label>
                <AutoComplete name="dinner" id="dinner" v-model="schedule.dinnerId" :suggestions="filteredRecipes"
                    @complete="searchRecipes" optionLabel="name" dropdown />


                <button type="button" v-on:click="removeDay(index)">Remove Ingredient</button>
            </div>

            <button type="button" v-on:click="addDay">Add Day</button>


            <button type="submit">{{ isEdit ? 'Save Changes' : 'Submit Meal Plan' }}</button>

            <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>


        </form>

    </div>
</template> -->

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
                    <AutoComplete name="breakfast" id="breakfast" v-model="schedule.breakfastId"
                        :suggestions="filteredRecipes" @complete="searchRecipes" dropdown optionLabel="name" class="form-input"/>
                </div>

                <div class="form-group">
                    <label for="lunch" class="form-label">Lunch:</label>
                    <AutoComplete name="lunch" id="lunch" v-model="schedule.lunchId" :suggestions="filteredRecipes"
                        @complete="searchRecipes" optionLabel="name" dropdown class="form-input"/>
                </div>

                <div class="form-group">
                    <label for="dinner" class="form-label">Dinner:</label>
                    <AutoComplete name="dinner" id="dinner" v-model="schedule.dinnerId" :suggestions="filteredRecipes"
                        @complete="searchRecipes" optionLabel="name" dropdown class="form-input"/>
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
                mealplanId: this.mealplan.mealplanId,
                // userId: this.mealplan.Id,
                name: this.mealplan.name,
                description: this.mealplan.description,
                schedule: this.mealplan.schedule,

            },
            recipeOptions: [],
            filteredRecipes: [],
        };

    },

    watch: {
        mealplan: {
            handler(newVal) {
                this.newMealplan = { ...newVal };
            },
            immediate: true
        }
    },

    methods: {

        //ADD DAY FUNCTION
        addDay() {
            const dayIndex = this.newMealplan.schedule.length + 1; // Calculate the day based on the current length
            this.newMealplan.schedule.push({
                day: dayIndex,  // Set day to be the next index (1-based)
                breakfastId: null,
                breakfastName: '',
                lunchId: null,
                lunchName: '',
                dinnerId: null,
                dinnerName: ''
            });
        },

        prepareSchedule() {
            this.newMealplan.schedule = this.newMealplan.schedule.map(day => ({
                day: day.day,
                breakfastId: day.breakfastId.recipeId,  // Extract only the recipeId
                breakfastName: day.breakfastId.name,
                lunchId: day.lunchId.recipeId,  // Extract only the recipeId
                lunchName: day.lunchId.name,
                dinnerId: day.dinnerId.recipeId,  // Extract only the recipeId
                dinnerName: day.dinnerId.name
            }));
            return this.newMealplan.schedule;
        },

        removeDay(index) {
            this.newMealplan.schedule.splice(index, 1);
        },

        // convertToMinutes(hours, minutes) {
        //     return (hours * 60) + minutes;
        // },

        //SEARCH RECIPES FUNCTION
        searchRecipes(event) {
            this.filteredRecipes = this.recipeOptions.filter((recipe) => {
                return recipe.name.toLowerCase().includes(event.query.toLowerCase());
            });
        },



        calculateDayNumber(index) {
            return index + 1;
        },

        // updateMeal(schedule, mealType, selectedRecipe) {
        //     schedule[mealType] = selectedRecipe.recipeId;
        // },


        // searchUnits(event) {
        //     this.filteredUnits = this.unitOptions.filter((unit) => {
        //         return unit.toLowerCase().includes(event.query.toLowerCase());
        //     });
        // },

        // hasDuplicates(array) {
        //     return array.length !== new Set(array).size;
        // },

        cancelForm() {
            this.$router.back();
        },

        //CHANGE TO MEALPLAN FORM DEFAULTS
        resetForm() {
            this.newMealplan = {
                mealplanId: 0,
                name: '',
                description: '',
                // userId: 0,
                schedule: [],
            };
            this.recipeOptions = [];
            this.filteredRecipes = [];
        },

        //UPDATE ALL LOGIC TO MEALPLAN
        submitForm() {
            // this.newRecipe.prepTime = this.convertToMinutes(this.prepTimeHours, this.prepTimeMinutes);
            // this.newRecipe.cookTime = this.convertToMinutes(this.cookTimeHours, this.cookTimeMinutes);
            // if (this.hasDuplicates(this.newRecipe.ingredients)) {
            //     console.log("your recipe has a duplicate");
            //     throw new Error('Your recipe must not have duplicate ingredients');

            // }

            const preparedMealplan = {
                ...this.newMealplan,
                schedule: this.prepareSchedule()
            };
            // not sure if we need this^


            console.log(this.newMealplan);
            console.log(this.mealplan);
            if (this.isEdit) {
                MealplanService.updateMealplan(this.newMealplan.mealplanId, this.newMealplan).then(response => {
                    console.log("This is our update response data", response.status);
                    this.resetForm();
                    // this.$router.push({ name: 'recipe-list' });
                });
            } else {
                MealplanService.submitMealplan(this.newMealplan).then(response => {
                    console.log("This is our submit response data", response.status);
                    this.resetForm();
                    // this.$router.push({ name: 'recipe-list' });
                });
            }
        }
    },

    created() {
        RecipeService.getRecipes().then(response => {
            this.recipeOptions = response.data;
            // console.log(this.recipeOptions);
        })
    },

}

</script>


<style scoped>
.mealplan-form-container {
    max-width: 900px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fdfdfd;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow-y: auto; 
}

.mealplan-form {
    max-height: calc(100vh - 200px); 
    overflow-y: auto;
    padding: 75px;
}

.form-group {
    margin-bottom: 20px;
}

.form-label {
    display: block;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 5px;
}

.form-input, .form-textarea {
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
    margin-bottom: 10px;
    padding: 10px;
    background-color: #f4f4f4;
    border-radius: 5px;
}

.remove-day-button {
    background-color: #e74c3c;
    color: #fff;
    border: none;
    padding: 8px 10px;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 5px;
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
    background-color: #2c3e50;
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


