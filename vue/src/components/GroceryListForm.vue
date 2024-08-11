<template>
    <div>

        <form v-on:submit.prevent="saveForm">
            <label for="name">Grocery List Name: </label>
            <input type="text" id="name" name="name" v-model="newGroceryList.name" required>

            <!-- <label for="ingredients">Ingredients: </label> -->
            <h3>Ingredients:</h3>

            <div v-for="(ingredient, index) in newGroceryList.ingredients" :key="index">
                <label for="ingredient">Ingredient: </label>

                <AutoComplete v-model="ingredient.name" :suggestions="filteredIngredients" @complete="searchIngredients"
                    dropdown />


                <label for="amount">Amount: </label>
                <input type="number" name="amount" min="0" v-model="ingredient.amount" step=".01" required>


                <label for="unit">Unit: </label>

                <AutoComplete v-model="ingredient.unit" :suggestions="filteredUnits" @complete="searchUnits" dropdown />

                <button type="button" v-on:click="removeIngredient(index)">Remove Ingredient</button>
            </div>

            <button type="button" v-on:click="addIngredient">Add Ingredient</button>

            <button type="submit">{{ isEdit ? 'Save Changes' : 'Save Grocery List' }}</button>

            <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>


        </form>

    </div>
</template>

<script>
import AutoComplete from 'primevue/autocomplete';
// import RecipeService from '../services/RecipeService.js';
import IngredientService from '../services/IngredientService.js';
import GroceryService from '../services/GroceryService.js';

export default {

    components: {
        AutoComplete
    },

    props: {
        groceryList: {
            type: Object,
            required: true,
        },
        
    },


    data() {
        return {
            newGroceryList: {
                groceryListId: this.groceryList.groceryListId,
                name: this.groceryList.name,
                ingredients: this.groceryList.ingredients,
            },
            ingredientOptions: ['Tomato', 'Onion', 'Garlic', 'Salt', 'Pepper', 'Olive Oil'], //TEMP -> API CALL
            filteredIngredients: [],
            unitOptions: [
                'tsp',  // teaspoon
                'tbsp', // tablespoon
                'cup',  // cup
                'oz',   // ounce
                'lb',   // pound
                'g',    // gram
                'kg',   // kilogram
                'ml',   // milliliter
                'l',    // liter
                'pinch',// pinch
                'dash', // dash
                'quart',// quart
                'pint', // pint
                'fl oz',// fluid ounce
                'gal',  // gallon
                'mg',   // milligram
                'cm',   // centimeter
                'inch', // inch
                'dozen', // dozen
                'whole', // whole
                'piece', // piece
                'slice', // slice
                'stick', // stick
                'bunch', // bunch
                'can',   // can
                'jar',   // jar
                'box',   // box
                'bag',   // bag
                'leaf',  // leaf
                'clove', // clove
                'head',  // head
                'stalk', // stalk
            ], 
            filteredUnits: [],
        };

    },



    methods: {

        addIngredient() {
            this.newGroceryList.ingredients.push({ name: '', amount: 0, unit: '' });
        },

        removeIngredient(index) {
            this.newGroceryList.ingredients.splice(index, 1);
        },

        searchIngredients(event) {
            this.filteredIngredients = this.ingredientOptions.filter((ingredient) => {
                return ingredient.toLowerCase().includes(event.query.toLowerCase());
            });
        },

        searchUnits(event) {
            this.filteredUnits = this.unitOptions.filter((unit) => {
                return unit.toLowerCase().includes(event.query.toLowerCase());
            });
        },

        hasDuplicates(array) {
            return array.length !== new Set(array).size;
        },

        cancelForm() {
            this.$router.back();
        }, 

        resetForm() {
            this.newGroceryList = {
                groceryListId: 0,
                name: '',
                ingredients: [],
            };
            this.newIngredients = [];
        },

        saveForm() {
                GroceryService.updateGroceryList(this.newGroceryList.groceryListId, this.newGroceryList).then(response => {
                    console.log("This is our update response data", response.status);
                    this.resetForm();
                    this.$router.push({ name: 'grocery-list' });
                });
            
        }
    },


    created() {
        IngredientService.getIngredients().then(response => {
            this.ingredientOptions = response.data;
        })
    },

}

</script>


<style scoped>
textarea {
    resize: none;
}
</style>
