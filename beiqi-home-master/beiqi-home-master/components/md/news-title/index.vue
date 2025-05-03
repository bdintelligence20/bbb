<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <md-style-text-output :data="finalData.title" class="max-1200 md-news-title" :style="newsStyle" />
  </div>
</template>

<script>
import _ from 'lodash'

// 默认数据
const defaultData = {
  title: {
    value: 'place input the title'
  },
  scpaceTop: 1,
  scpaceBottom: 1,
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsTitle',
  props: {
    // data 为当前模块的存储数据
    data: {
      type: Object,
      default: () => {
        return {}
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
    newsStyle () {
      return {
        paddingTop: (this.finalData.scpaceTop !== undefined) ? this.finalData.scpaceTop + 'em' : '',
        paddingBottom: (this.finalData.scpaceBottom !== undefined) ? this.finalData.scpaceBottom + 'em' : ''
      }
    }
  },
  watch: {

  },
  mounted () {
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
.md-news-title {
  font-size: 2.18em;
  font-weight: 700;
}
</style>
