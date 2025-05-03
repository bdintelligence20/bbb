<!--
 * Author: SHI
 * Date: 2022/7/19
 * Remark: feedback
 -->

<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="typeList">
      <el-table-column label="性别Gender" align="center" prop="gender" />
      <el-table-column label="全名Full Name" align="center" prop="name" />
      <el-table-column label="国籍Nationality" align="center" prop="country" />
      <el-table-column label="电话Phone" align="center" prop="telephone" />
      <el-table-column label="邮箱E-mail" align="center" prop="email" />
      <el-table-column label="问题类型Problem Type" align="center" prop="msgType" />
      <el-table-column label="留言问题Questions" align="center" prop="msg" show-overflow-tooltip />
      <el-table-column label="时间" align="center" prop="createTime">
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
  import { getFeedbackList } from '@/api/contactUs/feedback'
  
  export default {
    name: 'feedback',
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
          pageSize: 10
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.loading = true
        getFeedbackList(this.queryParams).then(response => {
          this.typeList = response.data.rows
          this.total = response.data.total
          this.loading = false
        })
      }
    }
  }
</script>

