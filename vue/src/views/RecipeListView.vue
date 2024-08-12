<template>
    <div class="recipe-list-container">
      <h1>Recipe List</h1>
      <div v-if="recipes.length" class="recipe-grid">
        <recipe-thumbnail v-for="recipe in recipes" :key="recipe.recipeId" :recipe="recipe" />
      </div>
      <div v-else>
        <p>No recipes available.</p>
      </div>
    </div>
  </template>

<script>
import RecipeService from '../services/RecipeService';
import RecipeThumbnail from '../components/RecipeThumbnail.vue'

export default {
    components: {
        RecipeThumbnail,
    },
    data() {
        return {
            recipes: [
            ],
        }
    },
    created() {
        RecipeService.getRecipes()
            .then(response => {
                // console.log('Recipes fetched:', response.data); // Log the fetched data
                this.recipes = response.data;
                console.log(response.data);
            })
            .catch(error => {
                console.error('Error fetching recipes:', error); // Log any error
            });
    }


}

</script>

<style scoped>
.recipe-list-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

.recipe-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;
}

.recipe-thumbnail {
    width: 100%; 
}

</style>