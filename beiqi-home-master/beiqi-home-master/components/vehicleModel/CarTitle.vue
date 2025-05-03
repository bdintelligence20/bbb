<!--
 * @Author: 不二
 * @Date: 2023-08-25 10:10:11
 * @LastEditTime: 2023-08-31 11:27:29
-->

<template>
    <div :class="['car_title',mobileClass]">
        <slot name="title"></slot>
        <slot name="content"></slot>
    </div>
</template>

<script>
export default {
    name: "CarTitle",
    data() {    
        return {
            mobileClass:''  
        }
    },
    watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
 
        if(['xs',"md","sm"].includes(this.$mq)){
            this.mobileClass = 'mobile'
        }else{
           this.mobileClass=""
        }
      },
    },
  
  },
}

</script>
<style  scoped>

.car_title{
    width: 100%;
    height: 116px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-transform: uppercase;
    font-size: 30px;
    color: #322e2f;
}
.mobile.car_title{
    height: 10vw;
    font-size: 4vw;
}
</style>