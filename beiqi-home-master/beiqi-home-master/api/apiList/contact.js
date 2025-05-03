export default (axios) => ({
  mediaContact: (data) => axios({ url: '/contact/media', method: 'post', data }),
  subscribeContact: (data) => axios({ url: '/contact/subscribe', method: 'post', data }),
  contactUs: (data) => axios({ url: '/contact/us', method: 'post', data }),
})
