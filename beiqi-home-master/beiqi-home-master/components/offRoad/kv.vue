<!--
 * @Author: 不二
 * @Date: 2024-01-05 16:43:35
 * @LastEditTime: 2024-01-10 15:39:50
-->
<template>
  <div class="kv_box">
    <mq-layout mq="lg+">  <img src="~/assets/image/offRoad/kv.jpg" alt=""></mq-layout>
    <mq-layout :mq="['xs', 'sm', 'md']"> <img src="~/assets/image/offRoad/kv_m.jpg" alt=""></mq-layout>
    <div class="page_title"> OFF ROAD</div>
  </div>
</template>

<script>
export default {
  data() {
return {

};
}

}

</script>
<style  lang="scss" scoped>
.kv_box{
  position: relative;
  img{
    width: 100%;
  }
}
.page_title{
  color: #fff;
  font-size: 4.5vw;
  font-weight: 900;
  position: absolute;
  right: 10vw;
  bottom: 15.5vw;
}
.md,.sm,.xs{
  .page_title{
    font-size: 13vw;
    width: 100%;
    text-align: center;
    top: 10vw;
    right: auto;
    bottom: auto;
    color: #000;
    left: auto;

  }
}
</style>