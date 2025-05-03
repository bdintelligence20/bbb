const changeCookie = {
  // 设置cookie
  setCookie(name, value) { 
    var exp = new Date()
    exp.setTime(exp.getTime() + 24 * 60 * 60 * 1000)
    document.cookie =
      name + '=' + escape(value) + ';expires=' + exp.toGMTString()
  },
  // 获取cookie
  getCookie(cname) {
    var name = cname + '='
    var ca = document.cookie.split(';')
    for (var i = 0; i < ca.length; i++) {
      var c = ca[i].trim()
      if (c.indexOf(name) == 0) return c.substring(name.length, c.length)
    }
    return ''
  },
  // 删除cookie
  delCookie(c_name) {
    changeCookie.setCookie(c_name, '', -1)
  },
}

export default changeCookie