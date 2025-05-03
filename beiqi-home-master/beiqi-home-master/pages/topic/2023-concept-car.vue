<template>
  <div class="concept_car_box" @scroll="onScroll">
    <topic-2023-capital-beauty-redefined-longImage :display-block="5"> </topic-2023-capital-beauty-redefined-longImage>
    <topic-2023-capital-beauty-redefined-LeftRightLayout6> </topic-2023-capital-beauty-redefined-LeftRightLayout6>

    <topic-2023-capital-beauty-redefined-longImage :display-block="6"> </topic-2023-capital-beauty-redefined-longImage>

    <div class="text_describe">
      <div class="Weextracted_text">{{ $t('Weextracted.a') }}</div>
    </div>


    <div class="carAnimationTop" ref="myElement">
      <topic-2023-capital-beauty-redefined-carAnimation :show="carshow"> </topic-2023-capital-beauty-redefined-carAnimation>
    </div>

    <div class="text_describe regarding_text">
      <div class="regarding_text">{{ $t('regarding.a') }}</div>
    </div>

    <div class="carAnimationTop1" ref="myElement1">
      <topic-2023-capital-beauty-redefined-carAnimationArrow1 :carshedShow="carshed" :carshedShow1="carshedLine"> </topic-2023-capital-beauty-redefined-carAnimationArrow1>
    </div>
    <div class="carAnimationTop2" ref="myElement2">
      <topic-2023-capital-beauty-redefined-carAnimationArrow2 :carShowAntwo="carshed1" :carshowdoor1="carshed2" :carshowxian2="carshed3"> </topic-2023-capital-beauty-redefined-carAnimationArrow2>
    </div>

    <!-- Oriental左右布局 -->

    <div class="outan2">
      <topic-2023-capital-beauty-redefined-LeftRightLayout3> </topic-2023-capital-beauty-redefined-LeftRightLayout3>
    </div>
    <topic-2023-capital-beauty-redefined-longImage :display-block="7"> </topic-2023-capital-beauty-redefined-longImage>

    <topic-2023-capital-beauty-redefined-LeftRightLayout4> </topic-2023-capital-beauty-redefined-LeftRightLayout4>
    <topic-2023-capital-beauty-redefined-imageUpandDown> </topic-2023-capital-beauty-redefined-imageUpandDown>
    <topic-2023-capital-beauty-redefined-LeftRightLayout5> </topic-2023-capital-beauty-redefined-LeftRightLayout5>
  </div>
</template>
<script>
export default {
  name: 'So',
  layout: 'main', //导航

  data() {
    return {
      carshow: false, //汽车动画
      carshed: false, //车棚&箭头
      carshedLine: false, //车顶部线
      carshed1: false, //遮罩文字
      carshed2: false, // 侧门动画
      carshed3: false, // 腰线动画
    }
  },

  mounted() {
    this.$nextTick(() => {
    })

    // 使用 nextTick 等待元素渲染完成
    this.$nextTick(() => {
      const options = {
      root: null,
      rootMargin: '0px',
      threshold: 0.5 // 当目标元素50%进入视口时触发回调函数
      };

      const observer = new IntersectionObserver(this.handleIntersection,options)
      observer.observe(this.$refs.myElement)
      observer.observe(this.$refs.myElement1)
      observer.observe(this.$refs.myElement2)
    })
  },
  beforeDestroy() {
  },
  created() {
  },

  methods: {
    handleIntersection(entries) {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          if (entry.target === this.$refs.myElement && entry.intersectionRatio >= 0.5) {
            console.log('目标元素1进入视口')
            // 执行针对目标元素1的操作
            this.carshow = true
          } else if (entry.target === this.$refs.myElement1 && entry.intersectionRatio >= 0.5) {
            console.log('目标元素2进入视口')

            this.carshed = true
            this.carshedLine = true

            // 执行针对目标元素2的操作
          } else if (entry.target === this.$refs.myElement2 && entry.intersectionRatio >= 0.5) {
            console.log('目标元素3进入视口')
            // 执行针对目标元素3的操作
            this.carshed1 = true
            this.carshed2 = true
            this.carshed3 = true
          }
        } else {
          if (entry.target === this.$refs.myElement) {
            console.log('目标元素1离开视口')
            this.carshow = false

            // 执行其他操作
          } else if (entry.target === this.$refs.myElement1) {
            console.log('目标元素2离开视口')
            this.carshed = false
            this.carshedLine = false
            // 执行其他操作
          } else if (entry.target === this.$refs.myElement2) {
            console.log('目标元素3离开视口')
            // 执行其他操作
            this.carshed1 = false
            this.carshed2 = false
            this.carshed3 = false
          }
        }
      })
    },

    onScroll() {
      const windowHeight = window.innerHeight
    },
    handleInitialScroll() {
      // 获取初始滚动位置
      const scrollTop = document.documentElement.scrollTop || document.body.scrollTop

      // 初始化布尔类型数据属性
      if (scrollTop > this.top - window.innerHeight / 2.5) {
        this.carshow = true
      }

      if (scrollTop > this.top2 - window.innerHeight / 1) {
        this.carshed = true
        setTimeout(() => {
          this.carshedLine = true
        }, 2000)
      }

      if (scrollTop > this.top3 - window.innerHeight / 9) {
        this.carshed1 = true
        setTimeout(() => {
          this.carshed2 = true
        }, 3000)
        setTimeout(() => {
          this.carshed3 = true
        }, 2000)
      }
    },
  },
}
</script>
<style lang="scss" scoped>
.concept_car_box {
  width: 100%;
  .text_describe {
    background-color: #000000;
    width: 100%;
    text-align: end;
    // font-size: 1.8vw;
    font-size: 1.5vw;
    padding: 0vw 5.1vw 5vw 5vw;
    color: #fff;
    .Weextracted_text {
      width: 80%;
      margin-left: 18vw;
      white-space: pre-line;
    }
    .regarding_text {
      width: 98%;
      white-space: pre-line;
    }
  }
}
</style>
<!-- 语言 -->
<i18n lang="json">
  {
    "en": {
      "cap": {
        "a": "CAPITAL BEAUTY: KEYWORDS OF THE DESIGN\nCONCEPT ARE..."
      },
      "Weextracted": {
        "a": "We extracted the basic elements from the humanistic characteristics and urban \n architecture of the East, and continued to study, aiming at the linear curvature design\n and the processing of chamfer, the basic pattern was formed, with order and \ntemperature."
      },
      "regarding": {
        "a": "Regarding the design of the front face, we start from the basic graphics and combine the balanced and\n stable number \"five\" to form a highly identifiable \"five-hole element\" design, representing the integrity of\nthe Eastern spirit. Combined with the requirements of different models of the BAlC brand, the five\nelements of differentiation are formed to strengthen the visual identity of the BAlC brand."
      },
      "theexterior": {
        "a": "The exterior design concept of confidence: \"Digital Chinese Knot\"\nfront face design"
      }
    },
    "ar": {
      "cap": {
        "a": "جمال العاصمة: الكلمات الرئيسية للتصميم هي……"
      },
      "Weextracted": {
        "a": "قمنا باستخراج العناصر الأساسية من الخصائص الإنسانية والعمارة الحضرية في الشرق، \n وواصلنا الدراسة، بهدف تصميم الانحناء الخطي ومعالجة الحواف المشطوفة، وتم تشكيل النمط الأساسي، مع النظام ودرجة الحرارة."
      },
      "regarding": {
        "a": "في تصميم الوجه الأمامي ، ونحن نبدأ مع الرسم البياني الأساسية ، والجمع بين التوازن والاستقرار الرقمي \" خمسة \" ، تشكيل \" خمسة \nعناصر \" تصميم مع درجة عالية من التمييز ، الذي يمثل سلامة الروح الشرقية . جنبا إلى جنب مع الطلب من أنواع مختلفة من السيارات من\nبكين للسيارات العلامة التجارية ، خمسة عوامل أساسية في تشكيل الفرق ، وتعزيز العلامة التجارية البصرية شعار بكين للسيارات ."
      },
      "theexterior": {
        "a": "الثقة بالنفس مفهوم التصميم : \" الرقمية عقدة الصين \"\nتصميم الوجه الأمامي"
      }
    },
    "es": {
      "cap": {
        "a": "CAPITAL BEAUTY: LAS PALABRAS CLAVE\nDEL CONCEPTO DE DISEÑO SON..."
      },
      "Weextracted": {
        "a": "Extrajimos elementos básicos de las características humanísticas del este y de la \n arquitectura urbana, y continuamos estudiando, para el diseño de la curvatura lineal y el \ntratamiento del acaparamiento, se formaron patrones básicos con orden y temperatura."
      },
      "regarding": {
        "a": "En el diseño de la cara delantera, comenzamos con gráficos básicos y combinamos el número equilibrado\ny estable \"cinco\" para formar un diseño muy reconocible de \"elementos de cinco agujeros\", que\nrepresenta la integridad del espíritu Oriental. De acuerdo con los requisitos de los diferentes modelos de\nla marca BAIC, se forman cinco elementos diferenciados para fortalecer el logotipo visual de la marca BAIC."
      },
      "theexterior": {
        "a": "Concepto de diseño exterior confiado: \"nudo chino digital\"\nDiseño frontal"
      }
    }
  }
  </i18n>
