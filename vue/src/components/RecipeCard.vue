<!-- <template>


    <div>
        <img :src="recipe.photoUrl" alt="">
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
</template> -->

<template>
    <div class="recipe-card">
        <div class="recipe-header">
            <img :src="recipe.photoUrl" alt="Recipe Image" class="recipe-image">
            <div class="recipe-info">
                <h1>{{ recipe.name }}</h1>
                <p class="prep-time">Prep time: {{ prepTimeHours }} hours, {{ prepTimeMinutes }} minutes</p>
                <p class="cook-time">Cook time: {{ cookTimeHours }} hours, {{ cookTimeMinutes }} minutes</p>
                <p class="description">{{ recipe.description }}</p>
                <span class="servings">Servings: {{ recipe.servings }}</span>
            </div>
        </div>

        <div class="recipe-content">
            <div class="recipe-ingredients">
                <h3>Ingredients</h3>
                <ul>
                    <li v-for="(ingredient, index) in recipe.ingredients" :key="index">
                        {{ ingredient.name }} {{ ingredient.amount }} {{ ingredient.unit }}
                    </li>
                </ul>
                <button class="grocery-button">Add Ingredients to Grocery List</button>
            </div>
            <div class="recipe-instructions">
                <h3>Instructions</h3>
                <p v-html="formattedInstructions"></p>
            </div>
        </div>
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
        },
        formattedInstructions() {
            return this.recipe.instructions.replace(/\n/g, '<br>');
        },
    },

    created() {
        console.log("this is from the component" + this.recipe);
    }
}
</script>

<style scoped>
.recipe-card {
    max-width: 900px;
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.recipe-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #e9e9e9;
    padding-bottom: 20px;
}

.recipe-image {
    width: 300px;
    height: auto;
    border-radius: 10px;
}

.recipe-info {
    flex-grow: 1;
    margin-left: 20px;
}

.recipe-info h1 {
    font-size: 32px;
    margin-bottom: 10px;
}

.recipe-content {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.recipe-ingredients,
.recipe-instructions {
    width: 48%;
}

.recipe-ingredients h3,
.recipe-instructions h3 {
    font-size: 20px;
    margin-bottom: 10px;
    text-transform: uppercase;
    color: #333;
    border-bottom: 2px solid #e9e9e9;
    padding-bottom: 5px;
}

.recipe-ingredients ul,
.recipe-instructions ol {
    list-style-position: inside;
    padding: 0;
    margin: 0;
}

.recipe-ingredients li,
.recipe-instructions li {
    margin-bottom: 10px;
    font-size: 16px;
    color: #555;
}

.grocery-button {
    background-color: #27ae60;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    margin-top: 20px;
    cursor: pointer;
}
</style>