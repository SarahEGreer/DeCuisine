<template>
  <div id="capstone-app">
    <header id="site-header"> DE CUISINE
      <img src="@\assets\DE_CUISINE_LOGO_WHITE.png" alt="De Cuisine Logo" class="logo-image" />

    </header>
    <div id="nav">
      
      <router-link v-bind:to="{ name: 'mealplan-scheduler' }">MEAL PLANNER</router-link>
      <router-link v-bind:to="{ name: 'mealplan-form' }">CREATE MEAL PLAN</router-link>
      <router-link v-bind:to="{ name: 'recipe-list' }">RECIPES</router-link>
      <router-link v-bind:to="{ name: 'recipe-form' }">CREATE RECIPE</router-link>
      <router-link v-bind:to="{ name: 'grocery-list' }">GROCERY LIST</router-link>

      <div class="nav-right">
        <router-link v-bind:to="{ name: 'about' }">ABOUT</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">LOGOUT</router-link>
        <router-link v-else v-bind:to="{ name: 'login' }">LOGIN</router-link>
      </div>
    </div>
    <router-view />


    <footer> <footer-info /> </footer>
  </div>

</template>

<script>
import FooterInfo from './components/FooterInfo.vue'

export default {
  components: {
    FooterInfo
  }
}
</script>



<style>
:root {
  --fd-button-bg-color: pink;
}

html {
  color-scheme: light;
  min-height: 100%;
}

body {
  font-family: "League Spartan", sans-serif;
  margin: 0;
  padding: 0;
  color: #000;
  display: flex;
  flex-direction: column;
  min-height: 100%;
  background-color: #faf7f2;
}

#capstone-app {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

/* #site-header {
  background-color: #000;
  color: #fff;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  padding: 20px 0;
  text-transform: uppercase;
  letter-spacing: 2px;
  border-bottom: 2px solid #e9e9e9;
} */

#site-header {
  background-color: #000;
  text-align: center;
  padding: 20px 0;
  border-bottom: 2px solid #e9e9e9;
}

.logo-image {
  max-width: 300px;
  height: auto;
}

#nav {
  background-color: #000;
  padding: 0 0 0 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;  
}


.nav-items {
  display: flex;
  align-items: center;
  flex-grow: 1;
}

/* #nav a {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  font-size: 23px;
  margin-left: 18px;
   
} */

.nav-right {
  display: flex;
  margin-left: auto;
  align-items: center;
  padding: 0 20px 0 0;
}

.logout-link {
  margin-left: auto;
}

/* .dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #333;
  min-width: 160px;
  box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
  border-radius: 5px;
}

.dropdown-content a {
  color: #fff;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
  background-color: #575757;
}

.dropdown:hover .dropdown-content {
  display: block;
} */

/* #nav a {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  font-size: 23px;
  margin-left: 18px;
  padding: 5px 10px;
  transition: background-color 0.3s ease, color 0.3s ease;
} */

/* #nav a:hover {
  background-color: #fff;
  color: #000;
  text-decoration: none;
} */

#nav a {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  font-size: 23px;
  padding: 15px 10px; /* Adjusted padding to take up full height */
  transition: background-color 0.3s ease, color 0.3s ease;
  display: flex;
  align-items: center; /* Center text within the link */
  height: 100%; /* Ensure the link takes the full height of the nav bar */
}

#nav a:hover {
  background-color: #fff;
  color: #000;
  text-decoration: none;
  border-radius: 0; /* Remove border radius to make edges flush */
}

h1,
h2,
h3,
h4,
h5,
h6 {
  color: #333;
  font-weight: bold;
}


p,
span,
li {
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

.page-title {
  text-align: center;
  text-transform: uppercase;
  font-size: 32px;
  margin-top: 40px;
}

.recipe-card,
.form-container {
  margin: 0 auto;
  margin-bottom: 50px;
}

.form-container {
  width: 80%;
  max-width: 700px;
  background-color: #fdfdfd;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.grocery-checklist-form {
  max-width: 600px;
}

/* form input,
form textarea,
form select {
  font-size: 16px;
  color: #555;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 100%;
  margin-bottom: 15px;
} */

/* #site-footer {
  text-align: center;
  padding: 20px 0;
  background-color: #000;
  color: #fff;
  font-size: 18px;
  width: 100%;
  letter-spacing: 1px;
  margin-top: auto;
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 10;
} */

footer {
  justify-self: flex-end;
}

.fc .fc-prev-button,
.fc .fc-next-button {
  /* Specific styling for previous and next buttons */
  background-color: #27ae60;
  border-color: #ffffff;
  color: #fff;
}

.fc .fc-prev-button:hover, .fc .fc-next-button:hover {
  /* Specific styling for previous and next buttons on hover */
  background-color: #1b7440;
}

.fc-icon-chevron-left,.fc-icon-chevron-right {
  /* Specific styling for the chevron icons */
  color: #fff;
}
</style>