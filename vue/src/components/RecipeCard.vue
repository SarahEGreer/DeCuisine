<template>
    <div>
        <h1>{{ recipe.name }}</h1>
        <span>Prep time: {{ recipe.prepTime }}</span>
        <span>Cook time: {{ recipe.cookTime }}</span>
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
    created() {
        console.log("this is from the component" + this.recipe);
    }
}
</script>