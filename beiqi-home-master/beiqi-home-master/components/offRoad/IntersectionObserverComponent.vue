<template>
  <div ref="elementRef">
    <slot></slot>
  </div>
</template>
  
  <script>
export default {
    props:{
        intersectionRatio:{
            type: Number,
            default: 0.5
        } 
    },
  data() {
    return {
      observer: null,
    }
  },
  methods: {
    handleIntersection(entries) {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          // 获取元素的可见比例
          const intersectionRatio = entry.intersectionRatio

          // 判断元素是否在视口中间（可见比例大于等于 0.5）
          if (intersectionRatio >= this.intersectionRatio) {

            // 元素进入视口中间时的逻辑
            this.$emit('enterViewport', entry.target)
          } else {
            // 元素进入视口但不在中间时的逻辑
            this.$emit('partialViewport', entry.target)
          }
        } else {
          // 元素离开视口时的逻辑
          this.$emit('leaveViewport', entry.target)
        }
      })
    },
  },
  mounted() {
    // 创建 Intersection Observer 实例
    this.observer = new IntersectionObserver(this.handleIntersection, {
      root: null,
      rootMargin: '0px',
      threshold: 0.5,
    })

    // 监听组件内的元素
    this.observer.observe(this.$refs.elementRef)
  },
  beforeDestroy() {
    // 在组件销毁前停止观察
    if (this.observer) {
      this.observer.disconnect()
    }
  },
}
</script>
  
  <style scoped>
/* 可以添加一些样式，根据需要进行调整 */
</style>
  