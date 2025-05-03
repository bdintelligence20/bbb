<!--
 * Author: SHI
 * Date: 2022/6/20
 * Remark: detail
 -->

<template>
  <div class="detail__page" :class="{ 'mt-80': className }" v-loading="fullLoading" @touchstart="videoControl">
    <title-section :img-url="imgUrl" :title="!doms.length ? (detailData.title || defaultText.title) : null" :image="image" :img-type="imgType" :news-id="newsId" :need-breadcrumb="showOther" />
    <div v-if="doms.length" class="ed-news-content" :class="{'ed-dir-rtl': isAr }">
      <Vnodes :vnodes="doms" />
    </div>

    <div class="content-section" :class="[newsId != 90 && newsId != 91 && newsId != 92 && imgType != 'csr' && imgType != 'vision' ? 'max-1200 margin-b60' : '']">
      <div class="video-box" v-if="newsId === '92'" ref="videoBox">
        <HomePageAutoplayVideo ref="video" :videoId="`Mp4Video_92`" videoUrl="/mov/power.mp4" />
      </div>

      <description v-if="!doms.length" :html-str="detailData.content" />

      <el-row :gutter="27" class="reports-list" v-if="reports.length">
        <el-col :span="8" v-for="(item, index) in reports" :key="index" class="reports-list-item">
          <div>
            <img :src="baseUrl + item.coverImageUrl" alt="" />
            <a :download="item.title" :href="baseUrl + item.reportUrl" target="_blank"></a>
          </div>
          <h4>{{ item.title }}</h4>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import mix from '~/utils/Mix'

export default {
  name: 'NewsDetail',
  components: {
    // 用来将VNode挂载到template
    Vnodes: {
      functional: true,
      render: (h, ctx) => ctx.props.vnodes
    }
  },
  mixins: [mix],
  layout: 'main',
  data() {
    return {
      doms:[],
      className: false,
      baseUrl: process.env.imgBaseUrl,
      fullLoading: false,
      imgUrl: '',
      image: {
        url: '',
        alt: '',
        aspectRatio: 2.5,
      },
      detailData: {
        title: '',
        content: '',
      },
      reports: [],
      imgType: '',
      showOther: true,
      newsId: '',
      // 其他
      defaultText: {
        title: 'Social Responsibility',
      },
      allLanguages: {
        en: {
          title: 'Social Responsibility',
        },
        es: {
          title: 'Responsabilidad Social',
        },
        ar: {
          title: 'مسؤولية اجتماعية',
        },
      },
    }
  },
  created() {},
  activated(){
    this.videoControl()
  },
  deactivated(){
  },
  computed: {
    isAr() {
      return this.$i18n.locale === 'ar'
    }
  },
  mounted(){
    const {path, params, query} = this.$route
    if (path.includes('socialResponsibility')) {
      this.$api.news.getAboutSubMenuSocial()
        .then((res) => {
          this.fullLoading = true
          this.detailData.title = ''
          this.detailData.content = res.data.content
          this.reports = res.data.reports
        })
        .finally(() => {
          this.fullLoading = false
        })
    } else if (path.includes('overview') || path.includes('ourVision')) {
      this.$api.news.getAboutSubMenu({ id: query.id })
        .then((res) => {
          this.fullLoading = true
          Object.assign(this.detailData, res.data)
        })
        .finally(() => {
          this.fullLoading = false
        })
    } else if (path.includes('news')) {
      this.defaultText.title = ''
      this.fullLoading = true
      this.newsId = params.id
      this.showOther = !(+params.id === 90 || +params.id === 91 || +params.id === 92)
      if (this.showOther) {
        this.$api.home.getBanner({ type: 5 }).then((res) => {
          this.imgUrl = this.image.url = res.data[0]?.imageUrl
          this.image.aspectRatio = parseFloat(res.data[0]?.aspectRatio) + '%'
        })
      } else {
        this.imgUrl = this.image.url = ''
      }
      if(!params.id) return;
      this.$api.news
        .getNewsDetail({ id: params.id })
        .then((res) => {
          Object.assign(this.detailData, res.data)
          // 组装新版本的components
          try{
            if(res.data.content && JSON.parse(res.data.content)) {
              this.buildDom(JSON.parse(res.data.content))
            }
          }catch(e){}
        })
        .catch(() => {
          this.$router.push('/')
        })
        .finally(() => {
          this.fullLoading = false
        })
    }
  },
  methods: {
    videoControl() {
      this.$refs.video?.videoControl()
    },
    /**
     * 把页面的模块数据做成 VNode
     */
    buildDom (components) {
      this.doms = components.map((c) => {
        c = JSON.parse(JSON.stringify(c))
        const componentDom = this.$createElement(c.name, {
          props: {
            /*
             将模块数据传入到内部
             我们在预览的时候，尝试获取组件当前 内容语言 的数据 pageData.components[X].data.LAN_CODE
             */
            data: c.data,
            // 语言
            lang: 'zh-CN',
            // 编辑模式
            mode: 'edit'
          }
        })

        console.log('c.name is', c.name, componentDom);
        return componentDom
      })
    }
  }
}
</script>

<style lang="scss">
@import url('@/components/md/theme-chalk/lib/index.scss');
@media only screen and (max-width: 767px) {
    .ed-news-content {
        font-size: 14px;
    }
}
.margin-b60{
  margin-bottom: 60px;
}
.detail__page {
  .video-box {
    position: relative;
    // margin-top: 8px;
    line-height: 0; //视频下面多了一条白色，加这个就没有了
    z-index: 4;
  }

  &.mt-80 {
    //margin-top: 80px;
  }
}
</style>
