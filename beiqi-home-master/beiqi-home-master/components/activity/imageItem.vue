<template>
  <nuxt-link :to="localePath(`/news/newsRelease/detail/${imageItem.id}`)" class="news-item" @mouseenter.native="expandTitle" @mouseleave.native="collapseTitle">
    <img :src="baseUrl + imageItem.imageUrl" :alt="imageItem.summarize" class="news-image" />
    <div :class="{ expanded: isExpanded }" class="new-item-text-wrap">
      <div class="text-title ellipsis2line">{{ imageItem.title }}</div>
    </div>
  </nuxt-link>
</template>

<script>
export default {
  name: 'ImageList',
  components: {},
  props: {
    imageItem: Object,
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      isExpanded: false,
    }
  },

  mounted() {},

  methods: {
    expandTitle() {
      this.isExpanded = true
    },
    collapseTitle() {
      this.isExpanded = false
    },
  },
}
</script>

<style lang="scss" scoped>
img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

.news-item {
  position: relative;
  display: block;
  overflow: hidden;
  margin-top: 5px;
  height: 100%;

  &:hover {
    .news-image {
      transform: scale(1.1);
    }
  }

  .new-item-text-wrap {
    position: absolute;
    bottom: 0;
    max-height: 3.8em;
    transition: all 0.4s;

    .text-title {
      margin: 20px;
      font-size: 20px;
      color: #fff;
      text-transform: uppercase;
    }
  }

  .expanded {
    //显示全部标题
    max-height: 100%;
  }

  .news-image {
    transition: transform 0.4s;
    /* 添加过渡效果 */
    transform: scale(1);
  }
}

@media only screen and (max-width: 768px) {
  .news-item {
    margin-top: 8px;

    .new-item-text-wrap {
      max-height: 5.6em;

      .text-title {
        font-size: 14px;
        padding: 5px;
      }
    }
  }
}
</style>
