import request from '@/utils/request'

// 查询招聘岗位列表
export function listJobCategory(query) {
  return request({
    url: '/website/category/list',
    method: 'get',
    params: query
  })
}

// 查询招聘岗位详细
export function getJobCategory(id) {
  return request({
    url: '/website/category/' + id,
    method: 'get'
  })
}

// 新增招聘岗位
export function addJobCategory(data) {
  return request({
    url: '/website/category',
    method: 'post',
    data: data
  })
}

// 修改招聘岗位
export function updateJobCategory(data) {
  return request({
    url: '/website/category',
    method: 'put',
    data: data
  })
}

// 删除招聘岗位
export function delJobCategory(id) {
  return request({
    url: '/website/category/' + id,
    method: 'delete'
  })
}

