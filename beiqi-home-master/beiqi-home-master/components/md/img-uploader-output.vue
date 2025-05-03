<!-- 用于显示上传的图片 -->
<template>
  <div :style="imgWrappStyle" class="ed-md-imguploader-output" @touchstart="videoControl">
    <img v-if="!value.isVideo" :alt="value.alt" :class="{ 'img-uploader-output-empty': true }" :height="value.height"
         :src="getImageUrl()"
         :style="imgStyle" :width="value.width"/>
    <video v-if="value.isVideo" ref="video" :autoplay="value.isVideoAutoPlay"
           :class="{ 'img-uploader-output-empty': true }" :loop="value.isVideoAutoPlay" :muted="value.isVideoAutoPlay"
           :playsinline="value.isVideoAutoPlay" :poster="value.src ? value.src : ''" :src="getVideoUrl()"
           :style="imgStyle"
           :webkit-playsinline="value.isVideoAutoPlay" controls preload="auto" x5-video-player-type="h5"/>
  </div>
</template>

<script>
const defaultSvg =
  '<svg viewBox="-512 -512 2048 2048" version="1.1" xmlns="http://www.w3.org/2000/svg" width="200" height="200"><path d="M216.327 223.774h616.465q27.01 0 46.076 19.066t19.066 46.076v467.115q0 27.01-19.066 46.076t-46.076 19.066H216.327q-26.216 0-46.076-19.066-19.066-19.066-19.066-46.076V591.587v-47.665-30.982-45.282-178.743q0-27.01 19.066-46.076 19.86-19.066 46.076-19.066z m0 631.559h616.465q41.31 0 69.908-28.599 27.804-28.599 27.804-69.114V289.71q0-40.515-27.804-69.114-28.599-28.599-69.908-28.599H216.327q-40.515 0-69.114 28.599-28.599 28.599-28.599 69.114V757.619q0 39.721 28.599 68.32 28.599 29.393 69.114 29.393z m46.076-429.778q0 28.599 19.86 48.459 20.655 20.655 49.254 20.655 28.599 0 49.254-20.655 19.86-19.86 19.86-48.459 0-28.599-19.86-48.459-20.655-20.655-49.254-20.655-28.599 0-49.254 20.655t-19.86 48.459z m32.571 0q0-15.094 11.122-26.216 10.327-10.327 25.421-10.327t25.421 10.327q11.122 11.122 11.122 26.216 0 15.094-11.122 26.216-10.327 10.327-25.421 10.327-14.299 0-25.421-10.327t-11.122-26.216z m368.608 67.526L835.97 665.469q5.561 5.561 5.561 11.122 0 6.355-5.561 11.916-5.561 6.355-11.916 6.355-5.561 0-11.122-6.355L640.544 516.119q-8.739-8.739-22.244-8.739-14.299 0-23.832 8.739-46.076 46.076-138.228 137.434-19.066 19.066-46.87 19.066-27.01 0-46.87-19.066-7.15-7.15-21.449-22.244-9.533-8.739-23.832-8.739-13.505 0-22.244 8.739l-57.198 57.198q-11.122 11.916-23.038 0-5.561-5.561-5.561-11.122t5.561-11.916l57.198-57.198q19.066-19.066 46.87-19.066 27.01 0 46.87 19.066 7.15 7.15 21.449 22.244 8.739 8.739 22.244 8.739 14.299 0 23.832-8.739 45.282-46.076 136.639-137.434 19.86-19.066 47.665-19.066 27.01 0 46.076 19.066z" fill="#909399"></path></svg>'
export default {
  name: 'MdImgUploaderOutput',
  props: {
    defaultImg: undefined,
    // 默认图片的宽度和高度
    // eslint-disable-next-line vue/require-default-prop
    width: String,
    // eslint-disable-next-line vue/require-default-prop
    height: String,
    value: {
      type: Object,
      default() {
        return {
          alignment: undefined,
          alt: undefined,
          width: undefined,
          height: undefined,
          outerWidth: undefined,
          src: undefined,
          videoSrc: undefined,
          isVideo: undefined,
          isVideoAutoPlay: true,
        }
      },
    },
  },
  computed: {
    imgWrappStyle() {
      return {
        justifyContent: this.value.alignment,
        width: this.value.alignment ? '100%' : undefined,
        display: this.value.alignment ? 'flex' : undefined,
      }
    },
    imgStyle() {
      return {
        width: this.value?.outerWidth < 0 ? undefined : this.value?.outerWidth + '%',
        height: this.value?.outerWidth < 0 ? undefined : 'auto',
      }
    },
  },
  methods: {
    getVideoUrl() {
      return process.env.imgBaseUrl + this.value?.videoSrc
    },
    getImageUrl() {
      const src = this.value?.src
      if (src) {
        if (src.indexOf('blob:') === 0) {
          return src
        }
        return process.env.imgBaseUrl + src
      } else {
        let newDefaultSvg = defaultSvg
        if (this.width) {
          newDefaultSvg = newDefaultSvg.replace('width="200"', `width="${this.width}"`)
        }
        if (this.height) {
          newDefaultSvg = newDefaultSvg.replace('height="200"', `height="${this.height}"`)
        }
        newDefaultSvg = 'data:image/svg+xml;base64,' + window.btoa(newDefaultSvg)
        return this.defaultImg || newDefaultSvg
      }
    },
    // 播放视频
    videoControl() {
      this.$refs.video?.play()
    },
  },
  activated() {
    this.videoControl()
  },
}
</script>

<style lang="scss" scoped>
.ed-md-imguploader-output {
  img,
  video {
    max-width: 100%;
    display: block;
    height: auto;
  }
}

.img-uploader-output-empty {
  background: #ededed;
}
</style>
