<template>
  <div class="main-container">
    <!-- gif 图 -->
    <div class="box_container_top">
      <div class="left_box">
        <div class="left_text">
          <div class="gifbox_title">
            <span class="span_or">{{ $t('form.Oriental') }}</span>
          </div>
          <div class="gifbox_content">{{ $t('form.detailsof') }}<br />{{ $t('form.combined') }}</div>
        </div>
      </div>
      <div class="right_box">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/image2.gif" class="leftgif" />
      </div>
    </div>

    <div class="box_container_bottom">
      <div class="left_box_b">
        <div class="gifbox_subtitle">
          <div class="left_content">{{ $t('form.fashion') }}</div>
        </div>
      </div>
      <div class="right_box_b">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/image1.gif" alt="" srcset="" />
      </div>
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
      "Oriental": "Oriental\n& stylish",
      "detailsof": "The details of the Oriental tide",
      "combined": "combined with fashion",
      "fashion": "The interior design of the JOY concept car\n shows the charm of warmth and national\n tide everywhere."
    }
  },
  "ar": {
    "form": {
      "Oriental": "شرقية وأنيقة",
      "detailsof": "تفاصيل الاتجاه الشرقي",
      "combined": "جنبا إلى جنب مع الموضة",
      "fashion": "ُظهر التصميم الداخلي للسيارة  النموذجية\" JOY \" \n سحر الدفء والمد والجزر الوطني في كل مكان."
    }
  },
  "es": {
    "form": {
      "Oriental": "Oriental\n& elegante",
      "detailsof": "Detalles de la tendencia Orienta",
      "combined": "Combinar con la moda",
      "fashion": "El diseño interior del concepto car\nJoy destaca el encanto de la\ncalidez y la marea nacional en\ntodas partes."
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: Heavy;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSerifSC-Heavy.otf') format('truetype');
// }
// @font-face {
//   font-family: Mediumtext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSansSC-Medium.otf') format('truetype');
// }
// @font-face {
//   font-family: AlibabaPuHuiTiB;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/AlibabaPuHuiTi-2-65-Medium.otf') format('truetype');
// }

.main-container {
  width: 100%;
  .box_container_top {
    display: flex;
    padding: 10vw 8vw 0 8vw;

    .left_box {
      width: 50%;

      .left_text {
        .gifbox_title {
          font-size: 5.4vw;
          text-align: right;
          width: 100%;
          // font-family: Heavy;
          line-height: 9vw;
          margin-top: -8vw;
          text-align: center;
          white-space: pre-line;
        }
        .gifbox_content {
          font-size: 1.5vw;
          padding-top: 3vw;
          text-align: center;
          width: 100%;
          // font-family: Mediumtext;
        }
      }
    }
    .right_box {
      width: 50%;
      img {
        width: 100%;
      }
    }
  }
}

.box_container_bottom {
  display: flex;
  flex-flow: row-reverse;
  justify-content: space-between;
  padding: 0vw 7vw 5vw 7vw;
  .left_box_b {
    width: 50%;
    .left_content {
      width: 45vw;
      font-size: 2vw;
      font-weight: 600;
      padding-left: 7vw;
      padding-top: 10vw;
      text-align: center;
      text-align: left;
      // font-style: italic;
      // font-family: AlibabaPuHuiTiB;
      white-space: pre-line;
    }
    .leftgif {
      width: 100%;
    }
  }
  .right_box_b {
    width: 50%;
    img {
      width: 100%;
    }
  }
}

.lang___en {
  .box_container_bottom .left_box_b .left_content {
    width: 48vw;
    font-size: 1.75vw;
    padding-left: 7.5vw;
    font-weight: 500;
  }
  .main-container .box_container_top .left_box .left_text .gifbox_title {
    padding-right: 8vw;
    font-size: 5vw;
  }
  .main-container .box_container_top .left_box .left_text .gifbox_content {
    padding-right: 8vw;
    font-size: 1.5vw;
  }
}
.lang___ar {
  .main-container .box_container_top .left_box .left_text .gifbox_title {
    margin-top: -6vw;
    text-align: center;
    font-size: 5vw;
    padding-left: 8.2vw;
  }
  .main-container .box_container_top .left_box .left_text .gifbox_content {
    text-align: center;
    padding-top: 4vw;
    padding-left: 7.6vw;
    font-size: 2vw;
  }
  .box_container_bottom .left_box_b .left_content {
    font-size: 1.7vw;
    text-align: center;
    margin-right: 6vw;
  }
  .main-container .box_container_top .left_box .left_text {
    padding-top: -1vw;
  }
}
.lang___es {
  .main-container .box_container_top .left_box .left_text .gifbox_title {
    font-size: 5vw;
    text-align: left;
    width: 100%;
    // font-family: Heavy;
    line-height: 7vw;
    margin-top: -7vw;
    padding-left: 4vw;
  }
  .main-container .box_container_top .left_box .left_text .gifbox_content {
    padding-right: 8vw;
    padding-top: 3vw;
  }

  .box_container_bottom .left_box_b .left_content {
    padding-left: 8.8vw;
  }
}

@media only screen and (max-width: 768px) {
  .box_container_top {
    padding: 5vw 6vw 0 6vw;
    .left_box {
      flex: 3;
      .left_text {
        .gifbox_content {
          font-size: 3vw;
        }
      }
    }
    .right_box {
      flex: 3;
    }
  }

  .box_container_bottom {
    .left_box_b {
      flex: 3;
      .left_content[data-v-939f6934] {
        padding-top: 13%;
      }
    }
    .right_box_b {
      flex: 3;
    }
  }
}
</style>
