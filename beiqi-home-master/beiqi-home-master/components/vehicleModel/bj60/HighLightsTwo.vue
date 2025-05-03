<!--
 * @Author: 不二
 * @Date: 2023-08-25 15:00:28
 * @LastEditTime: 2023-12-18 10:39:45
-->

<template>
  <div :class="['car_highlights', mobileClass]">
    <div class="car_highlights_max wow animate__slideInUp" v-if="highData.max">
      <mq-layout mq="lg+"> <img :src="highData.max.url" alt="" /></mq-layout>
      <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.max.url_m" alt="" /></mq-layout>

      <!-- <div class="car_highlights_text">
        <p v-html="highData.max.text.title"></p>
        <p v-html="highData.max.text.describe"></p>
      </div> -->
      <car-hover>
        <template v-slot:title>
          <p v-html="highData.max.text[locale].title"></p>
        </template>
        <template v-slot:content>
          {{ highData.max.text[locale].describe }}
        </template>
      </car-hover>
    </div>
    <div :class="['car_highlights_open']" :style="{ maxHeight: this.max_height }" ref="my_height_two">
      <div class="highlights_left wow animate__slideInUp">
        <mq-layout mq="lg+"> <img :src="highData.left.url" alt="" /></mq-layout>
        <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.left.url_m" alt="" /></mq-layout>

        <car-hover>
          <template v-slot:title>
            <p v-html="highData.left.text[locale].title"></p>
          </template>
          <template v-slot:content>
            {{ highData.left.text[locale].describe }}
          </template>
        </car-hover>
      </div>
      <div class="highlights_right">
        <div class="highlights_t">
          <div class="highlights_t_l wow animate__slideInUp">
            <mq-layout mq="lg+">
              <img :src="highData.right.right_t.right_t_l.url" alt="" />

              <div class="highlights_t_l_dot" ref="ani">
                <div class="dot_01">
                  <div class="dot_icon"></div>
                  <div class="dot_xian_1"></div>
                  <div class="dot_xian_2"></div>
                  <div class="content" :class="{'content-ar': $i18n.locale === 'ar'}">
                    <div v-html="highData.right.right_t.right_t_l.text[0][locale].title"></div>
                    <div>
                     <span class="describe" v-html="highData.right.right_t.right_t_l.text[0][locale].describe"></span>
                    </div>
                  </div>
                </div>

                <div class="dot_02">
                  <div class="dot_icon"></div>
                  <div class="dot_xian_1"></div>
                  <div class="dot_xian_2"></div>
                  <div class="content">
                    <div v-html="highData.right.right_t.right_t_l.text[1][locale].title"></div>
                    <div>
                     <span>{{ highData.right.right_t.right_t_l.text[1][locale].describe }}</span>
                    </div>
                  </div>
                </div>

                <div class="dot_03">
                  <div class="dot_icon"></div>
                  <div class="dot_xian_1"></div>
                  <div class="dot_xian_2"></div>
                  <div class="content">
                    <div v-html=" highData.right.right_t.right_t_l.text[2][locale].title "></div>
                    <div>
                     {{ highData.right.right_t.right_t_l.text[2][locale].describe }}
                    </div>
                  </div>
                </div>
              </div>
            </mq-layout>
            <mq-layout :mq="['xs', 'sm', 'md']">




              <img :src="highData.right.right_t.right_t_l.url_m" alt="" v-if="locale === 'en'"/>
              <img src="~/assets/image/vehicleModel/bj60/high_3_1_m_ar.jpg" alt="" v-if="locale === 'ar'"/>
              <img src="~/assets/image/vehicleModel/bj60/high_3_1_m_es.jpg" alt="" v-if="locale === 'es'"/>

            </mq-layout>

            <!--
            <car-hover>
              <template v-slot:title>

                <p v-html="highData.right.right_t.right_t_l.text[locale].title"></p>
              </template>
              <template v-slot:content>
                {{ highData.right.right_t.right_t_l.text[locale].describe }}
              </template>
            </car-hover> -->
          </div>

          <div class="highlights_t_r wow animate__slideInUp" v-if="highData.right.right_t.right_t_r">
            <mq-layout mq="lg+"> <img :src="highData.right.right_t.right_t_r.url" alt="" /></mq-layout>
            <mq-layout :mq="['xs', 'sm', 'md']">
               <img :src="highData.right.right_t.right_t_r.url_m" alt="" />
              </mq-layout>

            <car-hover>
              <template v-slot:title>
                <p v-html="highData.right.right_t.right_t_r ? highData.right.right_t.right_t_r.text[locale].title : ''"></p>
              </template>
              <template v-slot:content>
                {{ highData.right.right_t.right_t_r ? highData.right.right_t.right_t_r.text[locale].describe : '' }}
              </template>
            </car-hover>
          </div>
        </div>
        <div class="highlights_b wow animate__slideInUp">
          <mq-layout mq="lg+"> <img :src="highData.right.right_b.url" alt="" /></mq-layout>
          <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.right.right_b.url_m" alt="" /></mq-layout>

          <car-hover>
            <template v-slot:title>
              <p v-html="highData.right.right_b.text[locale].title"></p>
            </template>
            <template v-slot:content>
              {{ highData.right.right_b.text[locale].describe }}
            </template>
          </car-hover>
        </div>
      </div>
    </div>
    <car-more @handleClickMore="handleClickMore"></car-more>
  </div>
</template>

<script>
import { gsap } from 'gsap/dist/gsap'

import CarMore from '../CarMore.vue'
import CarHover from '../CarHover.vue'

export default {
  props: {
    highData: Object,
  },
  data() {
    return {
      isOpen: true,
      max_height: null,
      mobileClass: '',
      locale: this.$i18n.locale,
    }
  },

  components: {
    CarMore,
    CarHover,
  },
  mounted() {
    window.addEventListener('scroll', this.ani_scroll)
    // setTimeout(() => { this.handleClickMore()},1000)
    // window.ani = this.ani
  },
  methods: {
    handleClickMore() {
      this.isOpen = !this.isOpen
      if (this.isOpen) {
        this.max_height = this.$refs.my_height_two.scrollHeight + 'px'
        console.log(this.$refs.my_height_two.scrollHeight)
      } else {
        this.max_height = '0px'
      }
    },

    ani_scroll() {
      try {
        if (this.$refs.ani.getBoundingClientRect().top < window.innerHeight / 2) {
        this.ani()
        window.removeEventListener('scroll', this.ani_scroll)
      }
      } catch (error) {

      }

    },

    ani() {
      // 创建时间轴动画
      const tl = gsap.timeline({
        paused: true,
        defaults: {
          duration:.3,
          ease: 'power2.inOut',
        },
      })
      tl.to('.dot_01>.content', {
        opacity: 1,
        y: '0.1vw',
      })
      tl.to('.dot_01>.dot_xian_1', {
        '--my-width': '3vw',
      })
      tl.to('.dot_01>.dot_xian_2', {
        '--my-width': '4vw',
      })
      tl.to('.dot_01>.dot_icon', {
        opacity: 1,
        y: '0.1vw',

      })

      // 亮点2

      tl.to('.dot_02>.content', {
        opacity: 1,
        y: '0.1vw',

      })
      tl.to('.dot_02>.dot_xian_1', {
        '--my-width': '24vw',
      })
      tl.to('.dot_02>.dot_xian_2', {
        '--my-height': '5vw',
      })
      tl.to('.dot_02>.dot_icon', {
        opacity: 1,
        y: '0.1vw',

      })

      // 亮点3
      tl.to('.dot_03>.content', {
        opacity: 1,
        y: '0.1vw',

      })
      tl.to('.dot_03>.dot_xian_1', {
        '--my-width': '15.5vw',
      })
      tl.to('.dot_03>.dot_xian_2', {
        '--my-height': '8vw',
      })
      tl.to('.dot_03>.dot_icon', {
        opacity: 1,
        y: '0.1vw',

      })

      // 播放时间轴动画
      tl.play()
    },
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
          this.max_height = '279vw'
        } else {
          this.mobileClass = ''
          this.max_height = '53.9vw'
        }
      },
    },
  },
}
</script>
<style  scoped lang="scss">
img {
  width: 101%;
  max-width: none;
}

// 小亮点

.highlights_t_l_dot {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  .content {
    opacity: 0;
  }
  & > div {
    position: absolute;
    word-wrap: break-word;
    & > div {
      div:nth-child(1) {
        font-size: 1.2vw;
      }
      div:nth-child(2) {
        font-size: 0.8vw;
        max-width: 26vw;
      }
    }
  }
  & > div .dot_xian_1 {
    content: '';
    position: absolute;
    height: 1px;
    width: 10vw;

    background-color: #fff;
  }
  & > div .dot_xian_2 {
    content: '';
    position: absolute;
    height: 1px;
    width: 10vw;

    background-color: #fff;
  }
  .dot_icon {
    position: absolute;
    width: 0.8vw;
    height: 0.8vw;
    border-radius: 50%;
    background-color: #fff;
    opacity: 0;
  }
  .dot_01 {
    top: 19vw;
    left: 1vw;

    .dot_icon {
      left: 16.61vw;
      top: -5.5vw;
    }
    .content{
      top: -2vw;
    position: absolute;
    width: 30vw;
    }
    .content-ar {
      width: 13vw;
      text-align: right;
    }
    .describe {
      white-space: pre-line;
    }
    .dot_xian_1 {
      --my-width: 0;
      width: var(--my-width);
      left: 14vw;
      top: -1vw;
    }
    .dot_xian_2 {
      --my-width: 0;
      width: var(--my-width);
      left: 17vw;
      top: -1vw;
      transform-origin: 0 0;
      transform: rotate(-90deg);
    }
  }

  .dot_02 {
    top: 2vw;
    right: 2vw;
    max-width: 33vw;
    .dot_icon {
      right: 38.4vw;
      top: 5.5vw;
    }
    .dot_xian_1 {
      --my-width: 0;
      width: var(--my-width);
      right: 14.8vw;
      top: 0.8vw;
    }
    .dot_xian_2 {
      width: 1px;
      --my-height: 0;
      height: var(--my-height);
      right: 38.8vw;
      top: 0.8vw;
      transform-origin: 0 0;

    }
    & > div {
      div {
        text-align: right;
      }
    }
  }
  .dot_03 {
    top: 20vw;
    right: 2vw;
    .dot_icon {
      right: 31.05vw;
      top: -7.5vw;
    }
    .dot_xian_1 {
      --my-width: 0;
      width: var(--my-width);
      right: 16vw;
      top: 1vw;
    }
    .dot_xian_2 {
      width: 1px;
      --my-height: 0;
      height: var(--my-height);
      right: 31.4vw;
      top: 1vw;
      transform-origin: 0 0;
      transform: rotate(-180deg);
    }
    & > div {
      div {
        text-align: right;
      }
    }
  }
}

/* 亮点部分 */
.car_highlights {
  position: relative;
  margin-top: 1vw;
}

.car_highlights_max {
  position: relative;
  overflow: hidden;
  margin: 1vw 0;
}
.car_highlights_max img {
  width: 100%;
}

.car_highlights_text {
  position: absolute;
  bottom: 100px;
  left: 100px;
  color: #fff;
}

.car_highlights_text > p:nth-child(1) {
  font-size: 40px;
  line-height: 80px;
  font-weight: bolder;
}

.car_highlights_text > p:nth-child(2) {
  font-size: 24px;
  opacity: 0.6;
  line-height: 50px;
  font-weight: lighter;
}

.car_highlights_open {
  display: flex;
  justify-content: space-between;
  width: 95%;
  margin: 0 auto;
  transition: all 1s;
  overflow: hidden;
  height: auto;
}

.highlights_left {
  width: 638px;
  height: 54vw;
  position: relative;
}
.highlights_right {
  width: 1280px;
  display: flex;
  flex-direction: column;
  margin-left: 1vw;
}

.highlights_t {
  display: flex;
  justify-content: space-between;
  box-sizing: border-box;
  padding-bottom: 1vw;
}
.highlights_left > div {
  width: 100%;
  height: 53.9vw;
}
.highlights_left img {
  width: 101.1%;
  height: 100%;
  object-fit: cover;
}

.highlights_b {
  position: relative;
  height: 100%;
  overflow: hidden;
}
.highlights_b > div {
  height: 100%;
}
.highlights_b > div > img {
  height: 100%;
  object-fit: cover;
}
.highlights_t > div {
  position: relative;
  box-sizing: border-box;
}

.highlights_t > div:last-child {
  margin-left: 1vw;
}

.highlights_left img,
.highlights_t img,
.highlights_b img,
.car_highlights_max img,
.car_highlights_item img {
  transition: all 0.4s;
}
.highlights_left:hover img,
// .highlights_t_l:hover img,
.highlights_t_r:hover img,
.highlights_b_l:hover img,
.highlights_b_r:hover img,
.highlights_b:hover img,
.car_highlights_max:hover img,
.car_highlights_item:hover img {
  transform: scale(1.1);
}

.highlights_left,
.highlights_t_l,
.highlights_t_r,
.highlights_b_l,
.highlights_b_r,
.car_highlights_max,
.highlights_b > div,
.car_highlights_item {
  overflow: hidden;
}
::v-deep .car_highlights_max .hover_box:hover::before{
  opacity: 1;
}
::v-deep .car_highlights_max  .hover_box{
  background-color: transparent !important;
}
::v-deep .car_highlights_max .hover_box:before{
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.4s;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.5)) !important;
}

/* 移动端 */
.mobile .car_highlights_open {
  flex-direction: column !important;
  width: 100%;
  height: auto;
}

.mobile .highlights_left,
.mobile .highlights_right {
  width: 100%;
  margin-bottom: 1vw;
  height: auto;
}
.mobile .car_highlights_max {
  margin-bottom: 1vw;
}
.mobile .highlights_left{
  height: 136.6vw;
  overflow: visible;
  margin-bottom: 1vw;
}
.mobile .highlights_t{
  padding-bottom: 0;
}
.mobile .highlights_left > div {
  height: auto;
}
.mobile .highlights_t {
  display: block;
}
.mobile .highlights_t_l {
  /* margin-bottom: 2vw; */
  margin-bottom: 1vw;
}
.mobile .car_highlights_text {
  /* display: none; */
}
.mobile .car_highlights_text > p:nth-child(1) {
  /* display: none; */
  font-size: 3.5vw;
  line-height: 6vw;
  /* margin-bottom: 2vw; */
  margin-bottom: 1vw;
}
.mobile .car_highlights_text > p:nth-child(2) {
  /* display: none; */
  font-size: 3vw;
  line-height: 5vw;
}
.mobile .car_more {
  bottom: 3.5vw;
}
.mobile .car_more_off {
  bottom: -4vw;
}
.mobile .highlights_right {
  margin-left: 0;
}
.mobile .highlights_t > div {
  margin-left: 0;
}
.mobile .highlights_left:hover img,
.mobile .highlights_t_l:hover img,
.mobile .highlights_t_r:hover img,
.mobile .highlights_b_l:hover img,
.mobile .highlights_b_r:hover img,
.mobile .highlights_b:hover img,
.mobile .car_highlights_max:hover img,
.mobile .car_highlights_item:hover img {
  transform: scale(1);
}

.lang___ar .highlights_right {
  margin-left: 0vw !important;
    margin-right: 2vw !important;
    .dot_01{
      .content{
        // text-align: left !important;
      }
    }
}
.lang___ar .car_highlights_open{
  direction:ltr;
}

::v-deep .car_highlights_max .hover_title{
  // font-size: 2vw !important;
}
::v-deep .car_highlights_max  .hover_title , ::v-deep .car_highlights_max .hover_content{
  padding-left: 2.5% !important;
  padding-right: 2.5% !important;
}
.lang___ar .car_highlights .car_highlights_open .highlights_left ::v-deep .hover_content,
.lang___ar .car_highlights .car_highlights_open .highlights_left ::v-deep .hover_mobile {
    direction: rtl;
    white-space: nowrap;
  }

// .car_highlights_max >>> .hover_box:before {
//   content: '';
//   position: absolute;
//   top: 0;
//   left: 0;
//   width: 100%;
//   height: 100%;
//   opacity: 0;
//   transition: all 0.4s;
//   background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.5)) !important;
// }
// .car_highlights_max >>> .hover_box:hover::before {
//   opacity: 1;
// }
// .car_highlights_max >>> .hover_box:hover {
//   background-color: transparent !important;
// }
// .car_highlights_max >>> .hover_title {
//   font-size: 2vw !important;
// }
</style>