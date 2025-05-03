<template>
  <div v-show="isVisible">
    <client-only>
      <!--    banner-->
      <div v-if="banner?.imgVehicleUrl" class="banner">
        <img :src="banner?.imgVehicleUrl ? baseUrl + banner.imgVehicleUrl : ''" alt=""/>
      </div>

      <div class="max-1680">
        <!--      面包屑-->
        <DistributorCenter-breadcumb :link-url="preMenu" :title="$t('distributor.social')"/>
<!--        <section class="main">-->
<!--          <div class="theme-title">-->
<!--            <h6>{{ $t('distributor.social') }}</h6>-->
<!--          </div>-->

        <!--      查询-->
        <el-row :class="$i18n.locale == 'ar' ? 'row-ar' : ''" :gutter="15" class="queryForm">
          <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
            <SelectVue
              :key="typeKey"
              :options="typeOptions"
              :placeholder="$t('distributor.picsType')"
              @changeValue="changeType"
            ></SelectVue>
          </el-col>
          <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
            <el-input
              v-model="queryParams.keyWord"
              :placeholder="$t('distributor.content')"
              suffix-icon="el-icon-search"
              @change="handleChange"
            >
            </el-input>
          </el-col>
        </el-row>

<!--        列表-->
        <div v-if="selectSocialList.length > 0" :class="{'card-list-ar': langAr}" class="card-list">
          <distributorCenterCardListItem
            v-for="item in selectSocialList"
            :key="item.materialId"
            :item="item"
            class="card-item"
            @openPreview="openPreview"
            @closePreview="closePreview"
            @handleDownload="handleDownload"
          />
        </div>

        <el-empty v-else :description="$t('el.transfer.noData')"></el-empty>
        <!--        </section>-->
      </div>
    </client-only>
  </div>
</template>

<script>
import {checkToken} from "~/middleware/auth"
import SelectVue from '~/components/select'
import {getStorage} from "~/utils/storage"


export default {
  name: 'DistributorSocial',
  components: {SelectVue},
  layout: 'main',
  middleware: 'auth',
  meta: {
    requireAuth: true
  },

  data() {
    return {
      tableKey: 0,
      isPc: true,
      socialList: [],
      baseUrl: process.env.imgBaseUrl,
      indexArray: [],
      mergeArr: [],
      mergeObj: {},
      spanArr: [],
      isVisible: false,
      bannerKey: 0,
      typeOptions: [],
      typeKey: 0,
      queryParams: {
        type: '',
        keyWord: ''
      },
      banner: {},
      selectSocialList: []
    }
  },

  computed: {
    langAr() {
      return this.$i18n.locale === 'ar'
    },
    preMenu() {
      return `/distributorCenter/models?type=${this.$route.query.type}`
    },
  },

  mounted() {
    this.isVisible = checkToken()
  },

  activated() {
    this.selectSocialList = []
    this.queryParams = {
      type: '',
      keyWord: ''
    }
    this.typeOptions = []
    this.getmaterialList()
    this.typeKey++
    // 刷新banner组件
    this.bannerKey += 1
    this.isVisible = checkToken()
    this.banner = {}
    this.getBannerList()
  },

  methods: {
    getBannerList() {
      this.$api.home.productNavList({typeId: null, presentation: true}).then(res => {
        res.data.forEach(item => {
          if (item.vehicleClassSummaries) {
            item.vehicleClassSummaries.forEach(i => {
              if (i.id == this.$route.query.id) {
                this.banner = i
              }
            })
          }
        })
      })
    },
    // 类型筛选
    changeType(e) {
      this.queryParams.type = e
      this.queryParams.keyWord = ''
      this.selectSocialList = e ? this.socialList.filter(item => {
        if (this.queryParams.keyWord) {
          return item.content.includes(this.queryParams.keyWord) && item.materialPicsType === e
        } else {
          return item.materialPicsType === e
        }
      }) : this.socialList
    },
    openPreview(id) {
      const index = this.selectSocialList.findIndex(obj => obj.materialId == id)
      this.selectSocialList[index].showPreview = true
    },
    closePreview(id) {
      const index = this.selectSocialList.findIndex(obj => obj.materialId == id)
      this.selectSocialList[index].showPreview = false
    },
    // 下载
    handleDownload(item) {
      const user = getStorage('user')
      const data = {
        type: item.type,
        model: item.model,
        downloadLink: item.downloadLink,
        userId: user?.userId,
      }
      // 记录日志
      this.$api.distributor.recordDownloadLog(data)
      if (item.newLabelFlag == "1") return
      // 消除new接口
      // const user = JSON.parse(localStorage.getItem('user'))
      this.$api.distributor.materialNew({
        materialId: item.materialId,
        userId: user?.userId,
        newLabelFlag: "0"
      }).then(res => {
        if (res.code === 200) {
          // this.$message.success(res.msg)
          this.getmaterialList()
        }
      })
    },
    getmaterialList() {
      // const user = JSON.parse(localStorage.getItem('user'))
      const user = getStorage('user')
      const params = {
        type: 'Social',
        model: this.$route.query.id,
        userId: user?.userId
      }
      const typeOptions = [{name: 'all', value: ''}];
      this.$api.distributor.materialList(params).then(res => {
        console.log(res.data,'res')
        if(res.code == 200) {
          this.socialList = res.data.map(item => {
            console.log(item.date,'date')
            // 筛选类型
            if (!typeOptions.some(i => i.name === item.materialPicsType)) {
              if (item.materialPicsType) {
                typeOptions.push({
                  id: item.materialPicsType,
                  name: item.materialPicsType,
                  value: item.materialPicsType
                })
              }
            }

            this.typeOptions = typeOptions

            if (item.viewMore) {
              item.viewMore = item.viewMore.split(',')
            } else {
              item.viewMore = []
            }
            item.showPreview = false
            return item
          })
          // 按月份降序排序
          this.selectSocialList = this.socialList.sort((a,b) => b.date - a.date)
          console.log(this.socialList,'列表数据')
        }
      })
    },
    // 搜索内容
    handleChange(e) {
      this.selectSocialList = e ? this.socialList.filter(item => {
        if (this.queryParams.type) {
          return item.content.toLowerCase().includes(e.toLowerCase()) && item.materialPicsType === this.queryParams.type
        } else {
          return item.content.toLowerCase().includes(e.toLowerCase())
        }
      }) : this.socialList
    },
  }
}
</script>

<style lang="scss" scoped>
img {
  width: 100%;
  height: auto;
}

.queryForm {
  margin-top: 30px;
}

.row-ar {
  display: flex;
  flex-direction: row;
}

.main {
  width: 78.125vw;
  margin: 0 auto;
}

.theme-title {
  margin: 49px 0 44px;
  text-align: left;
  h6 {
    font-size: 30px;
    font-weight: 400;
  }
}

:deep .card-list {
  display: flex;
  flex-wrap: wrap;
  margin-top: 2.292vw;

  .card-item {
    width: 46%;
    margin: 0 8% 6% 0;

    .dialog {
      left: -103%;
    }
    &:nth-child(2n) {
      margin-right: 0;
    }
  }
}

:deep .card-list-ar {
  .card-item {
    margin-left: 6%;
    margin-right: 0;

    .dialog {
      right: -103%;
    }

    &:nth-child(2n) {
      margin-left: 0;
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
  .main {
    width: 70.74vw;
  }
  :deep .card-list {
    .card-item {
      width: 100%;
      margin-right: 0;
      .dialog {
        left: 0;
      }
    }
  }
  :deep .card-list-ar {
    .card-item {
      margin-left: 0;
      .dialog {
        right: 0;
      }
    }
  }
}

@media only screen and (max-width: 768px) {
  .main {
    width: 100%!important;
    margin: 0 auto;
    padding: 0;
  }
  :deep .card-list {
    margin-top: 6.5vw;
    .dialog {
      left: 50%!important;
    }
  }
}
</style>
