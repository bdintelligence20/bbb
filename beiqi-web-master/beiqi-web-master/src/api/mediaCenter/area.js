import request from '@/utils/request'

// 查询电影分类列表
export function listArea(query) {
  return request({
    url: '/area',
    method: 'get',
    params: query
  })
}

// 查询电影分类详细
export function getArea(areaId) {
  return request({
    url: '/area/' + areaId,
    method: 'get'
  })
}

// 新增电影分类
export function addArea(data) {
  return request({
    url: '/area',
    method: 'post',
    data: data
  })
}

// 修改电影分类
export function updateArea(data) {
  return request({
    url: '/area',
    method: 'put',
    data: data
  })
}

// 删除电影分类
export function delArea(areaId) {
  return request({
    url: '/area/' + areaId,
    method: 'delete'
  })
}
