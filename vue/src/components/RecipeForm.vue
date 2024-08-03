<template>
    <div>

        <form v-on:submit.prevent="submitForm">
            <label for="name">Recipe Name: </label>
            <input type="text" id="name" name="name" v-model="newRecipe.name" required>

            
            <div>

            <label for="prep-time-hours">Prep Time:</label>
            <input type="number" id="prep-time-hours" name="prep-time-hours"
                min="0" placeholder="Hours" >
            <input type="number" id="prep-time-minutes" name="prep-time-minutes"
                min="0" max="59" placeholder="Minutes">

            </div>

            <div>
            <label for="cook-time-hours">Cook Time:</label>
            <input type="number" id="cook-time-hours" name="cook-time-hours"
                min="0" placeholder="Hours" >
            <input type="number" id="cook-time-minutes" name="cook-time-minutes"
                min="0" max="59" placeholder="Minutes" >

                <!-- upon !SUBMIT! call converter to take in time-hours and time minutes, model the return to time object -->

                <!-- call converter to take in time-hours and time minutes, model the return to time object -->
                

                <!-- make into multiparam hour minute object pushed to new recipe as '....time'
                 convert hours to minutes and add to minutes then push out as time in minutes  
                take in two above inputs and create a new v-model
                -->

            </div>



                <!-- <label for="prep-time">Prep Time: </label>
            <input type="time" id="prep-time" name="prep-time" v-model="newRecipe.prepTime"> -->

            <!-- <label for="cook-time">Cook Time: </label>
            <input type="time" id="cook-time" name="cook-time" v-model="newRecipe.cookTime"> -->

            <!-- <label for="cook-time">Cook Time:</label>
            <input type="number" id="cook-time" name="cook-time" min="1"
                required><select id="cook-time-unit" name="cook-time-unit">
                <option value="minutes">Minutes</option>
                <option value="hours">Hours</option>
            </select> -->



            <label for="description">Description: </label>
            <textarea id="description" name="description" v-model="newRecipe.description"></textarea>

            <label for="servings">Servings: </label>
            <input type="number" id="servings" name="servings" v-model="newRecipe.servings">


            <label for="ingredients">Ingredients: </label>

            <div v-for="(ingredient, index) in newRecipe.ingredients" :key="index">
                <label for="ingredient">Ingredient: </label>
                <select id="ingredient" name="ingredient" v-model="ingredient.name" required>
                    <option v-for="option in ingredientOptions" :key="option" :value="option">{{ option }}</option>
                </select>

                <!-- 
                     TODO auto complete for ingredient name  
                     TODO make new ingredient name button that adds nonexistent ingredient
                     LOGIC for POST:
                     - UPON SUBMIT, first check if new ingredient name has been added
                        - IF new ingredient name has been added, POST new ingredient name to ingredient table
                        - THEN POST new recipe with new ingredient name
                     -->


                <label for="quantity">Quantity: </label>
                <input type="number" id="quantity" name="quantity" v-model="ingredient.quantity" required>

                <label for="unit">Unit: </label>
                <select id="unit" name="unit" v-model="ingredient.unit" required>
                    <option v-for="option in unitOptions" :key="option" :value="option">{{ option }}</option>
                </select>

                <button type="button" v-on:click="removeIngredient(index)">Remove Ingredient</button>
            </div>

            <button type="button" v-on:click="addIngredient">Add Ingredient</button>

            <!-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            
            For loop that generates a new ingredient builder for each index in ingredient array
            v-for loop that iterates through newRecipe key=index (length plus 1)

            Ingredient name
            select v-model ingredient.name 
            TEMP for testing purposes
            option v-for option in ingredientOptions TODO key option value option 

            Quantity input
            input type = number v-model ingredient.quantity 
            
            Unit selector
            select v-model ingredient.unit
            option v-for option in unitOptions TODO key unit value unit            
            
            
            -->

            <label for="instructions">Instructions: </label>
            <textarea id="instructions" name="instructions" v-model="newRecipe.instructions" required></textarea>

            <button type="submit">Submit Recipe</button>


        </form>

    </div>
</template>

<script>
// import RecipeService from '../services/RecipeService.js'
// import IngredientService from '../services/IngredientService.js'


let cookTime = 0; //UNUTILIZED VARIABLE for use in conversion at submit time
let prepTime = 0; //UNUTILIZED VARIABLE


export default {

    props: {
        recipe: {
            type: Object,
            required: true,
            default: () => {
                return {
                    id: 0,
                    name: '',
                    prepTime: '', //CONVERT TO MINUTES?
                    cookTime: '', //CONVERT TO MINUTES?
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
            unitOptions: ['tsp', 'tbsp', 'cup', 'oz', 'lb', 'g', 'kg'], //TEMP -> API CALL
        };

    },
    // data() {
    //     return {
    //         newRecipe: {
    //             // id: this.recipe.id,
    //             name: this.recipe.name,
    //             prepTime: this.recipe.prepTime,
    //             cookTime: this.recipe.cookTime,
    //             description: this.recipe.description,
    //             servings: this.recipe.servings,
    //             ingredients: this.recipe.ingredients,
    //             instructions: this.recipe.instructions
    //         },
    //         ingredients: []
    //     }
    // },

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
        

        updateCookTime(timeType, prepOrCook) { //UNUTILIZED FUNCTION for use in conversion at submit time
            if (prepOrCook === 'cook') {
                if (timeType === 'hours') {
                    this.newRecipe.cookTime += document.getElementById('cook-time-hours').value * 60;
                } else {
                    this.newRecipe.cookTime += document.getElementById('cook-time-minutes').value;
                }
            } else {
                if (timeType === 'hours') {
                this.newRecipe.prepTime += document.getElementById('prep-time-hours').value * 60;
                 } else {
                this.newRecipe.prepTime += document.getElementById('prep-time-minutes').value;
                }
            }
        },
    

        // submitForm() {
        //     // RecipeService.createRecipe(this.recipe).then(response => {
        //     //     this.$router.push({ name: 'RecipeList' })
        //     // })
        // }
    }

}


</script>




<style scoped>
textarea {
    resize: none;
  }
</style>