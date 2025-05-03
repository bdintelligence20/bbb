<template>
  <div class="main-container">
    <!-- 蓝色车背景图 -->
    <div class="box_container box_container_m">
      <div class="left_box">
        <div class="left_text">
          <div class="left_box_text">{{ $t('form.modern') }}<br />{{ $t('form.simplicity') }}</div>
          <div class="left_box_subtext hover_mobile" @click="openContent">
            <div
              :class="['content', isOpen && 'open_height']"
              :style="{
                maxHeight: this.max_height,
                '-webkit-line-clamp': lineClamp,
              }"
              ref="my_height"
            >{{ $t('form.ifone') }}</div>
          </div>
        </div>
      </div>
      <div class="right_box">
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/bluecar.jpg" alt="" srcset="" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['layout'],
  data() {
    return {
      max_height: '10vw',
      isOpen: false,
      lineClamp: 2,
    }
  },
  methods: {
    openContent() {
      if (!this.isOpen && this.$refs.my_height.scrollHeight > 45) {
        this.lineClamp = 'unset'
        this.max_height = this.$refs.my_height.scrollHeight + 'px'
      } else {
        this.max_height = '10vw'
        setTimeout(() => {
          this.lineClamp = 2
        }, 400)
      }
      this.isOpen = !this.isOpen
    },
  },
}
</script>
<!-- 语言 -->
<i18n lang="json">
{
  "en": {
    "form": {
      "modern": "MODERN",
      "simplicity": "SIMPLICITY",
      "ifone": "If one word can be used to describe the\nbiggest feature of ancient Chinese\npainting and calligraphy culture, it must be\nBLANK. It can be said that the white\nspace is the essence of the Chinese art\nform, a unique Oriental and very special\nartistic expression technique."
    }
  },
  "ar": {
    "form": {
      "modern": "البساطة",
      "simplicity": "العصرية",
      "ifone": "إذا كان لنا أن استخدام كلمة واحدة لوصف أكبر سمة\nمن سمات الثقافة الصينية القديمة والخط والرسم ، \n يجب أن يكون \"وضع البياض\". يمكن القول أن وضع \nالبيضاء هي جوهر الفن الصيني ، هو نوع من \nالاستشراق فريدة من نوعها و خاصة جدا في التعبير\nالفني ."
    }
  },
  "es": {
    "form": {
      "modern": "MODERNO",
      "simplicity": "SIMPLICIDAD",
      "ifone": "Si se utiliza una palabra para describir\nlas mayores características de la\nantigua cultura china de caligrafía y\npintura, debe ser \"vacío\". Se puede\ndecir que el espacio en blanco es la\nesencia de la forma de arte chino, una\norientalizad única y una técnica de\nexpresión artística muy especial."
    }
  }
}
</i18n>

<style lang="scss" scoped>
// @font-face {
//   font-family: twotext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/AdobeArabic-Regular.otf') format('truetype');
// }
// @font-face {
//   font-family: Mediumtext;
//   src: url('../../../static/topic/2023-capital-beauty-redefined/font/SourceHanSansSC-Medium.otf') format('truetype');
// }
.main-container {
  width: 100%;
  .box_container {
    display: flex;
    padding: 7vw 3vw 0 5vw;
    .left_box {
      width: 68%;
      margin-top: 0vw;
      .left_text {
        .left_box_text {
          // font-family: twotext;
          // font-size: 4vw;
          font-size: 2.5vw;
          padding-bottom: 2vw;
          // line-height: 3vw;//有字体
          line-height: 5vw;//默认字体
        }
        .left_box_subtext {
          // font-family: Mediumtext;
          width: 100%;
          font-size: 1.5vw;
          padding-right: 6vw;
          white-space: pre-line;
        }
      }
    }
    .right_box {
      width: 100%;
    }
  }
}

.lang___ar {
  .main-container .box_container .left_box .left_text .left_box_subtext {
    // font-family: Mediumtext;
    width: 100%;
    font-size: 1.5vw;
    padding-left: 6vw;
    padding-right: 0;
  }

  .main-container .box_container .left_box .left_text .left_box_text{
    font-weight: 600;
  }

}

@media only screen and (max-width: 768px) {
  .main-container {
    .box_container_m {
      padding: 6vw 3vw 0 4vw;
      .left_box {
        width: 93%;
        position: absolute;
        height: 48.5vw;
        .left_text {
          color: #fff;
          width: 100%;
          .left_box_text {
            font-size: 6vw;
            padding: 3vw 0 0vw 3vw;
            line-height: 5vw;
          }
        }
      }

      .left_box_subtext {
        position: absolute;
        padding-right: 0vw !important;
        // top: 30vw;
      }
      .hover_mobile {
        background: linear-gradient(0deg, rgba(0, 0, 0, 1), transparent);
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
          /* height: 10vw; */
          font-size: 3vw;
          line-height: 5vw;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          text-overflow: ellipsis;
          transition: all 0.4s;
          white-space: normal;
        }
        .open_height {
          -webkit-line-clamp: unset;
        }
      }
    }
  }
  .lang___ar{
    .main-container .box_container_m .left_box{
      text-align: left;
    }
  }
}
</style>
