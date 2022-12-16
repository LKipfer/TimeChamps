<script setup lang="ts">
import Navigation from './MainNavigation.vue';
import { computed } from 'vue';
import { useStore } from 'vuex';
const store = useStore();
const isAuthenticated = computed(() => store.state.auth.status.loggedIn);
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
