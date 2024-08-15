<!-- <template>
    <div>

        <form v-on:submit.prevent="submitForm">
            <label for="photo">Recipe Photo Link</label>
            <input type="text" id="photo" name="photo" v-model="newRecipe.photoUrl">
            <label for="name">Recipe Name: </label>
            <input type="text" id="name" name="name" v-model="newRecipe.name" required>

            <div>

                <label for="prep-time-hours">Prep Time:</label>
                <input type="number" id="prep-time-hours" name="prep-time-hours" min="0" placeholder="Hours"
                    v-model.number="prepTimeHours">
                <input type="number" id="prep-time-minutes" name="prep-time-minutes" min="0" max="59"
                    placeholder="Minutes" v-model.number="prepTimeMinutes">

            </div>

            <div>
                <label for="cook-time-hours">Cook Time:</label>
                <input type="number" id="cook-time-hours" name="cook-time-hours" min="0" placeholder="Hours"
                    v-model.number="cookTimeHours">
                <input type="number" id="cook-time-minutes" name="cook-time-minutes" min="0" max="59"
                    placeholder="Minutes" v-model.number="cookTimeMinutes">
            </div>


            <label for="description">Description: </label>
            <textarea id="description" name="description" v-model="newRecipe.description"></textarea>

            <label for="servings">Servings: </label>
            <input type="number" id="servings" name="servings" min="0" v-model="newRecipe.servings">


            
            <h3>Ingredients:</h3>

            <div v-for="(ingredient, index) in newRecipe.ingredients" :key="index">
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

            <label for="instructions">Instructions: </label>
            <textarea id="instructions" name="instructions" v-model="newRecipe.instructions" required></textarea>

       

            <button type="submit">{{ isEdit ? 'Save Changes' : 'Submit Recipe' }}</button>

            <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>


        </form>

    </div>
</template> -->

<template>
    <h2 class="page-title">{{ isEdit ? 'Edit Recipe' : 'Create Recipe' }}</h2>
    <div class="form-container">
        <form v-on:submit.prevent="submitForm" class="recipe-form">
            <div class="form-group">
                <label for="photo" class="form-label">Recipe Photo Link</label>
                <input type="text" id="photo" name="photo" v-model="newRecipe.photoUrl" class="form-input">
                <a href="https://postimg.cc/" target="_blank" class="dark-link">Upload an image and grab a direct link!
                    (.jpg, .png)</a>
            </div>

            <div class="form-group">
                <label for="name" class="form-label">Recipe Name</label>
                <input type="text" id="name" name="name" v-model="newRecipe.name" required class="form-input">
            </div>

            <div class="form-group">
                <h4>Prep Time</h4>
                <div class="time-flex-container">
                    <label for="prep-time-hours" class="form-sublabel">Hours:</label>
                    <input type="number" id="prep-time-hours" name="prep-time-hours" min="0" placeholder="Hours"
                        v-model.number="prepTimeHours" class="form-input">
                    <label for="prep-time-minutes" class="form-sublabel">Minutes:</label>
                    <input type="number" id="prep-time-minutes" name="prep-time-minutes" min="0" max="59"
                        placeholder="Minutes" v-model.number="prepTimeMinutes" class="form-input">
                </div>
            </div>

            <div class="form-group">
                <h4>Cook Time</h4>
                <div class="time-flex-container">
                    <label for="cook-time-hours" class="form-sublabel">Hours:</label>
                    <input type="number" id="cook-time-hours" name="cook-time-hours" min="0" placeholder="Hours"
                        v-model.number="cookTimeHours" class="form-input">
                    <label for="cook-time-minutes" class="form-sublabel">Minutes:</label>
                    <input type="number" id="cook-time-minutes" name="cook-time-minutes" min="0" max="59"
                        placeholder="Minutes" v-model.number="cookTimeMinutes" class="form-input">
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="form-label">Description</label>
                <textarea id="description" name="description" v-model="newRecipe.description"
                    class="form-textarea"></textarea>
            </div>

            <div class="form-group">
                <label for="servings" class="form-label">Servings</label>
                <input type="number" id="servings" name="servings" min="0" v-model="newRecipe.servings"
                    class="form-input">
            </div>

            <h3 class="section-title">Ingredients</h3>

            <div v-for="(ingredient, index) in newRecipe.ingredients" :key="index" class="ingredient-group">
                <div class="ingredient-flex-container">
                    <div class="form-group">
                        <label for="ingredient" class="form-label">Ingredient</label>
                        <AutoComplete v-model="ingredient.name" :suggestions="filteredIngredients"
                            @complete="searchIngredients" dropdown class="form-input" />
                    </div>

                    <div class="form-group">
                        <label for="amount" class="form-label">Amount</label>
                        <input type="number" name="amount" min="0" v-model="ingredient.amount" step=".01" required
                            class="form-input">
                    </div>

                    <div class="form-group">
                        <label for="unit" class="form-label">Unit</label>
                        <AutoComplete v-model="ingredient.unit" :suggestions="filteredUnits" @complete="searchUnits"
                            dropdown class="form-input" />
                    </div>
                </div>

                <button type="button" v-on:click="removeIngredient(index)" class="remove-ingredient-button">Remove
                    Ingredient</button>
            </div>

            <button type="button" v-on:click="addIngredient" class="add-ingredient-button">Add Ingredient</button>

            <div class="form-group">
                <label for="instructions" class="form-label">Instructions</label>
                <textarea id="instructions" name="instructions" v-model="newRecipe.instructions" required
                    class="form-textarea"></textarea>
            </div>

            <div class="form-buttons">
                <button type="submit" class="submit-button">{{ isEdit ? 'Save Changes' : 'Submit Recipe' }}</button>
                <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
            </div>
        </form>
    </div>
</template>

<script>
import AutoComplete from 'primevue/autocomplete';
import RecipeService from '../services/RecipeService.js';
import IngredientService from '../services/IngredientService.js';

export default {

    components: {
        AutoComplete
    },

    props: {
        recipe: {
            type: Object,
            required: true,
            // default: () => {
            //     return {
            //         recipeId: 0,
            //         name: '',
            //         prepTime: '',
            //         cookTime: '',
            //         description: '',
            //         servings: '',
            //         ingredients: [],
            //         instructions: '',
            //     }
            // }
        },
        isEdit: {
            type: Boolean,
            default: false
        }
    },


    data() {
        return {
            newRecipe: {
                photoUrl: this.recipe.photoUrl,
                recipeId: this.recipe.recipeId,
                name: this.recipe.name,
                prepTime: this.recipe.prepTime,
                cookTime: this.recipe.cookTime,
                description: this.recipe.description,
                servings: this.recipe.servings,
                ingredients: this.recipe.ingredients,
                instructions: this.recipe.instructions,
            },
            ingredientOptions: ['Tomato', 'Onion', 'Garlic', 'Salt', 'Pepper', 'Olive Oil'], //TEMP -> API CALL
            filteredIngredients: [],
            unitOptions: [
                'tsp',  // teaspoon
                'teaspoon',
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
                'grams',
            ],
            filteredUnits: [],
            prepTimeHours: null,
            prepTimeMinutes: null,
            cookTimeHours: null,
            cookTimeMinutes: null
        };

    },

    watch: {
        recipe: {
            handler(newVal) {
                this.newRecipe = { ...newVal };
                // Populate hours and minutes fields based on prepTime and cookTime
                this.prepTimeHours = Math.floor(this.newRecipe.prepTime / 60);
                this.prepTimeMinutes = this.newRecipe.prepTime % 60;
                this.cookTimeHours = Math.floor(this.newRecipe.cookTime / 60);
                this.cookTimeMinutes = this.newRecipe.cookTime % 60;
            },
            immediate: true
        }
    },

    methods: {

        addIngredient() {
            this.newRecipe.ingredients.push({ name: '', amount: 0, unit: '' });
        },

        removeIngredient(index) {
            this.newRecipe.ingredients.splice(index, 1);
        },

        convertToMinutes(hours, minutes) {
            return (hours * 60) + minutes;
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
            this.newRecipe = {
                photoUrl: '',
                recipeId: 0,
                name: '',
                prepTime: '',
                cookTime: '',
                description: '',
                servings: '',
                ingredients: [],
                instructions: ''
            };
            this.newIngredients = [];
            this.prepTimeHours = null;
            this.prepTimeMinutes = null;
            this.cookTimeHours = null;
            this.cookTimeMinutes = null;
        },

        submitForm() {
            this.newRecipe.prepTime = this.convertToMinutes(this.prepTimeHours, this.prepTimeMinutes);
            this.newRecipe.cookTime = this.convertToMinutes(this.cookTimeHours, this.cookTimeMinutes);
            if (this.hasDuplicates(this.newRecipe.ingredients)) {
                console.log("your recipe has a duplicate");
                throw new Error('Your recipe must not have duplicate ingredients');
            }

            console.log(this.newRecipe);
            if (this.isEdit) {
                RecipeService.updateRecipe(this.newRecipe.recipeId, this.newRecipe).then(response => {
                    console.log("This is our update response data", response.status);
                    this.resetForm();
                    // this.$router.push({ name: 'recipe-list' });
                    this.$router.back();
                });
            } else {
                RecipeService.submitRecipe(this.newRecipe).then(response => {
                    console.log("This is our submit response data", response.status);
                    this.resetForm();
                    this.$router.push({ name: 'recipe-list' });


                });
            }
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
.form-group {
    margin-top: 20px;
    margin-bottom: 20px;
}

.form-label {
    display: block;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 5px;
}

.form-sublabel {
    display: block;
    font-size: 12px;
    font-weight: bold;
    margin-bottom: 5px;
}

.form-input,
.form-textarea {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ddd;
    font-size: 14px;
}

.form-textarea {
    height: 100px;
    resize: none;
}

.section-title {
    font-size: 20px;
    font-weight: bold;
    margin: 20px 0 10px;
    text-transform: uppercase;
    color: #333;
}

.ingredient-flex-container {
    display: flex;
    gap: 20px;
    align-items: center;
}

.ingredient-group {
    display: flex;
    flex-direction: column;
    padding: 10px;
    background-color: #f4f4f4;
    border-radius: 5px;
    margin-bottom: 10px;
    position: relative;
}

.remove-ingredient-button {
    background-color: #e74c3c;
    color: #fff;
    border: none;
    padding: 8px 10px;
    border-radius: 5px;
    cursor: pointer;
    align-self: flex-end;
    margin-top: 10px;
}

.add-ingredient-button {
    background-color: #27ae60;
    color: #fff;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 20px;
    margin-bottom: 30px;
    display: block;
}

.form-buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.submit-button {
    background-color: #000000;
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

.time-flex-container {
    display: flex;
    gap: 20px;
}

.time-flex-container .form-group {
    flex: 1;
}
</style>
