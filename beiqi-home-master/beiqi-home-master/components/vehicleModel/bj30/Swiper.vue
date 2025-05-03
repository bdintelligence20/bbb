<template>
    <div v-if="swiperList.length > 0" v-swiper:vehicleSwiper="swiperOption" class="swiper">
      <div class="swiper-wrapper">
        <div v-for="(item, index) in swiperList" :key="index" class="swiper-slide">
          <img :src="baseUrl + item.imgUrl" alt="" class="image" @load="$emit('imageLoad')"/>
        </div>
      </div>
      <div slot="pagination" class="swiper-pagination swiper-pagination-bullets"></div>
    </div>
</template>

<script>
export default {
  props: {
    swiperList: {
      type: Array,
      default() {
        return []
      }
    },
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      swiperOption: {
        centeredSlides: true,
        loop: true,
        slidesPerView: 1.5,
        spaceBetween: 50,
        breakpoints: {
          640: {
            slidesPerView: 1.5,
            spaceBetween: 20,
          },
          980: {
            slidesPerView: 1.8,
            spaceBetween: 30,
          },
          1400: {
            slidesPerView: 2.2,
            spaceBetween: 50,
          },
        },
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
          renderBullet(index, className) {
            return `<div class="${className} swiper-pagination-bullet-custom"></div>`
          },
        },
      },
    }
  },
  mounted() {},
  methods: {},
}
</script>

<style scoped lang="scss">
.swiper {
  .swiper-wrapper {
    padding-bottom: 50px;
  }
  .swiper-slide {
    width: 1200px;
    transition-timing-function: linear;
    opacity: 0.5;
    img {
      width: 100%;
      max-height: 90vh;
      object-fit: cover;
    }
  }
  .swiper-slide.swiper-slide-active {
    opacity: 1;
  }
  @media only screen and (max-width: 1400px) {
    .swiper-slide {
      width: 770px;
    }
  }
  @media only screen and (max-width: 980px) {

    .swiper-slide {
      width: 371px;
    }
  }
  @media only screen and (max-height: 480px) {
    .swiper-slide {
      width: 471px;
    }
  }
  :deep .swiper-pagination-bullet-custom {
    width: 20px;
    height: 5px;
    border-radius: 0;
    background-color: #dcc5c5;
    opacity: 1;
    transition: 0.3s;
    border-radius: 10px;
    &.swiper-pagination-bullet-active {
      width: 50px;
      transition: 0.3s;
      background: #8c1e1e;
    }
  }
}
</style>
