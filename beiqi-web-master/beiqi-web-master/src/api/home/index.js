import request from '@/utils/request'

// 查询首页展示位列表
export function listSection(query) {
  return request({
    url: '/website/location/list',
    method: 'get',
    params: query
  })
}

// 查询首页展示位详细
export function getSectionDetail(bannerId) {
  return request({
    url: '/website/location/' + bannerId,
    method: 'get'
  })
}

export function getSection() {
  return request({
    url: '/website/location',
    method: 'get'
  })
}

// 新增首页展示位
export function addSection(data) {
  return request({
    url: '/website/location',
    method: 'post',
    data: data
  })
}

// 修改首页展示位
export function updateSection(data) {
  return request({
    url: '/website/location',
    method: 'put',
    data: data
  })
}

// 删除首页展示位
export function delSection(bannerId) {
  return request({
    url: '/website/location/' + bannerId,
    method: 'delete'
  })
}

