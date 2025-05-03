<template>
  <client-only>
    <div class="selling-point-slide">
      <div v-if="list.length > 1" class="slide">
        <div ref="swiper" v-swiper:swiper="swiperOptions" class="swiper" @slideChange="slideChange">
          <div class="swiper-wrapper">
            <template v-for="(item, index) in list">
              <div v-if="item.imgUrl" :key="index" class="swiper-slide">
                <mq-layout mq="lg+">
                  <img :src="item.imgUrl" alt @load="$emit('imageLoad')" />
                </mq-layout>
                <mq-layout :mq="['xs', 'sm', 'md']">
                  <img :src="item.mobileImg || item.imgUrl" alt @load="$emit('imageLoad')" />
                </mq-layout>

                <slot />
              </div>
            </template>
          </div>
          <mq-layout :mq="['xs', 'sm', 'md']">
            <div v-if="list.length > 1" class="swiper-button swiper-button-prev"></div>
            <div v-if="list.length > 1" class="swiper-button swiper-button-next"></div>
          </mq-layout>
        </div>
      </div>

      <VehicleModelBj30SellPointText :text-list="list" :active-index="activeIndex" @itemClick="itemClick" />
    </div>
  </client-only>
</template>

<script>
export default {
  props: {
    list: {
      type: Array,
      default() {
        return []
      },
    },
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      swiperOptions: {
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      },
      activeIndex: 0,
    }
  },
  mounted() {},
  methods: {
    slideChange() {
      this.activeIndex = this.swiper.realIndex
    },
    itemClick(index) {
      this.activeIndex = index
      this.swiper.slideTo(index)
    },
  },
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
