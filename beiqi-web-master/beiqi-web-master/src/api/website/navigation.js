import request from '@/utils/request'

// 查询导航列表
export function listMenu(query) {
  return request({
    url: '/system/category/list',
    method: 'get',
    params: query
  })
}

// 查询导航详细
export function getMenu(categoryId) {
  return request({
    url: '/system/category/' + categoryId,
    method: 'get'
  })
}

// 查询导航下拉树结构
export function treeselect(query) {
  return request({
    url: '/system/category/tree/',
    method: 'get',
    params: query
  })
}



// 新增导航
export function addMenu(data) {
  return request({
    url: '/system/category',
    method: 'post',
    data: data
  })
}

// 修改导航
export function updateMenu(data) {
  return request({
    url: '/system/category',
    method: 'put',
    data: data
  })
}

// 删除导航
export function delMenu(menuId) {
  return request({
    url: '/system/category/' + menuId,
    method: 'delete'
  })
}


// 导出导航
export function exportMenu(query) {
  return request({
    url: '/system/category/export',
    method: 'post',
    data: query
  })
}