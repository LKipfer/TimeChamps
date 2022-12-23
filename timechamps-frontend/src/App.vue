<!--
* @author Isabelle Bustamante


-->

<script setup lang="ts">
import MainLayout from "@/components/MainLayout.vue";
</script>

<template>
  <PToast />
  <MainLayout>
    <router-view></router-view>
  </MainLayout>
</template>

<style scoped></style>
