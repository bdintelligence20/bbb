<template>
  <div v-loading="loading" class="car-360">
    <div
      v-if="imgList.length > 0"
      ref="wrapper"
      class="wrapper"
      @mousedown="startRotation"
      @mousemove="handleMove"
      @mouseup="stopRotation"
      @mouseleave="handleMouseleave"
      @touchstart="startRotation"
      @touchmove="handleMove"
      @touchend="stopRotation"
    >
      <img :src="currentImage" alt="car" :width="carWidth" class="car-img" :style="carStyle" />
      <el-image
        ref="mouseIcon"
        :src="require('~/assets/image/home/360.png')"
        alt
        class="mouse-icon"
        :style="`top: ${top}px; left: ${left}px`"
        :class="{ 'show-icon': showIcon }"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: 'BeiqiHomeMasterCar360',
  props: {
    path: {
      type: String,
      default: '',
    },
    images: {
      type: Array,
      default() {
        return []
      }
    },
    carWidth: {
      type: String,
      default: '100%'
    },
    position: {
      type: Object,
      default() {
        return {};
      }
    }
  },

  data() {
    return {
      currentIndex: 0, // 当前显示的图片索引
      isRotating: false, // 是否正在旋转
      startX: 0, // 开始拖动的X坐标
      top: 0,
      left: 0,
      showIcon: false,
      loading: true,
      isWap: null,
      imgList: []
    }
  },
  computed: {
    currentImage() {
      return this.imgList[this.currentIndex].getAttribute('src')
    },
    carStyle() {
      return {
        top: this.position.top,
        left: this.position.left,
      }
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(value, oldValue) {
        if (value === 'xs') {
          this.isWap = true
        } else {
          this.isWap = false
        }
      },
    },
  },

  mounted() {
    this.loadImages()
  },

  methods: {
    loadImages() {
      this.imagesLoader(this.images).then(res => {
        this.imgList = res
        this.loading = false
      })
    },
    imagesLoader(imgs) {
      const promises = []
      if (imgs) {
        imgs.map(src => {
          promises.push(
            new Promise((resolve, reject) => {
              const img = new Image()
              img.src = src
              img.onload = () => {
                resolve(img)
              }
              img.onerror = () => {
                reject(src)
              }
            })
          )
        })
      }
      return Promise.all(promises)
    },
    startRotation(event) {
      event.preventDefault()
      // 开始旋转
      this.isRotating = true
      // 设置起始点
      this.startX = event.pageX || event.touches?.[0].pageX
    },
    stopRotation(e) {
      // 停止旋转
      this.isRotating = false
    },
    handleMouseleave() {
      this.isRotating = false
    },
    handleMove(e) {
      this.updatePosition(e)
      this.rotateImage(e)
    },
    updatePosition(e) {
      const { top, left, width, height } = this.$refs.wrapper.getBoundingClientRect()
      const rightDistance = width - 70
      const bottomDistance = height - 70
      this.left = (e.clientX || e.touches?.[0].clientX) - left - 35
      this.top = (e.clientY || e.touches?.[0].clientY) - top - 35
      if (!this.isWap) {
        if (this.left < 0 || this.left > rightDistance || this.top < 0 || this.top > bottomDistance) {
          this.showIcon = false
        } else {
          this.showIcon = true
        }
      }
    },
    rotateImage(event) {
      if (!this.isRotating) return
      const pageX = event.pageX || event.touches?.[0].pageX
      const moveX = pageX - this.startX // 计算本次事件和起始点的差值
      const speed = 6 // 滑动速度
      const moveIndex = Math.round(moveX / speed) // 计算应该移动的图片索引数

      let newIndex = this.currentIndex + moveIndex
      if (newIndex >= 36) {
        newIndex %= 36
      } else if (newIndex < 0) {
        newIndex = 36 + newIndex // 处理负数索引
      }

      if (newIndex !== this.currentIndex) {
        this.currentIndex = newIndex
        this.startX = pageX // 更新起始点，为下一次移动做准备
      }
      event.preventDefault()
    },
  },
}
</script>

<style lang="scss" scoped>
.car-360 {
  height: 100%;
  ::v-deep {
    .el-loading-mask {
      background: none;
    }
  }
}
.wrapper {
  height: 100%;
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: none;
}
.car-img {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
}
.mouse-icon {
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0;
  width: 70px;
}
.show-icon {
  opacity: 1;
}

.rotate-icon {
  width: 28px;
  position: absolute;
  left: 14%;
  top: 0;
}
</style>
