import request from '@/utils/request'

// 查询产品分类列表
export function listclass(query) {
  return request({
    url: '/vehicle/class',
    method: 'get',
    params: query
  })
}

// 查询图文特性列表
export function listImgAndText(query) {
  return request({
    url: '/vehicle/characteristic/imgAndText',
    method: 'get',
    params: query
  })
}

// 查询图文轮播列表
export function listImgAndTextList(query) {
  return request({
    url: '/vehicle/specification/imgAndText',
    method: 'get',
    params: query
  })
}

// 查询产品图文特性列表
export function listTextBlock(query) {
  return request({
    url: '/vehicle/characteristic/textBlock',
    method: 'get',
    params: query
  })
}

// 查询产品相册特性列表
export function listGallery(query) {
  return request({
    url: '/vehicle/gallery',
    method: 'get',
    params: query
  })
}

// 查询产品相册列表
export function listGalleryCharacteristic(query) {
  return request({
    url: '/vehicle/characteristic/gallery',
    method: 'get',
    params: query
  })
}

// 查询产品颜色特性列表
export function listColor(query) {
  return request({
    url: '/vehicle/color',
    method: 'get',
    params: query
  })
}

// 查询产品分类详细
export function getclass(classId) {
  return request({
    url: '/vehicle/class/' + classId,
    method: 'get'
  })
}

// 查询图文轮播详细
export function getImgAndTextList(classId) {
  return request({
    url: '/vehicle/specification/imgAndText/' + classId,
    method: 'get'
  })
}

// 查询图文详细
export function getImgAndText(classId) {
  return request({
    url: '/vehicle/characteristic/imgAndText/' + classId,
    method: 'get'
  })
}

// 查询文本块特性详细
export function getTextBlock(classId) {
  return request({
    url: '/vehicle/characteristic/textBlock/' + classId,
    method: 'get'
  })
}

// 查询文本相册详细
export function getGallery(classId) {
  return request({
    url: '/vehicle/gallery/' + classId,
    method: 'get'
  })
}

// 查询文本相册详细
export function getGalleryCharacteristic(classId) {
  return request({
    url: '/vehicle/characteristic/gallery/' + classId,
    method: 'get'
  })
}

// 查询文本相特性标题
export function getGalleryTitle(query) {
  return request({
    url: '/vehicle/characteristic/galleryDetail',
    method: 'get',
    params: query
  })
}

// 查询颜色详细
export function getColor(id) {
  return request({
    url: '/vehicle/color/' + id,
    method: 'get'
  })
}

// 新增产品分类
export function addclass(data) {
  return request({
    url: '/vehicle/class',
    method: 'post',
    data: data
  })
}

// 新增图文特性
export function addImgAndText(data) {
  return request({
    url: '/vehicle/characteristic/imgAndText',
    method: 'post',
    data: data
  })
}

// 新增图文轮播
export function addImgAndTextList(data) {
  return request({
    url: '/vehicle/specification/imgAndText',
    method: 'post',
    data: data
  })
}

// 新增文本块特性
export function addTextBlock(data) {
  return request({
    url: '/vehicle/characteristic/textBlock',
    method: 'post',
    data: data
  })
}

// 新增相册
export function addGallery(data) {
  return request({
    url: '/vehicle/gallery',
    method: 'post',
    data: data
  })
}

// 新增相册
export function addGalleryCharacteristic(data) {
  return request({
    url: '/vehicle/characteristic/gallery',
    method: 'post',
    data: data
  })
}

// 新增颜色
export function addColor(data) {
  return request({
    url: '/vehicle/color',
    method: 'post',
    data: data
  })
}

// 修改产品分类
export function updateclass(data) {
  return request({
    url: '/vehicle/class',
    method: 'put',
    data: data
  })
}

// 修改图文特性
export function updateImgAndText(data) {
  return request({
    url: '/vehicle/characteristic/imgAndText',
    method: 'put',
    data: data
  })
}

// 修改图文轮播
export function updateImgAndTextList(data) {
  return request({
    url: '/vehicle/specification/imgAndText',
    method: 'put',
    data: data
  })
}

// 修改文本块特性
export function updateTextBlock(data) {
  return request({
    url: '/vehicle/characteristic/textBlock',
    method: 'put',
    data: data
  })
}

// 修改相册
export function updateGallery(data) {
  return request({
    url: '/vehicle/gallery',
    method: 'put',
    data: data
  })
}

// 修改相册
export function updateGalleryCharacteristic(data) {
  return request({
    url: '/vehicle/characteristic/gallery',
    method: 'put',
    data: data
  })
}

// 修改相册标题
export function updateGalleryTitle(data) {
  return request({
    url: '/vehicle/characteristic/galleryDetail',
    method: 'put',
    data: data
  })
}

// 修改颜色
export function updateColor(data) {
  return request({
    url: '/vehicle/color',
    method: 'put',
    data: data
  })
}

// 删除产品分类
export function delclass(classId) {
  return request({
    url: '/vehicle/class/' + classId,
    method: 'delete'
  })
}

// 删除图文特性
export function delImgAndText(id) {
  return request({
    url: '/vehicle/characteristic/imgAndText/' + id,
    method: 'delete'
  })
}

// 删除图文特性
export function delImgAndTextList(id) {
  return request({
    url: '/vehicle/specification/imgAndText/' + id,
    method: 'delete'
  })
}

// 删除文本块特性
export function delTextBlock(id) {
  return request({
    url: '/vehicle/characteristic/textBlock/' + id,
    method: 'delete'
  })
}

// 删除相册
export function delGallery(id) {
  return request({
    url: '/vehicle/gallery/' + id,
    method: 'delete'
  })
}

// 删除相册
export function delGalleryCharacteristic(id) {
  return request({
    url: '/vehicle/characteristic/gallery/' + id,
    method: 'delete'
  })
}

// 删除相册
export function delColor(id) {
  return request({
    url: '/vehicle/color/' + id,
    method: 'delete'
  })
}

// 导出产品分类
export function exportclass(query) {
  return request({
    url: '/vehicle/class/export',
    method: 'get',
    params: query
  })
}
