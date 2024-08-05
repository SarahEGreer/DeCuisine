<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'recipe-form'}">Create Recipe</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'recipe-list'}">View All Recipes</router-link>&nbsp;|&nbsp;
    </div>
    <router-view />
  </div>
</template>
