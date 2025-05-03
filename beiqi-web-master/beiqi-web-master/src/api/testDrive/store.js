import request from '@/utils/request'

// 查询店铺分类列表
export function liststore(query) {
  return request({
    url: '/testdrive/area/store',
    method: 'get',
    params: query
  })
}

// 查询店铺分类详细
export function getstore(storeId) {
  return request({
    url: '/testdrive/area/store/' + storeId,
    method: 'get'
  })
}

// 查询区域树
export function getarea() {
  return request({
    url: '/testdrive/area',
    method: 'get'
  })
}

// 查询所有车型
export function getAllCartModel() {
  return request({
    url: '/testdrive/vehicle/class',
    method: 'get'
  })
}


// 查询门店关联车型
export function getAreClass(storeId) {
  return request({
    url: '/testdrive/area/vehicle/class',
    params: {
      storeId
    },
    method: 'get'
  })
}

// 新增店铺分类
export function addAreClass(data) {
  return request({
    url: '/testdrive/area/vehicle/class',
    method: 'post',
    data: data
  })
}

// 新增店铺分类
export function addstore(data) {
  return request({
    url: '/testdrive/area/store',
    method: 'post',
    data: data
  })
}

// 修改店铺分类
export function updatestore(data) {
  return request({
    url: '/testdrive/area/store',
    method: 'put',
    data: data
  })
}

// 删除店铺分类
export function delstore(storeId) {
  return request({
    url: '/testdrive/area/store/' + storeId,
    method: 'delete'
  })
}

// 导出店铺分类
export function exportstore(query) {
  return request({
    url: '/testdrive/area/store/export',
    method: 'get',
    params: query
  })
}
