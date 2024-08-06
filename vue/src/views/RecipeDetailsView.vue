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
    methods: {
        getCurrentRecipeDetails(recipeId) {
            RecipeService.getRecipeDetails(recipeId)
                .then((response => {
                    this.recipe = response.data;
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
        created() {
            this.getCurrentRecipeDetails(this.$route.params.recipeId);
        }
    }
}

</script>