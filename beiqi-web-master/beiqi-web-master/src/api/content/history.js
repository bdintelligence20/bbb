import request from '@/utils/request'

// 查询历史列表
export function listHistory(query) {
  return request({
    url: '/system/history/list',
    method: 'get',
    params: query
  })
}

// 查询历史详细
export function getHistory(historyId) {
  return request({
    url: '/system/history/' + historyId,
    method: 'get'
  })
}

// 新增历史
export function addHistory(data) {
  return request({
    url: '/system/history',
    method: 'post',
    data: data
  })
}

// 修改历史
export function updateHistory(data) {
  return request({
    url: '/system/history',
    method: 'put',
    data: data
  })
}

// 删除历史
export function delHistory(historyId) {
  return request({
    url: '/system/history/' + historyId,
    method: 'delete'
  })
}
