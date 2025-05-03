<template>
    <div class="video-container">
      <video src="/x7/video.mp4" class="video" width="100%" data-type="mp4" x5-video-player-type="h5" loop muted webkit-playsinline playsinline autoplay ref="video"></video>
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
      video {
        width: 100vw;
        height: 100vh;
        object-fit: cover;
      }
    }
  }
  </style>
  