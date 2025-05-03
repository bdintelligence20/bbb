<template>
  <div class="image-container">
    <img class="image" v-lazy="url" :alt="alt" />
  </div>
</template>

<script>

export default {
  props: {
    url: '',
    alt: '',
  },
  data() {
    return {}
  },
}
</script>

<style scoped lang="scss">
.image-container {
  position: relative;
  width: 100vw;
  height: 100vh;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}
</style>
