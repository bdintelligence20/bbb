<template>
  <div class="g-container">
    <!--banner-->
    <div ref="scrollWrap">
      <HomePageImageBanner :image-list="imageList" class="home-banner" showButton />
    </div>

    <!--车型-->
    <div class="index-model-section">
      <div class="title-wrap">
        <h4 class="home-section-title">{{ $t('home.modelTitle') }}</h4>
      </div>
      <HomePageModels v-if="productMenu.length > 0" :product-menu="productMenu" @handleLoad="handleLoad" />
    </div>

    <!-- baic x  -->
    <mq-layout mq="xs">
      <div v-if="imageList && imageList.length > 0" class="about-list">
        <div class="about-item">
          <nuxt-link to="/news/baicInX">
            <img alt="BAIC IN X" :src="require(`../assets/image/home/wap/baic in x.jpg`)" />
            <div class="text-body">
              <div class="text">{{ $t('news.baicIn') }}</div>
            </div>
          </nuxt-link>
        </div>
      </div>
    </mq-layout>

    <!-- capital-->
    <div class="index-capital-section wow animate__slideInUp hover-section mt30">
      <nuxt-link to="./topic/2023-capital-beauty-redefined">
        <mq-layout :mq="['xl', 'lg', 'md', 'sm']">
          <el-image :src="require('../assets/image/home/capital.jpg')" alt="capital" class="capital-img hover-img" lazy />
        </mq-layout>
        <mq-layout mq="xs">
          <img :src="require('~/assets/image/home/wap/capital.jpg')" alt="capital" class="hover-img" lazy />
        </mq-layout>
        <div class="text__wrapper">
          <div class="text__body">
            <h3 class="text-title">
              {{ $t('home.capital') }}
              <br />
              {{ $t('home.REDEFINED') }}
            </h3>
            <div class="text-desc">
              {{ $t('home.capitalDes') }}
              <br />
              {{ $t('home.capitalDes2') }}
            </div>
          </div>
        </div>
      </nuxt-link>
    </div>

    <div class="main-section mt30">
      <!-- 关于-->
      <div class="index-about-section wow animate__slideInUp">
        <mq-layout :mq="['xl', 'lg', 'md', 'sm']">
          <el-row :gutter="30" type="flex">
            <el-col>
              <el-row v-if="activity && activity.length > 0" class="mt20">
                <nuxt-link :to="localePath(activity[0].linkUrl)" class="about-item hover-section">
                  <BannerVue :image="{ url: activity[0].imageUrl, aspectRatio: parseFloat(activity[0].aspectRatio) + '%', alt: '' }"></BannerVue>
                  <div class="text__wrapper">
                    <div class="text__body">
                      <h3 class="text-title">{{ activity[0].title }}</h3>
                    </div>
                  </div>
                </nuxt-link>
              </el-row>

              <!-- baic x  -->
              <el-row v-if="research && research.length > 0" class="mt20">
                <nuxt-link :to="localePath(research[0].linkUrl)" class="about-item hover-section">
                  <BannerVue :image="{ url: research[0].imageUrl, aspectRatio: parseFloat(research[0].aspectRatio) + '%', alt: '' }"></BannerVue>
                  <div class="text__wrapper">
                    <div class="text__body">
                      <h3 class="text-title">{{ research[0].title }}</h3>
                    </div>
                  </div>
                </nuxt-link>
              </el-row>

              <el-row v-if="more && more.length > 0" class="mt20">
                <div class="more-item hover-section">
                  <nuxt-link :to="localePath(more[0].linkUrl)">
                    <BannerVue :image="{ url: more[0].imageUrl, aspectRatio: parseFloat(more[0].aspectRatio) + '%', alt: '' }"></BannerVue>
                    <div class="text__wrapper learn-more-text-wrapper">
                      <div class="text__body">
                        <h3 class="text-title">{{ more[0].title.replace(/\\n/g, '\n') }}</h3>
                        <nuxt-link :to="localePath(more[0].linkUrl, $i18n.locale)">
                          <el-button class="border-el-button learn-more-btn">{{ $t('btn.viewMore').toLocaleUpperCase() }}</el-button>
                        </nuxt-link>
                      </div>
                    </div>
                  </nuxt-link>
                </div>
              </el-row>
            </el-col>
            <el-col>
              <el-row v-if="vehicle && vehicle.length > 0" class="mt20">
                <nuxt-link :to="localePath(vehicle[0].linkUrl)" class="about-item hover-section">
                  <BannerVue :image="{ url: vehicle[0].imageUrl, aspectRatio: parseFloat(vehicle[0].aspectRatio) + '%', alt: '' }"></BannerVue>
                  <div class="text__wrapper">
                    <div class="text__body">
                      <h3 class="text-title">{{ vehicle[0].title }}</h3>
                    </div>
                  </div>
                </nuxt-link>
              </el-row>
              <el-row v-if="about && about.length > 0" class="mt20">
                <nuxt-link :to="localePath(about[0].linkUrl)" class="about-item hover-section">
                  <BannerVue :image="{ url: about[0].imageUrl, aspectRatio: parseFloat(about[0].aspectRatio) + '%', alt: '' }"></BannerVue>
                  <div class="text__wrapper">
                    <div class="text__body">
                      <h3 class="text-title">{{ about[0].title }}</h3>
                    </div>
                  </div>
                </nuxt-link>
              </el-row>
            </el-col>
          </el-row>
        </mq-layout>
        <mq-layout mq="xs">
          <div class="about-list">
            <div v-for="(item, index) in aboutWapList" :key="index" class="about-item">
              <nuxt-link :to="item.linkUrl">
                <img :alt="item.describe" :src="require(`../assets/image/home/wap/about${index + 1}.jpg`)" />
                <div class="text-body">
                  <div class="text">{{ item.title }}</div>
                </div>
              </nuxt-link>
            </div>
            <div v-if="more && more.length > 0" class="view-btn">
              <nuxt-link :to="localePath(more[0].linkUrl, $i18n.locale)">
                <el-button class="border-el-button learn-more-btn">{{ $t('btn.viewMore').toLocaleUpperCase() }}</el-button>
              </nuxt-link>
            </div>
          </div>
        </mq-layout>
      </div>

      <!--          地图-->
      <div class="index-map-section wow animate__slideInUp">
        <HomePageMap v-if="mapList && mapList.length > 0" :area-list="areaList" :map-list="mapList" />
      </div>
    </div>

    <!--    新闻-->
    <div class="index-news-section">
      <h4 class="home-section-title wow animate__slideInUp">{{ $t('home.news') }}</h4>
      <HomePageNews v-if="newsList && newsList.length > 0" class="news" :news-list="newsList" />
    </div>
  </div>
</template>

<script>
import BannerVue from '~/components/img/banner.vue'
if (process.browser) {
  // 在这里根据环境引入wow.js
  var { WOW } = require('wow.js/dist/wow')
}

export default {
  name: 'IndexPage',
  components: { BannerVue },
  layout: 'main',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      imageList: [],
      btnPosition: [
        { name: 'BJ30', linkUrl: '/models/43', text: require('~/assets/image/home/bj30-text.png'), vrUrl: { en: 'https://www.baicglobal.com/vrbj30/', es: 'https://www.baicglobal.com/vrbj30/?lang=es', ar: 'https://www.baicglobal.com/vrbj30/?lang=ar' } },
        { name: 'BJ60', linkUrl: '/models/31', text: require('~/assets/image/vehicleModel/bj60/text.png'), vrUrl: { en: 'https://www.baicglobal.com/vrbj60/', es: 'https://www.baicglobal.com/vrbj60/?lang=es', ar: 'https://www.baicglobal.com/vrbj60/?lang=ar' } },
        { name: 'X7', linkUrl: '/models/30', text: require('~/assets/image/home/x7-text.png'), vrUrl: { en: 'https://www.baicglobal.com/vrx7/', es: 'https://www.baicglobal.com/vrx7/?lang=es', ar: 'https://www.baicglobal.com/vrx7/?lang=ar' } },
        { name: 'X55II', linkUrl: '/models/26', text: require('~/assets/image/home/x55ii-text.png'), vrUrl: { en: 'https://www.baicglobal.com/x55/?car=X6&lang=en', es: 'https://www.baicglobal.com/x55/?car=X6&lang=es', ar: 'https://www.baicglobal.com/x55/?car=X6&lang=ar' } },
      ],
      more: [],
      mapList: [],
      areaList: [],
      section: [],
      research: [],
      vehicle: [],
      activity: [],
      about: [],
      aboutWapList: [],
      productMenu: [],
      carImagesList: [
        {
          id: 43,
          name: 'BJ30',
          colors: [
            {
              rgbCode: '#96B8A6',
              path: 'color0', // 对应360旋转图所在的文件夹
            },
            {
              rgbCode: '#225F78',
              path: 'color1',
            },
            {
              rgbCode: '#808080',
              path: 'color2',
            },
            {
              rgbCode: '#808080',
              path: 'color3',
            },
            {
              rgbCode: '#A9BACC',
              path: 'color4',
            },
            {
              rgbCode: '#000000',
              path: 'color5',
            },
            {
              rgbCode: '#FFFFFF',
              path: 'color6',
            },
          ],
        },
        {
          id: 31,
          name: 'BJ60',
          colors: [
            {
              rgbCode: '#8195A6',
              path: 'color0',
            },
            {
              rgbCode: '#FFFFFF',
              path: 'color1',
            },
            {
              rgbCode: '#414E5B',
              path: 'color2',
            },
            {
              rgbCode: '#000000',
              path: 'color3',
            },
            {
              rgbCode: '#365163',
              path: 'color4',
            },
            {
              rgbCode: '#566656',
              path: 'color5',
            },
          ],
        },
        {
          id: 20,
          name: 'BJ40 PLUS',
          carWidth: '89%',
          position: {
            top: '57%',
            left: '48%',
          },
          colors: [
            {
              rgbCode: '#BC1D04',
              path: 'color0',
            },
            {
              rgbCode: '#404544',
              imgUrl: require('~/assets/image/home/model/colors/bj40 plus-灰.webp'),
            },
            {
              rgbCode: '#F5F5F5',
              imgUrl: require('~/assets/image/home/model/colors/bj40 plus-白.webp'),
            },
            {
              rgbCode: '#080808',
              path: 'color3',
            },
            {
              rgbCode: '#BACBE2',
              path: 'color4',
            },
            {
              rgbCode: '#193346',
              path: 'color5',
            },
            {
              rgbCode: '#313D2A',
              path: 'color6',
            },
            {
              rgbCode: '#5D6043',
              imgUrl: require('~/assets/image/home/model/colors/bj40 plus-绿.webp'),
            },
          ],
        },
        {
          id: 24,
          wheelLeft: require('~/assets/image/home/wheel/BJ40-SE-01.webp'),
          wheelRight: require('~/assets/image/home/wheel/BJ40-SE-02.webp'),
          width: '14.352%',
          bottom: '5.333%',
          left: '16.706%',
          right: '23.941%',
          colors: [
            {
              rgbCode: '#C40F0F',
              imgUrl: require('~/assets/image/home/model/colors/bj40-se-红.webp'),
            },
            {
              rgbCode: '#030303',
              imgUrl: require('~/assets/image/home/model/colors/bj40-se-黑.webp'),
            },
            {
              rgbCode: '#F8F8F8',
              imgUrl: require('~/assets/image/home/model/colors/bj40-se-白.webp'),
            },
            {
              rgbCode: '#404E45',
              imgUrl: require('~/assets/image/home/model/colors/bj40-se-绿.webp'),
            },
            {
              rgbCode: '#B9C9E2',
              imgUrl: require('~/assets/image/home/model/colors/bj40-se-蓝.webp'),
            },
            {
              rgbCode: '#193346',
              imgUrl: require('~/assets/image/home/model/colors/bj40-se-拉普兰.webp'),
            },
          ],
        },
        {
          id: 14,
          wheelLeft: require('~/assets/image/home/wheel/BJ80-01.webp'),
          wheelRight: require('~/assets/image/home/wheel/BJ80-02.webp'),
          width: '12.941%',
          bottom: '5.066%',
          left: '18.647%',
          right: '23.235%',
          colors: [
            {
              rgbCode: '#060606',
              imgUrl: require('~/assets/image/home/model/colors/bj80-黑.webp'),
            },
            {
              rgbCode: '#FBFAFA',
              imgUrl: require('~/assets/image/home/model/colors/bj80-白.webp'),
            },
            {
              rgbCode: '#6C6C6C',
              imgUrl: require('~/assets/image/home/model/colors/bj80-灰.webp'),
            },
            {
              rgbCode: '#A11616/#000000',
              imgUrl: require('~/assets/image/home/model/colors/bj80-红.webp'),
            },
            {
              rgbCode: '#14475F/#FBFAFA',
              imgUrl: require('~/assets/image/home/model/colors/bj80-蓝.webp'),
            },
          ],
        },
        {
          id: 25,
          wheelLeft: require('~/assets/image/home/wheel/F40-01.webp'),
          wheelRight: require('~/assets/image/home/wheel/F40-02.webp'),
          width: '15.294%',
          bottom: '7.2%',
          left: '17.411%',
          right: '23.176%',
          colors: [
            {
              rgbCode: '#BF0808',
              imgUrl: require('~/assets/image/home/model/colors/F40-红.webp'),
            },
            {
              rgbCode: '#F8F8F8',
              imgUrl: require('~/assets/image/home/model/colors/F40-白.webp'),
            },
            {
              rgbCode: '#BACBE2',
              imgUrl: require('~/assets/image/home/model/colors/F40-蓝.webp'),
            },
            {
              rgbCode: '#101011',
              imgUrl: require('~/assets/image/home/model/colors/F40-黑.webp'),
            },
            {
              rgbCode: '#193346',
              imgUrl: require('~/assets/image/home/model/colors/F40-拉普兰.webp'),
            },
            {
              rgbCode: '#55674A',
              imgUrl: require('~/assets/image/home/model/colors/F40-绿.webp'),
            },
          ],
        },
        {
          id: 30,
          name: 'ALL NEW X7',
          colors: [
            {
              rgbCode: '#6A6A6A',
              path: 'color0',
            },
            {
              rgbCode: '#0E0E0F',
              path: 'color1',
            },
            {
              rgbCode: '#F6F3F3',
              path: 'color2',
            },
            {
              rgbCode: '#9AA2A8',
              path: 'color3',
            },
            {
              rgbCode: '#070707/#FFFFFF',
              path: 'color4',
            },
            {
              rgbCode: '#010101/#939191',
              path: 'color5',
            },
            {
              rgbCode: '#000000/#E0DEDE',
              path: 'color6',
            },
          ],
        },
        {
          id: 21,
          name: 'X35',
          colors: [
            {
              rgbCode: '#C91D1D',
              path: 'color0',
            },
            {
              rgbCode: '#FEF9F9',
              path: 'color1',
            },
            {
              rgbCode: '#1C61D0',
              path: 'color2',
            },
            {
              rgbCode: '#480808',
              path: 'color3',
            },
          ],
        },
        {
          id: 26,
          name: 'X55 II',
          colors: [
            {
              rgbCode: '#F6F30A',
              path: 'color0',
            },
            {
              rgbCode: '#D21313',
              path: 'color1',
            },
            {
              rgbCode: '#29B7A4',
              path: 'color2',
            },
            {
              rgbCode: '#424344',
              path: 'color3',
            },
            {
              rgbCode: '#B1B3B4',
              path: 'color4',
            },
            {
              rgbCode: '#F2F5F7',
              path: 'color5',
            },
          ],
        },
        {
          id: 17,
          name: 'U5 PLUS',
          colors: [
            {
              rgbCode: '#CBCBCB',
              path: 'color0',
            },
            {
              rgbCode: '#F0F2F5',
              path: 'color1',
            },
            {
              rgbCode: '#060606',
              path: 'color2',
            },
            {
              rgbCode: '#BB1A1A',
              path: 'color3',
            },
          ],
        },
        {
          id: 8,
          position: {
            top: '51%',
            left: '51%',
          },
          name: 'EU5',
          colors: [
            {
              rgbCode: '#DC1717',
              path: 'color0',
            },
            {
              rgbCode: '#F9F5F5',
              path: 'color1',
            },
            {
              rgbCode: '#060606',
              path: 'color2',
            },
            {
              rgbCode: '#5F6163',
              imgUrl: require('~/assets/image/home/model/colors/eu5-灰.webp'),
            },
            {
              rgbCode: '#1E41A9',
              imgUrl: require('~/assets/image/home/model/colors/eu5-蓝.webp'),
            },
            {
              rgbCode: '#D0D6DD',
              path: 'color5',
            },
          ],
        },
        {
          id: 41,
          wheelLeft: require('~/assets/image/home/wheel/eu5-plus-01.webp'),
          wheelRight: require('~/assets/image/home/wheel/eu5-plus-02.webp'),
          width: '13.117%',
          bottom: '8%',
          left: '17.529%',
          right: '18.647%',
          colors: [
            {
              rgbCode: '#CBCBCB',
              imgUrl: require('~/assets/image/home/model/colors/eu5 plus-灰.webp'),
            },
            {
              rgbCode: '#F0F2F5',
              imgUrl: require('~/assets/image/home/model/colors/eu5 plus-白.webp'),
            },
          ],
        },
      ],
      newsList: [],
    }
  },
  created() {},
  mounted() {
    this.getBanner()
    this.getDetail()
    this.getMapList()
    this.getAreaList()
    this.getNewsList()
    this.wowFun()
    this.generateImages()
    this.getProductMenu()
  },
  beforeDestroy() {},
  activated() {},
  deactivated() {},
  methods: {
    wowFun() {
      this.$nextTick(() => {
        if (process.browser) {
          // 在页面mounted生命周期里面 根据环境实例化WOW
          new WOW({ animateClass: 'animate__slideInUp', offset: 0 }).init()
        }
      })
    },
    getBanner() {
      this.$api.home.getBanner({ type: 0 }).then(res => {
        this.imageList = res.data.map(item => {
          console.log(item, '$$');

          const image = {}
          image.imgUrl = `${this.baseUrl}${item.imageUrl}`
          image.alt = ''
          image.aspectRatio = item.aspectRatio
          image.isLinkUrl = item.linkUrl.includes('http')
          image.linkUrl = item.linkUrl
          image.videoUrl = item.videoUrl
          image.bannerId = item.bannerId
          // 合并按钮位置
          const obj = this.btnPosition.find(obj => item.linkUrl.includes(obj.linkUrl) && item.title === obj.name)
          if (obj) {
            console.log(obj, '$$$')
            return { ...image, ...obj }
          }
          return image
        })
        console.log(this.imageList, 'kv图片列表')
      })
    },
    // 获取关于块
    getDetail() {
      this.$api.home.getSection().then(res => {
        this.research = res.data.filter(item => item.type === 6)
        this.vehicle = res.data.filter(item => item.type === 7)
        this.activity = res.data.filter(item => item.type === 8)
        this.about = res.data.filter(item => item.type === 9)
        this.more = res.data.filter(item => item.type === 10)
        const wapList = [...this.research, ...this.vehicle, ...this.about]
        wapList.forEach((item, index) => {
          item.wapImgUrl = `${index}.jpg`
        })
        this.aboutWapList = wapList
      })
    },
    getMapList() {
      this.$api.home.getMap().then(res => {
        this.mapList = res.data
      })
    },
    getAreaList() {
      this.$api.testDrive.countryList({ type: 2 }).then(res => {
        this.areaList = res.data
      })
    },
    getProductMenu() {
      this.$api.home.productNavList({ typeId: null }).then(res => {
        const data = res.data
        console.log(data, '获取到车型数据')
        const dataMap = new Map()
        this.carImagesList.forEach(item => dataMap.set(item.id, item))
        data.forEach(item => {
          item.vehicleClassSummaries = item.vehicleClassSummaries.filter(child => {
            const obj = dataMap.get(child.id)
            if (obj && child.id === obj.id) {
              Object.assign(child, obj, { load: false })
              return obj
            }
            return false
          })
        })
        this.productMenu = data
      })
    },
    generateImages() {
      this.carImagesList.forEach(item => {
        if (item && item.colors) {
          item.colors.forEach(color => {
            if (color.path) {
              const images = []
              for (let i = 0; i < 36; i++) {
                const rotateImgsPath = `/360/${item.name}/${color.path}/${i}.webp`
                images.push(rotateImgsPath)
              }
              color.rotateList = images
            }
          })
        }
      })
      console.log(this.carImagesList, '图片列表')
    },
    handleLoad(categoryIdx, vehicleIdx) {
      this.productMenu[categoryIdx].vehicleClassSummaries[vehicleIdx].load = true
    },
    getNewsList() {
      this.$api.home.getNews().then(res => {
        if (res.code === 200) {
          this.newsList = res.data
        }
      })
    },
  },
}
</script>
<style lang="scss" scoped>
img {
  width: 100%;
}
.home-banner {
  width: 100vw;
  height: 100vh;
}

.index-model-section {
  position: relative;
  background-size: 100% 100%;
}

.main-section {
  padding: 0 1.5625vw;

  .hover-section {
    overflow: hidden;
    cursor: pointer;

    :deep .image-wrap {
      overflow: hidden;

      img {
        transition: transform 0.5s;
      }
    }

    .hover-img {
      transition: transform 0.5s;
    }

    &:hover {
      :deep img {
        transform: scale(1.1);
      }
    }
  }
}

.text__wrapper {
  position: absolute;
  width: 100%;
  display: flex;
  flex-direction: column-reverse;
}

.index-capital-section {
  position: relative;
  .capital-img {
    width: 100%;
    height: auto;
    aspect-ratio: 640 / 349;
  }
  margin-top: 15px;

  .text__wrapper {
    top: 5.208vw;
    left: 4.167vw;

    .text__body {
      .text-title {
        font-size: 35px;
        max-width: 50%;
        line-height: 1.2;
        color: #181413;
        font-weight: 600;
      }

      .text-desc {
        margin-top: 3.021vw;
        font-size: 16px;
        color: #929292;
      }
    }
  }
}

.lang___ar {
  .index-capital-section {
    .text__wrapper {
      right: 4.167vw;

      .text-desc {
        font-size: 26px;
        margin-top: 2.021vw;
      }
    }
  }

  .index-about-section {
    .text__wrapper {
      right: 2.604vw;
    }

    .learn-more-text-wrapper {
      right: 0;
    }
  }
}

.index-about-section {
  .text__wrapper {
    top: 2.604vw;
    left: 2.604vw;

    .text-title {
      color: #fff;
      font-size: 30px;
    }
  }
  .more-item {
    .text-title {
      white-space: pre-line;
    }
  }

  .learn-more-text-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    top: 0;
    left: 0;
    height: 100%;

    .text__body {
      text-align: center;
    }

    .text-title {
      font-size: 2.1vw;
      color: #bcc0c3;
    }

    .learn-more-btn {
      margin-top: 3.125vw;
      padding: 1.042vw 3.646vw;
    }
  }
}

.index-news-section {
  padding: 0 1.5625vw 3.125vw;
}

.index-map-section {
  margin-top: 3.125vw;
}

@media screen and (max-width: 1200px) {
  .home-banner {
    height: 56.25vw;
  }
}

@media screen and (max-width: 768px) {
  .main-section {
    margin-top: 15px;
  }
  .index-capital-section {
    .text__wrapper {
      .text__body {
        .text-title {
          font-size: 16px;
        }

        .text-desc {
          display: none;
        }
      }
    }
  }
  //关于
  .about-list {
    .about-item {
      position: relative;
      margin-top: 15px;

      .text-body {
        position: absolute;
        top: 11%;
        left: 5%;

        .text {
          color: #fff;
          font-weight: 600;
          font-size: 16px;
        }
      }
    }

    .view-btn {
      margin-top: 7%;
      text-align: center;

      .learn-more-btn {
        font-size: 12px;
        padding: 10px 30px;
      }
    }
  }
  //新闻
  .index-news-section {
    padding: 0;
  }
}
</style>
