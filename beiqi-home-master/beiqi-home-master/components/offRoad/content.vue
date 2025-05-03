<!--
 * @Author: 不二
 * @Date: 2024-01-04 15:07:12
 * @LastEditTime: 2024-01-11 15:29:41
-->
<template>
  <div class="center_box">
    <div class="img_01">
      <img src="~/assets/image/offRoad/001.png" alt="" />
    </div>
    <div class="img_02">
      <p class="text title_1_p"></p>
      <img src="~/assets/image/offRoad/002.png" alt="" />
    </div>

    <div class="img_03">
      <img src="~/assets/image/offRoad/003.png" alt="" />
    </div>

    <div class="img_04">
      <img src="~/assets/image/offRoad/004.png" alt="" />
      <img src="~/assets/image/offRoad/006.png" alt="" class="img_06" />
    </div>

    <div class="text_1 content_text">
      <p>
        {{ $t('text_1_1') }}
      </p>
      <img src="~/assets/image/offRoad/005.png" alt="" class="img_05" />
    </div>
    <div class="text_2 content_text">
      <p>  {{ $t('text_1_2') }}</p>
      <img src="~/assets/image/offRoad/005.png" alt="" class="img_05" />
    </div>
    <div class="text_3 content_text">
      <p>  {{ $t('text_1_3') }}</p>
      <img src="~/assets/image/offRoad/005.png" alt="" class="img_05" />
    </div>

    <div class="img_07">
      <img src="~/assets/image/offRoad/007.png" alt="" />
    </div>

    <div class="title title_1">  {{ $t('title_2') }}</div>
    <div class="img_015">
        <img src="~/assets/image/offRoad/015.png" alt="" />"
    </div>
    <div class="img_08">
      <img src="~/assets/image/offRoad/008.png" alt="" />
      <img src="~/assets/image/offRoad/009.png" alt="" class="img_09" />
    </div>

    <div class="text_4 content_text">
      <p>  {{ $t('text_2_1') }}</p>
    </div>
    <div class="text_5 content_text">
      <p>  {{ $t('text_2_2') }}</p>
    </div>
    <div class="text_6 content_text">
      <p>  {{ $t('text_2_3') }}</p>
    </div>

    <div class="img_010">
      <img src="~/assets/image/offRoad/010.png" alt="" class="img_10_1"/>
      <img src="~/assets/image/offRoad/012.png" alt="" class="img_012" />
    </div>

    <div class="title title_2">{{ $t('title_3') }}</div>
    <div class="text_7 content_text">
      <p>  {{ $t('text_3_1') }}</p>
    </div>

    <div class="img_011">
      <img src="~/assets/image/offRoad/011.png" alt="" />
    </div>
    <div class="img_013">
      <div class="title title_3">{{ $t('title_4') }}</div>
      <div class="text_8 content_text">
        <p>  {{ $t('text_4_1') }}</p>
      </div>
      <img src="~/assets/image/offRoad/011.png" alt="" class="img_014" />
      <img src="~/assets/image/offRoad/013.jpg" alt="" class="img_013_1"/>

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

    }, 500)
  },

  methods: {

    init(){
        gsap.timeline({
        // 添加到整个时间线
        scrollTrigger: {
          trigger: '.center_box',
          pin: false, // 在执行时固定触发器元素
          start: "top 50%", // 触发位置为触发器的顶部进入视口
        //   end: '+=1500', // 在滚动 500 px后结束
          scrub: true, // 触发器1秒后跟上滚动条
          markers: false,

          onEnter: e => {
            console.log('enter')
            this.ani_1()
          }, // 滚动到进入
          onLeave: e => {
            console.log("leave 上到下离开哦")
            // e.disable()

          }, // 滚动到退出
          onEnterBack: () => {
            // console.log('enter back')

          }, // 滚动到进入位置
          onLeaveBack: () => console.log('leave back'), // 滚动到退出位置
          onUpdate: self => {

            console.log('progress:', self.progress.toFixed(3), 'direction:', self.direction, 'velocity', self.getVelocity())
            let p = self.progress.toFixed(2) * 10
            if(p>0.5){
                this.ani_2()
            }
            if(p>0.7){
                setTimeout(()=>{
                    this.ani_3()
                },100)

            }
            if(p>0.8){
                setTimeout(()=>{
                    this.ani_4()
                },500)
            }
            if(p>1){
                setTimeout(()=>{
                    this.ani_5()
                },1000)
            }
            if(p>2){
                this.ani_6()
            }
            if(p>2.5){
                this.ani_10()

                setTimeout(()=>{
                    this.ani_7()
                },600)
            }
            if(p>2.8){
                this.ani_8()
            }
            if(p>3){
                this.ani_9()
            }
            if(p>4.1){
                this.ani_11()
            }
            if(p>5){
                this.ani_12()
            }
            if(p>5.2){
                this.ani_13()
            }
            if(p>6){
                this.ani_14()
            }
            if(p>6.2){
                this.ani_15()
            }
            if(p>7.7){
                this.ani_16()
            }
            if(p>8.1){
                this.ani_17()
            }

          },
        },
      })
    },


    // 定义动画
    ani_1() {
      let t1 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })
      t1.to(
        '.img_01',
        {
          width: '100%',
          opacity: 1,

          delay: 0.2,
        },
        0
      )
      t1.to(
        '.img_02>img',
        {
          clipPath: "polygon(0 0, 100% 0, 100% 100%, 0 100%)",
          opacity: 1,
          delay: 0.2,
        },
        0.2
      )
      t1.to('.title_1_p', {
        duration: 1,
        text: {
          value: this.$t('title_1'),
          delimiter: '',
        },
        ease: 'ease',
        stagger: 0.01, // 每个字之间的间隔时间

      })

      t1.play()
    },
    ani_2() {
      let t2 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 0.6,
          ease: 'ease',
        },
      })

      t2.to(
        '.img_03',
        {
          height: '45.66vw',
          opacity: 1,

          delay: 0.2,
        },
        0
      )

      t2.to(
        '.img_04',
        {
          opacity: 1,

          delay: 0.2,
        },
        0.2
      )

      t2.to('.img_06', {
        duration: 1,
        right: 0,

      })

      t2.play()
    },
    ani_3(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".text_1>.img_05",{
          opacity:0.7,
          clipPath: 'circle(70.7% at 50% 50%)',
          ease:"expo.in",

        })
        t3.to(".text_1>p",{
            opacity:1,
            y:0,
            duration: 0.5,
        },"+=0.4")

        t3.play()
    },
    ani_4(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".text_2>.img_05",{
          opacity:0.8,
          clipPath: 'circle(70.7% at 50% 50%)',
          ease:"expo.in",

        })
        t3.to(".text_2>p",{
            opacity:1,
            y:0,
            duration: 0.5,
        },"+=0.4")

        t3.play()

    },
    ani_5(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".text_3>.img_05",{
          opacity:1,
          clipPath: 'circle(70.7% at 50% 50%)',
          ease:"expo.in",

        })
        t3.to(".text_3>p",{
            opacity:1,
            y:0,
            duration: 0.5,
        },"+=0.4")

        t3.play()

    },
    ani_6(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".img_07",{
            clipPath: "polygon(100% 0, 1% 0, 1% 100%, 100% 100%)",

            delay: 0.2,
        })
        t3.to(".title_1",{
            opacity: 1,
            y:0,

            delay: 0.2,

        })
        t3.play()
    },

    ani_7(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".text_4>p",{
            opacity:1,
            y:0,
        })
        t3.play()
    },
    ani_8(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".text_5>p",{
            opacity:1,
            y:0,
        })
        t3.play()
    },
    ani_9(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".text_6>p",{
            opacity:1,
            y:0,
        })
        t3.play()
    },

    ani_10() {
      let t3 = gsap.timeline({
        paused: true,
        defaults: {
          duration: 1,
          ease: 'ease',
        },
      })

      t3.to('.img_08', {
        clipPath: 'circle(70.7% at 50% 50%)',
        delay: 0.2,
      })

      t3.to(
        '.img_09',
        {
          left: 0,
          ease: 'ease',
          delay: .6,
        },
        0.6
      )

      t3.play()
    },
    ani_11(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".img_015",{
            clipPath: "polygon(0 0, 100% 0, 100% 100%, 0 100%)",

            delay: 0.2,
        })
        t3.to(".img_012",{
            right:0,
            top:"20%",

            delay: 0.2,
        })
        t3.to(".img_10_1",{
            clipPath: "circle(80% at 50% 50%)",


        })

        t3.play()
    },
    ani_12(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })

        t3.to(".title_2",{
            opacity: 1,
            y:0,

            delay: 0.2,

        })
        t3.play()
    },
    ani_13(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".text_7>p",{
            opacity:1,
            y:0,
        })
        t3.play()
    },
    ani_14(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".img_011",{
            clipPath: "polygon(0 0, 100% 0, 100% 100%, 0 100%)",
        })
        t3.to(".title_3",{
            opacity: 1,
            y:0,
            delay: 0.2,

        })
        t3.to(".text_8>p",{
            opacity:1,
            y:0,
        })
        t3.play()
    },
    ani_15(){
        // let t3 = gsap.timeline({
        //   paused: true,
        //   defaults: {
        //   duration: 0.6,
        //   ease: 'ease',
        //     },
        // })
        // t3.to(".text_8>p",{
        //     opacity:1,
        //     y:0,
        // })
        // t3.play()
    },
    ani_16(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".img_013_1",{
            clipPath: "polygon(100% 100%, 100% 1%, 0% 1%, 0% 100%)",
        })

        t3.play()
    },
    ani_17(){
        let t3 = gsap.timeline({
          paused: true,
          defaults: {
          duration: 0.6,
          ease: 'ease',
            },
        })
        t3.to(".img_014",{
            clipPath: "polygon(0 0, 100% 0, 100% 100%, 0 100%)",
        })

        t3.play()
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
      title_1:"قدرات تكامل السيارات",
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
$mr_left: 7vw;
.center_box {
  position: relative;
  height: 295vw;
  > div {
    position: absolute;

    img {
      max-width: none;
    }
  }
}
.img_01 {
  opacity: 0;
  overflow: hidden;
  img {
    width: 100vw;
    transform: translateY(-30%);
  }
}
.img_02 {
  width: 83vw;
  overflow: hidden;
  position: absolute;
  top: 0;
  left: 0;
  img {
    transform: translateY(-27%);
    width: 83vw;
    clip-path: polygon(0 0, 0 0, 0 100%, 0% 100%);
  }
  mix-blend-mode: darken;
  p {
    margin: 0;
    position: absolute;
    z-index: 1;
    left: $mr_left;
    top: 2vw;
    color: #fff;
    font-size: 3.5vw;
    font-weight: 900;
  }
}
.img_03 {
  top: 20vw;
  height: 0;
  overflow: hidden;
  transform: translateY(5vw);
  left: 0;
  img {
    height: 45.66vw;
    width: 6.61vw;
  }
}
.img_04 {
  right: 0;
  top: 15vw;
  width: 42.84vw;
  opacity: 0;
  img {
    width: 100%;

  }
  .img_06 {
    width: 35vw;
    position: absolute;
    right: -100%;
    top: 21vw;
  }
}
.content_text {
  position: absolute;
  left: $mr_left;
  font-size: 1.4vw;

  p{
    opacity: 0;
  transform: translateY(2vw);
  }
  img {
    position: absolute;
    opacity: 0;
    right: 0;
    top: 50%;
    clip-path :circle(0% at 50% 50%);
    transform: translate(100%, -50%) ;
  }
}

.text_1 {
  width: 40vw;
  top: 28vw;


}
.text_2 {
  width: 30vw;
  top: 41vw;


}
.text_3 {
  width: 36vw;
  top: 56vw;


}
.img_07 {
  top: 74vw;
    right: 0;
  width: 50.468vw;
  clip-path: polygon(100% 0, 99% 0, 100% 100%, 99% 100%);

  img {
    width: 50.468vw;
  }
}
.title {
  width: 100%;
  position: absolute;
  top: 81vw;
  font-size: 4.5vw;
  color: #000;
  font-weight: 900;
  padding-left: $mr_left;
  opacity: 0;
  transform: translateY(5vw);
  text-align: left;
}
.img_08 {
  top: 85vw;
  left: 0;
  width: 44.68vw;
  height: 67.55vw;
  -webkit-clip-path: circle(0% at 50% 50%);
  clip-path: circle(0% at 50% 50%);
  img {
    width: 100%;
  }
  .img_09 {
    position: absolute;
    top: 23vw;
    width: 50.8vw;
    left: -50.8vw;
  }
}
.img_015{
    position: absolute;
    top: 130vw;
    left: 0;
    width: 101vw;
    clip-path: polygon(0 0, 0 0, 0 100%, 0% 100%);
    img {
      width: 100%;
    }

}
.text_4 {
  max-width: 50vw;
  top: 95vw;
  left: 37vw;
}

.text_5 {
  max-width: 50vw;
  top: 107vw;
  left: 42vw;
}
.text_6 {
  max-width: 50vw;
  top: 119vw;
  left: 47vw;
}
.img_010 {
  top: 121vw;
  right: 0;

  img {
    width: 41.4vw;
  }
  .img_10_1{
    clip-path: circle(0% at 50% 50%);
  }
  .img_012 {
    width: 50.468vw;
    position: absolute;
    right: -50.468vw;
    top: 10%;
  }
}
.title_2 {
  top: 184vw;
}
.text_7 {
  left: $mr_left;
  top: 197vw;
  max-width: 60vw;
}
.img_011 {
  top: 212vw;
  left: 0;
  clip-path: polygon(0 0, 0 0, 0 100%, 0% 100%);
  img {
    width: 101vw;
  }
}
.img_013 {
  position: relative;
  top: 221vw;
  left: 0;
  width: 100%;
  height: 62vw;
  background: #000;
  img {
    margin-top: 10vw;
    width: 100%;
  }
  .img_013_1{
    clip-path: polygon(50% 50%, 50% 50%, 50% 50%, 50% 50%);
    position: absolute;
    width: 100vw;
    left: 0;
    height: 51.5625vw;

  }
  .title_3 {
    top: 0;
    left: 0;
    color: #fff;
  }
  .text_8 {
    left: 0;
    top: 10vw;
    width: 80vw;
    color: #fff;
    left: $mr_left;
    z-index: 2;
  }
  .img_014 {
    position: absolute;
    left: 0;
    bottom: -7vw;
    // mix-blend-mode: color;
    clip-path: polygon(0 0, 0 0, 0 100%, 0% 100%);
    transform: scaleX(-1);

  }
}
.lang___ar{

.img_02{
  img{
    transform: translate(-20%,-27%);
  }
  p{
    top: 5vw;
  }
}
}
.lang___es{
  .title_2{
    top: 180vw;
    width: 68%;
  }
.text_4{
  top: 98vw;
}
.text_5{
  top: 108vw;
}
.text_6{
  top: 122vw;
}
}
</style>