<template>
    <div>
        <h1>GROCERY LIST</h1>
        <ul>
            <li v-for="(ingredient, index) in groceryList.ingredients" :key="index">name: {{ ingredient.name }}
                amount: {{ ingredient.amount }} unit: {{ ingredient.unit }}</li> 
                <!-- continue making into GL specific to database -->
        </ul>
    </div>
</template>

<script>

// import RecipeService from '../services/RecipeService.js';

export default {
    props: {
        groceryList: {
            type: Object,
            required: true
        }
    },

    created() {
        console.log("this is from the component" + this.recipe);
    }
}
</script>