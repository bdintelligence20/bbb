<template>
  <div
    class="point"
    :class="point.name"
    :style="{
      left: point.pointPos.left,
      top: point.pointPos.top,
    }"
  >
    <!-- 波纹圆点  -->
    <div v-if="!point.type" class="line-wrap">
      <div v-if="!point.hideRippleDots" class="circle-dots">
        <img src="~/assets/image/vehicleModel/bj30/圆点.png" alt="" />
      </div>

      <div
        class="box"
        :style="{
          left: `${point.boxPos.left}px`,
          top: `${point.boxPos.top}px`,
          width: `${point.boxPos.width}px`,
          height: `${point.boxPos.height}px`,
        }"
      >
        <!-- 线条1 -->
        <div v-if="point.position.line1" class="border" :class="point.position.line1"></div>
        <!-- 线条2 -->
        <div v-if="point.position.line2" class="border" :class="point.position.line2"></div>
        <!-- 实心圆点 -->
        <div v-if="point?.textInfo?.content" class="circle" :class="point.position.circle ? `circle-${point.position.circle}` : ''">
          <!-- 文本  -->
          <div
            class="text"
            :class="`text-${point.position.text}`"
            :style="{
              top: `${point.textInfo?.pos?.top}px`,
              left: $i18n.locale !== 'ar' && `${point.textInfo?.pos?.left}px`,
            }"
          >
            {{ point.textInfo.content }}
          </div>
        </div>
      </div>
    </div>

    <!-- 角度 -->
    <div v-if="point?.type && point.type === 'angle'" class="angle-wrap">
      <div class="angle">
        <img :src="point?.imgUrl && point.imgUrl" alt="" />
        <span
          class="text"
          :style="{
            top: `${point.textInfo?.pos?.top}px`,
            left: `${point.textInfo?.pos?.left}px`,
          }"
          >{{ point.textInfo.content }}</span
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    /**
     * 定位点数组
     * @typedef {Object} PointItem
     * @property {Object} pointPos - 点的位置，相对于最外边容器(top,left)，使用百分比表示
     * @property {Object} boxPos - 两条线形成的矩形所在位置(top,left)和宽高，单位为px
     * @property {Object} position - 位置: 线条1、线条2、圆点、文本
     * @property {'top' | 'left' | 'right' | 'bottom' | 'top-reverse' | 'left-reverse' | 'right-reverse'} position.line1 - 线条1的位置,线条2同样
     *   - 'top'          : 线条1位于矩形的上边框
     *   - 'left'         : 线条1位于矩形的左边框
     *   - 'right'        : 线条1位于矩形的右边框
     *   - 'bottom'       : 线条1位于矩形的底边框
     *   - 'top-reverse'  : -reverse用于控制线条动画显示的方向，默认从上到下显示，reverse是从下到上
     * @property {'right' | 'right-bottom' } position.circle 圆点位置
     *   - 默认在矩形左上
     *   - right:右上
     *   - right-bottom:右下
     * @property {'top' | 'right' } position.text 文本位置，默认在圆点左边
     * @property {Object} textInfo - 文本: 内容、相对于圆点的位置(top,left)
     * @property {Object} name - 定位点名称，用于区分不同点，可自定义样式
     */
    point: {
      type: Object,
      default() {
        return {}
      },
    },
  },
  data() {
    return {}
  },
  mounted() {},
  methods: {},
}
</script>

<style scoped lang="scss">
.point {
  position: absolute;
  top: 0;
  left: 0;
  width: 10px;
  height: 10px;
}

.box {
  position: absolute;
  width: 100px;
  height: 100px;
  border: 1px solid transparent;
}

.circle-dots {
  position: absolute;
  top: -4px;
  left: -4px;
  opacity: 0;
  width: 20px;
  height: 20px;
  // opacity: 1;
}

.border {
  position: absolute;
}

// 横线
.horizontal-line {
  width: 0;
  // width: 100%;
  height: 1px;
  background-image: url('~/assets/image/vehicleModel/bj30/横线.png');
  background-size: 6px 100%;
  background-repeat: repeat-x;
}

// 竖线
.vertical-line {
  width: 1px;
  height: 0;
  // height: 100%;
  background-image: url('~/assets/image/vehicleModel/bj30/竖线.png');
  background-size: 100% 6px;
  background-repeat: repeat-y;
}

.top {
  @extend .horizontal-line;
  top: -1px;
  left: -1px;
}

// 上边线从右往左
.top-reverse {
  @extend .horizontal-line;
  top: -1px;
  right: -1px;
}

.left {
  @extend .vertical-line;
  left: -1px;
  top: 0;
}

// 左侧线从下往上
.left-reverse {
  @extend .vertical-line;
  left: -1px;
  bottom: 0;
}

.right {
  @extend .vertical-line;
  right: -1px;
  top: 0;
}

// 右侧线从下往上
.right-reverse {
  @extend .vertical-line;
  right: -1px;
  bottom: 0;
}

.bottom {
  @extend .horizontal-line;
  bottom: -1px;
  right: 1px;
}

// 默认圆点在矩形左上
.circle {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background-color: #fff;
  position: absolute;
  top: -4px;
  left: -2px;
  opacity: 0;
  // opacity: 1;
}

// 圆点在矩形右上
.circle-right {
  left: auto;
  right: -4px;
}

// 圆点在矩形右下
.circle-right-bottom {
  top: 97%;
}

// 文字在圆的左边
.text {
  position: absolute;
  width: 263px;
  top: -33px;
  right: 13px;
  opacity: 0;
  color: #fff;
  font-size: 22px;
  text-align: right;
  // opacity: 1;
}

// 文字在圆的上方
.text-top {
  top: -35px;
  right: -153px;
}

// 文字在圆的右边
.text-right {
  left: 18px;
}

.angle-wrap {
  width: 10px;
  height: 10px;

  .angle {
    position: absolute;
    left: 0;
    bottom: -29px;
    width: 230px;
    opacity: 0;

    // opacity: 1;
    .text {
      position: absolute;
      left: -67px;
      top: 133px;
      opacity: 0;
      text-align: left;
      // opacity: 1;
    }
  }
}

@media screen and (max-width: 1200px) {
  .text {
    text-align: center;
    font-size: 12px;
    width: 150px;
  }

  .text-top {
    top: -18px;
    left: -30px;
  }

  .circle-right {
    right: -4px;
  }

  .circle-right-bottom {
    left: -3px;
  }

  .angle-wrap {
    .angle {
      width: 80px;
      top: -22px;

      .text {
        width: 108px;
      }
    }
  }
}
</style>
