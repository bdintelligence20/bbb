// /*
//  * @Author: 不二
//  * @Date: 2023-08-31 22:30:39
//  * @LastEditTime: 2023-08-31 22:33:56
//  */
// var a = this,
//     i = Math.ceil((a.option.series[0].data.length - 1) / 2),
//     n = a.option.series[0].data.length;



// this.beforeIndex = 0,
//  this.option && "object" == typeof 
//  this.option && a.myChart.setOption(a.option),

//  this.myChart.on("click", "series", (function (e) {
//     if (e.dataIndex === a.beforeIndex || 0 !== e.seriesIndex || 1 == e.value || -1 == e.data.id) return !1;
//     for (var t = [], o = [], s = 1; s < n; s++) s <= i ? o.push(a.getNextIndex(a.beforeIndex, s)) : t.push(a.getNextIndex(a.beforeIndex, s));
//     t.reverse();
//     let r = o.findIndex((t => t === e.dataIndex));
//     if (r > -1) {
//         let t = a.getRotationAngle(r + 1, o),
//             i = o.findIndex((e => 0 === e));
//         0 === e.dataIndex || r > i && i > -1 ? a.option.series[0].startAngle += t + 154.6 : a.option.series[0].startAngle += t
//     }
//     if (r = t.findIndex((t => t === e.dataIndex)), r > -1) {
//         let e = t.findIndex((e => 0 === e));
//         0 === a.beforeIndex || r > e && e > -1 ? a.option.series[0].startAngle -= 180 + a.getRotationAngle(r, t) : a.option.series[0].startAngle -= a.getRotationAngle(r + 1, t)
//     }
//     var l = a.option.series[0].data[a.beforeIndex].value;
//     a.option.series[0].data[a.beforeIndex].value = a.option.series[0].data[e.dataIndex].value, a.option.series[0].data[e.dataIndex].value = l, a.beforeIndex = e.dataIndex, a.indexIndex = a.beforeIndex, console.log(e.data.id, "changeMaterial"), a.app.fire("changeMaterial", {
//         id: e.data.id,
//         color: e.data.carColor,
//         action: "open"
//     }), a.updatStartAngle = a.option.series[0].startAngle, a.updateData = a.option.series[0].data, a.myChart.setOption(a.option)
// })),





 