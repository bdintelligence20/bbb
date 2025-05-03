<!--
 * @Author: 不二
 * @Date: 2023-12-15 17:08:19
 * @LastEditTime: 2023-12-15 18:06:54
-->
<template>
    <div class="video-container">
      <mq-layout mq="lg+">
      <video src="/bj60/index.mp4" class="video" width="100%" data-type="mp4" x5-video-player-type="h5" loop muted webkit-playsinline playsinline autoplay ref="video"></video>
    </mq-layout>
    <mq-layout :mq="['xs', 'sm', 'md']">
      <video src="/bj60/index_m.mp4" class="video" width="100%" data-type="mp4" x5-video-player-type="h5" loop muted webkit-playsinline playsinline autoplay ref="video"></video>
    </mq-layout>
    </div>
  </template>
  
  <script>
  export default {

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
  
      },
    },
  }
  </script>
  
  <style lang="scss" scoped>
  .xl,
  .lg {
    .video-container {

      width: 100vw;
      background-color: #000;
      video {
        width: 100vw;
        // height: 80vh;
        object-fit: cover;
      }
    }
  }

  </style>
  