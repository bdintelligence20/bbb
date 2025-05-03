<template>
  <div>
    <client-only>
      <div v-if="Object.keys(imageItem).length > 0" class="selling-point-slide">
        <mq-layout mq="lg+" class="image-container">
          <img v-if="imageItem?.imgUrl" :src="imageItem.imgUrl" alt @load="$emit('imageLoad')" />
          <slot />
        </mq-layout>
        <mq-layout :mq="['xs', 'sm', 'md']" class="image-container">
          <img v-if="imageItem?.imgUrl" :src="imageItem.mobileImg || imageItem.imgUrl" alt @load="$emit('imageLoad')" />
          <slot />
        </mq-layout>

        <VehicleModelBj30SellPointText :text-list="[imageItem]" />
      </div>
    </client-only>
  </div>
</template>

<script>
export default {
  props: {
    imageItem: {
      type: Object,
      default() {
        return {}
      },
    },
  },
  data() {
    return {
      activeIndex: 0,
    }
  },
  mounted() {},
  methods: {},
}
</script>

<style scoped lang="scss">
.selling-point-slide {
  img {
    width: 100%;
  }
  .title {
    font-weight: 500;
  }
  .subtitle {
    color: #ccc;
  }

  .swiper-slide {
    position: relative;
  }
  .image-container {
    position: relative;
  }

  @media screen and (max-width: 1200px) {
    .slide {
      position: relative;
    }
    .main-title {
      font-size: 16px;
    }
    .swiper-container {
      position: static;
    }
    .swiper-button {
      width: 18px;
      background-size: 100% auto;
    }
    .swiper-button-prev {
      left: -19px;
      background-image: url('~/assets/image/vehicleModel/bj30/左箭头.png');
    }
    .swiper-button-next {
      right: -19px;
      background-image: url('~/assets/image/vehicleModel/bj30/右箭头.png');
    }
    .swiper-button-prev.swiper-button-disabled,
    .swiper-button-next.swiper-button-disabled {
      opacity: 1;
    }
  }
}
</style>
