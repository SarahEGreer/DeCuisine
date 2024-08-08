<template>
    <h1>Recipe Details</h1>
    <recipe-card v-bind:recipe="recipe" />

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
                    // console.log("this is the response data" + response.data);
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
        console.log("it has been created")
        this.getCurrentRecipeDetails(this.$route.params.recipeId);
        // console.log("it has been created")

    }
}

</script>