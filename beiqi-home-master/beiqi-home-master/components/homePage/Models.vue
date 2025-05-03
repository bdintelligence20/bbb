<template>
  <div class="models-wrap">
    <div class="g-flex-b">
      <div class="main">
        <ul v-if="productMenu.length > 0" class="car-tabs">
          <li v-for="(item, index) in productMenu" :key="item.id" :class="{ active: index === currentCategory }" class="el-tabs__item" @click="changeCategory(item, index)">{{ item.name }}</li>
        </ul>

        <div class="car-content">
          <ul v-if="vehicleClass.length > 0" :class="{ 'middle-block': vehicleClass.length === 1 }" class="model-list max-1680">
            <li v-for="(child, vehicleIdx) in vehicleClass" :key="child.id" :class="{ 'active-car': currentCar === child.id }" :style="{ width: 100 / vehicleClass.length + '%' }" class="model-item" @click="changeCarItem(child, vehicleIdx)">
              <div class="model-thumb-image-wrap">
                <el-image :src="baseUrl + child.imgUrl" alt lazy>
                  <div slot="placeholder" class="image-slot"><i class="el-icon-picture-outline"></i></div>
                </el-image>
              </div>
              <div class="model-name-wrap">
                <span class="model-name-text">{{ child.title }}</span>
              </div>
            </li>
          </ul>

          <!--车型图轮播-->
          <div v-if="vehicleClass.length > 0" v-swiper:carSwiper="swiperOption" v-lazy:background-image="require('~/assets/image/home/car-bg.png')" class="swiper" @slideChange="slideChange" @transitionEnd="transitionEnd">
            <div class="swiper-wrapper">
              <div v-for="(child, vehicleIdx) in vehicleClass" :key="child.id" class="swiper-slide swiper-no-swiping">
                <div class="img-wrap">
                  <div v-for="(color, colorIdx) in child.colors" :key="colorIdx" :class="colorIdx === currentColor ? 'img-item-show' : 'img-item-hide'" class="model-large-img-item">
                      <div v-if="color.path" ref="carCanvas" class="car-canvas-wrap">
                        <!-- 旋转图标-->
                        <mq-layout mq="xs">
                          <img :src="require('~/assets/image/home/360.png')" alt class="rotate-icon" />
                        </mq-layout>

                        <HomePageCar360 v-if="child.id === currentCar && colorIdx === currentColor" ref="car360" :images="color.rotateList" :car-width="child.carWidth" :position="child.position"/>
                      </div>

                        <img v-if="color.imgUrl && colorIdx === currentColor" v-lazy="color.imgUrl" @load="handleLoad(color, colorIdx, vehicleIdx)">
                          <!-- <div slot="error" class="image-slot">
                            <i class="el-icon-picture-outline"></i>
                          </div>
                          <div slot="placeholder" class="image-slot">
                            <i class="el-icon-loading"></i>
                          </div>
                        </el-image> -->
                        <div v-if="child.wheelLeft && child.load && showWheel" :style="{ width: child.width, bottom: child.bottom, left: child.left }" class="wheel wheel-left">
                          <img :key="leftKey" v-lazy="child.wheelLeft" alt :class="{ forward: activeIndex > prevIndex, backward: activeIndex < prevIndex }" />
                        </div>
                        <div v-if="child.wheelRight && child.load && showWheel" :style="{ width: child.width, bottom: child.bottom, right: child.right }" class="wheel wheel-right">
                          <img :key="rightKey" v-lazy="child.wheelRight" alt :class="{ forward: activeIndex > prevIndex, backward: activeIndex < prevIndex }" />
                        </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!--车型颜色-->
      <div class="model-color-wrap">
        <el-divider>
          <ul v-if="model && model.colors" class="color-list">
            <li v-for="(color, colorIdx) in model.colors" :key="colorIdx" class="color-item" @click="changeColor(colorIdx)">
              <mq-layout :mq="['xl', 'lg', 'md', 'sm']">
                <pieColor :class="{ active: currentColor === colorIdx }" :radius="40" :rgb-code="color.rgbCode && color.rgbCode.split('/')" class="color-icon" />
              </mq-layout>
              <mq-layout mq="xs">
                <pieColor :class="{ active: currentColor === colorIdx }" :radius="23" :rgb-code="color.rgbCode && color.rgbCode.split('/')" class="color-icon" />
              </mq-layout>
            </li>
          </ul>
        </el-divider>
      </div>

      <div class="bottom-btn-wrap">
        <nuxt-link :to="localePath('/testDrive', $i18n.locale)" class="border-button btn">
          {{ $t('btn.testDrive').toLocaleUpperCase() }}
        </nuxt-link>
        <a v-if="model && model.vrUrl" :href="model.vrUrl" target="_blank" class="border-button btn vr-btn">
          {{ $t('btn.viewVR').toLocaleUpperCase() }}
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import pieColor from '@/components/pieColor'

export default {
  name: 'Models',
  components: { pieColor },
  props: {
    productMenu: Array,
  },
  data() {
    return {
      swiperOption: {
        speed: 1300,
        observer: true, // 修改swiper自己或子元素时，自动初始化swiper
        observeParents: true, // 修改swiper的父元素时，自动初始化swiper
      },
      baseUrl: process.env.imgBaseUrl,
      currentCategory: 0, // 选中分类
      currentCar: null, // 当前车型
      currentColor: 0, // 当前颜色
      prevIndex: null,
      activeIndex: null,
      leftKey: Date.now(),
      rightKey: Date.now(),
      showWheel: true,
      images: [],
    }
  },
  computed: {
    model() {
      return this.vehicleClass.find(obj => obj.id === this.currentCar)
    },
    // 当前分类的车型
    vehicleClass() {
      return this.productMenu[this.currentCategory].vehicleClassSummaries
    }
  },

  mounted() {
    // 默认选中bj60
    this.currentCar = this.vehicleClass[2]?.id
    // 轮播图跳转到bj60
    this.carSwiper?.slideTo(2, 0, false)
  },

  methods: {
    // 车型图加载完的回调
    handleLoad(color, colorIdx, vehicleIdx) {
      // 如果第一个颜色的车型图加载完，显示车轮
      if (colorIdx === 0) {
        // console.log('图片加载完');
        this.$emit('handleLoad', this.currentCategory, vehicleIdx)
      }
    },
    // 轮播图切换
    slideChange() {
      // 触发车轮转动动画
      this.leftKey = Date.now()
      this.rightKey = Date.now()
      this.activeIndex = this.carSwiper.activeIndex
      this.prevIndex = this.carSwiper.previousIndex
    },
    transitionEnd() {
      this.activeIndex = null
      this.prevIndex = null
    },
    // 切换分类
    changeCategory(item, index) {
      this.currentCar = null
      this.currentColor = 0
      this.currentCategory = index
      this.showWheel = false
      // 默认选中第一个车型
      this.currentCar = this.vehicleClass[0].id
      this.carSwiper.slideTo(0, 0, false)
    },
    // 切换车型
    changeCarItem(child, vehicleIdx) {
      if (child.id === this.currentCar) {
        return
      }
      this.showWheel = true
      this.currentColor = 0
      this.currentCar = child.id
      // 滑动到指定轮播图
      this.carSwiper.slideTo(vehicleIdx)
    },
    // 切换颜色
    changeColor(index) {
      // 显示当前颜色的车图
      this.currentColor = index
      this.showWheel = false
    },
  },
}
</script>

<style lang="scss" scoped>
@keyframes wheelRound {
  0% {
    transform: rotate(0);
  }
  100% {
    transform: rotate(-360deg);
  }
}

@keyframes wheelRoundReverse {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

.main {
  width: 100%;
}

:deep .car-tabs {
  display: flex;
  justify-content: center;

  .el-tabs__header {
    display: flex;
    justify-content: center;
  }

  .el-tabs__content {
    overflow: inherit;
  }

  .el-tabs__item {
    font-size: 18px;
    color: #ababab;
  }

  .el-tabs__item {
    padding: 0;
    margin: 0 20px;
  }

  .active {
    border-bottom: 2px solid #c9362e;
    color: #505051;
  }

  .el-tabs__nav-wrap::after {
    background: transparent;
  }

  .el-tabs__active-bar {
    background-color: #c9362e;
  }

  .el-tabs__item:hover,
  .el-tabs__item.is-active {
    color: #505051;
  }
}

.swiper {
  width: 100%;
}

.models-wrap {
  .model-list {
    display: flex;
    width: 100%;
    margin-top: 30px;
    padding: 0 4.167vw;

    .model-item {
      display: flex;
      justify-content: center;
      position: relative;
      opacity: 0.5;

      .model-thumb-image-wrap {
        position: relative;
        margin-top: 3vw;
        cursor: pointer;
        .image-slot {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 100%;
          height: 100px;
          background: var(--el-fill-color-light);
          color: var(--el-text-color-secondary);
          font-size: 20px;
        }

        img {
          width: 100%;
        }
      }

      .model-name-wrap {
        width: 100%;
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        top: -1%;
        z-index: -1;
        text-align: center;

        .model-name-text {
          font-size: 2.9em;
          color: #666;
          font-style: italic;
          opacity: 0.25;
          font-weight: 500;
          white-space: nowrap;
        }
      }

      &:last-child {
        margin-right: 0;
      }
    }

    .active-car {
      opacity: 1;

      .model-name-wrap {
        .model-name-text {
          opacity: 1;
        }
      }
    }
  }

  .swiper {
    background-size: 100%;
    background-repeat: no-repeat;
  }
  .swiper-slide {
    display: flex;
    justify-content: center;
    position: relative;
    width: 100%;
    height: 30vw;
    overflow: inherit;
  }

  .model-large-img-item {
    display: flex;
    justify-content: center;
    position: absolute;
    top: -2%;
    width: 100%;
    height: 100%;
    .el-image {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 100%;
      height: auto;
    }

    .img-out {
      transform: translateX(-120%);
    }

    .img-in {
      transform: translateX(120%);
    }
  }

  .img-wrap {
    display: flex;
    position: relative;
    width: 68%;
    transform: translateX(0);
    transition: all 1s ease;

    .wheel {
      position: absolute;
    }

    .wheel-left {
      width: 22%;
      bottom: 5.9%;
      left: 7%;
    }

    .wheel-right {
      width: 22%;
      bottom: 5.9%;
      right: 10.5%;
    }

    img {
      width: 100%;
    }
  }

  .car-canvas-wrap {
    position: relative;
    width: 100%;
    margin: 4% auto 0;
    z-index: 2;

    .rotate-icon {
      width: 28px;
      position: absolute;
      left: 14%;
      top: 0;
    }

    .car-canvas {
      height: 100%;
    }
  }

  .img-item-show {
    transform: translateX(0);
    opacity: 1;
  }

  .img-item-hide {
    transform: translateX(150%);
    opacity: 0;
  }

  .model-color-wrap {
    width: 100%;

    .el-divider--horizontal {
      margin: 26px 0;
    }

    .color-list {
      display: flex;
      justify-content: center;

      .color-item {
        margin-right: 12px;

        &:last-child {
          margin-right: 0;
        }
      }

      :deep .color-icon {
        padding: 4px;
        cursor: pointer;

        div {
          border-radius: 0;
          box-shadow: 0 0 5px rgba(0, 0, 0, 0.18);
        }
      }

      .active {
        border: 1px solid #e45d60;
        box-shadow: inherit;
      }
    }
  }

  .bottom-btn-wrap {
    display: flex;
    justify-content: center;
    margin-top: 48px;
    width: 100%;

    .btn {
      width: 10%;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 18px;
      padding: 12px 0px;
      text-align: center;
    }

    .vr-btn {
      margin-left: 16px;
    }
  }
}

.lang___ar {
  .vr-btn {
    margin-right: 16px;
  }
}

@keyframes wheelRound {
  0% {
    transform: rotate(360deg);
  }
  100% {
    transform: rotate(0);
  }
}

.forward {
  animation: wheelRound 1.1s;
}

.backward {
  animation: wheelRoundReverse 1.1s;
}

.xs {
  .el-tabs__item {
    font-size: 15px;
  }

  .models-wrap {
    .model-item {
      width: 30%;

      .model-name-wrap {
        .model-name-text {
          font-size: 18px !important;
        }
      }
    }

    .img-wrap {
      width: 100%;
    }
  }
}

@media screen and (max-width: 1200px) {
  .models-wrap {
    .model-list {
      .model-item {
        .model-name-wrap {
          .model-name-text {
            font-size: 2em;
          }
        }
      }
    }
  }
}

@media screen and (max-width: 768px) {
  .models-wrap {
    .swiper-slide {
      height: 44vw;
    }

    .model-list {
      padding: 30px 10px 10px;
      margin: 0;
      overflow-x: auto;

      .model-item {
        flex-basis: 110px;
        flex-shrink: 0;
        padding-top: 5%;
        margin-right: 25px;

        .model-thumb-image-wrap {
          margin: 0;
          .image-slot {
            height: auto;
          }
        }

        .model-name-wrap {
          width: 100%;
          left: 0;
          top: 0;
          transform: translateX(0);
          z-index: 0;
        }
      }

      &::-webkit-scrollbar {
        height: 3px;
      }
    }

    .middle-block {
      justify-content: center;
    }

    .car-canvas-wrap {
      margin-top: 2%;
    }

    .swiper {
      background: none!important;
      margin-top: 2%;
    }

    .car-content {
      background: url('~/assets/image/home/wap/model-bg.jpg') no-repeat;
      background-size: cover;
    }

    .model-color-wrap {
      //margin-top: 20px;
    }

    .bottom-btn-wrap {
      margin-top: 3%;

      .btn {
        width: 30%;
        font-size: 12px;
      }
    }
  }
}
</style>
