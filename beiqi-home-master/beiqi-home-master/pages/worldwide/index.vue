<template>
  <div class="media-center-page">
    <Map key="" :mapList="mapList" :areaList="areaList"/>
  </div>
</template>

<script>
import Map from '~/components/homePage/Map.vue';
export default {
  name: 'MapPage',
  layout: 'main',
  components: {
    Map
  },
  data() {
    return {
      mapList: [],
      areaList: []
    }
  },
  created() {
    this.getMapList()
    this.getAreaList()
  },
  methods: {
    async getMapList() {
      this.$api.home.getMap().then( res => {
        this.mapList = res.data
      })
    },
    async getAreaList() {
      this.$api.testDrive.countryList({type:2}).then( res => {
        this.areaList = res.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
