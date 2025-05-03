<!--
 * @Author: 不二
 * @Date: 2023-08-25 15:00:28
 * @LastEditTime: 2023-12-15 11:17:00
-->

<template>
  <div :class="['car_highlights', mobileClass]">
    <div class="car_highlights_max wow animate__slideInUp" v-if="highData.max">
      <mq-layout mq="lg+"> <img :src="highData.max.url" alt="" /></mq-layout>

      <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.max.url_m" alt="" /></mq-layout>

      <car-hover v-if="highData.max.text">
        <template v-slot:title>
          <p v-html="highData.max.text[locale].title"></p>
        </template>
        <template v-slot:content>
          {{ highData.max.text[locale].describe }}
        </template>
      </car-hover>
    </div>
    <div class="car_highlights_open_02">
      <div>
        <div class="car_highlights_max car_highlights_max_2 wow animate__slideInUp" v-if="highData.max_2">
          <mq-layout mq="lg+"> <img :src="highData.max_2.url" alt="" /></mq-layout>
          <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.max_2.url_m" alt="" /></mq-layout>
          <img src="~/assets/image/vehicleModel/bj60/click.png" alt="" class="click_icon">
          <div class="high_btn">
            <p>{{ engine[locale] }}</p>
            <img src="~/assets/image/vehicleModel/bj60/power_2.png" alt=""  @click="handleClickMore" />
          </div>
        </div>
      </div>
    </div>

    <div :class="['car_highlights_open']" :style="{ maxHeight: this.max_height }" ref="my_height">
      <div>
        <div class="car_highlights_max car_highlights_max_2 wow animate__slideInUp">
          <div class="max_high_01" ref="ani_1" id="max_high_01">
            <mq-layout mq="lg+">
              <img src="~/assets/image/vehicleModel/bj60/high_4_2.jpg" alt="" />

              <div class="max_high_01_box">
                <div class="maidian_01">
                  <div class="drop_bright drop_bright_1"></div>
                  <div class="drop_xian_01"></div>
                  <div class="drop_xian_02"></div>
                  <div class="drop_content">
                    <strong> {{ text_list_2[0][locale].title }} </strong><br />
                    {{ text_list_2[0][locale].describe }}
                  </div>
                </div>
                <div class="maidian_02">
                  <div class="drop_bright drop_bright_2"></div>
                  <div class="drop_xian_01"></div>
                  <div class="drop_xian_02"></div>
                  <div class="drop_content">
                    <strong> {{ text_list_2[2][locale].title }} </strong><br />
                    {{ text_list_2[2][locale].describe }}
                  </div>
                </div>

                <div class="maidian_03">
                  <div class="drop_bright drop_bright_3"></div>
                  <div class="drop_xian_01"></div>
                  <div class="drop_xian_02"></div>
                  <div class="drop_content">
                    <strong> {{ text_list_2[1][locale].title }} </strong><br />
                    {{ text_list_2[1][locale].describe }}
                  </div>
                </div>
                <div class="maidian_04">
                  <div class="drop_bright drop_bright_4"></div>
                  <div class="drop_xian_01"></div>
                  <div class="drop_xian_02"></div>
                  <div class="drop_content">
                    <strong> {{ text_list_2[3][locale].title }} </strong><br />
                    {{ text_list_2[3][locale].describe }}
                  </div>
                </div>
              </div>
            </mq-layout>
            <mq-layout :mq="['xs', 'sm', 'md']">


              <img src="~/assets/image/vehicleModel/bj60/high_4_2_m.jpg" alt="" v-if="locale === 'en'" />
              <img src="~/assets/image/vehicleModel/bj60/high_4_2_m_es.jpg" alt="" v-if="locale === 'es'" />
              <img src="~/assets/image/vehicleModel/bj60/high_4_2_m_ar.jpg" alt="" v-if="locale === 'ar'" />


            </mq-layout>
          </div>

          <div class="max_high_02" ref="ani_2">
            <mq-layout mq="lg+">
              <div class="max_high_02_title">{{ title[locale] }}</div>
              <img src="~/assets/image/vehicleModel/bj60/high_4_3.jpg" alt="" />
              <div class="max_high_02_box">
                <div class="high_yuan_01 high_yuan">
                  <div class="high_yuan_icon"></div>
                  <div class="high_content" v-html=" this.text_list[0][locale].title"></div>
                </div>
                <div class="high_yuan_02 high_yuan">
                  <div class="high_yuan_icon"></div>
                  <div class="high_content" v-html=" this.text_list[1][locale].title"></div>
                </div>
                <div class="high_yuan_03 high_yuan">
                  <div class="high_yuan_icon"></div>
                  <div class="high_content" v-html=" this.text_list[2][locale].title"></div>
                </div>

                <div class="high_yuan_05 high_yuan">
                  <div class="high_yuan_icon"></div>
                  <div class="high_content" v-html=" this.text_list[3][locale].title"></div>
                </div>
                <div class="high_yuan_06 high_yuan">
                  <div class="high_yuan_icon"></div>
                  <div class="high_content" v-html=" this.text_list[4][locale].title"></div>
                </div>
                <div class="high_yuan_07 high_yuan">
                  <div class="high_yuan_icon"></div>
                  <div class="high_content" v-html=" this.text_list[5][locale].title"></div>
                </div>
                <div class="high_yuan_08 high_yuan">
                  <div class="high_yuan_icon"></div>
                  <div class="high_content" v-html=" this.text_list[6][locale].title"></div>
                </div>
              </div>
            </mq-layout>
            <mq-layout :mq="['xs', 'sm', 'md']">
              <div class="max_high_02_title max_high_02_title_mobile">
                {{ title[locale] }}
              </div>

              <img src="~/assets/image/vehicleModel/bj60/high_4_3_m.jpg" alt="" />
            </mq-layout>
          </div>

          <!-- <mq-layout mq="lg+"> <img :src="highData.max_2.url" alt="" /></mq-layout>
          <mq-layout :mq="['xs', 'sm', 'md']"> <img :src="highData.max_2.url_m" alt="" /></mq-layout> -->
        </div>
      </div>
    </div>

    <!-- <car-more @handleClickMore="handleClickMore"></car-more> -->
  </div>
</template>

<script>
import { gsap } from 'gsap/dist/gsap'
import CarMore from '../CarMore.vue'
import CarHover from '../CarHover.vue'

export default {
  props: {
    highData: Object,
  },
  data() {
    return {
      play_2:false,
      play_1:false,
      isOpen: false,
      max_height: null,
      mobileClass: '',
      locale: this.$i18n.locale,
      engine:{
        en:"ENGINE",
        es:'MOTOR',
        ar:"اللغة الإنجليزية"
      },
      title:{
        en:"ADVANCED INTELLIGENT ASSISTED DRIVING",
        es:"CONDUCCIÓN ASISTIDA POR CONDUCCIÓN AVANZADA INTELIGENTE",
        ar:"القيادة الذكية المتقدمة بمساعدة السائق",
      },
      text_list: [
        {
          en: {
            title: ' door opening <br> warning',

          },
          es: {
            title: 'door opening <br> warning',

          },
          ar: {
            title: 'door opening <br> warning',

          },
        },
        {
          en: {
            title: 'active emergency <br>braking',

          },
          es: {
            title: 'active emergency <br>braking',

          },
          ar: {
            title: 'active emergency <br>braking',

          },
        },
        {
          en: {
            title: 'reverse lateral <br>warning',

          },
          es: {
            title: 'reverse lateral <br>warning',

          },
          ar: {
            title: 'reverse lateral <br>warning',

          },
        },
        {
          en: {
            title: 'Full Speed Domain Adaptive  Cruise Control',

          },
          es: {
            title: 'Full Speed Domain Adaptive  Cruise Control',

          },
          ar: {
            title: 'Full Speed Domain Adaptive  Cruise Control',

          },
        },
        {
          en: {
            title: ' traffic signs',

          },
          es: {
            title: 'traffic signs',

          },
          ar: {
            title: 'traffic signs',

          },
        },
        {
          en: {
            title: 'front collision <br>warning',

          },
          es: {
            title: 'front collision <br>warning',

          },
          ar: {
            title: 'front collision <br>warning',

          },
        },
        {
          en: {
            title: 'lane center hold',

          },
          es: {
            title: 'lane center hold',

          },
          ar: {
            title: 'lane center hold',

          },
        },
      ],
      text_list_2:[
        {
          en: {
            title: '360° PANORAMIC IMAGING:',
            describe:"Four million pixels high-definition cameras, using high-definition image stitching technology, visually view the information around the vehicle.",
          },
          es: {
            title: 'IMAGEN PANORÁMICA DE 360 °：',
            describe:"Cámara de alta definición de 4 millones de píxeles, que utiliza tecnología de empalme de imágenes de alta definición para ver intuitivamente la información alrededor del vehículo.",
          },
          ar: {
            title: 'تصوير بانورامي بزاوية 360 درجة:',
            describe:"تستخدم الكاميرات عالية الدقة ذات الأربعة ملايين بكسل، التي تستخدم تقنية تجميع الصور عالية الدقة، وعرض المعلومات حول السيارة بصريًا.",
          },
        },
        {
          en: {
            title: '180° TRANSPARENT CHASSIS:',
            describe:"Through the vehicle's system, it can accurately display the blind spot information below the chassis, reducing the blind spots in the chassis while driving.",
          },
          es: {
            title: 'CHASIS TRANSPARENTE DE 180 ° C:',
            describe:"A través del sistema del vehículo, puede mostrar con precisión la información de los puntos ciegos debajo del chasis, reduciendo los puntos ciegos en el chasis al conducir.",
          },
          ar: {
            title: 'هيكل شفاف بزاوية 180 درجة:',
            describe:"من خلال نظام السيارة، يمكن عرض معلومات النقطة العمياء أسفل الهيكل بدقة، مما يقلل من النقاط العمياء في الهيكل أثناء القيادة.",
          },
        },

        {
          en: {
            title: "GOD'S PERSPECTIVE：",
            describe:"Supports a god's eye view to oversee the entire surroundings, providing a more reassuring driving experience.",
          },
          es: {
            title: 'LA PERSPECTIVA DE DIOS:',
            describe:"Apoyar la perspectiva de Dios para observar todo el entorno y proporcionar una experiencia de conducción más tranquila.",
          },
          ar: {
            title: 'منظور الحراسة:',
            describe:"يدعم رؤية عين الحراسة لمراقبة المناطق المحيطة بالكامل، مما يوفر تجربة قيادة أكثر طمأنينة.",
          },
        },
        {
          en: {
            title: 'AUTOMATED PARKING:',
            describe:"A single click initiates automatic parking within the vehicle.",
          },
          es: {
            title: 'APARCAMIENTO AUTOMÁTICO:',
            describe:"Un clic para iniciar el estacionamiento automático en el vehículo.",
          },
          ar: {
            title: 'ركن السيارة تلقائيًا:',
            describe:"بنقرة واحدة فقط يتم بدء ركن السيارة تلقائيًا.",
          },
        }
      ]
    }
  },

  components: {
    CarMore,
    CarHover,
  },
  mounted() {
    window.addEventListener('scroll', this.ani_scroll_bjg)
  },
  methods: {
    handleClickMore() {
      this.isOpen = !this.isOpen
      if (this.isOpen) {
        console.log(this.$refs.ani_1)
        setTimeout(()=>{
               this.$refs.ani_1.scrollIntoView({
            behavior:"smooth",
            block:"start",
          })
        },50)
        this.max_height = this.$refs.my_height.scrollHeight + 'px'

      } else {
        this.max_height = '0px'
      }
    },
    ani_scroll_bjg() {
      if (!this.isOpen) return
      if (this.$refs.ani_1.getBoundingClientRect().top < window.innerHeight / 2) {
        if(!this.play_1){
          this.play_1 = true
          this.init()
        }

      }
      if (this.$refs.ani_2.getBoundingClientRect().top < window.innerHeight / 2) {
        if(!this.play_2){
          this.play_2=true
          this.init_2()
        }

        window.removeEventListener('scroll', this.ani_scroll_bjg)
      }
    },
    init() {
      let tl = gsap.timeline({
        paused: true,
        defaults: {
          duration:.3,
          ease: 'power2.inOut',
        },
      })

      tl.to(
        '.maidian_01 .drop_xian_02',
        {
          '--my-width': '11.4vw',
        }
      )
      tl.to(
        '.maidian_01 .drop_xian_01',
        {
          '--my-width': '21vw',
        }
      )
      tl.to('.maidian_01 .drop_bright', {
        opacity: 1,
      })

      tl.to('.maidian_01 .drop_content', {
        opacity: 1,
        y: '0.1vw',
      })







      // 2

      tl.to(
        '.maidian_02 .drop_xian_02',
        {
          '--my-width': '10vw',
        }
      )
      tl.to('.maidian_02 .drop_xian_01', {
        '--my-width': '17vw',
      })
      tl.to('.maidian_02 .drop_bright', {
        opacity: 1,
      })

      tl.to('.maidian_02 .drop_content', {
        opacity: 1,
        y: '0.1vw',
      })





      // 3
      tl.to(
        '.maidian_03 .drop_xian_02',
        {
          '--my-width': '10.8vw',
        }
      )
      tl.to('.maidian_03 .drop_xian_01', {
        '--my-width': '20vw',
      })
      tl.to('.maidian_03 .drop_bright', {
        opacity: 1,
      })
      tl.to('.maidian_03 .drop_content', {
        opacity: 1,
        y: '0.1vw',
      })







      // 4
      tl.to(
        '.maidian_04 .drop_xian_02',
        {
          '--my-width': '8vw',
        }
      )
      tl.to('.maidian_04 .drop_xian_01', {
        '--my-width': '26vw',
      })
      tl.to('.maidian_04 .drop_bright', {
        opacity: 1,
      })
      tl.to('.maidian_04 .drop_content', {
        opacity: 1,
        y: '0.1vw',
      })






      tl.play()
      console.log('init')
    },
    init_2() {
      let tl = gsap.timeline({
        paused: true,
        defaults: {
          duration:.3,
          ease: 'power2.inOut',
        },
      })
      tl.to('.max_high_02_box', {
        opacity: 1,
        y: '16%',
      })

      tl.to('.high_yuan_01', {
        opacity: 1,
      })
      tl.to('.high_yuan_02', {
        opacity: 1,
      })
      tl.to('.high_yuan_03', {
        opacity: 1,
      })

      tl.to('.high_yuan_05', {
        opacity: 1,
      })
      tl.to('.high_yuan_06', {
        opacity: 1,
      })
      tl.to('.high_yuan_07', {
        opacity: 1,
      })
      tl.to('.high_yuan_08', {
        opacity: 1,
      })

      tl.play()
    },
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
          // this.max_height = '941vw'
        } else {
          this.mobileClass = ''
          // this.max_height = '124.06vw'
        }
      },
    },
  },
}
</script>
<style  scoped >
img {
  width: 101%;
  max-width: none;
}
.lang___ar .max_high_01_box,
.lang___ar .max_high_02_box {
  direction: ltr;
  text-align: left;
}
.lang___ar .drop_content {
    direction: rtl;
  }

    .lang___ar .high_content{
    direction: rtl;
   }

   .lang___ar .maidian_04 .drop_content{
    transform: translateX(50%) !important;
   }

.max_high_02_title {
  position: absolute;
  left: 0;
  top: 3vw;
  width: 100%;
  font-size: 1.5vw;
  color: #fff;
  text-align: center;
  text-transform: uppercase;
}
.max_high_02_title_mobile {
  top: 8vw;
  font-size: 5vw;
  z-index: 9;
  width: 80vw;
  left: 50%;
    transform: translateX(-50%);
}
.max_high_01,
.max_high_02 {
  overflow: hidden;
  position: relative;
  width: 95vw;
  margin: 0 auto;
  margin-bottom: 1vw;
}

.max_high_01_box {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;

}
.max_high_01_box  .drop_content {
    opacity: 0;
    font-size: 0.8vw;
    font-weight: lighter;
  position: absolute;
  bottom: 0;
  }
  .max_high_01_box  .drop_content strong{
    font-size: 1.2vw;
  }
.max_high_01_box > div {
  position: absolute;
  display: inline-block;
  color: #fff;
  font-size: 0.8vw;
  font-weight: 900;
  text-align: right;
  width: 26vw;
}
.max_high_01_box > div .drop_xian_01,
.max_high_01_box > div .drop_xian_02 {
  position: absolute;
  transform-origin: 0 0;
  background-color: #fff;
  height: 2px;
  bottom: -1vw;
  border-radius: 1px;
}
.maidian_01 {
  top: 8.5vw;
  right: 68vw;
}

.maidian_01 .drop_xian_01 {
  --my-width: 0vw;
  width: var(--my-width);
  left: 31vw;
  transform: rotate(180deg);
}
.maidian_01 .drop_xian_02 {
  --my-width: 0vw;
  width: var(--my-width);
  transform: rotate(-145deg);

  left: 40.2vw;
  bottom: -7.5vw !important;
}
.maidian_02 {
  top: 34vw;
  left: 5vw;
}
.maidian_02 .drop_xian_01 {
  transform: rotate(180deg);
  --my-width: 0vw;
  width: var(--my-width);
  left: 32vw;
}
.maidian_02 .drop_xian_02 {
  left: 40.2vw;
  transform: rotate(145deg);
  width: 10vw;
  --my-width: 0vw;
  width: var(--my-width);
  bottom: 4.75vw !important;
}
.maidian_03 {
  right: 4vw;
  top: 15vw;
  text-align: left;
}
.maidian_03 .drop_xian_01 {
  transform-origin: right !important;

  --my-width: 0vw;
  width: var(--my-width);
  transform: rotate(180deg);
  right: 30.1vw;
}
.maidian_03 .drop_xian_02 {
  transform-origin: right !important;
  right: 37.7vw;
  transform: rotate(135deg);
   width: 10vw;
  --my-width: 0vw;
  width: var(--my-width);
  bottom: -8.65vw !important;
}
.maidian_04 {
  right: 5vw;
  top: 45vw;
  text-align: left;
}
.maidian_04 .drop_xian_01 {
  transform-origin: right !important;
  --my-width: 0vw;
  width: var(--my-width);
  right: 36vw;
  transform: rotate(180deg);
  top: 3.7vw !important;

}
.maidian_04 .drop_xian_02 {
    transform-origin: right !important;
    right: 41.6vw !important;
    transform: rotate(-135deg);
    --my-width: 0vw;
    width: var(--my-width);
    top: -1.9vw !important;
    bottom: none;
}
.maidian_04 .drop_content{
  bottom: -2.5vw;
}
.drop_bright {
  position: absolute;
  width: 1vw;
  height: 1vw;
  border-radius: 50%;
  background-color: #fff;
  transform: translate(-50%, 50%);
  bottom: -0.9vw;
  opacity: 0;
}
.drop_bright_1,
.drop_bright_2 {
  left: 10vw;
}
.drop_bright_2 {
  left: 15vw;
}
.drop_bright_3,
.drop_bright_4 {
  right: 10vw;
  transform: translate(50%, 50%);

}
.drop_bright_4{
  bottom: -3.8vw;
}

.max_high_02_box {
  position: absolute;
  width: 40vw;
  height: 40vw;
  border-radius: 50%;
  inset: 0;
  margin: auto;
  background-image: url('~/assets/image/vehicleModel/bj60/xian.png');
  background-repeat: no-repeat;
  background-size: 100%;
  transform: translateY(28%);
  opacity: 0;

}
.max_high_02_box .high_content{
  text-transform: uppercase;
}
.high_yuan {
  position: absolute;
  color: #fff;
  font-size: 1vw;
  opacity: 0;
}
.high_yuan > div {
  position: absolute;
  right: 5vw;
  text-align: right;
  top: 0;
  width: 18vw;
}

.high_yuan:nth-child(n + 5) {
  right: 0;
}
.high_yuan:nth-child(n + 5) > .high_content {
  left: 5vw;
  text-align: left;
}
.high_yuan .high_yuan_icon {
  position: absolute;
  width: 1.5vw;
  height: 1.5vw;
  border-radius: 50%;
  background-color: #fff;
  transform: translate(-50%, 50%);
}
.high_yuan_01,
.high_yuan_08 {
  bottom: 17vw;
}

.high_yuan_01 .high_yuan_icon {
  left: 0.5vw;
}

.high_yuan_02,
.high_yuan_07 {
  bottom: 27.5vw;
}
.high_yuan_02 .high_yuan_icon {
  left: 0.9vw;
}
.high_yuan_03,
.high_yuan_06 {
  bottom: 37vw;
}
.high_yuan_03 .high_yuan_icon {
  left: 7.1vw;
}

.high_yuan_05 {
  left: 50%;
  transform: translateX(-50%);
  bottom: 39.5vw;
}
.high_yuan_04 .high_yuan_icon {
  left: 11vw;
}

.high_yuan_01 > .high_content {
  right: 2vw;
}
.high_yuan_02 > .high_content {
  right: 2vw;
}
.high_yuan_03 > .high_content {
  right: 0.5vw;
}
.high_yuan_04 > .high_content {
  right: -5vw;
}
.high_yuan_05 > .high_content {
  left: 0;
  top: -4vw;
  transform: translateX(-50%);
  width: 80vw;
  text-align: center;
}
.high_yuan_06 > .high_content {
  left: -2vw !important;
}
.high_yuan_07 > .high_content {
  left: 2vw !important;
}
.high_yuan_08 > .high_content {
  left: 2vw !important;
}

.high_yuan_05 .high_yuan_icon {
  right: 0vw;
  top: 0vw;
  transform: translate(50%, -71%);
}

.high_yuan_06 .high_yuan_icon {
  right: 6vw;
}
.high_yuan_07 .high_yuan_icon {
  right: -0.5vw;
}
.high_yuan_08 .high_yuan_icon {
  right: -1vw;
}

.click_icon{
  position: absolute;
  width: 5vw;
  top: 15vw;
  inset: 0;
  margin: auto;
  margin-top: 16vw;
  animation: heartbeat 1.5s ease-in-out infinite both
}



/* 亮点部分 */
.car_highlights {
  position: relative;
}

.car_highlights_max {
  position: relative;
  overflow: hidden;
  margin-bottom: 1vw;
}

.car_highlights_text {
  position: absolute;
  bottom: 100px;
  left: 100px;
  color: #fff;
}

.car_highlights_text > p:nth-child(1) {
  font-size: 40px;
  line-height: 80px;
  font-weight: bolder;
}

.car_highlights_text > p:nth-child(2) {
  font-size: 24px;
  opacity: 0.6;
  line-height: 50px;
  font-weight: lighter;
}
.car_highlights_open_02 {
  display: flex;
  justify-content: space-between;

  transition: all 1s;
  overflow: hidden;
  flex-direction: column;
  width: 95%;
  margin: 0 auto;
}
.car_highlights_open {
  display: flex;
  justify-content: space-between;
  max-height: 0;
  transition: all 1s;
  overflow: hidden;
  flex-direction: column;
  width: 95%;
  margin: 0 auto;
}
.car_highlights_open > div {
  display: flex;
}
.car_highlights_open > div:last-child {
  flex-direction: column;
}

.highlights_left {
  position: relative;
  margin-bottom: 1vw;
}
.highlights_left > div {
  width: 100%;
  height: 100%;
}
.highlights_left img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.highlights_right {
  display: flex;
  flex-direction: column;
  margin-left: 1vw;
}

.highlights_t,
.highlights_b {
  display: flex;
  justify-content: space-between;
  box-sizing: border-box;
  margin-bottom: 1vw;
  /* padding-bottom: 2px; */
}

.highlights_t > div,
.highlights_b > div {
  position: relative;
}

.highlights_t > div:last-child,
.highlights_b > div:last-child {
  margin-left: 1vw;
}
.car_highlights_items {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  justify-content: space-between;
}
.car_highlights_item {
  width: calc(50% - 0.5vw);
  position: relative;
}
.car_highlights_max_2 {
  margin-top: 0 !important;
  position: relative;
}
.high_btn {
  width: 50vw;
  height: 15vw;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #fff;
  justify-content: space-between;
  letter-spacing: 2px;
  font-size: 5vw;
  cursor: pointer;
}
.high_btn > p {
  opacity: 0.6;
  transition: all 0.4s;
}
.high_btn > img {
  width: 10vw;
  opacity: 0.2;
}
.car_highlights_item img,
.car_highlights_max_2 img {
  transition: all 0.4s;
}

.car_highlights_max_2:hover > .high_btn img {
  filter: drop-shadow(0px 0px 5px #fff);
  opacity: 1;
  animation: heartbeat 1.5s ease-in-out infinite both
}
.car_highlights_max_2:hover .click_icon{
    opacity: 0;
}
.car_highlights_max_2:hover .high_btn > p {
  opacity: 1;
  text-shadow: 0 0 5px #fff;
}
.highlights_left img,
.highlights_t img,
.highlights_b img,
.car_highlights_max_2 img,
.car_highlights_item img {
  transition: all 0.4s;
}
.highlights_left:hover img,
.highlights_t_l:hover img,
.highlights_t_r:hover img,
.highlights_b_l:hover img,
.highlights_b_r:hover img,
.highlights_b:hover img,
.car_highlights_max_2:hover img,
.car_highlights_item:hover img {
  /* transform: scale(1.1); */
}

.highlights_left,
.highlights_t_l,
.highlights_t_r,
.highlights_b_l,
.highlights_b_r,
.car_highlights_max,
.highlights_b > div,
.car_highlights_item {
  overflow: hidden;
}
.car_highlights_max >>> .hover_box:before{
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.4s;
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.5)) !important;
}

.highlights_t .highlights_t_l > div,
.highlights_t .highlights_t_r > div {
  overflow: hidden;
}

/* 移动端 */
.mobile .car_highlights_open {
  flex-direction: column !important;
  width: 100%;
}
.mobile .car_highlights_items {
  flex-direction: column !important;
}
.mobile .car_highlights_open > div {
  flex-direction: column;
}
.mobile .highlights_b {
  flex-direction: column;
}

.mobile .highlights_t > div:last-child,
.mobile .highlights_b > div:last-child {
  margin-left: 0;
}
.mobile .highlights_right {
  margin-left: 0;
  margin-bottom: 0 !important ;
}

.mobile .car_highlights_item {
  width: 100%;
  max-width: 100%;
  margin-bottom: 2vw;
}
.mobile .highlights_left,
.mobile .highlights_right {
  width: 100%;
  margin-bottom: 2vw;
}
/* .mobile .car_highlights_max {
  margin-bottom: 2vw;
} */
.mobile .highlights_t {
  display: block;
  margin-bottom: 0;
}
.mobile .car_highlights_max {
  margin-top: 0;
}
.highlights_b > div:last-child {
  margin-bottom: 0;
}
.mobile .highlights_t_l,
.mobile .highlights_t_r {
  margin-bottom: 2vw;
}

.mobile .car_highlights_text {
  /* display: none; */
}
.mobile .car_highlights_text > p:nth-child(1) {
  /* display: none; */
  font-size: 3.5vw;
  line-height: 6vw;
  margin-bottom: 2vw;
}
.mobile .car_highlights_text > p:nth-child(2) {
  /* display: none; */
  font-size: 3vw;
  line-height: 5vw;
}
.mobile .car_more {
  bottom: 3.5vw;
}
.mobile .car_more_off {
  bottom: -4vw;
}
.mobile .car_highlights_item:hover img,
.mobile.car_highlights_max_2:hover img {
  transform: scale(1);
}
.mobile .highlights_left:hover img,
.mobile .highlights_t_l:hover img,
.mobile .highlights_t_r:hover img,
.mobile .highlights_b_l:hover img,
.mobile .highlights_b_r:hover img,
.mobile .highlights_b:hover img,
.mobile .car_highlights_max:hover img,
.mobile .car_highlights_item:hover img {
  transform: scale(1);
}
.lang___ar .highlights_right {
  margin-left: 0;
  margin-right: 2vw;
}

.lang___ar .highlights_t > div:last-child,
.lang___ar .highlights_b > div:last-child {
  margin-left: 0;
  margin-right: 2vw;
}

.car_highlights_max >>> .hover_box:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  transition: all 0.4s;
  background-image: linear-gradient(to left bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.2)) !important;
}
.car_highlights_max >>> .hover_box:hover::before {
  opacity: 1;
}
.car_highlights_max >>> .hover_box:hover {
  background-color: transparent !important;
}
.car_highlights_max >>> .hover_title {
  font-size: 2vw !important;
}
.car_highlights_max >>> .hover_title,
.car_highlights_max >>> .hover_content {
  padding-left: 2.5% !important;
  padding-right: 2.5% !important;
}

.heartbeat {
  -webkit-animation: heartbeat 1.5s ease-in-out infinite both;
  animation: heartbeat 1.5s ease-in-out infinite both;
}

/* ----------------------------------------------
 * Generated by Animista on 2023-11-1 18:16:54
 * Licensed under FreeBSD License.
 * See http://animista.net/license for more info.
 * w: http://animista.net, t: @cssanimista
 * ---------------------------------------------- */

/**
 * ----------------------------------------
 * animation heartbeat
 * ----------------------------------------
 */
@-webkit-keyframes heartbeat {
  from {
    -webkit-transform: scale(1);
    transform: scale(1);
    -webkit-transform-origin: center center;
    transform-origin: center center;
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
  10% {
    -webkit-transform: scale(0.91);
    transform: scale(0.91);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  17% {
    -webkit-transform: scale(0.98);
    transform: scale(0.98);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
  33% {
    -webkit-transform: scale(0.87);
    transform: scale(0.87);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  45% {
    -webkit-transform: scale(1);
    transform: scale(1);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
}
@keyframes heartbeat {
  from {
    -webkit-transform: scale(1);
    transform: scale(1);
    -webkit-transform-origin: center center;
    transform-origin: center center;
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
  10% {
    -webkit-transform: scale(0.91);
    transform: scale(0.91);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  17% {
    -webkit-transform: scale(0.98);
    transform: scale(0.98);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
  33% {
    -webkit-transform: scale(0.87);
    transform: scale(0.87);
    -webkit-animation-timing-function: ease-in;
    animation-timing-function: ease-in;
  }
  45% {
    -webkit-transform: scale(1);
    transform: scale(1);
    -webkit-animation-timing-function: ease-out;
    animation-timing-function: ease-out;
  }
}
</style>