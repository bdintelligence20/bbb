<!--
 * @Author: Liu
 * @Date: 2023-06-21 14:37:34
 * @LastEditTime: 2023-09-15 15:37:47
-->
<template>
  <div :class="['car_highlights', mobileClass]">
    <div class="car_highlights_max wow animate__slideInUp">
      <mq-layout mq="lg+"> <img :src="highData.max.url" alt="" /></mq-layout>
      <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.max.url_m" alt="" /></mq-layout>
      <car-hover v-if="highData.max.text">
        <template v-slot:title>
          <p v-html="highData.max.text[locale].title"></p>
        </template>
        <template v-slot:content>
          {{ highData.max.text[locale].describe }}
        </template>
      </car-hover>
    </div>
    <div :class="['car_highlights_open']" ref="my_height" :style="{ maxHeight: this.max_height }">
      <div class="car_highlights_items">
        <div class="car_highlights_item wow animate__slideInUp" v-for="(item, index) in highData.list" :key="index">
          <mq-layout mq="lg+"> <img :src="item.url" alt="" /></mq-layout>
          <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="item.url_m" alt="" /> </mq-layout>

          <car-hover>
            <template v-slot:title>
              <p v-html="item.text[locale].title"></p>
            </template>
            <template v-slot:content>
              {{ item.text[locale].describe }}
            </template>
          </car-hover>
        </div>
      </div>
    </div>

    <car-more @handleClickMore="handleClickMore" v-if="highData.list.length > 0"></car-more>
  </div>
</template>

<script>
import CarMore from './CarMore.vue'
import CarHover from './CarHover.vue'
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
    // setTimeout(() => { this.handleClickMore()},1000)
  },
  methods: {
    handleClickMore() {
      this.isOpen = !this.isOpen
      if (this.isOpen) {
        this.max_height = this.$refs.my_height.scrollHeight + 'px'
      } else {
        this.max_height = '0px'
      }
    },
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
          this.max_height = '660vw'
        } else {
          this.mobileClass = ''
          this.max_height = '54vw'
        }
      },
    },
  },
}
</script>
<style scoped>
img {
  width: 101%;
  max-width: none;
}
/* 亮点部分 */
.car_highlights {
  position: relative;
  z-index: 1;
}

.car_highlights_max {
  position: relative;
  overflow: hidden;
  margin-bottom: 1vw;
}
.car_highlights_max >>> .hover_title,
.car_highlights_max >>> .hover_content {
  padding-left: 2.5% !important;
  padding-right: 2.5% !important;
}
.car_highlights_max img,
.car_highlights_max_2 img {
  width: 100%;
}

.car_highlights_max_2 {
  position: relative;
  display: flex;
  overflow: hidden;
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
  position: relative;
  flex-direction: column;
}

.car_highlights_items {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  justify-content: space-between;
}
.car_highlights_item {
  width: calc(32.666666%);
  position: relative;
  box-sizing: border-box;
  overflow: hidden;
  margin-bottom: 1vw;
}
.car_highlights_item > div {
  overflow: hidden;
}
.car_highlights_item img,
.car_highlights_max_2 img {
  transition: all 0.4s;
}
.car_highlights_item:hover img,
.car_highlights_max_2:hover img {
  transform: scale(1.1);
}
.car_highlights_item:nth-child(2n) {
  /* margin-left: 2vw; */
}
.car_highlights_item:nth-child(4) {
  /* margin-right: 1px; */
  width: 66.3%;
}
.car_highlights_item:nth-child(6) {
  position: absolute;
  right: 0;
  bottom: -0.9vw;
}
.car_highlights_item:nth-child(5) {
  height: 17.1vw;
}

/* 移动端 */
.mobile .car_highlights_item {
  position: relative !important;
  height: auto;
}
.mobile .car_highlights_open {
  flex-direction: column;
  width: 100%;
}
.mobile .car_highlights_items {
  flex-direction: column !important;
}

.mobile .car_highlights_item {
  width: 100%;
  max-width: 100%;
  margin-bottom: 2px;
  margin-left: 0 !important;
}
/* .mobile .car_highlights_max {
  margin-bottom: 2vw;
} */
.mobile .car_highlights_text > p:nth-child(1) {
  /* display: none; */
  font-size: 3.5vw;
  line-height: 6vw;
  margin-bottom: 2vw;
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
.mobile .car_highlights_text {
  bottom: 8.5vw;
}

.mobile .car_highlights_item:hover img,
.mobile .car_highlights_max_2:hover img {
  transform: scale(1);
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
.lang___ar .car_highlights_item {
  margin-right: 0vw;
  margin-left: 0px;
}
.lang___ar .car_highlights_item:nth-child(6) {
  right: auto;
  left: 0;
}
.car_highlights_item {
  margin-left: 0px;
  margin-right: 0;
}
.mobile .car_highlights_item {
  width: 100%;
}
.car_highlights_max >>> .hover_box:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.4s;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.5)) !important;
}
.car_highlights_max >>> .hover_box:hover::before {
  opacity: 1;
}
.car_highlights_max >>> .hover_box:hover {
  background-color: transparent !important;
}
.car_highlights_max >>> .hover_title {
  font-size: 2vw !important;
}
</style>
