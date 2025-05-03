<!--
 * @Author: Liu
 * @Date: 2023-06-21 14:37:34
 * @LastEditTime: 2023-09-15 15:30:05
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
                {{ highData.max.text[locale].describe}}
              </template>
            </car-hover>
    </div>
    <div :class="['car_highlights_open']" ref="my_height" :style="{ maxHeight: this.max_height  }">
      <div class="car_highlights_items">
        <div class="car_highlights_item wow animate__slideInUp">

          <video src="/x7/video_1.mp4?v=1"  width="100%" data-type="mp4" x5-video-player-type="h5" loop muted webkit-playsinline playsinline autoplay ref="video1"></video>

          <car-hover >
              <template v-slot:title>
                <p v-html="text_1[locale].title"></p>
              </template>
              <template v-slot:content>
                {{ text_1[locale].describe}}
              </template>
            </car-hover>
        </div>
        <div class="car_highlights_item wow animate__slideInUp">
          <video src="/x7/video_2.mp4?v=1"  width="100%" data-type="mp4" x5-video-player-type="h5" loop muted webkit-playsinline playsinline autoplay ref="video2"></video>

          <car-hover >
              <template v-slot:title>
                <p v-html="text_2[locale].title"></p>
              </template>
              <template v-slot:content>
                {{ text_2[locale].describe}}
              </template>
            </car-hover>
        </div>
      </div>

    </div>



    <car-more @handleClickMore="handleClickMore" ></car-more>
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
      text_1:{
        en:{
          title:'2021“China Heart”Top Ten Engine Award"Magic Core"1.5T Engine',
          describe:"Boasting a remarkable 39.2% thermal efficiency, 21 noise reduction technologies, and a 350bar+ high flow ratio.",
        },
        es:{
          title:'2021 "China Heart" Top Ten Engine Award"Magic Core" motor 1.5T',
          describe:"Con un notable 39,2% de eficiencia térmica, 21 tecnologías de reducción de ruido, y una alta relación de flujo de 350bar+.",
        },
        ar:{
          title:'جائزة "China Heart" لأفضل عشرة محركات لعام 2021محرك "Magic Core" سعة 1.5 لتر',
          describe:"يتميز بكفاءة حرارية ملحوظة تبلغ 39.2%، و21 تقنية لتقليل الضوضاء، ونسبة تدفق عالية تزيد عن 350 بار.",
        }
      },
      text_2:{
        en:{
          title:'High-flow VGT Turbocharger',
          describe:"With a 43% improvement in low-speed dynamic response time, lightweight design and efficient energy configuration bring exceptional performance to more than just supercars, providing a thrilling driving experience for all.",
        },
        es:{
          title:'Turbocompresvgt de alto flujo',
          describe:"Con una mejora del 43% en el tiempo de respuesta dinámica de baja velocidad, el diseño ligero y la configuración eficiente de la energía aportan un rendimiento excepcional a más de los supercoches, proporcionando una experiencia de conducción emocionante para todos.",
        },
        ar:{
          title:'شاحن توربيني VGT عالي التدفق',
          describe:"مع تحسن بنسبة 43% في وقت الاستجابة الديناميكية للسرعة المنخفضة، يوفر التصميم خفيف الوزن وتكوين الطاقة الفعّال أداءً استثنائيًا لأكثر من مجرد سيارات خارقة، مما يوفر تجربة قيادة مثيرة للجميع.",
        }
      }
    }
  },

  components: {
    CarMore,
    CarHover,
  },
  mounted(){
    // setTimeout(() => {   this.handleClickMore() }, 3500);
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
    videoControl() {
      this.$refs.video1.play()
      this.$refs.video2.play()
    }

  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
          this.max_height = '114vw'
        } else {
          this.mobileClass = ''
          this.max_height = '28.38vw'
        }
      },
    },
  },
}
</script>
<style  scoped>
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
  margin: 1vw 0;
}
.car_highlights_max img ,.car_highlights_max_2 img{
  width: 100%;
}

.car_highlights_max_2{
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
  max-height: 0;
  transition: all 1s;
  overflow: hidden;
  width: 95%;
  margin: 0 auto;
  flex-direction: column;
}

.car_highlights_items {
  display: flex;
  /* flex-wrap: wrap; */
  width: 100%;
  justify-content: space-between;
  line-height: 0;

}
.car_highlights_item {
  width: calc(50% - 0.5vw);
  position: relative;
  box-sizing: border-box;
  overflow: hidden;
  margin-bottom: 1vw;
  height: 100%;
}
.car_highlights_item video{
  width: 100%;
  height: 100%;
}
.car_highlights_item img,.car_highlights_max_2 img {

  transition: all 0.4s;
}
.car_highlights_item:hover img ,.car_highlights_max_2:hover img {
  transform: scale(1.1);
}
.car_highlights_item:nth-child(2n){
  /* margin-left: 1vw; */
}
.car_highlights_item:nth-child(2n+1){
  /* margin-right: 1vw; */
}

/* 移动端 */

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
  display: flex;

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

.mobile .car_highlights_item:hover img , .mobile .car_highlights_max_2:hover img {
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
.lang___ar .car_highlights_item{
  margin-right: 2vw;
  margin-left: 0px;

}
.mobile .car_highlights_item{
  margin-bottom: 1.5vw;
  margin-left: 0;
  margin-right: 0;
}

.car_highlights_max >>> .hover_title , .car_highlights_max >>> .hover_content{
  padding-left: 2.5% !important;
  padding-right: 2.5% !important;
}

.car_highlights_open >>> .hover_title{
  line-height: 1.5;
}
.car_highlights_open >>> .hover_content{
  line-height: 1.5;
}

</style>
