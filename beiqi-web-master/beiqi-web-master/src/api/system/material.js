/*
 * @Date: 2023-08-15 19:06:42
 * @LastEditors: liu
 * @LastEditTime: 2023-08-16 14:40:15
 */
import request from '@/utils/request'

// 查询素材列表
export function listMaterial(query) {
  return request({
    url: '/system/material/list',
    method: 'get',
    params: query
  })
}

// 查询素材详细
export function getMaterial(materialId) {
  return request({
    url: '/system/material/' + materialId,
    method: 'get'
  })
}

// 新增素材
export function addMaterial(data) {
  return request({
    url: '/system/material/',
    method: 'post',
    data: data
  })
}

// 修改素材
export function updateMaterial(data) {
  return request({
    url: '/system/material/',
    method: 'put',
    data: data
  })
}

// 删除素材
export function delMaterial(materialId) {
  return request({
    url: '/system/material/' + materialId,
    method: 'delete'
  })
}

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}
