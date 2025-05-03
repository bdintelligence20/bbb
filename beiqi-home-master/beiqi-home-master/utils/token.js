const TokenKey = 'token'

// token的存储数据格式：
/*
{
  expire: newExpire, // 这个数据，将在此时间之后失效，它是一个毫秒时间戳
  tokenStr: token string // 数据本身
}
*/

/**
 * 获取token 字符串的方法
 * @returns {null|*}
 */
export function getToken() {
  if (!process.client) {
    console.log('getToken in getToken.js but this is in server side ')
    return
  }

  // 从sessionStorage里面获取json 字符串
  const jsonStr = sessionStorage.getItem(TokenKey)
  if (jsonStr) {
    const jsonObj = JSON.parse(jsonStr)
    if (jsonObj && jsonObj.expire && jsonObj.tokenStr) {
      // 检查过期时间
      if (jsonObj.expire > Date.now()) {
        // 没过期
        return jsonObj.tokenStr
      }
    }
  }
  return null
}

/**
 * 只设置token的值
 * @param tokenStr
 * @param option
 */
export function setToken(tokenStr) {
  if (!process.client) {
    console.log('getToken in getToken.js but this is in server side ')
    return
  }

  let newExpire = new Date()
  newExpire.setHours(23)
  newExpire.setMinutes(59)
  newExpire.setSeconds(59)
  newExpire.setMilliseconds(999)
  const expire = newExpire.getTime()

  const jsonObj = {
    tokenStr,
    expire,
  }

  // 写入sessionStorage
  sessionStorage.setItem(TokenKey, JSON.stringify(jsonObj))
}

export function removeToken() {
  if (!process.client) {
    return
  }
  return sessionStorage.removeItem(TokenKey)
}
