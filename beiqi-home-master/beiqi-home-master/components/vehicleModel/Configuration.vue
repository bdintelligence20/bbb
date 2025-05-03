<!--
 * @Author: 不二
 * @Date: 2023-11-06 10:30:18
 * @LastEditTime: 2023-12-13 15:26:42
-->
<template>
  <section :class="mobileClass">
    <div class="configuration_title">{{ locale_data.configuration[locale] }}</div>
    <div class="configuration_box">
      <div class="configuration_l">
        <img :src="config.imgUrl" alt="" />
      </div>
      <div class="configuration_r">
        <div class="title_box">
          <div class="min_title">{{ locale_data.configuration[locale] }}</div>
          <div class="title">
            <strong>{{ config.model }}</strong> PARAMETER
          </div>
        </div>

        <div class="item_box">
          <div class="item" v-for="item in config.params" :key="item.name">
            <div class="item_t">{{ item.name }}</div>
            <div class="item_b">{{ item.value }}</div>
          </div>
        </div>
        <div class="config_btn" @click="handleShow">{{ locale_data.detailed[locale] }}</div>
      </div>
    </div>

    <el-dialog :visible.sync="dialogVisible" width="90%" append-to-body custom-class="my_dialog" :class="{ 'dialog-ar': $i18n.locale == 'ar' }">
      <ConfigTable :id="config.id" class="config-table"></ConfigTable>
    </el-dialog>
  </section>
</template>

<script>
import ConfigTable from './common/CommonConfigTable.vue'

export default {
  props: {
    config: Object,
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      existList: ['interior colors', 'color interior', 'اللون الداخلي'],
      dialogVisible: false,
      locale: this.$i18n.locale,
      mobileClass: '',
      locale_data: {
        configuration: {
          en: 'configuration',
          ar: 'تكوين',
          es: 'configuración',
        },
        l_w_x: {
          en: 'Length x Width x Height (mm)',
          es: 'Largo x ancho x alto (mm)',
          ar: 'الطول × العرض × الارتفاع ( مم )',
        },
        base: {
          en: 'Wheelbase (mm)',
          es: ' Distancia entre ejes (mm)',
          ar: 'قاعدة العجلات (مم)',
        },
        gradeability: {
          en: 'Engine',
          es: 'Motor ',
          ar: 'المحرك',
        },
        power: {
          en: 'Rated power (kw) ',
          es: 'Potencia nominal (kw)',
          ar: 'القدرة المقدرة (كيلو وات)',
        },
        torque: {
          en: 'Max. torque (N·m)',
          es: 'Max. Par (Nm)',
          ar: 'ماكس. العزم (نيوتن/متر)',
        },
        detailed: {
          en: 'DETAILED PARAMETERS',
          es: 'PARÁMETROS DETALLADOS',
          ar: 'تفاصيل المعلمة',
        },
      },
    }
  },
  components: { ConfigTable },
  mounted() {
    if (['xs', 'md', 'sm'].includes(this.$mq)) {
      console.log('config')
      this.mobileClass = 'mobile'
    } else {
      this.mobileClass = ''
    }
  },
  methods: {
    handleClose() {},
    handleShow() {
      this.dialogVisible = true
    },
  },

  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted< config', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'
        } else {
          this.mobileClass = ''
        }
      },
    },
  },
}
</script>
<style lang="scss">
.my_dialog .el-dialog__body {
  height: 80vh;
  overflow-y: scroll;
  box-sizing: border-box;
  padding-bottom: 2vw;
  padding: 0;
  margin-top: 2vw !important;
}
.my_dialog .params-table {
  width: 100% !important;
}
.dialog-ar {
  direction: rtl;
  text-align: right;
  .config-table {
    .params-table {
      .table_cell {
        border-right: 1px solid #c7c7c7;
        border-left: 0;
      }
    }
  }
}
</style>
<style scoped lang="scss">
.lang___ar {
  .configuration_r {
    padding-right: 5%;
    padding-left: 0;
  }
}
.mobile {
  .configuration_title {
    font-size: 5vw;
    font-weight: bolder;
  }
  .configuration_box {
    flex-direction: column;
    font-size: 3vw;
    .configuration_r {
      margin-top: 2vw;
      width: 100%;
      padding-left: 0;

      .min_title {
        font-size: 3vw;
      }
      .title {
        font-size: 5vw;
        display: inline-block;
      }
      .item_box {
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: flex-start;
        .item {
          width: calc(100% / 3);
          margin-bottom: 5vw;
        }
        // .item:nth-child(1) {
        //   width: 100%;
        // }
        // .item:nth-child(n + 2) {
        //   width: 50%;
        // }
        .item_t {
          font-size: 3vw;
        }
        .item_b {
          font-size: 4vw;
        }
      }

      .config_btn {
        font-size: 4vw;
        height: auto;
        padding: 2vw 0;
      }
    }
  }
}

.configuration_title {
  font-size: 1.5vw;
  font-weight: 900;
  text-transform: uppercase;
  text-align: center;
  margin: 3vw 0;
}
.configuration_box {
  width: 90%;
  margin: 0 auto;
  display: flex;
}

.configuration_r {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 30%;
  box-sizing: border-box;
  padding-left: 5%;
}
.min_title {
  color: #05aad2;
  font-size: 0.9vw;
}
.config_btn {
  width: 100%;
  height: 3.54vw;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #3d3d3d;
  color: #fff;
  font-size: 1vw;
  text-transform: uppercase;
}
.item_box {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 3vw 0;
}
.title {
  color: #626262;
  font-size: 1.5vw;
  position: relative;
  margin-top: 1vw;
}
.title::after {
  position: absolute;
  bottom: -1vw;
  left: 0;
  width: 100%;
  height: 1px;
  background-color: #d5d6d6;
  content: '';
}
.item_t {
  font-size: 1vw;
  color: #b0b0b0;
  margin-bottom: 1vw;
}
.item_b {
  color: #626262;
  font-size: 1.2vw;
}
.config_btn {
  cursor: pointer;
}
</style>
