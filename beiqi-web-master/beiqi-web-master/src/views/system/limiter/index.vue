<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="limiterList">
      <el-table-column align="center" label="限流编号" prop="id"/>
      <el-table-column align="center" label="系统模块" prop="name"/>
      <el-table-column align="center" label="限流时间(秒)" prop="time"/>
      <el-table-column align="center" label="限流次数" prop="count"/>
      <el-table-column align="center" label="方法名" prop="method"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改限流注解配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="系统模块" prop="id">
          <el-input v-model="form.name" disabled placeholder="请输入限流时间"/>
        </el-form-item>
        <el-form-item label="限流时间(秒)" prop="time">
          <el-input v-model="form.time" placeholder="请输入限流时间"/>
        </el-form-item>
        <el-form-item label="限流次数" prop="count">
          <el-input v-model="form.count" placeholder="请输入限流次数"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getLimiter, listLimiter, updateLimiter } from '@/api/system/limiter'

export default {
  name: 'Limiter',
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
      // 限流注解配置表格数据
      limiterList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        time: null,
        count: null,
        method: null
        // limitType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询限流注解配置列表 */
    getList() {
      this.loading = true
      listLimiter(this.queryParams).then(response => {
        this.limiterList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        time: null,
        count: null,
        method: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getLimiter(id).then(response => {
        console.log(response, 'response')
        this.form = response.data
        this.open = true
        this.title = '修改限流注解配置'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          const { id, time, count, method } = this.form
          if (this.form.id != null) {
            const params = {
              id,
              time,
              count,
              method
            }
            updateLimiter(params).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    }
  }
}
</script>
