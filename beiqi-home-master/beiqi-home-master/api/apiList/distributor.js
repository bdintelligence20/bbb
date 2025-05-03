import { getToken } from '~/utils/token'

export default (axios) => ({
  getCode: () => axios({url: '/captchaImage', method: 'get'}),
  materialList: (data) => axios({
    url: '/dealer-download/system/material/getAllMaterialList',
    method: 'get',
    params: data,
    headers: {'Authorization': getToken()}
  }),
  getInfo: (data) => axios({url: '/getInfo', method: 'get', params: data}),
  login: (data) => axios({url: '/login', method: 'post', data}),
  sendCode: (data) => axios({url: '/system/user/profile/retrieve', method: 'post', data}),
  getTypeNewLable: (data) => axios({
    url: '/dealer-download/system/material/getPyteNewLabels',
    method: 'get',
    params: data
  }),
  RetrievePassword: (data) => axios({url: '/system/user/profile/saveRetrieve', method: 'post', data}),
  resetPassword: (data) => axios({url: '/system/user/profile/updatePasswordd', method: 'put', data}),
  materialNew: (data) => axios({url: '/dealer-download/system/material/newLabel', method: 'post', data}),
  // 消除首页和车型页new标
  removeNewLabel: (data) => axios({url: '/dealer-download/system/material/PyteNewLabel', method: 'post', data}),
  // 获取车型
  getModels: (data) => axios({
    url: '/dealer-download/system/material/class',
    method: 'get',
    params: data,
    headers: {'Authorization': getToken()}
  }),
  // 记录下载日志
  recordDownloadLog: (data) => axios({url: '/dealer-download/system/materialLog', method: 'post', params: data}),
})
