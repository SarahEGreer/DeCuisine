<template>
    <div>
        <h1>{{ recipe.name }}</h1>
    </div>
</template>

<script>

export default{
    props: {
        recipe: { type: Object, required: true}
    }
}
</script>