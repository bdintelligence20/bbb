<template>
  <!-- <div :class="{ 'main-container-box': ishideBox }"> -->
    <!-- <div v-if="isShowIframeOnce" class="iframe-container" :class="{ 'hide-iframe': hideIframe }">
      <iframe class="frame" src="https://www.baicglobal.com/gulfcup/" width="100%" height="100%" frameborder="0" style="overflow: auto; width: 100vw; height: 100vh"></iframe>
    </div> -->

    <div id="main__layout" v-loading.body.fullscreen.lock="fullscreenLoading" class="main__layout" :class="[$mq, addLangClassName, scrollStyle]">
      <!-- 需要维护加这个 <div v-if="isWWW">
      <Maintenance />
    </div>
    <div v-else></div> -->
      <header ref="header" :class="[hasBanner > -1 ? 'site-header' : '',hasAllWhite > -1?'header-white-text':'', headerSticky ? 'header-sticky' : '', headerAppear ? 'header-appear' : 'header-hide', $route.path === '/' ? 'home-menu' : '']">
        <div class="max-1680">
          <div class="header-layout">
            <div class="header-item header-item--logo">
              <LogoVue></LogoVue>
            </div>
            <mq-layout mq="lg+" class="header-item header-item--navigation text-center">
              <NavBar v-if="productMenu && productMenu.length > 0" :main-menu="mainMenu" :product-menu="productMenu"></NavBar>
            </mq-layout>
            <mq-layout mq="lg+" class="header-item header-item--icons">
              <LangSelectorVue />
            </mq-layout>
            <mq-layout :mq="['xs', 'sm', 'md']" class="header-item header-item--icons">
              <i class="el-icon-s-operation drawer-btn__open" @click="drawer = true"></i>
            </mq-layout>
          </div>
        </div>
      </header>
      <!--弹框 用户调查 -->
      <div class="questionnaireDialog">
        <el-dialog v-show="dialogVisible" v-cloak :visible.sync="dialogVisible" :class="{ 'es-dialog': 'es' == $i18n.locale }" center>
          <div v-cloak class="dialog-text-box">
            <p v-html="$t('questionnaireDialog.Text')"></p>
          </div>
          <span v-cloak slot="footer" class="dialog-footer">
            <!-- <nuxt-link :to="localePath('/questionnaire', $i18n.locale)"> -->
            <nuxt-link :to="localePath('/questionnairepage', $i18n.locale)">
              <el-button class="border-el-button back" @click="onClickDialog">
                {{ $t('questionnaireDialog.BtnText') }}
              </el-button>
            </nuxt-link>
          </span>
        </el-dialog>
      </div>
      <!-- 用户调查 end -->
      <div class="main-container">
        <nuxt keep-alive />
      </div>
      <LayoutFooter :web-config="webConfig" :footer-link-list="footerLinkList" />

      <NavDrawVue :drawer="drawer" :main-menu="mainMenu" :product-menu="productMenu" @closeDrawer="closeDrawer"></NavDrawVue>
      <AsideBar v-if="showAside"></AsideBar>
    </div>
  <!-- </div> -->
</template>

<script>
import LayoutFooter from '~/components/footer/LayoutFooter.vue'
import LangSelectorVue from '~/components/header/LangSelector.vue'
import LogoVue from '~/components/header/Logo.vue'
import NavBar from '~/components/header/NavBar.vue'
import NavDrawVue from '~/components/header/navDraw.vue'
import AsideBar from '~/components/aside'

export default {
  name: 'Main',
  components: { LangSelectorVue, LayoutFooter, LogoVue, NavBar, NavDrawVue, AsideBar },
  provide() {
    return {
      main: this, // 传递可响应对象
    }
  },
  data() {
    return {
      // ishideBox: true,
      // ishowLayout: false,
      fullscreenLoading: false,
      innerDrawer: false,
      drawer: false,
      hasAllWhite: null,
      allWhiteArr: ['models-36'],
      hasBanner: null,
      bannerArr: ['models-36','index', 'job', 'mediaCenter', 'subscribe', 'models', 'contactUs', 'news-newsRelease', 'news-mediaReport', 'news-multimedia', 'news-multimedias', 'news-newsRelease-detail-id', 'news-mediaReport-detail-id', 'about-afterSale', 'models-id', 'testDrive', 'about-afterSale', 'about-ourVision', 'about-overview', 'about-socialResponsibility', 'about-history', 'distributorCenter-login', 'distributorCenter-forget', 'distributorCenter-reset', 'news-baicInX'],
      webConfig: {},
      footerLinkList: [],
      mainMenu: [],
      productMenu: [],
      title: '',
      description: '',
      screenWidth: 0,
      isMobile: false,
      showAside: false,
      headerSticky: false,
      headerAppear: true,
      addLangClassName: '',
      scrollStyle: '',
      isWWW: true,
      dialogVisible: false,
      // hideIframe: false,
      // lock: false,
      // isShowIframeOnce: false, // 是否显示过iframe
    }
  },
  head() {
    return {
      title: this.title || 'BAIC',
      meta: [{ hid: 'description', name: 'description', content: this.description || 'BAIC Motor Corporation., Ltd' }],
    }
  },
  watch: {
    '$i18n.locale': {
      immediate: true,
      handler(newName, oldName) {
        if (process.server) return // 服务端不处理
        this.addLangClassName = `lang___${newName}`
        this.getMenuList()
        this.getCarMenu()
        this.getFooterLink()
        this.getWebConfig()
        // let isHOME = this.$route.name && this.$route.name.indexOf('index___') > -1
        // if (isHOME) {
        //   this.$nextTick(() => {
        //     this.getDialogQuestionnaire()
        //   })
        // }
      },
    },
    $route: {
      immediate: true,
      handler(to, from) {
        if (to.name) {
          this.hasAllWhite = this.allWhiteArr.indexOf(to.name.split('___')[0])
          this.hasBanner = this.bannerArr.indexOf(to.name.split('___')[0])
          console.log(to.name.split('___')[0], 'to.name')
          to.name.indexOf('index') === 0 ? (this.showAside = true) : (this.showAside = false)
          console.log(this.showAside, 'show aside')
          this.closeDrawer()
        }
      },
    },
  },
  beforeCreate() {
    // if(!process.server){
    //   if(window.location.href.startsWith('https://www.baicintl.com/')){
    //     window.location.href = window.location.href.replace('https://www.baicintl.com/', 'https://www.baicglobal.com/')
    //   }
    // }
  },
  created() {
    this.fullscreenLoading = true
  },
  activated() {
    console.log(this.showAside, 'activated show aside')
  },
  mounted() {
    // this.getIframeCountry()
    // this.getUserLocation()

    // 获取当前页面的 URL 查询参数
    // const urlParams = new URLSearchParams(window.location.search)
    // 获取单个查询参数的值
    // const fromgj = urlParams.get('fromgj')
    // console.log(fromgj, 'fromgj')

    // if (fromgj) {
    //   this.isShowIframeOnce = true
    //   this.ishideBox = true
    //   this.ishowLayout = false
    //   this.getIframeMsg()
    // } else if (localStorage.getItem('isShowIframeOnce') === null) {
    //   // 如果沒有值，去請求接口
    //   this.getIframeCountry()
    // } else {
    //   console.log('已经显示过iframe了')
    //   // 如果有值，説明之前顯示過，直接顯示网页
    //   this.isShowIframeOnce = false
    //   this.ishideBox = false
    //   this.ishowLayout = true
    // }



    console.log(this.showAside, 'mounted show aside')
    this.isWWW = window.location.href.startsWith('https://www.baicglobal.com/')
    this.scrollTop = 0
    window.addEventListener('scroll', this.scrollHandle)
    this.$nextTick(() => {
      setTimeout(() => {
        this.fullscreenLoading = false
      }, 600)
    })
  },
  beforeDestroy() {
    // if (!this.isShowIframeOnce) {
    // window.removeEventListener('message', this.handleIframeHide, false)
    // }
  },
  destroyed() {
    window.removeEventListener('scroll', this.scrollHandle)
  },
  methods: {
    getIframeCountry() {
      const code = ['BH', 'QA', 'KW', 'AE', 'SA', 'IQ']
      // 获取iframe显示的国家
      this.$axios
        .get('/geoip')
        .then(res => {
          console.log('获取到的国家信息:', res)
          // res.country = 'SA' // 测试代码
          if (res.country && code.includes(res.country)) {
            console.log('在中东地区')
            this.isShowIframeOnce = true
            this.ishideBox = true
            // 获取iframe传递的消息
            this.getIframeMsg()
            // 保存ifrme显示过的状态
            localStorage.setItem('isShowIframeOnce', true)
          } else {
            console.log('不在中东地区')
            this.isShowIframeOnce = false
            this.ishideBox = false
            this.ishowLayout = true
          }
        })
        .catch(err => {
          console.error('获取国家信息失败:', err)
          // 隐藏iframe
          this.isShowIframeOnce = false
        })
    },
    // // 监听iframe
    // getIframeMsg() {
    //   // 没有显示过iframe，监听message事件
    //   window.addEventListener('message', this.handleIframeHide, false)
    // },
    // getUserLocation() {
    //   // 检查浏览器是否支持地理位置
    //   if (navigator.geolocation) {
    //     navigator.geolocation.getCurrentPosition(
    //       position => {
    //         const latitude = position.coords.latitude
    //         const longitude = position.coords.longitude
    //         console.log('获取到的经纬度:', latitude, longitude)
    //         this.getAddress(latitude, longitude)
    //       },
    //       error => {
    //         console.error('获取地理位置失败:', error)
    //         // 测试代码
    //         const testLocations = [
    //           {
    //             country: 'Bahrain',
    //             coords: { latitude: 26.2285, longitude: 50.586 },
    //           },
    //           {
    //             country: 'Qatar',
    //             coords: { latitude: 25.2867, longitude: 51.5333 },
    //           },
    //           {
    //             country: 'Kuwait',
    //             coords: { latitude: 29.3759, longitude: 47.9774 },
    //           },
    //           {
    //             country: 'United Arab Emirates',
    //             coords: { latitude: 25.2048, longitude: 55.2708 }, // 迪拜
    //           },
    //           {
    //             country: 'Saudi Arabia',
    //             coords: { latitude: 24.7136, longitude: 46.6753 },
    //           },
    //           {
    //             country: 'Iraq',
    //             coords: { latitude: 33.3152, longitude: 44.3661 },
    //           },
    //           {
    //             country: 'China',
    //             coords: { latitude: 36.66291110439971, longitude: 116.99958801269531 },
    //           },
    //         ]
    //         this.getAddress(testLocations[5].coords.latitude, testLocations[5].coords.longitude)

    //         // 没有取到位置，不显示iframe
    //         this.isShowIframeOnce = true
    //       }
    //     )
    //   } else {
    //     console.error('浏览器不支持地理位置')
    //     // 不显示iframe
    //     this.isShowIframeOnce = true
    //   }
    // },
    // 根据经纬度获取地址信息
    // getAddress(latitude, longitude) {
    //   // 使用必应地图 REST API 进行反向地理编码
    //   const bingMapsKey = 'SECRT-ID'
    //   const url = `https://dev.virtualearth.net/REST/v1/Locations/${latitude},${longitude}?key=${bingMapsKey}`

    //   fetch(url)
    //     .then(response => response.json())
    //     .then(data => {
    //       if (data.resourceSets && data.resourceSets[0].resources) {
    //         const address = data.resourceSets[0].resources[0].address
    //         const countryRegion = address.countryRegion
    //         console.log('获取到的位置信息:', address)
    //         console.log('获取到的城市信息:', countryRegion)
    //         // 巴林、卡塔尔、巴林、科威特、阿联酋、沙特、伊拉克
    //         // 测试坐标数组
    //         const countryList = ['Bahrain', 'Qatar', 'Kuwait', 'United Arab Emirates', 'Saudi Arabia', 'Iraq']
    //         // 判断是否在中东
    //         if (countryList.includes(countryRegion)) {
    //           // 控制iframe是否显示的变量
    //           this.isShowIframeOnce = false
    //           // 获取iframe传递的消息
    //           this.getIframeMsg()
    //         } else {
    //           this.isShowIframeOnce = true
    //         }
    //       }
    //     })
    //     .catch(error => {
    //       console.error('获取位置信息失败:', error)
    //     })
    // },
    // 隐藏iframe
    // handleIframeHide(e) {
    //   console.log('接收iframe传过来的数据', e.data, e.origin)
    //   if (e.data === 'hide') {
    //     if (this.lock) return
    //     this.lock = true
    //     this.hideIframe = true
    //     console.log('关闭iframe')
    //     this.ishideBox = false

    //     // 移除iframe
    //     setTimeout(() => {
    //       document.getElementsByClassName('iframe-container')[0].remove()
    //     }, 2000)
    //   }
    //   // iframe通知要显示首页了
    //   if (e.data === 'showHome') {
    //     console.log('接收到showHome')
    //     this.ishowLayout = true
    //   }
    // },
    getDialogQuestionnaire() {
      const isDialogQuestionnaire = localStorage.getItem('dialogQuestionnaire')
      if (isDialogQuestionnaire == null || isDialogQuestionnaire == 'false') {
        this.dialogVisible = true
      }
    },
    /**
     * 问卷调查弹框
     */
    onClickDialog() {
      this.dialogVisible = false
      localStorage.setItem('dialogQuestionnaire', true)
    },
    scrollHandle() {
      // 变量scrollTop是滚动条滚动时，距离顶部的距离
      const scrollTop = document.documentElement.scrollTop || document.body.scrollTop
      // 变量windowHeight是可视区的高度
      const windowHeight = document.documentElement.clientHeight || document.body.clientHeight
      // 变量scrollHeight是滚动条的总高度
      const scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight

      const lastToTop = this.$el.querySelector('.last-to-top')
      if (lastToTop && lastToTop.style) {
        if (scrollTop >= windowHeight) {
          lastToTop.style.display = 'block'
        } else {
          lastToTop.style.display = 'none'
        }
      }

      this.headerSticky = scrollTop > 20
      if (scrollTop > this.scrollTop && scrollTop > 10) {
        // console.log('向下')
        this.headerAppear = false
      } else if (scrollTop < this.scrollTop) {
        // console.log('向上')
        this.headerAppear = true
      }
      this.scrollTop = scrollTop
    },
    closeDrawer(data) {
      this.drawer = data
    },
    async getWebConfig() {
      this.$api.footer.getWebConfig({}).then(res => {
        this.webConfig = res.data
        this.title = this.webConfig.title
        this.description = this.webConfig.summary
      })
    },
    async getFooterLink() {
      this.$api.footer.menuList({ topOrBottom: 1 }).then(res => {
        this.footerLinkList = res.data
      })
    },
    async getMenuList() {
      this.$api.home.menuList({ topOrBottom: 0 }).then(res => {
        this.mainMenu = res.data
      })
    },
    async getCarMenu() {
      this.$api.home.productNavList({ typeId: null }).then(res => {
        this.productMenu = res.data
      })
    },
  },
}
</script>
<style lang="scss">
.main-container-box {
  width: 100vw;
  height: 100vh;
  position: fixed;
  overflow: hidden;
}

.iframe-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 10000000000;
  transition: all 2s;
  background: #1b1c20;
}
.hide-iframe {
  top: -100%;
}
.site-header {
  //background : linear-gradient(180deg, rgba(255, 255, 255, 0.8) 0%, rgba(255, 255, 255, 0) 100%);
  position: absolute;
  width: 100%;
  z-index: 99;
}

.header-appear.header-white-text:not(.header-sticky, :hover) * {
    color: #fff !important;
  }

  .header-appear.header-white-text:not(.header-sticky, :hover) img {
    filter: grayscale(100%) invert(100%) brightness(1.2) contrast(1.2);
  }
.header-hide {
  top: -100px;
  transition: all 0.5s ease-in-out;
}

.header-appear {
  top: 0;
  transition: all 0.5s ease-in-out;
  // border-bottom: 1px solid;
  // border-color: rgba(255,255,255,.5);
}
//首页导航
//.home-menu {
//  .site-nav__link--f {
//    color: #fff;
//  }
//  &:hover {
//    .site-nav__link {
//      color: #000;
//    }
//  }
//}

.header-sticky {
  background: #fff;
  position: fixed;
  width: 100%;
  z-index: 99;

  //.site-nav__link--f {
  //  color: #000;
  //
  //  .lang-switcher .el-dropdown-link,
  //  a {
  //    color: #000;
  //  }
  //}

  .lang-switcher .el-dropdown-link {
    color: #000;
  }

  .site-nav__dropdown {
    background: rgba(255, 255, 255, 0.9);
  }
}

.nav-drawer {
  .lang-item {
    padding-top: 20px;
    display: inline-block;
  }
}

.header-layout {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 17px;
  .header-item--logo {
    h1 {
      margin: 0.48em 0;
    }
  }

  ::v-deep .el-tabs__item {
    font-size: 17px;
  }

  ::v-deep .el-dropdown {
    font-size: 17px;
  }

  ::v-deep .el-dropdown-menu__item {
    font-size: 17px;
  }
}

.header-item--logo,
.header-item--icons {
  flex: 0 0 80px;
  justify-content: flex-end;
  color: #000;
}

// .header-item--icons {
//   justify-content: flex-end;
// }
.header-item {
  display: flex;
  align-items: center;
}

.header-item--navigation {
  flex: 1 1 auto;
  padding: 0 40px;
}

.drawer-btn__open {
  font-size: 30px;
  cursor: pointer;
}

.main__layout {
  overflow: hidden;
  height: 100%;
}

.xs,
.sm,
.md {
  .site-header {
    //background : linear-gradient(180deg, rgba(255, 255, 255, 0.8) 0%, rgba(255, 255, 255, 0) 100%);
    position: relative;
  }

  .header-sticky {
    position: fixed;
  }

  .logo-container {
    margin: 15px 0;
  }
}

.questionnaireDialog {
  .el-dialog {
    width: 453px;
    height: 511px;
    background: #fff;
    background: url('assets/image/bg-questionnaire.jpg') no-repeat;
    background-size: 100% 100%;

    .dialog-text-box {
      height: 135px;
      text-align: center;
      display: inline-block;
      font-size: 20px;
      margin-top: 175px;
      color: #000;
      word-break: break-word;
    }

    .el-button {
      min-width: 172px;
      height: 45px;
      color: white;
      background: black;
    }

    .el-dialog__footer {
      margin-top: -20px;
    }

    .el-dialog__close {
      font-size: 24px;
      padding: 3px;
      color: black;
      margin-top: -10px;
      margin-right: -5px;
      background: white;
      border-radius: 50%;

      &:hover {
        color: black;
      }
    }
  }

  @media only screen and (max-width: 768px) {
    .es-dialog {
      .el-dialog {
        height: 370px;
      }

      .el-dialog__footer {
        margin-top: -38px;
      }
    }

    .el-dialog {
      width: 320px;
      height: 348px;
      background: #fff;
      background: url('assets/image/bg-questionnaire.jpg') no-repeat;
      background-size: 100% 100%;

      .dialog-text-box {
        height: 10px;
        text-align: center;
        display: inline-block;
        font-size: 16px;
        margin-top: 85px;
        color: #000;
        word-break: break-word;
      }

      .el-button {
        min-width: 160px;
        height: 38px;
        color: white;
        background: black;
      }

      .el-dialog__close {
        font-size: 24px;
        padding: 3px;
        color: black;
        margin-top: -10px;
        margin-right: -5px;
        background: white;
        border-radius: 50%;

        &:hover {
          color: black;
        }
      }
    }

    .el-popup-parent--hidden {
      padding-right: 0 !important;
    }
  }
}
</style>
