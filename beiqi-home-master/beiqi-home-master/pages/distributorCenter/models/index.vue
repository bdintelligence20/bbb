<template>
  <div v-show="isVisible">
    <distributorCenterBanner :key="bannerKey" :imageList="imageList" @bannerClick="bannerClick" />
    <div class="max-1680">
      <DistributorCenter-breadcumb />
      <div class="theme-title">
        <h6>{{ titleObj[$route.query?.type] }}</h6>
      </div>
      <mq-layout mq="lg+">
        <header-product-menu :key="menuKey" v-loading="carList.length == 0" :class="$i18n.locale == 'ar' ? 'product-ar' : ''" :productMenu="carList" :routeName="routeName" :routeQuery="routeQuery" class="product-menu" type="page" @handleCarItem="handleCarItem" />
      </mq-layout>
      <mq-layout :mq="['xs', 'sm', 'md']">
        <header-product-menu v-loading="carList.length == 0" :class="$i18n.locale == 'ar' ? 'product-ar' : ''" :productMenu="carList" :routeName="routeName" :routeQuery="routeQuery" class="product-menu-mobile" type="drawer" @handleCarItem="handleCarItem" />
      </mq-layout>
    </div>
  </div>
</template>

<script>
import Breadcrumb from '~/components/breadcumb'
import { checkToken } from '~/middleware/auth'
import { getStorage } from '~/utils/storage'

export default {
  name: 'distributorModels',
  layout: 'main',
  middleware: 'auth',
  components: { Breadcrumb },
  meta: {
    requireAuth: true,
  },
  data() {
    return {
      imageList: [
        {
          imgUrl: require('~/assets/image/distributor-center/swiper-photo.jpg'),
          linkUrl: '/distributorCenter/models?type=1',
          text: this.$t('distributor.photos'),
          alt: ''
        },
        {
          imgUrl: require('~/assets/image/distributor-center/swiper-video.jpg'),
          linkUrl: '/distributorCenter/models?type=2',
          text: this.$t('distributor.video'),
          alt: ''
        },
        {
          imgUrl: require('~/assets/image/distributor-center/swiper-design.jpg'),
          linkUrl: '/distributorCenter/models?type=3',
          text: this.$t('distributor.design'),
          alt: ''
        },
        {
          imgUrl: require('~/assets/image/distributor-center/swiper-training.jpg'),
          linkUrl: '/distributorCenter/models?type=4',
          text: this.$t('distributor.training'),
          alt: ''
        },
        {
          imgUrl: require('~/assets/image/distributor-center/swiper-social.jpg'),
          linkUrl: '/distributorCenter/models?type=5',
          text: this.$t('distributor.social'),
          alt: ''
        },
        {
          imgUrl: require('~/assets/image/distributor-center/swiper-brand.jpg'),
          linkUrl: '/distributorCenter/brand',
          text: this.$t('distributor.resource'),
          alt: ''
        },
        {
          imgUrl: require('~/assets/image/distributor-center/swiper-event.jpg'),
          linkUrl: '/distributorCenter/event',
          text: this.$t('distributor.event'),
          alt: 'event'
        },
      ],
      productMenu: [],
      // routeName: 'distributorCenter-resource-id',
      routeQuery: {},
      titleObj: {
        1: this.$t('distributor.photos'),
        3: this.$t('distributor.design'),
        2: this.$t('distributor.video'),
        4: this.$t('distributor.training'),
        5: this.$t('distributor.social'),
      },
      // models: [], //type对应车型
      isVisible: false,
      // 类型
      typeObj: {
        1: 'Photos',
        3: 'Designs',
        2: 'Videos',
        4: 'Training',
        5: 'Social',
      },
      menuKey: 0,
      carList: [],
      bannerKey: 0,
    }
  },

  computed: {
    routeName() {
      const type = this.$route.query.type
      let page = this.typeObj[type]?.toLowerCase()
      if (type) {
        return `distributorCenter-${page}`
      }
    },
    user() {
      return getStorage('user')
    },
  },

  async activated() {
    console.log(this.carList, '$$')
    this.routeQuery = { type: this.$route.query.type }
    if (!this.$route.query.type) {
      const prePath = this.$i18n.locale == this.$i18n.defaultLocale ? '' : '/' + this.$i18n.locale
      await this.$router.push(prePath + '/distributorCenter')
      return
    }
    this.carList = []
    await this.getCarMenu()
    // new标状态
    this.bannerKey += 1
    this.isVisible = checkToken()
  },

  async mounted() {
    // console.log('加载')
    this.isVisible = checkToken()
  },

  methods: {
    bannerClick() {
      // console.log('重新请求车型')
      this.getCarMenu()
    },
    // 消除车型new标
    handleCarItem(carId) {
      let params = {
        newLabelFlag: '0',
        type: this.typeObj[this.$route.query.type],
        userId: this.user?.userId,
        model: carId,
      }
      this.$api.distributor.removeNewLabel(params).then(res => {
        if (res.code == 200) {
          // 重新获取new标
          this.getTypeNewLable()
          this.menuKey += 1
        }
      })
    },
    async getCarMenu() {
      const params = {
        type: this.typeObj[this.$route.query.type],
        userId: this.user?.userId,
        presentation: true,
      }
      this.$api.distributor.getModels(params).then(res => {
        this.productMenu = res.data
        // console.log(this.productMenu,'&&&')
        this.getTypeNewLable()
      })
    },
    // 获取new标显示状态
    getTypeNewLable() {
      // const user = JSON.parse(localStorage.getItem('user'))
      const user = getStorage('user')
      if (user && user.userId) {
        this.$api.distributor.getTypeNewLable({ userId: user.userId }).then(res => {
          let typeLabelList = []
          if (res.code == 200) {
            // res.data类型,如何type为0，将0的list筛选出 model相同的 将newLable放进去
            // 有下载资源的所有车型
            const list = res.data[this.typeObj[this.$route.query.type]]?.list.map(item => {
              return {
                id: item.model,
                newLabelFlag: item.newLabelFlag,
              }
            })
            // 将有下载资源的车型newlabel放到productMenu
            this.productMenu.forEach(item => {
              item.vehicleClassSummaries.forEach(child => {
                list?.forEach(i => {
                  if (i.id == child.id) {
                    child.newLabelFlag = Number(i.newLabelFlag)
                  } else {
                  }
                })
              })
            })
            this.carList = this.productMenu
          } else {
          }
        })
      }
    },
  },
}
</script>

<style lang="scss" scoped>
.bread-crumbs {
  margin-top: 25px;
  font-size: 13px;
}

.theme-title {
  margin: 39px auto 34px;
  text-align: left;
  width: 74%;

  h6 {
    font-size: 30px;
    font-weight: 400;
  }
}

:deep .product-menu {
  margin-bottom: 50px;

  .product-nav__tabs {
    display: flex;
    min-height: 15vw;
  }

  .el-tabs__content {
    width: 88%;
  }

  .text-center {
    margin: 0 15px;
  }

  .product-nav__pane {
    max-height: none;
  }

  .el-tabs__nav {
    &:nth-child(2) {
      margin-top: 0;
      background: skyblue;
    }
  }

  .el-tabs__item {
    margin: 18px 0;
    height: auto;
  }

  .nav-car__pane {
    margin-bottom: 8px;
  }

  .is-active {
    .label {
      padding-bottom: 5px;
      border-bottom: 1px solid #cd3627;
    }
  }

  .el-tabs__nav .el-tabs__item:nth-child(2) {
    margin-top: 0;
  }

  .nav__car-title {
    //margin-bottom: 6px;
    font-size: 15px;
  }

  .nav_car-item {
    padding-left: 26px !important;
    padding-right: 26px !important;
    margin: 0 15px;
    &:nth-child(n+6) {
      margin-top: 20px;
    }
  }

  .nav__car-name {
    //margin-top: 20px;
  }
}

:deep .product-ar {
  .el-tabs__nav .is-left {
    margin-left: 68px;
  }
}

:deep .product-menu-mobile {
  padding: 0 10px;

  .nav__car-title {
    //margin-bottom: 30px;
  }

  .nav__car-name {
    margin: 10px 0 20px;
  }

  @media only screen and (min-width: 768px) and (max-width: 1199px) {
    .el-col {
      width: 25%;
    }
    .car-item {
      padding-right: 20px;
    }
    .text-center {
      &:nth-child(2n) {
        .car-item {
          padding-left: 20px;
          padding-right: 0;
        }
      }
    }
  }
}

@media only screen and (max-width: 1199px) {
  .theme-title {
    margin: 24px 0;

    h6 {
      font-size: 20px;
    }
  }
}
</style>
