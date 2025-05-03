/**
 * Author: SHI
 * Date: 2022/6/22
 * Remark: enums
 */

// 接口返回状态码
export const RESPONSE_CODE = {
  SUCCESS: 200, // "成功"
  INVALID_PARAMETER: 400, // "参数不合法"
  FILE_FORMAT_ERROR: 402, // "数据内容有误"
  ACCESS_DENY: 403, // "用户权限不足"
  AUTH_FAIL: 405, // "当前登录已失效，请重新登录"
  USERNAME_NOT_EXIST: 406, // "账号不存在"
  FILE_UPLOAD_OVER: 408, // "上传文件出错"
  FILE_DOWNLOAD_OVER: 410, // "下载文件出错"
  SERVER_EXCEPTION: 500, // "服务开小差了，请稍后再试~"
  RESULT_NULL: 504, // "查询结果为空"
};
