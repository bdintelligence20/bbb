<template>
  <div class="heig">
  <div v-swiper:mySwiper="swiperOption" class="index-section" v-if="carList && carList.length > 0">
    <div class="swiper-wrapper">
      <div class="swiper-slide car-slide swiper-no-swiping" :key="car.colorImgUrl" v-for="car in carList">
       <img class="car-img" :src=" car.colorImgUrlMobile" />
      </div>
    </div>
    <!-- <div class="swiper-button-prev">
      <svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" fill="#fff" viewBox="0 0 200 200" style="enable-background: new 0 0 200 200" xml:space="preserve"><path d="M142.6,32.2c1.6-1.7,1.6-4.3-0.1-6c-1.5-1.6-4.1-1.6-5.7-0.1c0,0-0.1,0.1-0.1,0.1l-69.9,73.9l69.9,73.9 c1.6,1.7,4.2,1.7,5.8,0.1c1.6-1.6,1.7-4.3,0.1-6l-64-68L142.6,32.2L142.6,32.2z" /></svg>
    </div>
    <div class="swiper-button-next">
      <svg version="1.1" id="图层_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" fill="#fff" viewBox="0 0 200 200" style="enable-background: new 0 0 200 200" xml:space="preserve"><path d="M68.1,32.2l64,68l-64,68c-1.6,1.7-1.6,4.4,0.1,6c1.6,1.6,4.2,1.6,5.8-0.1l69.9-73.9L73.9,26.3 c0,0-0.1-0.1-0.1-0.1c-1.6-1.5-4.1-1.5-5.7,0.1C66.5,27.9,66.5,30.5,68.1,32.2L68.1,32.2z" /></svg>
    </div> -->
  </div>
</div>
</template>
<script>
export default {
  name: 'SlideCar',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
  
      carList: [
        {
          
          colorImgUrlMobile: require('~/assets/image/vehicleModel/bj60/kv1_m.jpg'),
        },


      ],
      swiperOption: {
        loop: false,
        noSwiping: true,
        observer: true, //修改swiper自己或子元素时，自动初始化swiper默认为false
        observeParents: true, //修改swiper的父元素时，自动初始化swiper
        autoplay: false,
        // navigation: {
        //   nextEl: '.swiper-button-next',
        //   prevEl: '.swiper-button-prev',
        // },
      },
      sliderHeight: '',
    }
  },
  created() {
    this.getCarlist()
  },
  mounted() {},
  methods: {
    async getCarlist() {
      this.$api.home.getCarlist().then(res => {
        console.log(res)
        // this.carList = res.data
      })
    },
  },
}
</script>
<style lang="scss" scoped>
.heig{
  height: 100vh;
}
.index-section {
  margin: 8px 0 0 0;
  background: url('assets/image/swiperBg.jpg') no-repeat 0 0;
  background-size: 100% 100%;
  height: 100vh;
  overflow: hidden;
  direction: ltr;
  .swiper {
    --swiper-theme-color: #ff6600;
    /* 设置Swiper风格 */
    --swiper-navigation-color: transparent;
    /* 单独设置按钮颜色 */
    --swiper-navigation-size: 20px;
    /* 设置按钮大小 */
  }

  .swiper-button-prev,
  .swiper-button-next {
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
}

.car-slide {
  width: 100%;
  // height: 100vh;
  // height: 100vh;
}
.car-slide img{
  object-fit: cover;
  width: 100%;
  height: 100%;
}

.car-title {
  margin-top: 80px;
  font-size: 52px;
  white-space: nowrap;
  opacity: 0;
  letter-spacing: 2px;

  margin-left: -100%;
  margin-right: -100%;
  text-align: center;
}

.lg{
  .index-section{
    height: 100vh;
    overflow: hidden;
  }
}

.swiper-slide-active .car-link,
.swiper-slide-active .car-title {
  opacity: 1;
}

.sm {
  .index-section {
    .swiper-button-prev,
    .swiper-button-next {
      width: 30px;
      height: 30px;
    }
  }
}

.xs {
  .index-section {
    .swiper-button-prev,
    .swiper-button-next {
      width: 30px;
      height: 30px;
    }

  }
}
</style>
