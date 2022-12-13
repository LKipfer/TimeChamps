<script setup>
const links = [
  {
    path: '/',
    label: 'Daily Timesheet',
  },
  {
    path: '/admin',
    label: 'Admin Dashboard',
  }
]
</script>

<template>
  <nav>
    <router-link v-for="link in links" :key="link.label" :to="link.path" class="link" exact>{{
      link.label
    }}</router-link>
  </nav>
</template>

<style scoped>
nav {
  border-bottom: 1px solid #ccc;
  display: flex;
}

.link {
  display: block;
  padding: 10px 20px;
  font-weight: bold;
  text-decoration: none;
  color: #000000;
}

.router-link-active {
  background-color: rgba(0, 0, 255, 0.05);
  color: rgba(0, 0, 255, 1);
}
</style>
