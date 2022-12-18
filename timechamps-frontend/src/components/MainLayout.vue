<script setup lang="ts">
import Navigation from "./MainNavigation.vue";
import { computed } from "vue";
import { useAuthStore } from "@/stores/auth";

const store = useAuthStore();
const isAuthenticated = computed(() => store.loggedIn);
</script>

<template>
  <div>
    <Navigation v-if="isAuthenticated" />
    <main>
      <slot />
    </main>
  </div>
</template>

<style scoped>
main {
  padding: 50px;
  min-height: 100vh;
}
</style>
