import Vue from 'vue'
import VueMq from 'vue-mq'

Vue.use(VueMq, {
  // should always be matching tailwind breakpoints
  breakpoints: {
    xs: 768,
    sm: 992,
    md: 1200,
    lg: 1920,
    xl: Infinity
  },
  defaultBreakpoint: 'lg'
})
