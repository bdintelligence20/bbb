<!--
 * Author: SHI
 * Date: 2022/7/19
 * Remark: client
 -->

<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="typeList">
      <el-table-column label="试驾国家" align="center" prop="areaName" />
      <el-table-column label="试驾车型" align="center" prop="testDriveVehicleClassName" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="电话" align="center" prop="telephone" />
      <el-table-column label="邮件地址" align="center" prop="mailbox" />
      <el-table-column label="时间" align="center" prop="createTime" >
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
  import { getClientList } from '@/api/testDrive/client'
  export default {
    name: 'client',
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        typeList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.loading = true
        getClientList(this.queryParams).then(response => {
          this.typeList = response.data.rows
          this.total = response.data.total
          this.loading = false
        })
      },
    }
  }
</script>


