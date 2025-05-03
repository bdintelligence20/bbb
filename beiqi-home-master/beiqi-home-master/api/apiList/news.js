export default (axios) => ({
  // 获取新闻列表
  getNewsList: (data) => axios({ url: '/website/news/nlist', method: 'get', params: data }),

  //获取新闻详情
  getNewsDetail: (data) => axios({ url: '/website/news/ndetail', method: 'get', params: data }),

  // 获取 about 子菜单详情 (Overview Our Vision)
  getAboutSubMenu: (data) => axios({ url: '/website/news/odetail', method: 'get', params: data }),

  // 获取 about 子菜单详情 (history)
  getAboutSubMenuHistory: (data) => axios({ url: '/website/about/history', method: 'post', params: data }),

  // 获取 about 子菜单详情 (Social Responsibility)
  getAboutSubMenuSocial: (data) => axios({ url: '/website/about/social', method: 'post', params: data }),

  // 获取 multimedia 列表
  getMultimediaList: (data) => axios({ url: '/multimedia/v3', method: 'get', params: data }),

  // 获取 about 子菜单详情 (after-sale) 售后服务
  getAboutSubMenuAfterSale: (data) => axios({ url: '/website/about/asale', method: 'post', params: data }),

  // 获取 about 子菜单 (after-sale) 售后服务----国家列表
  getAboutSubMenuAfterSaleCountryList: (data) => axios({ url: '/website/about/acountry', method: 'post', params: data }),
})
