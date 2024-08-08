<!-- <template>
    <h1>Recipe List</h1>
    <div v-for="recipe in recipes" :key="recipe.recipeId">
        <recipe-thumbnail :recipe="recipe" />
    </div>
</template> -->


<template>
    <h1>Recipe List</h1>
    <div v-if="recipes.length">
        <div v-for="recipe in recipes" :key="recipe.recipeId">
            <recipe-thumbnail :recipe="recipe" />
        </div>
    </div>
    <div v-else>
        <p>No recipes available.</p>
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
                // {
                //     name: 'Pizza',
                //     description: 'it is pizza',
                //     id: 1,
                // },
                // {
                //     name: 'Toast',
                //     description: 'put it in the toaster',
                //     id: 2,
                // },
                // {
                //     name: 'gilled cheese',
                //     description: 'grill it',
                //     id: 3,
                // }
            ],
        }
    },
    created() {
        RecipeService.getRecipes()
            .then(response => {
                // console.log('Recipes fetched:', response.data); // Log the fetched data
                this.recipes = response.data;
            })
            .catch(error => {
                console.error('Error fetching recipes:', error); // Log any error
            });
        // RecipeService.getRecipeDetails(1)
        //     .then(response => {
        //         console.log('Recipe details fetched:', response.data); // Log the fetched data
        //     })
        //     .catch(error => {
        //         console.error('Error fetching recipe details:', error); // Log any error
        //     });
    }


}

</script>