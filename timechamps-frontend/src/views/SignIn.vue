<template>
  <div class="surface-card p-4 shadow-2 border-round w-full lg:w-6">
    <div class="text-center mb-5">
      <div class="text-900 text-3xl font-medium mb-3">Welcome Back</div>
      <span class="text-600 font-medium line-height-3">Don't have an account?</span>
      <a class="font-medium no-underline ml-2 text-blue-500 cursor-pointer">Create today!</a>
    </div>

    <div>
      <label for="email" class="block text-900 font-medium mb-2">Email</label>
      <InputText id="email" type="text" v-model="email" class="w-full mb-3" />

      <label for="password" class="block text-900 font-medium mb-2">Password</label>
      <InputText id="password" type="password" v-model="password" class="w-full mb-3" />

      <Button label="Sign In" icon="pi pi-user" class="w-full" v-on:click="onSubmit()"></Button>
    </div>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue';

export default {
  setup() {
    const email = ref('');
    const password = ref('');
    function onSubmit() {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          email: email.value,
          password: password.value
        })
      };
      fetch('/api/auth/authenticate', requestOptions)
          .then(async response => {
            const token = await response.text();
            localStorage.setItem('access_token', token);
          })
          .catch(error => {
            console.log(error);
          })
    }

    return { email, password, onSubmit }
  },
}
</script>

'Inspiration: https://www.youtube.com/watch?v=eeHqZeJ9Vqc'

<style scoped>
.surface-card{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  background: white;
  border-radius: 10px;
}

.text-center{
border-bottom: 1px solid silver;
}


</style>
