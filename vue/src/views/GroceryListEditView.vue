<template>
    <div class="grocery-list-form-container">
        <h2>Edit Grocery List</h2>
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

.page-title {
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 20px;
    text-align: left;
    /* Align text to the left to match Recipe Details */
}
</style>