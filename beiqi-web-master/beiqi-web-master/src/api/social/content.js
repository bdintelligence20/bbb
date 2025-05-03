import request from '@/utils/request'

// 查询社会责任内容列表
export function listContent(query) {
  return request({
    url: '/system/lcontent/list',
    method: 'get',
    params: query
  })
}

// 查询社会责任内容详细
export function getContent(id) {
  return request({
    url: '/system/lcontent/' + id,
    method: 'get'
  })
}

// 新增社会责任内容
export function addContent(data) {
  return request({
    url: '/system/lcontent',
    method: 'post',
    data: data
  })
}

// 修改社会责任内容
export function updateContent(data) {
  return request({
    url: '/system/lcontent',
    method: 'put',
    data: data
  })
}

// 删除社会责任内容
export function delContent(id) {
  return request({
    url: '/system/lcontent/' + id,
    method: 'delete'
  })
}

