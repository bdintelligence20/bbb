import I18N from './plugins/i18n'
// const axios = require('axios')

export default {
  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'beiqi',
    htmlAttrs: {
      lang: 'en',
    },
    meta: [{ charset: 'utf-8' }, { name: 'viewport', content: 'width=device-width, initial-scale=1' }, { hid: 'description', name: 'description', content: 'my website description' }, { name: 'format-detection', content: 'telephone=no' }],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      { rel: 'preconnect', href: 'https://www.facebook.com' }
    ],
    script: [
      {
        innerHTML: `
        var browserType = function(from){
          var explorer = from.toLowerCase();
          var isIe = false;
          if (explorer.indexOf('msie') >= 0 || explorer.indexOf('rv:11.0') >= 0) {
             isIe = true;
           }
           return isIe;
           }
          var loggedIE = browserType(window.navigator.userAgent);
          if (loggedIE) {
             document.cookie="isIe=true";
              var nowHref = window.location.href
              if(nowHref.indexOf('ie') < 0) {
                window.location.pathname= "/ie";
              }
          } else {
           document.cookie="isIe=false";
          }
         `,
        //  文档解析完执行
        defer: true
      },
      {
        innerHTML: `
        !(function (f, b, e, v, n, t, s) {
          if (f.fbq) return;
          n = f.fbq = function () {
            n.callMethod ? n.callMethod.apply(n, arguments) : n.queue.push(arguments);
          };
          if (!f._fbq) f._fbq = n;
          n.push = n;
          n.loaded = !0;
          n.version = "2.0";
          n.queue = [];
          t = b.createElement(e);
          t.async = !0;
          t.src = v;
          s = b.getElementsByTagName(e)[0];
          s.parentNode.insertBefore(t, s);
        })(
          window,
          document,
          "script",
          "https://connect.facebook.net/en_US/fbevents.js"
        );
        fbq("init", "SECRT-ID");
        fbq("track", "PageView");
         `,
        //  脚本加载完立即执行
        async: true
      },
    ],
    __dangerouslyDisableSanitizers: ['script'],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    'element-ui/lib/theme-chalk/index.css',
    // '@/assets/scss/quill.css',
    '@/assets/scss/main.scss',
    'normalize.css/normalize.css',
    'animate.css/animate.css',
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '@/plugins/mq',
    '@/plugins/news',
    '@/plugins/element-ui',
    { src: '@/plugins/vue-lazyload', ssr: false },
    { src: '@/plugins/swiper', ssr: false },
    '@/plugins/axios',
    '@/plugins/api-plugin',
    { src: './plugins/gtag.js', mode: 'client' },
    { src: './plugins/vue-tel-input.js', ssr: false },
    { src: '@/plugins/vue-animate-number', ssr: false },
    { src: '@/plugins/vue-echarts.js', ssr: false }, // 配置 vue-echarts 插件
    // { src: './plugins/route', ssr: false },
  ],
  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  // buildModules: ['@nuxtjs/composition-api/module'],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: ['@nuxtjs/axios', '@nuxtjs/style-resources', ['@nuxtjs/i18n', I18N]],
  // 改为相对路径
  env: {
    imgBaseUrl: '/file',
  },
  // 配置axios 内容
  // See https://axios.nuxtjs.org/options 详细信息查看
  axios: {
    proxy: true, // Can be also an object with default options
    prefix: '', // 需要读取文件, 不在这里加前缀了
  },
  proxy: {
    '/home-api': {
      // target: 'https://www.baicglobal.com',
      target: 'http://192.168.1.141:8080',
      changeOrigin: true,
    },
    '/file': {
      // target: 'https://www.baicglobal.com',
      target: 'http://192.168.1.141:8080',
      changeOrigin: true,
    },
    '/geoip': {
      // target: 'https://www.baicglobal.com',
      target: 'http://192.168.1.141:3000',
      changeOrigin: true,
    },
  },
  // 打包静态文件的时候生成动态路由
  // generate: {
  //   routes() {
  //     let product = axios.get(`${env[process.env.NODE_ENV].BASE_API}/home-api/vehicle/class/en`).then(res => {
  //       return res.data.data.map(res => {
  //         return '/models/' + res.id
  //       })
  //     })

  //     let news = axios.get(`${env[process.env.NODE_ENV].BASE_API}/home-api/website/news/rlist/en`).then(res => {
  //       return res.data.data.map(res => {
  //         return '/news/newsRelease/detail/' + res.id
  //       })
  //     })
  //     return Promise.all([product,news]).then((values) => {
  //       console.log(values)
  //       return values
  //     });
  //   }
  // },
  // scss  全局变量配置
  styleResources: {
    scss: ['~/assets/scss/variables.scss', '~/assets/scss/mixins.scss'],
  },
  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    transpile: [/^element-ui/],
    extractCSS: true,
    optimizeCSS: true,
    loaders: {
      imgUrl: { limit: 8192 }, // 文件大小在8kb以内会被转成base64格式
    }
  },
  router: {
    base: '/', // 此为根目录，如果有具体目录需求按实际情况写
    prefetchLinks: false // 禁用链接的预加载
  },
  server: {
    host: '0.0.0.0',
    port: 3000,
  },
}
