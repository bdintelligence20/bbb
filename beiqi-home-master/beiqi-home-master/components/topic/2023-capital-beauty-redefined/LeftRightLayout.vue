<template>
  <div class="main-container">
    <div class="box_container">
      <div class="right_zhang">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/zhang.png" alt="" srcset="" class="zhang" />
      </div>
      <div class="left_box">
        <div class="text_describe">{{ $t('describe.content1') }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {}
  },
}
</script>
<!-- 语言 -->
<i18n lang="json">
{
  "en": {
    "describe": {
      "content1": "As the CAPITAL of China, Beijing is the unity of \n tradition and modernity, conveys the \n temperament of order, firmness and \n trustworthiness, and represents the confidence, \n glory and dream of Chinese culture."
    }
  },
  "ar": {
    "describe": {
      "content1": "بكين هي المدينة المتجمعة بين التقليد والحداثة باعتبارها عاصمة الصين،\nوهي تتمتع بصفات مثل نقل النظام وشراكة ومصداقية، تمثل الثقافة \nالصينية الثقة بالنفس والمجد والحلم ."
    }
  },
  "es": {
    "describe": {
      "content1": "Como capital de china, Beijing es la unidad de \n la tradición y la modernidad, transmitiendo un \n temperamento de orden, firmeza y credibilidad,\ny representando la confianza en sí mismo, la\n gloria y el sueño de la cultura china."
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: bannertext1;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/AlibabaPuHuiTi-2-65-Medium.otf') format('truetype');
// }

.main-container {
  width: 100%;
  .box_container {
    display: flex;
    padding: 8vw 5vw 10vw 5vw;
    .right_zhang {
      width: 100%;
      flex: 1;
      .zhang {
        margin-left: 5vw;
        width: 44%;
      }
    }
    .left_box {
      margin-top: 0;
      width: 68%;
      .text_describe {
        // font-size: 2vw;
        font-size: 1.6vw;
        // padding-top: 3vw;
        text-align: right;
        width: 100%;
        // font-family: 'bannertext1';
        // font-style: oblique;
        font-weight: 600;
        white-space: pre-line;
        display: inline-block;
        // padding-top: 1vw;
        padding-top: 1.5vw;
        br {
          // display: none;
        }
      }
    }
  }
}

.lang___ar {
  .main-container .box_container .left_box .text_describe{
    padding-top: 4vw
  }
}
</style>
