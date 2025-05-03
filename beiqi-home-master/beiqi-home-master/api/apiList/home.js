export default (axios) => ({
  menuList: (data) => axios({ url: '/index/tree', method: 'get', params: data }),
  getBanner: (data) => axios({ url: '/index/getAllBannerList', method: 'get', params: data }),
  productNavList: (data) => axios({ url: '/vehicle/class', method: 'get', params: data }),
  getSection: (data) => axios({ url: '/index/show', method: 'get', params: data }),
  getCarlist: (data) => axios({ url: '/index/class/home', method: 'get', params: data }),
  // 获取首页新闻
  getNews: (data) => axios({ url: '/index/newsShow', method: 'get', params: data }),
  getMap: (data) => axios({ url: '/contact/country', method: 'get', params: data }),
})
