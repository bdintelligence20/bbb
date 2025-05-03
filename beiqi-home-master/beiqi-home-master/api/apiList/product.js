export default (axios) => ({
  productInfo: (data) => axios({ url: '/vehicle/class/detail', method: 'get', params: data }),
})
