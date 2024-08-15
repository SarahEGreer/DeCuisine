import { createRouter as createRouter, createWebHistory } from "vue-router";
import { useStore } from "vuex";

// Import components
import AboutView from "../views/AboutView.vue";
import LoginView from "../views/LoginView.vue";
import LogoutView from "../views/LogoutView.vue";
import RegisterView from "../views/RegisterView.vue";
import RecipeFormView from "../views/RecipeFormView.vue";
import RecipeListView from "../views/RecipeListView.vue";
import RecipeDetailsView from "../views/RecipeDetailsView.vue";
import MealPlanFormView from "../views/MealPlanFormView.vue";
import GroceryListView from "../views/GroceryListView.vue";
import MealplanSchedulerView from "../views/MealplanSchedulerView.vue";
import MealplanDetailsView from "../views/MealplanDetailsView.vue";

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: "/",
    name: "about",
    component: AboutView,
    meta: {
      requiresAuth: false,
    },
  },

  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "/recipes/new",
    name: "recipe-form",
    component: RecipeFormView,
    meta: {
      requiresAuth: true, //Set to true when authentication is implemented
    },
  },
  {
    path: "/recipes/list",
    name: "recipe-list",
    // standardize naming conventions
    component: RecipeListView,
    meta: {
      requiresAuth: true, //Set to true when authentication is implemented
    },
  },
  {
    path: "/recipes/:recipeId",
    name: "RecipeDetailsView",
    component: RecipeDetailsView,
    meta: {
      requiresAuth: true, //Set to true when authentication is implemented
    },
  },

  {
    path: "/recipes/:recipeId/edit",
    name: "recipe-edit",
    component: RecipeFormView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/mealplans/new",
    name: "mealplan-form",
    component: MealPlanFormView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/mealplans/:mealplanId",
    name: "mealplan-details",
    component: MealplanDetailsView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/mealplans/:mealplanId/edit",
    name: "mealplan-edit",
    component: MealPlanFormView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/mealplans/scheduler",
    name: "mealplan-scheduler",
    component: MealplanSchedulerView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/grocerylist",
    name: "grocery-list",
    component: () => import("../views/GroceryListView.vue"),
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/grocerylist/edit",
    name: "grocery-list-edit",
    component: () => import("../views/GroceryListEditView.vue"),
    meta: {
      requiresAuth: true,
    },
  },
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

router.beforeEach((to) => {
  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === "") {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
