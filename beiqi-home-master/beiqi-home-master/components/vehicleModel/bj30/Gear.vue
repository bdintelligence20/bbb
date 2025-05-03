<template>
  <!-- 档把动效 -->
  <VehicleModelBj30SellPointImage :image-item="gear" class="gear-lever-container">
    <!-- 向前动画 -->
    <video ref="forwardVideo" src="/bj30/前.mp4" width="100%" data-type="mp4" x5-video-player-type="h5" muted webkit-playsinline playsinline :poster="poster" @ended="onForwardEnded"></video>
    <!-- 向后动画 -->
    <video v-show="isPlayingBackward" ref="backwardVideo" src="/bj30/后.mp4" class="gear-video" width="100%" data-type="mp4" x5-video-player-type="h5" muted webkit-playsinline playsinline :poster="poster" @ended="onBackwardEnded"></video>

    <div class="gear-btn-container">
      <div class="gear-btn forward-btn" :class="{ 'gear-btn-active': isPlayingForward }">
        <img v-show="!isPlayingForward" src="~/assets/image/vehicleModel/bj30/R.png" alt="" @click="playForwardVideo" />
        <img v-show="isPlayingForward" src="~/assets/image/vehicleModel/bj30/R-亮.png" alt="" />
      </div>

      <div class="gear-btn backward-btn" :class="{ 'gear-btn-active': isPlayingBackward }">
        <img v-show="!isPlayingBackward" src="~/assets/image/vehicleModel/bj30/D.png" alt="" @click="playBackwardVideo" />
        <img v-show="isPlayingBackward" src="~/assets/image/vehicleModel/bj30/D-亮.png" alt="" />
      </div>
    </div>
  </VehicleModelBj30SellPointImage>
</template>

<script>
export default {
  props: {
    gear: {
      type: Object,
      default() {
        return {}
      },
    },
  },
  data() {
    return {
      // 是否在播放向前动画
      isPlayingForward: false,
      // 是否在播放向后动画
      isPlayingBackward: false,
      poster: require('~/assets/image/vehicleModel/bj30/档把-背景.jpg'),
    }
  },
  mounted() {},
  methods: {
    // 播放向前动画
    playForwardVideo() {
      // 停止播放向后动画
      if (this.isPlayingBackward) {
        this.isPlayingBackward = false
        this.$refs.backwardVideo.pause()
        // 重置视频播放的当前位置
        this.$refs.backwardVideo.currentTime = 0
      }
      this.$nextTick(() => {
        this.isPlayingForward = true
        this.$refs.forwardVideo.play()
      })
    },
    // 播放向后动画
    playBackwardVideo() {
      if (this.isPlayingForward) {
        this.isPlayingForward = false
        this.$refs.forwardVideo.pause()
        this.$refs.forwardVideo.currentTime = 0
      }
      this.$nextTick(() => {
        this.isPlayingBackward = true
        this.$refs.backwardVideo.play()
      })
    },
    // 向前动画播放完
    onForwardEnded() {
      this.isPlayingForward = false
    },
    // 向后动画播放完
    onBackwardEnded() {
      this.isPlayingBackward = false
    },
  },
}
</script>

<style scoped lang="scss">
.gear-lever-container {
  ::v-deep .image-container {
    position: relative;
    overflow: hidden;
    line-height: 0;
    aspect-ratio: 1920 / 1132;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
    .gear-video {
      position: absolute;
      top: 0;
      left: 0;
      height: 100%;
      object-fit: cover;
    }
  }
}

.gear-btn-container {
  position: absolute;
  bottom: 12%;
  z-index: 2;
  width: 100%;
}
.gear-btn {
  position: absolute;
  transform: translate(-50%, -50%);
  width: 6%;
}
.gear-btn-active {
  width: 8%;
}
.forward-btn {
  left: 30%;
}
.backward-btn {
  left: 59%;
}
@media screen and (max-width: 1200px) {
  .gear-btn-container {
    bottom: 14%;
  }
  .gear-btn {
    width: 7%;
  }
  .gear-btn-active {
    width: 9%;
  }
}
</style>
