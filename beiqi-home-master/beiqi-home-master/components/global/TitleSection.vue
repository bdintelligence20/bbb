<!--
 * Author: SHI
 * Date: 2022/6/20
 * Remark: TitleSection
 -->

<template>
  <div class="TitleSection__page" :class="className">
    <div>
      <BannerVue v-if="!!imgUrl" :image="image" style="margin-bottom: 80px" class="banner-image-container"></BannerVue>
      <!-- <img class="sub-banner" src="~/assets/image/subBanner/csr.jpg" v-if="imgType === 'csr'" /> -->
      <!-- <img class="sub-banner" src="~/assets/image/subBanner/vision.jpg" v-if="imgType === 'vision'" /> -->
      <img class="sub-banner" src="~/assets/image/subBanner/overview.jpg" v-if="imgType === 'overview'" />
      <!-- <img class="sub-banner" src="~/assets/image/Innovation/design.jpg" v-if="newsId==='90'" /> -->
      <!-- <img class="sub-banner" src="~/assets/image/Innovation/off-road.jpg" v-if="newsId==='91'" /> -->
      <!-- <img class="sub-banner" src="~/assets/image/Innovation/power.jpg" v-if="newsId === '92'" /> -->
      <img class="sub-banner" src="~/assets/image/subBanner/history.jpg" v-if="imgType === 'history'" />
      <div class="max-1200 breadcumb-container">
        <breadcumb v-if="needBreadcrumb" />
      </div>
      <div class="max-1200 header">
        <h1 class="h1 title" :title="title" v-if="title && newsId != 90 && newsId != 91 && newsId != 92 && imgType != 'csr' && imgType != 'vision'">{{ title }}</h1>
        <p v-if="!!subTitle">{{ subTitle }}</p>
        <slot name="subTitle"></slot>
        <el-button v-if="title && newsId != 90 && newsId != 91 && newsId != 92 && imgType != 'csr' && imgType != 'vision' && $route.path.endsWith('/news/multimedias')" class="border-el-button" @click="$router.go(-1)">Back</el-button>
      </div>
    </div>
    <slot name="other"></slot>
  </div>
</template>

<script>
import BannerVue from '~/components/img/banner.vue'

export default {
  name: 'TitleSection',
  props: {
    className: {
      // 自定义样式的类名
      type: String,
      default: '',
    },
    imgUrl: {
      type: String,
      default: '',
    },
    needBreadcrumb: {
      type: Boolean,
      default: true,
    },
    title: {
      type: String,
      default: '',
    },
    subTitle: {
      type: String,
      default: '',
    },
    image: {
      type: Object,
      default: () => {},
    },
    imgType: {
      type: String,
      default: '',
    },
    newsId: {
      type: String,
      default: '',
    },
  },
  components: { BannerVue },
  data() {
    return {}
  },
}
</script>

<style lang="scss">
.TitleSection__page {
  img {
    width: 100%;
  }
  //overflow : hidden;
  .sub-banner {
    margin: 0 auto 50px;
  }
  .el-breadcrumb {
    margin: 0 0 10px;
  }

  .h1 {
    margin-bottom: 35px;
    font-size: 48px;
    word-break: break-word;
  }

  p {
    font-size: 18px;
    line-height: 26px;
  }

  .header {
    position: relative;

    .border-el-button {
      position: absolute;
      left: 15px;
      top: 0;
    }
  }
}
.xs,
.sm,
.md {
  .TitleSection__page {
    .el-breadcrumb {
      margin: 30px 0 10px;
    }

    .h1 {
      margin-bottom: 35px;
      font-size: 24px;
      word-break: break-word;
    }

    p {
      font-size: 14px;
      line-height: 22px;
    }

    .header {
      .border-el-button {
        display: none;
      }
    }
  }
}
</style>
