(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-59956cf7"],{"04d1":function(e,t,a){"use strict";var r=a("342f"),n=r.match(/firefox\/(\d+)/i);e.exports=!!n&&+n[1]},"4e82":function(e,t,a){"use strict";var r=a("23e7"),n=a("e330"),i=a("59ed"),o=a("7b0b"),l=a("07fa"),s=a("083a"),c=a("577e"),m=a("d039"),u=a("addb"),d=a("a640"),p=a("04d1"),h=a("d998"),f=a("2d00"),v=a("512ce"),g=[],b=n(g.sort),y=n(g.push),w=m((function(){g.sort(void 0)})),k=m((function(){g.sort(null)})),x=d("sort"),P=!m((function(){if(f)return f<70;if(!(p&&p>3)){if(h)return!0;if(v)return v<603;var e,t,a,r,n="";for(e=65;e<76;e++){switch(t=String.fromCharCode(e),e){case 66:case 69:case 70:case 72:a=3;break;case 68:case 71:a=4;break;default:a=2}for(r=0;r<47;r++)g.push({k:t+r,v:a})}for(g.sort((function(e,t){return t.v-e.v})),r=0;r<g.length;r++)t=g[r].k.charAt(0),n.charAt(n.length-1)!==t&&(n+=t);return"DGBEFHACIJK"!==n}})),_=w||!k||!x||!P,S=function(e){return function(t,a){return void 0===a?-1:void 0===t?1:void 0!==e?+e(t,a)||0:c(t)>c(a)?1:-1}};r({target:"Array",proto:!0,forced:_},{sort:function(e){void 0!==e&&i(e);var t=o(this);if(P)return void 0===e?b(t):b(t,e);var a,r,n=[],c=l(t);for(r=0;r<c;r++)r in t&&y(n,t[r]);u(n,S(e)),a=l(n),r=0;while(r<a)t[r]=n[r++];while(r<c)s(t,r++);return t}})},"512ce":function(e,t,a){"use strict";var r=a("342f"),n=r.match(/AppleWebKit\/(\d+)\./);e.exports=!!n&&+n[1]},d998:function(e,t,a){"use strict";var r=a("342f");e.exports=/MSIE|Trident/.test(r)},e74c:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,inline:!0,"label-wvehicleTypeIdth":"68px"}},[a("el-form-item",{attrs:{label:"区域编码",prop:"vehicleTypeId"}},[a("el-input",{attrs:{placeholder:"请输入区域编码",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.vehicleTypeId,callback:function(t){e.$set(e.queryParams,"vehicleTypeId",t)},expression:"queryParams.vehicleTypeId"}})],1),a("el-form-item",{attrs:{label:"区域名称",prop:"name"}},[a("el-input",{attrs:{placeholder:"请输入区域名称",clearable:"",size:"small"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.name,callback:function(t){e.$set(e.queryParams,"name",t)},expression:"queryParams.name"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("el-col",{attrs:{span:1.5}},[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["area:manage:add"],expression:"['area:manage:add']"}],attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1),a("el-col",{attrs:{span:1.5}},[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["area:manage:update"],expression:"['area:manage:update']"}],attrs:{type:"success",plain:"",icon:"el-icon-edit",size:"mini",disabled:e.single},on:{click:e.handleUpdate}},[e._v("修改")])],1),a("el-col",{attrs:{span:1.5}},[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["area:manage:delete"],expression:"['area:manage:delete']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:e.multiple},on:{click:e.handleDelete}},[e._v("删除")])],1),a("el-col",{attrs:{span:1.5}},[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["testDrive:storeCartModel:export"],expression:"['testDrive:storeCartModel:export']"}],attrs:{type:"warning",plain:"",icon:"el-icon-download",size:"mini"},on:{click:e.handleExport}},[e._v("导出")])],1),a("right-toolbar",{attrs:{"show-search":e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.typeList},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",wvehicleTypeIdth:"55",align:"center"}}),a("el-table-column",{attrs:{label:"区域编号",align:"center",prop:"id"}}),a("el-table-column",{attrs:{label:"区域标题",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(t.row.langs[0].name)+" ")]}}])}),a("el-table-column",{attrs:{label:"区域顺序",align:"center",prop:"sort"}}),a("el-table-column",{attrs:{label:"邮箱",align:"center",prop:"emails"}}),a("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime",wvehicleTypeIdth:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.parseTime(t.row.createTime)))])]}}])}),a("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-wvehicleTypeIdth"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["area:manage:update"],expression:"['area:manage:update']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(a){return e.handleUpdate(t.row)}}},[e._v("修改")]),a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["area:manage:delete"],expression:"['area:manage:delete']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("删除")])]}}])})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}}),a("el-dialog",{attrs:{title:e.title,visible:e.open,"append-to-body":"",width:"600px"},on:{"update:visible":function(t){e.open=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"150px"}},[a("el-form-item",{attrs:{label:"英语区域标题",prop:"nameen"}},[a("el-input",{attrs:{placeholder:"英语区域标题"},model:{value:e.form.nameen,callback:function(t){e.$set(e.form,"nameen",t)},expression:"form.nameen"}})],1),a("el-form-item",{attrs:{label:"西语区域标题",prop:"namees"}},[a("el-input",{attrs:{placeholder:"英语区域标题"},model:{value:e.form.namees,callback:function(t){e.$set(e.form,"namees",t)},expression:"form.namees"}})],1),a("el-form-item",{attrs:{label:"阿语区域标题",prop:"namear"}},[a("el-input",{attrs:{placeholder:"英语区域标题"},model:{value:e.form.namear,callback:function(t){e.$set(e.form,"namear",t)},expression:"form.namear"}})],1),a("el-form-item",{attrs:{label:"邮箱",prop:"emails"}},[a("el-input",{attrs:{placeholder:"邮箱"},model:{value:e.form.emails,callback:function(t){e.$set(e.form,"emails",t)},expression:"form.emails"}})],1),a("el-form-item",{attrs:{label:"区域顺序",prop:"sort"}},[a("el-input-number",{attrs:{"controls-position":"right",min:0},model:{value:e.form.sort,callback:function(t){e.$set(e.form,"sort",t)},expression:"form.sort"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),a("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1),a("el-dialog",{attrs:{title:"任务详细",visible:e.openView,"wvehicle-class-idth":"700px","append-to-body":""},on:{"update:visible":function(t){e.openView=t}}},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.itemLoading,expression:"itemLoading"}],attrs:{data:e.typeItemList}},[a("el-table-column",{attrs:{label:"区域id",align:"center",prop:"id"}}),a("el-table-column",{attrs:{label:"区域名称",align:"center",prop:"name"}}),a("el-table-column",{attrs:{label:"区域地址",align:"center",prop:"address"}}),a("el-table-column",{attrs:{label:"区域邮件",align:"center",prop:"mails"}}),a("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime","wvehicle-class-idth":"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.parseTime(t.row.createTime)))])]}}])})],1)],1)],1)},n=[],i=a("5530"),o=(a("caad"),a("2532"),a("d81d"),a("b0c0"),a("4e82"),a("b775"));function l(e){return Object(o["a"])({url:"/area",method:"get",params:e})}function s(e){return Object(o["a"])({url:"/area/"+e,method:"get"})}function c(e){return Object(o["a"])({url:"/area",method:"post",data:e})}function m(e){return Object(o["a"])({url:"/area",method:"put",data:e})}function u(e){return Object(o["a"])({url:"/area/"+e,method:"delete"})}var d={name:"StoreCartModel",dicts:["sys_normal_disable"],data:function(){return{loading:!0,itemLoading:!0,vehicleTypeIds:[],single:!0,typeOption:[{value:1,label:"预约试驾"},{value:0,label:"不需要预约试驾"}],multiple:!0,showSearch:!0,total:0,itemTotal:0,typeList:[],typeItemList:[],title:"",open:!1,openView:!1,addressOption:[],addressProps:{value:"id",label:"name",children:"nodes",emitPath:!1},queryParams:{pageNum:1,pageSize:10,type:this.$route.path.includes("country/Area")?2:1,id:void 0,name:void 0},form:{type:1},editResData:{},rules:{sort:[{required:!0,message:"区域顺序",trigger:"blur"}],nameen:[{required:!0,message:"英语区域标题不能为空",trigger:"blur"}],namees:[{required:!0,message:"西语区域标题不能为空",trigger:"blur"}],namear:[{required:!0,message:"阿语区域标题不能为空",trigger:"blur"}],imgUrl:[{required:!0,message:"特征车辆图片",trigger:"blur"}]}}},created:function(){this.getList()},watch:{$route:function(){this.getList()}},methods:{getList:function(){var e=this;this.loading=!0,l(this.queryParams).then((function(t){e.typeList=t.data,e.total=t.data.length,e.loading=!1}))},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={nameen:void 0,namees:void 0,emails:void 0,namear:void 0,type:void 0,sort:0,remark:void 0},this.resetForm("form")},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleSelectionChange:function(e){this.id=e.map((function(e){return e.id})),this.single=1!==e.length,this.multiple=!e.length},handleAdd:function(){this.reset(),this.open=!0,this.title="新增区域"},addFormatData:function(){var e=this,t=["en","ar","es"],a=[];t.map((function(t){a.push({name:e.form["name"+t],lang:t})})),this.form.langs=a},editButtonFormatData:function(e){var t={};return e.langs.map((function(e){t["name"+e.lang]=e.name})),t.emails=e.emails,t.sort=e.sort,t.id=e.id,t.type=e.type,t.createTime=e.createTime,t},editSubtimeFormatData:function(e){var t=this,a=["en","ar","es"];return a.map((function(a){e.langs.map((function(e){e.lang===a&&(e.name=t.form["name"+a])}))})),e.emails=this.form.emails,e.sort=this.form.sort,e.type=this.form.type,e},handleUpdate:function(e){var t=this;this.reset();var a=e.id||this.id;s(a).then((function(e){t.form=t.editButtonFormatData(e.data),t.editResData=e.data,t.open=!0,t.title="修改区域"}))},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(void 0!==e.form.id&void 0!==e.form.createTime?(e.form=e.editSubtimeFormatData(e.editResData),e.form.type=e.$route.path.includes("country/Area")?2:1,m(e.form).then((function(t){e.$modal.msgSuccess(t.msg),e.open=!1,e.getList()}))):(e.addFormatData(),e.form.type=e.$route.path.includes("country/Area")?2:1,c(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.open=!1,e.getList()}))))}))},handleDelete:function(e){var t=this,a=e.id;this.$modal.confirm('是否确认删除区域编号为"'+e.id+'"的数据项？').then((function(){return u(a)})).then((function(){t.getList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},handleExport:function(){this.download("system/post/export",Object(i["a"])({},this.queryParams),"post_".concat((new Date).getTime(),".xlsx"))}}},p=d,h=a("2877"),f=Object(h["a"])(p,r,n,!1,null,null,null);t["default"]=f.exports}}]);