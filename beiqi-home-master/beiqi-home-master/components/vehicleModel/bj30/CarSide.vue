<template>
  <client-only>
    <div class="car-side-wrap" :class="{ 'car-side-wrap-es': $i18n.locale === 'es' }">
      <mq-layout mq="lg+">
        <img :src="carSide.imgUrl" @load="startAnimate" />
        <div class="point-container">
          <VehicleModelBj30CarSidePoint v-for="(item, index) in positionList" :key="index" :point="item" />
        </div>
      </mq-layout>
      <mq-layout :mq="['xs', 'sm', 'md']">
        <img :src="carSide.mobileImgUrl" @load="startAnimate" />
        <div class="point-container">
          <VehicleModelBj30CarSidePoint v-for="(item, index) in mobilePositionList" :key="index" :point="item" />
        </div>
      </mq-layout>
    </div>
  </client-only>
</template>

<script>
import gsap from 'gsap'
import { ScrollTrigger } from 'gsap/dist/ScrollTrigger'

if (process.client) {
  gsap.registerPlugin(ScrollTrigger)
}

export default {
  data() {
    return {
      carSide: {
        imgUrl: require('~/assets/image/vehicleModel/bj30/pc/车侧.jpg'),
        mobileImgUrl: require('~/assets/image/vehicleModel/bj30/移动/车侧.jpg'),
      },
      positionList: [
        {
          pointPos: { left: '54.8%', top: '32.2%' },
          boxPos: { left: '-19', top: '-55', width: '25', height: '55' },
          position: { line1: 'top', line2: 'right' },
          textInfo: { content: this.$t('bj30.roof'), pos: { top: '-15' } },
          name: 'roof',
          // 线条动画的类名、动画属性
          animate: { line1Target: '.roof .top', line1Animate: { width: '100%' }, line2Target: '.roof .right', line2Animate: { height: '100%' } },
        },
        {
          pointPos: { left: '71%', top: '33.9%' },
          boxPos: { left: '4', top: '-109', width: '50', height: '115' },
          position: { line1: 'right', line2: 'bottom', circle: 'right', text: 'top' },
          textInfo: { content: this.$t('bj30.L-type') },
          name: 'hard-top',
          animate: { line1Target: '.hard-top .right', line1Animate: { height: '100%' }, line2Target: '.hard-top .bottom', line2Animate: { width: '100%' } },
        },
        {
          pointPos: { left: '71.5%', top: '41.6%' },
          boxPos: { left: '5', top: '-45', width: '154', height: '45' },
          position: { line1: 'top-reverse', line2: 'left', circle: 'right', text: 'right' },
          textInfo: { content: this.$t('bj30.PersonalityMark') },
          name: 'mark',
          animate: { line1Target: '.mark .top-reverse', line1Animate: { width: '100%' }, line2Target: '.mark .left', line2Animate: { height: '100%' } },
        },
        {
          pointPos: { left: '28.9%', top: '52%' },
          boxPos: { left: '-44', top: '-150', width: '50', height: '150' },
          position: { line1: 'top', line2: 'right' },
          textInfo: { content: this.$t('bj30.Personality') },
          name: 'lamp',
          animate: { line1Target: '.lamp .top', line1Animate: { width: '100%' }, line2Target: '.lamp .right', line2Animate: { height: '100%' } },
        },

        {
          pointPos: { left: '37.7%', top: '62.6%' },
          boxPos: { left: '-24', top: '99', width: '40', height: '0' },
          position: { line1: 'top-reverse', circle: 'right', text: 'right' },
          textInfo: { content: this.$t('bj30.Scratch-proof'), pos: { top: '-14', left: '-45' } },
          name: 'scratch',
          animate: { line1Target: '.scratch .top-reverse', line1Animate: { width: '100%' } },
          hideRippleDots: true,
        },
        {
          pointPos: { left: '37.7%', top: '62.6%' },
          boxPos: { left: '-24', top: '7', width: '40', height: '95' },
          position: { line1: 'top', line2: 'left-reverse' },
          name: 'scratch1',
          animate: { line1Target: '.scratch1 .left-reverse', line1Animate: { height: '100%' }, line2Target: '.scratch1 .top', line2Animate: { width: '100%' } },
        },
        {
          pointPos: { left: '37.7%', top: '62.6%' },
          boxPos: { left: '-63', top: '-46', width: '40', height: '53' },
          position: { line1: 'top-reverse', line2: 'right-reverse' },
          name: 'scratch2',
          animate: { line1Target: '.scratch2 .right-reverse', line1Animate: { height: '100%' }, line2Target: '.scratch2 .top-reverse', line2Animate: { width: '100%' } },
        },
        {
          type: 'angle',
          pointPos: { left: '28%', top: '73.6%' },
          textInfo: { content: this.$t('bj30.Approach'), pos: { top: '114', left: '26' } },
          name: 'angle-left',
          imgUrl: require('~/assets/image/vehicleModel/bj30/25度.png'),
          animate: { angleTarget: '.angle-left .angle' },
        },
        {
          type: 'angle',
          pointPos: { left: '73%', top: '71.6%' },
          textInfo: { content: this.$t('bj30.Departure'), pos: { top: '123', left: '43' } },
          name: 'angle-right',
          imgUrl: require('~/assets/image/vehicleModel/bj30/30度.png'),
          animate: { angleTarget: '.angle-right .angle' },
        },
      ],
      mobilePositionList: [
        {
          pointPos: { left: '47.8%', top: '45.2%' },
          boxPos: { left: '4', top: '-146', width: '0', height: '152' },
          position: { line1: 'right', circle: 'right', text: 'top' },
          textInfo: { content: this.$t('bj30.roof'), pos: { top: '-18', left: '-75' } },
          animate: { line1Target: '.roof .right', line1Animate: { height: '100%' } },
          name: 'roof',
        },
        {
          pointPos: { left: '72%', top: '46.9%' },
          boxPos: { left: '-38', top: '-72', width: '44', height: '75' },
          position: { line1: 'top', line2: 'right', text: 'top' },
          textInfo: { content: this.$t('bj30.L-type') },
          animate: { line1Target: '.hard-top .top', line1Animate: { width: '100%' }, line2Target: '.hard-top .right', line2Animate: { height: '100%' } },
          name: 'hard-top',
        },
        {
          pointPos: { left: '75.5%', top: '50.4%' },
          boxPos: { left: '5', top: '-172', width: '70', height: '179' },
          position: { line1: 'right', line2: 'bottom', circle: 'right', text: 'top' },
          textInfo: { content: this.$t('bj30.PersonalityMark'), pos: { top: '-37', left: '-80' } },
          name: 'mark',
          animate: { line1Target: '.mark .right', line1Animate: { height: '100%' }, line2Target: '.mark .bottom', line2Animate: { width: '100%' } },
        },
        {
          pointPos: { left: '19.9%', top: '55%' },
          boxPos: { left: '-58', top: '-150', width: '64', height: '155' },
          position: { line1: 'top', line2: 'right', text: 'top' },
          textInfo: { content: this.$t('bj30.Personality'), pos: { top: '-38', left: '-10' } },
          name: 'lamp',
          animate: { line1Target: '.lamp .top', line1Animate: { width: '100%' }, line2Target: '.lamp .right', line2Animate: { height: '100%' } },
        },
        {
          pointPos: { left: '34.2%', top: '61.22%' },
          boxPos: { left: '14', top: '7', width: '0', height: '150' },
          position: { line1: 'top', line2: 'left-reverse', circle: 'right-bottom', text: 'bottom' },
          textInfo: { content: this.$t('bj30.Scratch-proof'), pos: { top: '10', left: '-70' } },
          name: 'scratch',
          animate: { line1Target: '.scratch .left-reverse', line1Animate: { height: '100%' }, line2Target: '.scratch .top', line2Animate: { width: '100%' } },
        },
        {
          pointPos: { left: '34.2%', top: '61.22%' },
          boxPos: { left: '-19', top: '-20', width: '34', height: '105' },
          position: { line1: 'left-reverse', line2: 'bottom' },
          name: 'scratch1',
          animate: { line1Target: '.scratch1 .bottom', line1Animate: { width: '100%' }, line2Target: '.scratch1 .left-reverse', line2Animate: { height: '100%' } },
        },
        {
          type: 'angle',
          pointPos: { left: '18.5%', top: '65.4%' },
          textInfo: { content: this.$t('bj30.Approach'), pos: { top: '39', left: '9' } },
          name: 'angle-left',
          imgUrl: require('~/assets/image/vehicleModel/bj30/25度.png'),
          animate: { angleTarget: '.angle-left .angle' },
        },
        {
          type: 'angle',
          pointPos: { left: '76.9%', top: '65%' },
          textInfo: { content: this.$t('bj30.Departure'), pos: { top: '42', left: '-13' } },
          name: 'angle-right',
          imgUrl: require('~/assets/image/vehicleModel/bj30/30度.png'),
          animate: { angleTarget: '.angle-right .angle' },
        },
      ],
    }
  },
  watch: {},
  mounted() {},
  methods: {
    startAnimate() {
      this.$nextTick(() => {
        const tl = gsap.timeline({
          defaults: { duration: 0.3, ease: 'power2.inOut' },
          scrollTrigger: {
            trigger: '.car-side-wrap',
            // 元素到视口50%位置时，开始动画
            start: 'top 50%',
            onEnter: () => tl.play(), // 元素进入视口时播放动画
            // markers: true
          },
        })
        const media = ['xs', 'sm', 'md']
        const list = media.includes(this.$mq) ? this.mobilePositionList : this.positionList
        list.forEach(item => {
          const angle = item.animate.angleTarget
          if (angle) {
            // 角度
            tl.to(`${angle}`, { opacity: 1 })
            // 文本
            tl.to(`${angle} .text`, { opacity: 1 })
          } else {
            const className = item.animate.line1Target || item.animate.line2Target
            const target = className.split(' ')[0]
            console.log(target, 'target')

            // 文本、实心圆点
            if (document.querySelector(`${target} .text`)) {
              tl.to(`${target} .text`, { opacity: 1 })
              tl.to(`${target} .circle`, { opacity: 1 })
            }

            // 线条1
            if (item.animate.line1Target) {
              tl.to(`${item.animate.line1Target}`, item.animate.line1Animate)
            }
            // 线条2
            if (item.animate.line2Target) {
              tl.to(`${item.animate.line2Target}`, item.animate.line2Animate)
            }

            // 波纹圆点
            if (document.querySelector(`${target} .circle-dots`)) {
              tl.to(`${target} .circle-dots`, { opacity: 1 })
            }
          }
        })
        tl.paused()
      })
    },
  },
}
</script>

<style scoped lang="scss">
.car-side-wrap {
  position: relative;
  img {
    width: 100%;
  }
  ::v-deep .point-container {
    .angle-left .angle {
      left: -216px;
    }
    .angle-right .angle {
      left: -31px;
    }
    .mark {
      .text {
        text-align: left;
      }
    }
    .scratch1 {
      .circle-dots {
        left: 8px;
      }
    }
    .scratch2 {
      .circle-dots {
        top: -54px;
        left: -72px;
        width: 18px;
        opacity: 0;
        // opacity: 1;
      }
    }
    @media screen and (max-width: 1200px) {
      .angle-left .angle {
        left: -70px;
      }
      .angle-right .angle {
        left: 0;
      }

      .mark .text {
        width: 92px;
        text-align: right;
      }
      .lamp .text {
        text-align: left;
      }
      .scratch .circle-dots {
        display: block;
        left: 5px;
      }
      .scratch1 .circle-dots {
        top: -29px;
        left: -27px;
      }
    }
  }
}
.lang___ar {
  .car-side-wrap {
    ::v-deep .point-container {
      .mark {
        .text {
          text-align: right;
          right: -160px;
          top: -15px;
          right: -132px;
          width: 150px;
        }
      }
      .scratch .text {
        top: 11px;
        right: -155px;
        left: auto;
      }
      .angle .text {
        text-align: center;
      }
      @media screen and (max-width: 1200px) {
        .roof {
          .text {
            right: -75px;
          }
        }
        .hard-top {
          .text {
            width: 88px;
            right: -57px;
            top: -40px;
          }
        }
        .mark {
          .text {
            width: 80px;
            right: 0;
          }
        }
        .scratch {
          .text {
            right: -60px;
          }
        }
        .angle {
          .text {
            text-align: right;
          }
        }
      }
    }
  }
}
.car-side-wrap-es {
  ::v-deep .point-container {
    .point {
      .text {
        top: -26px;
        width: 343px;
        font-size: 18px;
      }
    }

    .mark .text {
      left: 10px;
      width: 190px;
    }
    .scratch .text {
      top: -9px !important;
      left: -95px !important;
    }
    @media screen and (max-width: 1200px) {
      .point {
        .text {
          font-size: 12px;
          width: 183px;
        }
        .text-top {
          left: -50px;
        }
      }
      .scratch .text {
        top: 4px !important;
        left: -95px !important;
      }
      .roof {
        .text {
          top: -39px !important;
          left: -65px !important;
          width: 120px;
        }
      }
      .mark {
        .text {
          top: -37px;
          left: -116px !important;
          width: 130px;
        }
      }
      .angle-left .text {
        width: 108px;
      }
    }
  }
}
</style>
