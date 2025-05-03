<template>
  <div v-show="isVisible">
    <distributorCenterBanner :key="bannerKey" :image-list="imageList"/>
    <div class="max-1680">
      <DistributorCenter-breadcumb/>
      <div class="theme-title">
        <h6>{{ $t('distributor.resource') }}</h6>
      </div>
      <client-only placeholder="Loading...">
        <mq-layout :mq="['xl','lg']">
          <el-table
            key="tableKey"
            ref="table"
            v-loading="loading"
            :data="tableData"
            :span-method="handleSpanMethod"
            border
            header-row-class-name="header">
            <el-table-column
              v-for="item in columnData"
              v-show="!item.hide"
              :key="item.materialId"
              :align="item.align"
              :class-name="item.className"
              :label="item.label"
              :min-width="item.minWidth"
              :prop="item.prop"
              :width="item.width"
            >
              <template slot-scope="scope">
                <!-- && scope.row?.newLabelFlag!='1'-->
<!--                <div v-if="item.className == 'icon-column' && scope.row?.newLabelFlag!='1'" class="icon-text">-->
<!--                  {{ $t('distributor.NEW') }}-->
<!--                </div>-->
                <div v-if="item.type === 'file'">
                  <span>{{ scope.row[item.prop] }}</span>
                  <div v-if="scope.row.newLabelFlag != 1" class="new-icon"> {{ $t('distributor.NEW') }} </div>
                </div>
                <div v-else-if="item.className === 'download'" @click="handleMaterialNew(scope.row)">
                  <a :href="scope.row[item.prop]" target="_blank">
<!--                    <i class="iconfont icon-xiazai"></i>-->
                    <i class="icon-download"></i>
                  </a>
                </div>
                <div v-else>
                  {{ scope.row[item.prop] }}
                </div>
              </template>
            </el-table-column>
          </el-table>
        </mq-layout>
      </client-only>

      <mq-layout :mq="['md','xs','sm']">
        <el-table
          ref="table"
          v-loading="loading"
          :data="tableData"
          :span-method="handleSpanMethod"
          border
          header-row-class-name="header">

          <el-table-column
            v-for="item in columnDataWap"
            :key="item.materialId"
            :align="item.align"
            :class-name="item.className"
            :label="item.label"
            :prop="item.prop"
            :width="item.width"
          >
            <template slot-scope="scope">
<!--              <div v-if="item.className == 'icon-column' && scope.row?.newLabelFlag!='1'" class="icon-text">-->
<!--                {{ $t('distributor.NEW') }}-->
<!--              </div>-->
              <div v-if="item.type === 'file'" class="content">
                <span>{{ scope.row[item.prop] }}</span>
<!--                <span v-if="scope.row?.newLabelFlag!='1'" class="circle"></span>-->
<!--                {{scope.row.newLabelFlag}}-->
                <div v-if="scope.row.newLabelFlag != 1" class="new-icon"> {{ $t('distributor.NEW') }} </div>
              </div>
              <div v-else-if="item.className === 'download'" @click="handleMaterialNew(scope.row)">
                <a :href="scope.row[item.prop]" target="_blank">
                  <i class="icon-download"></i>
                </a>
              </div>
              <div v-else>
                {{ scope.row[item.prop] }}
              </div>
            </template>
          </el-table-column>
        </el-table>
      </mq-layout>
    </div>
  </div>
</template>

<script>

import {checkToken} from "~/middleware/auth"
import {getStorage} from "~/utils/storage"

export default {
  name: 'DistributorBrand',
  layout: 'main',
  middleware: 'auth',
  meta: {
    requireAuth: true
  },
  data() {
    return {
      tableKey: 0,
      isVisible: false,
      isPc: true,
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
      tableData: [],
      columnData: [
        // {minWidth: '6', className: 'icon-column', hide: false, width: '100'},
        // triangle-down
        {label: this.$t('distributor.fileType'), prop: "fileType", minWidth: '25.3', className: 'type-column'},
        {
          label: this.$t('distributor.fileName'),
          prop: "fileName",
          minWidth: '54.3',
          type: 'file',
          className: 'name-column'
        },
        {
          label: this.$t('distributor.format'),
          prop: "format",
          minWidth: '23',
          align: 'center'
        },
        {
          label: this.$t('distributor.downloadLink'),
          prop: "downloadLink",
          minWidth: '23',
          className: 'download',
          align: 'center'
        }
      ],
      columnDataWap: [
        {label: this.$t('distributor.fileType'), prop: "fileType", className: 'type-column', width: 100},
        {label: this.$t('distributor.fileName'), prop: "fileName", type: 'file', className: 'name-column'},
        {
          label: this.$t('distributor.downloadLink'),
          prop: "downloadLink",
          className: 'download',
          align: 'center',
          width: 120
        }
      ],
      indexArray: [],
      mergeArr: [],
      mergeObj: {},
      spanArr: [],
      loading: false,
      bannerKey: 0
    }
  },

  mounted() {
    checkToken()
    this.isVisible = checkToken()
    this.getmaterialList()
  },
  activated() {
    this.bannerKey += 1
    this.isVisible = checkToken()
  },

  methods: {
    // 点击下载素材后,消除new标
    handleMaterialNew(params) {
      const user = getStorage('user')
      const data = {
        type: 'BasicBtand',
        downloadLink: params.downloadLink,
        userId: user?.userId,
      }
      // 记录日志
      this.$api.distributor.recordDownloadLog(data)
      if (params.newLabelFlag == "1") return
      // 消除new接口
      // const user = JSON.parse(localStorage.getItem('user'))
      this.$api.distributor.materialNew({
        materialId: params.materialId,
        userId: user?.userId,
        // 0显示 1隐藏
        newLabelFlag: "0"
      }).then(async res => {
        if (res.code === 200) {
          await this.getmaterialList()
          this.tableKey++
        }
      })
    },

    // 合并移动端的单元格
    // handleWapSpanMethod({row, column, rowIndex, columnIndex}) {
    //   if (columnIndex == 0) {
    //     const row = this.spanArr[rowIndex]  // 合并行数
    //     return {
    //       rowspan: row,
    //       colspan: 1
    //     }
    //   }
    // },

    // 合并pc端的单元格
    handleSpanMethod({row, column, rowIndex, columnIndex}) {
      if (columnIndex == 0) {
        const row = this.spanArr[rowIndex]  // 合并行数
        return {
          rowspan: row,
          colspan: 1
        }
      }
    },

    // 获取素材列表
    async getmaterialList() {
      // const user = JSON.parse(localStorage.getItem('user'))
      const user = getStorage('user')
      const params = {
        type: 'BasicBtand',
        userId: user?.userId
      }
      this.loading = true
      this.$api.distributor.materialList(params).then(res => {
        if (res.code == 200) {
          this.loading = false
          this.tableData = res.data
          // 将相同fileType放一起
          const keys = [] // 唯一值的数组
          this.tableData.forEach((item, index) => {
            if (!keys.includes(item.fileType)) {
              keys.push(item.fileType)
            }
          })

          const temp = []
          keys.forEach((item) => {  // 将同一名称的数据放在相邻位置
            this.tableData.forEach((cell) => {
              if (item === cell.fileType) {
                temp.push(cell)
              }
            })
          })

          this.tableData = temp
          this.getSpan(this.tableData)
        } else {
          this.loading = false
        }
      })
    },

    // 合并的行数
    getSpan(data) {
      let position  // 当前合并的行位置（连续相同名称的第一条数据位置）
      this.spanArr = []
      data.forEach((item, index) => {
        if (index === 0) {  // 第一行， 不进行处理
          this.spanArr.push(1)
          position = 0
        } else if (data[index].fileType === data[index - 1].fileType) {
            // 当条数据跟上一条数据名称相同，要合并
            this.spanArr[position] += 1  // 首条相同名称行合并行数增加
            this.spanArr.push(0) // 当前行消除
          } else { // 不需要处理的数据
            this.spanArr.push(1)
            position = index
          }
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.theme-title {
  margin: 49px auto 44px;
  text-align: left;
  width: 87%;
  h6 {
    font-size: 30px;
    font-weight: 400;
  }
}

:deep .el-table {
  width: 87%;
  margin: 0 auto;
  border: none;

  margin-bottom: 50px;

  .el-table__body-wrapper {
    border-left: 1px solid #ebeef5;
    border-right: 1px solid #ebeef5;
  }

  //头部
  .header {
    .cell {
      font-size: 14px;
      color: #5e5e5e;
      word-break: normal;
    }

    .el-table__cell {
      border-right: none;
    }
  }

  .el-table__row {
    font-size: 16px;

    &:hover {
      .icon-column {
        background: none;
      }
    }
  }

  .el-table__cell {
    letter-spacing: .1px;

    .cell {
      transform: scaleY(0.9);
    }
  }

  //.icon-column {
  //  border-bottom: none !important;
  //
  //  .cell {
  //    font-size: 12px;
  //    line-height: 1;
  //
  //    .icon-text {
  //      display: inline-block;
  //      padding: 5px 10px;
  //      margin: 0 12px;
  //      background: #d12519;
  //      color: #fff;
  //      border-radius: 18px 18px 0 18px;
  //      text-align: center;
  //    }
  //  }
  //}

  .new-icon {
    display: inline-block;
    padding: 0 6px;
    margin: 0 12px;
    background: #d12519;
    color: #fff;
    border-radius: 18px 18px 18px 0;
    text-align: center;
    font-size: 12px;
  }

  .circle {
    display: inline-block;
    width: 12px;
    height: 12px;
    margin-left: 12px;
    background-color: #d12519;
    border-radius: 50%;
    transform: scale(1);
  }

  .icon-xiazai {
    font-size: 30px;
  }

  .download {
    .cell {
      display: flex;
      justify-content: center;
      transform: scale(1);
    }
  }

  .icon-download {
    display: block;
    width: 46px;
    height: 28px;
    background: url('~/assets/image/icon/download.png') no-repeat;
    background-position-y: 50%;
    background-size: 80%;
    cursor: pointer;
  }

  .triangle-down {
    display: inline-block;
    width: 0;
    height: 0;
    border-left: 6px solid transparent;
    border-right: 6px solid transparent;
    border-top: 15px solid #5e5e5e;
    margin-left: 6px;
  }

  &:before,
  &:after {
    display: none;
  }
}

@media only screen and (max-width: 1199px) {
  .theme-title {
    margin: 24px 0;
    h6 {
      font-size: 20px;
    }
  }
  .el-table {
    width: 100%;
    margin-bottom: 0;
  }
  :deep .el-table {
    .type-column {
      border-left: 1px solid #EBEEF5;
    }

    .new-icon {
      font-size: 10px;
      padding: 0px 5px;
    }

    .name-column {
      .circle {
        width: 10px;
        height: 10px;
      }
    }

    .icon-column {
      display: none;
    }

    .header {
      .type-column {
        border-left: none;
      }
    }

    .el-table__cell {
      .cell {
        padding-left: 5px;
      }
    }

    .circle {
      margin-left: 0;
    }
  }
}
</style>
