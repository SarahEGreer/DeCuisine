<template>
    <h1 class="page-title">Meal Plan Details</h1>
    <div class=" mealplan-card">
        <div class="mealplan-header">
            <h2 class="mealplan-title">{{ mealplan.name }}</h2>
            <p>{{ mealplan.description }}</p>
            <span>Duration: {{ mealplanDuration }} days</span>
            <button class="add-btn" @click="addToGroceryList($route.params.mealplanId)">Add all ingredients to grocery
                list</button>
        </div>
        <div class="mealplan-schedule form-container">
            <div v-for="(day, index) in mealplan.schedule" :key="index">
                <h2 class="day-header">Day {{ index + 1 }}</h2>
                <!-- ^^^^^^^^^^^^^^^^^ band-aid dynamic fix^^ from day.day -->
                <div v-if="day.breakfastId" @click="viewRecipe(day.breakfastId)">
                    <h3>Breakfast</h3>
                    <div class="recipe-thumbnail ">
                        <img :src="day.breakfastPhotoUrl" alt="Recipe Image" class="recipe-thumbnail-image">
                        <p class="recipe-thumbnail-name">{{ day.breakfastName }}</p>
                    </div>
                </div>
                <div v-if="day.lunchId" @click="viewRecipe(day.lunchId)">
                    <h3>Lunch</h3>
                    <div class="recipe-thumbnail ">
                        <img :src="day.lunchPhotoUrl" alt="Recipe Image" class="recipe-thumbnail-image">
                        <p class="recipe-thumbnail-name">{{ day.lunchName }}</p>
                    </div>
                </div>
                <div v-if="day.dinnerId" @click="viewRecipe(day.dinnerId)">
                    <h3>Dinner</h3>
                    <div class="recipe-thumbnail ">
                        <img :src="day.dinnerPhotoUrl" alt="Recipe Image" class="recipe-thumbnail-image">
                        <p class="recipe-thumbnail-name">{{ day.dinnerName }}</p>
                    </div>
                </div>
            </div>
            <button @click="editMealplan($route.params.mealplanId)">Edit Mealplan</button>
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
        },
        editMealplan(mealplanId) {
            this.$router.push(`/mealplans/${mealplanId}/edit`)
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
/* .mealplanSchedule {
    width: 100%;
    text-align: center;
} */

.mealplan-schedule div {
    margin-top: 1em;
}

.mealplan-schedule {
    margin-top: 20px;
}

.mealplan-schedule,
.mealplan-header {
    text-align: center;
}

.mealplan-header {
    display: flex;
    flex-direction: column;
    gap: 8px;
    position: relative;
    width: 80%;
    max-width: 700px;
    background-color: #fdfdfd;
    padding: 20px;
    padding-top: 60px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin: 0 auto;
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

.mealplan-title {
    /* margin-top: 40px; */
    text-transform: uppercase;
    margin-bottom: 10px;
    font-size: 30px;
}


h2,
h3,
p,
span {
    margin: 0;
}

h3 {
    font-size: 25px;
}

/* .day-header {
    margin-top: 6vh;
} */

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
    width: 70%;
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

.add-btn {
    position: absolute;
    z-index: 10;
    top: 0px;
    right: 0px;
    border-radius: 2px 10px 2px 2px;
    margin: 0;
}

.day-header {
    font-size: 35px;
    margin: 30px;
    font-weight: 400;
}
</style>