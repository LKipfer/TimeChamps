<script setup>
import Navigation from './Navigation.vue'
</script>

<template>
  <div>
    <Navigation />
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
