<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <el-row type="flex" class="max-1200 md-news-image-text" :class="finalData.reverse ? 'reverse' : undefined" :style="{ alignItems:finalData.alignItems }">
    <el-col
      :span="24"
      class="md-news-image-text-image"
    >
      <MdImgUploaderOutput :value="finalData.image" width="960" height="500" />
      <!-- <img
        :src="getImageUrl()"
        :alt="finalData.image.alt"
        :width="finalData.image.width"
        :height="finalData.image.height"
        :style="imgStyle"
      > -->
    </el-col>
    <el-col
      :span="24"
      class="md-news-image-text-text"
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
  reverse: false,
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsImageText',
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
.md-news-image-text.reverse {
  flex-direction: row-reverse;
}
.md-news-image-text {
  padding-top: 1em;
  padding-bottom: 1em;
  gap: 2em;

  .md-news-image-text-image {
    ::v-deep >img,::v-deep >video {
      width: 100%;
      height: auto;
      object-fit: contain;
    }
  }
  .md-news-image-text-text {
    width: 100%;
    height: auto;
    text-align: justify;
  }
}
@media only screen and (max-width: 767px) {
  .md-news-image-text.reverse {
    flex-direction: column-reverse;
  }
  .md-news-image-text {
    padding-top: .5em;
    padding-bottom: .5em;
    flex-direction: column;
    gap: 1em;
  }
}
</style>
