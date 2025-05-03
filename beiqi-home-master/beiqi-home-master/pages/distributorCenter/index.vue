<template>
  <div>
    <div v-show="isVisible">
      <div class="banner">
        <img alt="" src="~/assets/image/distributor-center/home-distributor.jpg" />
        <h3>{{ $t('distributor.navTitle') }}</h3>
      </div>

      <div class="max-1680">
        <div class="title-wrap">
          <h3 class="section-title text-center">{{ $t('distributor.Category') }}</h3>
          <div class="options">
            <span class="iconfont icon-tuichu"></span>
            <div class="logout" @click="logout">{{ $t('distributor.logout') }}</div>
          </div>
        </div>

        <section v-if="typeLabelList.length > 0" class="distributor-section">
          <el-row :gutter="10">
            <el-col :span="24" class="brand-col img_box" @click.native="removeNewLabel(typeLabelList[5].type, typeLabelList[5].newLabelFlag)">
              <nuxt-link :to="localePath(typeLabelList[5].linkUrl)" class="section-item">
                <mq-layout :mq="['xl', 'lg', 'sm', 'md']">
                  <img :src="typeLabelList[5].pcImg" alt="" class="brand-img section-image" />
                </mq-layout>
                <mq-layout mq="xs">
                  <img :src="typeLabelList[5].mImg" alt="" class="brand-img section-image" />
                </mq-layout>
                <div :class="$i18n.locale == 'ar' ? 'title-ar' : ''" class="distributor-item-title">
                  <h5 class="text-title">{{ typeLabelList[5].title }}</h5>
                  <div v-if="typeLabelList[5].newLabelFlag != '1'" class="icon-text">{{ $t('distributor.NEW') }}</div>
                </div>
              </nuxt-link>
            </el-col>
          </el-row>
          <el-row :gutter="10" type="flex">
            <el-col :span="12">
              <el-row :gutter="10">
                <el-col :span="24" class="distributor-item-left img_box">
                  <nuxt-link :to="localePath(typeLabelList[0].linkUrl)" class="section-item">
                    <img :src="typeLabelList[0].imgUrl" alt="" class="section-image" />
                    <div :class="$i18n.locale == 'ar' ? 'title-ar' : ''" class="distributor-item-title">
                      <h5 class="text-title">{{ typeLabelList[0].title }}</h5>
                      <div v-if="!typeLabelList[0].newLabelFlag" class="icon-text">{{ $t('distributor.NEW') }}</div>
                    </div>
                  </nuxt-link>
                </el-col>
              </el-row>
              <el-row :gutter="10">
                <el-col :span="24" class="distributor-item-left img_box">
                  <nuxt-link :to="localePath(typeLabelList[1].linkUrl)" class="section-item">
                    <img :src="typeLabelList[1].imgUrl" alt="" class="section-image" />
                    <div :class="$i18n.locale == 'ar' ? 'title-ar' : ''" class="distributor-item-title">
                      <h5 class="text-title">{{ typeLabelList[1].title }}</h5>
                      <div v-if="!typeLabelList[1].newLabelFlag" class="icon-text">{{ $t('distributor.NEW') }}</div>
                    </div>
                  </nuxt-link>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="12" class="distributor-item-right">
              <el-row :gutter="10">
                <el-col :span="24" class="img_box distributor-item-bottom">
                  <nuxt-link :to="localePath(typeLabelList[2].linkUrl)" class="section-item">
                    <img :src="typeLabelList[2].imgUrl" alt="" class="section-image" />
                    <div :class="$i18n.locale == 'ar' ? 'title-ar' : ''" class="distributor-item-title">
                      <h5 class="text-title">{{ typeLabelList[2].title }}</h5>
                      <div v-if="!typeLabelList[2].newLabelFlag" class="icon-text">{{ $t('distributor.NEW') }}</div>
                    </div>
                  </nuxt-link>
                </el-col>
              </el-row>
              <el-row :gutter="10">
                <el-col :span="24" class="img_box distributor-item-bottom">
                  <nuxt-link :to="localePath(list[3].linkUrl)" class="section-item">
                    <img :src="typeLabelList[3].imgUrl" alt="" class="section-image" />
                    <div :class="$i18n.locale == 'ar' ? 'title-ar' : ''" class="distributor-item-title">
                      <h5 class="text-title">{{ typeLabelList[3].title }}</h5>
                      <div v-if="!typeLabelList[3].newLabelFlag" class="icon-text">{{ $t('distributor.NEW') }}</div>
                    </div>
                  </nuxt-link>
                </el-col>
              </el-row>
              <el-row :gutter="10">
                <el-col :span="24" class="img_box distributor-item-bottom">
                  <nuxt-link :to="localePath(typeLabelList[4].linkUrl)" class="section-item socail-item">
                    <img :src="typeLabelList[4].imgUrl" alt="" class="section-image" />
                    <div :class="$i18n.locale == 'ar' ? 'title-ar' : ''" class="distributor-item-title">
                      <h5 class="text-title">{{ typeLabelList[4].title }}</h5>
                      <div v-if="!typeLabelList[4].newLabelFlag" class="icon-text">{{ $t('distributor.NEW') }}</div>
                    </div>
                  </nuxt-link>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="24" class="brand-col img_box" @click.native="removeNewLabel(typeLabelList[6].type, typeLabelList[6].newLabelFlag)">
              <nuxt-link :to="localePath(typeLabelList[6].linkUrl)" class="section-item">
                <mq-layout :mq="['xl', 'lg', 'sm', 'md']">
                  <img :src="typeLabelList[6].pcImg" alt="" class="brand-img section-image" />
                </mq-layout>
                <mq-layout mq="xs">
                  <img :src="typeLabelList[6].mImg" alt="" class="brand-img section-image" />
                </mq-layout>
                <div :class="$i18n.locale == 'ar' ? 'title-ar' : ''" class="distributor-item-title">
                  <h5 class="text-title">{{ typeLabelList[6].title }}</h5>
                  <div v-if="typeLabelList[6].newLabelFlag != '1'" class="icon-text">{{ $t('distributor.NEW') }}</div>
                </div>
              </nuxt-link>
            </el-col>
          </el-row>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
import { checkToken } from '~/middleware/auth'
import { removeToken } from '~/utils/token'
import { getStorage, removeStorage } from '~/utils/storage'

export default {
  name: 'DistributorHome',
  components: {},
  layout: 'main',
  middleware: 'auth',
  meta: {
    requireAuth: true,
  },
  data() {
    return {
      list: [
        {
          title: this.$t('distributor.photos'),
          linkUrl: `/distributorCenter/models?type=1`,
          imgUrl: require('~/assets/image/distributor-center/photo.jpg'),
          type: 'Photos',
        },
        {
          title: this.$t('distributor.design'),
          linkUrl: '/distributorCenter/models?type=3',
          imgUrl: require('~/assets/image/distributor-center/design.jpg'),
          type: 'Designs',
        },
        {
          title: this.$t('distributor.video'),
          linkUrl: '/distributorCenter/models?type=2',
          imgUrl: require('~/assets/image/distributor-center/video.jpg'),
          type: 'Videos',
        },
        {
          title: this.$t('distributor.training'),
          linkUrl: '/distributorCenter/models?type=4',
          imgUrl: require('~/assets/image/distributor-center/training.jpg'),
          type: 'Training',
        },
        {
          title: this.$t('distributor.social'),
          linkUrl: '/distributorCenter/models?type=5',
          imgUrl: require('~/assets/image/distributor-center/social.jpg'),
          type: 'Social',
        },
        {
          title: this.$t('distributor.resource'),
          linkUrl: '/distributorCenter/brand',
          pcImg: require('~/assets/image/distributor-center/brand.jpg'),
          mImg: require('~/assets/image/distributor-center/m-brand.jpg'),
          type: 'BasicBtand',
        },
        {
          title: this.$t('distributor.event'),
          linkUrl: '/distributorCenter/event',
          pcImg: require('~/assets/image/distributor-center/event.jpg'),
          mImg: require('~/assets/image/distributor-center/m-event.jpg'),
          type: 'Event',
        },
      ],
      typeLabelList: [],
      isVisible: false,
    }
  },

  created() {},

  mounted() {
    checkToken()
    this.isVisible = checkToken()
  },

  activated() {
    this.getTypeNewLable()
    checkToken()
    this.isVisible = checkToken()
  },

  methods: {
    // 获取new标状态
    getTypeNewLable() {
      // const user = JSON.parse(localStorage.getItem('user'))
      const user = getStorage('user')
      if (user && user.userId) {
        this.$api.distributor.getTypeNewLable({ userId: user.userId }).then(res => {
          let typeLabelList = []
          if (res.code === 200) {
            // 从list中找type相同，将newLabel/list保存
            typeLabelList = this.list.map(item => {
              for (const i in res.data) {
                if (item.type === i) {
                  item.newLabelFlag = Number(res.data[i].newLabelFlag)
                  item.childLabel = res.data[i].list
                }
              }
              return item
            })
            this.typeLabelList = typeLabelList
            this.list = typeLabelList
          }
        })
      }
    },
    // 消除没有车型分类的new标
    removeNewLabel(type, newFlag) {
      // 如果type已经为1，则不执行
      if (newFlag) {
        return
      }
      const user = getStorage('user')
      const params = {
        newLabelFlag: '0',
        type,
        userId: user?.userId,
      }
      this.$api.distributor.removeNewLabel(params).then(res => {})
    },
    // 退出登录
    logout() {
      const prePath = this.$i18n.locale === this.$i18n.defaultLocale ? '' : '/' + this.$i18n.locale
      // 清除token
      removeToken()
      // localStorage.removeItem('user')
      removeStorage('user')
      // 跳转到登录页
      this.$router.push(prePath + `/distributorCenter/login`)
    },
  },
}
</script>

<style lang="scss" scoped>
.distributor-section {
  margin-top: 10px;
}

.banner {
  position: relative;

  h3 {
    position: absolute;
    top: 55%;
    left: 50%;
    color: #fff;
    font-size: 4.5vw;
    transform: translate(-50%, -50%);
  }
}

.title-wrap {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  padding: 30px 0;
  .section-title {
    padding: 0;
  }
}

.options {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  position: absolute;
  right: 0;
  cursor: pointer;

  .logout {
    margin-left: 8px;
  }
}

.img_box {
  height: 0;
  position: relative;

  .section-item {
    display: inline-block;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }

  .distributor-item-title {
    display: flex;
    align-items: center;
    position: absolute;
    bottom: 15px;
    left: 22px;
    //right: 0;
    text-align: center;
    color: #fff;
    font-size: 24px;
    text-shadow: 1px 1px 3px rgba($color: #000000, $alpha: 0.5);
  }

  .title-ar {
    width: 93%;

    .text-title {
      margin-left: 10px;
    }
  }
}

.icon-text {
  margin-left: 12px;
  padding: 2px 10px;
  background: #ee502f;
  color: #fff;
  border-radius: 15px 15px 15px 0;
  text-align: center;
  font-size: 13px;
}

.distributor-item-left {
  margin-bottom: 20px;
  padding-top: 50%;

  .section-item {
    right: 1%;
  }
}

.distributor-item-bottom {
  margin-bottom: 10px;
  padding-top: 33.8%;

  .section-item {
    left: 5px;
    right: 5px;
  }
}

.brand-col {
  padding-top: 16.5%;
  margin-bottom: 10px;

  .section-item {
    right: 5px;
  }
}

@media only screen and (max-width: 768px) {
  .distributor-section {
    margin-top: 10px;
  }

  .options {
    font-size: 12px;
    .icon-tuichu {
      font-size: 12px;
    }
  }

  .title-ar {
    .icon-text {
      left: -5px;
      right: auto !important;
    }
  }

  .img_box {
    .distributor-item-title {
      bottom: 10px;
      left: 8px;
    }

    .icon-text {
      margin-left: 0;
      padding: 0 5px;
      font-size: 10px;
      transform: scale(0.8);
    }

    .socail-item {
      width: 95%;

      .distributor-item-title {
        width: 80%;
      }

      .text-title {
        text-align: left;
      }

      .icon-text {
        position: absolute;
        right: 15%;
        bottom: 5px;
      }
    }

    .text-title {
      font-size: 12px;
    }
  }

  .distributor-item-left {
    margin-bottom: 5px;

    .distributor-item-title {
      bottom: 2px;
    }
  }
  .distributor-item-bottom {
    margin-bottom: 0;
    padding-top: 35.8%;
  }
  .brand-col {
    padding-top: 40%;
  }
}
</style>
