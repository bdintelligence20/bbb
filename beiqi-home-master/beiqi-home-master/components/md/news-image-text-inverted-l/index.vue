<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <el-row type="flex" class="max-1200 md-news-image-text-inverted-l">
    <!-- 左侧 -->
    <el-col :xs="{span:24}" :sm="{span:12}" class="text-and-image-small">
      <!-- 左侧文本 -->
      <div class="text-with-bg">
        <!-- 左侧title -->
        <md-style-text-output :data="finalData.title" class="title" />
        <!-- 大图作为左侧背景 -->
        <MdImgUploaderOutput :value="finalData.imageLarge" class="image-large-as-bg" width="960" height="1280" />
        <!-- <img
          :src="getImageUrl('imageLarge')"
          :alt="finalData.imageLarge.alt"
          :width="finalData.imageLarge.width"
          :height="finalData.imageLarge.height"
          class="image-large-as-bg"
        > -->
        <!-- 文本内容 -->
        <md-style-text-output :data="finalData.content" class="content" />
      </div>
      <!-- 左侧小图 -->
      <el-row type="flex" class="image-small">
        <el-col :xs="{span:24}" :sm="{span:12}">
          <MdImgUploaderOutput :value="finalData.imageSmall1" width="500" height="500" />
          <!-- <img :src="getImageUrl('imageSmall1')" :alt="finalData.imageSmall1.alt" :width="finalData.imageSmall1.width" :height="finalData.imageSmall1.height"> -->
        </el-col>
        <el-col :xs="{span:24}" :sm="{span:12}">
          <MdImgUploaderOutput :value="finalData.imageSmall2" width="500" height="500" />
          <!-- <img :src="getImageUrl('imageSmall2')" :alt="finalData.imageSmall2.alt" :width="finalData.imageSmall2.width" :height="finalData.imageSmall2.height"> -->
        </el-col>
      </el-row>
    </el-col>

    <!-- <div class="md-news-image-text-inverted-l-space" /> -->

    <!-- 右侧 -->
    <el-col class="image-large" :xs="{span:24}" :sm="{span:12}">
      <!-- 右侧大图 -->
      <MdImgUploaderOutput :value="finalData.imageLarge" class="image-large-as-bg" width="960" height="1280" />
      <!-- <img :src="getImageUrl('imageLarge')" :alt="finalData.imageLarge.alt" :width="finalData.imageLarge.width" :height="finalData.imageLarge.height"> -->
    </el-col>
  </el-row>
  </div>
</template>

<script>
import _ from 'lodash'
import defaultImg from './empty-image.png'
import defaultImgSmall from './empty-image-small.png'

// 默认数据
const defaultData = {
  title: {
    value: '',
    style: {}
  },
  content: {
    value: '',
    style: {}
  },
  imageLarge: {
    src: '',
    width: undefined,
    height: undefined,
    alt: 'news image'
  },
  imageSmall1: {
    src: '',
    width: undefined,
    height: undefined,
    alt: 'news image'
  },
  imageSmall2: {
    src: '',
    width: undefined,
    height: undefined,
    alt: 'news image'
  },
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsImageTextInvertedL',
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
    }
  },
  watch: {

  },
  mounted () {
  },
  methods: {
    getImageUrl (index) {
      if (this.finalData?.[index]?.src) {
        if (this.finalData?.[index]?.src.indexOf('blob:') === 0) {
          return this.finalData?.[index]?.src
        }
        return process.env.imgBaseUrl + this.finalData?.[index]?.src
      } else {
        if (index === 'imageLarge') {
          return defaultImg
        }
        return defaultImgSmall
      }
    }
    // ajax () {
    //   // 这个请求，在浏览器执行
    // }
  }
}
</script>

<style lang="scss" scoped>
.md-news-image-text-inverted-l {
  padding-top: 1em;
  padding-bottom: 1em;
  gap: 2em;
  .text-and-image-small{
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    gap: 1em;
    .text-with-bg{
      flex: 1;
      .title {
        font-size: 2.18em;
        margin-bottom: 1em;
        text-transform: uppercase;
        font-weight: normal;
      }
      .content {
        display: block;
        text-align: justify;
      }
      .image-large-as-bg {
        ::v-deep >img, ::v-deep >video{
          display: none;
          width: 100%;
          height: auto;
        }
      }
    }
    .image-small{
      display: flex;
      justify-content: space-around;
      align-items: flex-end;
      grid-gap: 1em;
      gap: 1em;
      ::v-deep >div>div>img, ::v-deep >div>div>video {
        width: 100%;
        height: auto;
      }
    }
  }
  .image-large {
    ::v-deep >div>img, ::v-deep >div>video{
      width: 100%;
      height: auto;
    }
  }
}

@media only screen and (max-width: 767px) {
  .md-news-image-text-inverted-l {
    padding-top: .5em;
    padding-bottom: .5em;
    gap: 1em;

  .text-and-image-small {
    .text-with-bg {
      position: relative;
      .title {
        padding: 1em;
        font-size: 1.5em;
        margin-bottom: 0;
      }
      .content {
        margin-top: 1em;
      }
      .image-large-as-bg {
        ::v-deep >img, ::v-deep >video{
          display: block;
          width: 100%;
        }
      }
    }
  }
    .image-large {
      display: none;
    }
  }
}
</style>
