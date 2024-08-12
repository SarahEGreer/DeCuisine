<template>
    <div class="recipe-thumbnail" v-on:click="$router.push({ name: 'RecipeDetailsView', params: { recipeId: recipe.recipeId } })">
        <img :src="recipe.photoUrl" alt="Recipe Image" class="recipe-thumbnail-image" />
        <h1 class="recipe-thumbnail-name">{{ recipe.name }}</h1>
        
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

<style scoped>
.recipe-thumbnail {
    display: flex;
    align-items: center;
    cursor: pointer;
    padding: 10px;
    border-radius: 10px;
    background-color: #fdfdfd;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 15px;
    width: 100%;
    box-sizing: border-box;
}

.recipe-thumbnail-image {
    width: 100px;
    height: 100px;
    object-fit: cover;
    border-radius: 10px;
    margin-right: 15px;
}

.recipe-thumbnail-name {
    font-size: 18px;
    font-weight: bold;
    color: #333;
    margin: 0;
}</style>