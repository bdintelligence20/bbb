<template>
  <div class="carAnimation_arrow">
    <img src="../../../static/topic/2023-capital-beauty-redefined/images/image11.jpg" />
    <div :class="{ zhezhao1: carShowAntwo }" class="zhezhao"></div>
    <div class="car_line" ref="image">
      <div class="car_line_box" :class="{ car_an: carshowdoor1 }">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/shadow_door.png" :style="{ height: imgHeight + 'px' }" />
      </div>
    </div>

    <div class="car_line1" ref="image1">
      <div class="car_line_box1" :class="{ car_an: carshowxian2 }">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/xian.png" :style="{ height: imgHeight1 + 'px' }" />
      </div>
    </div>

    <div class="text_box" :class="{ text1: carShowAntwo }">
      <div class="left_text">
        <div class="left_box_subtext hover_mobile" @click="openContent">
          <div
            :class="['content', isOpen && 'open_height']"
            :style="{
              maxHeight: this.max_height,
              '-webkit-line-clamp': lineClamp,
            }"
            ref="my_height"
          >
            {{ $t('an2.a') }}
          </div>
        </div>
      </div>
    </div>

    <!-- <div class="text" :class="{ text1: carShowAntwo }">{{ $t('an2.a') }}</div> -->
  </div>
</template>
<script>
export default {
  props: {
    carShowAntwo: Boolean,
    carshowdoor1: Boolean,
    carshowxian2: Boolean,
  },
  data() {
    return {
      imgHeight: 0,
      imgHeight1: 0,
      max_height: '10vw',
      isOpen: false,
      lineClamp: 1,
    }
  },
  mounted() {
    const imageElement = this.$refs.image
    const imageElement1 = this.$refs.image1
    const imageWidth = imageElement.offsetWidth
    const imageWidth1 = imageElement1.offsetWidth
    this.imgHeight = imageWidth * 0.11
    this.imgHeight1 = imageWidth1 * 0.19999

    // setTimeout(() => {
    //   this.animate = true
    // }, 1000) // 设置延时时间，单位为毫秒
  },
  methods: {
    openContent() {
      if (!this.isOpen && this.$refs.my_height.scrollHeight > 45) {
        this.lineClamp = 'unset'
        this.max_height = this.$refs.my_height.scrollHeight + 'px'
      } else {
        this.max_height = '10vw'
        setTimeout(() => {
          this.lineClamp = 1
        }, 400)
      }
      this.isOpen = !this.isOpen
    },
  },
}
</script>
<style scoped lang="scss">
// @font-face {
//   font-family: Regular;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSansSC-Regular.otf') format('truetype');
// }

.carAnimation_arrow {
  width: 100%;
  height: 100%;
  position: relative;
  .text_box {
    width: 55%;
    position: absolute;
    text-align: right;
    top: 25vw;
    color: #fff;
    font-size: 1.5vw;
    // font-size: 1.6vw;
    right: 5vw;
    opacity: 0;
  }
  .text1 {
    animation-name: fade-in;
    animation-duration: 2s;
    animation-delay: 2s; /* 等待2秒后再启动动画 */
    animation-fill-mode: forwards;
    // font-family: Regular;
  }
  //  线动画
  .car_line {
    width: 100%;
    position: absolute;
    top: 17vw;
    left: 20.5vw;
    right: 0;
    margin: 0 auto;
  }

  .car_line_box {
    width: 0%;
    transition: all 3s;
    transition-timing-function: linear;
    overflow-x: hidden;
    img {
      object-fit: cover;
      max-width: none;
    }
  }
  .car_line1 {
    width: 100%;
    position: absolute;
    top: 5.1vw;
    left: 20vw;
    right: 0;
    margin: 0 auto;
  }

  .car_line_box1 {
    width: 0%;
    transition: all 1s 1s;
    transition-timing-function: linear;
    overflow-x: hidden;
    img {
      object-fit: cover;
      max-width: none;
    }
  }

  .car_line_an {
    width: 100% !important;
  }

  .car_an {
    width: 100%;
  }
  .zhezhao {
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.5);
    position: absolute;
    opacity: 0;
  }
  .zhezhao1 {
    animation-name: fade-in;
    animation-duration: 1s;
    // animation-delay: 1s; /* 等待2秒后再启动动画 */
    animation-fill-mode: forwards;
  }
}
.lang___ar {
  .carAnimation_arrow {
    direction: initial !important;
    text-align: right;
  }
  .carAnimation_arrow .text {
    width: 48%;
    position: absolute;
    text-align: right;
    top: 25vw;
    color: #fff;
    font-size: 1.6vw;
    left: 3vw;
    right: 45vw;
    opacity: 0;
  }
}

@media only screen and (max-width: 768px) {
  .carAnimation_arrow {
    font-size: 2vw;
    .text_box {
      width: 98%;
      margin-top: 0vw;
      opacity: 0;
      top: 37vw;
      right: 0vw;
      .left_text {
        .left_box_text {
          // font-family: twotext;
          font-size: 4vw;
          padding-bottom: 2vw;
          line-height: 3vw;
        }
        .left_box_subtext {
          // font-family: Mediumtext;
          width: 100%;
          font-size: 1.5vw;
          padding-right: 6vw;
        }
      }
    }
    .hover_mobile {
      box-sizing: border-box;
      color: #fff;
      height: auto;
      left: 0;
      padding: 2vw 3vw;
      position: absolute;
      width: 100%;
      bottom: 0;
      .content {
        max-height: 10vw;
        overflow: hidden;
        font-size: 3vw;
        line-height: 5vw;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
        text-overflow: ellipsis;
        transition: all 0.4s;
      }
      .open_height {
        -webkit-line-clamp: unset;
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
</style>
<i18n lang="json">
{
  "en": {
    "an2": {
      "a": "\"Leisurely\" surface. The simple and pure curved design, combined with the strong wheel arch surface, creates a safe body image, with speed, and cutting sense of the line, reflecting the young fashion, as well as a sense of controlled movement."
    }
  },
  "ar": {
    "an2": {
      "a": "\" استرخاء \" السطح . بسيطة ونقية تصميم المنحنى ، جنبا إلى جنب مع أقواس قوية على السطح ، وخلق صورة الجسم آمنة ، مع شعور من السرعة و قطع الخط ، مما يعكس الشعور أزياء الشباب ، فضلا عن التحكم في الحركة ."
    }
  },
  "es": {
    "an2": {
      "a": "Una superficie \"relajada\". Diseño curvo simple y puro, combinado con una fuerte superficie de arco de rueda, crea una imagen segura de la carrocería, con un sentido de velocidad y Corte de líneas, que refleja un sentido de moda joven y un sentido de movimiento controlable."
    }
  }
}
</i18n>
