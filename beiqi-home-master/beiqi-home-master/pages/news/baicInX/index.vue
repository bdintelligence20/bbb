<template>
  <div
    v-loading="fullLoading"
    class="news__page"
  >
    <title-section
      :img-url="imgUrl"
      :image="image"
      class="activity-title-section"
    />
    <div
      v-loading="loading"
      class="content-section"
    >
      <div class="content-section__release">
        <h1>{{ $t('news.baicIn') }}</h1>
      </div>
      <ActivityImageList :news-list="newsList" :recommend-list="recommendList"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Activity',
  layout: 'main',
  data () {
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
      newsList: [],
      // 分页数据
      pageNum: 1, // 当前页数
      pageSize: 99, // 每页显示条目个数
      recommendList: [] // 推荐位
    }
  },
  created () {
    this.fullLoading = true
    this.$api.home.getBanner({ type: 6 }).then(res => {
      this.imgUrl = this.image.url = res.data[0]?.imageUrl
      this.image.aspectRatio = parseFloat(res.data[0]?.aspectRatio) + '%'
    })
    this.getPage()
  },
  methods: {
    async getPage () {
      this.loading = true
      const params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        // categoryId: this.$route.query.id,
        categoryId: 89,
        recommend: 0,
      }
      this.$api.news
        .getNewsList(params)
        .then(res => {
          this.newsList = res.rows
        })
        .catch(err => {
          this.$message.error(err.msg || 'Request failed')
        })
        .finally(() => {
          this.fullLoading = false
          this.loading = false
        })

        try {
          const res = await this.$api.news.getNewsList({...params, recommend: 1})
          if (res.code === 200) {
            this.recommendList = res.rows
            console.log(res, '$$')
          }
        } catch (err) {
          this.$message.error(err.msg || 'Request failed')
        }

    },
  },
}
</script>
<style lang="scss" scoped>
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
      padding: 0 80px;
      h1 {
        font-size: 48px;
        line-height: 72px;
        margin-top: 35px;
        margin-bottom: 35px;
      }
    }
  }
}

.xs,
.sm,
.md {
  .news__page {
    margin-bottom: 30px;
  }

  .content-section {
    .content-section__release {
      padding: 0 30px;
      h1 {
        font-size: 24px;
        line-height: 52px;
        margin-top: 20px;
        margin-bottom: 20px;
      }
    }
  }
}
</style>

<style lang="scss">
.activity-title-section {
  .banner-image-container {
    margin-bottom: 60px !important;
  }
  .breadcumb-container {
    max-width: 100%;
    padding: 0 80px;
  }
}
.xs,
.sm,
.md {
  .activity-title-section {
    .banner-image-container {
      margin-bottom: 30px !important;
    }
    .breadcumb-container {
      padding: 0 30px;
    }
  }
}
</style>
