<template>
  <div class="car_box">
    <!-- 标题  -->
    <div class="car_text" :class="{ car_text_an: show }">{{ $t('an.a') }}</div>
    <!--方型 -->
    <div class="fangkuai" :class="{ text: show }">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/image9.png" />
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/image9.png" />
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/image9.png" />
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/image9.png" />
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/image9.png" />
    </div>

    <!-- 车部分内容 -->
    <div class="car_content">
      <!-- 三辆车 -->
      <div class="three_car">
        <div class="k_text">
          <div class="car_title1" :class="{ car_title1_an: show }">{{ $t('an.Joyful') }}</div>
          <div class="describe" :class="{ describe1_an: show }">{{ $t('an.off') }}</div>
        </div>
        <div class="zhezhao"></div>
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/shadow_car1.png" :class="{ car1_animation1: show }" class="car_animation" />
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/car_light1.png" :class="{ light_an: show }" class="light car1_light" />
      </div>

      <div class="three_car">
        <div class="k_text">
          <div class="car_title1" :class="{ car_title2_an: show }">{{ $t('an.Joyful') }}</div>
          <div class="describe" :class="{ describe2_an: show }">{{ $t('an.out') }}</div>
        </div>
        <div class="zhezhao"></div>
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/shadow_car2.png" :class="{ car1_animation2: show }" class="car_animation" />
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/car_light3.png" :class="{ light_an: show }" class="light car2_light" />
      </div>
      <div class="three_car">
        <div class="k_text">
          <div class="car_title1" :class="{ car_title3_an: show }">{{ $t('an.Joyful') }}</div>
          <div class="describe" :class="{ describe3_an: show }">{{ $t('an.sty') }}</div>
        </div>
        <div class="zhezhao"></div>
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/shadow_car3.png" :class="{ car1_animation3: show }" class="car_animation" />
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/car_light2.png" :class="{ light_an: show }" class="light car3_light" />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    show: Boolean,
  },
  data() {
    return {}
  },
  mounted() {},
  methods: {},
}
</script>
<style scoped lang="scss">
.car_box {
  width: 100%;
  background-color: #000000;
  overflow: hidden;

  // 文字标题
  .car_text {
    color: #fff;
    text-align: center;
    font-size: 1.5vw;
    padding-bottom: 2vw;
    opacity: 0;
  }

  .car_text_an {
    animation-name: fade-in;
    animation-duration: 1s;
    animation-delay: 1s; /* 等待2秒后再启动动画 */
    animation-fill-mode: forwards;
  }

  .car_content {
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    .three_car {
      position: relative;
      width: 27%;
      .k_text {
        color: #fff;
        text-align: center;
        position: absolute;
        left: 0;
        right: 0;

        .car_title1 {
          opacity: 0;
        }
        .car_title2 {
          opacity: 0;
        }
        .car_title3 {
          opacity: 0;
        }
        .describe {
          opacity: 0;
        }
        .car_title1_an {
          animation-name: fade-in;
          animation-duration: 3s;
          animation-delay: 3s;
          animation-fill-mode: forwards;
        }
        .describe1_an {
          animation-name: fade-in;
          animation-duration: 3s;
          animation-delay: 3s;
          animation-fill-mode: forwards;
        }
        .car_title2_an {
          animation-name: fade-in;
          animation-duration: 3s;
          animation-delay: 4s;
          animation-fill-mode: forwards;
        }
        .describe2_an {
          animation-name: fade-in;
          animation-duration: 3s;
          animation-delay: 4s;
          animation-fill-mode: forwards;
        }
        .car_title3_an {
          animation-name: fade-in;
          animation-duration: 4s;
          animation-delay: 5s;
          animation-fill-mode: forwards;
        }
        .describe3_an {
          animation-name: fade-in;
          animation-duration: 4s;
          animation-delay: 5s;
          animation-fill-mode: forwards;
        }
      }
      .light {
        position: absolute;
        left: 0;
        right: 0;
        opacity: 0;
      }

      .light_an {
        animation-name: fade-in;
        animation-duration: 1s;
        animation-delay: 1s; /* 等待2秒后再启动动画 */
        animation-fill-mode: forwards;
      }

      .car_animation {
        opacity: 0;
      }

      .car1_animation1 {
        animation-name: car;
        animation-duration: 2s;
        animation-timing-function: ease;
        animation-delay: 2s; /* 设置延迟时间为2秒 */
        animation-fill-mode: forwards; /* 动画结束后保持最后一帧状态 */
      }

      .car1_animation2 {
        animation-name: car;
        animation-duration: 2s;
        animation-timing-function: ease;
        animation-delay: 3s; /* 设置延迟时间为2秒 */
        animation-fill-mode: forwards; /* 动画结束后保持最后一帧状态 */
      }
      .car1_animation3 {
        animation-name: car;
        animation-duration: 2s;
        animation-timing-function: ease;
        animation-delay: 4s; /* 设置延迟时间为2秒 */
        animation-fill-mode: forwards; /* 动画结束后保持最后一帧状态 */
      }

      .car1_light {
        margin: 0 auto;
        top: 8.9vw;
        width: 15vw;
        left: 1vw;
        right: 0;
      }
      .car2_light {
        margin: 0 auto;
        top: 7.6vw;
        width: 22vw;
        right: 1vw;
      }
      .car3_light {
        margin: 0 auto;
        top: 9.9vw;
        width: 21vw;
        left: 0.2vw;
      }
    }
  }

  .fangkuai {
    width: 100%;
    height: 10vw;
    left: 48%;
    // transform: translate(-50%, 0);
    margin: 0 auto;
    position: relative;

    transition: opacity 1s ease-in-out;
    img {
      width: 5vw;
      height: 5vw;
      position: absolute;
      left: 0;
      top: 0;
      transition: left 2s;
      opacity: 1;
    }
  }
  .text img:nth-child(2) {
    left: -5vw;
    opacity: 1;
  }
  .text img:nth-child(3) {
    left: 5vw;
    opacity: 1;
  }
  .text img:nth-child(4) {
    left: -10vw;
    opacity: 1;
  }
  .text img:nth-child(5) {
    left: 10vw;
    opacity: 1;
  }
}
@media only screen and (max-width: 768px) {
  .car_box {
    margin: -1px 0;
    .car_content {
      padding-top: 3vw;
      .three_car {
        .k_text {
          font-size: 2vw;
          white-space: nowrap;
          position: static !important;
        }
        .car1_light {
          margin: 0 auto;
          // top: 17.9vw;
          top: 15vw;
          width: 12vw;
          left: 1vw;
          right: 0;
        }
        .car2_light {
          margin: 0 auto;
          // top: 17.3vw;
          top: 14vw;
          width: 22vw;
          right: 1vw;
        }
        .car3_light {
          margin: 0 auto;
          // top: 18.9vw;
          top: 16vw;
          width: 21vw;
          left: 0.2vw;
        }
      }
    }
  }
}

@keyframes fade-in {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

// 汽车动画
@-webkit-keyframes car {
  0% {
    opacity: 0;
    -webkit-transform: translateY(+100%);
    transform: translateY(+100%);
  }

  to {
    opacity: 1;
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
}

@keyframes car {
  0% {
    opacity: 0;
    -webkit-transform: translateY(+100%);
    transform: translateY(+100%);
  }

  to {
    opacity: 1;
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
}

@keyframes slideRight {
  0% {
    transform: translateX(0);
    opacity: 0;
  }
  100% {
    transform: translateX(100%);
    opacity: 1;
  }
}

@keyframes slideR {
  0% {
    transform: translateX(0);
    // margin-left: 0vw;
    opacity: 0;
  }

  100% {
    transform: translateX(100%);
    // margin-left: 12vw;
    opacity: 1;
  }
}

@keyframes slideLeft {
  0% {
    transform: translateX(0);
    opacity: 0;
  }
  100% {
    transform: translateX(100%);
    opacity: 1;
  }
}

// @keyframes slideL {
//   0% {
//     transform: translateX(0);
//     margin-left: 0vw;
//     opacity: 0;
//   }

//   100% {
//     transform: translateX(100%);
//     margin-left: -24vw;
//     opacity: 1;
//   }
// }
</style>
<i18n lang="json">
{
  "en": {
    "an": {
      "a": "The balanced number: Five",
      "Joyful": "Joyful",
      "off": "Off-road performance",
      "out": "Outdoor experience",
      "sty": "Stylish journey"
    }
  },
  "ar": {
    "an": {
      "a": "خدر التوازن : خمسة",
      "Joyful": "سعيد !",
      "off": "عبر البلاد الأداء",
      "out": "تجربة في الهواء الطلق",
      "sty": "رحلة أزياء"
    }
  },
  "es": {
    "an": {
      "a": "Entumecimiento del equilibrio: cinco",
      "Joyful": "Feliz",
      "off": "Rendimiento todoterreno",
      "out": "Experiencia al aire libre",
      "sty": "Un viaje de moda"
    }
  }
}
</i18n>
