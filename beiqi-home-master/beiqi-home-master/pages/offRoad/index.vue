<!--
 * @Author: 不二
 * @Date: 2024-01-04 09:41:33
 * @LastEditTime: 2024-01-11 15:49:05
-->
<!--
 * @Author: 不二
 * @Date: 2024-01-04 09:41:33
 * @LastEditTime: 2024-01-04 14:32:49
-->
<!--
 * @Author: 不二
 * @Date: 2023-12-18 10:13:43
 * @LastEditTime: 2023-12-21 17:32:15
-->
<template>
  <div>
    <section>
      <kv></kv>
    </section>
    <section class="content_box">
      <!-- 内容部分 -->

      <mq-layout mq="lg+"> <offRoadContent></offRoadContent></mq-layout>
      <mq-layout :mq="['xs', 'sm', 'md']"><offRoadContentMobile></offRoadContentMobile></mq-layout>
    </section>

    <section :class="['page_box', show_page]">
      <!-- 大图 -->
      <section>
        <div v-swiper:mySwiper="swiperOption" v-if="list && list.length > 0" class="img_box swiper-no-swiping">
          <div class="swiper-wrapper">
            <div class="swiper-slide" :key="index" v-for="(item, index) in list">
              <mq-layout mq="lg+">
                <img :src="item.images.pc" alt="" />
              </mq-layout>
              <mq-layout :mq="['xs', 'sm', 'md']">
                <img :src="item.images.web" alt="" />
              </mq-layout>
            </div>
          </div>
        </div>
      </section>

      <!-- 时间 -->
      <section>
        <div class="year_max">
          <div :class="['year_item', active_index == index && 'year_active']" v-for="(item, index) in list" :key="index" @click="handleClick(index)">{{ item.year }}</div>
        </div>

        <div v-swiper:mySwiper_2="swiperOption_2" v-if="list && list.length > 0" class="year_box swiper-no-swiping">
          <div class="swiper-wrapper">
            <div class="swiper-slide" :key="index" v-for="(item, index) in list">
              <div class="year_title" @click="handleClick(index)">{{ item.year }}</div>
            </div>
          </div>
          <div class="yare_xian"></div>
        </div>
      </section>

      <!-- 文案 -->
      <section class="desc_swiper_box">
        <div v-swiper:mySwiper_3="swiperOption_3" v-if="list && list.length > 0" class="desc_box">
          <div class="swiper-wrapper">
            <div class="swiper-slide" :key="index" v-for="(item, index) in list">
              <div class="desc_content">
                {{ item.detail[locale].describe }}
              </div>
            </div>
          </div>
        </div>

        <div class="swiper-pagination">
          <div class="swiper-button-prev">
            <svg t="1702884899839" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6064" width="200" height="200"><path d="M236.552013 926.853955a55.805997 55.805997 0 0 0 0 80.454996 59.682997 59.682997 0 0 0 82.794996 0l468.099978-455.081978a55.805997 55.805997 0 0 0 0-80.453996L319.348009 16.689999a59.682997 59.682997 0 0 0-82.794996 0 55.805997 55.805997 0 0 0 0 80.454996L663.401993 511.999975 236.624013 926.853955z" p-id="6065" fill="#535353"></path></svg>
          </div>
          <div class="swiper-button-next">
            <svg t="1702884899839" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6064" width="200" height="200"><path d="M236.552013 926.853955a55.805997 55.805997 0 0 0 0 80.454996 59.682997 59.682997 0 0 0 82.794996 0l468.099978-455.081978a55.805997 55.805997 0 0 0 0-80.453996L319.348009 16.689999a59.682997 59.682997 0 0 0-82.794996 0 55.805997 55.805997 0 0 0 0 80.454996L663.401993 511.999975 236.624013 926.853955z" p-id="6065" fill="#535353"></path></svg>
          </div>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
import list from './index.js'
import kv from '~/components/offRoad/kv.vue'
import offRoadContent from '~/components/offRoad/content.vue'
import offRoadContentMobile from '~/components/offRoad/content_mobile.vue'
var _that_vue
export default {
  layout: 'main',
  components: {
    kv,
    offRoadContent,
    offRoadContentMobile,
  },
  data() {
    return {
      list: list,
      active_index: 0,
      swiperOption: {
        loop: false,
        observer: true, //修改swiper自己或子元素时，自动初始化swiper默认为false
        observeParents: true, //修改swiper的父元素时，自动初始化swiper
        autoplay: false,
      },
      swiperOption_2: {
        slideToClickedSlide: true,
        loop: false,
        slidesPerView: 'auto',
        centeredSlides: true,
        observer: true, //修改swiper自己或子元素时，自动初始化swiper默认为false
        observeParents: true, //修改swiper的父元素时，自动初始化swiper
        autoplay: false,
        on: {
          init: (function (that) {
            return function () {
              this.controller.control = that.mySwiper
            }
          })(this),
        },
      },
      swiperOption_3: {
        loop: false,
        autoplay: false,
        observer: true, //修改swiper自己或子元素时，自动初始化swiper默认为false
        observeParents: true, //修改swiper的父元素时，自动初始化swiper
        effect: 'fade',
        fadeEffect: {
          crossFade: true,
        },
        on: {
          init: (function (that) {
            return function () {
              this.controller.control = that.mySwiper_2
            }
          })(this),
          slideChange: function (_that) {
            console.log(_that_vue, '--------')
            return (function (swiper) {
              // console.log(swiper.realIndex)
              _that_vue.active_index = swiper.realIndex
            })(this)
          },
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      },
      locale: this.$i18n.locale,
      show_page: 'hide_page',
    }
  },
  mounted() {
    _that_vue = this
    this.show_page = ''
    setTimeout(() => {
      console.log(this.$refs.mySwiper)
    }, 5000)
  },
  methods: {
    handleClick(index) {
      console.log(this.mySwiper_3.slideTo(index))
    },
  },
}
</script>



<style  scoped lang="scss">
.hide_page {
  visibility: hidden;
}
.content_box {
  overflow: hidden;
}
.xs,
.sm,
.md {
  .page_box {
    width: 100%;
    max-width: 100%;
    margin-bottom: 0;
  }
  .year_box {
    display: block;
    height: auto;
    width: 90%;
    margin: 20px auto;
    .swiper-slide {
      // margin: 0 7%;
      width: 33.333%;
      .year_title {
        font-size: 4vw;
        &::after {
          transform: scaleX(1);
          height: 4px;
          bottom: -9px;
        }
      }
    }
    .yare_xian {
      display: block;
    }

    .swiper-slide-active {
      .year_title {
        transform: scale(1);
      }
    }
  }
  .desc_swiper_box {
    width: 90%;
    margin: 0 auto;
    .swiper-slide {
      width: 100% !important;
    }
    .swiper-pagination {
      display: none;
    }
    .desc_content {
      font-size: 3.5vw;
      // font-size: 1.75vw;
    }
  }
  .year_max {
    overflow: hidden;
    height: 0;
    margin: 0;
  }
}

.desc_content {
  text-align: justify;
  font-size: 1.4vw;
}
.yare_xian {
  position: absolute;
  width: 100%;
  bottom: 2px;
  height: 2px;
  background: #9b9b9b;
  display: none;
}
.page_box {
  max-width: 58.33vw;
  margin: 0 auto;
  margin-bottom: 50px;
}
.img_box img {
  width: 100%;
}

.year_max {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 20px 0;
}
.year_item {
  font-size: 28px;
  font-weight: 900;
  color: #626262;
  transform: scale(0.7);
  position: relative;
  white-space: nowrap;
  cursor: pointer;
}
.year_active {
  color: #d12519;
  transform: scale(1);
  transition: all 0.4s;
}
.year_item::after {
  content: '';
  position: absolute;
  width: 2px;
  height: 2px;
  background: transparent;
  transform: scaleX(0);
  bottom: 0;
  left: 0;
  transition: all 0.4s;
  opacity: 0;
}
.year_active::after {
  width: 100%;
  background: #d12519;
  transform: scale(1);
  opacity: 1;
}

.year_box {
  margin: 20px 0;
  height: 0;
  overflow: hidden;
}
.year_box .swiper-slide {
  display: inline-block;
  width: auto;
  // margin: 0 30px;
}
.desc_swiper_box {
  position: relative;
}
.swiper-pagination {
  position: absolute;
  width: 66vw;
  left: 50%;
  top: 50%;
  transform: translate(-50%, 50%);
}

.swiper-pagination .swiper-button-prev,
.swiper-pagination .swiper-button-next {
  background-image: none;
}
.swiper-pagination .swiper-button-prev svg,
.swiper-pagination .swiper-button-next svg {
  max-width: 60px;
  max-height: 60px;
}
.swiper-pagination .swiper-button-prev {
  left: 0px;
  transform: translateX(-150%) ;
}
.swiper-pagination .swiper-button-prev svg {

  transform:  scale(-1);
}
.year_box .swiper-slide {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50px;
}
.year_title {
  position: relative;
  display: inline-block;
  transition: all 0.4s;
  font-size: 30px;
  font-weight: bolder;
  color: #626262;
  transform: scale(0.8);
  white-space: nowrap;
  cursor: pointer;
}
.year_box .swiper-slide .year_title::after {
  content: '';
  position: absolute;
  width: 2px;
  height: 2px;
  background: transparent;
  transform: scaleX(0);
  bottom: 0;
  left: 0;
  transition: all 0.4s;
  opacity: 0;
}
.year_box .swiper-slide-active .year_title {
  color: #d12519;
  transform: scale(1);
}
.year_box .swiper-slide-active .year_title::after {
  width: 100%;
  transform: scaleX(1);
  background-color: #d12519;
  opacity: 1;
}
.lang___ar{
  .page_box{
    direction: ltr;
  }
  .desc_content{
    direction: rtl;
  }
}
</style>