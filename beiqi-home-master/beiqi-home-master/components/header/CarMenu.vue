<template>
  <div class="product-nav__wrapper">
    <!-- pc-->
    <el-tabs class="product-nav__tabs" tab-position="left" @tab-click="handleTabClick">
      <!-- all -->
      <el-tab-pane v-if="productMenu && productMenu.length > 0" :label="$t('btn.all')" class="product-nav__pane">
        <template #label>
          <span class="label">{{ $t('btn.all') }}</span>
        </template>

        <!-- 车型缩略图  -->
        <div v-for="link in productMenu" :key="link.id" :label="link.name" class="nav-car__pane all-pane">
          <label class="nav__car-title">{{ link.name }}</label>
          <el-row v-if="link.vehicleClassSummaries && link.vehicleClassSummaries.length > 0">
            <el-col v-for="car in link.vehicleClassSummaries" :key="car.id" :lg="4" :md="12" :sm="24" :xl="4" :xs="24"
              class="nav_car-item" :gutter="50">
              <nuxt-link :to="localePath(`/models/${car.id}`, $i18n.locale, $i18n.locale)"
                class="nav__car-link g-flex-b" @click.native="hideMenu">
                <el-image :src="baseUrl + car.imgUrl" class="nav__car-image">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                  <div slot="placeholder" class="image-slot"><i class="el-icon-picture-outline"></i></div>
                </el-image>
                <label class="nav__car-name">{{ car.title }}</label>
              </nuxt-link>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>

      <!-- 列表-->
      <el-tab-pane v-for="link in productMenu" :key="link.id" :label="link.name" class="product-nav__pane">
        <template #label>
          <span class="label">{{ link.name }}</span>
        </template>
        <div class="tab-content">
          <ul class="car-list-wrap">
            <li v-for="car in link.vehicleClassSummaries" :key="car.id" :class="{ active: currentCarId === car.id }"
              class="car-item g-flex-b" @click="handleCarItem(car.id)">
              <el-image :src="baseUrl + car.imgUrl" class="nav-car-image">
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
              <label class="nav-car-title">{{ car.title }}</label>
            </li>
          </ul>
          <div v-if="Object.keys(productInfo).length > 0" class="car-params g-flex-b">
            <nuxt-link :to="localePath(`/models/${currentCarId}`, $i18n.locale, $i18n.locale)" class="explore"
              :class="{ 'explore-show': showExplre }">
              {{ $t('home.explore') }}
              <i class="el-icon-d-arrow-right"></i>
            </nuxt-link>

            <nuxt-link :class="{ 'params-image-show': productInfo.isLoaded }"
              :to="localePath(`/models/${currentCarId}`, $i18n.locale, $i18n.locale)" class="image-wrap"
              @click.native="hideMenu">
              <el-image :src="productInfo.imgUrl" class="params-image" @load="handleImgLoad">
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
                <div slot="placeholder" class="image-slot placeholder"></div>
              </el-image>
              <div v-if="productInfo.wheelLeft && $i18n.locale !== 'ar'"
                :style="{ width: productInfo.width, bottom: productInfo.bottom, left: productInfo.left }"
                class="wheel wheel-left">
                <img :class="{ 'wheel-rotate': productInfo.isLoaded }" :src="productInfo?.wheelLeft" alt="" />
              </div>
              <div v-if="productInfo.wheelRight && $i18n.locale !== 'ar'"
                :style="{ width: productInfo.width, bottom: productInfo.bottom, right: productInfo.right }"
                class="wheel wheel-right">
                <img :class="{ 'wheel-rotate': productInfo.isLoaded }" :src="productInfo?.wheelRight" alt="" />
              </div>
            </nuxt-link>
            <div class="params-container" :class="{ 'params-container-es': $i18n.locale === 'es' }">
              <ul :key="paramsKey" class="params-list g-flex">
                <li class="params-item">
                  <span class="label">{{ $t('POWER') }}</span>
                  <span class="value">
                    <animate-number ref="power" :duration="500" :to="productInfo?.power || 0" from="0"></animate-number>
                    <i class="unit">KW</i>
                  </span>
                </li>
                <li class="params-item">
                  <span class="label">{{ $t('TORQUE') }}</span>
                  <span class="value">
                    <animate-number ref="torqueNum" :duration="500" :to="productInfo?.torque || 0"
                      from="0"></animate-number>
                    <i class="unit">N·M</i>
                  </span>
                </li>
                <li class="params-item">
                  <span class="label">{{ $t('WHEELBASE') }}</span>
                  <span class="value">
                    <animate-number ref="wheelbaseNum" :duration="500" :to="productInfo?.wheelbase || 0"
                      from="0"></animate-number>
                    <i class="unit">MM</i>
                  </span>
                </li>
              </ul>
              <nuxt-link class="btn test-dirve-btn" to="/testDrive">
                {{ $t('btn.testDrive').toLocaleUpperCase() }}
              </nuxt-link>
              <a v-if="productInfo && productInfo.vrUrl" :href="productInfo.vrUrl" target="_blank" class="btn vr-btn">
                {{ $t('btn.viewVR').toLocaleUpperCase() }}
              </a>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    props: {
      productMenu: {
        type: Array,
        default: [],
      },
    },
    data() {
      return {
        baseUrl: process.env.imgBaseUrl,
        currentCarId: null, // 当前选中车型
        paramsList: [
          {
            id: 36,
            title: 'BJ40 PRO',
            power: 180,
            torque: 305,
            wheelbase: 2760,
            imgUrl: require('~/assets/image/home/model/colors/BJ40-PRO.webp'),
            wheelLeft: require('~/assets/image/home/wheel/BJ40-PRO-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/BJ40-PRO-02.webp'),
            width: '13.305%',
            bottom: '8.66%',
            left: '18.45%',
            right: '21.88%',
          },
          {
            id: 43,
            title: 'BJ30',
            power: 138,
            torque: 305,
            wheelbase: 2820,
            imgUrl: require('~/assets/image/home/model/colors/BJ30.webp'),
            wheelLeft: require('~/assets/image/home/wheel/BJ30-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/BJ30-02.webp'),
            width: '12.705%',
            bottom: '10.66%',
            left: '20.65%',
            right: '21.88%',
          },
          {
            id: 20,
            title: 'BJ40 PLUS',
            power: 184,
            torque: 350,
            wheelbase: 2745,
            imgUrl: require('~/assets/image/home/model/colors/bj40 plus-灰.webp'),
            wheelLeft: require('~/assets/image/home/wheel/BJ40-PLUS-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/BJ40-PLUS-02.webp'),
            width: '13.058%',
            bottom: '9.333%',
            left: '18.647%',
            right: '22.705%',
          },
          {
            id: 40,
            title: 'BJ40 PLUS RHD',
            power: 184,
            torque: 350,
            wheelbase: 2745,
            imgUrl: require('~/assets/image/home/model/colors/bj40 plus-RHD.webp'),
            wheelLeft: require('~/assets/image/home/wheel/BJ40-PLUS-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/BJ40-PLUS-02.webp'),
            width: '13.058%',
            bottom: '9.333%',
            left: '18.647%',
            right: '22.705%',
          },
          {
            id: 24,
            title: 'BJ40 SE',
            power: 165,
            torque: 380,
            wheelbase: 2465,
            imgUrl: require('~/assets/image/home/model/colors/bj40-se-红.webp'),
            wheelLeft: require('~/assets/image/home/wheel/BJ40-SE-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/BJ40-SE-02.webp'),
            width: '14.352%',
            bottom: '5.333%',
            left: '16.706%',
            right: '23.941%',
          },
          {
            id: 14,
            title: 'BJ80',
            power: 206,
            torque: 420,
            wheelbase: 2800,
            imgUrl: require('~/assets/image/home/model/colors/bj80-黑.webp'),
            wheelLeft: require('~/assets/image/home/wheel/BJ80-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/BJ80-02.webp'),
            width: '12.941%',
            bottom: '5.066%',
            left: '18.647%',
            right: '23.235%',
          },
          {
            id: 31,
            title: 'BJ60',
            power: 196,
            torque: 406,
            wheelbase: 2820,
            imgUrl: require('~/assets/image/home/model/colors/BJ60.webp'),
            wheelLeft: require('~/assets/image/home/wheel/BJ60-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/BJ60-02.webp'),
            width: '12.823%',
            bottom: '9.792%',
            left: '19.705%',
            right: '23.647%',
          },
          {
            id: 25,
            title: 'F40',
            power: 165,
            torque: 380,
            wheelbase: 2465,
            imgUrl: require('~/assets/image/home/model/colors/F40-红.webp'),
            wheelLeft: require('~/assets/image/home/wheel/F40-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/F40-02.webp'),
            width: '15.294%',
            bottom: '7.2%',
            left: '17.411%',
            right: '23.176%',
          },
          {
            id: 30,
            title: 'ALL NEW X7',
            power: 138,
            torque: 305,
            wheelbase: 2800,
            imgUrl: require('~/assets/image/home/model/colors/ALL NEW X7.webp'),
            wheelLeft: require('~/assets/image/home/wheel/new-x7-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/new-x7-02.webp'),
            width: '12.588%',
            bottom: '10.8%',
            left: '19.882%',
            right: '20.176%',
          },
          // {
          //   id: 18,
          //   title: 'X7',
          //   power: 138,
          //   torque: 275,
          //   wheelbase: 2800,
          //   imgUrl: require('~/assets/image/home/model/X7.webp'),
          //   wheelLeft: require('~/assets/image/home/wheel/x7-01.webp'),
          //   wheelRight: require('~/assets/image/home/wheel/x7-02.webp'),
          //   width: '14.5%',
          //   bottom: '13%',
          //   left: '18.8%',
          //   right: '17.8%',
          // },
          {
            id: 21,
            title: 'X35',
            power: 110,
            torque: 210,
            wheelbase: 2570,
            imgUrl: require('~/assets/image/home/model/colors/X35.webp'),
            wheelLeft: require('~/assets/image/home/wheel/X35-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/X35-02.webp'),
            width: '13.353%',
            bottom: '7.267%',
            left: '18.059%',
            right: '16.294%',
          },
          {
            id: 26,
            title: 'X55 II',
            power: 138,
            torque: 275,
            wheelbase: 2735,
            imgUrl: require('~/assets/image/home/model/colors/X55-II.webp'),
            wheelLeft: require('~/assets/image/home/wheel/X55II-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/X55II-02.webp'),
            width: '13.941%',
            bottom: '9.733%',
            left: '17.882%',
            right: '17.352%',
          },
          {
            id: 39,
            title: 'X55 II RHD',
            power: 138,
            torque: 275,
            wheelbase: 2735,
            imgUrl: require('~/assets/image/home/model/colors/X55-II-RHD.webp'),
            wheelLeft: require('~/assets/image/home/wheel/X55II-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/X55II-02.webp'),
            width: '13.941%',
            bottom: '9.733%',
            left: '17.882%',
            right: '17.352%',
          },
          {
            id: 17,
            title: 'U5 PLUS',
            power: 83,
            torque: 142,
            wheelbase: 2670,
            imgUrl: require('~/assets/image/home/model/colors/U5 PLUS.webp'),
            wheelLeft: require('~/assets/image/home/wheel/u5-plus-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/u5-plus-02.webp'),
            width: '12.706%',
            bottom: '6.133%',
            left: '17.118%',
            right: '18.647%',
          },
          {
            id: 8,
            title: 'EU5',
            power: 120,
            torque: 240,
            wheelbase: 2670,
            imgUrl: require('~/assets/image/home/model/colors/EU5.webp'),
            wheelLeft: require('~/assets/image/home/wheel/eu5-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/eu5-02.webp'),
            width: '12.706%',
            bottom: '6.133%',
            left: '18.412%',
            right: '18.235%',
          },
          {
            id: 41,
            title: 'EU5',
            power: 120,
            torque: 240,
            wheelbase: 2670,
            imgUrl: require('~/assets/image/home/model/colors/EU5 PLUS.webp'),
            wheelLeft: require('~/assets/image/home/wheel/eu5-plus-01.webp'),
            wheelRight: require('~/assets/image/home/wheel/eu5-plus-02.webp'),
            width: '13.117%',
            bottom: '8%',
            left: '17.529%',
            right: '18.647%',
          },
        ], // 车型参数
        paramsKey: Date.now(), // 车型参数key
        productInfo: {},
        showExplre: false,
      }
    },
    computed: {},
    activated() { },
    mounted() {
    },
    methods: {
      // 获取车型参数
      getCarParams() {
        let resultObj = {}
        const index = this.paramsList.findIndex(obj => obj.id == this.currentCarId)
        if (index > -1) {
          this.productMenu.forEach(item => {
            const obj = item.vehicleClassSummaries.find(child => child.id === this.currentCarId)
            // 合并车型配置和vr链接
            if (obj) {
              resultObj = { ...this.paramsList[index], vrUrl: obj.vrUrl }
            }
          })
          this.productInfo = resultObj
          // 重新渲染参数组件，开始动画
          this.paramsKey++
        }
      },
      // 监听车型大图的加载
      handleImgLoad() {
        // 响应式修改对象中的属性
        this.$set(this.productInfo, 'isLoaded', true)
        setTimeout(() => {
          this.showExplre = true
        }, 1000)
      },
      // 隐藏窗口
      hideMenu() {
        // 隐藏车型图
        this.productInfo.isLoaded = false
        this.showExplre = false
        this.$emit('hideMenu')
      },
      // 切换车型分类
      handleTabClick(tab) {
        this.showExplre = false
        // 重置车型图加载状态
        this.$set(this.productInfo, 'isLoaded', false)
        // 默认选择车型为分类列表第一个车型
        let index
        if (tab.label !== this.$t('btn.all')) {
          index = this.productMenu.findIndex(obj => obj.name === tab.label)
        } else {
          index = 0
        }
        const id = this.productMenu[index].vehicleClassSummaries[0].id
        this.handleCarItem(id)
      },
      // 切换车型项
      handleCarItem(id) {
        if (id === this.currentCarId) {
          return
        }
        this.showExplre = false
        this.currentCarId = id
        // 重置车型图加载状态
        this.$set(this.productInfo, 'isLoaded', false)
        // 获取车型参数
        this.getCarParams()
        // 数字滚动动画
        this.startNumAnimate()
      },
      // 车型参数滚动动画开始
      startNumAnimate() {
        this.$nextTick(() => {
          const animateAllList = [this.$refs.powerNumAll, this.$refs.torqueNumAll, this.$refs.wheelbaseNumAll]
          this.startAnimate(animateAllList)
          this.startAnimate(this.$refs.power)
          this.startAnimate(this.$refs.torqueNum)
          this.startAnimate(this.$refs.wheelbaseNum)
        })
      },
      // 执行dom的滚动动画
      startAnimate(name) {
        if (name && name.length > 0) {
          name.forEach(item => {
            if (item) {
              item.start()
            }
          })
        } else if (name) {
          name.start()
        }
      },
    },
  }
</script>

<style lang="scss" scoped>
  @keyframes wheelRound {
    0% {
      transform: rotate(360deg);
    }

    100% {
      transform: rotate(0);
    }
  }

  :deep .product-nav__tabs {
    .el-tabs__header {
      width: 8%;
    }

    .el-tabs__item.is-left {
      margin-top: 8px;
      width: 8.75vw;
      padding: 0;
      font-size: 16px;
    }

    .el-tabs__item.is-active {
      .label {
        border-bottom: 1px solid #cd3627;
      }
    }
  }

  .el-tabs__content {
    width: 92%;
  }

  .product-nav__pane {
    padding: 10px;

    .tab-content {
      display: flex;
      align-items: flex-start;
      /* 使所有盒子在垂直方向上对齐到顶部 */
      padding-bottom: 30px;

      .car-list-wrap {
        width: 15%;
        max-height: 30vw;
        overflow-x: hidden;
        overflow-y: auto;
      }

      ::-webkit-scrollbar {
        width: 4px;
      }

      .nav-car-title {
        font-size: 15px;
        margin-top: -5px;
        margin-bottom: 20px;
        cursor: pointer;
      }

      .car-item {
        cursor: pointer;

        .el-image {
          margin: 0 2.302vw;
        }

        &:first-child {
          margin-top: 0;
        }
      }

      .active {
        position: relative;

        .nav-car-title {
          color: #cd3627;
        }
      }

      .car-params {
        position: relative;
        width: 85%;

        .image-wrap {
          position: relative;
          width: 86%;
          transform: translateX(105%);
          opacity: 0;

          .placeholder {
            height: 26vw;
          }
        }

        .params-image-show {
          transform: translateX(0);
          opacity: 1;
          transition: all 1s ease;

          .wheel {
            img {
              opacity: 1;
            }
          }
        }

        .explore {
          position: absolute;
          top: 26%;
          left: 16%;
          font-weight: 600;
          opacity: 0;
        }

        .explore-show {
          opacity: 1;
          transition: all 0.3s;
        }

        .el-icon-d-arrow-right {
          color: #000;
          font-size: 16px;
        }

        .params-image {
          display: block;
          cursor: pointer;
          aspect-ratio: 34 / 15;
        }

        .wheel {
          position: absolute;

          img {
            width: 100%;
            opacity: 0;
          }
        }

        .wheel-rotate {
          animation: wheelRound 1s ease;
        }

        .wheel-left {
          width: 14%;
          left: 14.5%;
          bottom: 10.5%;
        }

        .wheel-right {
          width: 14%;
          right: 33%;
          bottom: 10.5%;
        }

        .params-container {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 100%;

          .params-list {
            align-items: center;
            padding: 0 26px;
            margin-right: 30px;
            border-radius: 30px;
            background: #fff;
            box-shadow: rgba(0, 0, 0, 0.2) 0 2px 8px;

            .params-item {
              margin-right: 20px;

              .value {
                margin-left: 10px;
                font-size: 28px;

                .unit {
                  font-weight: 600;
                  font-size: 12px;
                  font-style: inherit;
                }
              }

              &:last-child {
                margin-right: 0;
              }
            }
          }
        }

        .btn {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 12%;
          padding: 8px 0;
          border: 3px solid #fff;
          font-size: 15px;
          box-shadow: rgba(0, 0, 0, 0.2) 0 2px 8px;
          text-align: center;

          &:hover {
            background-color: #d1291a;
            color: #fff;
          }
        }

        .params-container-es {
          .btn {
            font-size: 13px;
          }

          .vr-btn {
            align-self: stretch;
          }
        }

        .test-dirve-btn {}

        .vr-btn {
          margin-left: 8px;
        }
      }
    }

    .all-content {
      .car-params {
        .params-container {
          bottom: 12%;
        }
      }
    }
  }

  .all-pane {
    .nav__car-image {
      width: 100%;
      object-fit: cover;

      .image-slot {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 128px;
        height: 50px;
        background: var(--el-fill-color-light);
        color: var(--el-text-color-secondary);
        font-size: 20px;
      }
    }
  }

  .nav__car-title {
    font-size: 20px;
    margin-bottom: 10px;
    margin-left: 2.302vw;
  }

  .nav-car__pane {
    margin-bottom: 10px;
  }

  .car-item {
    position: relative;
  }

  .nav_car-item {
    position: relative;
    text-align: center;
    // 和其他分类的车型宽度保持一致
    width: 15%;
    margin-right: 2.3%;

    &:nth-child(n + 6) {
      margin-top: 20px;
    }

    .el-image {
      padding: 0 2.302vw;
    }
  }

  .icon-text {
    position: absolute;
    right: -10px;
    top: -20px;
    padding: 2px 5px;
    background: #ee502f;
    color: #fff;
    border-radius: 15px 15px 15px 0;
    text-align: center;
    font-size: 12px;
  }

  .icon-es {
    right: -40px;
    top: -26px;
  }

  //分类下的new
  .car-category {
    .icon-text {
      top: -10px;
      right: -88px;
    }
  }

  .nav__car-name {
    color: #000;
    font-size: 15px;
    cursor: pointer;
  }

  .nav-car__mobile {
    margin-bottom: 15px;
  }

  .text-center {
    text-align: center;
  }

  .xs {
    .icon-text {
      top: -25px;
    }
  }

  .lang___ar {
    .product-nav__tabs {
      display: flex;

      :deep .el-tabs__item {
        direction: rtl;
        text-align: right;
      }

      .tab-content {
        .car-params {
          .image-wrap {
            transform: translateX(0);
            opacity: 1;
          }

          .params-image-show {
            transform: translateX(0);
            opacity: 1;
            transition: all 1s ease;
          }
        }

        .wheel-rotate {
          animation: wheelRound 1s ease;
        }

        .params-list {
          flex: 1;
          margin-left: 30px;
          margin-right: 0;
        }

        .params-item {
          margin-left: 2.604vw;
          margin-right: 0 !important;

          &:last-child {
            margin-left: 0;
          }
        }
      }
    }
  }

  @media only screen and (min-width: 1199px) and (max-width: 1280px) {
    .product-nav__pane .tab-content .nav-car-title {
      font-size: 14px;
    }

    .nav__car-name {
      font-size: 14px;
    }
  }
</style>