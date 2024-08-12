<template>
  <div id="capstone-app">
    <header id="site-header"> DE CUISINE</header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'recipe-form' }">Create Recipe</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'recipe-list' }">View All Recipes</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'mealplan-form' }">Create Mealplan</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />

    <footer id="site-footer"> FOOTER </footer>

  </div>
</template>



<style>
body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 0;
  color: #555;
}

#site-header {
  background-color: #000;
  color: #fff;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  padding: 20px 0;
  text-transform: uppercase;
  letter-spacing: 2px;
  border-bottom: 2px solid #e9e9e9;
}

#nav {
  background-color: #000;
  padding: 10px;
}

#nav a {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  font-size: 16px;
}

#nav a:hover {
  text-decoration: underline;
}


h1, h2, h3, h4, h5, h6 {
  color: #333;
  font-weight: bold;
}


p, span, li {
  font-size: 16px;
  color: #555;
}


button {
  font-size: 16px;
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background-color: #000;
  color: #fff;
}

button:hover {
  background-color: #333;
}


form label {
  font-size: 16px;
  color: #333;
  font-weight: bold;
}

form input, form textarea, form select {
  font-size: 16px;
  color: #555;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 100%;
  margin-bottom: 15px;
}

#site-footer {
  text-align: center;
  padding: 20px 0;
  background-color: #000;
  color: #fff;
  font-size: 18px;
  position: fixed;
  bottom: 0;
  width: 100%;
  letter-spacing: 1px;
}
</style>