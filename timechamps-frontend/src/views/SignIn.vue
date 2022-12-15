<script setup lang="ts">
import { ref } from 'vue';
import { computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const router = useRouter();
const store = useStore();
const email = ref('');
const password = ref('');

computed(() => store.state.auth.status.loggedIn);

function onSubmit(): void {
  const payload = {
    email: email.value,
    password: password.value,
  };
  store.dispatch('auth/login', payload).then(() => {
    router.push('/');
  });
}
</script>

<template>
  <div class="surface-card p-4 shadow-2 border-round w-full lg:w-6">
    <div class="text-center mb-5">
      <div class="text-900 text-3xl font-medium mb-3">Welcome Back</div>
      <span class="text-600 font-medium line-height-3">Don't have an account?</span>
      <a class="font-medium no-underline ml-2 text-blue-500 cursor-pointer">Create today!</a>
    </div>

    <div>
      <label for="email" class="block text-900 font-medium mb-2">Email</label>
      <InputText id="email" v-model="email" type="text" class="w-full mb-3" />

      <label for="password" class="block text-900 font-medium mb-2">Password</label>
      <InputText id="password" v-model="password" type="password" class="w-full mb-3" />

      <Button label="Sign In" icon="pi pi-user" class="w-full" @click="onSubmit"></Button>
    </div>
  </div>
</template>

<style scoped></style>
