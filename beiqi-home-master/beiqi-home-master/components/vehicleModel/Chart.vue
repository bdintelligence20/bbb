<template>
  <div :class="mobileClass">
    <div ref="chart"  id="chartContainer" class="chart"></div>
    <img src="~/assets/image/vehicleModel/close.png" alt="" class="close_icon" @click="handleClose" />
  </div>
</template>

<script>

var option = {
  animation: true, // 是否开启动画
  animationDuration: 1000, // 动画持续时间
  animationEasing: 'cubicOut', // 动画缓动函数
  tooltip: {
    trigger: 'none', // 禁用鼠标悬停效果

  },

  // pixelRatio: 2, // 尝试调整这个值，以改善抗锯齿效果
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: ['70%', '100%'],
      hoverAnimation: false,
      avoidLabelOverlap: false,
      startAngle: 90,
      itemStyle: {
        borderRadius: 2,
      },
      label: {
        show: false,
        position: 'center',
      },
      data: [],
      emphasis:{
        disabled:true
      },
      animation: "expansion", // 启用过渡动画
    },
    {
      name: 'Access From',
      type: 'pie',
      radius: ['60%', '80%'],
      hoverAnimation: false,
      avoidLabelOverlap: false,
      startAngle: 90,
      itemStyle: {
        borderRadius: 2,
      },
      emphasis:{
        disabled:true
      },
      label: {
        show: false,
        position: 'center',
      },
      data: [],
      animation: true, // 启用过渡动画
    },
    {
      name: '内环1',
      type: 'pie',
      clockwise: !0,
      hoverAnimation: !1,
      avoidLabelOverlap: !1,
      startAngle: 90,
      radius: ['28%', '52%'],
      center: ['50%', '50%'],
      label: {
        normal: {
          show: !1,
        },
      },
      data: [{ value: 1048, name: 'Search Engine', itemStyle: {  color: 'rgba(0,0,0,0.5)' } }],
    },
 
  ],
}
export default {
  props:{
    optionColor:{
      type:Object,
      default:null
    },
    isShow:{
      type:Boolean,
      default:false
    }
  },
  data() {
    return {
      myChart: null,
      beforeIndex:0,
      colorList: [
        {
          value: 47.5,
          name: '灰色',
          itemStyle: {
            color: "#d4d4d4",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: '黑色 + #d4d4d4',
          itemStyle: {
            color:  '#000000' ,
          },
        },

        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: '#757575',
          itemStyle: {
            color: "#757575",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: '黑色 + #757575',          itemStyle: {
            color:'#000000',
          }, },
          { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: '黑色', itemStyle: { color: '#000000' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: '黑色+ffffff', itemStyle: {
            color: '#000000',
          } },
   
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: '白色', itemStyle: { color: '#ffffff' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
      

        { value: 5, name: '红色', itemStyle: { color: '#b1303a' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零

        { value: 5, name: '蓝色', itemStyle: { color: '#407dc0' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
      ],
      colorList_2: [
        {
          value: 47.5,
          name: 'Search Engine',
          itemStyle: {
            color: "#d4d4d4",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: 'Search Engine',
          itemStyle: {
            color:'#d4d4d4'
          },
        },

        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: 'Search Engine',
          itemStyle: {
            color: "#757575",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine',          itemStyle: {
            color:'#757575'
          }, },
          { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: { color: '#000000' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: {
            color: '#ffffff' ,
          } },

       
   
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: { color: '#ffffff' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
      
      
        { value: 5, name: '红色', itemStyle: { color: '#b1303a' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零

        { value: 5, name: '蓝色', itemStyle: { color: '#407dc0' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
 
      ]
    }
  },
  mounted() {

    if(this.optionColor){
      option = this.optionColor
    }else{
      option.series[0].data = this.colorList
      option.series[1].data = this.colorList_2
    }
    // 绘制图表
    this.initChart()
  },
  methods: {
    initChart() {
      // 基于准备好的dom，初始化echarts实例
      var chartDom = document.getElementById('chartContainer')

      this.myChart = this.$echarts.init(chartDom)
      
      this.myChart.beforeIndex = this.optionColor?.beforeIndex||0
      this.myChart.indexIndex = this.optionColor?.indexIndex||0
      this.myChart.updatStartAngle = this.optionColor?.updatStartAngle||0
       this.myChart.setOption(option)
       this.myChart.option = option
      this.myChart.on('click', this.handleChartClick)
      window.myChart = this.myChart;
      console.log(window.myChart)
      window.option = option;
      window.addEventListener('resize', this.handleResize);
      this.myChart.clear()
    },
    handleResize() {
      console.log('resize')
      // 调用 ECharts 的 resize 方法，使图表重新适应容器大小
      this.myChart.resize();
    },
    // 判断旋转角度以及正反方向
    getRotate(e,index) {  

      let a = this.myChart;
      let i = Math.ceil((a.option.series[index].data.length - 1) / 2);
      let n = option.series[index].data.length;
    //  if (e.dataIndex === a.beforeIndex || 0 !== e.seriesIndex || 1 == e.value || -1 == e.data.id) return !1;

     for (var t = [], o = [], s = 1; s < n; s++) {
        s <= i ? o.push(this.getNextIndex(a.beforeIndex, s,index)) : t.push(this.getNextIndex(a.beforeIndex, s,index));
    }
    
      t.reverse();
      let r = o.findIndex((t => t === e.dataIndex));
      if (r > -1) {
          let t = this.getRotationAngle(r + 1, o,index),
              i = o.findIndex((e => 0 === e));
          0 === e.dataIndex || r > i && i > -1 ? a.option.series[index].startAngle += t + 154.6 : a.option.series[index].startAngle += t
      }
      if (r = t.findIndex((t => t === e.dataIndex)), r > -1) {
          let e = t.findIndex((e => 0 === e));
          0 === a.beforeIndex || r > e && e > -1 ? a.option.series[index].startAngle -= 180 + this.getRotationAngle(r, t,index) : a.option.series[index].startAngle -= this.getRotationAngle(r + 1, t,index)
          
      }

      var l = a.option.series[index].data[a.beforeIndex].value;
      a.option.series[index].data[a.beforeIndex].value = a.option.series[index].data[e.dataIndex].value, 
      a.option.series[index].data[e.dataIndex].value = l;
      

      if(index!=1){
        this.getRotate(e,"1")
      }
      
      a.beforeIndex = e.dataIndex, 
      a.indexIndex = a.beforeIndex,

      a.updatStartAngle = a.option.series[index].startAngle
      a.updateData = a.option.series[index].data
      a.option.series[2].startAngle = a.updatStartAngle
      // console.log(a.option,option)
      console.log(a.option===option)
      //  this.myChart.setOption(a.option)
    },
    getRotationAngle (e, t,index) {
      let a = t.slice(0, e),
        i = 0;
      return a.forEach((e => {
        i += option.series[index].data[e].value
      })), 3.625 * i
    },
    getNextIndex (e, t,index) {
	    return ((e + t) % option.series[index].data.length + option.series[index].data.length) % option.series[index].data.length
    },

    handleChartClick(params) {

      if (params.componentType === 'series') {
        // 获取点击的数据项名称
        // 执行您的自定义点击事件操作
        if (params.data.name == 'spacing') return
        if (params.value == 37.5) return

 

        // 获取点击的数据项的索引
        let index = params.dataIndex
        this.getRotate(params,'0')

        console.log(option)
        this.$emit('changeColor', option, index)
        this.myChart.setOption(option,true)
        return

        // 遍历数组将值为37.5的修改为5
        for (const item of this.colorList) {
          if (item.value === 37.5) {
            // 找到目标项，修改值
            item.value = 5
            break // 跳出循环
          }
        }
        for (const item of this.colorList_2) {
          if (item.value === 37.5) {
            // 找到目标项，修改值
            item.value = 5
            break // 跳出循环
          }
        }
          this.colorList[index].value = 37.5
          this.colorList_2[index].value = 37.5


          // let arr_1 = this.colorList.slice(0, index + 1).reverse().concat(this.colorList.slice(index + 1, this.colorList.length));
          // let arr_2 = this.colorList_2.slice(0, index + 1).reverse().concat(this.colorList_2.slice(index + 1, this.colorList_2.length));
          // console.log(this.colorList,arr_1,"this.colorList,arr_1")
          // console.log(this.colorList_2,arr_2,"this.colorList,arr_2")
          // option.series[index].data = arr_1
          // option.series[1].data = arr_2


          // 截取数组中的后几项翻放到数组前面



          option.series[0].data = this.colorList
          option.series[1].data = this.colorList_2

          option.series[0].startAngle = this.returnAngle(index)
          option.series[1].startAngle = this.returnAngle(index)
          option.series[2].startAngle = this.returnAngle(index)
          this.$emit('changeColor', option, index)
 
          this.myChart.setOption(option,true)
      }
    },
    handleClose() {
      this.$emit('closeColor')
    },
    returnAngle(index){
        switch(index){
          case 0:
            return 90
          case 2:
          return 121.5
           break;
          case 4:
            return 151
          break;
          case 6:
            return 178
          break;
          case 8: 
          return 207
          break;
          case 10:
          return 235
          break;
          case 12:
          return -93 
          break;
          default:360;
          break;

        }
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
        } else {
          this.mobileClass = ''
        }
      },
    },
    isShow:{
      handler(n, o) {
        console.log('isShow', n, o)
          if(n){
            this.myChart.resize()
            this.myChart.setOption(option)
            
          }else{
            this.myChart.clear()
          }
      },
    }
  },
  beforeDestroy() {
    this.myChart.clear()
    // 移除点击事件监听
    this.myChart.off('click', this.handleChartClick)
  },
}
</script>

<style lang="scss" scoped>
.close_icon {
  position: absolute;
  width: 5vw;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  cursor: pointer;
}
.chart{
  width: 15vw;
  height: 15vw;
}
.mobile .chart{
  width: 40vw;
  height: 40vw;
}
</style>
