<!-- Please remove this file from your project -->
<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <MdImgUploaderOutput :value="finalData.image" class="max-1200 md-news-image" width="800" height="400" />
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
    outerWidth: undefined,
    alt: 'news image',
    videoSrc: undefined,
    isVideo: false
  },
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsImage',
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
.md-news-image {
  display: flex;
  padding-top: 1em;
  padding-bottom: 1em;
  ::v-deep >img {
    width: auto;
    height: auto;
    object-fit: contain;
  }
}
@media only screen and (max-width: 767px) {
  .md-news-image {
    padding-top: .5em;
    padding-bottom: .5em;
  }
}
</style>
