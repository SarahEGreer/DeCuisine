<!-- 
Meal plan name
description
schedule:
 ingredient adder (create new day)

 day 1
    breakfast     
    lunch
    dinner
 day 2
    breakfast
    lunch
    dinner
 (Add new day button)

View recipe of added stuff? (view recipe button) LATER FUNCTIONALITY 
FAVORITE RECIPES? (view favorite recipes button) LATER FUNCTIONALITY

 (submit new meal plan)

 //NOTE THIS IS DISTINCT FROM MEAL PLAN SCHEDULER AND SHOULD NOT INCLUDE START DATE



-->

<template>
   <mealplan-form v-bind:mealplan="mealplan" v-bind:isEdit="isEdit" />
</template>


<script>
import MealplanForm from '../components/MealplanForm.vue';
import RecipeService from '../services/RecipeService.js';
import MealplanService from '../services/MealplanService.js';
// create meal plan service

export default {
   components: {
      MealplanForm
   },
   data() {
      return {
         mealplan: {
            mealplanId: 0,
            name: '',
            description: '',
            userId: 0,
            schedule: [],
         },
         isEdit: false
      }
   },
   methods: {
      // getRecipeDetails(recipeId) {
      //    RecipeService.getRecipeDetails(recipeId)
      //       .then((response) => {
      //          this.recipe = response.data;
      //          this.recipe = this.recipe.recipe;
      //          this.isEdit = true;
      //       })

      // }
      getMealplanDetails(mealplanId) {
         MealplanService.getMealplanDetailsById(mealplanId)
            .then((response) => {
               this.mealplan = response.data;
               // console.log(response.data.mealplan);
               // this.recipe = this.recipe.recipe;
               this.isEdit = true;
            })

      }
   },
   created() {
      if (this.$route.params.mealplanId) {
         this.getMealplanDetails(this.$route.params.mealplanId);
         // } else {
         //     this.isEdit = false;
         //     this.recipe = {
         //         recipeId: 0,
         //         name: '',
         //         prepTime: '',
         //         cookTime: '',
         //         description: '',
         //         servings: '',
         //         ingredients: [],
         //         instructions: ''
         //     }
      }
   }
}

//STEP 1: created() using if statement, find out whether the current route has a recipeId parameter
//STEP 2: if it does, call the RecipeService.getRecipeDetails method to get the recipe details
//STEP 3: if the recipeId parameter is present, set the recipe data to the response data
//STEP 4: If getRecipe is called, include a method to change "submit recipe" to "edit recipe"
</script>
