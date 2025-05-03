<template>
  <div>
    <div v-if="imageList && imageList.length > 0" v-swiper:mySwiper="swiperOption" class="swiper" :style="{ height: imgHeight }" @touchstart="videoControl">
      <div class="swiper-wrapper">
        <div v-for="(item, index) in imageList" :key="index" class="swiper-slide">
          <!-- 外部链接或空字符串 -->
          <template v-if="item.linkUrl.includes('http') || !item.linkUrl">
            <a :href="item.linkUrl ? item.linkUrl : false" target="_blank">
              <HomePageAutoplayVideo v-if="item.videoUrl" ref="bannerVideo" :video-id="`Mp4Video${item.bannerId}`" :video-url="`${baseUrl}${item.videoUrl}`" />
              <img v-else class="image swiper-lazy" :data-src="item.imgUrl" :alt="item.alt" />
              <div class="swiper-lazy-preloader"></div>
            </a>
          </template>

          <!-- 内部链接 -->
          <template v-else>
            <nuxt-link :to="localePath(item.linkUrl, $i18n.locale)">
              <HomePageAutoplayVideo v-if="item.videoUrl" ref="bannerVideo" :video-id="`Mp4Video${item.bannerId}`" :video-url="`${baseUrl}${item.videoUrl}`" />
              <img v-else class="image swiper-lazy" :data-src="item.imgUrl" :alt="item.alt" />
              <div class="swiper-lazy-preloader"></div>
            </nuxt-link>
          </template>

          <div class="content" v-if="showButton && item.name" :class="item.name">
            <img :src="item.text" alt="" />
            <div class="btn-container">
              <nuxt-link class="btn secondary--btn-dark" :to="localePath('/testDrive', $i18n.locale)">{{ $t('btn.testDrive') }}</nuxt-link>
              <a class="btn vr-btn secondary--btn-dark" :href="item.vrUrl[$i18n.locale]" target="_blank">{{ $t('btn.viewVR') }}</a>
            </div>
          </div>
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
    showButton: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      imgHeight: '56.25vw',
      swiperOption: {
        loop: true,
        lazy: {
          loadPrevNext: true,
        },
        autoplay: {
          delay: 3500,
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
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        this.handleHeight()
      },
    },
    imageList: {
      immediate: true,
      handler(n, o) {
        this.handleHeight()
      },
    },
  },
  activated() {
    this.videoControl()
  },
  created() {},
  mounted() {},
  methods: {
    handleHeight() {
      switch (this.$mq) {
        case 'lg':
        case 'xl': {
          this.imgHeight = '100vh'
          break
        }
        case 'xs':
        case 'sm':
        case 'md': {
          if (this.imageList && this.imageList[0]) {
            this.imgHeight = this.imageList[0].aspectRatio + 'vw'
          } else {
            this.imgHeight = '56.25vw'
          }
          break
        }
      }
    },
    videoControl() {
      this.$refs.bannerVideo?.[0]?.videoControl()
    },
  },
}
</script>

<style lang="scss" scoped>
.swiper {
  width: 100vw;
  height: 100vh;
  .swiper-slide {
    img {
      transform: scale(1, 1);
    }
    .content {
      position: absolute;
      top: 24.3%;
      left: 5%;
      width: 100%;
      img {
        width: 24%;
      }
    }
    .BJ30 {
      top: 43%;
      left: 5%;
      img {
        mix-blend-mode: lighten;
      }
    }
    .BJ60 {
      top: 51%;
      left: 5%;
      .btn-container {
        text-align: left;
      }
    }
    .X7 {
      top: 65%;
      left: 70%;
    }
    .btn-container {
      margin-top: 15px;
    }
    .btn {
      border-color: #fff;
      color: #fff;
      margin-right: 15px;
      &:hover {
        background: none;
      }
    }
  }
  .swiper-slide-active img,
  .swiper-slide-duplicate-active img {
    transition: 6s linear;
    transform: scale(1.03, 1.03);
  }
  .image {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  .btn-list {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    .border-el-button.el-button {
      padding: 0;
      width: 100%;
      height: 100%;
      border: none;
      border-radius: 0;
      background: transparent;
      position: relative;
    }
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
  @media screen and (max-width: 1200px) {
    .swiper-slide {
      .btn-container {
        display: flex;
        margin-top: 5px;
        .btn {
          display: flex;
          justify-content: center;
          align-items: center;
          margin-right: 5px;
          font-size: 10px;
        }
      }

      .content {
        width: 100%;
        img {
          width: 35%;
        }
      }

      .BJ30 {
        top: 36%;
        left: 5%;
      }
      .X7 {
        top: 58%;
        left: 57%;
        img {
          margin-left: 15px;
        }
      }
      .X55II {
        top: 17%;
      }
    }
  }
}
.lang___es {
  .swiper .swiper-slide {
    .X7 {
      left: 65%;
    }
  }
  @media screen and (max-width: 1200px) {
    .swiper .swiper-slide {
      .content {
        left: 2%;
      }
      .BJ60 {
        top: 53%;
      }
      .X7 {
        left: 0;
        display: flex;
        flex-direction: column;
        align-items: flex-end;
      }
      .X55II,
      .BJ30 {
        top: 9%;
      }
    }
  }
}
.lang___ar {
  .content {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
  }
  @media screen and (max-width: 1200px) {
    .BJ30 {
      left: 5%;
    }
    .BJ60 {
      left: 0;
    }
    .X7 {
      left: 50%;
    }
  }
}
</style>
