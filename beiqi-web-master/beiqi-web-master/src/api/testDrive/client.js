/**
 * Author: SHI
 * Date: 2022/7/19
 * Remark: client
 */
import request from '@/utils/request'

// 试驾客户列表
export function getClientList(query) {
  return request({
    url: '/testdrive/commit',
    method: 'get',
    params: query
  })
}
