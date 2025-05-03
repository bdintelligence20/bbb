<template>
  <div class="job-page">
    <BannerVue :image="image"></BannerVue>
    <div class="max-1680">
      <JobIconVue></JobIconVue>
    </div>
    <div class="max-1200">
      <div class="index-section">
        <h2 class="section-title text-center">{{ $t('job.list.title') }}</h2>
        <el-row :gutter="50" type="flex" justify="space-between">
          <el-col :xs="12" :sm="12" :md="10" :lg="10" :xl="10">
            <SelectVue :options="jobCategoryOptions" :placeholder="$t('job.form.jobCategoryOptions')" @changeValue="categoryChange"></SelectVue>
          </el-col>
          <el-col :xs="12" :sm="12" :md="10" :lg="10" :xl="10">
            <SelectVue :options="areaOptions" :placeholder="$t('job.form.areaOptions')" @changeValue="areaChange"></SelectVue>
          </el-col>
        </el-row>
        <el-table
          :data="tableData"
          style="width: 100%; ">
          <el-table-column :label="$t('job.list.jobTitle')">
            <template slot-scope="scope">
              <nuxt-link :to="localePath({name: 'job-detail-id', params: {id: scope.row.id}})" class="link">{{ scope.row.jobTitle }}</nuxt-link>
            </template>
          </el-table-column>
          <el-table-column prop="type" :label="$t('job.list.jobNatrue')"></el-table-column>
          <el-table-column prop="numb" :label="$t('job.list.number')" align="center"></el-table-column>
          <el-table-column prop="areaName" :label="$t('job.list.location')" align="center"></el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="total"
          style="display: flex; justify-content: center;">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import JobIconVue from '~/components/joinUs/jobIcon.vue'
import SelectVue from '~/components/select'
import BannerVue from '~/components/img/banner.vue';

export default {
  name: "Job",
  components: { JobIconVue, SelectVue, BannerVue },
  layout: 'main',
  data() {
    return {
      list: [],
      areaOptions: [],
      areaId: null,
      jobCategoryOptions: [],
      jobCategoryId: null,
      queryParams: {
        areaId: null,
        categoryId: null
      },
      pageNum: 1, // 当前页数
      pageSize: 10, // 每页显示条目个数
      total: 0,  // 总条目数
      tableData: [],
      image: {
        url: '',
        alt: '',
        aspectRatio: 2.5
      },

    }
  },
  created() {
    this.getBanner()
    this.getList()
    this.getjobCategory()
    this.getjobArea()
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      this.pageNum = 1;
      this.getList({ recommend: 0 });
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getList({ recommend: 0 });
    },
    async getList() {
      let params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
      };
      this.$api.job.jobList(Object.assign(params, this.queryParams)).then(res => {
        this.tableData = res.rows
        this.total = res.total
      });

    },
    areaChange(val) {
       this.queryParams.areaId = val
       this.getList()
    },
    categoryChange(val) {
       this.queryParams.categoryId = val
       this.getList()
    },
    async getBanner() {
      this.$api.home.getBanner({type: 3}).then(res => {
        const data = res.data
        this.image.url = data[0]?.imageUrl;
        this.image.aspectRatio = parseFloat(data[0].aspectRatio) + '%'
      });
    },
    async getjobCategory() {
      this.$api.job.jobCategory().then(res => {
        this.jobCategoryOptions = res.data
      });
    },
    async getjobArea() {
      this.$api.job.jobArea().then(res => {
        this.areaOptions = res.data
      });
    }
  }
}
</script>
<style lang="scss" scope>
.job-page{
  min-height: 100vh;
}
.job-page input {
  background-color: #f8f8f8;
}
.link:hover {
  color: $themen-color;
  text-decoration: underline;
  text-decoration-color: $themen-color;
}
</style>
