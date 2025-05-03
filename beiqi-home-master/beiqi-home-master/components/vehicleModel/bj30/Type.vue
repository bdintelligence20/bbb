<template>
  <div v-if="typeList.length > 0" ref="vehicleDetails" class="vehicle-details-container">
    <div class="image-list">
      <div class="image-container">
        <mq-layout mq="lg+">
          <img v-lazy="typeList[carType].imgUrl" alt="">
        </mq-layout>
        <mq-layout :mq="['xs', 'sm', 'md']">
          <img v-lazy="typeList[carType].mobileImg" alt="">
        </mq-layout>
      </div>
    </div>
    <div class="tabs-container" :class="{'tabs-container-es': $i18n.locale === 'es'}">
      <span v-for="(item, index) in typeList" :key="item.type" class="btn text-uppercase" :class="{'active': carType === index}" @click="carType=index">{{ item.type }}</span>
    </div>

    <Params ref="params" class="params" :params-list="typeList[carType].params" :class="{'params-es': $i18n.locale === 'es'}"/>
  </div>
</template>

<script>
import Params from './Params'

export default {
  components: {Params},
  props: {
    typeList: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {
      carType: 0,
    };
  },
  mounted() {
  },
  methods: {
  },
};
</script>

<style scoped lang="scss">
.vehicle-details-container {
  position: relative;
}
img {
  width: 100%;
  max-height: 100vh;
  object-fit: cover;
}
.tabs-container {
  position: absolute;
  top: 5%;
  left: 50%;
  transform: translate(-50%);
  background-color: rgba($color: #000000, $alpha: 0.3);
  border-radius: 30px;
  white-space: nowrap;
}
.btn {
  border-radius: 30px;
  font-size: 22px;
  padding: 8px 50px;
  color: #9c9a9a;
  font-weight: 600;
  border: none;
  background: none;
}
.active {
  color: #303030;
  background-color: #fff;
}
.params {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  bottom: 5%;
  white-space: nowrap;
}
@media screen and (max-width: 1200px) {
  .tabs-container {
    display: flex;
    width: 85%;
    border-radius: 15px;
    .btn {
      font-size: 14px;
      padding: 5px 10px;
      text-align: center;
      border-radius: 15px;
      &:first-child {
        flex: 1;
      }
      &:last-child {
        width: 60%;
      }
    }
  }
  .params {
    bottom: 2%;
    .params-item {
      &:nth-last-child(-n+2) {
        width: 50%;
      }
  }
  }
  .tabs-container-es {

    width: auto;
    .btn {
      width: auto!important;
      font-size: 12px;
      flex: 0;
    }
}
}
</style>
