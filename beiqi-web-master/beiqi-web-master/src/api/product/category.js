import request from '@/utils/request'

// 查询产品分类列表
export function listtype(query) {
  return request({
    url: '/vehicle/type',
    method: 'get',
    params: query
  })
}

// 查询产品分类详细
export function gettype(typeId) {
  return request({
    url: '/vehicle/type/' + typeId,
    method: 'get'
  })
}

// 新增产品分类
export function addtype(data) {
  return request({
    url: '/vehicle/type',
    method: 'post',
    data: data
  })
}

// 修改产品分类
export function updatetype(data) {
  return request({
    url: '/vehicle/type',
    method: 'put',
    data: data
  })
}

// 删除产品分类
export function deltype(typeId) {
  return request({
    url: '/vehicle/type/' + typeId,
    method: 'delete'
  })
}

// 导出产品分类
export function exporttype(query) {
  return request({
    url: '/vehicle/type/export',
    method: 'get',
    params: query
  })
}
