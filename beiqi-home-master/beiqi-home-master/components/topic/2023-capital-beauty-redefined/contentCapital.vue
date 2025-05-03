<template>
  <div class="content_capital">
    <!-- 五张图排列 -->
    <div class="capital">
      <div class="content_text">{{ $t('capital.colour') }}</div>
      <div class="images_arrange">
        <div class="left_images">
          <!-- <img src="../../../static/topic/2023-capital-beauty-redefined/images/redbg.jpg" class="redbg" /> -->
          <img src="../../../static/topic/2023-capital-beauty-redefined/images/gugong1.jpg"  />
        </div>
        <div class="right_images">
          <!-- <img src="../../../static/topic/2023-capital-beauty-redefined/images/gugong.jpg" />
          <img src="../../../static/topic/2023-capital-beauty-redefined/images/taoci.jpg" class="taoci" /> -->
        </div>
      </div>
    </div>

    <div class="leaving">
      <!-- 两张图排列 -->
      <div class="content_leaving">
        <div class="leaving1">
          <div class="white_jade"><img src="../../../static/topic/2023-capital-beauty-redefined/images/white_jade.jpg" alt="" srcset="" /></div>
          <div class="china_ink"><img src="../../../static/topic/2023-capital-beauty-redefined/images/china_ink.jpg" alt="" /></div>
        </div>
        <div class="leaving2">
          <div class="leaving2_text">{{ $t('capital.theessenceof') }}</div>
          <div class="leaving2_big">"{{ $t('capital.leaving') }}</div>
          <div class="leaving2_big">{{ $t('capital.blank') }}"</div>
          <div class="leaving2_text">{{ $t('capital.expandsnew') }}</div>
        </div>
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
    "capital": {
      "colour": "COLOUR\nAESTHETICS\nOF\nCAPITAL\nBEAUTY",
      "theessenceof": "The essence of",
      "leaving": "leaving ",
      "blank": "blank",
      "expandsnew": "expands new\nreverie"
    }
  },
  "ar": {
    "capital": {
      "colour": "الألوان الجمال\nلجمال العاصمة",
      "theessenceof": "جوهر",
      "leaving": "وضع",
      "blank": "البياض",
      "expandsnew": "لتوسيع خيالية جديدة"
    }
  },
  "es": {
    "capital": {
      "colour": "ESTÉTICA DEL\nacolor\nDE\nCAPITAL\nBEAUTY",
      "theessenceof": "La esencia de",
      "leaving": "leaving ",
      "blank": "blank",
      "expandsnew": "amplía la nueva\nensoñación"
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: pintext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/AdobeArabic-Regular.otf') format('truetype');
// }

// @font-face {
//   font-family: righttext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/AlibabaPuHuiTi-2-65-Medium.otf') format('truetype');
// }

.content_capital {
  width: 100%;
  padding: 7vw 6vw 0 9vw;
  .capital,
  .leaving {
    display: flex;
  }
  .content_text {
    color: #000000;
    // font-size: 6vw;//有字体
    font-size: 3vw;
    width: 40%;
    text-align: center;
    padding-top: 4vw;
    padding-right: 7vw;
    // font-family: pintext;
    line-height: 6vw;
    white-space: pre-line;
    font-weight: 600;
  }
  .images_arrange {
    display: flex;
    img {
      width: 100%;
    }
    .left_images {
      .redbg {
        padding-right: 2vw;
      }
    }
    .right_images {
      .taoci {
        // padding-top: 1.8vw;
      }
    }
  }
  .content_leaving {
    width: 100%;
    display: flex;
    margin-top: 2vw;
    line-height: 4.5vw;
    .leaving1 {
      display: flex;
      padding-left: 6vw;
      img {
        width: 100%;
      }
      .white_jade {
        width: 20.5vw;
        margin-right: 2vw;
      }
      .china_ink {
        width: 26.9vw;
      }
    }
    .leaving2 {
      width: 100%;
      text-align: right;
      padding-top: 1vw;
      .leaving2_text {
        // font-family: righttext;
        font-size: 3vw;
        // font-weight: 700;
        font-style: oblique;
        white-space: pre-line;
      }
      .leaving2_big {
        // font-family: righttext;
        font-size: 3.5vw;
        font-weight: 900;
        font-style: oblique;
      }
    }
  }
}
.lang___ar {
  .main-container .four_bj .text_Glory .glory {
    padding-left: 35.5vw;
  }

  .content_capital .images_arrange .left_images .redbg {
    padding-left: 2vw;
  }
  .content_capital .content_leaving .leaving1 .white_jade {
    width: 20.5vw;
    margin-left: 2vw;
  }
  .content_capital .content_text {
    width: 55%;
    padding-top: 10vw;
    padding-right: 0vw;
  }
}
.lang___en{
  .content_capital .content_leaving .leaving2 .leaving2_text{
    font-weight: 600;
  }
  .box_container_bottom .left_box_b .left_content{
    font-weight: 500;
  }
}
.lang___es {
  .content_capital .content_text {
    width: 67%;
    padding-top: 1.1vw;
    padding-right: 3vw;
  }
}

</style>
