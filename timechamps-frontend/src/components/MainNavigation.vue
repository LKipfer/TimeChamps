<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";

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
</script>

<template>
  <MenuBar :model="items">
    <template #start>
      <img alt="logo" src="../assets/vue.svg" height="40" class="mr-2" />
    </template>
  </MenuBar>
</template>

<style scoped></style>
