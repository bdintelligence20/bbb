<template>
  <div class="image-container" :style="{ 'padding-bottom': `${ratio}%` }">
    <div class="image-wrap">
      <img class="image" v-lazy="url" :alt="alt" />
    </div>
  </div>
</template>

<script>

export default {
  props: {
    url: '',
    alt: '',
    ratio: '',
  },
  data() {
    return {}
  },
}
</script>

<style scoped lang="scss">
.image-container {
  position: relative;
  width: 100%;

  .image-wrap {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }
}
</style>
