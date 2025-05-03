<!--
 * @Author: 不二
 * @Date: 2024-01-08 15:47:20
 * @LastEditTime: 2024-01-11 17:43:29
-->
<template>
  <div class="content_box content_box_mobile">
    <div class="title title_m_01">
      <img src="~/assets/image/offRoad/m_01.png" alt="" />
      <p class="title_data"></p>
    </div>

    <div class="content_1">
      <img src="~/assets/image/offRoad/m_02.png" alt="" class="img_m_02" />
      <div class="text text_1">
        <span> {{ $t("text_1_1") }}</span>
      </div>
      <div class="text text_2">
        <span> {{ $t("text_1_2") }}</span>
      </div>
      <div class="text text_3">
        <span> {{ $t("text_1_3") }}</span>
      </div>
      <img src="~/assets/image/offRoad/006.png" alt="" class="img_m_006" />
    </div>

    <div class="title title_m_02">
      <img src="~/assets/image/offRoad/m_03.png" alt="" />
      <p class="title_data">{{ $t("title_2") }}</p>
    </div>

    <div class="content_2">
      <div class="text text_4">{{ $t("text_2_1") }}</div>

      <div class="text text_5">{{ $t("text_2_2") }}</div>

      <div class="text text_6">{{ $t("text_2_3") }}</div>
      <img src="~/assets/image/offRoad/m_04.png" alt="" class="img_m_04" />
    </div>

    <div class="content_3">
      <img src="~/assets/image/offRoad/m_05.png" alt="" class="img_m_05" />
      <img src="~/assets/image/offRoad/008.png" alt="" class="img_m_008" />
      <img src="~/assets/image/offRoad/009.png" alt="" class="img_m_009" />
      <img src="~/assets/image/offRoad/010.png" alt="" class="img_m_010" />
      <img src="~/assets/image/offRoad/012.png" alt="" class="img_m_012" />
    </div>

    <div class="title title_m_03">
      <p class="title_data">{{ $t("title_3") }}</p>
    </div>

    <div class="content_4">
      <div class="text text_7">{{ $t("text_3_1") }}</div>
    </div>

    <div class="content_5">
      <img src="~/assets/image/offRoad/011.png" alt="" class="img_m_011" />
      <div class="content_5_box">
        <div class="title title_m_04">
          <p class="title_data">{{ $t("title_4") }}</p>
        </div>
        <div class="text text_8">{{ $t("text_4_1") }}</div>
        <img src="~/assets/image/offRoad/013.jpg" alt="" class="img_m_13">
      </div>
      <img src="~/assets/image/offRoad/011.png" alt="" class="img_m_011_1" />
    </div>
  </div>
</template>

<script>
import { gsap } from 'gsap/dist/gsap'
import TextPlugin from 'gsap/dist/TextPlugin'
import { ScrollTrigger } from 'gsap/dist/ScrollTrigger'
gsap.registerPlugin(TextPlugin)
gsap.registerPlugin(ScrollTrigger)
export default {
  data() {
    return {}
  },
  mounted() {
    setTimeout(() => {
      this.init()
    })
  },
  methods: {
    init() {
      gsap.timeline({
        // 添加到整个时间线
        scrollTrigger: {
          trigger: '.content_box_mobile',
          pin: false, // 在执行时固定触发器元素
          start: 'top 50%', // 触发位置为触发器的顶部进入视口
          //   end: '+=1500', // 在滚动 500 px后结束
          scrub: true, // 触发器1秒后跟上滚动条
          markers: false,
          onEnter: e => {
            console.log('enter')
            this.ani_1()
          }, // 滚动到进入
          onUpdate: self => {
            // console.log('progress:', self.progress.toFixed(3), 'direction:', self.direction, 'velocity', self.getVelocity())
            let p = self.progress.toFixed(2) * 10
            console.log('进度', p)

            if (p > 0.3) {
              this.ani_2()
            }
            if (p > 0.5) {
              this.ani_3()
            }
            if (p > 1.5) {
              this.ani_4()
            }
            if (p > 2) {
              this.ani_5()
            }
            if (p > 2.6) {
              this.ani_6()
            }
            if (p > 3) {
              this.ani_7()
            }
            if (p > 3.4) {
              this.ani_8()
            }
            if(p>3.6){
                this.ani_9()
            }
            // if(p>3.8){
            //     this.ani_10()
            // }
            // if(p>4.2){
            //     this.ani_11()
            // }
            if(p>4.8){
                this.ani_12()
            }
            if(p>5.2){
                this.ani_13()
            }
            if(p>5.8){
                this.ani_16()
            }
            if(p>6){
                this.ani_14()
            }
            if(p>6.5){
                this.ani_15()
            }
            if(p>7){
                this.ani_18()
            }
            // if(p>7.5){
            //     this.ani_17()
            // }
          },
        },
      })
    },
    ani_1() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.title_m_01>img', {
        clipPath: 'polygon(0 0, 100% 0, 100% 100%, 0 100%)',

        delay: 0.2,
      })

      t1.to(
        '.title_m_01>.title_data',
        {
          duration: 1,
          text: {
            value: this.$t('title_1'),
            delimiter: '',
          },
          ease: 'ease',
          stagger: 0.01, // 每个字之间的间隔时间
        },
        '+=0.2'
      )

      t1.play()
    },
    ani_2() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.img_m_02', {
        clipPath: 'circle(70.9% at 50% 50%)',
      })

      t1.play()
    },
    ani_3() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.text_1', {
        opacity: 1,
        y: 0,
      })
      t1.to('.text_2', {
        opacity: 1,
        y: 0,
      })

      t1.to(
        '.text_3',
        {
          opacity: 1,
          y: 0,
        },
        '+=0.4'
      )
      t1.play()
    },
    ani_4() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.img_m_006', {
        x: 0,
      })
      t1.play()
    },
    ani_5() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.title_m_02>img', {
        clipPath: 'polygon(0 0, 100% 0, 100% 100%, 0 100%)',

        delay: 0.2,
      })

      t1.to(
        '.title_m_02 .title_data',
        {
          duration: 1,
          opacity: 1,
          y: 0,
        },
        '+=0.2'
      )

      t1.play()
    },
    ani_6() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })

      t1.to('.text_4', {
        opacity: 1,
        y: 0,
      })

      t1.play()
    },
    ani_7() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })

      t1.to('.text_5', {
        opacity: 1,
        y: 0,
      })

      t1.play()
    },
    ani_8() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })

      t1.to('.text_6', {
        opacity: 1,
        y: 0,
      })

      t1.play()
    },
    ani_9() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.img_m_04', {
        clipPath: 'polygon(100% 0, 1% 0, 1% 100%, 100% 100%)',
        delay: 0.2,
        onComplete:()=>{
          this.ani_10()
        }
      })

      t1.play()
    },
    ani_10() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.img_m_05', {
        clipPath: 'polygon(0 0, 100% 0, 100% 100%, 0 100%)',
        delay: 0.2,
      })
      t1.to(".img_m_009",{
       x:0,
        delay: 0.2,
      })
      t1.to(".img_m_008",{
        clipPath: 'circle(70.9% at 50% 50%)',
        delay: 0.2,
        onComplete:()=>{
          this.ani_11()
        }
      })

      t1.play()
    },
    ani_11() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to(".img_m_012",{
       x:0,
        delay: 0.2,
      })
      t1.to(".img_m_010",{
        clipPath: 'circle(70.9% at 50% 50%)',
        delay: 0.2,
      })

      t1.play()
    },
    ani_12() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })


      t1.to(
        '.title_m_03 .title_data',
        {
          duration: 1,
          opacity: 1,
          y: 0,
        },
        '+=0.2'
      )

      t1.play()
    },
    ani_13() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })

      t1.to('.text_7', {
        opacity: 1,
        y: 0,
      })

      t1.play()
    },
    ani_14() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to(
        '.title_m_04 .title_data',
        {
          duration: 1,
          opacity: 1,
          y: 0,
        },
        '+=0.2'
      )
      t1.play()
    },
    ani_15() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })

      t1.to('.text_8', {
        opacity: 1,
        y: 0,
      })

      t1.play()
    },
    ani_16() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.img_m_011', {
        clipPath: 'polygon(0 0, 100% 0, 100% 100%, 0 100%)',
        delay: 0.2,
      })

      t1.play()
    },
    // ani_17() {
    //   var t1 = gsap.timeline({
    //     paused: true,
    //     defaults: {
    //       duration: 0.6,
    //       ease: 'ease',
    //     },
    //   })
    //   t1.to('.img_m_011_1', {
    //     clipPath: 'polygon(0 0, 100% 0, 100% 100%, 0 100%)',
    //     delay: 0.2,
    //   })

    //   t1.play()
    // },
    ani_18() {
      var t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to('.img_m_13', {
        clipPath: "polygon(100% 100%, 100% 1%, 1% 1%, 1% 99%)",
        delay: 0.2,
      })

      t1.play()
    },
  },
  i18n:{
    messages:{
      en:{
      title_1:"Vehicle Integration <br>Capability",
      title_2:"Engineering Verification Ability",
      title_3:"Vehicle Production Ability",
      title_4:"High Designed Reliability",
      text_1_1:"Comprehensive application of computer-aided development methods;",
      text_1_2:"ln-depth integration of simulation analysis with design and experimental verification;",
      text_1_3:"The integration of the system such as component level, system level, and vehiclelevel is a comprehensive integration of virtual and reality.",
      text_2_1:"Able to test and verify the functions, performance, reliability, durability and environmental adaptability of components,  systems and complete vehicles, and have obtained CNAS certification.",
      text_2_2:"Professional trial production workshop, with complete trial production verification process, can produce 500 trial production, special production and modified prototypecars per vear, and the trial production capacity is leading in China.",
      text_2_3:"The big data intelligent analysis platform has realized the rapid and accurate extraction of valuable information from massive data, and successfully applied it to various processes of vehicle reearch and development.",
      text_3_1:'The off-road vehicle factory has an overall construction area of 240,000 square meters and an annual production capacity of 100,000 vehicles, and won the"National Green Factory", "'+"China's Best Factory Diqita Competition Excellence Award"+'"',
      text_4_1:"It has also created 28 limit and typical working condition load spectrum analysis and extraction methods combining dvnamic and static loads, invented 35 high-reliability structures such as opening and closing parts structure, whole vehicle locking system,  established virtual and physical tests combined with the multi-level test platform and method, realized the positive design of high reliability of off-road vehicles. The average mileage between failures of BAlC of-road vehicle base test has been increasd from 1,200km to 7,536km, well above the international level of similar products.",
    },
    es:{
      title_1:"Capacidad de integración <br>de vehículos",
      title_2:"Capacidad de verificación de ingeniería",
      title_3:"Capacidad de producción de vehículos",
      title_4:"Alta confiabilidad diseñada",
      text_1_1:"Aplicación integral de métodos de desarrollo asistidos por computadora;",
      text_1_2:"Integración profunda del análisis de simulación con diseño y verificacion experimental",
      text_1_3:"La integración del sistema, como el nivel de componentes, el nivel del sistema y el nivel del vehículo, es una integración integral de la virtualidad y la realidad.",
      text_2_1:" Capaz de probar y verificar las funciones,  el rendimiento, la confiabilidad, la durabilidad y la adaptabilidad ambiental de los componentes, sistemas y vehiculos completos, y baber obtenido la certificación CNAS;",
      text_2_2:"El taller de producción de prueba profesional, con un proceso completo de verificación de producción de prueba, puede producir 500 prototipos de producción de prueba, producción especial y prototipos modificados por año, y la capacidad de producción de prueba es líder en China.",
      text_2_3:"La plataforma de análisis inteligente de big data ha realizado la extracción rápida y precisa de información valiosa de datos masivos y la ha aplicado con éxito a varios procesos de investigación y desarrollo de vehículos.",
      text_3_1:'La fábrica de vehículos todoterreno tiene una superficie capacidad total de 240,000 metros cuadrados y una capacidad de producción anual de 100,000 vehículos, y ganó la "Fábrica Nacional Verde", "La Mejor Fábrica Dichita de China Premio a la Excelencia en el Concurso".',
      text_4_1:'También se ha creado 28 métodos de análisis y extracción de espectro de carga de condiciones de trabajo límite y típicas que combinan cargas dinámicas y estáticas, inventado 35 estructuras de alta confiabilidad como estructuras de apertura y cierre de piezas, sistema de bloqueo de todo el vehículo, pruebas virtuales y físicas establecidas combinadas con la plataforma y el metodo de prueba multinivel, realizado el diseño postivo de alta confiabilidad de los vehículos todoterreno. EI kilometraje promedio entre fallas de la prueba básica de vehiculos todoterreno BAlC se ha incrementado de 1200km a 7536 km, muy por encima del nivel internacional de productos similares.'
    },
    ar:{
      title_1:"إمكانية دمج المركبات",
      title_2:'القدرة على التحقق الهندسي',
      title_3:'قدرة إنتاج المركبات',
      title_4:"موثوقية عالية في التصميم",
      text_1_1:"التطبيق الشامل لأساليب التطوير بمساعدة الكمبيوتر؛",
      text_1_2:'التكامل العميق لتحليل المحاكاة مع التصميم والتحقق التجريبي؛',
      text_1_3:"إن تكامل النظام مثل مستوى المكون ومستوى النظام ومستوى المركبة هو تكامل شامل بين الافتراضي والواقع.",
      text_2_1:"القدرة على اختبار والتحقق من وظائف وأداء وموثوقية ومتانة وقابلية التكيف البيئي للمكونات والأنظمة والمركبات الكاملة، والحصول على شهادة CNAS.",
      text_2_2:'يمكن لورشة الإنتاج التجريبي الاحترافية، مع عملية التحقق الكاملة من الإنتاج التجريبي، إنتاج 500 سيارة تجريبية وإنتاج خاص ونموذج أولي معدل لكل عام، والقدرة على الإنتاج التجريبي رائدة في الصين.',
      text_2_3:'تمكنت منصة التحليل الذكي للبيانات الضخمة من استخراج المعلومات القيمة من البيانات الضخمة بسرعة ودقة، وتطبيقها بنجاح على مختلف عمليات البحث والتطوير في مجال المركبات.',
      text_3_1:'يبلغ إجمالي مساحة بناء مصنع مركبات الطرق الوعرة  240.000 متر مربع وسعة إنتاج سنوية تبلغ 100.000 مركبة، وقد فاز بجائزة "المصنع الأخضر الوطني" و"جائزة التميز في مسابقة ديكيتا لأفضل مصنع في الصين"',
      text_4_1:"كما ابتكرت 28 طريقة لتحليل واستخراج طيف الحمل لظروف العمل النموذجية والحدية من خلال الجمع بين الأحمال الديناميكية والثابتة، واختراع 35 بنية عالية الموثوقية مثل هيكل الأجزاء المفتوحة والمغلقة، ونظام قفل المركبة بالكامل، وتأسيس اختبارات افتراضية وفيزيائية جنبًا إلى جنب مع منصة الاختبار متعددة المستويات والطريقة، وتحقيق التصميم الإيجابي للموثوقية العالية للمركبات على الطرق الوعرة. تمت زيادة متوسط ​​المسافة بين حالات فشل اختبار قاعدة BAlC للمركبات على الطرق الوعرة من 1200 كيلومتر إلى 7536 كيلومتر، وهو أعلى بكثير من المستوى الدولي للمنتجات المماثلة."
    }
    }
  }

}
</script>
<style  scoped lang="scss">
$left_mg: 5vw;

.content_box {
  position: relative;
  min-height: 597vw;
}
.title_data {
  transform: translateY(5vw);
  opacity: 0;
}
.title {
  position: absolute;
  top: 0;
  left: 0;
  font-size: 5vw;
  color: #fff;
  font-weight: 900;

  img {
    width: 100vw;
    transform: translateY(-50%);

    clip-path: polygon(0 0, 1% 0, 0 100%, 0% 100%);
  }

  p {
    position: absolute;
    inset: 0;
    margin: auto;
    padding: $left_mg;

  }
}

.content_1 {
  position: absolute;
  top: 30vw;
  width: 100%;
  .img_m_02 {
    width: 100%;
    -webkit-clip-path: circle(0.1% at 50% 50%);
    clip-path: circle(0.1% at 50% 50%);
  }
  .text{
    justify-content: center;
  }
}

.text {
  font-size: 3.5vw;
  color: #000;
  position: absolute;
  left: 0;
  width: 100%;
  display: flex;

  justify-content: flex-start ;
  opacity: 0;
  transform: translateY(10vw);
  span {
    max-width: 70vw;
    line-height: 1.8;
  }
}

.text_1 {
  top: 28vw;
}

.text_2 {
  top: 50vw;
}

.text_3 {
  top: 73vw;
  z-index: 1;
}

.img_m_006 {
  position: absolute;
  right: 0;
  width: 50vw;
  bottom: -8vw;
  z-index: 0;
  transform: translateX(100%);
}
.content_2 {
  position: absolute;
  top: 169vw;

}
.title_m_01 {
  .title_data {
    opacity: 1;
    transform: none;
  }
}
.title_m_02 {
  top: 172vw;
  color: #000;
  img {
    transform: translateY(-25%);
  }
}
.content_2 {
  position: absolute;
  top: 200vw;
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0 5vw;
  .text {
    position: relative;
    margin-bottom: 6vw;
  }
  .img_m_04 {
    position: absolute;
    bottom: -18vw;
    right: -5vw;
    width: 60vw;
    clip-path: polygon(100% 0, 99% 0, 100% 100%, 99% 100%);

  }
}
.content_3 {
  position: absolute;
  top: 292vw;
  width: 100%;
  .img_m_008 {
    width: 50vw;
    position: absolute;
    top: -10vw;
    -webkit-clip-path: circle(0.1% at 50% 50%);
    clip-path: circle(0.1% at 50% 50%);
  }
  .img_m_009 {
    position: absolute;
    left: 0;
    top: 20vw;
    width: 50vw;
    transform: translateX(-100%);
  }
  .img_m_05 {
    position: absolute;
    left: 0;
    top: 20vw;
    width: 100%;
    clip-path: polygon(0 0, 1% 0, 0 100%, 0% 100%);
  }
  .img_m_010 {
    position: absolute;
    right: 0;
    width: 45vw;
    top: 30vw;
    -webkit-clip-path: circle(0.1% at 50% 50%);
    clip-path: circle(0.1% at 50% 50%);
  }
  .img_m_012 {
    position: absolute;
    width: 65vw;
    top: 50vw;
    right: 0vw;
    transform: translateX(100%);

  }
}
.title_m_03 {
  top: 391vw;
  width: 100%;
  color: #000;
  p {
    position: relative;
  }
}
.content_4 {
  position: absolute;
  top: 410vw;
  width: 100%;
  .text_7 {
    padding: 0 5vw;
  }
}
.content_5 {
  position: absolute;
  top: 440vw;
  width: 100%;
  .content_5_box {
    background-color: #000;
    min-height: 50vw;
    transform: translateY(-12vw);
    z-index: 1;
    position: relative;
    .title {
      position: relative;
      p {
        position: relative;
      }
    }
    .text {
      position: relative;
      color: #fff;
      padding: 0 $left_mg;
      margin-top: 5vw;
    }
  }
  .img_m_011{
    clip-path: polygon(0 0, 1% 0, 0 100%, 0% 100%);
  }
  .img_m_011_1 {
    position: relative;
    z-index: 0;
    transform: scale(-1) translateY(27vw);
    // clip-path: polygon(0 0, 1% 0, 0 100%, 0% 100%);
  }
}
.img_m_13{
    transform: translateY(-5vw);
    mix-blend-mode: difference;
    clip-path: polygon(50% 50%, 50% 50%, 50% 50%, 50% 50%);
}
.lang___es{
    .content_box_mobile{
        height: 625vw;
    }
    .img_m_006{
        bottom: -9vw;
    }
    .title_m_02{
        top: 173vw;
        img{
            transform: translateY(-13%);
        }
    }
    .img_m_04{
        bottom: -13vw;
    }
    .content_3{
        top: 294vw;
    }
    .title_m_03{
        top: 400vw;
    }
    .content_4{
        top: 425vw;
    }
    .content_5{
        top: 460vw;
    }

}
.lang___ar{
    .title_m_02{
        img{
            transform: translateY(-25%) scale(-1);
        }
    }
    .img_m_008{
        left: 0 ;
    }
    .img_m_04{
        bottom: -24vw;
    }
    .title_m_01{
        img{
            transform: translateY(-50%) scale(-1);
        }
    }
    .content_3{
        top: 268vw;
    }
    .title_m_03{
        top: 382vw;
    }
    .content_4{
        top: 401vw;
    }
    .content_5{
        top: 427vw;
    }
    .content_box_mobile{
        min-height: 569vw !important;
    }
}
</style>