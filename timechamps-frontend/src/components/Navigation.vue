<script setup>
const links = [
{
    path: '/login',
    label: 'SignIn',
  },
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
  background-color: #24252A;
  align-items: center;
  justify-content: space-around;
  margin: auto;
}

.link{
  position: relative;
  text-decoration: none;
  display: block;
  color: #a0a0a0;
  padding: 10px 20px;
  font-weight: bold;
  font-size: 15px;
  letter-spacing: 0.2px;
}

.link:after{
  content:"";
  position: absolute;
  background-color:aliceblue;
  height: 2px;
  width: 0%;
  left: 0;
  bottom: 4px;
  transition: 0.3s;
}

.link:hover{
  color: #ffffff;
}
.link:hover:after{
  width: 100%;
}

.router-link-active {
  background-color: rgba(213, 213, 216, 0.05);
  color: rgb(229, 229, 241);
}


</style>
