import request from '@/utils/request'

// 查询首页新闻列表
export function listNews(query) {
  return request({
    url: '/website/news/list',
    method: 'get',
    params: query
  })
}

// 查询首页新闻详细
export function getNewsDetail(id) {
  return request({
    url: '/website/news/' + id,
    method: 'get'
  })
}

export function getNews() {
  return request({
    url: '/website/news',
    method: 'get'
  })
}

// 新增首页新闻
export function addNews(data) {
  return request({
    url: '/website/news',
    method: 'post',
    data: data
  })
}

// 修改首页新闻
export function updateNews(data) {
  return request({
    url: '/website/news',
    method: 'put',
    data: data
  })
}

// 删除首页新闻
export function delNews(id) {
  return request({
    url: '/website/news/' + id,
    method: 'delete'
  })
}

