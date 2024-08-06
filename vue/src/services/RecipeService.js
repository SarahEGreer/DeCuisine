import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

    getRecipes() {
        return http.get('/recipes')
    },

    submitRecipe(recipe) {
        return http.post('/recipes', recipe)
    },

    getRecipeDetails(recipeId){
        return http.get(`/recipes/details/${recipeId}`)
    }

}