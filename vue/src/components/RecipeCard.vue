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
            </div>
            <div class="recipe-instructions">
                <h3>Instructions</h3>
                <p v-html="formattedInstructions"></p>
            </div>
        </div>
        <div class="button-container">
            <button class="grocery-button" @click="addToGroceryList($route.params.recipeId)">Add Ingredients to
                Grocery List</button>
            <button @click="editRecipe" class="edit-button">Edit Recipe</button>

        </div>
    </div>
</template>

<script>

import GroceryService from '../services/GroceryService';


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
    methods: {
        editRecipe() {
            // take to edit recipe view 
            this.$router.push({ name: 'recipe-edit', params: { recipeId: this.recipe.recipeId } });
        },
        addToGroceryList(recipeId) {
            GroceryService.addRecipeToGroceryList(recipeId).then(response => {
                console.log("your meal has been added")
                this.$router.push('/grocerylist')
                // Decide where to push, later
            })
        }
    },

    created() {
        console.log("this is from the component" + this.recipe);
    }
}
</script>

<style scoped>
.recipe-card {
    max-width: 900px;
    min-height: 400px;
    margin: 20px auto;
    padding: 32px;
    padding-bottom: 50px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    justify-content: space-between;
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
    flex-grow: 1;
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

.recipe-instructions p {
    font-size: 16px;
    color: #555;
    line-height: 1.5;
}

.button-container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    gap: 45px;

}

.edit-button {
    background-color: #e63946;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
}

.grocery-button {
    background-color: #27ae60;
    color: #fff;
    border: none;
    padding: 10px 0px;
    /* margin: 0 auto; */
    min-width: fit-content;
    border-radius: 5px;
    width: 100%;

}
</style>