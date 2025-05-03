<template>
  <client-only>
    <div class="cart-model-main" @touchstart="videoControl">
      <div v-if="carList.title" class="cart-banner max-1680" ref="cartBanner">
        <div class="position-box">
          <div>
            <div class="desc">{{ carList.subTitle }}</div>
            <h1 class="title h1">{{ carList.title }}</h1>
            <div class="subTitle" v-html="carList.desc"></div>
            <div class="color-list">
              <div class="color-item" :class="[colorIndex == index ? 'color-item-show' : '']" v-for="(color, index) in carList.colors" :key="index"  @click="changeColor(index)">
                <pieColor class="hidden-sm-and-down" radius="36"  :rgbCode="color.rgbCode && color.rgbCode.split('/') "></pieColor>
                <pieColor class="hidden-md-and-up" radius="20"  :rgbCode="color.rgbCode && color.rgbCode.split('/') "></pieColor>
              </div>
            </div>
            <nuxt-link class="btn secondary--btn-dark" :to="localePath('/testDrive', $i18n.locale)">{{ $t('btn.testDrive') }}</nuxt-link>
            <a v-if="!!carList.vrViewUrl" class="btn secondary--btn-dark" :href="carList.vrViewUrl" target="_blank">
              {{ $t('btn.viewVR') }}
            </a>
          </div>
        </div>
        <div class="cart-list">
          <div class="cart-item" :class="[colorIndex == index ? 'cart-item-show' : 'cart-item-hide']" v-for="(color, index) in carList.colors" :key="index">
            <img :src="baseUrl + color.imgUrl" />
          </div>
        </div>
      </div>
      <div class="desc-img-list">
        <div class="desc-img-item" v-for="(characteristic, index) in carList.characteristics" :key="index">
          <div class="desc-img"  v-if="characteristic.type === 'img_and_text'"  :style="{ 'z-index': index }" ref="sliderItem">
            <div class="desc-img-text-box">
              <img :src="baseUrl + characteristic.imgUrl" />
              <imgText :characteristic="characteristic"></imgText>
            </div>
          </div>
        </div>
      </div>

      <div class="desc-img-slider" v-if="sliderImgs.length > 0">
        <AboutImageBanner :imageList="sliderImgs" />
      </div>

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
              <td v-for="(item, j) in line" :key="i + '_' + j" class="table_cell" :style="{ width: cellWidth, fontWeight: i === 0 ? 'bold' : '' }" :rowspan="item.rowspan" :colspan="item.colspan">
                {{ item.label }}
              </td>
            </tr>

            <tr v-for="(block, k) in paramsData.body" :key="k" v-if="k > 0">
              <td :colspan="colNum">
                <table class="params-table__params">
                  <thead v-if="block.menu" class="table_line_menu" @click="menuClick(k)">
                    <tr>
                      <td v-for="(item, j) in block.menu" :key="'m_' + j" class="table_cell" :style="{ width: cellWidth }" :rowspan="item.rowspan" :colspan="item.colspan">
                        <div class="table_cell__menu">
                          <div class="icon-plus-menu" :class="[showBlock.includes(`${k},`) ? 'open' : 'close']"></div>
                          {{ item.label }}
                        </div>
                      </td>
                    </tr>
                  </thead>
                  <tbody :style="{ display: showBlock.includes(`${k},`) ? '' : 'none' }">
                    <tr v-for="(line, i) in block.lines" :key="i" class="table_line" :class="[`line_${i}`, line.length === 1 ? 'line_menu' : '']">
                      <td v-for="(item, j) in line" :key="i + '_' + j" class="table_cell" :style="{ width: cellWidth, fontWeight: k === 0 && i === 0 ? 'bold' : '' }" :rowspan="item.rowspan" :colspan="item.colspan">
                        {{ item.label }}
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
        <div class="h3 title params-table__title">
          {{ paramsDataMobile.title }}
        </div>

        <div class="params-div">
          <div class="params-div__select" @click="menuClickMobile(k)">
            <el-select v-model="selectModel" size="small" @change="showBlockMobile = '0,'">
              <el-option v-for="(item, i) in paramsDataMobile.header" :key="i" :label="item" :value="i"> </el-option>
            </el-select>
          </div>

          <div v-for="(block, k) in paramsDataMobile.body" :key="k" class="params-div__block">
            <div v-if="block.menu" class="params-div__menu params-div__line" @click="menuClickMobile(k)">
              <div class="params-div__name">{{ block.menu[0] }}</div>
              <div class="params-div__value" :class="[showBlockMobile.includes(`${k},`) ? 'open' : 'close']"></div>
            </div>
            <div v-if="!block.menu || (block.menu[0].toLowerCase() !== 'body color' && block.menu[0].toLowerCase() !== 'color del cuerpo' && block.menu[0].toLowerCase() !== 'لون جسم السيارة')" class="params-div__body" :style="{ height: showBlockMobile.includes(`${k},`) ? `${block.lines.length * 51}px` : '0px' }">
              <div v-for="(item, i) in block.lines" :key="i" class="params-div__line">
                <div class="params-div__name">{{ item[0] }}</div>
                <div class="params-div__value">{{ item[selectModel + 1] }}</div>
              </div>
            </div>
            <div v-else class="params-div__body" :style="{ height: showBlockMobile.includes(`${k},`) ? `${block.lines.length * 51}px` : '0px' }">
              <div v-for="(item, i) in block.lines" :key="i" class="params-div__line">
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
  </client-only>
</template>

<script>
import { read, utils } from 'xlsx'
import imgText from '@/components/imgText'
import pieColor from '@/components/pieColor';
export default {
  name: 'ModelPage',
  layout: 'main',
  components: { imgText,pieColor },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      carList: {},
      sliderImgs: [],
      colorIndex: 0,
      bottomSliderHeight: '',

      paramsData: '',
      rowNum: 0,
      colNum: 0,
      cellWidth: '0',
      showBlock: '0,',

      paramsDataMobile: '',
      rowNumMobile: 0,
      colNumMobile: 0,
      showBlockMobile: '0,',
      selectModel: 0
    }
  },
  mounted() {
    const { id } = this.$route.params
    if (!id) {
      this.$router.push('/')
    } else {
      this.$api.product
        .productInfo({ id })
        .then(async res => {
          const { characteristics } = res.data
          const imgs = characteristics.filter(item => item.type === 'img_and_text')
          if (imgs?.length > 10) {
            res.data.characteristics = imgs.slice(0, 9)
            const sliderImgs=[]
            for(let i=9;i<imgs.length;i++){
              sliderImgs.push({imgUrl:`${this.baseUrl}${imgs[i].imgUrl}`})
            }
            this.sliderImgs = sliderImgs
          }

          this.carList = Object.assign({}, this.carList, res.data)
          if (this.carList.specificationsPDFUrl && this.carList.specificationsPDFUrl.endsWith('.xlsx')) {
            try {
              const buffer = await this.$axios.get(`${this.baseUrl}${this.carList.specificationsPDFUrl}`, { responseType: 'arraybuffer' })
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
        .catch(() => {
          this.$router.push('/')
        })
    }

    this.startFixDescImgListHeight()
  },
  activated() {
    window.addEventListener('scroll', this.scrollWithWindow)
    window.addEventListener('scroll', this.fixDescImgListHeight)
    window.addEventListener('resize', this.fixDescImgListHeight)
    this.videoControl()
    this.startFixDescImgListHeight()
    this.fixDescImgListHeight()
  },
  deactivated() {
    window.removeEventListener('scroll', this.scrollWithWindow)
    window.removeEventListener('scroll', this.fixDescImgListHeight)
    window.removeEventListener('resize', this.fixDescImgListHeight)
  },
  methods: {
    videoControl() {
      this.$refs.video?.videoControl()
    },
    scrollWithWindow() {
      if (this.$refs.cartBanner) {
        const { top, bottom } = this.$refs.cartBanner.getBoundingClientRect()

        if (top < 0 && bottom >= 0) {
          this.$refs.cartBanner.style.transform = `matrix(1,0,0,1,0,${-top / 2})`
        } else {
          this.$refs.cartBanner.style.transform = `matrix(1,0,0,1,0,0)`
        }

        if (this.$refs.videoBox) {
          const { top, bottom } = this.$refs.videoBox.getBoundingClientRect()
          if ((top < 0) & (bottom >= 0)) {
            this.$refs.videoBox.style.transform = `matrix(1,0,0,1,0,${-top / 2})`
          } else {
            this.$refs.videoBox.style.transform = `matrix(1,0,0,1,0,0)`
          }
        }

        if (this.$refs.sliderItem) {
          const len = this.$refs.sliderItem.length
          for (let i = 0; i < len; i++) {
            if (i < len - 1) {
              const item = this.$refs.sliderItem[i]
              const { top, bottom } = item.getBoundingClientRect()
              if ((top < 0) & (bottom >= 0)) {
                item.style.transform = `matrix(1,0,0,1,0,${-top / 2})`
              } else {
                item.style.transform = `matrix(1,0,0,1,0,0)`
              }
            }
          }
        }
        }
    },
    changeColor(index) {
      this.colorIndex = index
    },
    menuClick(k) {
      if (this.showBlock.includes(`${k},`)) {
        this.showBlock = this.showBlock.replace(`${k},`, '')
      } else {
        this.showBlock += `${k},`
      }
    },
    menuClickMobile(k) {
      console.log(k, '点击行')

      if (this.showBlockMobile.includes(`${k},`)) {
        this.showBlockMobile = this.showBlockMobile.replace(`${k},`, '')
      } else {
        this.showBlockMobile += `${k},`
      }
      console.log(this.showBlockMobile, 'showBlockMobile')
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
    parseParams(csv) {
      const data = this.csv2Array(csv)
      const rowNum = data.length // 总行数
      const colNum = data[0].length // 列数,表格每一行列数应该相等

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
        } else if (formatLine[0].label.toLowerCase() === 'body color' || formatLine[0].label.toLowerCase() === 'color del cuerpo' || formatLine[0].label.toLowerCase() === 'لون جسم السيارة') {
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
        } else if (line[0].toLowerCase() === 'body color' || line[0].toLowerCase() === 'color del cuerpo' || line[0].toLowerCase() === 'لون جسم السيارة') {
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
          if (line[0].toLowerCase() === 'basic basic' || line[0].toLowerCase() === 'básicos') {
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
      this.rowNumMobile = rowNum
      this.colNumMobile = colNum
    },
    /**
     * 在图片加载完成后重新计算图片容器的高度，以修复特殊样式带来的bug
     */
    startFixDescImgListHeight(){
      const imgs = Array.from(document.querySelectorAll('.desc-img-text-box>img'))
      imgs.forEach(x => {
        x.addEventListener('load', this.fixDescImgListHeight, { once: true })
        x.addEventListener('error', this.fixDescImgListHeight, { once: true })
      })
    },
    /**
     * 重新计算图片容器的高度，以修复特殊样式带来的bug
     */
    fixDescImgListHeight(){
      const d = document.querySelector('.desc-img-list')
      if (d) {
        const imgs = Array.from(d.querySelectorAll('.desc-img-text-box>img'))
        if(imgs.length > 1) {
          const img0 = imgs.shift();
          const imgX = imgs.pop();
          d.style.height = imgX.getBoundingClientRect().y - img0.getBoundingClientRect().y + imgX.height + 'px'
        }
      }
    }
  },
}
</script>



<style scoped lang="scss">
// $product-themen-color: #f8f8f8;
.cart-model-main {
  min-height: 100vh;
  .h3 {
    font-size: calc($body-text-size * 2);
    position: relative;
    font-weight: 700;
    margin-bottom: 30px;
    margin-top: 50px;
  }
  .cart-banner {
    // background-color: $product-themen-color;
    position: relative;
    width: 100%;
    height: 100vh;
    .position-box {
      position: absolute;
      /*top: 20%;*/
      top: 0;
      left: 0;
      padding-left: 50px;

      z-index: 1;

      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      flex-wrap: nowrap;
      .title {
        font-size: calc($body-text-size * 6);
        margin-bottom: 0;
        margin-left: -7px;
      }
      .desc {
        font-size: calc($body-text-size * 2);
      }
      .subTitle {
        font-size: calc($body-text-size * 1.5);
        color: #6f6f6f;
        overflow: hidden;
        p,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
          margin: 0;
          padding: 0;
        }
      }
      .btn {
        margin-right: 15px;
      }
      .color-list {
        /*margin-top: 120px;
        margin-bottom: 50px;*/
        margin-top: 12vh;
        margin-bottom: 6vh;
        .color-item {
          display: inline-flex;
          width: 35px;
          height: 35px;
          margin-right: 20px;
          border-radius: 50%;
          cursor: pointer;
          &.color-item-show {
            box-shadow: 0 0 10px rgb(0 0 0 / 56%);
          }
        }
      }
    }
    .cart-list {
      position: absolute;
      right: -20%;
      top: 0;
      bottom: 0;
      width: 100%;
      .cart-item {
        position: absolute;
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;

        &.cart-item-show {
          transform: translateX(0);
          opacity: 1;
          transition: all 0.5s ease;
        }
        &.cart-item-hide {
          transform: translateX(100%);
          opacity: 0;
        }
        img {
          max-width: 100%;
          max-height: 100%;
        }
      }
    }
  }
  .desc-img-list {
    position: relative;
    z-index: 5;
    .desc-img-item {
      .desc-img {
        width: 100vw;
        height: 105vh;
        & > img {
          width: 100%;
          height: 100%;
          object-fit: cover;
          position: relative;
        }
        .desc-img-text-box{
          position: relative;
        }
      }
    }
  }
  .specificationsPDF {
    margin: 70px 0;
    text-align: center;
    .subtitle {
      font-size: 18px;
      margin-bottom: calc($body-text-size * 2);
    }
    .specificationsPDF-btn {
    }
  }
}

.md {
  .cart-banner {
    height: 95vh;
  }
  .desc-img-list {
    .desc-img-item {
      .desc-img {
        height: auto;
      }
    }
  }
}

.xs,
.sm {
  .h3 {
    font-size: 18px;
  }

  .cart-banner {
    // background-color: $product-themen-color;
    position: relative;
    width: 100%;
    height: auto;
    .position-box {
      position: relative;
      top: 20%;
      left: 0;
      padding: 30px 0px 0;
      z-index: 1;
      .title {
        font-size: 34px;
        margin-bottom: 0;
        margin-left: 0;
      }
      .desc {
        font-size: 14px;
      }
      .subTitle {
        font-size: 16px;
        color: #000;
        overflow: hidden;
      }
      .color-list {
        margin-top: 0px;
        margin-bottom: 20px;
        .color-item {
          display: inline-flex;
          width: 20px;
          height: 20px;
          margin-right: 10px;
          border-radius: 50%;
          cursor: pointer;
          &.color-item-show {
            box-shadow: 0 0 10px rgb(0 0 0 / 56%);
          }
        }
      }
      .btn {
        margin-right: 15px;
      }
    }
    .cart-list {
      position: relative;
      width: 100vw;
      margin: 0 -15px;
      left: 0;
      right: 0;
      padding-bottom: 55%;
      .cart-item {
        position: absolute;
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;

        &.cart-item-show {
          transform: translateX(0);
          opacity: 1;
          transition: all 0.5s ease;
        }
        &.cart-item-hide {
          transform: translateX(100%);
          opacity: 0;
        }
        img {
          max-width: 100%;
          max-height: 100%;
        }
      }
    }
  }
  .desc-img-list {
    position: relative;
    z-index: 5;
    .desc-img-item {
      .desc-img {
        width: 100vw;
        height: auto;
        & > img {
          width: 100%;
          height: 100%;
          object-fit: cover;
          position: relative;
        }
      }
    }
  }

  .specificationsPDF {
    margin: 20px 0;
    text-align: center;
    .subtitle {
      font-size: 14px;
      margin-bottom: 20px;
    }
    .specificationsPDF-btn {
    }
  }
}

.video-box {
  position: relative;
  // margin-top: 8px;
  line-height: 0; //视频下面多了一条白色，加这个就没有了
  z-index: 4;
}
.desc-img-box {
  position: relative;
  z-index: 5;
}
</style>

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
                z-index: 10;
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
                z-index: 10;
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
    }

    .params-div__line {
      height: 51px;
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

<style>
 @media only screen and (max-width:991px){
    .hidden-sm-and-down{
        display:none!important
    }
  }
  @media only screen and (min-width:992px){
      .hidden-md-and-up{
          display:none!important
      }
  }
</style>
