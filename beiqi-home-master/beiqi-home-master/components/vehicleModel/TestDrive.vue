<!--
 * @Author: 不二
 * @Date: 2023-09-07 18:15:02
 * @LastEditTime: 2024-02-29 18:23:47
-->
<template>
  <div class="test_drive">
    <mq-layout mq="lg+">
        <div class="test_drive_pc">
            <p class="car_title">
                <img src="@/assets/image/vehicleModel/new_x7/text.png" alt="">
            </p>
            <div :class="{'layout-ar': $i18n.locale === 'ar'}">
                <nuxt-link class="my_btn_test" :to="localePath('/testDrive', $i18n.locale)">{{ $t('btn.testDrive') }}</nuxt-link>
                <a class="my_btn_test" :href="btn_vr[$i18n.locale].vrUrl" target="_blank">{{ $t('btn.viewVR') }}</a>
            </div>
        </div>
  </mq-layout>

    <mq-layout :mq="['xs', 'sm', 'md']">
        <div class="test_drive_mobile">
            <p class="car_title">   <img src="@/assets/image/vehicleModel/new_x7/text.png" alt=""></p>
            <div :class="{'layout-ar': $i18n.locale === 'ar'}">
                <nuxt-link class="my_btn_test" :to="localePath('/testDrive', $i18n.locale)">{{ $t('btn.testDrive') }}</nuxt-link>
                <a class="my_btn_test" :href="btn_vr[$i18n.locale].vrUrl" target="_blank">{{ $t('btn.viewVR') }}</a>
            </div>
        </div>
    </mq-layout>

  </div>
</template>

<script>
export default {
  data() {
    return {
        locale: this.$i18n.locale,
        btn_vr:{
            en:{
                viewVR:"View VR",
                vrUrl:"https://www.baicglobal.com/vrx7/"
            },
            ar:{
                viewVR:"تجربة القيادة",
                vrUrl: 'https://www.baicglobal.com/vrx7/?lang=ar'
            },
            es:{
                viewVR:"Ver VR",
                vrUrl: 'https://www.baicglobal.com/vrx7/?lang=es'
            }
        }
    }
  },
}
</script>
<style  scoped>
.test_drive_pc{
    left: 3vw;
    top: 58vh;
    position: absolute;
    color: #fff;
    /*低于导航下拉菜单的层级*/
    z-index: 4;
    width: 54vw;
}
.layout-ar {
    direction: rtl;
  }

.test_drive_pc .my_btn_test{
    /* width: 8vw; */
    height: 2.5vw;
    line-height: 2.5vw;
    border-radius: 2.5vw;
    font-size: 1.1vw;
    padding: 0 2vw;
    margin-left: 1vw;
}
.test_drive_pc .my_btn_test:hover {
    background-color: rgba(255, 255, 255, .2);
}

.test_drive_mobile{
    z-index: 99;
    left: 0;
    top: 13vh;
    position: absolute;
    color: #fff;
    font-size: 8vw;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 100%;
}


.test_drive_pc .car_title{
    letter-spacing: 0.8vw;
    font-size: 5vw;
    font-style: italic;
}
.test_drive_pc .car_title img{
    width: 60%;
}
.car_title >span{
    font-size: 8vw;
}
.test_drive_mobile .car_title{
    font-size: 7vw;
    display: flex;
    justify-content: center;
}
.test_drive_mobile .car_title img{
    width: 80%;
}
.car_title{
    text-shadow: 0.5vw 0.5vw 8px #000;

}
.my_btn_test{
    text-align: center;
    color: #fff;
    border: 1px solid rgba(255, 255, 255, 1);
    display: block;
    letter-spacing: 0.1vw;
    text-align: center;
    transition: all 0.5s;
    display: inline-block;

}
.test_drive_mobile .my_btn_test{
    /* width: 30vw; */
    height: 8vw;
    line-height: 8vw;
    border-radius: 8vw;
    font-size: 3vw;
    margin-top: 5vw;
    padding: 0 5vw;
}
.lang___ar .test_drive{
    direction:ltr;
    text-align: left;
}
</style>
