<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工作类别" prop="categoryName">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入工作类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工作区域" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入工作区域"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="mailList">
      <el-table-column label="ID" align="center" prop="id" width="80" />
      <el-table-column label="名称" align="center" prop="fullName" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="电话" align="center" prop="phone" />
      <el-table-column label="工作区域" align="center" prop="areaName" />
      <el-table-column label="岗位名称" align="center" prop="jobTitle" />
      <el-table-column label="岗位类别" align="center" prop="categoryName" />
      <el-table-column label="简历" align="center" prop="resumeUrl">
        <template slot-scope="scope">
          <a :href="getFile(scope.row.resumeUrl)" target="_black" class="download-style">
          {{scope.row.fullName}} 的简历
          </a>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" >
        <template slot-scope="scope">
          {{ parseTime(scope.row.createTime) }}
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { listMail } from "@/api/joinUs/mail";
import { titleCase } from '@/utils/index'
import { parseTime } from '@/utils/ruoyi'
export default {
  name: "Area",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: null,
        name: null
      },
      mailList: []
    };
  },
  created() {
    // console.log(window.location)
    this.getList();
  },
  methods: {
    getFile(file) {
      if (file.startsWith('http')) {
        return file
      } else {
        return `${window.location.origin}/api/file${file}`
      }
    },
    /** 查询售后banner列表 */
    getList() {
      this.loading = true;
      listMail(this.queryParams).then(response => {
        // console.log(response.rows)
        this.mailList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 分类处理
    typeFormat(type) {
      const typeOptions = [
        { label: '显示', type: 0 },
        { label: '隐藏', type: 1 },
      ]
      let res = typeOptions.filter( i => {
        return i.type == type
      })
      return res[0].label
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    }
  }
};
</script>
<style lang="scss">
.image-inline {
  margin-left: 80px;
  .el-form-item__label {
    float: none;
  }
  .el-form-item__content {
    margin-left: 0 !important;
  }
}
.download-style,
.download-style:hover {
  color: #1890ff;
  text-decoration: underline;
}
</style>
