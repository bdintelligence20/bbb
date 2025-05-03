<template>
  <div v-swiper:mySwiper="swiperOption" class="index-section my_swiper wow animate__slideInUp" v-if="carList && carList.length > 0" ref="swiper_box">
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
</template>
<script>
export default {
  name: 'SlideCar',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      carList: [
        {
          colorImgUrl: require('~/assets/image/vehicleModel/new_x7/img_01.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/new_x7/img_01_m.jpg'),
        },
        {
          colorImgUrl: require('~/assets/image/vehicleModel/new_x7/img_02.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/new_x7/img_02_m.jpg'),
        },
        {
          colorImgUrl: require('~/assets/image/vehicleModel/new_x7/img_03.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/new_x7/img_03_m.jpg'),
        },
        {
          colorImgUrl: require('~/assets/image/vehicleModel/new_x7/img_04.jpg'),
          colorImgUrlMobile: require('~/assets/image/vehicleModel/new_x7/img_04_m.jpg'),
        },
      ],
      swiperOption: {
        notNextTick: true,
        setWrapperSize: true,
        autoHeight: true,
        observer: true, //修改swiper自己或子元素时，自动初始化swiper默认为false
        observeParents: true, //修改swiper的父元素时，自动初始化swiper
        auto:false,
        loop: false,
        // direction: "vertical",
        mousewheel: {
          releaseOnEdges: true,
          enable:false,
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
        on: {
          slideChangeTransitionEnd: ((that)=>{
            return function () {
              console.log(1)
              console.log(that.$emit('changeActiveIndex',this.activeIndex))
              that.$parent.active_index = this.activeIndex
              if (this.activeIndex == 0 || this.activeIndex == 3) {
                //window.off()
                // that.off()
             
                console.log('闭包里的off执行力')
              }
            }
          })(this),
        },
      },
      sliderHeight: '',
      isOn: false,
      luck: false,

      isUp: false,
    }
  },
  created() {},
  mounted() {
    // window.addEventListener('mousewheel', this.onwheel, false);
    console.log(this,"this")
    // window.addEventListener('scroll', this.onscroll)
    //  监听元素滚动到顶部 禁止页面滚动
   
    // window.off = this.off
    // this.randId = Math.random()
   
    // console.dir(this.$refs.swiper_box)
  },
  methods: {

    closeWheel(){
      this.$refs.swiper_box.swiper.mousewheel.disable()
    },
    openWheel(){
      this.$refs.swiper_box.swiper.mousewheel.enable()
    },



    // 监听滚动事件
    onwheel(e) {
      // console.log(e)
      if (e.deltaY > 0) {
        this.isUp = true
      } else {
        this.isUp = false
      }
    },

    // 监听元素距离顶部的距离
    changeDistance() {
      try {
        if (!this.luck) {
          document.querySelector('.header-appear').style.position = 'fixed'
        }
      } catch (error) {}
      let top = this.$refs.swiper_box.getBoundingClientRect().top
      if (top <= -1 && top!=0) {
        console.log('top', top)
        if (!this.luck) {
          this.on()
        }
      }
    },

    // 监听页面滚动

    onscroll(e) {
      // console.log(this.luck,"this.luck", this.randId)
      if (!this.luck) {
        this.changeDistance()
      }
    },

    on() {
      if (this.isOn) {
        return
      }
      this.$refs.swiper_box.swiper.mousewheel.enable()
    
      console.log('on')
      // 特殊标识，表明开启页面滚动禁用，设置了top值，不执行scroll事件
      window.isScrollingByNoscroll = true
      var doc = document.documentElement
      var scrollTop = window.pageYOffset
      doc.style.position = 'fixed'
      doc.style.top = -scrollTop + 'px'
      doc.style.left = 0
      doc.style.right = 0

      // 当数据较少页面高度有限时，可能出现弹层被截断，故注释掉overflow，暂未发现有什么影响
      // doc.style.overflow = 'hidden';
      this.isOn = true
    },
    off() {
      window.removeEventListener('scroll', this.onscroll)
      try {
        document.querySelector('.header-appear').style.position = 'relative'
      } catch (error) {}
      this.$refs.swiper_box.swiper.mousewheel.disable()
      this.luck = true
      // console.log(this.luck,"this.luck  000")
      this.isOn = false
      window.isScrollingByNoscroll = false
      var doc = document.documentElement
      var scrollVal = Math.abs(parseFloat(document.documentElement.style.top))
      doc.style.position = ''
      doc.style.overflow = ''
      doc.style.top = scrollVal
      if (document.body) {
        document.body.scrollTop = scrollVal
      }
      if (document.documentElement) {
        document.documentElement.scrollTop = scrollVal
      }

      // 禁用swiper 的滚轮事件

      document.querySelector('.header-appear').style.position = 'relative'
    },
    isActive() {
      return this.isOn
    },
  },
}
</script>
<style lang="scss" scoped>
.index-section {
  margin: 0px 0 0 0;
  background: url('assets/image/swiperBg.jpg') no-repeat 0 0;
  background-size: 100% 100%;
  position: relative;
  z-index: 9;
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
  height: 100vh;
  // height: 100vh;
}
.car-slide img {
  object-fit: cover;
  width: 100%;
  height: 100vh;
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

.lg {
  .index-section {
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
