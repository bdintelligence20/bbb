<template>
  <el-dialog
    class="success-dialog"
    :visible.sync="show"
    width="30%"
    :before-close="cancel"
    >
    <div class="success-box">
      <div class="success-img">
        <svg t="1654092504974" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3135" xmlns:xlink="http://www.w3.org/1999/xlink">
          <defs>
            <style type="text/css">@font-face { font-family: feedback-iconfont; src: url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff2?t=1630033759944") format("woff2"), url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff?t=1630033759944") format("woff"), url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.ttf?t=1630033759944") format("truetype"); }</style>
          </defs>
          <path d="M512.465455 0.116364C230.609455 0.116364 1.210182 229.492364 1.210182 511.371636S230.586182 1022.650182 512.465455 1022.650182s511.278545-229.376 511.278545-511.278546C1023.720727 229.492364 794.344727 0.116364 512.465455 0.116364z m286.091636 413.230545L466.013091 754.222545a42.519273 42.519273 0 0 1-30.254546 12.753455h-0.232727a42.589091 42.589091 0 0 1-30.114909-12.427636l-178.711273-178.827637a42.565818 42.565818 0 0 1 0-60.253091 42.565818 42.565818 0 0 1 60.253091 0l148.363637 148.247273 302.312727-310.062545a42.682182 42.682182 0 0 1 60.253091-0.698182 42.821818 42.821818 0 0 1 0.674909 60.392727z m0 0" p-id="3136" fill="#31be0d"></path>
        </svg>
      </div>
      <h3 class="h4 title">{{ title || $t('successDialog.Text') }}</h3>
      <div class="content"> {{ msg || $t('successDialog.Text1')}}</div>
    </div>
  </el-dialog>
</template>
<script >
export default {
  name: 'successDialog',
  props: {
    show: {
      type: Boolean
    },
    title: {
      type: String,
    },
    msg: {
      type: String,
    }
  },
  methods: {
    cancel() {
      this.$emit('dialog-close')
    }
  }
}
</script>

<style lang="scss">
.success-dialog{
  .el-dialog{
    min-width: 280px !important;
  }
}
</style>
<style scoped lang="scss">
.success-box {
  padding: 50px 0;
  text-align: center;
  color: #000;
  .success-img {
    display: inline-block;
    width: 110px;
    margin-bottom: 30px;
    svg {
      width: 80px;
    }
  }
  .title {
    font-size: 28px;
    margin-bottom: 20px;
  }
  .content {
    font-size:16px;
    word-break: break-word;
  }
}
</style>
