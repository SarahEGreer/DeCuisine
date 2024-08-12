<template>
    <h1>{{ recipe.recipeName }}</h1>
    <p>{{ recipe.recipeInstructions }}</p>
    <p>{{ recipe.prepTime }}</p>
</template>

<script>
export default {
    props: {
        recipe: {
            type: Object,
            required: true
        }
    }
}
</script>