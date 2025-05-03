<!--
 * @Date: 2023-08-14 19:12:33
 * @LastEditors: liu
 * @LastEditTime: 2023-08-15 14:59:25
-->
<template>
  <div class="bread-crumbs-box">
    <nuxt-link :to="localePath(linkUrl)" class="bread-crumbs">
      <mq-layout mq="lg+">
        <i class="el-icon-d-arrow-left"></i>
      </mq-layout>

      <mq-layout :mq="['xs', 'sm', 'md']">
        <i class="el-icon-back"></i>
      </mq-layout>

      <span class="title">{{ title ? title : $t('distributor.HOME') }}</span>
    </nuxt-link>
  </div>
</template>

<script>
export default {
  name: "DistributorBreadcumb",
  props: {
    title: {
      type: String,
      default: ''
    },
    linkUrl: {
      type: String,
      default: '/distributorCenter'
    }
  },
  data() {
    return {}
  },

  mounted() {

  },

  methods: {},
}
</script>

<style lang="scss" scoped>
.bread-crumbs-box{
  display: inline-block;
}
.bread-crumbs {
  display: flex;
  align-items: center;
  margin-top: 25px;
  font-size: 13px;


  .el-icon-d-arrow-left {
    display: block;
    transform: scaleX(1.2);
    font-size: 17px;
    color: #000;
    cursor: pointer;
  }

  .el-icon-back {
    font-size: 24px;
  }

  .title {
    margin-left: 10px;
    cursor: pointer;
    line-height: 1;
  }
}
</style>
