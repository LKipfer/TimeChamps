/**
 * @author Tufan Basaran
 * Source Router Basics: https://www.youtube.com/watch?v=juocv4AtrHoI
 */


import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import { useAuthStore } from "@/stores/auth";
import { Role } from "@/types/role";

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
      path: "/admin",
      name: "Admin",
      meta: {
        roleRequired: Role.ADMIN,
      },
      component: () => import("../views/AdminView.vue"),
    },
    {
      path: "/unauthorized",
      name: "Unauthorized",
      component: () => import("../views/UnauthorizedView.vue"),
    },
  ],
});

router.beforeEach(async (to) => {
  const authStore = useAuthStore();
  const isAuthenticated = authStore.loggedIn;

  if (!isAuthenticated && to.name !== "Login") {
    // redirect the user to the login page
    return { name: "Login" };
  } else {
    //access control
    if (to.meta.roleRequired) {
      if (to.meta.roleRequired !== authStore.role) {
        return { name: "Unauthorized" };
      }
    }
  }
});

export default router;
