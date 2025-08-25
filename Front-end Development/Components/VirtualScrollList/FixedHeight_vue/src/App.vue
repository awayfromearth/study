<script setup>
import { ref } from 'vue'
import VirtualScrollList from './components/FixedHeightVirtualScrollList.vue'

const data = ref([])
for (let i = 0; i < 10000; i++) {
  data.value.push({ id: i, value: i })
}
</script>

<template>
  <div style="width: 100vw; height: 100vh">
    <VirtualScrollList :list="data" :single-size="100" />
  </div>
</template>

<style>

</style>
