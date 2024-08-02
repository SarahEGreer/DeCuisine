<template>
    <div>
        
        <form v-on:submit.prevent="submitForm">
            <label for="name">Recipe Name: </label>
            <input type="text" id="name" name="name" v-model="newRecipe.name" required>

            <label for="prep-time">Prep Time: </label>
            <input type="time" id="prep-time" name="prep-time" v-model="newRecipe.prepTime">

            <label for="cook-time">Cook Time: </label>
            <input type="time" id="cook-time" name="cook-time" v-model="newRecipe.cookTime">

            <label for="description">Description: </label>
            <textarea id="description" name="description" v-model="newRecipe.description"></textarea>

            <label for="servings">Servings: </label>
            <input type="number" id="servings" name="servings" v-model="newRecipe.servings">

            <label for="ingredients">Ingredients: </label>
            <input type="select" id="ingredients" name="ingredients" v-model="newRecipe.ingredients" required>

            <label for="instructions">Instructions: </label>
            <textarea id="instructions" name="instructions" v-model="newRecipe.instructions" required></textarea>

            <button type="submit">Submit Recipe</button>


        </form>

    </div>
</template>

<script>
// import RecipeService from '../services/RecipeService.js'
// import IngredientService from '../services/IngredientService.js'

export default {

    data() {
        return {
            newRecipe: {
                id: this.recipe.id,
                name: this.recipe.name,
                prepTime: this.recipe.prepTime,
                cookTime: this.recipe.cookTime,
                description: this.recipe.description,
                servings: this.recipe.servings,
                ingredients: this.recipe.ingredients,
                instructions: this.recipe.instructions
            },
            ingredients: []
        }
    },

    mounted() {
        // IngredientService.getIngredients().then(response => {
        //     this.ingredients = response.data
        // })
    },

    methods: {
        submitForm() {
            // RecipeService.createRecipe(this.recipe).then(response => {
            //     this.$router.push({ name: 'RecipeList' })
            // })
        }
    }

}


</script>




<style scoped></style>