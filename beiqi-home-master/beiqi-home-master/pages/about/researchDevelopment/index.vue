<template>
  <client-only @touchstart="videoControl">
    <div class="research">
      <div v-for="(item, index) in list" :key="index" :class="{ 'video-wrap': item.videoUrl }">
        <img :src="item.imgUrl" @load="item.load = true" :key="imgKey"/>
        <div v-if="item.load && item.videoUrl" class="video">
          <HomePageAutoplayVideo ref="video" :videoUrl="item.videoUrl" videoId="research" />
        </div>
      </div>
    </div>
  </client-only>
</template>

<script>
export default {
  name: 'researchDevelopment',
  layout: 'main',
  components: {},
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        if (this.$mq === 'xs') {
          this.isMobile = true
        } else {
          this.isMobile = false
        }
        this.getImgList()
      },
    },
  },
  data() {
    return {
      list: [],
      isMobile: null,
      imgKey: Date.now()
    }
  },
  activated() {
    this.videoControl()
  },
  mounted() {
    this.getImgList()
  },
  methods: {
    getImgList() {
      let list = []
      const lan = this.$i18n.locale
      const imgNum = 17
      const file = this.isMobile ? 'researchWap' : 'research'
      let imgUrl
      for (let i = 1; i <= imgNum; i++) {
        imgUrl = require(`~/assets/image/research-development/${lan}/${file}_${i < 10 ? '0' + i : i}.jpg`)
        list.push({ imgUrl: imgUrl, load: false })
      }
      // 更新图片组件，解决当缩放浏览器时不显示视频问题
      this.imgKey = Date.now()
      // Now we acquire..图片
      list[11].videoUrl = '/mov/research.mp4'
      this.list = list
    },
    // 播放视频
    videoControl() {
      this.$refs.video?.[0].videoControl()
    }
  },
}
</script>

<style lang="scss" scoped>
.research {
  img {
    width: 100%;
  }
}
.video-wrap {
  position: relative;
}

.video {
  position: absolute;
  left: 10.47%;
  bottom: 0;
  width: 44.06%;

  :deep {
    #research {
      width: 100%;
      height: auto;
    }
  }
}

.xs {
  .video {
    width: 86.4%;
    left: 50%;
    bottom: 30.8%;
    transform: translateX(-50%);
  }
}
</style>
