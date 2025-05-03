import request from '@/utils/request'

// 查询店铺车型分类列表
export function listclass(query) {
  return request({
    url: '/testdrive/vehicle/class',
    method: 'get',
    params: query
  })
}

// 查询店铺车型分类详细
export function getclass(classId) {
  return request({
    url: '/testdrive/vehicle/class/' + classId,
    method: 'get'
  })
}

// 查询店铺车型分类详细
export function getvehicle(classId) {
  return request({
    url: '/testdrive/vehicle',
    method: 'get'
  })
}

// 新增店铺车型分类
export function addclass(data) {
  return request({
    url: '/testdrive/vehicle/class',
    method: 'post',
    data: data
  })
}

// 修改店铺车型分类
export function updateclass(data) {
  return request({
    url: '/testdrive/vehicle/class',
    method: 'put',
    data: data
  })
}

// 删除店铺车型分类
export function delclass(classId) {
  return request({
    url: '/testdrive/vehicle/class/' + classId,
    method: 'delete'
  })
}

// 导出店铺车型分类
export function exportclass(query) {
  return request({
    url: '/testdrive/vehicle/class/export',
    method: 'get',
    params: query
  })
}
