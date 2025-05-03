<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <MdImgUploaderOutput :value="finalData.image" class="md-news-image-full" width="1920" height="650" />
  </div>
  <!-- <div class="md-news-image-full" :style="imgWrappStyle">
    <img
      :src="getImageUrl()"
      :alt="finalData.image.alt"
      :width="finalData.image.width"
      :height="finalData.image.height"
      :style="imgStyle"
    >
  </div> -->
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
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsImageFull',
  props: {
    // data 为当前模块的存储数据
    data: {
      type: Object,
      default: () => {
        return defaultData
      }
    },
    imgBaseUrl: {
      type: String,
      default: () => {
        return ''
      }
    }
  },
  data () {
    return {
    }
  },
  async fetch () {
    // 这个请求，在SSG阶段执行
    // this.fetchData = await this.$axios.$get('/apis/public/news')
  },
  computed: {
    finalData () {
      return _.defaults(this.data, defaultData)
    }
  },
  watch: {

  },
  mounted () {
    // this.ajax()
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
.md-news-image-full {
  padding-top: 1em;
  padding-bottom: 1em;
  ::v-deep >img, ::v-deep >video {
    width: 100%;
    height: auto;
    object-fit: contain;
  }
}
@media only screen and (max-width: 767px) {
  .md-news-image-full {
    padding-top: .5em;
    padding-bottom: .5em;
  }
}
</style>
