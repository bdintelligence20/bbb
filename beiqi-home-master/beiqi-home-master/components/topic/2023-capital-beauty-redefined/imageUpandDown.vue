<template>
  <div class="main-container">
    <div class="box_container">
      <div class="box">
        <div class="text">{{ $t('form.Ripple') }}</div>
        <div class="image">
          <img src="../../../static/topic/2023-capital-beauty-redefined/images/image5.jpg" />
        </div>
      </div>
      <div class="box imagebox">
        <div class="text1">{{ $t('form.character') }}</div>
        <div class="image">
          <img src="../../../static/topic/2023-capital-beauty-redefined/images/image6.jpg" />
        </div>
      </div>
      <div class="box">
        <div class="text2">{{ $t('form.Oriental') }}</div>
        <div class="image">
          <img src="../../../static/topic/2023-capital-beauty-redefined/images/image7.jpg" />
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
    "form": {
      "Ripple": "Ripple-design control panel",
      "character": "\"福\"character sound panel",
      "Oriental": "Oriental style cockpit"
    }
  },
  "ar": {
    "form": {
      "Ripple": " قمرة القيادة على الطراز الشرقي",
      "character": "لوحة على شكل الكلمة الصينية\"福\"",
      "Oriental": "لوحة تحكم بتصميم تموج"
    }
  },
  "es": {
    "form": {
      "Ripple": "Panel de control de diseño corrugado",
      "character": "\" 福 \"Panel de sonido de caracteres",
      "Oriental": "Cabina de estilo oriental"
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: Medium;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/AlibabaPuHuiTi-2-65-Medium.otf') format('truetype');
// }

.main-container {
  width: 100%;
  padding-top: 8vw;
  display: flex;
  justify-content: center;
  .box_container {
    display: flex;
    justify-content: center;
    width: 97%;
    .imagebox {
      margin: 0 2vw;
    }
    .box {
      .text {
        // font-size: 1.7vw;
        font-size: 1.5vw;
        font-weight: 600;
        text-align: center;
        // padding-left: 3vw;
        padding-bottom: 2vw;
        // font-style: oblique;
        // font-family: Medium;
      }
      .text1 {
        // font-size: 1.7vw;
        font-size: 1.5vw;
        font-weight: 600;
        // padding-left: 3vw;
        text-align: center;
        padding-bottom: 2vw;
        // font-style: oblique;
        // font-family: Medium;
      }
      .text2 {
        // font-size: 1.7vw;
        font-size: 1.5vw;
        font-weight: 600;
        // padding-left: 0vw;
        text-align: center;
        padding-bottom: 2vw;
        // font-style: oblique;
        // font-family: Medium;
      }
    }
  }
}
.lang___ar {
  .main-container .box_container {
    // margin-right: 5vw;
  }
}
.lang___es {
  .main-container .box_container .box .text {
    font-size: 1.5vw;
    font-weight: 600;
    padding-left: 0vw;
    height: 6vw;
    line-height: 5vw;
  }
  .main-container .box_container .box .text1 {
    font-size: 1.5vw;
    font-weight: 600;
    padding-left: 0vw;
    padding-bottom: 2vw;
    // font-style: oblique;
    // font-family: Medium;
    height: 6vw;
    line-height: 5vw;
  }
  .main-container .box_container .box .text2 {
    font-size: 1.5vw;
    line-height: 5vw;
    height: 6vw;
  }
}

@media only screen and (max-width: 768px) {
  .main-container .box_container {
    display: flex;
    width: 95%;
  }
}
</style>
