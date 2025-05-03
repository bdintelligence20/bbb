import request from '@/utils/request'

// 查询部门列表
export function listBlack(query) {
  return request({
    url: '/system/blockIp',
    method: 'get',
    params: query
  })
}

// 新增部门
export function addBlack(data) {
  return request({
    url: '/system/blockIp',
    method: 'post',
    data: data
  })
}

// 删除部门
export function delBlack(data) {
  return request({
    url: '/system/blockIp',
    method: 'delete',
    data
  })
}
