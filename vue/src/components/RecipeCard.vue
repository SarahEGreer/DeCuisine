<template>
    <div>
        <!-- <h1>{{ recipe.recipeName }}</h1>
        <span>Prep time: {{ recipe.prepTime}}</span>
        <span>Cook time: {{ recipe.cookTime }}</span>
        <p>{{ recipe.recipeDescription }}</p>
        <span>Servings: {{ recipe.servings }}</span>
        <ul>
            <li v-for="(ingredient, index) in recipe.ingredientsList" :key="index">name: {{ ingredient.ingredientName }} amount: {{ ingredient.amount }} unit: {{ ingredient.unitType }}</li>
        </ul>
        <p>{{ recipe.recipeInstructions }}</p> -->
        TEST
    </div>
</template>

<script>

// import RecipeService from '../services/RecipeService.js';

export default{
    props: {
        recipe:{ type: Object, required: true}
    },
    created(){
        console.log(this.recipe);
    }
}
</script>