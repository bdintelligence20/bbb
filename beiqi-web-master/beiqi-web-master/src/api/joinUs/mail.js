import request from '@/utils/request'

// 查询招聘区域列表
export function listMail(query) {
  return request({
    url: '/website/mail/list',
    method: 'get',
    params: query
  })
}