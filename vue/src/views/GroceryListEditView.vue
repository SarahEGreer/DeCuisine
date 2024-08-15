<template>
    <div class="grocery-list-form-container">
        <h1 class="page-title">Edit Grocery List</h1>
        <grocery-list-form v-bind:groceryList="groceryList" />
    </div>
</template>


<script>
import GroceryListForm from '../components/GroceryListForm.vue';
import GroceryService from '../services/GroceryService.js';
// import RecipeService from '../services/RecipeService.js';

export default {
    components: {
        GroceryListForm
    },
    data() {
        return {

            groceryList: [],

        }
    },
    methods: {
        getGroceryList() {
            GroceryService.getGroceryList()
                .then((response) => {
                    this.groceryList = response.data;
                })
                .catch((error) => {
                    console.error("Error fetching grocery list:", error);
                });
        }
    },

    created() {
        this.getGroceryList();
    }
}

</script>

<style scoped>
.recipe-form-container {
    max-width: 900px;
    margin: 20px auto;
    padding: 20px;
}
</style>