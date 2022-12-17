<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";

const router = useRouter();
const store = useAuthStore();

const email = ref("");
const password = ref("");

function onSubmit(): void {
  const payload = {
    email: email.value,
    password: password.value,
  };
  store.register(payload).then(() => router.push("/"));
}
</script>

<template>
  <div class="surface-card p-4 shadow-2 border-round w-full lg:w-6">
    <div class="text-center mb-5">
      <div class="text-900 text-3xl font-medium mb-3">Create new Account</div>
      <span class="text-600 font-medium line-height-3"
        >Already have an account?</span
      >
      <router-link
        to="/login"
        class="font-medium no-underline ml-2 text-blue-500 cursor-pointer"
        >Login!</router-link
      >
    </div>

    <div>
      <label for="email" class="block text-900 font-medium mb-2">Email</label>
      <InputText id="email" v-model="email" type="text" class="w-full mb-3" />

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
        label="Create account"
        icon="pi pi-user"
        class="w-full"
        @click="onSubmit"
      ></PButton>
    </div>
  </div>
</template>

<style scoped></style>
