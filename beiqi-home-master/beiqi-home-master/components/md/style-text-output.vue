<!-- 用于显示带样式的文本 -->
<template>
  <div :style="data.style" class="md-style-text-output" v-html="replaceBr(data.value)" />
</template>

<script>
export default {
  name: 'MdStyleTextOutput',
  props: {
    data: {
      type: Object,
      default () {
        return {
          value: '',
          style: {}
        }
      }
    }
  },
  methods: {
    replaceBr (content) {
      return ('<p>' + content.split('\n').join('</p><p>') + '</p>').replaceAll('<p></p>', '<p>&nbsp;</p>')
    }
  }
}
</script>

<style lang="scss">
.md-style-text-output {
  font-weight: lighter;
}
/* 阿拉伯文字方向 */
.ed-dir-rtl .md-style-text-output {
  direction: rtl;
}
</style>
