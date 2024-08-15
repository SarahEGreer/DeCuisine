<template>

    <div class="recipe-list-container">
        <div class="title-container">
            <h1 class="page-title">Recipe List</h1>
        </div>
        <input type="text" v-model="searchQuery" placeholder="Search for a recipe..." class="recipe-search-bar" />
        <div v-if="filteredRecipes.length" class="recipe-grid">
            <recipe-thumbnail v-for="recipe in filteredRecipes" :key="recipe.recipeId" :recipe="recipe" />
        </div>
        <div v-else>
            <p>No recipes available.</p>
        </div>
    </div>
</template>

<script>
import RecipeService from '../services/RecipeService';
import RecipeThumbnail from '../components/RecipeThumbnail.vue';

export default {
    components: {
        RecipeThumbnail,
    },
    data() {
        return {
            recipes: [],
            searchQuery: '',
        };
    },
    computed: {
        filteredRecipes() {
            return this.recipes.filter(recipe =>
                recipe.name.toLowerCase().includes(this.searchQuery.toLowerCase())
            );
        }
    },
    created() {
        RecipeService.getRecipes()
            .then(response => {
                this.recipes = response.data;
            })
            .catch(error => {
                console.error('Error fetching recipes:', error);
            });
    },
};

</script>

<style scoped>
.recipe-list-container {
    max-width: 1200px;
    margin: 0 auto;
    padding-bottom: 20px;
}

.recipe-search-bar {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
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