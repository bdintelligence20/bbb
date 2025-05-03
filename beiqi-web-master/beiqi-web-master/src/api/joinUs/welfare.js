import request from '@/utils/request'

// 查询招聘福利列表
export function listWelfare(query) {
  return request({
    url: '/website/welfare/list',
    method: 'get',
    params: query
  })
}

// 查询招聘福利详细
export function getWelfare(id) {
  return request({
    url: '/website/welfare/' + id,
    method: 'get'
  })
}

// 新增招聘福利
export function addWelfare(data) {
  return request({
    url: '/website/welfare',
    method: 'post',
    data: data
  })
}

// 修改招聘福利
export function updateWelfare(data) {
  return request({
    url: '/website/welfare',
    method: 'put',
    data: data
  })
}

// 删除招聘岗位
export function delWelfare(id) {
  return request({
    url: '/website/welfare/' + id,
    method: 'delete'
  })
}

