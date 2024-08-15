<!-- <template>
    <h1>Recipe Details</h1>
    <recipe-card v-bind:recipe="recipe" />
    <button @click="editRecipe">Edit Recipe</button>
    

</template> -->

<template>
    <div class="recipe-details-container">
        <h1 class="page-title">Recipe Details</h1>
        <div class="recipe-card-container">
            <recipe-card v-if="recipe" :recipe="recipe" />

        </div>
    </div>
</template>

<script>
import RecipeCard from '../components/RecipeCard.vue';
import RecipeService from '../services/RecipeService';


export default {
    components: {
        RecipeCard
    },
    data() {
        return {
            recipe: {}
        }
    },
    methods: {
        getCurrentRecipeDetails(recipeId) {
            console.log("get card details")
            RecipeService.getRecipeDetails(recipeId)
                .then((response => {
                    this.recipe = response.data;
                    this.recipe = this.recipe.recipe;
                    // API returns recipe nested within a recipe object. May update on backend if time allows
                    console.log("This is from the view" + this.recipe);
                }))
                .catch((error => {
                    this.handleErrorResponse(error);
                }));
        },
        handleErrorResponse(error) {
            if (error.response.status == 404) {
                this.$router.push({ name: 'NotFoundView' });
            } else {
                this.isLoading = false;
                this.$store.commit('SET_NOTIFICATION', `Could not get message data from server.`);
            }
        },
    },
    created() {
        this.getCurrentRecipeDetails(this.$route.params.recipeId);
    }
}

</script>

<style scoped>
.recipe-details-container {
    max-width: 900px;
    margin: 20px auto;
    padding: 20px;
}

.recipe-card-container {
    position: relative;
}

.edit-button {
    background-color: #e63946;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    position: absolute;
    bottom: 10px;
    right: 25px;
    width: 42%;
    z-index: 10;
}

.grocery-button {
    position: absolute;
    background-color: #27ae60;
    color: #fff;
    border: none;
    padding: 10px 0px;
    /* margin: 0 auto; */
    min-width: fit-content;
    width: 42%;
    border-radius: 5px;
    margin-top: 20px;
    cursor: pointer;
    z-index: 10;
    bottom: 10px;
    left: 25px;
}
</style>