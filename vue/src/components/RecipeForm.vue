<template>
    <div>

        <form v-on:submit.prevent="submitForm">
            <label for="name">Recipe Name: </label>
            <input type="text" id="name" name="name" v-model="newRecipe.name" required>

            <div>

            <label for="prep-time-hours">Prep Time:</label>
            <input type="number" id="prep-time-hours" name="prep-time-hours"
                min="0" placeholder="Hours" v-model.number="prepTimeHours">
            <input type="number" id="prep-time-minutes" name="prep-time-minutes"
                min="0" max="59" placeholder="Minutes" v-model.number="prepTimeMinutes">

            </div>

            <div>
            <label for="cook-time-hours">Cook Time:</label>
            <input type="number" id="cook-time-hours" name="cook-time-hours"
                min="0" placeholder="Hours" v-model.number="cookTimeHours">
            <input type="number" id="cook-time-minutes" name="cook-time-minutes"
                min="0" max="59" placeholder="Minutes" v-model.number="cookTimeMinutes">
            </div>


            <label for="description">Description: </label>
            <textarea id="description" name="description" v-model="newRecipe.description"></textarea>

            <label for="servings">Servings: </label>
            <input type="number" id="servings" name="servings" min="0" v-model="newRecipe.servings">


            <!-- <label for="ingredients">Ingredients: </label> -->
             <h3>Ingredients:</h3>

            <div v-for="(ingredient, index) in newRecipe.ingredients" :key="index">
                <label for="ingredient">Ingredient: </label>
                
                <AutoComplete v-model="ingredient.name" :suggestions="filteredIngredients" 
                @complete="searchIngredients" dropdown />


                <label for="quantity">Quantity: </label>
                <input type="number" name="quantity" v-model="ingredient.quantity" required>
                

                <label for="unit">Unit: </label>
                <select id="unit" name="unit" v-model="ingredient.unit" required>
                    <option v-for="option in unitOptions" :key="option" :value="option">{{ option }}</option>
                </select>

                <button type="button" v-on:click="removeIngredient(index)">Remove Ingredient</button>
            </div>

            <button type="button" v-on:click="addIngredient">Add Ingredient</button>

            <label for="instructions">Instructions: </label>
            <textarea id="instructions" name="instructions" v-model="newRecipe.instructions" required></textarea>

            <button type="submit">Submit Recipe</button>


        </form>

    </div>
</template>

<script>
import AutoComplete from 'primevue/autocomplete';
import RecipeService from '../services/RecipeService.js'
import IngredientService from '../services/IngredientService.js'

export default {

    components: {
        AutoComplete
    },

    props: {
        recipe: {
            type: Object,
            required: true,
            default: () => {
                return {
                    id: 0,
                    name: '',
                    prepTime: '', 
                    cookTime: '', 
                    description: '',
                    servings: '',
                    ingredients: [],
                    instructions: '',
                }
            }
        }
    },


    data(){
        return {
            newRecipe: {...this.recipe},
            ingredientOptions: ['Tomato', 'Onion', 'Garlic', 'Salt', 'Pepper', 'Olive Oil'], //TEMP -> API CALL
            filteredIngredients: [],
            unitOptions: ['tsp', 'tbsp', 'cup', 'oz', 'lb', 'g', 'kg'], //TEMP -> API CALL
            prepTimeHours: 0,
            prepTimeMinutes: 0,
            cookTimeHours: 0,
            cookTimeMinutes: 0
        };

    },

    // mounted() {
    //     // IngredientService.getIngredients().then(response => {
    //     //     this.ingredients = response.data
    //     // })
    // },

    methods: {

        addIngredient() {
            this.newRecipe.ingredients.push({ name: '', quantity: 0, unit: '' });
        },

        removeIngredient(index) {
            this.newRecipe.ingredients.splice(index, 1);
        },

        convertToMinutes(hours, minutes){
            return (hours*60) + minutes;
        },

        searchIngredients(event) {
            this.filteredIngredients = this.ingredientOptions.filter((ingredient) => {
                return ingredient.toLowerCase().includes(event.query.toLowerCase());
            });
        },

        checkForDuplicateIngredients(){
            

        },





        submitForm() {
            // RecipeService.submitRecipe(this.recipe).then(response => {
            //     this.$router.push({ name: 'RecipeList' })
            // })
            
            this.newRecipe.prepTime = this.convertToMinutes(this.prepTimeHours, this.prepTimeMinutes);
            this.newRecipe.cookTime = this.convertToMinutes(this.cookTimeHours, this.cookTimeMinutes);
            //check ingredients for duplicates and give error (try catch? something like that?)
            //if throw error, don't submit form
            
            console.log(this.newIngredients);
            console.log(this.newRecipe);
            
            this.newRecipe = {
                    id: 0,
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

        }
    }

}

</script>


<style scoped>
textarea {
    resize: none;
  }
</style>


