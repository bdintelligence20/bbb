<template>
  <div class="main-container">
    <!-- banner -->
    <div class="banner" v-if="displayBlock === 1">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/banner.jpg" />
      <div class="text_title">
        {{ $t('header.content1') }}<br />{{ $t('header.content2') }}
        <div class="text_symbol">
          <div class="border"><hr /></div>
          <div class="symbol">+</div>
        </div>
      </div>
    </div>

    <!-- 京剧背景图 -->
    <div class="four_bj" v-if="displayBlock === 2">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/four_bj.jpg" />
      <div class="text_Glory">
        <div class="glory">{{ $t('about.Glory') }}</div>
        <div class="dream">{{ $t('about.Dream') }}</div>
      </div>
    </div>

    <!-- 三张图 -->
    <div class="three_image" v-if="displayBlock === 3">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/three_image.jpg" alt="" srcset="" />
      <div class="text_three">
        <div class="text_box">
          <div class="text1">{{ $t('about.Confidence') }}</div>
          <div class="text2">{{ $t('about.Confidence1') }}<br />{{ $t('about.Confidence2') }}</div>
        </div>
        <div class="text_box">
          <div class="text1">{{ $t('about.Warmth') }}</div>
          <div class="text2">{{ $t('about.Warmth1') }}<br />{{ $t('about.Warmth2') }}</div>
        </div>
        <div class="text_box">
          <div class="text1">{{ $t('about.Oriental') }}</div>
          <div class="text2">{{ $t('about.Oriental1') }}<br />{{ $t('about.Oriental2') }}</div>
        </div>
      </div>
    </div>

    <!-- 一张图 -->
    <div class="Oblique_four" v-if="displayBlock === 4">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/four_bg2.jpg" alt="" srcset="" />
    </div>

    <div class="oneimages" v-if="displayBlock === 5">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/car2.jpg" alt="" srcset="" />
    </div>

    <div class="oneimages" v-if="displayBlock === 6">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/carbg.jpg" alt="" srcset="" />
    </div>

    <!-- 两张穿插图 -->
    <div class="Scenario-based" v-if="displayBlock === 7">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/two_bj.jpg" />
      <div class="scenario_text">
        <div class="scenario">{{ $t('about.scenario') }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['displayBlock'],
  data() {
    return {}
  },
  activated() {},
  created() {},
  mounted() {},
  methods: {},
}
</script>
<!-- 语言 -->
<i18n lang="json">
{
  "en": {
    "header": {
      "content1": "CAPITAL BEAUTY",
      "content2": "REDEFINED"
    },
    "about": {
      "Glory": "Glory",
      "Dream": "Dream",
      "Confidence": "Confidence",
      "Confidence1": "Peaceful, calm, simple",
      "Confidence2": "but not shallow",
      "Warmth": "Warmth",
      "Warmth1": "Friendly, practical, comfortable",
      "Warmth2": "but not exaggerated",
      "Oriental": "Oriental Style",
      "Oriental1": "Unique,aesthetic,stylish",
      "Oriental2": "but not conservative",
      "scenario": "Scenario-based Design"
    }
  },
  "ar": {
    "header": {
      "content1": "إعادة تعريف",
      "content2": "جمال العاصمة"
    },
    "about": {
      "Glory": "شرف",
      "Dream": "حلم",
      "Confidence": "ثقة",
      "Confidence1": "السلام والهدوء والبساطة",
      "Confidence2": "ولكن ليست سطحية",

      "Warmth": "دفء",
      "Warmth1": "ودية ومفيدة ومريحة",
      "Warmth2": "ولكن ليست من المبالغة",

      "Oriental":"النمط الشرقي",
      "Oriental1": "فريدة من نوعها، جميلة وعصرية",
      "Oriental2": " ومنفتحة",
      "scenario": "المشهد القائم على التصميم"
    }
  },
  "es": {
    "about": {
      "Glory": "Honor",
      "Dream": "Sueño",
      "Confidence": "Confianza",
      "Confidence1": "Paz, calma, sencillez",
      "Confidence2": "Pero no poco profundo",
      "Warmth": "Cálido",
      "Warmth1": "Amigable, práctico y cómodo",
      "Warmth2": "Pero no es exagerado",
      "Oriental": "Estilo oriental",
      "Oriental1": "único, hermoso, de moda",
      "Oriental2": "Pero no es conservador",
      "scenario": "Diseño basado en escenarios"
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: bannertext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/MerriweatherBlack.ttf') format('truetype');
// }
// @font-face {
//   font-family: fourCenter;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/Achafexp.ttf') format('truetype');
// }

// @font-face {
//   font-family: Regular;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSansSC-Regular.otf') format('truetype');
// }

.main-container {
  width: 100%;
  .banner {
    position: relative;
    cursor: pointer;
    overflow: hidden;
    img {
      width: 100%;
    }
    .text_title {
      position: absolute;
      top: 5.9vw;
      left: 7.3vw;
      color: #181413;
      // font-size: 2.9vw;//字体默认
      font-size: 2.5vw;
      font-weight: 600;
      line-height: 1.2;
      max-width: 50%;
      // font-family: 'bannertext';
      text-align: left;
      .text_symbol {
        position: absolute;
        // top: 4.5vw;//字体默认
        // left: 18vw;//字体默认
        top: 3.8vw;
        left: 15vw;
        display: flex;
        .border {
          // width: 5.6vw;//字体默认
          width: 4.4vw;
          height: 10%;
        }
        .symbol {
          font-size: 1.5vw;
          line-height: 1.1vw;
          padding: 0.9vw;
        }
      }
    }
  }
  .four_bj {
    position: relative;
    .text_Glory {
      position: absolute;
      width: 100%;
      display: flex;
      background-color: rgba(0, 0, 0, 0.5);
      top: 15vw;
      justify-content: center;
      color: #fff;
      // font-size: 4vw;
      font-size: 2.5vw;
      // font-family: fourCenter;
      .glory {
        padding-right: 35.5vw;
      }
      .dream {
      }
    }
  }
  .three_image {
    position: relative;
    text-align: center;
    .text_three {
      position: absolute;
      width: 86%;
      display: flex;
      top: 9.5vw;
      font-size: 1vw;
      left: 6vw;
      text-align: center;
      justify-content: space-around;
      .text_box {
        color: #fff;
        .text1 {
          font-size: 1.2vw;
        }
        .text2 {
          padding-top: 4vw;
          font-size: 1vw;
        }
      }
    }
  }
  .Oblique_four {
    margin: 5vw 0;
  }
  .oneimages {
    width: 100%;
  }
  .Scenario-based {
    position: relative;
    .scenario_text {
      position: absolute;
      width: 100%;
      bottom: 2vw;
      right: 6vw;
      text-align: right;
      color: #fff;
      font-size: 1.5vw;
      // font-size: 1.8vw;
      // font-family: Regular;
    }
  }
  .the_ashion {
    display: flex;
    padding: 10vw 0vw 0 1.3vw;
    .left_ashion {
      width: 100%;
      flex: 3;
    }
    .right_ashion {
      flex: 1.5;
      font-size: 2vw;
      margin-left: 2.5vw;
      margin-top: 1.5vw;
    }
  }
  .fanzhuan {
  }
}

.lang___ar {
  .banner .text_title {
    // right: 7.3vw;
    // left: 0;
    // right: -16vw;
    // left: 0;
    // text-align: left;
    // top: 4.9vw;
  }

  .main-container .banner .text_title .text_symbol {
    // position: absolute;
    // top: 4.5vw;
    // left: 18vw;
    // display: flex;
    direction: ltr;
  }
  .main-container .four_bj .text_Glory .glory {
    // padding-left: 35.5vw;
  }
  .main-container .three_image .text_three{
    width: 94%;
    left: 2vw;
    direction: ltr;
  }

  .main-container .four_bj .text_Glory{
    direction: ltr;
  }
  .main-container .three_image .text_three .text_box .text1 {
    font-size: 2vw;
  }
  .main-container .three_image .text_three .text_box .text2 {
    padding-top: 4.1vw;
    font-size: 1.5vw;
  }
}

@media only screen and (max-width: 768px) {
  .main-container .oneimages {
    margin-bottom: -1px;
  }
  .main-container .three_image .text_three .text_box .text2 {
    padding-top: 4.1vw;
    font-size: 1.5vw;
  }
  .main-container .three_image .text_three .text_box .text1 {
    font-size: 2vw;
  }
}
</style>
