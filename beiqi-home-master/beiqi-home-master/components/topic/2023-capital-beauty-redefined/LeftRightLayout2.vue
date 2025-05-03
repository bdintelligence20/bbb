<template>
  <div class="main-container">
    <!-- 黑白背景 -->
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
        <img src="../../../static/topic/2023-capital-beauty-redefined/images/meeting.jpg" alt="" srcset="" />
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
      "modern": "WARM",
      "simplicity": "EXPERIENCE",
      "ifone": "\"Purity of Confidence\" and \"Warm\nWisdom\" are the design concepts of BAIC\nGroup's BEIJING brand. Whether it is exteri-\nor design or interior design, from the per-\nspective of users, experience a feeling of \nunlimited freedom, bring positive feelings\nto future users, and convey the maturity\nand confidence of brand design from the\nemotional value."
    }
  },
  "ar": {
    "form": {
      "modern": "دفء و",
      "simplicity": "خبرة",
      "ifone":"\n \"نقاء الثقة\" و\"الحكمة الدافئة\" \n  هما مفهوما التصميم  للعلامة التجارية بكين لمجموعة BAIC، \n  سواء كان ذلك تصميمًا خارجيًا أو تصميمًا داخليًا،  \n من وجهة نظر المستخدمين، يمكنك تجربة شعور بالحرية غير المحدودة، \n وجلب مشاعر إيجابية للمستخدمين المستقبليين، \n  ونقل نضج وثقة تصميم العلامة التجارية من القيمة العاطفية."
    }
  },
  "es": {
    "form": {
      "modern": "CALIENTE",
      "simplicity": "EXPERIENCIA",
      "ifone": "La \"pureza de la confianza\" y el \"calor de \nla sabiduría\" son los conceptos de diseño\nde la marca Beijing del Grupo BAIC. Ya\nsea diseño exterior o diseño de\ninteriores, desde la perspectiva del\nusuario, experimente una sensación de\nlibertad infinita, traiga sentimientos\npositivos a los futuros usuarios y\ntransmita la madurez y confianza en sí\nmismo del diseño de marca desde el\nvalor emocional."
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
    flex-flow: row-reverse;
    justify-content: space-between;
    padding: 6vw 3vw 0 4vw;
    .left_box {
      width: 68%;
      margin-top: 0vw;
      .left_text {
        text-align: right;
        padding-right: 2vw;

        .left_box_text {
          // font-size: 4vw;
          font-size: 2.5vw;
          padding-bottom: 2vw;
          // font-family: twotext;
          // line-height: 3vw;//有字体
          line-height: 5vw;
        }
        .left_box_subtext {
          // font-family: Mediumtext;
          font-size: 1.4vw;
          padding-left: 5vw;
          padding-right: 0 !important;
          width: 100%;
          white-space:pre-line;
        }
      }
    }
    .right_box {
      width: 100%;
    }
  }
}

.lang___ar {
  .main-container .box_container .left_box .left_text {
    text-align: right;
    padding-right: 4vw;
  }
  .main-container .box_container .left_box .left_text .left_box_text {
    font-weight: 600;
  }
  .main-container .box_container .left_box .left_text .left_box_subtext{
    font-size: 1.39vw;
    padding-left: 0;
  }
}

@media only screen and (max-width: 768px) {
  .main-container {
    .box_container_m {
      .left_box {
        width: 93%;
        position: absolute;
        height: 51.9vw;
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
        bottom: -1vw;
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
          padding-right: 3vw;
          white-space: normal;
        }
        .open_height {
          -webkit-line-clamp: unset;
        }
      }
    }
  }
}
</style>
