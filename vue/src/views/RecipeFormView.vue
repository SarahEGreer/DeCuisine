<template>
    
        <recipe-form v-bind:recipe="recipe" v-bind:isEdit="isEdit" />
   
</template>


<script>
import RecipeForm from '../components/RecipeForm.vue'
import RecipeService from '../services/RecipeService.js'

export default {
    components: {
        RecipeForm
    },
    data() {
        return {

            // recipe: {},

            recipe: {
                photoUrl: '',
                recipeId: 0,
                name: '',
                prepTime: '',
                cookTime: '',
                description: '',
                servings: '',
                ingredients: [],
                instructions: ''
            },
            isEdit: false
        }
    },
    methods: {
        getRecipeDetails(recipeId) {
            RecipeService.getRecipeDetails(recipeId)
                .then((response) => {
                    this.recipe = response.data;
                    // console.log(response.data);
                    this.recipe = this.recipe.recipe;
                    this.isEdit = true;
                })

        }
    },
    created() {
        if (this.$route.params.recipeId) {
            this.getRecipeDetails(this.$route.params.recipeId);
            // } else {
            //     this.isEdit = false;
            //     this.recipe = {
            //         recipeId: 0,
            //         name: '',
            //         prepTime: '',
            //         cookTime: '',
            //         description: '',
            //         servings: '',
            //         ingredients: [],
            //         instructions: ''
            //     }
        }
    }
}

//STEP 1: created() using if statement, find out whether the current route has a recipeId parameter
//STEP 2: if it does, call the RecipeService.getRecipeDetails method to get the recipe details
//STEP 3: if the recipeId parameter is present, set the recipe data to the response data
//STEP 4: If getRecipe is called, include a method to change "submit recipe" to "edit recipe"
</script>

