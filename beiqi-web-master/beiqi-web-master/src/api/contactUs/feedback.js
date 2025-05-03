/**
 * Author: SHI
 * Date: 2022/7/19
 * Remark: feedback
 */
import request from '@/utils/request'

// 试驾客户列表
export function getFeedbackList(query) {
  return request({
    url: '/contact/us',
    method: 'get',
    params: query
  })
}
