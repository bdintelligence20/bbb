export default (axios) => ({
  iconList: () => axios({ url: '/website/join/jwelfare', method: 'get' }),
  jobCategory: (data) => axios({ url: '/website/join/jcategory', method: 'get', params: data }),
  jobArea: (data) => axios({ url: '/website/join/area', method: 'get', params: data }),
  jobList: (data) => axios({ url: '/website/join/jlist', method: 'get', params: data }),
  jobDetail: (data) => axios({ url: '/website/join/jdetail', method: 'get', params: data }),
  jobSubmit: (data) => axios({ url: '/website/join/add', method: 'post', data: data }),
})
