export default (axios) => ({
  countryList: (data) => axios({ url: '/area', method: 'get', params: data }),
  carList: (data) => axios({ url: '/testdrive/vehicle/class', method: 'get', params: data }),
  submitForm: (data) => axios({ url: '/testdrive/commit', method: 'post', data }),
})