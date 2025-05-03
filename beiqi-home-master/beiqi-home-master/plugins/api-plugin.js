import apis from "@/api/index";
export default (ctx, inject) => {

  var apiObject = {};
  for (var i in apis) {
    apiObject[i] = apis[i](ctx.$axios);
  }
 
  //文档: https://www.nuxtjs.cn/guide/plugins
  //inject:注入到服务端context, vue实例, vuex中
  inject('api', apiObject)

}