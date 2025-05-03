<template>
  <div class="media-center-page">

    <el-dialog class="questionnaireDialogIframe" :fullscreen="true" :visible.sync="dialogVisible" center>
      <iframe class="frame" src='https://www.wjx.cn/vm/PpKMRYF.aspx?width=760&source=iframe&s=t' width='700' height='750'
        frameborder='0' style='overflow:auto'></iframe>
    </el-dialog>
    <div class="container">
      <!-- <img src="../../assets/image/screenshot-20230316-152854.png">  -->
      <!-- <iframe src='https://www.wjx.cn/vm/PpKMRYF.aspx?width=760&source=iframe&s=t' width='799' height='800' frameborder='0' style='overflow:auto'></iframe> -->
      <!-- <iframe src='https://www.wjx.cn/vm/PpKMRYF.aspx?width=760&source=iframe&s=t' width='799' height='700' frameborder='0' style='overflow:auto'></iframe> -->
      <!-- <iframe src='https://www.wjx.cn/vm/PpKMRYF.aspx?width=760&source=iframe&s=t' width='799' height='800' frameborder='0' style='overflow:auto'></iframe> -->


      <!-- en英语问答卷 -->
      <!-- <iframe ref="ifr"  v-if="'en' === $i18n.locale" class="frame" src='https://www.wjx.cn/vm/PpKMRYF.aspx?width=760&source=iframe&s=t'
      onload="this.style.display='block'" width='799' height='400' frameborder='0' style='overflow:auto'></iframe> -->
      <!-- es西班牙问答卷 -->
      <!-- <iframe ref="ifr"  v-if="'es' === $i18n.locale" class="frame" src='https://www.wjx.cn/vm/eUnFAta.aspx?width=760&source=iframe&s=t'
      onload="this.style.display='block'" width='799' height='800' frameborder='0' style='overflow:auto'></iframe> -->
      <!-- ar阿拉伯问答卷 -->
      <!-- <iframe ref="ifr"  v-if="'ar' === $i18n.locale" class="frame" src='https://www.wjx.cn/vm/tG4DoaX.aspx?width=760&source=iframe&s=t'
      onload="this.style.display='block'" width='799' height='800' frameborder='0' style='overflow:auto'></iframe> -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'questionnaire',
  layout: 'main',
  data() {
    return {
      addLangClassName: {},
      dialogVisible: true
    }
  },
  watch: {
  },
  mounted() {

  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
.media-center-page {


  .container {
    width: 800px;
    height: 900px;
    // overflow: hidden;
    margin-top: 400px;
    margin: 0 auto;

    img {
      width: 800px;
      margin: 0 auto;
    }
  }
}

.page-main {
  .header {
    text-align: center;
    margin-top: 100px;

    .h1 {
      margin-bottom: 35px;
      font-size: 28px;
    }

    p {
      font-size: 16px;
      line-height: 28px;
      white-space: pre-line;
    }
  }

}

.xs,
.sm,
.md {
  .page-main {
    .header {
      text-align: center;
      margin-top: 30px;

      .h1 {
        margin-bottom: 15px;
        font-size: 24px;
      }

      p {
        font-size: 14px;
        line-height: 20px;
      }
    }

    .form-box {
      margin-top: 30px;

      .form-header {
        margin-top: 30px;
        text-align: center;
        margin-bottom: 30px;

        .form-title {
          font-size: 24px;
          margin-bottom: 15px;
        }
      }
    }

    .bottom-btn {
      text-align: center;

      p {
        margin: 30px 0 10px;
      }

      .btn {
        cursor: pointer;
      }
    }
  }
}
</style>
<style lang="scss" >
.questionnaireDialogIframe {
  height: 100vh;

  .frame {
    width: 100%;
    margin: 0 auto;
    overflow: auto;
    display: block;
    height: 100%;
  }

  .el-dialog__header {
    padding: 0 !important;
  }

  .el-dialog__body {
    padding: 0 !important;
    height: 100vh;
  }

  .el-dialog__close {
    font-size: 24px;
    padding: 3px;
    color: black;
    margin-top: -10px;
    margin-right: -5px;
    background: white;
    border-radius: 50%;

    &:hover {
      color: black;
    }
  }
}
</style>
