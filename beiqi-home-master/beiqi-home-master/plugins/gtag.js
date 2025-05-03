import Vue from 'vue'
import VueGtag from 'vue-gtag'

export default ({ app }) => {
  Vue.use(VueGtag, {
    config: { id: 'G-YOU-GTAG-ID' },
    appName: 'beiqi',
  }, app.router);
}
