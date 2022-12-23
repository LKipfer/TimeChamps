<script setup lang="ts">
import { ref } from "vue";
import { computed } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";

const router = useRouter();
const store = useAuthStore();

const username = ref("");
const password = ref("");

computed(() => store.loggedIn);

function onSubmit(): void {
  const user = {
    username: username.value,
    password: password.value,
  };
  store.login(user).then(() => router.push("/"));
}
</script>

<template>
  <div class="surface-card p-4 shadow-2 border-round w-full lg:w-6">
    <div class="text-center mb-5">
      <div class="text-900 text-3xl font-medium mb-3">Welcome Back</div>
    </div>

    <div>
      <label for="username" class="block text-900 font-medium mb-2"
        >Username</label
      >
      <InputText
        id="username"
        v-model="username"
        type="text"
        class="w-full mb-3"
      />

      <label for="password" class="block text-900 font-medium mb-2"
        >Password</label
      >
      <InputText
        id="password"
        v-model="password"
        type="password"
        class="w-full mb-3"
      />

      <PButton
        label="Sign In"
        icon="pi pi-user"
        class="w-full"
        @click="onSubmit"
      ></PButton>
    </div>
  </div>
</template>

<style scoped></style>
