<template>
  <div class="main-container">
    <div class="the_coming">
      <div class="left_coming">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/image8.jpg" alt="" srcset="" />
      </div>
      <div class="right_coming">
        <div class="coming_text">{{ $t('ying.a') }}</div>
      </div>
    </div>
  </div>
</template>

<script></script>
<!-- 语言 -->
<i18n lang="json">
{
  "en": {
    "ying": {
      "a": "In the coming \nyears, JOY will\ninspire the BAIC\nbrand's strong\nproduct lineup.\n\nWe firmly\nbelieve that\nBAIC will bring\nthousands of\nfamilies the\nbest trave\nexperience!"
    }
  },
  "ar": {
    "ying": {
      "a": "في السنوات القادمة،\n  سوف تلهم JOY مجموعة المنتجات القوية \n\n  لعلامة BAIC التجارية. \n نحن نؤمن بشدة  \n بأن BAIC ستوفر \n آلاف العائلات بأفضل خبرة قيادة!"
    }
  },
  "es": {
    "ying": {
      "a": "En los próximos\naños, JOY\ninspirará a la\nfuerte\nalineación de\nproductos de la\nmarca BAIC.\n¡¡creemos\nfirmemente\nque BAIC\ndefinitivamente\ntraerá la mejor\nexperiencia de\nviaje a millones\nde hogares!"
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: Mediumtext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSansSC-Medium.otf') format('truetype');
// }
.main-container {
  width: 100%;
  .the_coming {
    display: flex;
    flex-flow: row-reverse;
    justify-content: space-between;
    padding: 14vw 0 4vw 0;
    .left_coming {
      flex: 3;
      width: 100%;
    }
    .right_coming {
      flex: 1.5;
      // font-size: 1.95vw;
      font-size: 1.5vw;
      margin-right: 5vw;
      text-align: right;
      // font-family: Mediumtext;
      // font-style: oblique;
      white-space: pre-line;
      .coming_text {
        padding-bottom: 2vw;
      }
    }
  }
}
.lang___es{
  .main-container .the_coming .right_coming .coming_text{
    margin-top: -1vw;
}
}
.lang___ar {
  .main-container .the_coming .right_coming {
    flex: 1.5;
    font-size: 2vw;
    margin-top: 2vw;
    margin-left: 5vw;
    text-align: left;
    // font-family: Mediumtext;
    // font-style: oblique;
  }
}

@media only screen and (max-width: 768px) {
  .main-container {
    width: 100%;
    .the_coming {
      // display: flex;
      // flex-flow: row-reverse;
      // justify-content: space-between;
      // padding: 14% 0;
      // br {
      //   display: none;
      // }
      .left_coming {
        flex: 1.5;
        padding-top: 4vw;
      }
      .right_coming {
        // text-align: left;
        font-size: 2vw;
        .coming_text {
          padding-bottom: 0vw;
          // br {
          //   display: none;
          // }
        }
      }
    }
  }
}
</style>
