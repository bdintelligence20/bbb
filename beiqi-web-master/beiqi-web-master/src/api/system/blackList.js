import request from '@/utils/request'

// 查询黑名单
export function listBlackList(query) {
  return request({
    url: '/blacklist/intercept/list',
    method: 'get',
    params: query
  })
}

// 查询黑名单详细
export function getBlackList(id) {
  return request({
    url: '/blacklist/intercept/' + id,
    method: 'get'
  })
}

// 新增黑名单
export function addBlackList(data) {
  return request({
    url: '/blacklist/intercept',
    method: 'post',
    data: data
  })
}

// 删除黑名单
export function delBlackList(id) {
  return request({
    url: '/blacklist/intercept',
    method: 'delete',
    data: id
  })
}
