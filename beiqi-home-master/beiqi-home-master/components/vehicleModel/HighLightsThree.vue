<!--
 * @Author: 不二
 * @Date: 2023-08-25 15:00:28
 * @LastEditTime: 2023-09-15 15:38:37
-->

<template>
  <div :class="['car_highlights', mobileClass]">
    <div class="car_highlights_max wow animate__slideInUp" v-if="highData.max">

        <mq-layout mq="lg+">  <img :src="highData.max.url" alt="" /></mq-layout>

        <mq-layout :mq="['xs', 'sm', 'md']">  <img :src="highData.max.url_m" alt=""></mq-layout>

        <car-hover v-if="highData.max.text">
              <template v-slot:title>
                <p v-html="highData.max.text[locale].title"></p>
              </template>
              <template v-slot:content>
                {{ highData.max.text[locale].describe}}
              </template>
            </car-hover>


    </div>
    <div :class="['car_highlights_open']" :style="{ maxHeight: this.max_height }" ref="my_height">
      <div>
        <div class="highlights_left wow animate__slideInUp">
          <mq-layout mq="lg+">
            <img :src="highData.left.url" alt="" />
          </mq-layout>
          <mq-layout :mq="['xs', 'sm', 'md']">
            <img :src="highData.left.url_m" alt="" />
          </mq-layout>
          <car-hover>
            <template v-slot:title>
              {{ highData.left.text[locale].title }}
            </template>
            <template v-slot:content>
              {{ highData.left.text[locale].describe }}
            </template>
          </car-hover>
        </div>

        <div class="highlights_right">
          <div class="highlights_t">
            <div class="highlights_t_l wow animate__slideInUp">
              <mq-layout mq="lg+"> <img :src="highData.right.right_t.right_t_l.url" alt="" /></mq-layout>
              <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.right.right_t.right_t_l.url_m" alt="" /></mq-layout>

              <car-hover>
                <template v-slot:title>
                  {{ highData.right.right_t.right_t_l.text[locale].title }}
                </template>
                <template v-slot:content>
                  {{ highData.right.right_t.right_t_l.text[locale].describe }}
                </template>
              </car-hover>
            </div>

            <div class="highlights_t_r wow animate__slideInUp" v-if="highData.right.right_t.right_t_r">
              <mq-layout mq="lg+"><img :src="highData.right.right_t.right_t_r.url" alt="" /></mq-layout>
              <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.right.right_t.right_t_r.url_m" alt="" /></mq-layout>

              <car-hover>
                <template v-slot:title>
                  {{ highData.right.right_t.right_t_r ? highData.right.right_t.right_t_r.text[locale].title : '' }}
                </template>
                <template v-slot:content>
                  {{ highData.right.right_t.right_t_r ? highData.right.right_t.right_t_r.text[locale].describe : '' }}
                </template>
              </car-hover>
            </div>
          </div>
          <div class="highlights_b">
            <div class="highlights_t_l wow animate__slideInUp">
              <mq-layout mq="lg+"> <img :src="highData.right.right_b.right_b_l.url" alt="" /></mq-layout>
              <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.right.right_b.right_b_l.url_m" alt="" /></mq-layout>

              <car-hover>
                <template v-slot:title>
                  {{ highData.right.right_b.right_b_l.text[locale].title }}
                </template>
                <template v-slot:content>
                  {{ highData.right.right_b.right_b_l.text[locale].describe }}
                </template>
              </car-hover>
            </div>

            <div class="highlights_t_r wow animate__slideInUp" v-if="highData.right.right_b.right_b_r">
              <mq-layout mq="lg+"> <img :src="highData.right.right_b.right_b_r.url" alt="" /></mq-layout>
              <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.right.right_b.right_b_r.url_m" alt="" /></mq-layout>
              <car-hover>
                <template v-slot:title>
                  {{  highData.right.right_b.right_b_r.text[locale].title }}
                </template>
                <template v-slot:content>
                  {{ highData.right.right_b.right_b_r.text[locale].describe}}
                </template>
              </car-hover>
            </div>
          </div>
        </div>
      </div>

      <div>
        <div class="car_highlights_max car_highlights_max_2 wow animate__slideInUp" v-if="highData.max_2">
          <mq-layout mq="lg+"> <img :src="highData.max_2.url" alt="" /></mq-layout>
          <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.max_2.url_m" alt="" /></mq-layout>


          <car-hover>
              <template v-slot:title>
                <p v-html="highData.max_2.text[locale].title"></p>
              </template>
              <template v-slot:content>
                {{ highData.max_2.text[locale].describe}}
              </template>
            </car-hover>
        </div>

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
    </div>

    <car-more @handleClickMore="handleClickMore"></car-more>
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
      locale:this.$i18n.locale,
    }
  },

  components: {
    CarMore,
    CarHover,
  },
  mounted(){
    // setTimeout(() => { this.handleClickMore()},1500)
  },
  methods: {
    handleClickMore() {
      this.isOpen = !this.isOpen
      if (this.isOpen) {
        this.max_height = this.$refs.my_height.scrollHeight +"px"
      } else {
        this.max_height = "0px"
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
          this.max_height ="941vw"
        } else {
          this.mobileClass = ''
          this.max_height ="124.06vw"
        }
      },
    },
  },
}
</script>
<style  scoped>
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
  max-height: 0;
  transition: all 1s;
  overflow: hidden;
  flex-direction: column;
  width:95%;
  margin: 0 auto;
}
.car_highlights_open > div {
  display: flex;
}
.car_highlights_open > div:last-child {
  flex-direction: column;
}

.highlights_left {

  position: relative;
  margin-bottom: 1vw;
}
.highlights_left>div{
  width: 100%;
  height: 100%;
}
.highlights_left img{
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.highlights_right {

  display: flex;
  flex-direction: column;
  margin-left: 1vw;
}

.highlights_t,
.highlights_b {
  display: flex;
  justify-content: space-between;
  box-sizing: border-box;
  margin-bottom: 1vw;
    /* padding-bottom: 2px; */
}

.highlights_t > div,
.highlights_b > div {
  position: relative;
}

.highlights_t > div:last-child,
.highlights_b > div:last-child {
  margin-left: 1vw;
}
.car_highlights_items {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  justify-content: space-between;
}
.car_highlights_item {
  width: calc(50% - 0.5vw);
  position: relative;
}
.car_highlights_max_2{
  margin-top: 0 !important;
}
.car_highlights_item img,.car_highlights_max_2 img {

transition: all 0.4s;
}
.car_highlights_item:hover img ,.car_highlights_max_2:hover img {
transform: scale(1.1);
}

.highlights_left img,.highlights_t img,.highlights_b img,.car_highlights_max_2 img,.car_highlights_item img{
  transition: all 0.4s;
}
.highlights_left:hover img,
.highlights_t_l:hover img,
.highlights_t_r:hover img,
.highlights_b_l:hover img,
.highlights_b_r:hover img,
.highlights_b:hover img,
.car_highlights_max_2:hover img,
.car_highlights_item:hover img{
  transform: scale(1.1);
}

.highlights_left,
.highlights_t_l,
.highlights_t_r,
.highlights_b_l,
.highlights_b_r,
.car_highlights_max,
.highlights_b>div,
.car_highlights_item{
 overflow: hidden;
}


.highlights_t .highlights_t_l>div,.highlights_t .highlights_t_r>div{
  overflow: hidden;
}

/* 移动端 */
.mobile .car_highlights_open {
  flex-direction: column !important;
  width: 100%;
}
.mobile .car_highlights_items {
  flex-direction: column !important;
}
.mobile .car_highlights_open > div {
 flex-direction: column;
}
.mobile .highlights_b{
    flex-direction: column;
}

.mobile .highlights_t > div:last-child ,.mobile .highlights_b > div:last-child{
    margin-left: 0;
}
.mobile .highlights_right{
    margin-left: 0;
    margin-bottom: 0 !important ;
}

.mobile .car_highlights_item {
  width: 100%;
  max-width: 100%;
  margin-bottom: 2vw;
}
.mobile .highlights_left,
.mobile .highlights_right {
  width: 100%;
  margin-bottom: 2vw;
}
/* .mobile .car_highlights_max {
  margin-bottom: 2vw;
} */
.mobile .highlights_t {
  display: block;
  margin-bottom: 0;
}
.mobile .car_highlights_max{
  margin-top: 0;
}
 .highlights_b > div:last-child{
  margin-bottom: 0;
}
.mobile .highlights_t_l, .mobile .highlights_t_r {
  margin-bottom: 2vw;
}

.mobile .car_highlights_text {
  /* display: none; */
}
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
.mobile .car_highlights_item:hover img , .mobile.car_highlights_max_2:hover img {
  transform: scale(1);
}
.mobile .highlights_left:hover img,
.mobile .highlights_t_l:hover img,
.mobile .highlights_t_r:hover img,
.mobile .highlights_b_l:hover img,
.mobile .highlights_b_r:hover img,
.mobile .highlights_b:hover img,
.mobile .car_highlights_max:hover img,
.mobile .car_highlights_item:hover img{
  transform: scale(1);
}
.lang___ar .highlights_right{
  margin-left: 0;
  margin-right: 2vw;
}

.lang___ar .highlights_t > div:last-child,.lang___ar .highlights_b > div:last-child{
  margin-left: 0;
  margin-right: 2vw;
}

.car_highlights_max >>> .hover_box:before{
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.4s;
  background-image: linear-gradient(to left bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.2)) !important;
}
.car_highlights_max >>> .hover_box:hover::before{
  opacity: 1;
}
.car_highlights_max >>> .hover_box:hover{
  background-color: transparent !important;
}
.car_highlights_max >>> .hover_title{
  font-size: 2vw !important;
}
.car_highlights_max >>> .hover_title , .car_highlights_max >>> .hover_content{
  padding-left: 2.5% !important;
  padding-right: 2.5% !important;
}
</style>
