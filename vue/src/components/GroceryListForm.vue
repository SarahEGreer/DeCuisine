<template>
    <div>
        <h1>GROCERY LIST FORM</h1>

        <form v-on:submit.prevent="saveForm">
            <!-- <label for="name">Grocery List Name: </label>
            <input type="text" id="name" name="name" v-model="newGroceryList.name" required> -->

            <!-- <label for="ingredients">Ingredients: </label> -->
            <h3>Ingredients:</h3>

            <div v-for="(item, index) in newGroceryList" :key="index">
                <!-- called item_name -->
                <div>
                    <label for="item-name">Item: </label>

                    <AutoComplete v-model="item.name" :suggestions="filteredIngredients" @complete="searchIngredients"
                        id="item-name" name="item-name" dropdown />
                    <!-- can I do required with autocomplete^ -->
                </div>
                <div>
                    <label for="amount">Amount: </label>
                    <input type="number" name="amount" min="0" v-model="item.amount" step=".01" required id="amount">
                </div>
                <div>
                    <label for="unit">Unit: </label>

                    <AutoComplete v-model="item.unit" :suggestions="filteredUnits" @complete="searchUnits" dropdown />
                </div>

                <button type="button" v-on:click="removeIngredient(index)">Remove Item</button>
                <!-- change to item -->
            </div>

            <button type="button" v-on:click="addIngredient">Add Ingredient</button>

            <button type="submit" id="submit-button">Save Changes</button>

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
            type: Array,
            required: true,
        },

    },


    data() {
        return {
            newGroceryList: [...this.groceryList],
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

    // Do we need a watch?
    watch: {
        groceryList: {
            handler(newVal) {
                this.newGroceryList = [...newVal]; // Ensure it's a new array
            },
            immediate: true
        }
    },


    methods: {

        addIngredient() {
            this.newGroceryList.push({ name: '', amount: 0, unit: '' });
        },

        removeIngredient(index) {
            this.newGroceryList.splice(index, 1);
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

        // hasDuplicates(array) {
        //     return array.length !== new Set(array).size;
        // },

        cancelForm() {
            this.$router.back();
        },

        resetForm() {
            this.newGroceryList = [];
        },

        saveForm() {
            GroceryService.updateGroceryList(this.newGroceryList).then(response => {
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

#amount {
    display: inline-block;
    width: 50%;
}

#submit-button {
    margin-bottom: 5em;
}
</style>
