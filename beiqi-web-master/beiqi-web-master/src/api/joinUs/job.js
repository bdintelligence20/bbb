import request from '@/utils/request'

// 查询招聘岗位列表
export function listJob(query) {
  return request({
    url: '/website/title/list',
    method: 'get',
    params: query
  })
}

// 查询招聘岗位详细
export function getJob(id) {
  return request({
    url: '/website/title/' + id,
    method: 'get'
  })
}

// 新增招聘岗位
export function addJob(data) {
  return request({
    url: '/website/title',
    method: 'post',
    data: data
  })
}

// 修改招聘岗位
export function updateJob(data) {
  return request({
    url: '/website/title',
    method: 'put',
    data: data
  })
}

// 删除招聘岗位
export function delJob(id) {
  return request({
    url: '/website/title/' + id,
    method: 'delete'
  })
}

