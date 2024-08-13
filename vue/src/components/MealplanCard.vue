<template>
    <div class="mealplan-card">
        <div class="mealplan-header">
            <h1>{{ mealplan.name }}</h1>
            <p>{{ mealplan.description }}</p>
            <span>Duration: {{ mealplanDuration }} days</span>
        </div>
        <div class="mealplanSchedule">
            <button @click="addToGroceryList($route.params.mealplanId)">Add all ingredients to grocery list</button>
            <div v-for="(day, index) in mealplan.schedule" :key="index">
                <h2>Day {{ day.day }}</h2>
                <div v-if="day.breakfastId">
                    <h3>Breakfast</h3>
                    <p>Recipe: {{ day.breakfastName }}</p>
                    <!-- <recipe-thumbnail :recipe="updateMealcard(day.breakfastId)" /> -->
                </div>
                <div v-if="day.lunchId">
                    <h3>Lunch</h3>
                    <p>Recipe: {{ day.lunchName }}</p>
                    <!-- <recipe-thumbnail :recipe="updateMealcard(day.breakfastId)" /> -->
                </div>
                <div v-if="day.dinnerId">
                    <h3>Dinner</h3>
                    <p>Recipe: {{ day.dinnerName }}</p>
                    <!-- <recipe-thumbnail :recipe="updateMealcard(day.breakfastId)" /> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import GroceryService from '../services/GroceryService';

// import RecipeThumbnail from '../components/RecipeThumbnail.vue'
// import RecipeService from '../services/RecipeService.js'
export default {
    components: {
        // RecipeThumbnail,
    },
    props: {
        mealplan: {
            type: Object,
            required: true
        }
    },

    computed: {
        meaplanDuration() {
            return this.mealplan.schedule.length
        }
    },

    methods: {
        addToGroceryList(mealplanId) {
            GroceryService.addMealplanToGroceryList(mealplanId).then(response => {
                console.log("your meal has been added")
                // Decide where to push, later
            })
        }
        // updateMealCard(recipeId) {
        //     let currentRecipe;
        //     RecipeService.getRecipeByRecipeId(recipeId).then(response => {
        //         currentRecipe = response.data;
        //     })
        //     return currentRecipe;
        // }
    }
}

</script>