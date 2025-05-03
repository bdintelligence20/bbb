import request from '@/utils/request'

// 查询售后页面card列表
export function listCard(query) {
  return request({
    url: '/system/intro/list',
    method: 'get',
    params: query
  })
}

// 查询售后页面card详细
export function getCard(bannerId) {
  return request({
    url: '/system/intro/' + bannerId,
    method: 'get'
  })
}

// 新增售后页面card
export function addCard(data) {
  return request({
    url: '/system/intro',
    method: 'post',
    data: data
  })
}

// 修改售后页面card
export function updateCard(data) {
  return request({
    url: '/system/intro',
    method: 'put',
    data: data
  })
}

// 删除售后页面card
export function delCard(bannerId) {
  return request({
    url: '/system/intro/' + bannerId,
    method: 'delete'
  })
}
