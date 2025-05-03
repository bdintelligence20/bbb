<template>
  <div :class="mobileClass">
    <div id="chartContainer"  ref="chart" class="chart"></div>
    <img src="~/assets/image/vehicleModel/close.png" alt="" class="close_icon" @click="handleClose" />
  </div>
</template>

<script>

let option = {
  // tooltip: {
  //   trigger: 'none', // 禁用鼠标悬停效果
  // },
  pixelRatio: 2, // 尝试调整这个值，以改善抗锯齿效果
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: ['60%', '100%'],
      hoverAnimation: false,
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 2,
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
    }
  },
  data() {
    return {
      myChart: null,
      colorList: [
        {
          value: 37.5,
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
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 1,
              y2: 1,
              colorStops: [
                { offset: 0, color: '#000000' },
                { offset: 0.5, color: '#000000' },
                { offset: 0.5, color: '#d4d4d4' },
                { offset: 1, color: '#d4d4d4' },
              ],
            },
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
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 1,
              y2: 1,
              colorStops: [
                { offset: 0, color: '#000000' },
                { offset: 0.5, color: '#000000' },
                { offset: 0.5, color: '#757575' },
                { offset: 1, color: '#757575' },
              ],
            },
          }, },
          { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: { color: '#000000' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 1,
              y2: 1,
              colorStops: [
                { offset: 0, color: '#000000' },
                { offset: 0.5, color: '#000000' },
                { offset: 0.5, color: '#ffffff' },
                { offset: 1, color: '#ffffff' },
              ],
            },
          } },



        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零
        { value: 5, name: 'Search Engine', itemStyle: { color: '#ffffff' } },
        { value: 1, name: 'spacing', itemStyle: { color: 'rgba(0,0,0,0)' } }, // 虚拟数据项，值为零


      ],
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {

        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
        } else {
          this.mobileClass = ''
        }
      },
    },
  },
  mounted() {

    if(this.optionColor){
      option = this.optionColor
    }else{
      option.series[0].data = this.colorList
    }
    // 绘制图表
    this.initChart()
  },
  beforeDestroy() {
    // 移除点击事件监听
    this.myChart.off('click', this.handleChartClick)
  },
  methods: {
    initChart() {
      // 基于准备好的dom，初始化echarts实例
      const chartDom = document.getElementById('chartContainer')
      this.myChart = this.$echarts.init(chartDom)
       this.myChart.setOption(option)
      this.myChart.on('click', this.handleChartClick)

      window.addEventListener('resize', this.handleResize);
    },
    handleResize() {

      // 调用 ECharts 的 resize 方法，使图表重新适应容器大小
      this.myChart.resize();
    },
    createLinearGradient(colors) {
      const colorStops = []
      const step = 1 / (colors.length - 1)

      for (let i = 0; i < colors.length; i++) {
        colorStops.push({
          offset: i * step,
          color: colors[i],
        })
      }
      const echarts = this.$echarts
      return new echarts.graphic.LinearGradient(0, 0, 1, 0, colorStops)
    },

    handleChartClick(params) {

      if (params.componentType === 'series') {
        // 获取点击的数据项名称
        // 执行您的自定义点击事件操作
        if (params.data.name == 'spacing') return
        if (params.value == 37.5) return
        // 获取点击的数据项的索引
        const index = params.dataIndex
        // 遍历数组将值为37.5的修改为5
        for (const item of this.colorList) {
          if (item.value === 37.5) {
            // 找到目标项，修改值
            item.value = 5
            break // 跳出循环
          }
        }
          this.colorList[index].value = 37.5
          option.series[0].data = this.colorList
          option.series[0].startAngle =0
          option.series[1].startAngle =0

          option.series[0].startAngle = this.returnAngle(index)
          option.series[1].startAngle = this.returnAngle(index)
          this.$emit('changeColor', option, index)
          window.option = option
          window.myChart = this.myChart
          this.myChart.setOption(option)
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
