<template>
    <grocery-list-card v-bind:groceryList="groceryList" />
    <button @click="editGroceryList">Edit Grocery List</button>
    

</template>

<script>
import GroceryListCard from '../components/GroceryListCard.vue';
import RecipeService from '../services/RecipeService';

export default {
    components: {
        GroceryListCard
    },
    data() {
        return {
            groceryList: {}
        }
    },

    methods: {
        // getGroceryList() {
        //     console.log("get card details")
        //     RecipeService.getRecipeDetails(recipeId)
        //         .then((response => {
        //             this.recipe = response.data;
        //             this.recipe = this.recipe.recipe;
        //             // API returns recipe nested within a recipe object. May update on backend if time allows
        //             console.log("This is from the view" + this.recipe);
        //         }))
        //         .catch((error => {
        //             this.handleErrorResponse(error);
        //         }));
        // },
        // handleErrorResponse(error) {
        //     if (error.response.status == 404) {
        //         this.$router.push({ name: 'NotFoundView' });
        //     } else {
        //         this.isLoading = false;
        //         this.$store.commit('SET_NOTIFICATION', `Could not get message data from server.`);
        //     }
        // },
        editGroceryList() {
            // take to edit recipe view 
            this.$router.push({ name: 'grocery-list-edit',});
        }
    }
}
    // created() {
    //     this.getGroceryList(this.$route.params.recipeId);
    // }
// }

</script>