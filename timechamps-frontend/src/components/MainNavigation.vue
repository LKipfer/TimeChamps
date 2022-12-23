<!--
* @author Tufan Basaran
* Source https://vuejs.org/guide/scaling-up/sfc.html
* Source https://vuejs.org/api/built-in-directives.html
* Source https://vuejs.org/guide/typescript/overview.html
*/-->

<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";
import { Role } from "@/types/role";

const store = useAuthStore();
const router = useRouter();
const items = ref([
  {
    label: "Timesheet",
    to: "/",
    icon: "pi pi-fw pi-calendar",
  },
  {
    label: "Admin",
    to: "/admin",
    icon: "pi pi-fw pi-user",
    visible: () => isAdmin(),
  },
  {
    label: "Logout",
    icon: "pi pi-fw pi-power-off",
    command: () => logout(),
  },
]);
function logout(): void {
  store.logout();
  router.push("/login");
}
function isAdmin(): boolean {
  return store.role === Role.ADMIN;
}
</script>

<template>
  <MenuBar :model="items">
    <template #start>
      <img alt="logo" src="../assets/vue.svg" height="40" class="mr-2" />
    </template>
  </MenuBar>
</template>

<style scoped></style>
