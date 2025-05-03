import request from '@/utils/request'

// 查询媒体线索列表
export function listSubscribe(query) {
  return request({
    url: '/contact/subscribe',
    method: 'get',
    params: query
  })
}

// 修改媒体线索
export function updateSubscribe(data) {
  return request({
    url: '/contact/subscribe',
    method: 'put',
    params: data
  })
}

// 删除媒体线索
export function delclass(classId) {
  return request({
    url: '/testdrive/vehicle/class/' + classId,
    method: 'delete'
  })
}

// 导出媒体线索
export function exportclass(query) {
  return request({
    url: '/testdrive/vehicle/class/export',
    method: 'get',
    params: query
  })
}
