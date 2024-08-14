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
                <h2 class="day-header">Day {{ day.day }}</h2>
                <div v-if="day.breakfastId" @click="viewRecipe(day.breakfastId)">
                    <h3>Breakfast</h3>
                    <div class="recipe-thumbnail">
                        <img :src="day.breakfastPhotoUrl" alt="Recipe Image" class="recipe-thumbnail-image">
                        <p class="recipe-thumbnail-name">{{ day.breakfastName }}</p>
                    </div>
                </div>
                <div v-if="day.lunchId" @click="viewRecipe(day.lunchId)">
                    <h3>Lunch</h3>
                    <div class="recipe-thumbnail">
                        <img :src="day.lunchPhotoUrl" alt="Recipe Image" class="recipe-thumbnail-image">
                        <p class="recipe-thumbnail-name">{{ day.lunchName }}</p>
                    </div>
                </div>
                <div v-if="day.dinnerId" @click="viewRecipe(day.dinnerId)">
                    <h3>Dinner</h3>
                    <div class="recipe-thumbnail">
                        <img :src="day.dinnerPhotoUrl" alt="Recipe Image" class="recipe-thumbnail-image">
                        <p class="recipe-thumbnail-name">{{ day.dinnerName }}</p>
                    </div>
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
        mealplanDuration() {
            return this.mealplan.schedule.length
        }
    },

    methods: {
        addToGroceryList(mealplanId) {
            GroceryService.addMealplanToGroceryList(mealplanId).then(response => {
                console.log("your meal has been added")
                this.$router.push('/grocerylist')
                // Decide where to push, later
            })
        },
        viewRecipe(recipeId) {
            this.$router.push(`/recipes/${recipeId}`)
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


<style scoped>
.mealplan-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 1em;
    padding: 1em;
    border: 1px solid black;
    border-radius: 5px;
    text-align: center;
}

.mealplan-header,
.mealplanSchedule {
    width: 100%;
    text-align: center;
}

.mealplanSchedule div {
    margin-top: 1em;
}

button {
    margin-top: 1em;
    padding: 0.5em 1em;
    border: none;
    background-color: #000;
    color: white;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
}

button:hover {
    background-color: #333;
}

h1,
h2,
h3,
p,
span {
    margin: 0.5em 0;
}

.day-header {
    margin-top: 6vh;
}

.recipe-thumbnail {
    margin: 0 auto;
    display: flex;
    align-items: center;
    cursor: pointer;
    padding: 10px;
    border-radius: 10px;
    background-color: #fdfdfd;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 15px;
    width: 50%;
    max-width: 400px;
    box-sizing: border-box;
    /* make this global */
}

.recipe-thumbnail-image {
    width: 100px;
    height: 100px;
    object-fit: cover;
    border-radius: 10px;
    margin-right: 15px;
}

.recipe-thumbnail-name {
    font-size: 18px;
    font-weight: bold;
    color: #333;
    margin: 0;
}
</style>