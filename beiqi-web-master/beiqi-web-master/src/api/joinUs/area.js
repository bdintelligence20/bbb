import request from '@/utils/request'

// 查询招聘区域列表
export function listArea(query) {
  return request({
    url: '/website/area/list',
    method: 'get',
    params: query
  })
}

// 查询招聘区域详细
export function getArea(id) {
  return request({
    url: '/website/area/' + id,
    method: 'get'
  })
}

// 新增招聘区域
export function addArea(data) {
  return request({
    url: '/website/area',
    method: 'post',
    data: data
  })
}

// 修改招聘区域
export function updateArea(data) {
  return request({
    url: '/website/area',
    method: 'put',
    data: data
  })
}

// 删除招聘区域
export function delArea(id) {
  return request({
    url: '/website/area/' + id,
    method: 'delete'
  })
}

