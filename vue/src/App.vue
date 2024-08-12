<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'recipe-form' }">Create Recipe</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'recipe-list' }">View All Recipes</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'mealplan-form' }">Create Mealplan</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
<style>
img {
  width: 500px;
}
</style>