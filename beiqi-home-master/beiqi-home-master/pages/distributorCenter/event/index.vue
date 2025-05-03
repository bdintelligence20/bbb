<template>
  <div v-show="isVisible">
    <!--    banner-->
    <distributorCenterBanner :key="bannerKey" :image-list="imageList" />

    <div class="max-1680">
      <!--      面包屑-->
      <DistributorCenter-breadcumb />

      <!--      查询-->
      <el-row :class="$i18n.locale == 'ar' ? 'row-ar' : ''" :gutter="15" class="queryForm">
        <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
          <SelectVue :key="typeKey" :options="typeOptions" :placeholder="$t('distributor.picsType')" @changeValue="changeType"></SelectVue>
        </el-col>
        <el-col :lg="12" :md="12" :sm="12" :xl="12" :xs="12">
          <el-input v-model="queryParams.keyWord" :placeholder="$t('distributor.content')" suffix-icon="el-icon-search" @change="handleChange"> </el-input>
        </el-col>
      </el-row>

      <!--列表-->
      <div v-if="selectTableData.length > 0" :class="{ 'card-list-ar': langAr }" class="card-list">
        <distributorCenterCardListItem v-for="item in selectTableData" :key="item.materialId" :item="item" class="event-card-item" @openPreview="openPreview" @closePreview="closePreview" @handleDownload="handleDownload" />
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
  name: 'DistributorEvent',
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
      baseUrl: process.env.imgBaseUrl,
      typeOptions: [],
      queryParams: {
        type: '',
        keyWord: '',
      },
      typeKey: 0,
      bannerKey: 0,
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
    }
  },

  computed: {
    langAr() {
      return this.$i18n.locale === 'ar'
    },
  },

  mounted() {
    this.isVisible = checkToken()
  },

  activated() {
    this.bannerKey += 1
    this.selectTableData = []
    this.queryParams = {
      type: '',
      keyWord: '',
    }
    this.typeKey++
    this.getmaterialList()
    this.isVisible = checkToken()
  },

  methods: {
    // 获取列表数据
    getmaterialList() {
      this.loading = true
      const user = getStorage('user')
      const params = {
        type: 'Event',
        userId: user?.userId,
      }
      const typeOptions = [{ name: 'all', value: '' }]
      this.$api.distributor.materialList(params).then(res => {
        if (res.code === 200) {
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
            // 如果有null或空则不保存
            if (item.viewMore) {
              item.viewMore = item.viewMore.split(',')
            } else {
              item.viewMore = []
            }
            item.showPreview = false
            return item
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
      if (item.newLabelFlag === '1') return
      // 消除new接口
      this.$api.distributor
        .materialNew({
          materialId: item.materialId,
          userId: user?.userId,
          newLabelFlag: '0',
        })
        .then(res => {
          if (res.code === 200) {
            this.getmaterialList()
          }
        })
    },
    // 预览更多
    openPreview(id) {
      const index = this.selectTableData.findIndex(obj => obj.materialId === id)
      this.selectTableData[index].showPreview = true
    },
    // 关闭预览
    closePreview(id) {
      const index = this.selectTableData.findIndex(obj => obj.materialId === id)
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

  :deep .event-card-item {
    width: 46%;
    margin: 0 8% 6% 0;

    &:nth-child(2n) {
      margin-right: 0;
    }
  }
}

.card-list-ar {
  :deep .event-card-item {
    margin-left: 6%;
    margin-right: 0;

    &:nth-child(2n) {
      margin-left: 0;
    }
  }
}

@media only screen and (max-width: 1199px) {
  .card-list {
    :deep .event-card-item {
      width: 100%;
      margin-right: 0;
    }
  }
  .card-list-ar {
    :deep .event-card-item {
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
