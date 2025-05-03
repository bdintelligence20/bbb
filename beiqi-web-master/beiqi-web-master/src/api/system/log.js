import request from '@/utils/request'

// 查询materialLog列表
export function listLog(query) {
  return request({
    url: '/system/log/list',
    method: 'get',
    params: query
  })
}

// 查询materialLog详细
export function getLog(id) {
  return request({
    url: '/system/log/' + id,
    method: 'get'
  })
}

// 新增materialLog
export function addLog(data) {
  return request({
    url: '/system/log',
    method: 'post',
    data: data
  })
}

// 修改materialLog
export function updateLog(data) {
  return request({
    url: '/system/log',
    method: 'put',
    data: data
  })
}

// 删除materialLog
export function delLog(id) {
  return request({
    url: '/system/log/' + id,
    method: 'delete'
  })
}
