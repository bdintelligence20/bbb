<template>
  <div class="index-section">
    <h3 class="section-title text-center">{{ $t('job.title') }}</h3>
    <!-- <el-row :gutter="0" v-if="iconList && iconList.length > 0" type="flex" justify="space-between" class="index-section">
      <el-col :xs="12" :sm="12" :md="4" :lg="4" :xl="4" v-for="icon in iconList" :key="icon.id">
        <div  class="icon-grid-item">
          <el-image :src="baseUrl + icon.imageUrl" class="icon-image">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
          <label class="icon-title">{{ icon.name }}</label>
        </div>
      </el-col>
    </el-row>  -->
    <el-row class="job-list" :gutter="10">
      <el-col class="job-item-left img_box" v-if="iconList && iconList.length > 0" :span="8">
        <el-image  :src="baseUrl + iconList[0].imageUrl" class="icon-image"></el-image>
        <div class="job-item-title">
           {{ iconList[0].name }}
        </div>
      </el-col>
      <el-col class="job-item-right" :span="16" v-if="iconList && iconList.length > 0">
        <el-row class="" :gutter="10">
          <el-col :span="12" class="img_box job-item-right-left" v-if="iconList[1]">
            <el-image  :src="baseUrl + iconList[1].imageUrl" class="icon-image"></el-image>
            <div class="job-item-title">
              {{ iconList[1].name }}
            </div>
          </el-col>
          <el-col :span="12" class="img_box job-item-right-right" v-if="iconList[2]">
            <el-image  :src="baseUrl + iconList[2].imageUrl" class="icon-image"></el-image>
            <div class="job-item-title">
              {{ iconList[2].name }}
            </div>
          </el-col>
        </el-row>
        <el-row class="" :gutter="10" v-if="iconList[3]">
          <el-col :span="24" class="img_box job-item-bottom">
          <el-image  :src="baseUrl + iconList[3].imageUrl" class="icon-image"></el-image>
          <div class="job-item-title">
            {{ iconList[3].name }}
          </div>
          </el-col>
        </el-row>
        <el-row class="" :gutter="10" v-if="iconList[4]">
          <el-col :span="24" class="img_box job-item-bottom">
          <el-image  :src="baseUrl + iconList[4].imageUrl" class="icon-image"></el-image>
          <div class="job-item-title">
            {{ iconList[4].name }}
          </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      iconList: [],
      baseUrl: process.env.imgBaseUrl
    };
  },
  created() {
    this.getIconList()
  },
  methods: {
    async getIconList() {
      this.$api.job.iconList().then(res => {
        this.iconList = res.data;
      });
    }
  }
}
</script>

<style scoped lang="scss">
.job-list {
  display: flex;
}
.img_box {
  height: 0;
  position: relative;
  .el-image {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }
  .job-item-title  {
    position: absolute;
    bottom: 20px;
    left: 0;
    right: 0;
    text-align: center;
    color: #fff;
    font-size: 24px;
    text-shadow: 1px 1px 3px rgba($color: #000000, $alpha: 0.5);
  }
}
.job-item-left {
  padding-top: 54.6%;
  .el-image {
    right: 1%;
  }
}
.job-item-right-left,.job-item-right-right {
  margin-bottom: 1%;
  padding-top: 26.6%;
  .el-image {
    left: 5px;
    right: 5px;
  }
}
.job-item-bottom {
  margin-bottom: 10px;
  padding-top: 26.6%;
  .el-image {
    left: 5px;
    right: 5px;
  }
}
.xs, .md, .sm {
  .job-item-title  {
    display: none;
  }
}

</style>
