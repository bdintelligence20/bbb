<!--
 * Author: SHI
 * Date: 2022/6/25
 * Remark: multimedia
 -->

<template>
  <div class="multimedia__page" v-loading="fullLoading">
    <title-section :title="fileName" :class-name="'multimedia-section'" :need-breadcrumb="false" :img-url="imgUrl" :image="image" />
    <div class="max-1200">
      <div style="min-height: 650px" v-loading="loading">
        <el-row :gutter="12" v-if="multimediaList.length">
          <el-col :span="6" v-for="item in multimediaList" :key="item.id">
            <div class="img-video-box">
              <div class="icon-group">
                <div v-if="type === `1`" @click="downloadFile(baseUrl + item.fileUrl, item.fileName)">
                  <svg t="1656131705327" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2763" width="128" height="128"><path d="M166.2976 723.3536v67.24266667a34.13333333 34.13333333 0 0 0 34.13333333 34.13333333h623.13813334a34.13333333 34.13333333 0 0 0 34.13333333-34.13333333v-67.24266667a49.01546667 49.01546667 0 1 1 98.03093333 0v128.8192a68.26666667 68.26666667 0 0 1-68.26666666 68.26666667H135.03146667a68.26666667 68.26666667 0 0 1-68.26666667-68.8128l1.09226667-128.27306667a49.42506667 49.42506667 0 0 1 49.42506666-49.01546667c27.10186667 0 49.01546667 21.9136 49.01546667 49.01546667z m482.64533333-270.67733333H559.9232V151.48373333a48.9472 48.9472 0 1 0-97.96266667 0v301.2608H372.736a13.65333333 13.65333333 0 0 0-11.60533333 20.95786667l134.69013333 213.53813333a18.29546667 18.29546667 0 0 0 30.99306667 0l133.80266666-213.67466666a13.65333333 13.65333333 0 0 0-11.60533333-20.8896z" p-id="2764"></path></svg>
                </div>
                <a href="https://twitter.com/baicglobal" target="_blank">
                  <svg width="25px" height="25px" viewBox="0 0 25 25" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                    <g id="Homepage" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                      <g id="find-your-country" transform="translate(-1880.000000, -383.000000)" fill-rule="nonzero">
                        <g transform="translate(1865.000000, 368.000000)">
                          <g id="instagram-fill" transform="translate(15.000000, 15.000000)">
                            <path d="M12.9807692,8.65384615 C10.5982024,8.65384615 8.65384615,10.5982024 8.65384615,12.9807692 C8.65384615,15.363336 10.5982024,17.3076923 12.9807692,17.3076923 C15.363336,17.3076923 17.3076923,15.363336 17.3076923,12.9807692 C17.3076923,10.5982024 15.363336,8.65384615 12.9807692,8.65384615 Z" id="路径" fill="#2C2C2C"></path>
                            <path
                              d="M24.9986045,12.5 C24.9986045,10.7738337 25.0142397,9.06330303 24.917301,7.34026388 C24.8203623,5.33891169 24.3638124,3.56271161 22.9003508,2.09922282 C21.4337622,0.63260692 19.6607223,0.179175564 17.6594073,0.0822350675 C15.9332732,-0.0147054293 14.2227744,0.000930134682 12.4997673,0.000930134682 C10.7736332,0.000930134682 9.0631343,-0.0147054293 7.34012722,0.0822350675 C5.33881229,0.179175564 3.56264529,0.635734033 2.09918374,2.09922282 C0.632595143,3.56583873 0.179172229,5.33891169 0.0822335365,7.34026388 C-0.0147051556,9.06643014 0.000930117366,10.7769608 0.000930117366,12.5 C0.000930117366,14.2230392 -0.0147051556,15.936697 0.0822335365,17.6597361 C0.179172229,19.6610883 0.635722198,21.4372884 2.09918374,22.9007772 C3.56577234,24.3673931 5.33881229,24.8208244 7.34012722,24.9177649 C9.06626135,25.0147054 10.7767602,24.9990699 12.4997673,24.9990699 C14.2259014,24.9990699 15.9364003,25.0147054 17.6594073,24.9177649 C19.6607223,24.8208244 21.4368893,24.364266 22.9003508,22.9007772 C24.3669394,21.4341613 24.8203623,19.6610883 24.917301,17.6597361 C25.0173668,15.936697 24.9986045,14.2261663 24.9986045,12.5 Z M12.4966402,18.9137084 C8.94743328,18.9137084 6.08305128,16.049273 6.08305128,12.5 C6.08305128,8.95072697 8.94743328,6.08629165 12.4966402,6.08629165 C16.0458472,6.08629165 18.9102292,8.95072697 18.9102292,12.5 C18.9102292,16.049273 16.0458472,18.9137084 12.4966402,18.9137084 Z M19.1729018,7.3215012 C18.3442323,7.3215012 17.6750426,6.65229906 17.6750426,5.82361417 C17.6750426,4.99492928 18.3442323,4.32572714 19.1729018,4.32572714 C20.0015712,4.32572714 20.6707609,4.99492928 20.6707609,5.82361417 C20.6676339,6.65542618 20.0015712,7.3215012 19.1729018,7.3215012 Z"
                              id="形状"
                              fill="#000000"
                            ></path>
                          </g>
                        </g>
                      </g>
                    </g>
                  </svg>
                </a>
                <a href="https://www.facebook.com/baicglobal/?ref=pages_you_manage" target="_blank">
                  <svg width="13px" height="25px" viewBox="0 0 13 25" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                    <g id="Homepage" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                      <g id="find-your-country" transform="translate(-1886.000000, -439.000000)" fill="#000000" fill-rule="nonzero">
                        <g id="侧边栏" transform="translate(1865.000000, 368.000000)"><path d="M33.5,71.1802885 L33.5,75.1466346 L31.228588,75.1466346 C30.3991127,75.1466346 29.8396991,75.3269231 29.5503472,75.6875 C29.2609953,76.0480769 29.1163194,76.5889423 29.1163194,77.3100962 L29.1163194,80.1496394 L33.3553241,80.1496394 L32.791088,84.5967548 L29.1163194,84.5967548 L29.1163194,96 L24.6892361,96 L24.6892361,84.5967548 L21,84.5967548 L21,80.1496394 L24.6892361,80.1496394 L24.6892361,76.874399 C24.6892361,75.0114183 25.1907793,73.5666066 26.1938657,72.539964 C27.1969522,71.5133213 28.5327932,71 30.2013889,71 C31.619213,71 32.71875,71.0600962 33.5,71.1802885 Z" id="路径"></path></g>
                      </g>
                    </g>
                  </svg>
                </a>
              </div>
              <el-image v-if="type === `1`" :src="baseUrl + item.fileUrl" :preview-src-list="srcList" />
              <video controls muted :src="baseUrl + item.fileUrl" v-if="type === `2`">Sorry, your browser doesn't support embedded videos.</video>
            </div>
            <span>{{ item.uploadTime.split(' ')[0] }}</span>
            <h3 class="ellipsis1line" :title="item.fileName">{{ item.fileName }}</h3>
          </el-col>
        </el-row>
        <el-empty :image-size="200" v-if="!multimediaList.length"></el-empty>
        <el-pagination style="text-align: center" background :current-page="pageNum" :page-size="pageSize" layout="prev, pager, next" @size-change="handleSizeChange" @current-change="handleCurrentChange" :total="total" />
      </div>
    </div>
    <el-dialog width="1440px" destroy-on-close :close-on-click-modal="false" :visible.sync="dialogVisible" :before-close="handleDialogClose">
      <video style="width: 100%; height: 100%" :autoplay="isAutoPlay" :src="dialogSrc">Sorry, your browser doesn't support embedded videos.</video>
    </el-dialog>
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
      type: '1', //类型,1是图片,2是视频
      fileName:'', //文件夹名
      multimediaList: [],
      srcList: [], // 图片预览列表
      // 分页数据
      pageNum: 1, // 当前页数
      pageSize: 20, // 每页显示条目个数
      total: 0, // 总条目数
      dialogVisible: false, // 弹窗显示隐藏
      dialogSrc: '', // 弹窗视频源
      isAutoPlay: false, // 是否自动播放
      // 其他
      defaultText: {
        title: 'Multimedia',
        P: 'Pictures',
        V: 'Videos',
      },
      allLanguages: {
        en: {
          title: 'Multimedia',
          P: 'Pictures',
          V: 'Videos',
        },
        es: {
          title: 'Multimedia',
          P: 'Fotos',
          V: 'Vídeos',
        },
        ar: {
          title: 'الوسائط المتعددة',
          P: 'الصور',
          V: 'أشرطة فيديو',
        },
      },
    }
  },
  mounted(){
    this.fullLoading = true
    this.$api.home.getBanner({ type: 4 }).then(res => {
      this.imgUrl = this.image.url = res.data[0]?.imageUrl
      this.image.aspectRatio = parseFloat(res.data[0]?.aspectRatio) + '%'
    })
  },
  activated() {
    const { type, name } = this.$route.query
    this.type = type
    this.fileName = name
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
        filePath: this.type === '1' ? `/Pictures/${this.fileName}/` : this.type === '2' ? `/Videos/${this.fileName}/` : '/Null',
      }
      this.multimediaList = []
      this.$api.news
        .getMultimediaList(params)
        .then(res => {
          console.log(res)
          this.multimediaList = res.data.list
          this.total = res.data.total
          if (this.type === '1') {
            this.srcList = res.data.list.map(item => this.baseUrl + item.fileUrl)
          } else {
            this.srcList = []
          }
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
  },
}
</script>

<style lang="scss">
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
</style>
