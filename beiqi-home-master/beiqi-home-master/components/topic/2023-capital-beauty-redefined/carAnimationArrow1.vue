<template>
  <div class="carAnimation_arrow">
    <img src="../../../static/topic/2023-capital-beauty-redefined/images/image10.jpg" class="topimage" />
    <div :class="{ zhezhao1: carshedShow }" class="zhezhao"></div>

    <div class="car_line" ref="image">
      <div class="car_line_box" :class="{ car_an: carshedShow1 }">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/car_line.png" class="car_line_img" />
      </div>
    </div>

    <div class="arrow_box" :class="{ zhezhao1: carshedShow }">
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/left_arrow.png" :class="{ left_arrow: carshedShow }" />
      <img src="../../../static/topic/2023-capital-beauty-redefined/images/right_arrow.png" :class="{ right_arrow: carshedShow }" />
    </div>

    <div class="text_box" :class="{ text1: carshedShow }">
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
            {{ $t('an1.the') }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    carshedShow: Boolean,
    carshedShow1: Boolean,
  },
  data() {
    return {
      imgHeight: 0,
      max_height: '10vw',
      isOpen: false,
      lineClamp: 1,
      // animate: false,
    }
  },
  mounted() {
    const imageElement = this.$refs.image
    const imageWidth = imageElement.offsetWidth
    console.log(imageWidth)
    this.imgHeight = imageWidth * 0.20625
    // setTimeout(() => {
    //   // this.animate = true
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
  // height: 100%;
  position: relative;
  .text_box {
    width: 48%;
    position: absolute;
    text-align: left;
    top: 25vw;
    color: #fff;
    // font-size: 1.6vw;
    font-size: 1.5vw;
    left: 3vw;
    opacity: 0;
  }
  .topimage {
    width: 100%;
  }
  .text1 {
    animation-name: fade-in;
    animation-duration: 2s;
    animation-delay: 3s; /* 等待2秒后再启动动画 */
    animation-fill-mode: forwards;
    // font-family: Regular;
  }

  //  线动画
  .car_line {
    width: 100%;
    // position: absolute;
    top: 1vw;
    left: 0;
    right: 0;
    margin: 0 auto;
  }

  .car_line_box {
    width: 0%;
    transition: all 3s;
    transition-timing-function: linear;
    overflow-x: hidden;
    position: absolute;
    top: 0;
    height: 100%;
    left: 0;

    img {
      object-fit: cover;
      max-width: none;
      position: absolute;
      width: 100vw;
      top: 1vw;
      left: 0;
    }
  }

  .car_an {
    width: 100%;
  }
}

.car_line_an {
  width: 100% !important;
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

.arrow_box {
  position: absolute;
  display: flex;
  width: 63%;
  top: 18vw;
  justify-content: space-between;
  left: -8vw;
  right: 0;
  margin: 0 auto;
  opacity: 0;
  .left_arrow {
    width: 15vw;
    height: 15vw;
    opacity: 0;
    animation: showImage 1s 1s forwards, moveLeft 2s ease-in-out 2s forwards;
  }
  .right_arrow {
    width: 15vw;
    height: 15vw;
    opacity: 0;
    animation: showImage 2s 2s forwards, moveRight 2s ease-in-out 2s forwards;
  }
}

.lang___es{
  .carAnimation_arrow .text_box{
    top: 24vw;
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

@keyframes moveImages {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(100px);
  }
}

// 箭头动画

@keyframes showImage {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

@keyframes moveLeft {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-3vw);
  }
}
@keyframes moveRight {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(5vw);
  }
}

.lang___ar {
  .main-container .joy_box .left_box {
    margin-top: 0;
    width: 73%;
    flex: 4;
  }
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
    right: 3vw;
    opacity: 0;
    left: 47vw;
  }
}
</style>
<i18n lang="json">
{
  "en": {
    "an1": {
      "the": "The \"poised\" ratio. The JOY concept car is 5013 mm long, 2042 mm wide and has a wheelbase of 3000 mm. Large shaft ratio and low wind resistance profile bring stable posture and spacious interior space to the user."
    }
  },
  "ar": {
    "an1": {
      "the": "تاسا دي \"التوازن\". مفهوم الفرح 5.013 ميلمتر متري دي لانغو، 2.042 ميلمتر متري دي لانتشو و 3.000 ميلمتر متري دي ديستانتسيا بين الجزر. العلاقة بين المياه والمياه الجوفية مقاومة المياه العذبة العذبة بعد الولادة وبيئة الفضاء الداخلية."
    }
  },
  "es": {
    "an1": {
      "the": "Tasa de \"equilibrio\". El concepto JOY tiene 5.013 milímetros de largo, 2.042 milímetros de ancho y 3.000 milímetros de distancia entre ejes. la gran relación entre ejes y la baja resistencia al viento aportan al usuario una postura estable y un amplio espacio interior."
    }
  }
}
</i18n>
