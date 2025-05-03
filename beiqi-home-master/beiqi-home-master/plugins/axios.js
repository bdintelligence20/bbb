import { Message } from 'element-ui'
import { getToken, removeToken } from '~/utils/token'

const errorCode = {
  401: '认证失败，无法访问系统资源',
  403: '当前操作没有权限',
  404: '访问资源不存在',
  default: '系统未知错误，请反馈给管理员',
}

function isSubStringInArray(subString, array) {
  return array.some(item => subString.includes(item))
}

export default function ({$axios, i18n, route, redirect}) {
  // console.log(redirect)
  // 基本配置
  $axios.defaults.timeout = 10000
  $axios.defaults.headers.post['Content-Type'] = 'application/json'
  $axios.defaults.headers.patch['Content-Type'] = 'application/json'
  $axios.defaults.headers.put['Content-Type'] = 'application/json'

  // 不需要加多语言的接口
  let filterateUrl = [
    "/newLabel",
    "/captchaImage",
    "/login",
    "/getInfo",
    "/getAllMaterialList",
    "/user",
    '/getPyteNewLabel',
    '/PyteNewLabel',
    '/materialLog',
  ]

  // 请求回调
  $axios.onRequest(
    config => {
      console.log(config.url, 'config.url')

      // file开头不需要加语言
      if (!config.url.startsWith('/file') && !config.url.startsWith('/geoip')) {
        if (isSubStringInArray(config.url, filterateUrl)) {
          config.url = '/home-api' + config.url
          config.headers['Authorization'] = getToken()
        } else {
          config.url = '/home-api' + config.url + '/' + i18n.locale
        }
      }

      // console.log('onRequest:'+config.url)
      // get请求映射params参数
      // if (config.method === 'get' && config.params) {
      //   let url = config.url + '?' + tansParams(config.params)
      //   url = url.slice(0, -1)
      //   config.params = {}
      //   config.url = url
      // }
      return config
    },
    error => {
      return Promise.reject(error)
    }
  )

  // 返回回调
  $axios.onResponse(res => {
    // console.log('onResponse:'+res.config.url)
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200
    // 获取错误信息
    const msg = errorCode[code] || res.data.msg || errorCode['default']
    // 二进制数据则直接返回
    if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
      return res.data
    }
    if (code !== 200) {
      if (isSubStringInArray(res.config.url, filterateUrl)) {
        // token过期,需重新登录
        if (code == 401) {
          Message({message: 'Login status has expired, please log in again', type: 'error'})
          // 清除token
          removeToken()
          localStorage.removeItem('user')
          // 跳转到登录页
          redirect('/distributorCenter/login')
          return
        } else {
          Message({message: msg, type: 'error'})
        }
      }
      // 在页面里处理,不要全局报错
      Message({message: msg, type: 'error'})
      return Promise.reject(new Error(msg))
    } else {
      return res.data
    }
  })

  // 错误回调
  $axios.onError(error => {
    console.error(error)
  })
}
