<!--
 * @Author: 不二
 * @Date: 2023-08-31 19:23:20
 * @LastEditTime: 2024-01-04 14:08:15
-->


<template>
  <div @touchstart="videoControl" class="model-30-wrap">
    <div ref="scrollWrap" class="swiper_kv">
      <mq-layout :mq="['xs', 'sm', 'md']">
        <SlideCarBanner></SlideCarBanner>
      </mq-layout>
      <mq-layout mq="lg+">
        <!-- <HomePageImageBanner :image-list="imageList" /> -->
        <img src="~/assets/image/vehicleModel/bj60/kv1.jpg" alt="">
      </mq-layout>
      <TestDriveBtn></TestDriveBtn>
    </div>
    <!-- <div class="min_height"></div> -->

<!-- 视频 -->
       <div class="video-box  wow animate__slideInUp">
         <mq-layout mq="lg+">
           <video v-show="isPlay" src="/bj60/bj60.mp4" class="video" width="100%" data-type="mp4" controls x5-video-player-type="h5" loop webkit-playsinline playsinline :poster="require('~/assets/image/vehicleModel/bj60/cover.jpg')" ref="pcVideo" preload="auto"></video>
           <img v-show="!isPlay" src="~/assets/image/vehicleModel/bj60/cover.jpg" alt="" @click="playVideo">
           <div class="icon-wrap" v-show="!isPlay" @click="playVideo">
             <img src="~/assets/image/vehicleModel/bj60/播放.png" alt="">
           </div>
         </mq-layout>
         <mq-layout :mq="['xs', 'sm', 'md']">
           <VehicleVideo ref="video" />
         </mq-layout>
       </div>


    <!-- <div class="min_height"></div> -->
    <!-- <CarTitle>
        <template v-slot:content> {{ title[locale].title_1 }}</template>
      </CarTitle> -->

    <SlideCarColor></SlideCarColor>

    <!-- <mq-layout mq="lg+"> -->
    <!-- <div class="heig"> -->

    <Meitu ref="swiper_img"></Meitu>

    <!-- </div> -->
    <!-- </mq-layout> -->

    <!-- <mq-layout :mq="['xs', 'sm', 'md']">
      <SlideCarMobile></SlideCarMobile>
    </mq-layout> -->

    <!-- 配置项 -->
    <parameter></parameter>

    <!-- 配置项 -->
    <HighLights :high-data="highData"></HighLights>

    <div class="min_height"></div>
    <section class="high_2_box">
      <HighLightsOne :high-data="highDataPage2"></HighLightsOne>
    </section>

    <div class="min_height"></div>

    <section class="high_2_box">
      <HighLightsTwo :high-data="highDataPage3"></HighLightsTwo>
    </section>

    <div class="min_height"></div>
    <HighLightsThree :high-data="highDataPage4"></HighLightsThree>
    <div class="min_height"></div>

    <!-- 配置项 -->
    <section class="configuration">
      <Configuration  ></Configuration>
    </section>

    <!-- 预约试驾 -->
    <section>
      <CommonTestDrive></CommonTestDrive>
    </section>

    <BackTop></BackTop>
  </div>
</template>

  <script>
import Parameter from '~/components/vehicleModel/bj60/parameter.vue'
import HighLights from '~/components/vehicleModel/bj60/HighLights.vue'
import HighLightsOne from '~/components/vehicleModel/bj60/HighLightsOne.vue'
import HighLightsTwo from '~/components/vehicleModel/bj60/HighLightsTwo.vue'
import HighLightsThree from '~/components/vehicleModel/bj60/HighLightsThree.vue'
import Meitu from '~/components/vehicleModel/bj60/Meitu.vue'
import SlideCarMobile from '~/components/vehicleModel/SlideCarMobile.vue'
import SlideCarBanner from '~/components/vehicleModel/bj60/SlideCarBanner.vue'
import SlideCarColor from '~/components/vehicleModel/bj60/SlideCarColor.vue'
import BackTop from '~/components/vehicleModel/BackTop.vue'
import ConfigTable from '~/components/vehicleModel/common/CommonConfigTable.vue'
import VehicleVideo from '~/components/vehicleModel/bj60/VehicleVideo.vue'
import TestDriveBtn from '~/components/vehicleModel/bj60/TestDriveBtn.vue'
import { gsap } from 'gsap/dist/gsap'
import { ScrollTrigger } from 'gsap/dist/ScrollTrigger'
import Configuration from '~/components/vehicleModel/bj60/Configuration.vue'
import CommonTestDrive from '~/components/vehicleModel/common/CommonTestDrive.vue'

// import WOW from 'wow.js'
if (process.browser) {
  // 在这里根据环境引入wow.js
  var { WOW } = require('wow.js/dist/wow')
}

export default {
  name: 'VehicleNewX7',
  layout: 'main',
  components: { CommonTestDrive, Parameter, HighLights,HighLightsOne, HighLightsThree, HighLightsTwo, Meitu, SlideCarColor, BackTop, ConfigTable, SlideCarBanner, SlideCarMobile, VehicleVideo, TestDriveBtn, Configuration, Configuration },
  data() {
    return {

      active_index: 0,
      locale: this.$i18n.locale,

      baseUrl: '/file',
      imageList: [
        {
          imgUrl: require('~/assets/image/vehicleModel/bj60/kv1.jpg'),
          alt: '',
          linkUrl: '',
          aspectRatio: '56.25',
        },
      ],

      highData: {
        max: null,
        left: {
          url: require('~/assets/image/vehicleModel/bj60/high_1_1.jpg'),
          url_m: require('~/assets/image/vehicleModel/bj60/high_1_1_m.jpg'),
          text: {
            en: {
              title: 'Wheel Hub',
              describe: '18-inch, 20-inch oversized wheels.',
            },
            es: {
              title: 'Cubo de la rueda',
              describe: 'Ruedas de gran tamaño de 18 y 20 pulgadas.',
            },
            ar: {
              title: 'محور العجلة',
              describe: 'عجلات كبيرة الحجم مقاس 18 بوصة و20 بوصة.',
            },
          },
        },

        right: {
          right_t: {
            right_t_l: {
              url: require('~/assets/image/vehicleModel/bj60/high_1_2.jpg'),
              url_m: require('~/assets/image/vehicleModel/bj60/high_1_2_m.jpg'),
              text: {
                en: {
                  title: 'LED Taillights',
                  describe: 'When lit, an abstract lantern pattern appears on the inner upper and lower brake light cover.',
                },

                es: {
                  title: 'Luz trasera LED',
                  describe: 'Después de encenderlo, aparecerá un patrón abstracto de linterna en la pantalla de freno superior e inferior del Interior.',
                },
                ar: {
                  title: 'مصابيح خلفية LED',
                  describe: 'عند إضاءتها، يظهر نمط فانوس تجريدي على غطاء مصباح الفرامل الداخلي العلوي والسفلي.',
                },
              },
            },

            right_t_r: {
              url: require('~/assets/image/vehicleModel/bj60/high_1_3.jpg'),
              url_m: require('~/assets/image/vehicleModel/bj60/high_1_3_m.jpg'),

              text: {
                en: {
                  title: 'Brake Light',
                  describe: 'The inspiration comes from the Great Wall arrow buttress.',
                },

                es: {
                  title: 'Luz de freno',
                  describe: 'Inspirado en las flechas de la gran muralla.',
                },
                ar: {
                  title: 'أضواء الفرامل',
                  describe: 'الإلهام يأتي من دعامة السهم في سور الصين العظيم.',
                },
              },
            },
          },
          right_b: {
            url: require('~/assets/image/vehicleModel/bj60/high_1_4.jpg'),
            url_m: require('~/assets/image/vehicleModel/bj60/high_1_4_m.jpg'),
            text: {
              en: {
                title: 'Grille Light',
                describe: 'The "five-hole" grille lamp is the core embodiment of the the lighting design, inheriting the elements of the five-square hole of BAIC off-road vehicles families.',
              },
              es: {
                title: 'Lámpara de rejilla',
                describe: 'La lámpara de rejilla "cinco agujeros" es la encarnación central del diseño de iluminación, heredando el elemento de cinco agujeros cuadrados de la familia de vehículos todoterreno de baic.',
              },
              ar: {
                title: 'مصابيح الشبكة',
                describe: 'يعتبر مصباح الشبك "ذي الخمس فتحات" التجسيد الأساسي لتصميم الإضاءة، حيث ورث عناصر الفتحات ذات الخمس مربعات الموجودة في عائلات مركبات الطرق الوعرة من BAIC.',
              },
            },
          },
        },
      },

      highDataPage2: {
        max: {
          url: require('~/assets/image/vehicleModel/bj60/high_kv_1.jpg'),
          url_m: require('~/assets/image/vehicleModel/bj60/high_kv_1_m.jpg'),
          text: {
            en: {
              title: 'Interior',
              describe: 'The Infinity theatre-grade luxury sound system along with the luxurious and comfortable first-class seats, provides an experience of indulgent comfort.',
            },

            es: {
              title: 'Interior',
              describe: 'El lujoso sistema de sonido de la clase de teatro Infinity y los asientos de primera clase lujosos y cómodos ofrecen una experiencia indulgente y cómoda.',
            },
            ar: {
              title: 'المقصورة الداخلية',
              describe: 'يوفر نظام الصوت الفاخر من فئة Infinity إلى جانب المقاعد الفاخرة والمريحة من الدرجة الأولى تجربة من الراحة المطلقة.',
            },
          },
        },
        left: {
          url: require('~/assets/image/vehicleModel/bj60/high_2_3.jpg'),
          url_m: require('~/assets/image/vehicleModel/bj60/high_2_3_m.jpg'),

          text: {
            en: {
              title: 'Panoramic Sunroof',
              describe: 'The full range comes standard with a transparent panoramic skylight of 0.82m².',
            },

            es: {
              title: 'Tragaluz panorámico',
              describe: 'Gama completa estándar con tragaluz panorámico transparente de 0,82 metros cuadrados.',
            },
            ar: {
              title: 'فتحة سقف بانورامية',
              describe: 'تأتي المجموعة الكاملة قياسيًا مع فتحة سقف بانورامية شفافة تبلغ مساحتها 0.82 مترًا مربعًا.',
            },
          },

        },

        right: {
          right_t: {
            right_t_l: {
              url: require('~/assets/image/vehicleModel/bj60/high_2_1.jpg'),
              url_m: require('~/assets/image/vehicleModel/bj60/high_2_1_m.jpg'),

              text: {
              en: {
                title: 'Four-screen linkage',
                describe: 'Off-road features W-HUD head-up display, more suitable for off-road scenarios, imaging distance up to 2.2m.',
              },
              es: {
                title: 'Dispositivo de enlace de cuatro pantallas',
                describe: 'Función todoterreno W - HUD Head - up display, más adecuado para escenas todoterreno, con una distancia de imagen de hasta 2,2 metros.',
              },
              ar: {
                title: 'ربط أربع شاشات',
                describe: 'تتميز شاشة العرض الأمامية W-HUD بالقدرة على القيادة على الطرق الوعرة، وهي أكثر ملاءمة لسيناريوهات القيادة على الطرق الوعرة، مع مسافة تصوير تصل إلى 2.2 متر.',
              },
            },
            },
          },
          right_b: {
            url: require('~/assets/image/vehicleModel/bj60/high_2_2.jpg'),
            url_m: require('~/assets/image/vehicleModel/bj60/high_2_2_m.jpg'),
            text: {
                en: {
                  title: 'Large Space',
                  describe: '7/5 seats for diverse needs, with a third row of easy access (7 seats).',
                },

                es: {
                  title: 'Gran espacio',
                  describe: '7 / 5 asientos pueden satisfacer diferentes necesidades y la tercera fila es conveniente para entrar (7 asientos).',
                },
                ar: {
                  title: 'مساحة كبيرة',
                  describe: 'مقاعد 7/5 لتلبية الاحتياجات المتنوعة، مع صف ثالث من السهل الوصول إليه (7 مقاعد).',
                },
              },

          },
        },
      },
      highDataPage3: {
        max: {
          url: require('~/assets/image/vehicleModel/bj60/high_kv_2.jpg'),
          url_m: require('~/assets/image/vehicleModel/bj60/high_kv_2_m.jpg'),
          text: {
            en: {
              title: 'performance',
              describe: 'Based on the characteristics of different terrains, different modes can be selected through the all-terrain knob and transfer knob, making it easier to navigate through paved surfaces and complex road conditions.',
            },

            es: {
              title: 'Espectáculo',
              describe: 'De acuerdo con las características de los diferentes terrenos, se pueden seleccionar diferentes modos a través de la perilla de todo terreno y la perilla de conversión, lo que facilita su navegación en el pavimento pavimentado y las complejas condiciones de la carretera.',
            },
            ar: {
              title: 'الأداء',
              describe: 'استنادًا إلى خصائص التضاريس المختلفة، يمكن اختيار أوضاع مختلفة من خلال مقبض جميع التضاريس ومقبض النقل، مما يجعل التنقل عبر الأسطح المعبدة وظروف الطريق المعقدة أسهل.',
            },
          },
        },
        left: {
          url: require('~/assets/image/vehicleModel/bj60/high_3_3.jpg'),
          url_m: require('~/assets/image/vehicleModel/bj60/high_3_3_m.jpg'),
          text: {
            en: {
              title: 'Tires',
              describe: 'AT tread all-terrain tires(optional, 265/65R18-AT275/50 R20-HT).',
            },

            es: {
              title: 'Neumáticos',
              describe: 'Neumáticos todo terreno de banda de rodadura at (opcional, 265 / 65r18 - at275 / 50 R20 - ht).',
            },
            ar: {
              title: 'الإطارات',
              describe: 'إطارات AT لجميع أنواع التضاريس (اختياري، 265/65R18-AT275/50 R20-HT)',
            },
          },
        },

        right: {
          // ! 这里三条
          right_t: {
            right_t_l: {
              url: require('~/assets/image/vehicleModel/bj60/high_3_1.jpg'),
              url_m: require('~/assets/image/vehicleModel/bj60/high_3_1_m.jpg'),
              text: [
                {
                  en: {
                    title: 'Three Differential Locks',
                    describe: "Center differential lock &nbsp;&nbsp;&nbsp;Front differential lock\nRear differential lock",
                  },

                  es: {
                    title: 'TRES CERRADURAS  <br> DIFERENCIALES',
                    describe: 'Cuando las ruedas motrices de un lado se deslizan, Tres cerraduras pueden transmitir el par del motor a las ruedas motrices del otro lado para ayudar al vehículo a salir de problemas.',
                  },
                  ar: {
                    title: 'ثلاثة أقفال تفاضلية',
                    describe: 'قفل التفاضل المركزي&nbsp;&nbsp;&nbsp; قفل التفاضل الأمامي\nقفل التفاضل الخلفي',
                  },
                },
                {
                  en: {
                    title: 'Crawl Control System',
                    describe: 'Unerder 1-12km/h Lowspeed with Smart off-road modde',
                  },

                  es: {
                    title: 'SISTEMA DE CONTROL <br> DE AVANCE',
                    describe: 'La función modo de escalada está especialmente desarrollada para conducir en terrenos desafiantes, con un rango de velocidad controlable de 1 a 12 km / H.',
                  },
                  ar: {
                    title:'نظام التحكم في الزحف',
                    describe: 'السرعة المنخفضة بين 12-1 كم/ساعة مع نظام القيادة الذكي للطرق الوعرة',
                  },
                },
                {
                  en: {
                    title: 'Part Time 4WD',
                    describe: 'Smooth switching between 2H, 4H, and 4L.',
                  },
                  es: {
                    title: 'TRACCIÓN A LAS CUATRO<br>RUEDAS A TIEMPO PARCIAL',
                    describe: 'Cambia suavemente entre 2h, 4H y 4l.',
                  },
                  ar: {
                    title: 'نظام دفع رباعي  جزئي',
                    describe: 'التبديل السلس بين الدفع الثنائي والدفع الرباعي والدفع الرباعي لليسار',
                  },
                },
              ],
            },
          },
          right_b: {
            url: require('~/assets/image/vehicleModel/bj60/high_3_2.jpg'),
            url_m: require('~/assets/image/vehicleModel/bj60/high_3_2_m.jpg'),
            text: {
              en: {
                title: 'Global Intelligent Control System',
                describe: 'It has 9 driving modes and 17 driving combinations.',
              },
              es: {
                title: 'Sistema global de control inteligente',
                describe: 'Tiene 9 modos de conducción y 17 combinaciones de conducción.',
              },
              ar: {
                title: 'نظام التحكم الذكي العالمي',
                describe: 'يحتوي على 9 أوضاع قيادة و17 مجموعة قيادة.',
              },
            },
          },
        },
      },
      highDataPage4: {
        max:null,
        // max: {
        //   url: require('~/assets/image/vehicleModel/bj60/high_kv_3.jpg'),
        //   url_m: require('~/assets/image/vehicleModel/bj60/high_kv_3_m.jpg'),
        //   text: {
        //     en: {
        //       title: 'Panoramic Sunroof',
        //       describe: 'Measuring the class-leading size of 1320 x 850mm, offers an immersive and cinematic visual experience.',
        //     },

        //     es: {
        //       title: 'Terraza panorámica',
        //       describe: 'La medición del tamaño líder de su clase de 1320 x 850mm ofrece una experiencia visual inmersiva y cinematográfica.',
        //     },
        //     ar: {
        //       title: 'فتحة سقف بانورامية',
        //       describe: 'قياس الحجم الرائد من 1320 × 850mmoffers تجربة بصرية سينمائية غامرة',
        //     },
        //   },
        // },
        max_2: {
          url: require('~/assets/image/vehicleModel/bj60/high_4_1.jpg'),
          url_m: require('~/assets/image/vehicleModel/bj60/high_4_1.jpg'),
        },
      },
      isPlay: false
    }
  },
  activated() {
    this.videoControl()
    this.isPlay = false
    // window.addEventListener('scroll', this.scrollWithWindow)
  },
  mounted() {
    // console.log('mounted', gsap, ScrollTrigger)

    this.$nextTick(() => {
      if (process.browser) {
        // 在页面mounted生命周期里面 根据环境实例化WOW
        new WOW({ animateClass: 'animate__animated', offset: 0 }).init()
      }
      this.init()
    })
  },
  methods: {
    videoControl() {
      this.$refs.video?.videoControl()
      this.$refs.HighLightsTwoFour?.videoControl()
    },
    changeActiveIndex(e) {
      // console.log(e)
      this.active_index = e
    },
    init() {
      return

      let that = this
      gsap.registerPlugin(ScrollTrigger)

      gsap.timeline({
        // 添加到整个时间线
        scrollTrigger: {
          trigger: '.heig',
          pin: true, // 在执行时固定触发器元素
          start: 'top top', // 当触发器的顶部碰到视口的顶部时
          end: '+=1500', // 在滚动 500 px后结束
          scrub: true, // 触发器1秒后跟上滚动条
          // markers: true,

          onEnter: e => {
            try {
              this.$refs.swiper_img.openWheel()
            } catch (error) {}
          }, // 滚动到进入
          onLeave: e => {
            // console.log("leave 上到下离开哦")
            // e.disable()
            try {
              this.$refs.swiper_img.closeWheel()
            } catch (error) {}
          }, // 滚动到退出
          onEnterBack: () => {
            // console.log('enter back')
            try {
              this.$refs.swiper_img.closeWheel()
            } catch (error) {}
          }, // 滚动到进入位置
          onLeaveBack: () => console.log('leave back'), // 滚动到退出位置
          onUpdate: self => {
            // console.log(that.active_index)
            // console.log('progress:', self.progress.toFixed(3), 'direction:', self.direction, 'velocity', self.getVelocity())
            if (that.active_index == 3) {
              // self.disable()
              try {
                this.$refs.swiper_img.closeWheel()
                self.revert()
              } catch (error) {}
            }
          },
        },
      })
    },
    playVideo() {
      this.isPlay = true
      this.$nextTick(() => {
        console.log(this.$refs)
        this.$refs.pcVideo.play()
      })
    }
  },
}
</script>
  <style scoped>
  .model-30-wrap >>> .hover_mobile .title{
    text-transform: capitalize;
  }
   .lang___ar .model-30-wrap >>> .hover_mobile{
    padding: 2vw 1vw;
  }
.heig_box {
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  /* overflow: hidden; */
  /* background-color: aqua; */
}

.heig {
  position: relative;
  width: 100vw;
  height: 100vh;
}
.video-box {
  position: relative;
  /* margin-top: 5px; */
  line-height: 0;
}

.video-box .icon-wrap {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
}

.min_height {
  margin-bottom: 1vw;
}
.swiper_kv {
  position: relative;
  /* height: 100vh; */
}
.swiper_kv  img{
  width: 100vw;
  height: 100%;
  object-fit: cover;
}
.model-30-wrap >>> p {
  margin: 0 !important;
}

.car_highlights_max .hover_content {
  width: 40%;
}
.pin-spacer {
  z-index: 999;
}
.high_2_box >>> .car_highlights_open {
  flex-direction: row-reverse;
}
.high_2_box >>> .highlights_right {
  margin-left: 0;
  margin-right: 1vw;
}
.high_2_box >>> .highlights_t_l {
  margin-left: 0 !important;
  flex: 1;
}

.configuration {
  box-sizing: border-box;
  padding: 0 2vw 5vw 2vw;
  background-image: linear-gradient(to bottom, #fff, #eff0f1);
}
</style>
