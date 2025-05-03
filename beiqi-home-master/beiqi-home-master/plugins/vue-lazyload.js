import Vue from 'vue'
import VueLazyload from 'vue-lazyload'
// or with options
Vue.use(VueLazyload, {
  preLoad: 1.3, // 预加载的宽高比
  // error: 'https://taoic.oss-cn-hangzhou.aliyuncs.com/static/images/product.jpg', // 图片加载失败时使用的图片源
  loading: '', // 图片加载的路径
  attempt: 1, // 尝试加载次数
  throttleWait: 100,//加载时间快些，不然时间有点久
  observer: true
})
