// 获取
export function getStorage(key) {
  if (!process.client) {
    return
  }
  const jsonStr = sessionStorage.getItem(key)
  if(jsonStr) {
    const jsonObj = JSON.parse(jsonStr)
    return jsonObj
  }
}

// 存储
export function setStorage(key,value) {
  if (!process.client) {
    return
  }
  sessionStorage.setItem(key,JSON.stringify(value))
}

// 移除
export function removeStorage(key) {
  if(!process.client) {
    return
  }
  sessionStorage.removeItem(key)
}
