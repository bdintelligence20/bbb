<template>
  <div v-show="isVisible">
    <!--    banner-->
    <div v-if="banner?.imgVehicleUrl" class="banner">
      <img :src="banner?.imgVehicleUrl ? baseUrl + banner.imgVehicleUrl : ''" alt="" />
    </div>
    <div class="max-1680">
      <!--      面包屑-->
      <DistributorCenter-breadcumb :link-url="linkUrl" :title="$t('distributor.photos')" />

      <!--      查询-->
      <el-row :class="$i18n.locale == 'ar' ? 'row-ar' : ''" :gutter="15" class="queryForm">
        <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
          <SelectVue :key="typeKey" :options="typeOptions" :placeholder="$t('distributor.picsType')" @changeValue="changeType"></SelectVue>
        </el-col>
        <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
          <el-input v-model="queryParams.keyWord" :placeholder="$t('distributor.content')" suffix-icon="el-icon-search" @change="handleChange"> </el-input>
        </el-col>
      </el-row>

      <!--      列表-->
      <div v-if="selectTableData.length > 0" :class="{ 'card-list-ar': langAr }" class="card-list">
        <distributorCenterCardListItem v-for="item in selectTableData" :key="item.materialId" :item="item" class="photo-card-item" @openPreview="openPreview" @closePreview="closePreview" @handleDownload="handleDownload" />
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
  name: 'DistributorPhoto',
  components: { SelectVue },
  layout: 'main',
  middleware: 'auth',
  meta: {
    requireAuth: true,
  },
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

  computed: {
    linkUrl() {
      return `/distributorCenter/models?type=${this.$route.query.type}`
    },
    langAr() {
      return this.$i18n.locale === 'ar'
    },
  },

  mounted() {
    this.isVisible = checkToken()
  },

  activated() {
    const prePath = this.$i18n.locale === this.$i18n.defaultLocale ? '' : '/' + this.$i18n.locale
    if (!this.$route.query.type || !this.$route.query.id) {
      this.$router.push(prePath + '/distributorCenter')
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
      const params = {
        type: 'Photos',
        model: this.$route.query.id,
        userId: user?.userId,
      }
      const typeOptions = [{ name: 'all', value: '' }]
      this.$api.distributor.materialList(params).then(res => {
        if (res.code == 200) {
          this.tableData = res.data.map(item => {
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
            if (item.viewMore) {
              item.viewMore = item.viewMore.split(',')
            } else {
              item.viewMore = []
            }
            item.showPreview = false
            return item
          })
          // 按materialPicsType排序，Studio在前Outdoor在后
          this.tableData.sort((a, b) => {
            if (a.materialPicsType === 'Studio Shot' && b.materialPicsType === 'Outdoor Shot') {
              // studio在outdoor之前
              return -1
            } else if (a.materialPicsType === 'Outdoor Shot' && b.materialPicsType === 'Studio Shot') {
              // outdoor在studio之后
              return 1
            } else {
              // 保持原有顺序
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
              return item.content.includes(this.queryParams.keyWord) && item.materialPicsType === e
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
              return item.content.toLowerCase().includes(e.toLowerCase()) && item.materialPicsType === this.queryParams.type
            } else {
              return item.content.toLowerCase().includes(e.toLowerCase())
            }
          })
        : this.tableData
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
      // 如果已经隐藏，则不执行
      if (item.newLabelFlag == '1') return
      // 消除new标志
      // const user = JSON.parse(localStorage.getItem('user'))
      this.$api.distributor
        .materialNew({
          materialId: item.materialId,
          userId: user?.userId,
          newLabelFlag: '0',
        })
        .then(res => {
          if (res.code === 200) {
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

  :deep .photo-card-item {
    width: 46%;
    margin: 0 8% 6% 0;

    &:nth-child(2n) {
      margin-right: 0;
    }
  }
}

.card-list-ar {
  :deep .photo-card-item {
    margin-left: 6%;
    margin-right: 0;

    &:nth-child(2n) {
      margin-left: 0;
    }
  }
}

@media only screen and (max-width: 1199px) {
  .card-list {
    :deep .photo-card-item {
      width: 100%;
      margin-right: 0;
    }
  }
  .card-list-ar {
    :deep .photo-card-item {
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
