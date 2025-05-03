<template>
  <div v-if="paramsList.length > 0" ref="params" class="vehicle-params-container">
    <ul class="vehicle-params-list">
      <li v-for="item in paramsList" :key="item.value" class="params-item">
        <span class="title">{{ item.name }}</span>
        <div class="value">
          <NumberAnimate ref="numberAnimate" :number="formatString(item.value)[0]" :decimal="item.decimal" />
          <span>{{ formatString(item.value)[1] }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import NumberAnimate from './NumberAnimate.vue'
export default {
  components: { NumberAnimate },
  props: {
    paramsList: {
      type: Array,
      default() {
        return []
      },
    },
  },
  data() {
    return {
    }
  },
  mounted() {
    this.checkVisibility()
  },
  methods: {
    // 开始数字滚动动画
    startAnimateNumber() {
      if (this.$refs.numberAnimate) {
        this.$refs.numberAnimate.forEach(item => {
          item.startAnimate()
        })
      }
    },
    // 检查元素是否可见
    checkVisibility() {
      const observer = new IntersectionObserver(
        entries => {
          entries.forEach(entry => {
            if (entry.isIntersecting && entry.intersectionRatio >= 0.8) {
              this.startAnimateNumber()
              // 取消观察，只观察一次
              observer.unobserve(entry.target)
            }
          })
        },
        { threshold: 0.8 }
      )
      observer.observe(this.$refs.params)
    },
    // 将字符串中数字和字符分开
    formatString(string) {
      const regex = /(\d+(\.\d+)?)/ // 匹配数字（包括小数）
      const match = string.match(regex)
      if (match) {
        const number = Number(match[0]) // 提取的数字
        const text = string.replace(number, '') // 其他字符串
        return [number, text]
      }
    },
  },
}
</script>

<style scoped lang="scss">
.vehicle-params-container {
  display: flex;
  justify-content: center;
  width: 100%;
}
.vehicle-params-list {
  display: flex;
  width: 64.8%;
  justify-content: space-around;
}
.params-item {
  color: #fff;
  display: flex;
  flex-direction: column;
  font-size: 24px;
  text-align: center;
  line-height: 1.5;
  .title {
    color: #fff;
    font-size: 13px;
    text-transform: uppercase;
  }
}
@media screen and (max-width: 1200px) {
  .vehicle-params-list {
    width: 100%;
    flex-wrap: wrap-reverse;
  }
  .params-item {
    width: calc(100% / 3);
    font-size: 16px;
    .title {
      font-size: 12px;
    }
    &:nth-child(n + 2) {
      margin-bottom: 15px;
    }
  }
}
.lang___ar {
  .vehicle-params-container {
    .params-item {
      .title {
        font-size: 16px;
      }
    }
    @media screen and (max-width: 1200px) {
      .params-item {
        .title {
          font-size: 12px;
        }
      }
    }
  }
}
</style>
