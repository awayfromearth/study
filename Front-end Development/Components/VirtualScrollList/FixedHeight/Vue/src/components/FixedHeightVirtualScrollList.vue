<script setup>
import { computed, onMounted, ref } from 'vue'

const { list, singleSize } = defineProps({
  list: {
    type: Array,
    default: () => []
  },
  singleSize: {
    type: Number,
    default: 100
  }
})

const totalHeight = computed(() => list.length * singleSize)

const container = ref(null)
const containerHeight = ref(0)
const start = ref(0)
const offset = ref(0)
const renderCount = computed(() => Math.ceil(containerHeight.value / singleSize))
const end = computed(() => start.value + renderCount.value)
const renderList = computed(() => list.slice(start.value, end.value + 1))
const transform = computed(() => `translate3d(0, ${offset.value}px, 0)`)

onMounted(() => {
  containerHeight.value = container.value.clientHeight
})

function handleScroll(e) {
  const scrollTop = e.target.scrollTop
  start.value = Math.floor(scrollTop / singleSize)
  offset.value = scrollTop - (scrollTop % singleSize)
}
</script>

<template>
  <div class="container" ref="container" @scroll="handleScroll">
    <div class="placeholder" :style="{ height: totalHeight + 'px' }"></div>
    <div class="list-wrapper" :style="{ transform }">
      <div
          class="card-item"
          v-for="(item, index) in renderList"
          :key="item.id"
          :style="{
            height: singleSize + 'px',
            lineHeight: singleSize - 20 + 'px',
            backgroundColor: `${index % 2 === 0 ? '#fff' : '#eee'}`
          }"
      >
        item - {{ item.value + 1 }}
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 50%;
  height: 60%;
  overflow: auto;
  position: relative;
}
.placeholder {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: -1;
}
.card-item {
  width: 110%;
  padding: 10px 30px;
  box-sizing: border-box;
  border-bottom: 1px solid #e1e1e1;
  color: #777;
}
</style>