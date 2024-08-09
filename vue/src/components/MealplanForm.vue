<template>
    <div>

        <form v-on:submit.prevent="submitForm">
            <label for="name">Meal Plan Name: </label>
            <input type="text" id="name" name="name" v-model="newMealplan.name" required>

            <!-- <div>

                <label for="prep-time-hours">Prep Time:</label>
                <input type="number" id="prep-time-hours" name="prep-time-hours" min="0" placeholder="Hours"
                    v-model.number="prepTimeHours">
                <input type="number" id="prep-time-minutes" name="prep-time-minutes" min="0" max="59"
                    placeholder="Minutes" v-model.number="prepTimeMinutes">

            </div> -->

            <!-- <div>
                <label for="cook-time-hours">Cook Time:</label>
                <input type="number" id="cook-time-hours" name="cook-time-hours" min="0" placeholder="Hours"
                    v-model.number="cookTimeHours">
                <input type="number" id="cook-time-minutes" name="cook-time-minutes" min="0" max="59"
                    placeholder="Minutes" v-model.number="cookTimeMinutes">
            </div> -->


            <label for="description">Description: </label>
            <textarea id="description" name="description" v-model="newMealplan.description"></textarea>

            <!-- <label for="servings">Servings: </label>
            <input type="number" id="servings" name="servings" min="0" v-model="newRecipe.servings"> -->


            <!-- <label for="ingredients">Ingredients: </label> -->
            <h3>Schedule:</h3>

            <div v-for="(ingredient, index) in newRecipe.schedule" :key="index">
                <label for="ingredient">Day: </label>

                <AutoComplete v-model="ingredient.name" :suggestions="filteredIngredients" @complete="searchIngredients"
                    dropdown />


                <label for="amount">Amount: </label>
                <input type="number" name="amount" min="0" v-model="ingredient.amount" step=".01" required>


                <label for="unit">Unit: </label>

                <AutoComplete v-model="ingredient.unit" :suggestions="filteredUnits" @complete="searchUnits" dropdown />

                <!-- <select id="unit" name="unit" v-model="ingredient.unit" required>
                    <option v-for="option in unitOptions" :key="option" :value="option">{{ option }}</option>
                </select> -->

                <button type="button" v-on:click="removeIngredient(index)">Remove Ingredient</button>
            </div>

            <button type="button" v-on:click="addIngredient">Add Ingredient</button>

            <label for="instructions">Instructions: </label>
            <textarea id="instructions" name="instructions" v-model="newRecipe.instructions" required></textarea>

            <!-- <button type="submit">Submit Recipe</button> -->

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
                // mealplanId: this.mealplan.mealplanId //TO COME
                userId: this.mealplan.Id,
                name: this.mealplan.name,
                description: this.mealplan.description,
                schedule: this.mealplan.schedule,
                
            },
            ingredientOptions: ['Tomato', 'Onion', 'Garlic', 'Salt', 'Pepper', 'Olive Oil'], //TEMP -> API CALL
            filteredIngredients: [],
            unitOptions: [
                'tsp',  // teaspoon
                'tbsp', // tablespoon
                'cup',  // cup
                'oz',   // ounce
                'lb',   // pound
                'g',    // gram
                'kg',   // kilogram
                'ml',   // milliliter
                'l',    // liter
                'pinch',// pinch
                'dash', // dash
                'quart',// quart
                'pint', // pint
                'fl oz',// fluid ounce
                'gal',  // gallon
                'mg',   // milligram
                'cm',   // centimeter
                'inch', // inch
                'dozen', // dozen
                'whole', // whole
                'piece', // piece
                'slice', // slice
                'stick', // stick
                'bunch', // bunch
                'can',   // can
                'jar',   // jar
                'box',   // box
                'bag',   // bag
                'leaf',  // leaf
                'clove', // clove
                'head',  // head
                'stalk', // stalk
            ], 
            filteredUnits: [],
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

        removeIngredient(index) {
            this.newRecipe.ingredients.splice(index, 1);
        },

        // convertToMinutes(hours, minutes) {
        //     return (hours * 60) + minutes;
        // },

            //SEARCH RECIPES FUNCTION
        searchIngredients(event) {
            this.filteredIngredients = this.ingredientOptions.filter((ingredient) => {
                return ingredient.toLowerCase().includes(event.query.toLowerCase());
            });
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

    // submitForm() {
    //     this.newRecipe.prepTime = this.convertToMinutes(this.prepTimeHours, this.prepTimeMinutes);
    //     this.newRecipe.cookTime = this.convertToMinutes(this.cookTimeHours, this.cookTimeMinutes);
    //     //check ingredients for duplicates and give error (try catch? something like that?)
    //     //if throw error, don't submit form
    //     if (this.hasDuplicates(this.newRecipe.ingredients)) {
    //         console.log("your recipe has a duplicate")
    //         throw new Error('Your recipe must not have duplicate ingredients')
    //     }
    //     // this is not working^
    //     console.log("You made it past the error");
    //     RecipeService.submitRecipe(this.newRecipe).then(response => {
    //         console.log("This is our submit response data" + response.status);
    //         // ^ check for response status and add user success message for created - 201, updated 200 in App view
    //         // route to recipe list 

    //         this.resetForm();
    //         this.$router.push({ name: 'recipe-list' });
    //         // add try catch block
    //     })

    // }
    // },

    created() {
        IngredientService.getIngredients().then(response => {
            this.ingredientOptions = response.data;
        })
    },

}

</script>


<style scoped>
textarea {
    resize: none;
}
</style>


./MealplanForm.vue/index.js
