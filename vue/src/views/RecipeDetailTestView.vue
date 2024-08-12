<template>
    <h1>test view</h1>
    <recipe-detail-test v-bind:recipe="recipe" />
</template>

<script>
import RecipeDetailTest from '../components/RecipeDetailTest.vue';
import RecipeService from '../services/RecipeService';

export default {
    components: {
        RecipeDetailTest
    },
    data() {
        return {
            recipe: {}
        }
    },
    methods: {
        getRecipeTest() {
            RecipeService.getRecipeDetails(1).then((response => {
                this.recipe = response.data;
                this.recipe = this.recipe.recipe;
                console.log('Recipe object:', this.recipe);
                if (this.recipe && this.recipe.recipe) {
                    console.log('Recipe name:', this.recipe.recipe.recipeName);
                } else {
                    console.log('Recipe name is undefined');
                }
            }));
            // RecipeService.getRecipes().then((response => {
            //     this.recipe = response.data;
            //     console.log(response.data);
            // }));
        }
    },
    created() {
        this.getRecipeTest();
    }
}

</script>