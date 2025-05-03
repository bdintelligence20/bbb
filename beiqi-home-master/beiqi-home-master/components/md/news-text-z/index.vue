<template>
  <div :class="{'dark-theme': finalData.darkTheme}">
    <div class="max-1200 md-news-text-z">
      <el-row type="flex">
        <el-col :xs="{span:24}" :sm="{span:12}" :offset="finalData.reverse * 12">
          <md-style-text-output :data="finalData.text1" />
        </el-col>
      </el-row>
      <el-row type="flex">
        <el-col :xs="{span:24}" :sm="{span:12}" :offset="!finalData.reverse * 12">
          <md-style-text-output :data="finalData.text2" />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import _ from 'lodash'

// 默认数据
const defaultData = {
  text1: {
    value: '',
    style: {}
  },
  text2: {
    value: '',
    style: {}
  },
  reverse: false,
  darkTheme: false
}
export default {
  // 组件名称
  name: 'MdNewsTextZ',
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
.md-news-text-z {
  gap: 2em;

  .el-row {
    padding-top: 1em;
    padding-bottom: 1em;
    text-align: justify;

    .el-col-offset-12 {
      padding-left: 1em;
    }
    .el-col-offset-0 {
      padding-right: 1em;
    }
  }
}

@media only screen and (max-width: 767px) {
  .md-news-text-z {
    gap: 1em;
    .el-row {
      padding-top: .5em;
      padding-bottom: .5em;

      .el-col-offset-12 {
        margin: 0;
      }

      .el-col-offset-12 {
        padding-left: 0;
      }
      .el-col-offset-0 {
        padding-right: 0;
      }
    }
  }
}
</style>
