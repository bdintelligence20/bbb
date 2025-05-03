<!--
 * @Author: 不二
 * @Date: 2023-08-31 19:23:20
 * @LastEditTime: 2023-09-20 23:11:53
-->


<template>
  <div @touchstart="videoControl" class="model-30-wrap">
    <!-- <div class="heig"></div> -->
    <div ref="scrollWrap" class="swiper_kv">
      <mq-layout :mq="['xs', 'sm', 'md']">
        <SlideCarBanner></SlideCarBanner>
      </mq-layout>
      <mq-layout mq="lg+">
        <HomePageImageBanner :image-list="imageList" />
      </mq-layout>
      <TestDrive></TestDrive>
    </div>
    <!-- <div class="min_height"></div> -->
<!--    <div class="video-box  wow animate__slideInUp">-->
<!--      <VehicleVideo ref="video" />-->
<!--    </div>-->
    <!-- <div class="min_height"></div> -->
    <!-- <CarTitle>
      <template v-slot:content> {{ title[locale].title_1 }}</template>
    </CarTitle> -->

      <SlideCarColor></SlideCarColor>


    <!-- <div class="min_height"></div> -->
    <!-- <CarTitle>
      <template v-slot:content> {{ title[locale].title_2 }} </template>
    </CarTitle> -->
    <mq-layout mq="lg+">
      <div class="heig">
        <div class="heig_box"> <SlideCar ref="swiper_img" @changeActiveIndex="changeActiveIndex" ></SlideCar></div>
      </div>
    </mq-layout>

    <mq-layout :mq="['xs', 'sm', 'md']">
      <SlideCarMobile></SlideCarMobile>
    </mq-layout>

    <!-- <CarTitle>
      <template v-slot:content> {{ title[locale].title_3 }} </template>
    </CarTitle> -->

    <!-- <div class="min_height"></div> -->
    <!-- 配置项 -->
    <parameter></parameter>

    <!-- 配置项 -->
    <HighLights :high-data="highData"></HighLights>

    <div class="min_height"></div>

    <HighLightsTwo :high-data="highDataPage2"></HighLightsTwo>
    <div class="min_height"></div>

    <HighLightsTwo :high-data="highDataPage4"></HighLightsTwo>
    <!-- <div class="min_height"></div> -->
    <HighLightsThree :high-data="highDataPage3"></HighLightsThree>
    <!-- <div class="min_height"></div>
    <HighLightsTwo :high-data="highDataPage5"></HighLightsTwo>   -->
    <div class="min_height"></div>
    <HighLightsTwoFour :high-data="highDataPage6" ref="HighLightsTwoFour"></HighLightsTwoFour>
    <div class="min_height"></div>
    <ConfigTable id="30"></ConfigTable>

    <BackTop></BackTop>
  </div>
</template>

<script>
import CarTitle from '~/components/vehicleModel/CarTitle.vue'
import Parameter from '~/components/vehicleModel/parameter.vue'
import HighLights from '~/components/vehicleModel/HighLights.vue'
import HighLightsTwo from '~/components/vehicleModel/HighLightsTwo.vue'
import HighLightsThree from '~/components/vehicleModel/HighLightsThree.vue'
import HighLightsTwoFour from '~/components/vehicleModel/HighLightsTwoFour.vue'
import SlideCar from '~/components/vehicleModel/SlideCar.vue'
import SlideCarMobile from '~/components/vehicleModel/SlideCarMobile.vue'
import SlideCarBanner from '~/components/vehicleModel/SlideCarBanner.vue'
import SlideCarColor from '~/components/vehicleModel/SlideCarColor.vue'
import BackTop from '~/components/vehicleModel/BackTop.vue'
import ConfigTable from '~/components/vehicleModel/ConfigTable.vue'
import VehicleVideo from '~/components/vehicleModel/VehicleVideo.vue'
import TestDrive from '~/components/vehicleModel/TestDrive.vue'
import { gsap } from 'gsap/dist/gsap'
import { ScrollTrigger } from 'gsap/dist/ScrollTrigger'

// import WOW from 'wow.js'
if (process.browser) {
  // 在这里根据环境引入wow.js
  var { WOW } = require('wow.js/dist/wow')
}

export default {
  name: 'VehicleNewX7',
  layout: 'main',
  components: { CarTitle, Parameter, HighLights, HighLightsTwo, HighLightsThree, SlideCar, SlideCarColor, BackTop, ConfigTable, SlideCarBanner, SlideCarMobile, HighLightsTwoFour, VehicleVideo,TestDrive },
  data() {
    return {
      active_index: 0,
      locale: this.$i18n.locale,
      title: {
        en: {
          title_1: 'MODEL EXTERIOR COLOR',
          title_2: 'MODEL OUTDOOR SHOT',
          title_3: 'MODEL HIGHLIGHTS',
        },
        es: {
          title_1: 'MODELO DE COLOR EXTERIOR',
          title_2: 'MODELO OUTDOOR SHOT',
          title_3: 'MODELO HIGHLIGHTS',
        },
        ar: {
          title_1: 'لون خارجي للموديل',
          title_2: 'نموذج التصوير الخارجي',
          title_3: 'مصابيح عالية الطراز',
        },
      },
      baseUrl: '/file',
      imageList: [
        {
          imgUrl: require('~/assets/image/vehicleModel/new_x7/kv1.jpg'),
          alt: '',
          linkUrl: '',
          aspectRatio: '56.25',
        },
        {
          imgUrl: require('~/assets/image/vehicleModel/new_x7/kv2.jpg'),
          alt: '',
          aspectRatio: '56.25',
          linkUrl: '',
        },
      ],

      highData: {
        max: null,
        left: {
          url: require('~/assets/image/vehicleModel/new_x7/high_1_1.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_1_1_m.jpg'),
          text: {
            en: {
              title: 'Rim Design',
              describe: 'The 19-inch wheel hub uses the image of a digital flying wing, with slender and delicate lines and a three dimensional shape of bright black forming a strong personality combination, highlighting technology and fashion.',
            },

            es: {
              title: 'Rim design',
              describe: 'El eje de rueda de 19 pulgadas utiliza la imagen de una ala voladora digital, con líneas delgadas y delicadas y una forma tridimensional de color negro brillante formando una fuerte combinación de personalidad, destacando la tecnología y la moda.',
            },
            ar: {
              title: 'تصميم الحافة',
              describe: 'يستخدم محور العجلة مقاس 19 بوصة صورة جناح طائر رقمي، بخطوط رفيعة ودقيقة وشكل ثلاثي الأبعاد باللون الأسود اللامع يشكل مزيجًا قويًا من الشخصية، ويسلط الضوء على التكنولوجيا والموضة.',
            },
          },
        },

        right: {
          right_t: {
            right_t_l: {
              url: require('~/assets/image/vehicleModel/new_x7/high_1_2.jpg'),
              url_m: require('~/assets/image/vehicleModel/new_x7/high_1_2_m.jpg'),
              text: {
                en: {
                  title: 'Capital Beauty Design Concept',
                  describe: 'The modeling adheres to the family language of Capital Beauty, refining its unique characteristic lines in the large-scale modeling.',
                },

                es: {
                  title: 'Capital Beauty Design Concept ',
                  describe: 'El modelado se adhial al lenguaje familiar de la belleza Capital, refinsus líneas características únicas en el modelado a gran escala.',
                },
                ar: {
                  title: 'مفهوم تصميم جمال العاصمة (Capital Beauty)',
                  describe: 'يلتزم التصميم باللغة العائلية لـجمال العاصمة، ويصقل خطوطها المميزة الفريدة في التصميم واسع النطاق.',
                },
              },
            },

            right_t_r: {
              url: require('~/assets/image/vehicleModel/new_x7/high_1_3.jpg'),
              url_m: require('~/assets/image/vehicleModel/new_x7/high_1_3_m.jpg'),

              text: {
                en: {
                  title: 'Kylin Wing Tail Light Design',
                  describe: 'The new tail light adheres to the overall design concept, the Digital Matrix is like a flying wing – Kylin Wing.',
                },

                es: {
                  title: 'Kylin Wing Tail Light Design',
                  describe: 'La nueva luz de cola se adhial al concepto de diseño general, la matriz Digital es como un ala vol- ala Kylin.',
                },
                ar: {
                  title: 'تصميم مصباح الذيل Kylin Wing',
                  describe: 'يلتزم المصباح الخلفي الجديد بمفهوم التصميم العام، حيث يشبه Digital Matrix جناحًا طائرًا - Kylin Wing.',
                },
              },
            },
          },
          right_b: {
            url: require('~/assets/image/vehicleModel/new_x7/high_1_4.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_1_4_m.jpg'),
            text: {
              en: {
                title: 'Bilateral Quadruple Exhaust Design',
                describe: 'Bilateral and quadruple exhaust design highlights dynamic and power of ALL NEW X7.',
              },
              es: {
                title: 'Diseño de escape Bilateral cuádruple',
                describe: 'La nueva luz de cola se adhial al concepto de diseño general, la matriz Digital es como un ala vol- ala Kylin.',
              },
              ar: {
                title: 'تصميم العادم الثنائي الرباعي',
                describe: 'يبرز تصميم العادم الثنائي والرباعي ديناميكية وقوة سيارة X7 الجديدة كليًا.',
              },
            },
          },
        },
      },

      highDataPage2: {
        max: {
          url: require('~/assets/image/vehicleModel/new_x7/high_kv_2.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_kv_2_m.jpg'),
          text: {
            en: {
              title: 'Interior',
              describe: 'The careful design and extreme space utilization of ALL NEW X7 ensure that every inch of the interior is optimized to offer the most functional and convenient storage solutions.',
            },

            es: {
              title: 'Interior',
              describe: 'El diseño cuidadoso y la utilización extrema del espacio de todo el nuevo X7 aseguran que cada pulgada del interior está optimipara ofrecer las soluciones de almacenaje más funcionales y convenientes. ',
            },
            ar: {
              title: ' التصميم الداخلي ',
              describe: 'يضمن التصميم الدقيق والاستغلال الأقصى للمساحة في سيارة X7 الجديدة كليًا تحسين كل بوصة من التصميم الداخلي لتقديم حلول التخزين الأكثر عملية وراحة.',
            },
          },
        },
        list: [
          {
            url: require('~/assets/image/vehicleModel/new_x7/high_2_1.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_2_1_m.jpg'),
            text: {
              en: {
                title: 'Technology Floating Speaker',
                describe: 'With the cool and futuristic design,deliver a gravity-defying musical experience.',
              },

              es: {
                title: 'Tecnología de suspensión de alta',
                describe: 'Con el diseño fresco y futurista, ofrecen una experiencia musical que desafla la gravedad.',
              },
              ar: {
                title: 'مكبر صوت عائم التقنية',
                describe: 'بفضل التصميم الرائع والمستقبلي، يمكنك الاستمتاع بتجربة موسيقية تتحدى الجاذبية.',
              },
            },
          },
          {
            url: require('~/assets/image/vehicleModel/new_x7/high_2_4.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_2_4_m.jpg'),
            text: {
              en: {
                title: 'Backlighting Garnish',
                describe: '64-color ambient lighting, and welcomingcabin features, the ALL NEW X7 provides anintegrated welcoming experience for the passengers.',
              },

              es: {
                title: 'Fabricación a partir de',
                describe: 'Iluminación ambiente de 64 colores y características de cabina de bienvenida, el nuevo X7 ofrece una experiencia de bienvenida integrada para los pasajeros.',
              },
              ar: {
                title: 'إضاءة خلفية مزخرفة',
                describe: 'إضاءة محيطية بـ 64 لونًا، وميزات مقصورة ترحيبية، توفر سيارة X7 الجديدة كليًا تجربة ترحيبية متكاملة للركاب.',
              },
            },
          },

          {
            url: require('~/assets/image/vehicleModel/new_x7/high_2_2.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_2_2_m.jpg'),
            text: {
              en: {
                title: 'Penetrated-type Air Outlet',
                describe: 'The air outlet design that runs through the entireconsole provides comprehensive air filtrationcapabilities, and the air conditioning system offers arange of comfortable options.',
              },

              es: {
                title: 'Salida de aire a través',
                describe: 'El diseño de salida de aire que recorre toda la planta proporciona capacidades de filtración de aire completas, y el sistema de aire acondicionado ofrece una serie de opciones cómodas.',
              },
              ar: {
                title: 'زينة الإضاءة الخلفية',
                describe: 'يوفر تصميم منفذ الهواء الذي يمر عبر وحدة التحكم بأكملها إمكانيات شاملة لتنقية الهواء، كما يقدم نظام تكييف الهواء مجموعة من الخيارات المريحة.',
              },
            },
          },

          {
            url: require('~/assets/image/vehicleModel/new_x7/high_2_6.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_2_6_m.jpg'),
            text: {
              en: {
                title: 'Ergonomic Seating <br>(Incl Premium Passenger Seat)',
                describe: 'The epitome of luxury, ALL NEW X7 boasts an enchanting rear seating experience. ',
              },

              es: {
                title: 'Asientos ergonómicos<br>(incluye copiloto queen)',
                describe: 'El epítome del lujo, la nueva X7 cuenta con una experiencia de asientos traseros encantadora.',
              },
              ar: {
                title: 'مقاعد مريحة<br>（بما في ذلك مساعد الطيار على شكل الملكة）',
                describe: 'تجسيدًا للفخامة، تتميز سيارة X7 الجديدة كليًا بتجربة جلوس ساحرة في المقعد الخلفي.',
              },
            },
          },
          {
            url: require('~/assets/image/vehicleModel/new_x7/high_2_5.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_2_5_m.jpg'),
            text: {
              en: {
                title: 'Black Textured Panel',
                describe: 'This distinctive design element creates a modern and bold aesthetic, elevating the overall feel of ALL NEW X7. ',
              },

              es: {
                title: 'Panel de textura negra',
                describe: 'Este elemento de diseño distintivo crea una estética moderna y audaz, elevando la sensación general de todo el nuevo X7.',
              },
              ar: {
                title:'لوحة سوداء منقوشة',
                describe: 'يخلق عنصر التصميم المميز هذا جمالية عصرية وجريئة، مما يرفع من الشعور العام لسيارة X7 الجديدة كليًا.',
              },
            },
          },
          {
            url: require('~/assets/image/vehicleModel/new_x7/high_2_3.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_2_3_m.jpg'),
            text: {
              en: {
                title: 'Luxurious Rear-row Experience',
                describe: 'Experience the difference as the ergonomic seating contours to your body, reducing muscle fatigue. ',
              },

              es: {
                title: 'Experiencia en la parte trasera de lujo',
                describe: 'Experimla la diferencia a medida que el asiento ergonse adapta al cuerpo, reduciendo la fatiga muscular.',
              },
              ar: {
                title: 'تجربة صف خلفي فاخرة',
                describe: 'اختبر الفرق مع المقاعد المريحة التي تتلاءم مع جسمك، مما يقلل من إجهاد العضلات.',
              },
            },
          },
        ],
      },
      highDataPage3: {
        max: {
          url: require('~/assets/image/vehicleModel/new_x7/high_kv_3.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_kv_3_m.jpg'),
          text: {
            en: {
              title: 'Intelligence',
              describe: "Embark on a journey into the future with our new car's intelligent driving functions. Enjoy the freedom that comes with intelligent driving, where every decision and maneuver is enhanced by cutting-edge technology.",
            },
            es: {
              title: 'inteligencia',
              describe: 'El diseño cuidadoso y la utilización extrema del espacio de todo el nuevo X7 aseguran que cada pulgada del interior está optimipara ofrecer las soluciones de almacenaje más funcionales y convenientes.',
            },
            ar: {
              title: 'الذكاء',
              describe: 'انطلق في رحلة إلى المستقبل مع وظائف القيادة الذكية في سيارتنا الجديدة. استمتع بالحرية التي تأتي مع القيادة الذكية، حيث يتم تعزيز كل قرار ومناورة من خلال التكنولوجيا المتطورة.',
            },
          },
        },

        max_2: {
          url: require('~/assets/image/vehicleModel/new_x7/high_3_6.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_3_6_m.jpg'),
          text: {
            en: {
              title: 'Hill Hold Control (HHC)',
              describe: 'It prevents the vehicle from slipping, thus improving driving safety when starting on an uphill slope.',
            },

            es: {
              title: 'Control de colina (HHC)',
              describe: 'Evita que el vehículo resbale, mejorando así la seguridad de conducción al arrancar en cuesta arriba.',
            },
            ar: {
              title: 'نظام التحكم في الثبات على التل (HHC)',
              describe: 'يمنع السيارة من الانزلاق، وبالتالي تحسين سلامة القيادة عند بدء التشغيل على منحدر صاعد.',
            },
          },
        },

        left: {
          url: require('~/assets/image/vehicleModel/new_x7/high_3_1.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_3_1_m.jpg'),
          text: {
            en: {
              title: 'Integrated Adaptive Cruiser Control (IACC)',
              describe: 'It intelligently adapts to the preset speed and automatically switches targets, taking the load off your feet and allowing for a more relaxed  driving experience.',
            },

            es: {
              title: 'Integrated Adaptive Cruiser Control (IACC)',
              describe: 'Se adapta de forma inteligente a la velocidad preestablecida y cambia de objetivo automáticamente, quitpeso de tus pies y permitiendo una experiencia de conducción más relaj.',
            },
            ar: {
              title: 'نظام تثبيت السرعة التكيفي المتكامل (IACC)',
              describe: 'يتكيف بذكاء مع السرعة المحددة مسبقًا ويغير الأهداف تلقائيًا، مما يخفف العبء عن قدميك ويسمح بتجربة قيادة أكثر استرخاءً.',
            },
          },
        },
        right: {
          right_t: {
            right_t_l: {
              url: require('~/assets/image/vehicleModel/new_x7/high_3_2.jpg'),
              url_m: require('~/assets/image/vehicleModel/new_x7/high_3_2_m.jpg'),
              text: {
                en: {
                  title: 'Lane Departure Warning (LDW)',
                  describe: 'It aims to help drivers minimize the risk of unintended lane departures on highways, expressways, and main roads by both visual and auditory alerts.',
                },

                es: {
                  title: 'Advertencia de salida de carril (LDW)',
                  describe: 'Su objetivo es ayudar a los conductores a minimizar el riesgo de salidas de carril no intencionadas en carreteras, autopistas y carreteras principales por alertas visuales y auditivas.',
                },
                ar: {
                  title: 'نظام تحذير مغادرة المسار (LDW)',
                  describe: 'يهدف إلى مساعدة السائقين على تقليل مخاطر مغادرة المسار غير المقصودة على الطرق السريعة والطرق الأوتوستراد والطرق الرئيسية من خلال التنبيهات البصرية والسمعية.',
                },
              },
            },
            right_t_r: {
              url: require('~/assets/image/vehicleModel/new_x7/high_3_3.jpg'),
              url_m: require('~/assets/image/vehicleModel/new_x7/high_3_3_m.jpg'),
              text: {
                en: {
                  title: 'Blind Spot Detection (BSD)',
                  describe: 'While driving, the blind spot detection system uses millimeter-wave radar to detect blind spots at the rear of the vehicle on both sides, reducing the risk of traffic accidents caused by blind spots.',
                },

                es: {
                  title: 'Detección de puntos ciegos (BSD)',
                  describe: 'Durante la conducción, el sistema de detección de puntos ciegos utiliza un radar de onda milipara detectar puntos ciegos en la parte trasera del vehículo en ambos lados. Reducción de los accidentes de tráfico por conducir puntos ciegos.',
                },
                ar: {
                  title: 'نظام اكتشاف النقاط العمياء (BSD)',
                  describe: 'أثناء القيادة، يستخدم نظام اكتشاف النقاط العمياء رادار الموجات المليمترية لاكتشاف النقاط العمياء في الجزء الخلفي من السيارة على كلا الجانبين، مما يقلل من خطر وقوع حوادث المرور الناجمة عن النقاط العمياء.',
                },
              },
            },
          },
          right_b: {
            right_b_l: {
              url: require('~/assets/image/vehicleModel/new_x7/high_3_4.jpg'),
              url_m: require('~/assets/image/vehicleModel/new_x7/high_3_4_m.jpg'),

              text: {
                en: {
                  title: 'Forward Collision Warning (FCW)',
                  describe: 'lt alerts the driver through auditory and visual warnings when the distance to the preceding vehicle becomes dangerously close, helping the driver maintain a safe distance from the vehicle ahead and prevent rear-end collisions.',
                },

                es: {
                  title: 'Advertencia de colihacia adelante (FCW)',
                  describe: 'El lt avisa al conductor mediante avisos auditivos y visuales cuando la distancia al vehículo anterior se acerca peligrosamente, lo que ayuda al conductor a mantener una distancia segura del vehículo que va delante y evitar colisiones por la parte trasera.',
                },
                ar: {
                  title: 'تحذير الاصطدام الأمامي (FCW)',
                  describe: 'ينبه السائق من خلال تحذيرات سمعية وبصرية عندما تصبح المسافة بينه وبين السيارة السابقة قريبة بشكل خطير، مما يساعد السائق على الحفاظ على مسافة آمنة من السيارة التي أمامه ومنع الاصطدامات الخلفية.',
                },
              },
            },
            right_b_r: {
              url: require('~/assets/image/vehicleModel/new_x7/high_3_5.jpg'),
              url_m: require('~/assets/image/vehicleModel/new_x7/high_3_5_m.jpg'),
              text: {
                en: {
                  title: 'Autonomous Emergency Braking (AEB)',
                  describe: 'It detects pedestrians or vehicles in front of the car and alarms if there is a risk of collision. Safety control, no more tailgating.  ',
                },

                es: {
                  title: 'Frenado autónomo de emergencia (AEB)',
                  describe: 'Detecta peato vehículos delante del coche y alarmas si hay riesgo de coli. Control de seguridad, no más puertas traseras.',
                },
                ar: {
                  title: 'نظام الكبح الطارئ المستقل (AEB)',
                  describe: 'يكتشف المشاة أو المركبات أمام السيارة وينبهك في حالة وجود خطر الاصطدام. التحكم في الأمان، لا مزيد من المطاردة عن قرب.',
                },
              },
            },
          },
        },
        list: [
          {
            url: require('~/assets/image/vehicleModel/new_x7/high_3_7.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_3_7_m.jpg'),
            text: {
              en: {
                title: 'High Beam Assist (HMA)',
                describe: 'Automatically turning on or off the high and low beams to avoid dazzling other drivers, promoting safer driving conditions.',
              },

              es: {
                title: 'Asistencia de alto haz (HMA)',
                describe: 'Automáticamente enciende o apaga las vigas altas y bajas para evitar desluma otros conductores, promoviendo condiciones de conducción más seguras.',
              },
              ar: {
                title: 'مساعد الضوء العالي (HMA)',
                describe: 'تشغيل أو إيقاف الضوء العالي والمنخفض تلقائيًا لتجنب إبهار السائقين الآخرين، وتعزيز ظروف القيادة الآمنة.',
              },
            },
          },
          {
            url: require('~/assets/image/vehicleModel/new_x7/high_3_8.jpg'),
            url_m: require('~/assets/image/vehicleModel/new_x7/high_3_8_m.jpg'),
            text: {
              en: {
                title: 'Hill Descent Control (HDC)',
                describe: 'It allows for a gradual descent to promote safety and comfort. The speed is automatically controlled to prevent accidents.',
              },

              es: {
                title: 'Control de descenso de colina (HDC)',
                describe: 'Descienda lentamente para promover la seguridad y el confort. Velocidad controlada automáticamente para evitar accidentes.',
              },
              ar: {
                title: 'نظام التحكم في نزول المنحدرات (HDC)',
                describe: 'يسمح بالنزول التدريجي لتعزيز السلامة والراحة. كما يتم التحكم في السرعة تلقائيًا لمنع الحوادث.',
              },
            },
          },
        ],
      },
      highDataPage4: {
        max: {
          url: require('~/assets/image/vehicleModel/new_x7/high_kv_4.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_kv_4_m.jpg'),
          text: {
            en: {
              title: 'Panoramic Sunroof',
              describe: 'Measuring the class-leading size of 1320 x 850mm, offers an immersive and cinematic visual experience.',
            },

            es: {
              title: 'Terraza panorámica',
              describe: 'La medición del tamaño líder de su clase de 1320 x 850mm ofrece una experiencia visual inmersiva y cinematográfica.',
            },
            ar: {
              title: 'سقف بانورامي',
              describe: 'يتميز بحجم 1320 × 850 مم، وهو الحجم الرائد في فئته، مما يوفر تجربة بصرية غامرة وسينمائية.',
            },
          },
        },
        list: [],
      },
      highDataPage5: {
        max: {
          url: require('~/assets/image/vehicleModel/new_x7/high_kv_5.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_kv_5_m.jpg'),
        },
        list: [],
      },
      highDataPage6: {
        max: {
          url: require('~/assets/image/vehicleModel/new_x7/high_kv_6.jpg'),
          url_m: require('~/assets/image/vehicleModel/new_x7/high_kv_6_m.jpg'),
          text: {
            en: {
              title: 'Powertrain',
              describe: 'The golden powertrain of ALL NEW X7 is the result of a groundbreaking collaboration between BAIC and Meta, combining cutting-edge technology with expert engineering to create the perfect balance between power and precision.',
            },

            es: {
              title: 'Tren de potencia',
              describe: 'El tren motriz dorado de todos los nuevos X7 es el resultado de una innovadora colaboración entre BAIC y Meta, combinando tecnología de vanguardia con ingeniería experta para crear el equilibrio perfecto entre potencia y precisión.',
            },
            ar: {
              title: 'مجموعة نقل الحركة',
              describe: 'مجموعة نقل الحركة الذهبية لسيارة X7 الجديدة كليًا هي نتيجة تعاون رائد بين BAIC وMeta، حيث تجمع بين التكنولوجيا المتطورة والهندسة المتخصصة لخلق التوازن المثالي بين القوة والدقة.',
            },
          },
        },
        list: [],
      },
    }
  },
  activated() {
    this.videoControl()
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
            console.log(e)
            console.log('enter  上到下 进入')
            try {
              this.$refs.swiper_img.openWheel()
            } catch (error) {

            }

          }, // 滚动到进入
          onLeave: e => {
            // console.log("leave 上到下离开哦")
            // e.disable()
            try {
              this.$refs.swiper_img.closeWheel()
            } catch (error) {

            }


          }, // 滚动到退出
          onEnterBack: () => {
            // console.log('enter back')
            try {
              this.$refs.swiper_img.closeWheel()
            } catch (error) {

            }

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
            } catch (error) {

            }


            }
          },
        },
      })
    },
  },
}
</script>
<style  scoped>
.heig_box {
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  /* overflow: hidden; */
  /* background-color: aqua; */
}

.heig{
  position: relative;
  z-index: 1;
  width: 100vw;
  height: 100vh;
}
.video-box {
  /* margin-top: 5px; */
  line-height: 0;
}
.min_height {
  margin-bottom: 1vw
}
.swiper_kv{
  position: relative;
  height: 100vh;
}
.model-30-wrap >>> p {
  margin: 0 !important;
}

.car_highlights_max .hover_content {
  width: 40%;
}
.pin-spacer{
  z-index: 999;
}
</style>
