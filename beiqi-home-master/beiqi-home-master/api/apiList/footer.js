export default (axios) => ({
  menuList: (data) => axios({ url: '/index/tree', method: 'get', params: data }),
  getWebConfig: (data) => axios({ url: '/website/webConfig/getWebConfig', method: 'post', data: data }),
  newsList: (data) => axios({ url: '/website/news/rlist/', method: 'get', params: data }),
})
