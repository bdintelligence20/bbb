<template>
  <div class="main-container">
    <div class="box_container joy_box">
      <div class="left_box">
        <div class="joy_text">{{ $t('describe.joy') }}</div>
        <div class="joy_subtitle">{{ $t('describe.joy1') }}
        </div>
      </div>
      <div class="right_box">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/logo.png" />
      </div>
    </div>
  </div>
</template>

<script></script>
<!-- 语言 -->
<i18n lang="json">
{
  "en": {
    "describe": {
      "joy": "JOY: KEYWORDS OF THE DESIGN CONCEPT ARE...",
      "joy1": "BAIC brand JOY concept car interprets the design concept of CAPITAL BEAUTY.\nIt will bring users a quiet sense of satisfaction and practical comfort.\nThe design of \"confident, warm and Oriental charm\" is the beautiful vision of the\nBAIC brand for future travel."
    }
  },
  "ar": {
    "describe": {
      "joy": "المتعة: الكلمة الرئيسية لمفهوم التصميم هي…",
      "joy1": "تعكس السيارة  الاختبارية من ماركة BAIC مفهوم التصميم  \"المتعة \" الخاص بجمال العاصمة. \n ستمنح المستخدمين شعورًا هادئًا بالرضا والراحة العملية. \n يعد تصميم \"الثقة والدفء والسحر الشرقي\" الرؤية الجميلة لعلامة BAIC التجارية للسفر في المستقبل."
    }
  },
  "es": {
    "describe": {
      "joy": "JOY: LAS PALABRAS CLAVE DEL CONCEPTO DE DISEÑO SON...",
      "joy1": "El concepto car de la marca BAIC JOY interpreta el concepto de diseño de capital\n beato. Traerá a los usuarios una sensación de satisfacción tranquila y\ncomodidad práctica.\nEl diseño de \"confianza en sí mismo, calidez y encanto oriental\" es la hermosa\nvisión de la marca BAIC para los viajes futuros."
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: Regular;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSansSC-Regular.otf') format('truetype');
// }
// @font-face {
//   font-family: Mediumtext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSansSC-Medium.otf') format('truetype');
// }
.main-container {
  width: 100%;
  .joy_box {
    // padding: 5vw 2vw 5vw 3vw;
    padding: 5vw 2vw 5vw 5vw;
    display: flex;
    .left_box {
      margin-top: 0;
      // width: 68%;
      width: 60%;
      flex: 2.5;
      .joy_text {
        // font-size: 2.5vw;
        font-size: 2.19vw;
        // font-family: Regular;
      }
      .joy_subtitle {
        // font-size: 1.8vw;
        font-size: 1.5vw;
        padding-top: 4vw;
        // font-family: Mediumtext;
        white-space: pre-line;
      }
    }
    .right_box {
      width: 100%;
      // flex: 0.8;
      flex: 1;
      margin-top: 5vw;
      padding-left: 4vw;
    }
  }
}
.lang___es{
  .main-container .joy_box .right_box{
    margin-top: 6vw
  }
}
.lang___ar{
  .main-container .joy_box .left_box .joy_subtitle{
    font-size: 1.65vw;
  }
}
@media only screen and (max-width: 768px) {
  .main-container {
    width: 100%;
    .joy_box {
      display: flex;
      padding: 8vw 6vw 13vw;

      .left_box {
        margin-top: 0;
        width: 73%;
        flex: 3;
        .joy_text {
          font-size: 2.6vw;
        }
        .joy_subtitle {
          font-size: 2.6vw;
          padding-top: 5.3vw;
        }
      }
      .right_box {
        display: flex;
        justify-content: flex-end;
        margin-top: 10vw !important;
        width: 50%;
        flex: 1;
        img {
          width: 100%;
        }
      }
    }
  }
}
</style>
