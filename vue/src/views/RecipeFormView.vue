<template>
    <h1>Create Recipe</h1>

    <recipe-form v-bind:recipe="recipe"/>

    


</template>



<script>
import RecipeForm from '../components/RecipeForm.vue'

export default {
    components: {
        RecipeForm
    },
    data() {
        return {
            recipe: {
                id: 0,
                name: '',
                prepTime: '',
                cookTime: '',
                description: '',
                servings: '',
                ingredients: [],
                instructions: ''
            }
        }
    }
}



</script>