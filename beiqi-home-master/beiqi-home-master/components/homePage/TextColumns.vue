<template>
  <div class="news-section" v-if="section && section.length > 0">
    <div class="max-1680">
      <div class="section-header g-flex-b">
        <h3 class="h3"> {{ $t('news.title') }} </h3>
        <nuxt-link
          :to="localePath({path:'/news/newsRelease',query:{id:section[0].categoryId}}, $i18n.locale)">
          <el-button
            style="margin: 20px 0;"
            class="border-el-button white">{{ $t('btn.viewAll') }}</el-button>
        </nuxt-link>
      </div>
      <el-row :gutter="40">
        <el-col :xs="24" :sm="8" :md="8" :lg="8" :xl="8" class="grid__item" v-for="item in section" :key="item.id">
          <nuxt-link :to="localePath({ name:'news-newsRelease-detail-id',params: { id: item.id }}, $i18n.locale)">
            <div>
              <a :href="item.linkUrl">
                <div class="image-wrap text-spacing" :style="{  backgroundImage: `url(${baseUrl}${encodeURIComponent(item.imageUrl)})` }">
                </div>
              </a>
            </div>
            <div class="text-spacing time">{{ item.time }}</div>
            <div class="des text-spacing">{{ item.summarize }}</div>
            <el-button class="border-el-button white">{{ $t('btn.viewMore') }}</el-button>
          </nuxt-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  import { parseTime } from '~/utils/index';
  
  export default {
    props: {
      section: {
        type: Array,
      },
    },
    data() {
      return {
        baseUrl: process.env.imgBaseUrl,
        time: '',
      };
    },
    created() {
      // this.getNewsList()
    },
    methods: {
      // async getNewsList() {
      //   let params = {
      //     recommend: 1
      //   }
      //   this.$api.home.getNews(params).then(res => {
      //     this.section = []
      //     res.data.map((item, index) => {
      //       if(index < 3) {
      //         item.time = parseTime(item.createTime, '{y}-{m}-{d}')
      //         this.section.push(item)
      //       } else {
      //         return
      //       }
      //     })
      //   })
      // }
    },
  };
</script>

<style lang="scss" scoped>
.news-section{
  background-color: #222833;
  margin: 8px 0;
  padding: 0 0 80px 0;
}
.section-header{
  h3{
    margin-top: 80px;
    font-size: 44px;
    color: #fff;
  }
}
.time{
  color: #fff;
}
.des {
  font-size          : 18px;
  // word-break: break-all;
  text-overflow      : ellipsis;
  display            : -webkit-box;
  -webkit-box-orient : vertical;
  -webkit-line-clamp : 2; /* 这里是超出几行省略 */
  overflow           : hidden;
  min-height         : 54px;
  margin-bottom      : 15px;
  cursor             : pointer;
  color: #fff;
  
  &:hover {
    // color : #D1291A
  }
}

.btn-link {
  margin-top : 20px;
}

img {
  margin : 0 auto;
}

.grid__item {
  margin-bottom : 30px;
}

.image-wrap {
  cursor              : pointer;
  position            : relative;
  height              : 0;
  padding-top         : 70%;
  background-position : center;
  background-repeat   : no-repeat;
  background-size     : cover;
}

.xs {
  .section-header {
    h3 {
      margin-top: 60px;
      font-size: 26px;
    }
  }
  .des {
    font-size  : 16px;
    min-height : 48px;
  }
}
</style>
