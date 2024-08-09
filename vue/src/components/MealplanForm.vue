<template>
    <div>

        <form v-on:submit.prevent="submitForm">
            <label for="name">Meal Plan Name: </label>
            <input type="text" id="name" name="name" v-model="newMealplan.name" required>


            <label for="description">Description: </label>
            <textarea id="description" name="description" v-model="newMealplan.description"></textarea>

            <h3>Schedule:</h3>

            <div v-for="(schedule, index) in newMealPlan.schedule" :key="index">
                <h4>Day {{ calculateDayNumber(index) }}</h4>
                
                
                <label for="breakfast">Breakfast:</label> 
                <!-- search for recipe name  -->
                <AutoComplete name="breakfast" id="breakfast" v-model="recipe.name" :suggestions="filteredRecipes" @complete="searchRecipes"
                    dropdown />
                    <!-- make id dynamically change -->


                <label for="lunch">Lunch: </label>
                <AutoComplete name="lunch" id="lunch" v-model="recipe.name" :suggestions="filteredRecipes" @complete="searchRecipes"
                    dropdown />


                <label for="dinner">Dinner: </label>
                <AutoComplete name="dinner" id="dinner" v-model="recipe.name" :suggestions="filteredRecipes" @complete="searchRecipes"
                    dropdown />


                <button type="button" v-on:click="removeDay(index)">Remove Ingredient</button>
            </div>

            <button type="button" v-on:click="addDay">Add Day</button>

           
            <button type="submit">{{ isEdit ? 'Save Changes' : 'Submit Recipe' }}</button>

            <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>


        </form>

    </div>
</template>

<script>
import AutoComplete from 'primevue/autocomplete';
import RecipeService from '../services/RecipeService.js';
import IngredientService from '../services/IngredientService.js';

export default {

    components: {
        AutoComplete
    },

    props: {
        mealPlan: {
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
                userId: this.mealplan.Id,
                name: this.mealplan.name,
                description: this.mealplan.description,
                schedule: this.mealplan.schedule,
                
            },
            recipeOptions: [], 
            filteredRecipes: [],
            
            
            prepTimeHours: 0,
            prepTimeMinutes: 0,
            cookTimeHours: 0,
            cookTimeMinutes: 0
        };

    },

    // watch: {
    //     recipe: {
    //         handler(newVal) {
    //             this.newRecipe = { ...newVal };
    //             // Populate hours and minutes fields based on prepTime and cookTime
    //             this.prepTimeHours = Math.floor(this.newRecipe.prepTime / 60);
    //             this.prepTimeMinutes = this.newRecipe.prepTime % 60;
    //             this.cookTimeHours = Math.floor(this.newRecipe.cookTime / 60);
    //             this.cookTimeMinutes = this.newRecipe.cookTime % 60;
    //         },
    //         immediate: true
    //     }
    // },

    methods: {

        //ADD DAY FUNCTION
        addDay() {
            this.newMealplan.schedule.push({ name: '', amount: 0, unit: '' });
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
                return recipe.toLowerCase().includes(event.query.toLowerCase());
            });
        },

        calculateDayNumber(index) {
            return index + 1;
        },


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
            this.newRecipe = {
                recipeId: 0,
                name: '',
                prepTime: '',
                cookTime: '',
                description: '',
                servings: '',
                ingredients: [],
                instructions: ''
            };
            this.newIngredients = [];
            this.prepTimeHours = 0;
            this.prepTimeMinutes = 0;
            this.cookTimeHours = 0;
            this.cookTimeMinutes = 0;
        },

        //UPDATE ALL LOGIC TO MEALPLAN
        submitForm() {
            this.newRecipe.prepTime = this.convertToMinutes(this.prepTimeHours, this.prepTimeMinutes);
            this.newRecipe.cookTime = this.convertToMinutes(this.cookTimeHours, this.cookTimeMinutes);
            if (this.hasDuplicates(this.newRecipe.ingredients)) {
                console.log("your recipe has a duplicate");
                throw new Error('Your recipe must not have duplicate ingredients');
            }
            if (this.isEdit) {
                RecipeService.updateRecipe(this.newRecipe.recipeId, this.newRecipe).then(response => {
                    console.log("This is our update response data", response.status);
                    this.resetForm();
                    this.$router.push({ name: 'recipe-list' });
                });
            } else {
                RecipeService.submitRecipe(this.newRecipe).then(response => {
                    console.log("This is our submit response data", response.status);
                    this.resetForm();
                    this.$router.push({ name: 'recipe-list' });
                });
            }
        }
    },

    created() {
        RecipeService.getRecipes().then(response => {
            this.recipeOptions = response.data;
        })
    },

}

</script>


<style scoped>
textarea {
    resize: none;
}
</style>


<!-- ./MealplanForm.vue/index.js -->
