<template>
  <div class="box">
    <!-- 导航 -->
    <topic-2023-capital-beauty-redefined-longImage :display-block="1"> </topic-2023-capital-beauty-redefined-longImage>

    <topic-2023-capital-beauty-redefined-LeftRightLayout> </topic-2023-capital-beauty-redefined-LeftRightLayout>

    <!-- 北京四张图 -->
    <topic-2023-capital-beauty-redefined-longImage :display-block="2"> </topic-2023-capital-beauty-redefined-longImage>

    <div class="capital_text">{{ $t('cap.a') }}</div>

    <!-- 三张图 -->
    <topic-2023-capital-beauty-redefined-longImage :display-block="3"> </topic-2023-capital-beauty-redefined-longImage>

    <!-- 五张图拼凑 -->
    <topic-2023-capital-beauty-redefined-contentCapital> </topic-2023-capital-beauty-redefined-contentCapital>

    <topic-2023-capital-beauty-redefined-LeftRightLayout1> </topic-2023-capital-beauty-redefined-LeftRightLayout1>
    <topic-2023-capital-beauty-redefined-LeftRightLayout2> </topic-2023-capital-beauty-redefined-LeftRightLayout2>

    <!-- 一张图带故宫的图 -->
    <topic-2023-capital-beauty-redefined-longImage :display-block="4"> </topic-2023-capital-beauty-redefined-longImage>
  </div>
</template>
<script>
export default {
  name: 'So',
  layout: 'main', //导航

  data() {
    return {
    }
  },
  mounted() {
    this.$nextTick(() => {
    })
  }
}
</script>
<style lang="scss" scoped>
.box {
  width: 100%;
  .capital_text {
    width: 100%;
    text-align: center;
    padding: 5vw 0;
    // font-size: 3vw;
    font-size: 2.5vw;
    white-space: pre-line;
  }

  .text_describe {
    background-color: #000000;
    width: 100%;
    text-align: end;
    font-size: 1.8vw;
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
  .regarding_text {
    text-align: left;
    white-space: pre-line;
  }

  .regarding_text1 {
    padding: 17vw 5vw 17vw 5vw;
    // margin: -1px;
    text-align: center;
    white-space: pre-line;
  }
  .text_Capital_Beauty {
    text-align: center;
    color: #000000;
    font-size: 2.8vw;
    padding: 4.7vw 0;
    text-transform: uppercase;
  }
  .joy {
    .joy_text_box {
      padding: 8vw 6vw 8vw 6vw;
      .joy_text {
        font-size: 3vw;
      }
      .joy_subtitle {
        padding-top: 5.3vw;
        font-size: 1.7vw;
      }
    }
  }
  .module1 {
    width: 100%;
    height: 100vh;
    background-color: #f5f5f5;
  }

  .module1-enter-active,
  .module1-leave-active {
    transition: all 0.5s;
  }
  .module1-enter {
    transform: translateX(-100%);
  }
  .module1-leave-to {
    transform: translateX(100%);
  }
}
.lang___ar {
  .box .regarding_text {
    text-align: right;
  }
  .box .capital_text {
    // direction: ltr;
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
      "a": "نحن استخراج العناصر الأساسية من الخصائص الإنسانية والمباني الحضرية في الشرق ، ومواصلة البحث في تصميم \nانحناء خطي و الشطب ، وتشكيل نمط الأساسية ، مع النظام و درجة الحرارة ."
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
