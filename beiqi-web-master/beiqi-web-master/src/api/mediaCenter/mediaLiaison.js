/**
 * Author: SHI
 * Date: 2022/7/19
 * Remark: mediaLiaison
 */
import request from '@/utils/request'

// 媒体联络列表
export function getMediaLiaisonList(query) {
  return request({
    url: '/contact/media',
    method: 'get',
    params: query
  })
}
