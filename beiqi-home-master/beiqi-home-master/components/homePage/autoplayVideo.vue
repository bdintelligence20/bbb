<!--
 * @Author: 不二
 * @Date: 2023-08-31 19:13:46
 * @LastEditTime: 2024-02-19 09:36:29
-->
<template>
  <div v-if="videoId && videoUrl" class="video-container">
    <video :id="videoId" :src="videoUrl" class="video" width="100%" data-type="mp4" x5-video-player-type="h5" loop muted webkit-playsinline playsinline autoplay ref="video"></video>
  </div>
</template>

<script>
export default {
  props: {
    videoId: {
      type: String,
    },
    videoUrl: {
      type: String,
    },
  },
  data() {
    return {}
  },
  created() {},
  mounted() {
    // window.addEventListener('touchmove', this.videoControl)
  },
  destroyed() {
    // window.removeEventListener('touchmove', this.videoControl)
  },
  methods: {
    videoControl() {
      if (!this.$refs.video) return
      this.$refs.video.play()

      return
      //先不要这个
      const { top, bottom } = this.$refs.video.getBoundingClientRect()
      const clientHeight = document.documentElement.clientHeight || document.body.clientHeight
      //判断视频是否在可视区域内
      if (top < clientHeight && bottom > 0) {
        this.$refs.video.play()
      } else {
        this.$refs.video.pause()
      }
    },
  },
}
</script>

<style lang="scss" scoped>
.xl,
.lg {
  .video-container {
    video {
      width: 100vw;
      height: 100vh;
      object-fit: cover;
    }
  }
}
.md,.xs,.sm{
  .video-container {
    video {
      width: 100vw;
      height: 55.9vw;
      object-fit: cover;
    }
  }
}
</style>
