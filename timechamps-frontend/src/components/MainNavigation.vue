<script setup lang="ts">
import { ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const store = useStore();
const router = useRouter();
const items = ref([
  {
    label: 'Timesheet',
    to: '/',
    icon: 'pi pi-fw pi-calendar',
  },
  {
    label: 'Admin',
    to: '/admin',
    icon: 'pi pi-fw pi-user',
  },
  {
    label: 'Logout',
    icon: 'pi pi-fw pi-power-off',
    command: () => logout(),
  },
]);
function logout(): void {
  store.dispatch('auth/logout').then(() => router.push('/login'));
}
</script>

<template>
  <Menubar :model="items">
    <template #start>
      <img alt="logo" src="../assets/vue.svg" height="40" class="mr-2" />
    </template>
  </Menubar>
</template>

<style scoped></style>
