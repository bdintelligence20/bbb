<template>
  <el-checkbox-group v-model="checkedList" class="el-checkbox-group" @change="handleChange">
    <el-checkbox label="image">
      {{ $t('Image') }}
    </el-checkbox>

    <el-checkbox label="text">
      {{ $t('Text content') }}
    </el-checkbox>

    <el-checkbox label="alignItems">
      {{ $t('Vertical alignment') }}
    </el-checkbox>

    <el-checkbox label="reverse">
      {{ $t('Content reverse') }}
    </el-checkbox>
  </el-checkbox-group>
</template>

<i18n lang="json">
{
    "en": {
    "Image": "Image",
    "Text content": "Text content",
    "Content reverse": "Content reverse",
    "Vertical alignment": "Vertical alignment",
    "top": "top",
    "center": "center",
    "bottom": "bottom",
    "default": "default"
    },
    "zh": {
    "Image": "图片",
    "Text content": "文本内容",
    "Content reverse": "左右反转",
    "Vertical alignment": "垂直对齐",
    "top": "顶端",
    "center": "居中",
    "bottom": "底部",
    "default": "默认"
    }
}
</i18n>

<script>
export default {
  name: 'MdNewsImageTextCopy',
  data () {
    return {
      checkedList: [
        // 'image',
        // 'text',
        // 'alignItems',
        // 'reverse'
      ]
    }
  },
  methods: {
    handleChange (data) {
      this.$emit('input', data)
      this.$emit('change', data)
    }
  }
}
</script>

<style lang="scss" scoped>
.el-checkbox-group {
    display: flex;
    flex-direction: column;
}
</style>
