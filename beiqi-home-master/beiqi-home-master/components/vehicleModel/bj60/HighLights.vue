<!--
 * @Author: 不二
 * @Date: 2023-08-25 15:00:28
 * @LastEditTime: 2023-12-04 14:52:20
-->

<template>
  <div :class="['car_highlights', mobileClass] ">
    <div class="car_highlights_max wow animate__slideInUp "  v-if="highData.max">
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
                {{ highData.max.text[locale].describe}}
              </template>
            </car-hover>
    </div>
    <div :class="['car_highlights_open']" :style="{ maxHeight: this.max_height  }" ref="my_height">
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
            <mq-layout mq="lg+"> <img :src="highData.right.right_t.right_t_l.url" alt="" /></mq-layout>
            <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.right.right_t.right_t_l.url_m" alt="" /></mq-layout>

            <car-hover>
              <template v-slot:title>
       
                <p v-html="highData.right.right_t.right_t_l.text[locale].title"></p>
              </template>
              <template v-slot:content>
                {{ highData.right.right_t.right_t_l.text[locale].describe }}
              </template>
            </car-hover>
          </div>

          <div class="highlights_t_r wow animate__slideInUp" v-if="highData.right.right_t.right_t_r">
            <mq-layout mq="lg+"> <img :src="highData.right.right_t.right_t_r.url" alt="" /></mq-layout>
            <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.right.right_t.right_t_r.url_m" alt="" /></mq-layout>

            <car-hover>
              <template v-slot:title>
            
                <p v-html="highData.right.right_t.right_t_r ? highData.right.right_t.right_t_r.text[locale].title : '' "></p>
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
      locale:this.$i18n.locale,
    }
  },

  components: {
    CarMore,
    CarHover,
  },
  mounted(){
    console.log(this.$i18n.locale)
    // setTimeout(() => { this.handleClickMore()},1000) 
  },
  methods: {
    handleClickMore() {
      this.isOpen = !this.isOpen
      if (this.isOpen) {
        this.max_height = this.$refs.my_height.scrollHeight + 'px'
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
          this.max_height = "418vw"
        } else {
          this.mobileClass = ''
          
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
  margin-top: 1vw;
}

.car_highlights_max {
  position: relative;
  overflow: hidden;
  margin: 1vw 0;
}
.car_highlights_max img{
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
  width:95%;
  margin: 0 auto;
  transition: all 1s;
  overflow: hidden;
  /* height: 54.9vw; */
  height: auto;
}


.highlights_left {
  width: 638px;
  position: relative;
  height: 58vw;
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
.highlights_left>div{
  width: 100%;
  height: 100%;
}
.highlights_left img{
  width: 101.1%;
  height: 100%;
  object-fit: cover;
}

.highlights_b {
  position: relative;
  height: 100%;
}
.highlights_b > div{
  height: 100%;
}
.highlights_b > div>img{
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



.highlights_left img,.highlights_t img,.highlights_b img,.car_highlights_max img,.car_highlights_item img{
  transition: all 0.4s;
}
.highlights_left:hover img,
.highlights_t_l:hover img,
.highlights_t_r:hover img,
.highlights_b_l:hover img,
.highlights_b_r:hover img,
.highlights_b:hover img,
.car_highlights_max:hover img,
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
 
}
.mobile .highlights_left{
  height: 141vw;
  overflow: visible;
}

.mobile .car_highlights_max {
  margin-bottom: 1vw;
}

.mobile .highlights_left>div{
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
.mobile .highlights_right{
  margin-left: 0;
}
.mobile .highlights_t>div{
  margin-left: 0;
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
.lang___ar .highlights_t > div:last-child{
  margin-left: 0;
  margin-right: 2vw;
}

.lang___ar .mobile .highlights_right{
  margin: 0;
}
.lang___ar .mobile .highlights_t > div:last-child{
  margin: 0;
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
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.5)) !important;
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
</style>