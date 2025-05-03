import request from '@/utils/request'

// 查询限流注解配置列表
export function listLimiter(query) {
  return request({
    url: '/system/limiter/list',
    method: 'get',
    params: query
  })
}

// 查询限流注解配置详细
export function getLimiter(id) {
  return request({
    url: '/system/limiter/' + id,
    method: 'get'
  })
}

// 修改限流注解配置
export function updateLimiter(data) {
  return request({
    url: '/system/limiter',
    method: 'put',
    data: data
  })
}
