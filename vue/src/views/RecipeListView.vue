<template>
<h1>Recipe List</h1>
<div v-for="recipe in recipes" :key="recipe.id">
    <RecipeThumbnail :recipe="recipe" />
</div>
</template>

<script>
import RecipeService from '../services/RecipeService';
import RecipeThumbnail from '../components/RecipeThumbnail.vue'

export default{
    components: {
        RecipeThumbnail,
    },
    data(){
        return {
            recipes: [
                // {
                //     name: 'Pizza',
                //     description: 'it is pizza',
                // },
                // {
                //     name: 'Toast',
                //     description: 'put it in the toaster',
                // }, 
                // {
                //     name: 'gilled cheese',
                //     description: 'grill it'
                // }
            ],
        }
    },
    mounted(){
        RecipeService.getRecipes().then(response => {
            this.recipes = response.data;
            // add catch block 
        })
    }
}

</script>