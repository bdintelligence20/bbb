<template>
  <client-only>
    <div ref="container" class="model-container">
      <!-- kv  -->
      <section class="kv-wrap">
        <mq-layout mq="lg+">
          <img :src="images.kv.imgUrl" alt="" />
        </mq-layout>
        <mq-layout :mq="['xs', 'sm', 'md']">
          <img :src="images.kv.mobileImgUrl" alt="" />
        </mq-layout>
        <div v-if="vrViewUrl" class="btn-container">
          <nuxt-link class="btn secondary--btn-dark" :to="localePath('/testDrive', $i18n.locale)">{{ $t('btn.testDrive') }}</nuxt-link>
          <a class="btn secondary--btn-dark" :href="vrViewUrl" target="_blank">{{ $t('btn.viewVR') }}</a>
        </div>
      </section>

      <!-- 视频 -->
      <div class="video-box" @touchstart="videoControl">
        <video v-if="isPlay" ref="pcVideo" src="/bj30/BJ30.mp4" class="video" width="100%" data-type="mp4" controls x5-video-player-type="h5" loop webkit-playsinline playsinline :poster="images.video" preload="auto"></video>
        <img v-else :src="images.video" alt="" @click="playVideo" />
      </div>

      <!-- 车型切换 -->
      <VehicleModelBj30Color :color-list="colorList" />

      <!-- 精美图片轮播 -->
      <VehicleModelBj30Swiper :swiper-list="swiperList" class="exquisite-picture" @imageLoad="startBodyAnimate" />

      <!-- 车型基本参数 -->
      <VehicleModelBj30Type :type-list="typeList" class="vehicle-type"/>

      <!-- 卖点部分 -->
      <div class="sell-point-wrap">
        <!-- 外观卖点 -->
        <section class="sell-point">
          <!-- 大灯 -->
          <VehicleModelBj30SellPointImage :image-item="sellPoint.light" class="section-top" />

          <!-- 前脸、尾灯 -->
          <VehicleModelBj30SellPointSlide :list="sellPoint.appearanceList" class="section-top" />
        </section>

        <!-- 氛围灯图 -->
        <VehicleModelBj30SellPointImage :image-item="sellPoint.atmosphereLights" class="ambient-light section-top" />

        <!-- 内饰卖点 -->
        <section class="sell-point">
          <!-- 档把  -->
          <VehicleModelBj30Gear :gear="sellPoint.gear" />
          <!-- 仪表组合+座椅 -->
          <VehicleModelBj30SellPointSlide :list="sellPoint.interiorList" class="section-top" />
        </section>

        <!-- 越野 -->
        <VehicleModelBj30SellPointImage :image-item="sellPoint.offRoad" class="off-road section-top" />

        <!-- 车身动效 -->
        <section ref="carBody" class="sell-point car-body">
          <VehicleModelBj30SellPointImage :image-item="sellPoint.body" class="relative model">
            <div class="frame-img">
              <mq-layout mq="lg+">
                <img :src="sellPoint.body.frame" alt="" />
              </mq-layout>
              <mq-layout :mq="['xs', 'sm', 'md']">
                <img :src="sellPoint.body.mobileFrame" alt="" />
              </mq-layout>
            </div>

            <VehicleModelBj30Params v-if="paramsList.length > 0" :params-list="paramsList" class="cross-country-params" :class="{ 'cross-country-params-es': $i18n.locale === 'es' }" />
          </VehicleModelBj30SellPointImage>

          <!-- ats -->
          <VehicleModelBj30SellPointImage :image-item="sellPoint.ats" class="section-top" />
        </section>
      </div>

      <!-- 车侧 -->
      <VehicleModelBj30CarSide />

      <!-- 配置项 -->
      <VehicleModelConfiguration :config="config" />

      <!-- 预约试驾 -->
      <CommonTestDrive />
    </div>
  </client-only>
</template>

<script>
import gsap from 'gsap'
import { ScrollTrigger } from 'gsap/dist/ScrollTrigger'

import CommonTestDrive from '~/components/vehicleModel/common/CommonTestDrive.vue'

if (process.client) {
  gsap.registerPlugin(ScrollTrigger)
}

export default {
  name: 'VehicleBJ30',
  components: { CommonTestDrive },
  layout: 'main',
  data() {
    return {
      isPlay: false,
      swiperList: [],
      colorList: [require('~/assets/image/vehicleModel/bj30/color/绿.png'), require('~/assets/image/vehicleModel/bj30/color/白.png'), require('~/assets/image/vehicleModel/bj30/color/灰.png'), require('~/assets/image/vehicleModel/bj30/color/天青蓝.png'), require('~/assets/image/vehicleModel/bj30/color/黑.png'), require('~/assets/image/vehicleModel/bj30/color/浅蓝.png'), require('~/assets/image/vehicleModel/bj30/color/铂金灰.png')],
      typeList: [
        {
          type: this.$t('bj30.Fuel'),
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/车图.jpg'),
          mobileImg: require('~/assets/image/vehicleModel/bj30/移动/车图.jpg'),
          params: [
            { name: this.$t('home.POWER'), value: '138 KW' },
            { name: this.$t('home.TORQUE'), value: '305 N·M' },
            { name: this.$t('bj30.ENGINE'), value: '1.5 T', decimal: 1 },
            { name: this.$t('bj30.TRANSMISSION'), value: '7 DCT' },
            { name: this.$t('bj30.EFFICIENCY'), value: '39.2 %', decimal: 1 },
          ],
        },
        {
          type: this.$t('bj30.Hybrid'),
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/越野参数.jpg'),
          mobileImg: require('~/assets/image/vehicleModel/bj30/移动/越野参数.jpg'),
          params: [
            { name: this.$t('home.POWER'), value: '301 KW' },
            { name: this.$t('home.TORQUE'), value: '685 N·M' },
            { name: this.$t('bj30.ENGINE'), value: `1.5 ${this.$t('bj30.T&HEV')}`, decimal: 1 },
            { name: this.$t('bj30.TRANSMISSION'), value: '2 DHT' },
            { name: this.$t('bj30.km/h'), value: '6.9 S', decimal: 1 },
          ],
        },
      ],
      config: {
        imgUrl: require('~/assets/image/vehicleModel/bj30/预约试驾.jpg'),
        id: '43',
        model: 'BJ30',
        params: [
          { name: this.$t('home.POWER'), value: '138 KW' },
          { name: this.$t('home.TORQUE'), value: '305 N·M' },
          { name: this.$t('bj30.ENGINE'), value: '2.0 T', decimal: 1 },
          { name: this.$t('bj30.NEDC'), value: '7.39 L', decimal: 2 },
          { name: this.$t('home.WHEELBASE'), value: '2820 MM' },
        ],
      },
      paramsList: [
        { name: this.$t('bj30.STEEL'), value: '63.4 %', decimal: 1 },
        { name: this.$t('bj30.MAXIMUM'), value: '25420 N·M' },
        { name: this.$t('bj30.THERMAL'), value: `20 ${this.$t('bj30.POSITIONS')}` },
      ],
      sellPoint: {
        light: {
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/大灯.jpg'),
          mobileImg: require('~/assets/image/vehicleModel/bj30/pc/大灯.jpg'),
          title: this.$t('bj30.headlight'),
          subTitle: this.$t('bj30.daytimeRunningLightBelt'),
        },
        gear: {
          title: this.$t('bj30.electronicGearSelector'),
          subTitle: this.$t('bj30.electronicGearSelectorDesign'),
        },
        atmosphereLights: {
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/氛围灯.jpg'),
          mobileImg: require('~/assets/image/vehicleModel/bj30/移动/氛围灯.jpg'),
          title: this.$t('bj30.Ambient'),
          subTitle: this.$t('bj30.AmbientDesc'),
        },
        body: {
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/车身.jpg'),
          frame: require('~/assets/image/vehicleModel/bj30/pc/车架.jpg'),
          mobileImg: require('~/assets/image/vehicleModel/bj30/移动/车身.jpg'),
          mobileFrame: require('~/assets/image/vehicleModel/bj30/移动/车架.jpg'),
          title: this.$t('bj30.hardcoreSafetyBody'),
          subTitle: this.$t('bj30.bodyStructureOptimization'),
        },
        offRoad: {
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/越野.jpg'),
          mobileImg: require('~/assets/image/vehicleModel/bj30/移动/越野.jpg'),
          title: this.$t('bj30.ExcellentOffroad'),
          subTitle: this.$t('bj30.ExcellentOffroadDesc'),
        },
        ats: {
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/ATS.jpg'),
          mobileImg: require('~/assets/image/vehicleModel/bj30/移动/ATS.jpg'),
          title: this.$t('bj30.ats'),
          subTitle: this.$t('bj30.atsDesc'),
        },
        appearanceList: [
          {
            imgUrl: require('~/assets/image/vehicleModel/bj30/pc/前脸.jpg'),
            mobileImg: require('~/assets/image/vehicleModel/bj30/移动/前脸.jpg'),
            title: this.$t('bj30.face'),
            subTitle: this.$t('bj30.faceDesc'),
          },
          {
            imgUrl: require('~/assets/image/vehicleModel/bj30/pc/车尾.jpg'),
            mobileImg: require('~/assets/image/vehicleModel/bj30/移动/车尾.jpg'),
            title: this.$t('bj30.Rugged'),
            subTitle: this.$t('bj30.RuggedDesc'),
          },
        ],
        interiorList: [
          {
            imgUrl: require('~/assets/image/vehicleModel/bj30/pc/组合仪表.jpg'),
            mobileImg: require('~/assets/image/vehicleModel/bj30/移动/组合.jpg'),
            title: this.$t('bj30.panel'),
            subTitle: this.$t('bj30.panelDesc'),
          },
          {
            imgUrl: require('~/assets/image/vehicleModel/bj30/pc/座椅.jpg'),
            mobileImg: require('~/assets/image/vehicleModel/bj30/移动/座椅.jpg'),
            title: this.$t('bj30.space'),
            subTitle: this.$t('bj30.spaceDesc'),
          },
        ],
      },
      images: {
        kv: {
          imgUrl: require('~/assets/image/vehicleModel/bj30/pc/kv.jpg'),
          mobileImgUrl: require('~/assets/image/vehicleModel/bj30/移动/kv.jpg'),
        },
        video: require('~/assets/image/vehicleModel/bj30/视频.jpg'),
      },
      vrViewUrl: '',
    }
  },
  activated() {
    this.videoControl()
    this.isPlay = false
  },
  mounted() {
    this.getModelData()
    window.scrollTo(0, 0)
  },
  methods: {
    getModelData() {
      this.$api.product
        .productInfo({ id: 43 })
        .then(res => {
          if (res && res.data) {
            this.vrViewUrl = res.data.vrViewUrl
            this.swiperList = res.data.characteristics.find(item => item.type === 'img_and_text_list')?.list
          }
          // 轮播图
        })
        .catch(() => {
          this.$router.push('/')
        })
    },
    // 开始车身动画
    startBodyAnimate() {
      this.$nextTick(() => {
        console.log('车身开始动画')
        const tl1 = gsap.timeline({
          scrollTrigger: {
            trigger: '.model',
            start: 'top 10%',
            end: '+=100',
            // 对应四个切换位置：onEnter进入(start)、onLeave离开(end)、onEnterBack从视口底部(end)返回 和 onLeaveBack从视口顶部(start)离开
            toggleActions: 'play none none reverse',
            // markers: true,
          },
        })
        tl1.to('.frame-img', { opacity: 1 })
        tl1.paused()
      })
    },
    videoControl() {
      this.$refs.video?.videoControl()
    },
    playVideo() {
      this.isPlay = true
      this.$nextTick(() => {
        this.$refs.pcVideo.play()
      })
    },
  },
}
</script>

<style scoped lang="scss">
img {
  width: 100%;
}
.section-top {
  margin-top: 12px;
}
.kv-wrap {
  position: relative;
  .btn-container {
    position: absolute;
    top: 52%;
    left: 70%;
  }
  .btn {
    border-color: #fff;
    color: #fff;
    margin-right: 15px;
    &:hover {
      background: none;
    }
  }
}
.video-box {
  line-height: 0;
}
.exquisite-picture {
  margin: 60px 0;
}
.sell-point-wrap {
  margin: 60px 0 12px;
}
.sell-point {
  width: 90%;
  margin: 12px auto 0;
}
::v-deep .cross-country-params {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  bottom: 5%;
  .params-item {
    flex-direction: column-reverse;
  }
}
.relative {
  position: relative;
}
.frame-img {
  position: absolute;
  top: 0;
  opacity: 0;
  width: 100%;
}
.gear-lever-img {
  position: absolute;
  top: 20.2%;
  left: 37.9%;
  width: 29.5%;
  opacity: 0;
}
.text-light-img {
  position: absolute;
  top: 61.1%;
  left: 50.4%;
  width: 3%;
}
.gear-lever-img-ani {
  animation: move 1.5s;
}
.light-img {
  position: absolute;
  top: -18%;
  width: 100%;
  animation: runner 3.5s linear infinite;
  z-index: 1;
}
.light-img-reverse {
  position: absolute;
  left: -15%;
  top: -17%;
  width: 80%;
  opacity: 1;
  z-index: 1;
  animation: runnerReverse 3.5s linear infinite;
}
@media screen and (max-width: 1200px) {
  .sell-point-wrap {
    margin: 30px 0 12px;
  }
  .section-top {
    margin-top: 5px;
  }
  .sell-point {
    margin-top: 5px;
  }
  .kv-wrap {
    .btn-container {
      top: 30%;
      left: 0;
      width: 100%;
      display: flex;
      justify-content: center;
    }
    .btn {
      padding: 6px 28px;
    }
  }
  .exquisite-picture {
    margin: 30px 0;
  }
  .vehicle-type {
    ::v-deep .params-item:nth-last-child(-n+2) {
      flex: 1;
    }
  }
  ::v-deep .ambient-light,
  ::v-deep .off-road {
    .text-wrap {
      width: 90%;
    }
  }
  ::v-deep .cross-country-params {
    bottom: 2%;
    .vehicle-params-list {
      width: 100%;
      flex-wrap: nowrap;
      align-items: flex-start;
      justify-content: space-between;
    }
    .params-item {
      margin-bottom: 0;
      padding: 0;
      width: 30%;
      .title {
        font-size: 10px;
      }
      .value {
        font-size: 14px;
      }
      &:nth-child(2) {
        flex: 1;
      }
    }
  }
  ::v-deep .cross-country-params-es {
    .params-item {
      width: 32%;
      .value {
        font-size: 13px;
      }
    }
  }
}

.lang___es {
  .btn-container {
    .btn {
      padding: 10px 28px;
    }
  }
}
</style>
