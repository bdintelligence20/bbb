import request from '@/utils/request'

// 查询售后页面icon列表
export function listIcon(query) {
  return request({
    url: '/system/item/list',
    method: 'get',
    params: query
  })
}

// 查询售后页面icon详细
export function getIcon(id) {
  return request({
    url: '/system/item/' + id,
    method: 'get'
  })
}

// 新增售后页面icon
export function addIcon(data) {
  return request({
    url: '/system/item',
    method: 'post',
    data: data
  })
}

// 修改售后页面icon
export function updateIcon(data) {
  return request({
    url: '/system/item',
    method: 'put',
    data: data
  })
}

// 删除售后页面icon
export function delIcon(bannerId) {
  return request({
    url: '/system/item/' + bannerId,
    method: 'delete'
  })
}
