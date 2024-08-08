<template>
    <div>
        <h1>{{ recipe.name }}</h1>
        <span>Prep time: {{ prepTimeHours }} hours, {{ prepTimeMinutes }} minutes</span>
        <span>Cook time: {{ cookTimeHours }} hours, {{ cookTimeMinutes }} minutes</span>
        <p>{{ recipe.description }}</p>
        <span>Servings: {{ recipe.servings }}</span>
        <ul>
            <li v-for="(ingredient, index) in recipe.ingredients" :key="index">name: {{ ingredient.name }}
                amount: {{ ingredient.amount }} unit: {{ ingredient.unit }}</li>
        </ul>
        <p>{{ recipe.instructions }}</p>
    </div>
</template>

<script>

// import RecipeService from '../services/RecipeService.js';

export default {
    props: {
        recipe: {
            type: Object,
            required: true
        }
    },
    
    computed: {
        // Computed property to convert prep time from minutes to hours and minutes
        prepTimeHours() {
            return Math.floor(this.recipe.prepTime / 60);
        },
        prepTimeMinutes() {
            return this.recipe.prepTime % 60;
        },
        // Computed property to convert cook time from minutes to hours and minutes
        cookTimeHours() {
            return Math.floor(this.recipe.cookTime / 60);
        },
        cookTimeMinutes() {
            return this.recipe.cookTime % 60;
        }
    },

    created() {
        console.log("this is from the component" + this.recipe);
    }
}
</script>