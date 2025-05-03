<template>
  <div class="text-over-image">
    <div class="image-text__container">
      <img v-lazy="baseUrl + list.imageUrl" />
    </div>
    <div class="image-text__inner">
      <h3 class="title h3">{{ list.title }}</h3>
      <nuxt-link :to="localePath(list.linkUrl, $i18n.locale)">
        <el-button style="margin-top: 10px" class="border-el-button">
          {{ $t('btn.viewMore') }}
        </el-button>
      </nuxt-link>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    list: {
      type: Object,
    },
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
    }
  },
  created() {},
  methods: {},
}
</script>

<style lang="scss" scoped>
.text-over-image {
  margin-top: 8px;
  //为了地图在worldwide页面,和footer间距小,所以增加了-margin,这里要多加一点
  position: relative;
  .image-text__container {
    img {
      width: 100%;
      min-height: 100px;
      object-fit: cover;
    }
  }

  .image-text__inner {
    position: absolute;
    height: 100%;
    top: 0;
    left: 0;
    display: flex;
    flex-direction: column;
    align-items: center;

    text-align: center;
    justify-content: center;
    padding: 0px 100px;
  }
}

.md,
.sm,
.xs {
  .index-section {
    margin-bottom: 0;
  }
}

.sm,
.xs {
  .image-text__inner {
    padding: 0 30px;
    h3 {
      font-size: 14px;
    }
    .text-spacing-b {
      margin-bottom: 6px;
    }
  }
}

@media only screen and (max-width: 768px) {
  .image-text__inner {
    //position: relative;
    //justify-content: flex-start;
    left: 10px;
  }
}
</style>
