<!--
 * Author: SHI
 * Date: 2022/6/25
 * Remark: multimedia
 -->

<template>
  <div v-loading="fullLoading" class="multimedia__page">
    <title-section :title="defaultText.title" :class-name="'multimedia-section'" :need-breadcrumb="false" :img-url="imgUrl" :image="image" />
    <div class="max-1200">
      <el-tabs v-model="activeName" class="media-el-table" @tab-click="handleClick">
        <el-tab-pane :label="defaultText.P" name="1"></el-tab-pane>
        <el-tab-pane :label="defaultText.V" name="2"></el-tab-pane>
        <el-tab-pane :label="defaultText.U" name="3"></el-tab-pane>
      </el-tabs>
      <!-- 用户手册 -->
      <div v-if="activeName === '3'" style="min-height: 650px">
        <el-row v-if="manualPageData.length" :gutter="12">
          <el-col v-for="(item, index) in manualPageData" :key="item.fileName" :span="6">
            <div class="user">
              <div class="img-video-box" @mouseenter="currentIndex = index" @mouseleave="currentIndex = null">
                <el-image :src="item.dirCover" :class="{ blur: index === currentIndex || mobile }" />
                <transition name="fade">
                  <ul v-if="index === currentIndex || mobile" class="text-wrap">
                    <li v-for="child in item.fileList" :key="child.fileName">
                      <a :href="child.fileUrl" class="title-item" target="_blank">
                        <span class="ellipsis1line title" :title="formatFileName(child.fileName)">{{ formatFileName(child.fileName) }}</span>
                        <i class="el-icon-arrow-right"></i>
                      </a>
                    </li>
                  </ul>
                </transition>
              </div>
              <span>{{ item.uploadTime.split(' ')[0] }}</span>
              <h3 class="ellipsis1line" :title="item.modelName">{{ item.modelName }}</h3>
            </div>
          </el-col>
        </el-row>
        <el-pagination style="text-align: center" background :current-page="userManual.pageNum" :page-size="userManual.pageSize" layout="prev, pager, next" @size-change="handleManualPageChange" @current-change="handleManualPageChange" :total="userManualList.length" />
      </div>

      <div v-else v-loading="loading" style="min-height: 650px">
        <el-row v-if="multimediaList.length" :gutter="12">
          <el-col v-for="item in multimediaList" :key="item.id" :span="6">
            <nuxt-link :to="localePath({ path: '/news/multimedias', query: { type: activeName, name: item.fileName } }, $i18n.locale)">
              <div class="img-video-box">
                <el-image :src="baseUrl + item.dirCover" />
              </div>
              <span>{{ item.uploadTime.split(' ')[0] }}</span>
              <h3 class="ellipsis1line" :title="item.fileName">{{ item.fileName }}</h3>
            </nuxt-link>
          </el-col>
        </el-row>
        <el-empty v-if="!multimediaList.length" :image-size="200"></el-empty>
        <el-pagination style="text-align: center" background :current-page="pageNum" :page-size="pageSize" layout="prev, pager, next" @size-change="handleSizeChange" @current-change="handleCurrentChange" :total="total" />
      </div>
    </div>
  </div>
</template>

<script>
import mix from '~/utils/Mix'

export default {
  name: 'NewsMultimedia',
  layout: 'main',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      fullLoading: false,
      loading: false,
      imgUrl: '',
      image: {
        url: '',
        alt: '',
        aspectRatio: 2.5,
      },
      activeName: '1',
      multimediaList: [],
      // 分页数据
      pageNum: 1, // 当前页数
      pageSize: 20, // 每页显示条目个数
      total: 0, // 总条目数
      dialogVisible: false, // 弹窗显示隐藏
      dialogSrc: '', // 弹窗视频源
      isAutoPlay: false, // 是否自动播放
      // 其他
      defaultText: {
        title: 'Download Center',
        P: 'Pictures',
        V: 'Videos',
        U: 'User Manual',
      },
      allLanguages: {
        en: {
          title: 'Download Center',
          P: 'Pictures',
          V: 'Videos',
          U: 'User Manual',
        },
        es: {
          title: 'Centro de descarga',
          P: 'Fotos',
          V: 'Vídeos',
          U: 'Manual de usuario',
        },
        ar: {
          title: 'مركز تحميل',
          P: 'الصور',
          V: 'أشرطة فيديو',
          U: 'دليل المستخدم',
        },
      },
      mobile: false,
      currentIndex: null,
      userManualList: [
        {
          modelName: 'BJ30',
          dirCover: require('~/assets/image/multimedia/BJ30.jpg'),
          fileList: [{ fileName: 'BJ30,EN', fileUrl: 'https://we.tl/t-FXyGDpaGcO' }],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'BJ40',
          dirCover: require('~/assets/image/multimedia/B40C 2.0蜂巢.jpg'),
          fileList: [
            { fileName: 'BJ40 C2.0,EN', fileUrl: 'https://we.tl/t-5w2MykSgPz' },
            { fileName: 'BJ40 C2.0,AR', fileUrl: 'https://we.tl/t-0Zmn1G3Lhd' },
            { fileName: 'BJ40 C2.0 RHD,EN', fileUrl: 'https://we.tl/t-ePXs8KZGL3' },
            { fileName: 'BJ40 Diesel,RU', fileUrl: 'https://we.tl/t-z71sjjL7ZN' }
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: this.$t('multimedia.BJ40Saudi'),
          dirCover: require('~/assets/image/multimedia/BJ40 C2.0右舵.jpg'),
          fileList: [
            { fileName: `BJ40 C2.0-${this.$t('multimedia.saudiVersion')},EN`, fileUrl: 'https://we.tl/t-73RZkeZio6' },
            { fileName: `${this.$t('multimedia.Saudi')} BJ40 C2.0,EN`, fileUrl: 'https://we.tl/t-6HW1V3goOb' },
            { fileName: `${this.$t('multimedia.Saudi')} BJ40 C2.0,AR`, fileUrl: 'https://we.tl/t-w5N0MOBmbi' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: this.$t('multimedia.BJ40SouthAfrican'),
          dirCover: require('~/assets/image/multimedia/B40C2.0-沙特版.jpg'),
          fileList: [{ fileName: `BJ40 C2.0-${this.$t('multimedia.SouthAfrican')},EN`, fileUrl: 'https://we.tl/t-abWJibL9Tv' }],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'BJ60',
          dirCover: require('~/assets/image/multimedia/BJ60.jpg'),
          fileList: [
            { fileName: 'BJ60,AR', fileUrl: 'https://we.tl/t-ltMsdGroTy' },
            { fileName: 'BJ60,EN', fileUrl: 'https://we.tl/t-xMPg9pHzuZ' },
            { fileName: 'BJ60,ES', fileUrl: 'https://we.tl/t-6QnirqgoGL' },
            { fileName: 'BJ60,RU', fileUrl: 'https://we.tl/t-oruXptsYwA' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'BJ80',
          dirCover: require('~/assets/image/multimedia/BJ80.jpg'),
          fileList: [
            { fileName: 'BJ80C 3.0T,EN', fileUrl: 'https://we.tl/t-gpEZnE8WIC' },
            { fileName: 'BJ80C 3.0T,AR', fileUrl: 'https://we.tl/t-L82qItmbnJ' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'EU5',
          dirCover: require('~/assets/image/multimedia/EU5.jpg'),
          fileList: [
            { fileName: 'EU5,AR', fileUrl: 'https://we.tl/t-VsnyI1RR07' },
            { fileName: 'EU5,ES', fileUrl: 'https://we.tl/t-jeeQKEyZLx' },
            { fileName: 'EU5 BSV,EN', fileUrl: 'https://we.tl/t-Gm4NVgCydY' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'EU5 PLUS',
          dirCover: require('~/assets/image/multimedia/EU5 PLUS.jpg'),
          fileList: [
            { fileName: 'EU5PLUS,AR', fileUrl: 'https://we.tl/t-BQLbZxaZg4' },
            { fileName: 'EU5PLUS,ES', fileUrl: 'https://we.tl/t-EdV6AKVNxU' },
            { fileName: 'EU5PLUS,EN', fileUrl: 'https://we.tl/t-DNp48Jkvko' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'U5PLUS',
          dirCover: require('~/assets/image/multimedia/U5 PLUS.jpg'),
          fileList: [
            { fileName: 'U5PLUS,AR', fileUrl: 'https://we.tl/t-fXedmQ1gTF' },
            { fileName: 'U5PLUS,ES', fileUrl: 'https://we.tl/t-WvpVra2oaD' },
            { fileName: 'U5PLUS,EN', fileUrl: 'https://we.tl/t-9VKfLBbSpM' },
            { fileName: 'U5PLUS,FR', fileUrl: 'https://we.tl/t-nhWeCbsYq2' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'ALL NEW X7',
          dirCover: require('~/assets/image/multimedia/ALL NEW X7.jpg'),
          fileList: [
            { fileName: `${this.$t('multimedia.ALLNewx7')},AR`, fileUrl: 'https://we.tl/t-rkDRn6XDI6' },
            { fileName: `${this.$t('multimedia.ALLNewx7')},ES`, fileUrl: 'https://we.tl/t-1xeOu3G0Kx' },
            { fileName: `${this.$t('multimedia.ALLNewx7')},EN`, fileUrl: 'https://we.tl/t-NZKxK2q6Jk' },
            { fileName: `${this.$t('multimedia.ALLNewx7')},FR`, fileUrl: 'https://we.tl/t-cMPJ8fNJFC' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'X35',
          dirCover: require('~/assets/image/multimedia/X35.jpg'),
          fileList: [
            { fileName: 'X35,AR', fileUrl: 'https://we.tl/t-xHxbuZqiM1' },
            { fileName: 'X35,ES', fileUrl: 'https://we.tl/t-GQSpglDDqL' },
            { fileName: 'X35,EN', fileUrl: 'https://we.tl/t-rLnaopCvgj' },
            { fileName: 'X35,FR', fileUrl: 'https://we.tl/t-poQGOjjLax' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: this.$t('multimedia.X35Saudi'),
          dirCover: require('~/assets/image/multimedia/X35-沙特版.jpg'),
          fileList: [
            { fileName: `X35-${this.$t('multimedia.saudiVersion')},EN`, fileUrl: 'https://we.tl/t-m0nkMBnENf' },
            { fileName: `X35-${this.$t('multimedia.saudiVersion')},AR`, fileUrl: 'https://we.tl/t-F8bEmjjYhp' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'X5',
          dirCover: require('~/assets/image/multimedia/X5.jpg'),
          fileList: [{ fileName: 'X5,FR', fileUrl: 'https://we.tl/t-XtmHvGu96Q' },],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'X55II',
          dirCover: require('~/assets/image/multimedia/X55II魔方.jpg'),
          fileList: [
            { fileName: 'X55II,AR', fileUrl: 'https://we.tl/t-kUpljgA0Or' },
            { fileName: 'X55II,ES', fileUrl: 'https://we.tl/t-EOMVY6TGdh' },
            { fileName: 'X55II,EN', fileUrl: 'https://we.tl/t-6S1kcMiOQi' },
            { fileName: 'X55II,FR', fileUrl: 'https://we.tl/t-kHxdvaS3Ko' },
          ],
          uploadTime: '2024-07-24 14:29:02',
        },
        {
          modelName: 'X55II RHD',
          dirCover: require('~/assets/image/multimedia/x5II魔方-右舵.jpg'),
          fileList: [{ fileName: 'X55II RHD,EN', fileUrl: 'https://we.tl/t-vDNo8p4csG' }],
          uploadTime: '2024-07-24 14:29:02',
        },
      ],
      userManual: {
        pageNum: 1,
        pageSize: 20,
      },
    }
  },
  computed: {
    manualPageData() {
      const { pageNum, pageSize } = this.userManual
      // 数据分页
      return this.userManualList.slice((pageNum - 1) * pageSize, pageNum * pageSize)
    },
  },
  watch: {
    $mq: {
      immediate: true,
      handler(value) {
        console.log(value, '当前尺寸')
        if (value === 'xs') {
          this.mobile = true
        } else {
          this.mobile = false
        }
      },
    },
  },
  created() {
    this.fullLoading = true
    this.$api.home.getBanner({ type: 4 }).then(res => {
      this.imgUrl = this.image.url = res.data[0]?.imageUrl
      this.image.aspectRatio = parseFloat(res.data[0]?.aspectRatio) + '%'
    })
    this.getPage()
  },
  mixins: [mix],
  methods: {
    handleClick(tab, event) {
      this.pageNum = 1
      this.getPage()
    },
    getPage() {
      this.loading = true
      const params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        filePath: this.activeName === '1' ? '/Pictures/' : this.activeName === '2' ? '/Videos/' : '/Null/',
      }
      this.multimediaList = []
      this.$api.news
        .getMultimediaList(params)
        .then(res => {
          this.multimediaList = res.data.list
          this.total = res.data.total
        })
        .catch(err => {
          this.$message.error(err.msg || 'Request failed')
        })
        .finally(() => {
          this.fullLoading = false
          this.loading = false
        })
    },
    // 分页
    handleSizeChange(val) {
      this.pageSize = val
      this.pageNum = 1
      this.getPage()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.getPage()
    },
    playVideo(src) {
      this.dialogVisible = true
      this.dialogSrc = src
      this.isAutoPlay = true
    },
    handleDialogClose() {
      this.isAutoPlay = false
      this.dialogSrc = ''
      this.dialogVisible = false
    },
    downloadFile(path, name) {
      const xhr = new XMLHttpRequest()
      xhr.open('get', path)
      xhr.responseType = 'blob'
      xhr.send()
      xhr.onload = function () {
        if (this.status === 200 || this.status === 304) {
          // 如果是IE10及以上，不支持download属性，采用msSaveOrOpenBlob方法，但是IE10以下也不支持msSaveOrOpenBlob
          if ('msSaveOrOpenBlob' in navigator) {
            navigator.msSaveOrOpenBlob(this.response, name)
            return
          }
          // const blob = new Blob([this.response], { type: xhr.getResponseHeader('Content-Type') });
          // const url = URL.createObjectURL(blob);
          const url = URL.createObjectURL(this.response)
          const a = document.createElement('a')
          a.style.display = 'none'
          a.href = url
          a.download = name
          document.body.appendChild(a)
          a.click()
          document.body.removeChild(a)
          URL.revokeObjectURL(url)
        }
      }
      xhr.onerror = function (e) {}
    },
    // 用户手册分页
    handleManualSizeChange(val) {
      this.userManual.pageSize = val
    },
    // 用户手册当前页码改变
    handleManualPageChange(val) {
      this.userManual.pageNum = val
    },
    formatFileName(value) {
      if (!value) return ''
      value = value.toString()
      const arr = value.split(',')
      return `${arr[0]}-${this.$t('multimedia.userManual')}-${arr[1]}`
    },
  },
}
</script>

<style lang="scss">
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}
.fade-enter,
.fade-leave-active {
  opacity: 0;
}
.multimedia__page {
  min-height: 100vh;
  margin-bottom: 60px;
  .multimedia-section {
    .h1 {
      text-align: center;
      font-size: calc($body-text-size * 2);
    }
  }

  .el-tabs {
    text-align: center;

    .el-tabs__item {
      font-size: calc($body-text-size * 1.3);

      &.is-active {
        color: #000;
      }
    }

    .el-tabs__active-bar {
      height: 3px;
      background-color: #d1291a;
    }

    .el-tabs__nav-scroll {
      display: flex;
      justify-content: center;
    }

    .el-tabs__nav-wrap::after {
      height: 0;
    }
  }

  .el-row {
    margin-top: 64px;

    .el-col {
      margin-bottom: 72px;

      .img-video-box {
        position: relative;
        //width         : 290px;
        height: 160px;
        margin-bottom: 13px;
        overflow: hidden;
        display: flex;
        justify-content: center;
        align-items: center;

        &:hover {
          .video-mask {
            display: block;
          }
        }

        > .icon-group {
          position: absolute;
          right: 9px;
          top: 7px;
          z-index: 9;

          > div,
          > a {
            display: block;
            width: 26px;
            height: 26px;
            background: #f8f8f8;
            border-radius: 50%;
            cursor: pointer;
            text-align: center;
            line-height: 26px;
            margin-top: 7px;

            > svg {
              width: 12px;
              height: 12px;
            }
          }
        }

        > .video-mask {
          display: none;
          width: 100%;
          height: 100%;
          position: absolute;
          right: 0;
          top: 0;
          z-index: 7;
          background: rgba(255, 255, 255, 0.5);
          text-align: center;
          line-height: 190px;
        }

        img {
          width: 100%;
          height: 100%;
          object-fit: scale-down;
        }

        video {
          width: 100%;
          height: 100%;
        }
      }

      .user {
        .blur {
          img {
            filter: blur(5px);
            transition: all 0.3s;
          }
        }
        .text-wrap {
          position: absolute;
          width: 100%;
          height: 100%;
          background: rgba(0, 0, 0, 0.5);
          padding: 8px 18px;
          .title-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            color: #fff;
            .title {
              font-size: 14px;
              line-height: 29px;
            }
            .el-icon-arrow-right {
              font-size: 18px;
            }
          }
        }
      }

      span {
        font-size: 12px;
        line-height: 28px;
      }

      h3 {
        font-size: 16px;
        line-height: 28px;
      }
    }
  }
}
.xs,
.sm,
.md {
  // .image-container {
  //   margin-bottom: 30px !important;
  // }
  .multimedia__page {
    .multimedia-section {
      .h1 {
        text-align: center;
        font-size: calc($body-text-size * 2);
        margin-bottom: 15px;
      }
    }

    .el-tabs {
      text-align: center;

      .el-tabs__item {
        font-size: calc($body-text-size * 1.3);

        &.is-active {
          color: #000;
        }
      }

      .el-tabs__active-bar {
        height: 3px;
        background-color: #d1291a;
      }

      .el-tabs__nav-scroll {
        display: flex;
        justify-content: center;
      }

      .el-tabs__nav-wrap::after {
        height: 0;
      }
    }

    .el-row {
      margin-top: 30px;

      .el-col {
        margin-bottom: 25px;

        .img-video-box {
          position: relative;
          //width         : 290px;
          height: 160px;
          margin-bottom: 13px;
          overflow: hidden;
          display: flex;
          justify-content: center;
          align-items: center;

          &:hover {
            .video-mask {
              display: block;
            }
          }

          > .icon-group {
            position: absolute;
            right: 9px;
            top: 7px;
            z-index: 9;

            > div,
            > a {
              display: block;
              width: 26px;
              height: 26px;
              background: #f8f8f8;
              border-radius: 50%;
              cursor: pointer;
              text-align: center;
              line-height: 26px;
              margin-top: 7px;

              > svg {
                width: 12px;
                height: 12px;
              }
            }
          }

          > .video-mask {
            display: none;
            width: 100%;
            height: 100%;
            position: absolute;
            right: 0;
            top: 0;
            z-index: 7;
            background: rgba(255, 255, 255, 0.5);
            text-align: center;
            line-height: 190px;
          }

          img {
            width: 100%;
            height: 100%;
            object-fit: scale-down;
          }

          video {
            width: 100%;
            height: 100%;
          }
        }

        span {
          font-size: 12px;
          line-height: 28px;
        }

        h3 {
          font-size: 16px;
          line-height: 28px;
        }
      }
    }
  }
}
.xs {
  .user {
    .text-wrap {
      padding: 28px 18px !important;
      .title-item {
        .title {
          line-height: 40px !important;
        }
      }
    }
  }
}
</style>
