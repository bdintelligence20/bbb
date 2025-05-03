<!--
 * @Author: 不二
 * @Date: 2023-08-25 13:54:50
 * @LastEditTime: 2023-12-18 10:43:21
-->

<template>
  <div>
    <!-- 参数 -->
      <div :class="['car_parameter',mobileClass,'wow animate__slideInUp']">
        <div class="parameter_data">
          <div class="parameter_l" ref="car_parameter">
            <div class="parameter_list">
              <div class="list_t">
                <template v-if="locale=='ar'">
                  KW
                </template>
                <animate-number from="0" to="196" duration="1500" mode="manual"
                  ref="power"></animate-number>
                  <template v-if="locale!='ar'">
                  KW
                </template>
                </div>
              <div class="list_b">{{ language[locale].power }}</div>
            </div>
            <div class="parameter_list">
              <div class="list_t">
                <template v-if="locale=='ar'">
                  N·M
                </template>
                <animate-number from="0" to="406" duration="1500" mode="manual"
                  ref="torque"></animate-number>
                  <template v-if="locale!='ar'">
                  N·M
                </template>
                 </div>
              <div class="list_b">{{ language[locale].torque }}</div>
            </div>
            <div class="parameter_list">
              <div class="list_t">
                <template v-if="locale=='ar'">
                  T
                </template>
                <animate-number from="0" to="2.0" duration="1500" :formatter="formatter_1" mode="manual"
                  ref="acceleration">
                </animate-number>

                <template v-if="locale!='ar'">
                  T
                </template>
              </div>
              <div class="list_b">{{ language[locale].speed }}</div>
            </div>
            <div class="parameter_list">
              <div class="list_t">
                <template v-if="locale=='ar'">
                  L
                </template>
                <animate-number from="0" to="7.39" duration="1500" :formatter="formatter" mode="manual"
                  ref="nedc">
                </animate-number>
                  <template v-if="locale!='ar'">
                    L
                </template>
              </div>
              <div class="list_b">{{ language[locale].nedc }}</div>
            </div>
            <div class="parameter_list">
              <div class="list_t">
                <template v-if="locale=='ar'">
                  MM
                </template>
                <animate-number from="0" to="2820" duration="1500" mode="manual"
                  ref="wheelbase">
                </animate-number>
                <template v-if="locale!='ar'">
                  MM
                </template>
                </div>
              <div class="list_b">{{ language[locale].wheelbase }}</div>
            </div>
          </div>
        </div>
        <mq-layout mq="lg+">
          <img src="~/assets/image/vehicleModel/bj60/parameter.jpg" alt="" />
        </mq-layout>
        <mq-layout :mq="['xs', 'sm', 'md']">
          <img src="~/assets/image/vehicleModel/bj60/parameter_m.jpg" alt="" />
        </mq-layout>
      </div>
  </div>
</template>

<script>
export default {
  name: 'parameter',
  data() {
    return {
      mobileClass:"",
      locale:this.$i18n.locale,
      language:{
        en:{
          power:"POWER",
          torque:"torque",
          speed:"ENGINE",
          nedc:"NEDC",
          wheelbase:"wheelbase",
        },
        es:{
          power:"PROPULSIÓN",
          torque:"TORQUE",
          speed:"ENGINEENGINE",
          nedc:"NEDC",
          wheelbase:"BATALLA",
        },
        ar:{
          power:"القوة",
          torque:"عزم الدوران",
          speed:"المحرك",
          nedc:"NEDC",
          wheelbase:"قاعدة العجلات",
        }
      }
    }
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll)
  },
  methods: {
    formatter(num) {
      return num.toFixed(2)
    },
    formatter_1(num) {
      return num.toFixed(1)
    },
    handleScroll() {
      // 获取是否显示在视口中
      // console.log(scrollTop)
      if (this.$refs.car_parameter.getBoundingClientRect().top < window.innerHeight + 10) {
        this.$refs.power.start()
        this.$refs.torque.start()
        this.$refs.acceleration.start()
        this.$refs.nedc.start()
        this.$refs.wheelbase.start()
        window.removeEventListener('scroll', this.handleScroll)
      }
    },
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted',this.$mq)
        if(['xs',"md","sm"].includes(this.$mq)){
            this.mobileClass = 'mobile'
        }else{
           this.mobileClass=""
        }
      },
    },
  }
}
</script>
<style  lang="scss" scoped>

.car_parameter {
  position: relative;
  img{
    width: 100%;
  }
}

.parameter_data {
  position: absolute;
  width: 100%;
  height: 6.56vw;
  bottom: 3.125vw;
  left: 0;
  display: flex;
  // justify-content: center;
  align-items: center;
  direction: ltr;
    padding: 0 0.3vw;


}

.parameter_r {
  margin-left: 7.81vw;
  height: 3.4375vw;
  border-radius: 3.4375vw;
  background-color: #fff;
  color: #253039;
  font-size: 1.25vw;
  text-align: center;
  line-height: 3.4375vw;
  padding: 0 1.56vw;
  cursor: pointer;
}

.parameter_l {
  display: flex;
  align-items: center;
}

.parameter_list {
  height: 3.95vw;
  display: flex;
  flex-direction: column;
  padding: 0 2vw;
  border-right: 1px solid #c0c3c5;
  color: #fff;
  font-size: 1.56vw;

  text-align: center;
  justify-content: space-between;
}

.parameter_list .list_t,
.parameter_list .list_t span {

  letter-spacing: 0.1vw;
}

.parameter_list:last-child {
  border-right: none;
}

.list_b {
  color: rgba(255, 255, 255, 0.6);
  font-size: 1vw;
  font-weight: 100;
  text-transform: uppercase;
}

/* 移动端 */

.mobile
{
  .parameter_data{
    flex-direction: column;
    height: 100%;
    justify-content: flex-end;
    bottom: 3.5vw;
  }
  .parameter_l{
    margin-top: 10vw;
    flex-wrap: wrap;
  }
  .parameter_list{
    width: 33.333%;
    box-sizing: border-box;
    margin-bottom: 5vw;
    height: 10vw;
   padding: 0;
  }
  .list_t{
    font-size: 4.5vw;
    white-space: nowrap;
  }
  .parameter_list:nth-child(3n){
    border: none;
  }
  .parameter_list:last-child{
    border: none;
  }
  .parameter_r{
    position: absolute;
    bottom: 5vw;
    margin-left: 0;
    height: 8vw;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 8vw;
    border-radius: 5vw;
    font-size: 3.5vw;
  }
  .list_b{
    font-size: 2.5vw;
  }
}

</style>
