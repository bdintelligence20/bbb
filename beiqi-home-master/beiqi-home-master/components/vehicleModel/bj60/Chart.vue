<template>
  <div :class="mobileClass">
    <div id="chartContainer"  ref="chart" class="chart"></div>
    <img src="~/assets/image/vehicleModel/close.png" alt="" class="close_icon" @click="handleClose" />
  </div>
</template>

<script>

let option = {
  animation: true, // 是否开启动画
  animationDuration: 1000, // 动画持续时间
  animationEasing: 'cubicOut', // 动画缓动函数
  // tooltip: {
  //   trigger: 'none', // 禁用鼠标悬停效果

  // },

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
          value: 31,
          name: '#8195a6',
          itemStyle: {
            color: "#8195a6",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: '#1d344a',
          itemStyle: {
            color:  '#1d344a' ,
          },
        },

        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: '#000000',
          itemStyle: {
            color: "#000000",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: '39463b',          itemStyle: {
            color:'#39463b',
          }, },
          { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零

        { value: 5, name: '#414e5b', itemStyle: { color: '#414e5b' } },

        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'ffffff', itemStyle: {
            color: '#ffffff',
          } },

        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零



      ],
      colorList_2: [
        {
          value: 31,
          name: 'Search Engine',
          itemStyle: {
            color: "#8195a6",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: 'Search Engine',
          itemStyle: {
            color:'#1d344a'
          },
        },

        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        {
          value: 5,
          name: 'Search Engine',
          itemStyle: {
            color: "#000000",
          },
        },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine',          itemStyle: {
            color:'#39463b'
          }, },
          { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: { color: '#414e5b' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: {
            color: '#ffffff' ,
          } },



        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零



      ]
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
  beforeDestroy() {
    this.myChart.clear()
    // 移除点击事件监听
    this.myChart.off('click', this.handleChartClick)
  },
  methods: {
    initChart() {
      // 基于准备好的dom，初始化echarts实例
      const chartDom = document.getElementById('chartContainer')

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

      const a = this.myChart;
      const i = Math.ceil((a.option.series[index].data.length - 1) / 2);
      const n = option.series[index].data.length;
    //  if (e.dataIndex === a.beforeIndex || 0 !== e.seriesIndex || 1 == e.value || -1 == e.data.id) return !1;

     for (var t = [], o = [], s = 1; s < n; s++) {
        s <= i ? o.push(this.getNextIndex(a.beforeIndex, s,index)) : t.push(this.getNextIndex(a.beforeIndex, s,index));
    }

      t.reverse();
      let r = o.findIndex((t => t === e.dataIndex));
      if (r > -1) {
          const t = this.getRotationAngle(r + 1, o,index);
              const i = o.findIndex((e => e === 0));
          e.dataIndex === 0 || r > i && i > -1 ? a.option.series[index].startAngle += t + 150.96 : a.option.series[index].startAngle += t
      }
      if (r = t.findIndex((t => t === e.dataIndex)), r > -1) {
          const e = t.findIndex((e => e === 0));
          a.beforeIndex === 0 || r > e && e > -1 ? a.option.series[index].startAngle -= 180 + this.getRotationAngle(r, t,index) : a.option.series[index].startAngle -= this.getRotationAngle(r + 1, t,index)

      }

      const l = a.option.series[index].data[a.beforeIndex].value;
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
      const a = t.slice(0, e);
        let i = 0;

      a.forEach((e => {
        console.log(option.series[index].data[e].value,"option.series[index].data[e].value")
        i += option.series[index].data[e].value
      }))
      console.log(i,"iiiiiiiiiiii")
      return 5.806451612903226 * i
    },
    getNextIndex (e, t,index) {
	    return ((e + t) % option.series[index].data.length + option.series[index].data.length) % option.series[index].data.length
    },

    handleChartClick(params) {

      if (params.componentType === 'series') {
        // 获取点击的数据项名称
        // 执行您的自定义点击事件操作
        if (params.data.name == 'spacing') return
        if (params.value == 31) return

        // 获取点击的数据项的索引
        const index = params.dataIndex
        this.getRotate(params,'0')

        console.log(option)
        this.$emit('changeColor', option, index)
        this.myChart.setOption(option,true)

      }
    },
    handleClose() {
      this.$emit('closeColor')
    },

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
