<template>
  <div>


    <mq-layout v-if="paramsData && paramsData.title" :mq="['lg', 'xl', 'md']">
      <div class="h3 title params-table__title">
        {{ paramsData.title }}
      </div>
      <table class="params-table">
        <thead class="params-table__header">
          <tr v-for="(line, i) in paramsData.header" :key="i" class="table_line" :class="[`line_${i}`, line.length === 1 ? 'line_menu' : '']">
            <td v-for="(item, j) in line" :key="i + '_' + j" class="table_cell" :style="{ width: cellWidth }" :rowspan="item.rowspan" :colspan="item.colspan">
              {{ item.label }}
            </td>
          </tr>
        </thead>
        <tbody class="params-table__body">
          <tr v-for="(line, i) in paramsData.body[0].lines" :key="'0_' + i" class="table_line" :class="[`line_${i}`, line.length === 1 ? 'line_menu' : '']">
            <td v-for="(item, j) in line" :key="i + '_' + j" class="table_cell" :style="{ width: cellWidth, fontWeight: i === 0 ? 'bold' : '' }" :rowspan="item.rowspan" :colspan="item.colspan" v-html="item.label">

            </td>
          </tr>

          <tr v-for="(block, k) in paramsData.body" :key="k" v-if="k > 0">
            <td :colspan="colNum">
              <table class="params-table__params">
                <thead v-if="block.menu" class="table_line_menu" @click="menuClick(k)">
                  <tr>
                    <td v-for="(item, j) in block.menu" :key="'m_' + j" class="table_cell" :style="{ width: cellWidth }" :rowspan="item.rowspan" :colspan="item.colspan">
                      <div class="table_cell__menu">
                        <div class="icon-plus-menu" :class="[showBlock.includes(`${k}`) ? 'open' : 'close']"></div>
                        {{ item.label }}
                      </div>
                    </td>
                  </tr>

                </thead>
                <tbody :style="{ display: showBlock.includes(`${k}`) ? '' : 'none' }">
                  <!-- 默认列数处理单独table合并布局出错的情况 -->
                  <tr class="table_line" style="visibility: collapse;">
                    <td v-for="(td_item,td_index) in colNum" :key="td_index" rowspan="1" colspan="1" class="table_cell" :style="{ width: cellWidth }"> 11111</td>
                  </tr>


                  <tr v-for="(line, i) in block.lines" :key="i" class="table_line" :class="[`line_${i}`, line.length === 1 ? 'line_menu' : '']">
                    <td v-for="(item, j) in line" :key="i + '_' + j" class="table_cell" :style="{ width: cellWidth, fontWeight: k === 0 && i === 0 ? 'bold' : '' }" :rowspan="item.rowspan" :colspan="item.colspan" v-html="item.label">

                    </td>
                  </tr>

                </tbody>
              </table>
            </td>
          </tr>
        </tbody>
        <tfoot class="params-table__footer">
          <tr v-for="(line, i) in paramsData.footer" :key="i" class="table_line" :class="[`line_${i}`, line.length === 1 ? 'line_menu' : '']">
            <td v-for="(item, j) in line" :key="i + '_' + j" class="table_cell" :style="{ width: cellWidth }" :rowspan="item.rowspan" :colspan="item.colspan">
              {{ item.label }}
            </td>
          </tr>
        </tfoot>
      </table>
      <div class="params-table__remark">{{ paramsData.remarks[0] }}</div>
      <div class="params-table__remark">{{ paramsData.remarks[1] }}</div>
    </mq-layout>
    <mq-layout v-if="paramsDataMobile && paramsDataMobile.title" :mq="['xs', 'sm']">
      <div class="h3 title params-table__title  params-table__title_mobile">
        {{ paramsDataMobile.title }}
      </div>

      <div class="params-div">
        <div class="params-div__select" @click="menuClickMobile(k)">
          <el-select v-model="selectModel" size="small" @change="ChangeShowBlockMobile">
            <el-option v-for="(item, i) in paramsDataMobile.header" :key="i" :label="item" :value="i"> </el-option>
          </el-select>
        </div>

        <div v-for="(block, k) in paramsDataMobile.body" :key="k" class="params-div__block">
          <div v-if="block.menu" class="params-div__menu params-div__line" @click="menuClickMobile(k)" :ref="'params-div__menu_'+k">
            <div class="params-div__name">{{ block.menu[0] }}</div>
            <div class="params-div__value" :class="[showBlockMobile.includes(`${k}`) ? 'open' : 'close']"></div>
          </div>
          <!-- 第一行默认展开，其他行收缩 -->
          <div v-if="!block.menu || (block.menu[0].toLowerCase() !== 'body color' && block.menu[0].toLowerCase() !== 'color del cuerpo' && block.menu[0].toLowerCase() !== 'لون جسم السيارة')" class="params-div__body" :style="{ height: k==0 ? 'auto' :'0px'}" :ref="'params-div__body_'+k"  :a="k">
            <div v-for="(item, i) in block.lines" :key="i" class="params-div__line">
              <div class="params-div__name">{{ item[0] }}</div>
              <div class="params-div__value">{{ item[selectModel + 1] }}</div>
            </div>
          </div>
          <!-- body color所在行，默认收缩 -->
          <div v-else class="params-div__body" :ref="'params-div__body_'+k" :style="{ height:  showBlockMobile.includes(`${k},`) ? `${block.lines.length * 51}px` : '0px'}" a="2223 ">
            <div v-for="(item, i) in block.lines" :key="i" class="params-div__line" >
              <div class="params-div__name">{{ item[selectModel + 1] }}</div>
              <div class="params-div__value"></div>
            </div>
          </div>
        </div>
      </div>

      <div class="params-div__remark">{{ paramsDataMobile.remarks[0] }}</div>
      <div class="params-div__remark">{{ paramsDataMobile.remarks[1] }}</div>
    </mq-layout>
  </div>
</template>

<script>
import { read, utils } from 'xlsx'
export default {
    props:{
        id:String,
        // 根据BJ60的配置表参数来定义的
        // 区分默认的底部分组
        existList:{
          typeof:Array,
          default:function(){
            return [
            'body color',
            'color del cuerpo',
            'لون جسم السيارة'
          ]
          }
        },
        // 区分默认的头部分组
        topExistList:{
          typeof:Array,
          default:function(){
            return [
            'basic basic',
            'básicamente básico',
            "الأساسية الأساسية"
          ]
          }
        }
    },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      carList: {},
      paramsData: '',
      rowNum: 0,
      colNum: 0,
      cellWidth: '0',
      showBlock: [0],
      paramsDataMobile: '',
      rowNumMobile: 0,
      colNumMobile: 0,
      showBlockMobile: [0],
      selectModel: 0
    }
  },
  async mounted(){
    console.log(this.existList)
    this.$api.product
        .productInfo({ id:this.id })
        .then(async res => {
            console.log(res)

          this.carList = Object.assign({}, this.carList, res.data)
          if (this.carList.specificationsPDFUrl && this.carList.specificationsPDFUrl.endsWith('.xlsx')) {
            try {
              const buffer = await this.$axios.get(`${this.baseUrl}${this.carList.specificationsPDFUrl}`, { responseType: 'arraybuffer' })
              // console.log(buffer)
              const ex = read(buffer)
              const csv1 = utils.sheet_to_csv(ex.Sheets[ex.SheetNames[0]], { FS: '[]', RS: '{}' }) // 用一对空括号区分 行 和 列
              const csv2 = utils.sheet_to_csv(ex.Sheets[ex.SheetNames[1]], { FS: '[]', RS: '{}' }) // 用一对空括号区分 行 和 列
              this.parseParams(csv1)
              this.parseParamsMobile(csv2)
            } catch (error) {
              console.log(error)
            }
          }
        })

  },
  activated() {


  },
  methods:{
    parseParams(csv) {

      const data = this.csv2Array(csv)
      const rowNum = data.length // 总行数
      const colNum = data[0].length // 列数,表格每一行列数应该相等
      console.log(data,"this.data")
      let colorLineNum = -1 // 车身颜色是表尾, 之后的应改为备注
      const eData = { title: '', header: [], body: [], footer: [], remarks: [] }
      for (let i = 0; i < rowNum; i++) {
        const line = data[i]
        if (line.length !== colNum) throw new Error(`excel结构不对, Sheet1第${i + 1}行列数为${line.length}`)
        const formatLine = []
        for (let j = 0; j < colNum; j++) {
          if (line[j]) {
            let down = 0
            let right = 0
            for (; right < colNum - j - 1; right++) {
              // 向右探索
              if (line[j + right + 1]) {
                break
              }
            }
            if (j === 0) {
              // 只有第一列需要向下探索
              for (; down < rowNum - i - 1; down++) {
                // 向下探索
                if (data[i + down + 1][j]) {
                  break
                }
              }
            }
            let label = line[j]

            // 需要根据表格换行的话替换下字符并用v-html来渲染
            label = label.replace('\n', '<br>')
            // if(label.includes('In-car air intelligence system')){
            //   label = label.replace('\n', '<br>')
            //   console.log(label)
            // }
            label = label.replaceAll('""', '$')
            label = label.replaceAll('"', '')
            label = label.replaceAll('$', '"')

            formatLine.push({ label, rowspan: down + 1, colspan: right + 1 })
          }
        }
        if (formatLine.length === 0) throw new Error(`excel结构不对, 第${i + 1}行为空行`)
        if (i === 0) {
          // 第0行是车型标题
          eData.title = formatLine[0].label
        } else if (i === 1 || (eData.header[0] && eData.header[0][0] && i < eData.header[0][0].rowspan + 1)) {
          // 第1行第1列决定标题是几行
          eData.header.push(formatLine)
        } else if ( this.existList.includes( formatLine[0].label.toLowerCase()) ) {
          // Body color是表尾
          eData.footer.push(formatLine)
          colorLineNum = i // 标记Body color所在行
        } else if (colorLineNum > 0 && i > colorLineNum) {
          // Body Color后面是注释
          eData.remarks.push(formatLine[0].label)
        } else if (formatLine.length === 1) {
          // 只有一个参数,是参数组标题, 第一组参数特殊处理
          if (eData.body.length === 0) {
            formatLine.push({ label: '', rowspan: 1, colspan: formatLine[0].colspan - 1 })
            formatLine[0].colspan = 1
            eData.body.push({ lines: [formatLine] })
          } else {
            eData.body.push({ menu: formatLine, lines: [] })
          }
        } else {
          eData.body[eData.body.length - 1].lines.push(formatLine)
        }
      }

      console.log(eData)

      this.paramsData = eData
      this.rowNum = rowNum
      this.colNum = colNum
      this.cellWidth = 100 / colNum + '%'
    },
    parseParamsMobile(csv) {
      const data = this.csv2Array(csv)
      const rowNum = data.length // 总行数
      const colNum = data[0].length // 列数,表格每一行列数应该相等

      let colorLineNum = -1 // 车身颜色是表尾, 之后的应改为备注
      const eData = { title: '', header: [], body: [], footer: [], remarks: [] }
      eData.title = data[0][0] // 第一行为车型标题
      for (let i = 1; i < rowNum; i++) {
        const line = data[i]
        if (line.length !== colNum) throw new Error(`excel结构不对, Sheet2第${i + 1}行列数为${line.length}`)
        // 第0列为参数名,不处理
        for (let j = 1; j < colNum; j++) {
          if (!line[j] && j > 1) line[j] = line[j - 1] // 用左边的值进行填充, 从第二列开始

          if (!line[0]) {
            // 参数名为空,则向上找到不为空的那一行进行合并
            for (let k = i - 1; k > 0; k--) {
              if (data[k][0]) {
                data[k][j] = data[k][j] + ' ' + line[j]
              }
            }
          }

          let label = line[j]
          label = label.replaceAll('""', '$')
          label = label.replaceAll('"', '')
          label = label.replaceAll('$', '"')
          line[j] = label
        }
        if (line[0]) {
          let label = line[0]
          label = label.replaceAll('""', '$')
          label = label.replaceAll('"', '')
          label = label.replaceAll('$', '"')
          line[0] = label
        }

        // 参数名为空的是标题,会合并给第一行,所以要多次加第一行
        if (i === 1 || !line[0]) {
          // 第1行为车型选择
          eData.header = data[1].slice(1)
        } else if (this.existList.includes( line[0].toLowerCase())) {
          console.log(line, 'color行');

          // Body Color是表尾, 移动端添加到参数后面
          if (line[1]) {
            eData.body.push({ menu: line, lines: [line] })
          } else {
            // BJ80特殊,颜色是空的,但是还要保留,为了区分表尾
          }

          colorLineNum = i // 标记Body color所在行
        } else if (colorLineNum > 0 && i > colorLineNum) {
          // Body Color后面是注释
          eData.remarks.push(line[0])
        } else if (line[0] && !line[1]) {
          // 参数组标题
          if (this.topExistList.includes(line[0].toLowerCase()) ) {
            eData.body.push({ menu: null, lines: [] })
          } else {
            eData.body.push({ menu: line, lines: [] })
          }
        } else {
          // 剩下的都是普通参数
          eData.body[eData.body.length - 1].lines.push(line)
        }
      }

      this.paramsDataMobile = eData
      console.log(this.paramsDataMobile, '移动端数据');

      this.rowNumMobile = rowNum
      this.colNumMobile = colNum
    },
    csv2Array(csv) {
      return csv
        .split('{}')
        .map(line => line.split('[]'))
        .filter(line => {
          // 剔除空行
          for (const item of line) if (item) return true
          return false
        })
    },
    menuClick(k) {


      if (this.showBlock.includes(`${k}`)) {
        this.showBlock.splice(this.showBlock.indexOf(`${k}`),1)
        this.showBlock = this.showBlock
      } else {
        // this.showBlock += `${k},`
        this.showBlock.push(`${k}`)
      }

    },
    menuClickMobile(k) {
      console.log(k)
      console.dir( this.$refs[`params-div__body_${k}`])
      let el = this.$refs[`params-div__body_${k}`][0]
      if(el.style.height=="0px"){
        el.style.height=el.scrollHeight + 'px'
      }else{
        el.style.height = "0px"
      }

      // if (this.showBlockMobile.includes(`${k},`)) {
      //   this.showBlockMobile = this.showBlockMobile.replace(`${k},`, '')
      // } else {
      //   this.showBlockMobile += `${k},`
      // }


      if (this.showBlockMobile.includes(`${k}`)) {
        this.showBlockMobile.splice(this.showBlockMobile.indexOf(`${k}`),1)
        this.showBlockMobile = this.showBlockMobile
      } else {
        // this.showBlockMobile += `${k},`
        this.showBlockMobile.push(`${k}`)
      }

    },
    ChangeShowBlockMobile(k){

      // 循环对象
      this.showBlockMobile = ['0']
        Object.values(this.$refs).forEach((item,index) => {
          if(index>0){
            item[0].style.height = "0px"
          }
        })

    }
  }

}
</script>


<style scoped lang="scss">
.params-table {
  width: 80%;
  margin: 0 auto 20px;
  border-collapse: collapse;
  border-spacing: 0;

  td {
    padding: 0;
  }

  .table_cell {
    padding: 6px;
    text-align: center;
    border: 1px solid #c7c7c7;
    &:first-child {
      border-left: none;
      border-right: 1px solid #c7c7c7;
    }
    &:last-child {
      border-left: 1px solid #c7c7c7;
      border-right: none;
    }
  }

  .params-table__body {
    .table_line {
      &:nth-child(even) {
        background-color: #e8e8ea;
      }
      word-break: auto-phrase;
    }
    .params-table__params {
      width: 100%;
      border-collapse: collapse;

      .table_line_menu {
        color: #fff;
        border: none;
        background-color: #8894a3;
        .table_cell {
          text-align: left;
          font-weight: bold;
          padding: 5px 0;

          .table_cell__menu {
            display: flex;
            flex-direction: row;
            align-items: center;
            min-height: 24px;

            .icon-plus-menu {
              position: relative;
              margin: 0 34px 0 20px;
              &.close::after {
                transform: rotate3d(0, 0, 1, 90deg);
              }
              &::after {
                position: absolute;
                top: 0;
                left: 0;
                //z-index: 10;
                width: 14px;
                height: 1px;
                background: #fff;
                content: '';
                transition: transform 0.3s;
              }

              &::before {
                position: absolute;
                top: 0;
                left: 0;
                //z-index: 10;
                width: 14px;
                height: 1px;
                background: #fff;
                content: '';
              }
            }
          }
        }
      }
    }
  }

  .params-table__header,
  .params-table__footer {
    background-color: #53586d;
    color: #fff;
    .table_cell {
      font-weight: bold;
    }
  }
}
.params-div {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  .params-div__select {
    display: flex;
    align-items: center;
    height: 51px;
    padding: 0 20% 0 15px;
    background-color: #8894a3;
  }

  // &:nth-child(2) {
  //   background-color: green;
  // }

  .params-div__block {
    display: flex;
    flex-direction: column;

    &:nth-child(2) {
      .params-div__line {
        &:nth-child(even) {
          background-color: transparent;
          border-top: 1px solid #e8e8ea;
          border-bottom: 1px solid #e8e8ea;
        }
      }
    }
    .params-div__menu {
      background-color: #8894a3;
      color: #fff;
      border-bottom: 1px solid #fff;

      .params-div__value {
        position: relative;
        text-align: right;
        &.close::after {
          transform: rotate3d(0, 0, 1, 90deg);
        }
        &::after {
          position: absolute;
          top: 0;
          right: 0;
          left: -14px;
          z-index: 10;
          width: 14px;
          height: 1px;
          background: #fff;
          content: '';
          transition: transform 0.5s;
        }

        &::before {
          position: absolute;
          top: 0;
          right: 0;
          left: -14px;
          z-index: 10;
          width: 14px;
          height: 1px;
          background: #fff;
          content: '';
        }
      }
    }

    .params-div__body {
      transition: all 0.4s ease-in-out;
      overflow: hidden;
      .params-div__line{
        word-break: break-word;
        .params-div__name{
          min-width: 36%;
        }

      }
    }

    .params-div__line {
      min-height: 51px;
      display: flex;
      flex-direction: row;
      align-items: center;
      padding: 0 15px;
      &:nth-child(even) {
        background-color: #e8e8ea;
      }
      .params-div__name {
        flex: 1;
      }
      .params-div__value {
        text-align: right;
      }
    }
  }
}

.params-table__title {
  text-align: center;
  line-height: 3.5;
}
.params-table__title_mobile{
  line-height: 2;
}

.params-table__remark {
  width: 80%;
  margin: auto;
  margin-bottom: 10px;
}

.params-div__remark {
  padding: 0 15px;
  margin-top: 10px;
}

.lang___ar {
  .params-table {
    .table_cell {
      &:first-child {
        border-right: none;
        border-left: 1px solid #c7c7c7;
      }
      &:last-child {
        border-right: 1px solid #c7c7c7;
        border-left: none;
      }
    }
  }
  .params-div {
    .params-div__select {
      padding: 0 15px 0 20%;
    }

    .params-div__block {
      .params-div__menu {
        .params-div__value {
          margin-left: 15px;
        }
      }
    }
  }
}
</style>
.params-table__title {
  text-align: center;
}

.params-table__remark {
  width: 80%;
  margin: auto;
  margin-bottom: 10px;
}

.params-div__remark {
  padding: 0 15px;
  margin-top: 10px;
}
</style>
