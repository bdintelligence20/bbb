import request from '@/utils/request'

// 查询新闻文章列表
export function listArticle(query) {
  return request({
    url: '/system/news/list',
    method: 'get',
    params: query
  })
}

// 查询新闻文章详细
export function getArticle(articleId) {
  return request({
    url: '/system/news/' + articleId,
    method: 'get'
  })
}

// 新增新闻文章
export function addArticle(data) {
  return request({
    url: '/system/news',
    method: 'post',
    data: data
  })
}

// 修改新闻文章
export function updateArticle(data) {
  return request({
    url: '/system/news',
    method: 'put',
    data: data
  })
}

// 删除新闻文章
export function delArticle(articleId) {
  return request({
    url: '/system/news/' + articleId,
    method: 'delete'
  })
}
