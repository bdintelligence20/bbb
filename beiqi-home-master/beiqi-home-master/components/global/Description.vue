<!--
 * Author: SHI
 * Date: 2022/6/20
 * Remark: description 描述类
 -->

<template>
  <div class="description__page ql-editor">
    <div v-if="isHtml" v-html="htmlStr"></div>
  </div>
</template>

<script>
  export default {
    name: 'description',
    props: {
      isHtml: {
        type: Boolean,
        default: true,
      },
      htmlStr: {
        type: String,
        default: '',
      },
    },
    data() {
      return {};
    },
    created() {

    },
    methods: {},
  };
</script>

<style lang='scss'>
  .description__page {
    word-break: break-word;

    // 两端对齐的图片100%显示, block就不会有间隔
    .ql-align-justify img{
      width: 100%;
      display: block;
    }
  }
  .lang___ar{
    .description__page{
      text-align: right;
      direction: rtl;
    }
  }
</style>
