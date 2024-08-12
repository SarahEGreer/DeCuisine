<template>
    <div class="grocery-list-form-container">
        <h2>Edit Grocery List</h2>
        <grocery-list-form v-bind:groceryList="groceryList"/>
    </div>
</template>


<script>
import GroceryListForm from '../components/GroceryListForm.vue'
import RecipeService from '../services/RecipeService.js'

export default {
    components: {
        GroceryListForm
    },
    data() {
        return {

            groceryList: {
                ingredients: [],
            },
            
        }
    },
    // methods: {
    //     getGroceryList(recipeId) {
    //         GroceryService.getGroceryList
    //             .then((response) => {
    //                 this.groceryList = response.data;
    //                 this.groceryList = this.recipe.recipe;
                    
    //             })

    //     }
    // },
    // created() {
    //     if (this.$route.params.recipeId) {
    //         this.getGroceryList(this.$route.params.recipeId);
    //     }
    // }
}

</script>

<style scoped>
.recipe-form-container {
    max-width: 900px;
    margin: 20px auto;
    padding: 20px;
}

.page-title {
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 20px;
    text-align: left; /* Align text to the left to match Recipe Details */
}
</style>