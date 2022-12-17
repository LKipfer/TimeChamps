import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import { useAuthStore } from "@/stores/auth";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "HomeView",
      component: HomeView,
    },
    {
      path: "/login",
      name: "Login",
      component: LoginView,
    },
    {
      path: "/register",
      name: "Register",
      component: RegisterView,
    },
    {
      path: "/admin",
      name: "Admin",
      component: () => import("../views/AdminView.vue"),
    },
  ],
});

router.beforeEach(async (to) => {
  const authStore = useAuthStore();
  const isAuthenticated = authStore.loggedIn;
  if (!isAuthenticated && to.name !== "Login" && to.name !== "Register") {
    // redirect the user to the login page
    return { name: "Login" };
  }
});

export default router;
