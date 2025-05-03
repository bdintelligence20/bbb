<template>
  <div>
    <div v-if="imageList && imageList.length > 0" v-swiper:mySwiper="swiperOption" class="swiper">
      <div class="swiper-wrapper">
        <div v-for="(item, index) in imageList" :key="index" class="swiper-slide">
          <nuxt-link :to="localePath(item.linkUrl)">
            <img :alt="item.alt" :src="item.imgUrl" class="image"/>
          </nuxt-link>
          <div v-if="item.text" class="text">{{ item.text }}</div>
        </div>
      </div>
      <div slot="pagination" class="swiper-pagination swiper-pagination-bullets"></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    imageList: {
      type: Array,
    },
  },
  data() {
    return {
      swiperOption: {
        loop: true,
        autoplay: {
          delay: 3000,
          stopOnLastSlide: false,
          disableOnInteraction: false,
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
  created() {
  },
  mounted() {
  },
  methods: {},
}
</script>

<style lang="scss" scoped>
.swiper {
  width: 100%;

  .image {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .text {
    position: absolute;
    bottom: 3%;
    right: 2%;
    font-size: 24px;
    color: #fff;
  }

  ::v-deep .swiper-pagination-bullet-custom {
    width: 30px;
    height: 2px;
    border-radius: 0;
    background-color: #fff;
    opacity: 0.3;
    transition: 0.3s;

    &.swiper-pagination-bullet-active {
      opacity: 1;
      transition: 0.3s;
    }
  }
}

@media only screen and (max-width: 768px) {
  .swiper {
    .text {
      bottom: 8%;
      right: 2%;
      font-size: 14px;
    }
  }
}
</style>
