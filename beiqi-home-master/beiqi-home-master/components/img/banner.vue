<template>
  <div class="image-container">
    <div class="image-wrap" :style="{ 'padding-bottom': image.aspectRatio}">
      <img class="image"
           v-lazy="image.url ? baseUrl + image.url : ''"
           :alt="image.alt">
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      image: {
        type: Object,
        default: {
          default: () => ({
            url: '',
            aspectRatio: 1,
            alt: '',
          }),
        },
      },
    },
    data() {
      return {
        baseUrl: process.env.imgBaseUrl,
      };
    },
  }
</script>

<style scoped lang="scss">
  .image-wrap {
    position : relative;

    img {
      position : absolute;
      top      : 0;
      left     : 0;
      width    : 100%;
    }
  }
</style>
