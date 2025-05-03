<!--
 * Author: SHI
 * Date: 2022/7/19
 * Remark: mediaLiaison
 -->

<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="typeList">
      <el-table-column label="性别Gender" align="center" prop="gender" />
      <el-table-column label="姓氏First Name" align="center" prop="lastName" />
      <el-table-column label="名字Last Name" align="center" prop="firstName" />
      <el-table-column label="媒体名称Media Name" align="center" prop="mediaName" />
      <el-table-column label="邮箱E-mail" align="center" prop="email" />
      <el-table-column label="留言信息Message" align="center" prop="msg" show-overflow-tooltip />
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
  import { getMediaLiaisonList } from '@/api/mediaCenter/mediaLiaison'
  export default {
    name: 'mediaLiaison',
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
        getMediaLiaisonList(this.queryParams).then(response => {
          this.typeList = response.data.rows
          this.total = response.data.total
          this.loading = false
        })
      },
    }
  }
</script>
<style>
  .el-tooltip__popper {
    max-width: 700px;
  }
</style>


