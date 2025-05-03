<template>
  <div class="news__page" v-loading="fullLoading">
    <title-section :img-url="imgUrl" :image="image" />
    <div class="content-section max-1200" v-loading="loading">
      <div class="content-section__release" v-if="!isMediaReport">
        <h1>{{ $t('news.release') }}</h1>
        <el-row :gutter="44">
          <el-col :span="8" v-for="item in recommendList" :key="item.id">
            <nuxt-link :to="localePath({ name: 'news-newsRelease-detail-id', params: { id: item.id } })">
              <img :src="baseUrl + item.imageUrl" alt="" srcset="" />
            </nuxt-link>
            <span style="min-height: 18px">{{ item.publishTime ? item.publishTime.split('T')[0] : '' }}</span>
            <nuxt-link :to="localePath({ name: 'news-newsRelease-detail-id', params: { id: item.id } })">
              <h3 class="ellipsis2line text-uppercase" :title="item.title">{{ item.title }}</h3>
              <p class="ellipsis2line" :title="item.summarize">{{ item.summarize }}</p>
            </nuxt-link>
            <nuxt-link :to="localePath({ name: 'news-newsRelease-detail-id', params: { id: item.id } })">
              <el-button class="border-el-button">{{ $t('news.readMore') }}</el-button>
            </nuxt-link>
          </el-col>
        </el-row>
      </div>
      <div class="content-section__others" v-if="!isMediaReport">
        <h1 v-if="newsList.length">{{ $t('news.otherNews') }}</h1>
        <NewsItemRow v-for="item in newsList" :key="item.id" :item="item" />
        <el-empty :image-size="200" v-if="!newsList.length"></el-empty>
      </div>
      <div class="content-section__media" v-if="isMediaReport">
        <h1 style="margin-bottom: 70px">{{ $t('news.mediaReport') }}</h1>
        <el-divider />
        <ul v-if="newsList.length">
          <li v-for="item in newsList" :key="item.id" class="item-content-column">
            <el-row :gutter="10">
              <el-col :span="3">{{ item.createTime ? item.createTime.split('T')[0] : '' }}</el-col>
              <el-col :span="21">
                <div>
                  <div>
                    <h3 class="ellipsis2line" :title="item.title">{{ item.title }}</h3>
                    <p class="ellipsis2line" :title="item.summarize">{{ item.summarize }}</p>
                  </div>
                  <div>
                    <img :src="baseUrl + item.imageUrl" alt="" srcset="" />
                  </div>
                </div>
                <el-link>
                  <nuxt-link :to="localePath({ name: 'news-mediaReport-detail-id', params: { id: item.id } })">
                    {{ $t('news.readMore') }}
                  </nuxt-link>
                </el-link>
              </el-col>
            </el-row>
            <el-divider />
          </li>
        </ul>
        <el-empty :image-size="200" v-if="!newsList.length"></el-empty>
      </div>
      <el-pagination style="text-align: center" background :current-page="pageNum" :page-size="pageSize" layout="prev, pager, next" @size-change="handleSizeChange" @current-change="handleCurrentChange" :total="total" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'NewsRelease',
  layout: 'main',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      fullLoading: false,
      loading: false,
      // 组件参数
      title: '',
      imgUrl: '',
      image: {
        url: '',
        alt: '',
        aspectRatio: 2.5,
      },
      // 数据参数
      recommendList: [],
      newsList: [],
      // 分页数据
      pageNum: 1, // 当前页数
      pageSize: 10, // 每页显示条目个数
      total: 0, // 总条目数
      isMediaReport: false,
    }
  },
  created() {
    this.fullLoading = true
    this.$api.home.getBanner({ type: 1 }).then((res) => {
      this.imgUrl = this.image.url = res.data[0]?.imageUrl
      this.image.aspectRatio = parseFloat(res.data[0]?.aspectRatio) + '%'
    })
    if (!this.isMediaReport) {
      this.getPage({ recommend: 1 })
    }
    this.getPage({ recommend: 0 })
  },
  methods: {
    getPage(obj) {
      this.loading = true
      let params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        categoryId: this.$route.query.id,
      }
      Object.assign(params, obj)
      this.$api.news
        .getNewsList(params)
        .then((res) => {
          if (params.recommend === 1) {
            this.recommendList = res.rows
          } else {
            this.newsList = res.rows
            this.total = res.total
          }
        })
        .catch((err) => {
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
      this.getPage({ recommend: 0 })
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.getPage({ recommend: 0 })
    },
  },
}
</script>
<style lang="scss">
.news__page {
  min-height: 100vh;
  margin-bottom: 60px;
  .content-section {
    .el-link {
      font-size: 18px;
      line-height: 40px;
      align-self: center;
      color: #000;

      &.is-underline {
        &::after {
          content: '';
          position: absolute;
          left: 0;
          right: 0;
          height: 0;
          bottom: 0;
          border-bottom: 1px solid #000;
        }
      }

      &:hover {
        color: #d1291a;

        a {
          color: #d1291a;
        }

        &:after {
          border-bottom-color: #d1291a;
        }
      }
    }

    .content-section__release {
      h1 {
        font-size: 48px;
        line-height: 72px;
        margin-bottom: 80px;
      }

      .el-row {
        .el-col {
          margin-bottom: 90px;

          img {
            width: 374px;
            height: 258px;
            object-fit: cover;
            margin-bottom: 15px;
          }

          > span {
            display: inline-block;
            font-size: 12px;
            margin-bottom: 10px;
          }

          h3 {
            font-size: 16px;
            line-height: 28px;
            margin-bottom: 10px;
            height: 56px;

            &:hover {
              color: #d1291a;
            }
          }

          p {
            height: 80px;
            font-size: 14px;
            line-height: 26px;
            margin-bottom: 10px;
            -webkit-line-clamp: 3;
            line-clamp: 3;
          }
        }
      }
    }

    .content-section__others {
      h1 {
        font-size: 48px;
        line-height: 72px;
        margin: 60px 0 80px;
      }
    }

    .content-section__media {
      .item-content-column {
        > div {
          margin: 38px 0;
        }

        > .el-row {
          > div {
            height: 380px;
          }

          .el-col-21 {
            display: flex;
            flex-direction: column;

            > div {
              display: flex;

              > div {
                word-break: break-word;

                &:first-of-type {
                  flex: 1;
                  padding-right: 50px;
                  overflow: hidden;
                }

                &:last-of-type {
                  width: 550px;
                  height: 380px;

                  > img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                  }
                }
              }
            }
          }
        }

        h3 {
          font-size: 28px;
          line-height: 40px;
          -webkit-line-clamp: 3;
          line-clamp: 3;
        }

        p {
          font-size: 16px;
          line-height: 26px;
          -webkit-line-clamp: 3;
          line-clamp: 3;
          margin-top: 30px;
        }

        .el-link {
          font-size: 18px;
          line-height: 28px;
          align-self: flex-start;
          margin-top: -28px;
        }
      }
    }
  }
}

.xs,
.sm,
.md {
  .content-section {
    .content-section__release {
      h1 {
        font-size: 24px;
        line-height: 52px;
        margin-bottom: 20px;
      }

      .el-row {
        .el-col {
          margin-bottom: 40px;

          img {
            width: 100%;
            object-fit: cover;
            margin-bottom: 20px;
          }

          > span {
            display: inline-block;
            font-size: 12px;
            margin-bottom: 15px;
          }

          h3 {
            font-size: 16px;
            line-height: 24px;
            margin-bottom: 0px;
            height: 50px;
          }

          p {
            height: 78px;
            font-size: 14px;
            line-height: 24px;
            margin-bottom: 10px;
            -webkit-line-clamp: 3;
            line-clamp: 3;
          }
        }
      }
    }

    .content-section__others {
      h1 {
        font-size: 24px;
        line-height: 52px;
        margin: 10px 0 20px;
      }
    }

    .content-section__media {
      h1 {
        margin-bottom: 30px !important;
      }

      .item-content-column {
        > div {
          margin: 20px 0;
        }

        > .el-row {
          > div {
            height: 380px;
          }

          .el-col-21 {
            display: flex;
            flex-direction: column;

            > div {
              display: flex;

              > div {
                word-break: break-word;

                &:first-of-type {
                  flex: 1;
                  padding-right: 50px;
                  overflow: hidden;
                }

                &:last-of-type {
                  width: 550px;
                  height: 380px;
                  margin: 20px 0 !important;

                  > img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                  }
                }
              }
            }
          }
        }

        h3 {
          font-size: 22px;
          line-height: 30px;
          -webkit-line-clamp: 3;
          line-clamp: 3;
        }

        p {
          font-size: 14px;
          line-height: 24px;
          -webkit-line-clamp: 3;
          line-clamp: 3;
          margin-top: 30px;
        }

        .el-link {
          font-size: 16px;
          line-height: 24px;
          align-self: flex-start;
          margin-top: -38px;
        }
      }
    }
  }
}
</style>
