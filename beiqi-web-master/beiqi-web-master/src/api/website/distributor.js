import request from '@/utils/request'

// 查询国家经销商列表
export function listCountry(query) {
  return request({
    url: '/contact/country',
    method: 'get',
    params: query
  })
}

// 查询区域列表
export function listArea(query) {
  return request({
    url: '/area/en',
    method: 'get',
    params: query
  })
}

// 查询国家经销商详细
export function getCountry(bannerId) {
  return request({
    url: '/contact/country/' + bannerId,
    method: 'get'
  })
}

// 新增国家经销商
export function addCountry(data) {
  return request({
    url: '/contact/country',
    method: 'post',
    data: data
  })
}

// 修改国家经销商
export function updateCountry(data) {
  return request({
    url: '/contact/country',
    method: 'put',
    data: data
  })
}

// 删除国家经销商
export function delCountry(bannerId) {
  return request({
    url: '/contact/country/' + bannerId,
    method: 'delete'
  })
}
