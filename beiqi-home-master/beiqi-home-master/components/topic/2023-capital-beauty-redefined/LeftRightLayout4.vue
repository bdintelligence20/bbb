<template>
  <div class="main-container">
    <!-- 两个坐 -->
    <div class="the_ashion">
      <div class="left_ashion">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/image4.jpg" alt="" srcset="" />
      </div>
      <div class="right_ashion">{{ $t('form.thefashion') }}<br />{{ $t('form.expression') }}<br />{{ $t('form.usedto') }}<br />{{ $t('form.oriental') }}<br />{{ $t('form.elements') }}<br />{{ $t('form.design') }}</div>
    </div>
  </div>
</template>

<script>
export default {}
</script>
<!-- 语言 -->
<i18n lang="json">
{
  "en": {
    "form": {
      "thefashion": "The fashion design",
      "expression": "expression technique is",
      "usedto": "used to integrate",
      "oriental": "Oriental aesthetic",
      "elements": "elements into the interior",
      "design": "design."
    }
  },
  "ar": {
    "form": {
      "thefashion": "تم استخدام تقنية التعبير عن تصميم الأزياء",
      "expression": "لدمج العناصر الجمالية الشرقية",
      "usedto": "في التصميم الداخلي.",
      "oriental": " ",
      "elements": " ",
      "design": " "
    }
  },
  "es": {
    "form": {
      "thefashion": "Utilizando técnicas de",
      "expression": "expresión de diseño de ",
      "usedto": "moda, los elementos ",
      "oriental": "estéticos orientales se",
      "elements": "integran en el diseño de ",
      "design": "interiores."
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: AlibabaPuHuiTiB;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/AlibabaPuHuiTi-2-65-Medium.otf') format('truetype');
// }
.main-container {
  width: 100%;

  .the_ashion {
    display: flex;
    padding: 10vw 0 0 1vw;

    .left_ashion {
      flex: 3;
      width: 100%;
    }

    .right_ashion {
      flex: 1.5;
      font-size: 2vw;
      font-weight: 600;
      margin-left: 3vw;
      margin-top: 2vw;
      // font-family: AlibabaPuHuiTiB;
      // font-style: oblique;
    }
  }
}

.lang___ar {
  .main-container .the_ashion .right_ashion {
    margin-right: 3vw;
  }
}

@media only screen and (max-width: 768px) {
  .main-container {
    width: 100%;
    .box_container .box .text {
      font-size: 1.7vw;
      font-weight: 600;
      padding-left: 0vw;
      padding-bottom: 2vw;
      font-style: oblique;
      // font-family: Medium;
    }
    .the_ashion {
      .left_ashion {
        flex: 2;
        // padding-top: 5vw;
        width: 90%;
      }

      .right_ashion {
        flex: 1.5;
        font-size: 2vw;
        font-weight: 600;
        margin-left: 3%;
        margin-top: 1.5vw;
      }
    }
  }
}
</style>
