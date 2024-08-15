<template>
    <div class="grocery-list-form-container">


        <form v-on:submit.prevent="saveForm">
            <!-- <label for="name">Grocery List Name: </label>
            <input type="text" id="name" name="name" v-model="newGroceryList.name" required> -->

            <!-- <label for="ingredients">Ingredients: </label> -->
            <!-- <h3 class="section-title">Ingredients:</h3> -->

            <div v-for="(item, index) in newGroceryList" :key="index">
                <div class="ingredient-flex-container">
                    <!-- called item_name -->
                    <div class="form-group">
                        <label for="item-name">Item: </label>

                        <AutoComplete v-model="item.name" :suggestions="filteredIngredients"
                            @complete="searchIngredients" id="item-name" name="item-name" dropdown />
                        <!-- can I do required with autocomplete^ -->
                    </div>
                    <div>
                        <label for="amount">Amount: </label>
                        <input type="number" name="amount" min="0" v-model="item.amount" step=".01" required
                            id="amount">
                    </div>
                    <div>
                        <label for="unit">Unit: </label>

                        <AutoComplete v-model="item.unit" :suggestions="filteredUnits" @complete="searchUnits"
                            dropdown />
                    </div>

                    <button type="button" v-on:click="removeIngredient(index)" class="remove-ingredient-btn">Remove
                        Item</button>

                </div>

                <!-- change to item -->
            </div>

            <button type="button" v-on:click="addIngredient" class="add-ingredient-btn">Add Item</button>

            <div class="form-buttons">
                <button type="submit" id="submit-button">Save Changes</button>

                <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
            </div>

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
                'grams', // grams
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
.grocery-list-form-container {
    max-width: 800px;
    margin: 0 auto;
    margin-bottom: 100px;
    background-color: #fdfdfd;
    padding: 30px;
    width: 90%;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.grocery-list-form h2 {
    text-align: center;
    margin-bottom: 20px;
    font-size: 2rem;
    font-weight: bold;
    color: #333;
}

/* .section-title {
    font-size: 20px;
    font-weight: bold;
    margin: 20px 0 10px;
    text-transform: uppercase;
    color: #333;
} */

.ingredient-group {
    display: flex;
    flex-direction: column;
    padding: 10px;
    background-color: #f4f4f4;
    border-radius: 5px;
    margin-bottom: 10px;
    position: relative;
}

.ingredient-flex-container {
    display: flex;
    gap: 20px;
    align-items: center;
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 20px;
}

.form-label {
    display: block;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 5px;
}

.form-input {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ddd;
    font-size: 14px;
}

.remove-ingredient-btn {
    background-color: #e74c3c;
    color: #fff;
    border: none;
    padding: 8px 10px;
    border-radius: 5px;
    cursor: pointer;
    font-size: x-small;
    margin-top: 10px;
}

.add-ingredient-btn {
    background-color: #27ae60;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 20px;
    display: block;
    width: fit-content;
    margin-left: auto;
    margin-right: auto;
}

.form-buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.submit-button {
    background-color: #000;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
}

.btn-cancel {
    background-color: #e74c3c;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
}
</style>
