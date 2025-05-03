<template>
  <div class="product-nav__wrapper">
    <!--    pc-->
    <el-tabs v-if="type == 'bar' || type == 'page'" v-model="activeName" class="product-nav__tabs" tab-position="left">
      <!--      all-->
      <el-tab-pane v-if="productMenu && productMenu.length > 0" :label="$t('btn.all')" class="product-nav__pane" name="all">
        <template v-slot:label>
          <span class="label">{{ $t('btn.all') }}</span>
        </template>
        <div v-for="link in productMenu" :key="link.id" :label="link.name" class="nav-car__pane">
          <label class="nav__car-title">{{ link.name }}</label>
          <el-row v-if="link.vehicleClassSummaries && link.vehicleClassSummaries.length > 0" :gutter="50">
            <el-col v-for="car in link.vehicleClassSummaries" :key="car.id" :lg="4" :md="12" :sm="24" :xl="4" :xs="24" class="nav_car-item">
              <!--              all-->
              <div v-show="showNewLabel(car.newLabelFlag)" :class="$i18n.locale == 'es' ? 'icon-es' : ''" class="icon-text">{{ $t('distributor.NEW') }}</div>
              <div @click="handleCarItem(car.id, car.newLabelFlag)">
                <!--                routeName-->
                <nuxt-link :to="localePath(toLink(car.id), $i18n.locale, $i18n.locale)">
                  <el-image :src="baseUrl + car.imgUrl" class="nav__car-image">
                    <div slot="error" class="image-slot">
                      <i class="el-icon-picture-outline"></i>
                    </div>
                  </el-image>
                  <label class="nav__car-name">{{ car.title }}</label>
                </nuxt-link>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <!--      列表-->
      <el-tab-pane v-for="link in productMenu" :key="link.id" :label="link.name" class="product-nav__pane">
        <template v-slot:label>
          <span class="label">{{ link.name }}</span>
        </template>
        <label class="nav__car-title">{{ link.name }}</label>
        <el-row v-if="link.vehicleClassSummaries && link.vehicleClassSummaries.length > 0" :gutter="50">
          <el-col v-for="car in link.vehicleClassSummaries" :key="car.id" :lg="4" :md="12" :sm="24" :xl="4" :xs="24" class="text-center">
            <!--            v-if="!car.newLabelFlag"-->
            <div @click="handleCarItem(car.id, car.newLabelFlag)">
              <nuxt-link :to="localePath(toLink(car.id), $i18n.locale, $i18n.locale)" class="nav_car-item car-category">
                <div :class="$i18n.locale == 'es' ? 'icon-es' : ''" class="icon-wrap">
                  <div v-show="showNewLabel(car.newLabelFlag)" class="icon-text">{{ $t('distributor.NEW') }}</div>
                </div>
                <el-image :src="baseUrl + car.imgUrl" class="nav__car-image">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                </el-image>
                <label class="nav__car-name">{{ car.title }}</label>
              </nuxt-link>
            </div>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
    <!--    移动端-->
    <div v-for="link in productMenu" v-else :key="link.id" :label="link.name" class="nav-car__mobile">
      <label class="nav__car-title">{{ link.name }}</label>
      <el-row v-if="link.vehicleClassSummaries && link.vehicleClassSummaries.length > 0" :gutter="30">
        <el-col v-for="car in link.vehicleClassSummaries" :key="car.id" :span="12" class="text-center">
          <!--          {{car.newLabelFlag}}-->
          <div class="car-item" @click="handleClose(car.id, car.newLabelFlag)">
            <nuxt-link :to="localePath(toLink(car.id), $i18n.locale, $i18n.locale)">
              <div v-show="showNewLabel(car.newLabelFlag)" :class="$i18n.locale == 'es' ? 'icon-es' : ''" class="icon-text">{{ $t('distributor.NEW') }}</div>
              <el-image :src="baseUrl + car.imgUrl" class="nav__car-image">
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
              <label class="nav__car-name">{{ car.title }}</label>
            </nuxt-link>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    type: {
      type: String,
      default: 'bar',
    },
    productMenu: {
      type: Array,
      default: [],
    },
    modelSpan: {
      type: Number,
      default: 4,
    },
    routeName: {
      type: String,
      default: 'models-id',
    },
    routeQuery: {
      type: Object,
      default() {
        return {}
      },
    },
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      activeName: 'all',
    }
  },
  deactivated() {
    this.activeName = 'all'
  },
  mounted() {},
  methods: {
    toLink(id) {
      if (this.routeName.includes('distributorCenter')) {
        return { name: this.routeName, query: { id, type: this.$route.query.type } }
      } else {
        return {
          name: this.routeName,
          params: { id },
        }
      }
    },
    showNewLabel(newLabelFlag) {
      // 如果是undefined或1,不显示，如果是0，则显示
      if (newLabelFlag === undefined || newLabelFlag === 1) {
        return false
      } else {
        return true
      }
    },
    handleClose(carId, newLabelFlag) {
      this.$emit('closeInnerDrawer', false)
      // 如果已经隐藏，则不执行
      if (newLabelFlag === undefined || newLabelFlag === 1) {
        return
      }
      this.$emit('handleCarItem', carId)
    },
    handleCarItem(carId, newLabelFlag) {
      if (this.type === 'page' || this.type === 'drawer') {
        // 如果已经隐藏，则不执行
        if (newLabelFlag === undefined || newLabelFlag === 1) {
          return
        }
        this.$emit('handleCarItem', carId)
      }
      this.$emit('handleHideMenu', false)
    },
  },
}
</script>

<style lang="scss" scoped>
.product-nav__pane {
  padding: 30px;
}

.product-nav__pane {
  overflow-x: hidden;
  overflow-y: auto;
  max-height: calc(100vh - 100px);
  padding: 10px;
}

.nav__car-title {
  font-size: 20px;
  margin-bottom: 10px;
}

.nav-car__pane {
  margin-bottom: 10px;
}

.car-item {
  position: relative;
}

.nav_car-item {
  position: relative;
  text-align: center;
}

.icon-text {
  position: absolute;
  right: -10px;
  top: -20px;
  padding: 2px 5px;
  background: #ee502f;
  color: #fff;
  border-radius: 15px 15px 15px 0;
  text-align: center;
  font-size: 12px;
}

.icon-es {
  right: -40px;
  top: -26px;
}

//分类下的new
.car-category {
  .icon-text {
    top: -10px;
    right: -88px;
  }
}

.nav__car-name {
  color: #000;
  font-size: 14px;
  cursor: pointer;
}

.nav__car-image {
  aspect-ratio: 2.33;
  width: 100%;
  object-fit: cover;
}

.nav-car__mobile {
  margin-bottom: 15px;
}

// .el-col {
//   .nav__car-name {
//     text-align: center;
//   }
// }
.text-center {
  text-align: center;
}

.xs {
  .icon-text {
    top: -25px;
    font-size: 10px;
  }
}
</style>
