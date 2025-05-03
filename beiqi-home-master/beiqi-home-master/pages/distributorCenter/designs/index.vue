<template>
  <div v-show="isVisible">
    <!--    banner-->
    <div v-if="Object.keys(banner).length > 0" class="banner">
      <img :src="banner?.imgVehicleUrl ? baseUrl + banner.imgVehicleUrl : ''" alt="" />
    </div>
    <div class="max-1680">
      <!--      面包屑-->
      <DistributorCenter-breadcumb :linkUrl="linkUrl" :title="$t('distributor.design')" />

      <!--      查询-->
      <el-row :class="$i18n.locale == 'ar' ? 'row-ar' : ''" :gutter="15" class="queryForm">
        <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
          <SelectVue :options="typeOptions" :placeholder="$t('distributor.picsType')" @changeValue="changeType" :key="typeKey"></SelectVue>
        </el-col>
        <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
          <el-input v-model="queryParams.keyWord" :placeholder="$t('distributor.content')" suffix-icon="el-icon-search" @change="handleChange"></el-input>
        </el-col>
      </el-row>

      <!--      列表-->
      <div :class="{ 'card-list-ar': langAr }" class="card-list" v-if="selectTableData.length > 0">
        <distributorCenterCardListItem v-for="item in selectTableData" :key="item.materialId" :item="item" class="designs-card-item" @openPreview="openPreview" @closePreview="closePreview" @handleDownload="handleDownload" />
      </div>

      <el-empty v-else :description="$t('el.transfer.noData')"></el-empty>
    </div>
  </div>
</template>

<script>
import { checkToken } from '~/middleware/auth'
import SelectVue from '~/components/select'
import { getStorage } from '~/utils/storage'

export default {
  name: 'training',
  layout: 'main',
  middleware: 'auth',
  meta: {
    requireAuth: true,
  },
  components: { SelectVue },
  data() {
    return {
      loading: false,
      tableData: [],
      isVisible: false,
      selectTableData: [],
      banner: {},
      baseUrl: process.env.imgBaseUrl,
      typeOptions: [],
      queryParams: {
        type: '',
        keyWord: '',
      },
      typeKey: 0,
    }
  },

  mounted() {
    console.log('组件加载')
    this.isVisible = checkToken()
    this.queryParams.type = ''
  },

  computed: {
    linkUrl() {
      return `/distributorCenter/models?type=${this.$route.query.type}`
    },
    langAr() {
      return this.$i18n.locale === 'ar'
    },
  },

  activated() {
    console.log('进入页面')
    if (!this.$route.query.type) {
      this.$router.push('/distributorCenter')
      return
    }
    if (!this.$route.query.id) {
      this.$router.push('/distributorCenter/models')
      return
    }
    this.banner = {}
    this.selectTableData = []
    this.queryParams = {
      type: '',
      keyWord: '',
    }
    this.typeKey++
    this.getBannerList()
    this.getmaterialList()
    this.isVisible = checkToken()
    // this.getmaterialList()
  },

  methods: {
    getBannerList() {
      this.$api.home.productNavList({ typeId: null, presentation: true }).then(res => {
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
    // 获取列表数据
    getmaterialList() {
      this.loading = true
      // const user = JSON.parse(localStorage.getItem('user'))
      const user = getStorage('user')
      let params = {
        type: 'Designs',
        model: this.$route.query.id,
        userId: user?.userId,
      }
      let typeOptions = [{ name: 'all', value: '' }]
      this.$api.distributor.materialList(params).then(res => {
        if (res.code == 200) {
          this.tableData = res.data.map(item => {
            // 更多图片
            // item.moreImgList = []
            // if (item.viewMore) {
            //   item.moreImgList = item.viewMore.split(',')
            // }
            // 筛选类型
            if (!typeOptions.some(i => i.name === item.materialPicsType)) {
              if (item.materialPicsType) {
                typeOptions.push({
                  id: item.materialPicsType,
                  name: item.materialPicsType,
                  value: item.materialPicsType,
                })
              }
            }
            // 如果有null或空则不保存
            // const thumbList = []
            // thumbList.push(item.firstScreenshot, item.secondScreenshot, item.thirdScreenshot)
            // item.thumbList = thumbList.filter(item => item)
            if (item.viewMore) {
              item.viewMore = item.viewMore.split(',')
            } else {
              item.viewMore = []
            }
            item.showPreview = false
            return item
          })

          // 图片类型Trifold放Design前面
          this.tableData.sort((a, b) => {
            if (a.materialPicsType === 'Trifold' && b.materialPicsType === 'Design Pics') {
              return -1
            } else if (a.materialPicsType === 'Design Pics' && b.materialPicsType === 'Trifold') {
              return 1
            } else {
              return 0
            }
          })

          this.selectTableData = JSON.parse(JSON.stringify(this.tableData))
          this.loading = false
          this.typeOptions = typeOptions
        } else {
          this.loading = false
        }
      })
    },
    // 类型筛选
    changeType(e) {
      this.queryParams.type = e
      this.queryParams.keyWord = ''
      this.selectTableData = e
        ? this.tableData.filter(item => {
            if (this.queryParams.keyWord) {
              return item.content.indexOf(this.queryParams.keyWord) != -1 && item.materialPicsType === e
            } else {
              return item.materialPicsType === e
            }
          })
        : this.tableData
    },
    // 搜索内容
    handleChange(e) {
      this.selectTableData = e
        ? this.tableData.filter(item => {
            if (this.queryParams.type) {
              return item.content.toLowerCase().indexOf(e.toLowerCase()) != -1 && item.materialPicsType === this.queryParams.type
            } else {
              return item.content.toLowerCase().indexOf(e.toLowerCase()) != -1
            }
          })
        : this.tableData
    },
    // 下载
    handleDownload(item) {
      // console.log(item,'item')
      const user = getStorage('user')
      const data = {
        type: item.type,
        model: item.model,
        downloadLink: item.downloadLink,
        userId: user?.userId,
      }
      // 记录日志
      this.$api.distributor.recordDownloadLog(data)
      if (item.newLabelFlag == '1') return
      // 消除new
      // const user = JSON.parse(localStorage.getItem('user'))
      this.$api.distributor
        .materialNew({
          materialId: item.materialId,
          userId: user?.userId,
          newLabelFlag: '0',
        })
        .then(res => {
          if (res.code === 200) {
            console.log('消除new标')
            // this.$message.success(res.msg)
            this.getmaterialList()
          }
        })
    },
    // 预览更多
    openPreview(id) {
      const index = this.selectTableData.findIndex(obj => obj.materialId == id)
      this.selectTableData[index].showPreview = true
    },
    // 关闭预览
    closePreview(id) {
      const index = this.selectTableData.findIndex(obj => obj.materialId == id)
      this.selectTableData[index].showPreview = false
    },
  },
}
</script>

<style lang="scss" scoped>
.queryForm {
  margin-top: 30px;
}

.row-ar {
  display: flex;
  flex-direction: row;
}
.card-list {
  display: flex;
  flex-wrap: wrap;
  margin-top: 2.292vw;

  :deep .designs-card-item {
    width: 46%;
    margin: 0 8% 6% 0;

    &:nth-child(2n) {
      margin-right: 0;
    }
  }
}

.card-list-ar {
  :deep .designs-card-item {
    margin-left: 6%;
    margin-right: 0;

    &:nth-child(2n) {
      margin-left: 0;
    }
  }
}

@media only screen and (max-width: 1199px) {
  .card-list {
    :deep .designs-card-item {
      width: 100%;
      margin-right: 0;
    }
  }
  .card-list-ar {
    :deep .designs-card-item {
      margin-left: 0;
    }
  }
}
@media only screen and (max-width: 768px) {
  .card-list {
    margin-top: 6.5vw;
  }
}
</style>
