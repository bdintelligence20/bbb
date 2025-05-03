<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <div class="max-1200 md-news-image-text-3columns">
    <div class="md-news-image-text-col">
      <div class="md-news-image-text-3columns-image">
        <MdImgUploaderOutput :value="finalData.image1" width="640" height="900" />
        <!-- <img :src="getImageUrl(1)" :alt="finalData.image1.alt" :width="finalData.image1.width" :height="finalData.image1.height"> -->
      </div>
      <div class="md-news-image-text-3columns-space" :style="SpaceStyle" />
      <md-style-text-output :data="finalData.text1" class="md-news-image-text-3columns-text" />
    </div>

    <div class="md-news-image-text-3columns-space" />

    <div class="md-news-image-text-col">
      <div class="md-news-image-text-3columns-image">
        <MdImgUploaderOutput :value="finalData.image2" width="640" height="900" />
        <!-- <img :src="getImageUrl(2)" :alt="finalData.image2.alt" :width="finalData.image2.width" :height="finalData.image2.height"> -->
      </div>
      <div class="md-news-image-text-3columns-space" :style="SpaceStyle" />
      <md-style-text-output :data="finalData.text2" class="md-news-image-text-3columns-text" />
    </div>

    <div class="md-news-image-text-3columns-space" />

    <div class="md-news-image-text-col">
      <div class="md-news-image-text-3columns-image">
        <MdImgUploaderOutput :value="finalData.image3" width="640" height="900" />
        <!-- <img :src="getImageUrl(3)" :alt="finalData.image3.alt" :width="finalData.image3.width" :height="finalData.image3.height"> -->
      </div>
      <div class="md-news-image-text-3columns-space" :style="SpaceStyle" />
      <md-style-text-output :data="finalData.text3" class="md-news-image-text-3columns-text" />
    </div>
  </div>
  </div>
</template>

<script>
import _ from 'lodash'

// 默认数据
const defaultData = {
  image1: {
    src: '',
    width: undefined,
    height: undefined,
    alt: 'news image'
  },
  text1: {
    value: '',
    style: {}
  },
  image2: {
    src: '',
    width: undefined,
    height: undefined,
    alt: 'news image'
  },
  text2: {
    value: '',
    style: {}
  },
  image3: {
    src: '',
    width: undefined,
    height: undefined,
    alt: 'news image'
  },
  text3: {
    value: '',
    style: {}
  },
  textPicSpace: 1,
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsImageText3columns',
  props: {
    // data 为当前模块的存储数据
    data: {
      type: Object,
      default: () => {
        return defaultData
      }
    }
  },
  data () {
    return {
    }
  },
  async fetch () {
    // 这个请求，在SSG阶段执行
  },
  computed: {
    finalData () {
      return _.defaults(this.data, defaultData)
    },
    SpaceStyle () {
      return {
        height: this.finalData.textPicSpace + 'em'
      }
    }
  },
  watch: {

  },
  mounted () {
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.md-news-image-text-3columns {
  width: 100%;
  position: relative;
  z-index: 1;
  display: flex;
  justify-content: space-around;
  padding-top: 1em;
  padding-bottom: 1em;
  .md-news-image-text-col {
    display: flex;
    flex-direction: column;
    flex: 1;
    .md-news-image-text-3columns-image {
      flex: 1;
      ::v-deep >div>img,::v-deep >div>video {
        width: 100%;
        height: auto;
        object-fit: contain;
      }
    }
    .md-news-image-text-3columns-text {
      flex: 1;
      width: 100%;
      height: auto;
      object-fit: contain;
      text-align: center;
    }
  }
  .md-news-image-text-3columns-space {
    width: 1em;
    height: 1em;
  }
}
</style>
