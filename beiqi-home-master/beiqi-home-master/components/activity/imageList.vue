<template>
  <div class="image-list-container">
    <mq-layout :mq="['xl', 'lg', 'md', 'sm']">
      <el-row type="flex" :gutter="5">
        <el-col v-if="recommendList?.[0]" :span="16">
          <ActivityImageItem :image-item="recommendList[0]" />
        </el-col>

        <el-col v-if="newsList && newsList.length >= 0" :span="8">
          <el-row v-for="item in newsList.slice(0, 2)" :key="item.id">
            <ActivityImageItem :image-item="item" />
          </el-row>
        </el-col>

        <template v-if="newsList && newsList.length >= 3">
          <el-col v-for="item in newsList.slice(2, 5)" :key="item.id" :span="8">
            <ActivityImageItem :image-item="item" />
          </el-col>
        </template>

        <el-col v-if="newsList && newsList.length >= 6" :span="8">
          <el-row v-for="item in newsList.slice(5, 7)" :key="item.id">
            <ActivityImageItem :image-item="item" />
          </el-row>
        </el-col>

        <el-col v-if="recommendList?.[1]" :span="16">
          <ActivityImageItem :image-item="recommendList[1]" />
        </el-col>

        <template v-if="newsList && newsList.length >= 8">
          <el-col v-for="item in newsList.slice(7)" :key="item.id" :span="8">
            <ActivityImageItem :image-item="item" />
          </el-col>
        </template>
      </el-row>
    </mq-layout>
    <mq-layout mq="xs">
      <template v-if="recommendList && recommendList.length > 0">
        <div v-for="item in recommendList" :key="item.id">
          <ActivityImageItem :image-item="item" />
        </div>
      </template>
      <template v-if="newsList && newsList.length > 0">
        <div v-for="item in newsList" :key="item.id">
          <ActivityImageItem :image-item="item" />
        </div>
      </template>
    </mq-layout>
  </div>
</template>

<script>
export default {
  name: 'ImageList',
  components: {},
  props: {
    newsList: Array,
    recommendList: Array,
  },
  data() {
    return {
    }
  },

  mounted() { },

  methods: {},
}
</script>

<style lang="scss" scoped>
.image-list-container {
  .el-row {
    flex-wrap: wrap;
  }

  .el-col {
    overflow: hidden;
  }
}

@media only screen and (max-width: 768px) {
  .image-list-container {
    display: block;
    padding: 0 8px;
  }
}
</style>
