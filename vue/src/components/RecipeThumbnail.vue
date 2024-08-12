<template>
    <div v-on:click="$router.push({ name: 'RecipeDetailsView', params: { recipeId: recipe.recipeId } })">
        <h1>{{ recipe.name }}</h1>
        <img :src="recipe.photoUrl" alt="">
    </div>
</template>

<script>

export default {
    props: {
        recipe: { type: Object, required: true }
    },
    // created() {
    //     console.log('RecipeThumbnail created for:', this.recipe); // Log the creation of the component
    // },
};
</script>