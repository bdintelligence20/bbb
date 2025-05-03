<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <el-row type="flex" class="max-1200 md-news-image-large" :style="SpaceStyle">
    <el-col
      :span="24"
      class="md-news-image-large-image"
    >
      <MdImgUploaderOutput :value="finalData.image" width="1920" height="600" />
    </el-col>
    <el-col
      :span="24"
      class="md-news-image-large-text"
    >
      <md-style-text-output :data="finalData.text" />
    </el-col>
  </el-row>
  </div>
</template>

<script>
import _ from 'lodash'

// 默认数据
const defaultData = {
  image: {
    src: '',
    width: undefined,
    height: undefined,
    alt: 'news image'
  },
  text: {
    value: '',
    style: {}
  },
  textPicSpace: 1,
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsImageLarge',
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
        gap: this.finalData.textPicSpace + 'em'
      }
    }
  },
  watch: {

  },
  mounted () {
    // this.ajax()
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.md-news-image-large {
  padding-top: 1em;
  padding-bottom: 1em;
  flex-direction: column;
  gap: 1em;
  .md-news-image-large-image {
    ::v-deep >div>img, ::v-deep >div>video {
      width: 100%;
      height: auto;
      object-fit: contain;
    }
  }
  .md-news-image-large-text {
    width: 100%;
    height: auto;
    object-fit: contain;
  }
}
@media only screen and (max-width: 767px) {
  .md-news-image-large {
    padding-top: .5em;
    padding-bottom: .5em;
    gap: 1em;
  }
}
</style>
