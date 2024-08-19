<template>
  <div id="splash-page">
    <div class="overlay">
      <div class="login-form-container">
        <form v-on:submit.prevent="login" class="login-form">
          <h1>Please Sign In</h1>
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-input-group">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="user.username" required autofocus />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="user.password" required />
          </div>
          <button type="submit" class="btn-signin">Sign in</button>
          <p>
            <router-link class="register-link" v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
          </p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/mealplans/scheduler");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style scoped>
#splash-page {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-image: url('@/assets/pexels-photo-1640777.jpeg'); */
  background-image: url('@/assets/pexels-micheile-8366815.jpg');
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
}

.login-form-container {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  max-width: 500px;
  min-width: 400px;
  width: 100%;
}

h1 {
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}

.form-input-group {
  margin-bottom: 15px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #333;
}

input {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

.btn-signin {
  width: 100%;
  padding: 10px;
  background-color: #333;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 10px;
}

.btn-signin:hover {
  background-color: #000000;
}

.register-link {
  color: #003566;
}

.register-link:hover {
  color: #0077b6;
}
</style>
