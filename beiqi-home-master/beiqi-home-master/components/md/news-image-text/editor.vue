<template>
  <div class="ed-form-dynamic">
    <el-form :model="fData" label-width="80px" label-position="top" size="mini">
      <el-form-item :label="$t('Image')" prop="image">
        <editor-form-img-uploader
          v-model="fData.image"
          placeholder="place input the large img src"
          @change="handleSubmit"
        />
      </el-form-item>

      <el-form-item :label="$t('Text content')" prop="text">
        <editor-form-style-text-input
          v-model="fData.text"
          placeholder="Please enter the Text content"
          default-value="Xxxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx Xxxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx xxxxx "
          @change="handleSubmit"
        />
      </el-form-item>

      <el-form-item :label="$t('Vertical alignment')" prop="alignItems">
        <el-radio-group v-model="fData.alignItems" @change="handleSubmit">
          <el-radio label="">
            {{ $t('default') }}
          </el-radio>
          <el-radio label="center">
            {{ $t('center') }}
          </el-radio>
          <el-radio label="flex-start">
            {{ $t('top') }}
          </el-radio>
          <el-radio label="flex-end">
            {{ $t('bottom') }}
          </el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item :label="$t('Content reverse')" prop="reverse">
        <el-switch
          v-model="fData.reverse"
          active-text="Text-Image"
          inactive-text="Image-Text"
          @change="handleSubmit"
        />
      </el-form-item>

      <el-form-item :label="$t('Theme')" prop="darkTheme">
        <el-switch
          v-model="fData.darkTheme"
          active-text="Dark theme"
          inactive-text="Bright color theme"
          @change="handleSubmit"
        />
      </el-form-item>
      <!--提交按钮-->
      <!-- <el-form-item>
          <el-button type="primary" @click="handleSubmit"> {{ $t('Commit') }} </el-button>
        </el-form-item> -->
    </el-form>
  </div>
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
      "default": "default",
      "Theme": "Theme"
    },
    "zh": {
      "Image": "图片",
      "Text content": "文本内容",
      "Content reverse": "左右反转",
      "Vertical alignment": "垂直对齐",
      "top": "顶端",
      "center": "居中",
      "bottom": "底部",
      "default": "默认",
      "Theme": "主题"
    }
  }
  </i18n>

<script>
import _ from 'lodash'
import { setOne } from 'vue-set-path'
export default {
  name: 'MdNewsImageTextEditor',
  props: {
    // 当前模块的Schema
    schema: {
      type: Object,
      default () {
        return {}
      }
    },
    // 当前模块的数据
    formData: {
      type: Object,
      default () {
        return {
        }
      }
    }
  },
  data () {
    return {
      fData: JSON.parse(JSON.stringify(this.formData))
    }
  },
  watch: {
    formData: {
      deep: true,
      immediate: true,
      handler () {
        this.fData = JSON.parse(JSON.stringify(this.formData || {}))
      }
    }
  },
  mounted () {
  },
  methods: {
    /**
       * 添加一个分组
       */
    handleAddCard (path) {
      const newList = _.get(this.fData, path, [])
      newList.push({})
      setOne(this.fData, path, newList)
    },
    /**
       * 删除一个分组
       */
    handleRemoveCard (path, i) {
      const newList = _.get(this.fData, path, [])
      newList.splice(i, 1)
      setOne(this.fData, path, newList)
    },
    /**
       * 提交表单
       */
    handleSubmit () {
      this.$emit('onSubmit', JSON.parse(JSON.stringify(this.fData)))
    }
  }
}
</script>

  <style lang="scss">
  .ed-form-dynamic{
    padding-top: 12px;
    .ed-form-dynamic-card-title {
      border: none;
    }
    .ed-form-dynamic-content-website{
      height: auto;
    }
    .ed-form-dynamic-content{
      height: calc(100vh - 90px - 5em);
      overflow: auto;
      &::-webkit-scrollbar {
        width: 2px;
        height: 1px;
        position: absolute;
        left: 0px;
      }
    }
    .ed-form-dynamic-close-card{
      position:absolute;
      top: 2px;
      right: 2px;
      border: none;
    }
    .ed-form-dynamic-alert{
      margin-top: 1.5em;
    }
    /*修正elemnt ui*/
    .el-card{
      margin: 1em auto;
      position: relative;
    }
    .el-form-item__label{
      font-size: 12px;
      padding: 0;
    }
    .el-radio__label, .el-checkbox__label{
      font-size: 12px;
    }
    .el-input__inner, .el-textarea__inner{
      border-radius: 0;
    }
    .el-form-item--mini.el-form-item{
      margin-bottom: 12px;
    }
    .el-divider--horizontal:first-child{
      display:none;
    }
    .el-collapse-item__header.is-active{
      color: #1a73e8;
      font-weight: bold;
    }
    .el-upload-list--picture{
      position: absolute;
      visibility: hidden;
    }
    .el-form-item--mini{
      .el-switch {
        padding-top: 10px;
        padding-bottom: 10px;
      }
      .el-switch__label *{
        font-size: 12px;
      }
    }
  }
  </style>
