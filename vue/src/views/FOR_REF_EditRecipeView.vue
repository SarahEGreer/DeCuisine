<!-- FOR REFERENCE ONLY, DELETE BEFORE DEPLOYMENT -->

<!-- <template>
    <h1>Edit Recipe</h1>
    <recipe-form v-bind:recipe="recipe" />


</template>

<script>
import RecipeService from '../services/RecipeService.js';
import IngredientService from '../services/IngredientService.js';
import RecipeForm from '../components/RecipeForm.vue';

export default {
    components: {
        RecipeForm,
    },
    data() {
        return {
            recipe: {} //TODO get recipe from API          

        }
    },
    method: {
        getRecipe(recipeId) {
            RecipeService.getRecipe(this.$route.params.recipeId).then(response => {
                this.recipe = response.data;
            })
                .catch(error => {
                    if (error.response) {
                        if (error.response.status == 404) {
                            this.$router.push({ name: 'NotFoundView' });
                        } else {
                            this.$store.commit('SET_NOTIFICATION',
                                `Error getting message. Response received was "${error.response.statusText}".`);
                        }
                    } else if (error.request) {
                        this.$store.commit('SET_NOTIFICATION', `Error getting message. Server could not be reached.`);
                    } else {
                        this.$store.commit('SET_NOTIFICATION', `Error getting message. Request could not be created.`);
                    }
                });
        },
        created () {
            this.getRecipe(this.$route.params.recipeId);
        }
    }

} -->







<!-- </script> -->