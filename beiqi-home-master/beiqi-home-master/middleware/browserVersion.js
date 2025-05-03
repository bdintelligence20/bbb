// export default function(context) {
//   const userAgent = process.server ? context.req.headers['user-agent'] : navigator.userAgent
//   let re = new RegExp('MSIE ([0-9]{1,}[\.0-9]{0,})')
//   if (re.exec(userAgent) != null) {
//       let rv = parseFloat(RegExp.$1)
//       if (rv <= 11) { // 低于IE11以下  跳转 提示浏览器升级 页面
//           return context.redirect('https://support.dmeng.net/upgrade-your-browser.html')
//       }
//   }
// }