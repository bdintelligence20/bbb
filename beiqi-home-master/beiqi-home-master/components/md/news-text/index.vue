<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <md-style-text-output :data="finalData.text" class="max-1200 md-news-text" />
  </div>
</template>

<script>
import _ from 'lodash'

// 默认数据
const defaultData = {
  text: {
    value: '',
    style: {}
  },
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsText',
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
  }
}
</script>

<style lang="scss" scoped>
.md-news-text {
  padding-top: 1em;
  padding-bottom: 1em;
}

@media only screen and (max-width: 767px) {
  .md-news-text {
    padding-top: .5em;
    padding-bottom: .5em;
  }
}
</style>
