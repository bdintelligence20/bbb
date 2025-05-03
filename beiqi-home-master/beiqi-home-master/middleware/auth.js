import { getToken } from '~/utils/token'

const loginPageRoutePath = '/distributorCenter/login'

export default function ({app, route, redirect}) {
  if (!process.client) {
    return
  }

  const path = app.i18n.locale !== app.i18n.defaultLocale ? '/' + app.i18n.locale : ''
  // 如果用户未登录且当前页需要验证登录状态，则跳转到登录页
  if (!getToken() && route.meta[0].requireAuth) {
    // 如果当前页是登录页,则不需要跳转
    if (route?.matched?.[0]?.path == `${path}/distributorCenter/login`) {
      return
    } else {
      // 如果是默认语言则不携带
      return redirect(`${path}/distributorCenter/login`)
    }
  }
  // 如果用户已登录且访问的是登录页，则直接跳转到首页
  if (getToken() && route?.matched?.[0]?.path.substr(0 - loginPageRoutePath.length) === loginPageRoutePath) {
    return redirect((app.i18n.locale !== app.i18n.defaultLocale ? '/' + app.i18n.locale : '') + '/distributorCenter')
  }
}


export function checkToken() {
  if (!process.client) {
    return
  }

  // 当前页面路由
  const {app, redirect, route} = $nuxt.context
  const currentRoutePath = route?.matched?.[0]?.path
  // 语言前缀 e.g. /en /ar /is
  const lanPathPre = app.i18n.locale !== app.i18n.defaultLocale ? '/' + app.i18n.locale : ''
  // 当前页面是否是登录页
  const isCurrentLoginPage = currentRoutePath.substr(0 - loginPageRoutePath.length) === loginPageRoutePath
  // 当前用户是否已经登录
  const  isLogin = getToken()

  let targetPath = currentRoutePath
  if(!isLogin && !isCurrentLoginPage) {
    // 如果未登录，并且当前不是登录页，那么我们呢应该跳到登录页去
    // to login page
    targetPath = `${lanPathPre}/distributorCenter/login`
  } else if (isLogin && isCurrentLoginPage) {
    // 如果已经登录，并且当前是登录页，那么我们呢应该跳到首页去
    // to home page
    targetPath = `${lanPathPre}/distributorCenter`
  } else {
    // no action
    return true
  }

  if(redirect) {
    return redirect(targetPath)
  } else {
    window.location.replace(targetPath)
  }
}
