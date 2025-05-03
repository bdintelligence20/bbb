import request from '@/utils/request'

// 查询黑名单拦截日志
export function listBlackListLog(query) {
  return request({
    url: '/monitor/log/list',
    method: 'get',
    params: query
  })
}
