<template>
  <div :class="['index-section',mobileClass]">
    <div v-swiper:mySwiper="swiperOption" class="wow animate__slideInUp" v-if="carList && carList.length > 0" ref="swiper_box" id="certify">
      <div class="swiper-wrapper">
        <div class="swiper-slide car-slide" :key="car.colorImgUrl" v-for="car in carList">
          <img class="car-img" :src="car.colorImgUrl" />
        </div>
      </div>
      <div class="swiper-button-prev">
        <svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" fill="#fff" viewBox="0 0 200 200" style="enable-background: new 0 0 200 200" xml:space="preserve"><path d="M142.6,32.2c1.6-1.7,1.6-4.3-0.1-6c-1.5-1.6-4.1-1.6-5.7-0.1c0,0-0.1,0.1-0.1,0.1l-69.9,73.9l69.9,73.9 c1.6,1.7,4.2,1.7,5.8,0.1c1.6-1.6,1.7-4.3,0.1-6l-64-68L142.6,32.2L142.6,32.2z" /></svg>
      </div>
      <div class="swiper-button-next">
        <svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" fill="#fff" viewBox="0 0 200 200" style="enable-background: new 0 0 200 200" xml:space="preserve"><path d="M68.1,32.2l64,68l-64,68c-1.6,1.7-1.6,4.4,0.1,6c1.6,1.6,4.2,1.6,5.8-0.1l69.9-73.9L73.9,26.3 c0,0-0.1-0.1-0.1-0.1c-1.6-1.5-4.1-1.5-5.7,0.1C66.5,27.9,66.5,30.5,68.1,32.2L68.1,32.2z" /></svg>
      </div>
    </div>
  </div>
</template>
  <script>
export default {
  name: 'SlideCar',
  props:{
    carList:{
        type:Array,
        default:[
 
        {
          colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/bj60/img_04.jpg'),
        },
        {
          colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_05.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/bj60/img_05.jpg'),
        },
        {
          colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_06.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/bj60/img_06.jpg'),
        },
        {
          colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_02.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/bj60/img_02.jpg'),
        },
      
        {
          colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_01.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/bj60/img_01.jpg'),
          
        },
           
        {
          colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_03.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/bj60/img_03.jpg'),
        },
      ],
    }
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      mobileClass:"",
      swiperOption: {
        watchSlidesProgress: true,
        slidesPerView: 'auto',
        centeredSlides: true,
        loop: true,
        loopedSlides: 5,
        autoplay: false,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
        on: {
          progress: function (progress) {
            for (let i = 0; i < this.slides.length; i++) {
              var slide = this.slides.eq(i)
              var slideProgress = this.slides[i].progress
              let modify = 1
              if (Math.abs(slideProgress) > 1) {
                modify = (Math.abs(slideProgress) - 1) * 0.3 + 1
              }
              let translate = slideProgress * modify * 260 + 'px'
              let scale = 1 - Math.abs(slideProgress) / 5
              let zIndex = 999 - Math.abs(Math.round(10 * slideProgress))
              slide.transform('translateX(' + translate + ') scale(' + scale + ')')
              slide.css('zIndex', zIndex)
              slide.css('opacity', 1)
              if (Math.abs(slideProgress) > 3) {
                slide.css('opacity', 0)
              }
            }
          },
          setTransition: function (transition) {
            for (var i = 0; i < this.slides.length; i++) {
              var slide = this.slides.eq(i)
              slide.transition(transition)
            }
          },
        },
      },
    }
  },
  created() {},
  mounted() {
    if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
        } else {
          this.mobileClass = ''
        }
  },
  methods: {},
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
}
</script>
  <style lang="scss" scoped>
.lang___ar{
  .index-section{
    direction: ltr;
  }
    
}
.index-section {
  margin: 0px 0 0 0;
  background: url('assets/image/vehicleModel/bj60/meitu_bg.jpg') no-repeat 0 0;
  background-size: 100% 100%;
  position: relative;
  z-index: 9;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  position: relative;
  overflow: hidden;
  .swiper-button-next,
  .swiper-button-prev {
    width: 50px;
    height: 50px;
    background-size: 150%;
    border-radius: 100%;
    top: 45%;
    margin-top: -25px;
    background-color: transparent;
    background-image: none;
  }
  .swiper-button-next {
    right: 5%;
  }

  .swiper-button-prev {
    left: 5%;
  }
  .swiper-slide-active {
    transform: scale(0.9) !important;

  }
  #certify .swiper-container {
    padding-bottom: 60px;
  }

  #certify .swiper-slide {
    width: 980px;
    height: 670px;
    border: 2px solid #fff;
    border-radius: 20px;
    overflow: hidden;
    
    box-shadow: 0px 0px 20px #000;
  }
  #certify .swiper-slide img {
    width: 100%;
    height: 100%;
    display: block;
    object-fit: cover;
  }
}
.mobile{
  height: 68vw;
  #certify {
    // width: 90vw;
  }
  #certify .swiper-slide {
    width: 80vw;
    height: auto;
    box-shadow: 0px 0px 10px #000;
  }
}

.sm {
  .index-section {
    height: 68vw;
  #certify {
    // width: 90vw;
  }
  #certify .swiper-slide {
    width: 80vw;
    height: auto;
    box-shadow: 0px 0px 10px #000;
  }
  }
}

.xs {
  .index-section {
    height: 68vw;
  #certify {
    // width: 90vw;
  }
  #certify .swiper-slide {
    width: 80vw;
    height: auto;
    box-shadow: 0px 0px 10px #000;
  }

  }
}



</style>
  