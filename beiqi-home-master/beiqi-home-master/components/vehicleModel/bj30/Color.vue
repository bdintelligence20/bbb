<!--
 * @Author: 不二
 * @Date: 2023-08-27 20:55:11
 * @LastEditTime: 2023-12-14 17:28:21
-->
<template>
  <div :class="['car_color_box',mobileClass,'wow animate__slideInUp']">
    <div class="turntable">

    <Chart v-show="show_color" :is-show="show_color" :option-color="optionColor" @closeColor="show_color = false" @changeColor="changeColor"></Chart>
    <div v-show="!show_color" class="open_icon" @click="show_color = !show_color">
        <img src="~/assets/image/vehicleModel/open.png" alt="">
      </div>
    </div>
    <div class="car">
      <div ref="swiperMy" v-swiper:mySwiper="swiperOption" class="car_swiper">
        <div  class="swiper-wrapper">
          <div v-for="(item, index) in colorList" :key="index" class="swiper-slide ">
            <img :src="item" alt="" />
            <!-- <img :src="item.car_m_img" alt="" v-else /> -->
          </div>
        </div>
      </div>
      <mq-layout mq="lg+"><img src="~/assets/image/vehicleModel/bj30/color-bg.jpg" alt="" /></mq-layout>
      <mq-layout :mq="['xs', 'sm', 'md']"> <img src="~/assets/image/vehicleModel/bj30/color-bg-m.jpg" alt="" /></mq-layout>
    </div>
  </div>
</template>

<script>
import Chart from './Chart.vue'
export default {
  components: {Chart},
  props: {
    colorList: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {
      mobileClass:"",
      show_color:false,
      swiperOption: {
        notNextTick: true,
        setWrapperSize: true,
        loop: true,
        autoplay: false,
        effect: 'fade',
        fadeEffect: {
          crossFade: true,
        },
        simulateTouch: false, // 禁止鼠标模拟
        observer: true, // 修改swiper自己或子元素时，自动初始化swiper默认为false
        observeParents: true, // 修改swiper的父元素时，自动初始化swiper
      },
      optionColor:null
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
        } else {
          this.mobileClass = ''
        }
      },
    },
  },
  mounted(){
    if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
        } else {
          this.mobileClass = ''
        }
  },
  methods:{
    changeColor(e,clickedIndex){
      this.optionColor = e
      console.log(clickedIndex)
      // 处理轮播切换
      this.$refs.swiperMy.swiper.slideTo(clickedIndex==0?1:clickedIndex/2+1)
    },

  },
}
</script>
<style lang="scss" scoped>
.car_color_box{
  position:relative;
  overflow: hidden;
  direction: ltr;
  z-index: 0;
  height: 56.6vw;
}
.turntable{
  position: absolute;
  left: 10vw;
  top: 5vw;
  z-index: 9;
}
.car{
  position: relative;
  overflow: hidden;
  > div {
    img{
    width: 101%;
    max-width: none;
  }
  }
  .car_swiper{
    position: absolute;
    width: 60%;
    max-height: 50vw;
    height: 33vw;
    left: 50%;
    transform: translateX(-48%);
    bottom: 4.5vw;
    overflow: hidden !important;
    img{

      max-width: none;
      width: 100%;
    }
  }
}
.open_icon{
  position: absolute;
  top: 5vw;
  left: 5vw;
  width: 5vw;
  cursor: pointer;
  img{
    width: 100%;
  }
}
.mobile.car_color_box{
    height: auto;
  }
.mobile{

  .open_icon{
    width: 10vw;
    left: 50%;
    transform: translateX(-50%);
  }
  .turntable{
    left: 50%;
    transform: translateX(-50%);
  }
  .car{
    .car_swiper{
      width: 66vw;
      bottom: 21.5vw;
    }
  }
}

</style>
