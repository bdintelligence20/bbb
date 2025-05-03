import request from '@/utils/request'

// 查询售后页面banner列表
export function listBanner(query) {
  return request({
    url: '/system/scontent/list',
    method: 'get',
    params: query
  })
}

// 查询售后页面banner详细
export function getBanner(bannerId) {
  return request({
    url: '/system/scontent/' + bannerId,
    method: 'get'
  })
}

// 新增售后页面banner
export function addBanner(data) {
  return request({
    url: '/system/scontent',
    method: 'post',
    data: data
  })
}

// 修改售后页面banner
export function updateBanner(data) {
  return request({
    url: '/system/scontent',
    method: 'put',
    data: data
  })
}

// 删除售后页面banner
export function delBanner(bannerId) {
  return request({
    url: '/system/scontent/' + bannerId,
    method: 'delete'
  })
}

