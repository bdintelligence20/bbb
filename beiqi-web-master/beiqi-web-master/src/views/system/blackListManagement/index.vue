<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="logList" @selection-change="handleSelectionChange">
      <el-table-column align="center" label="IP地址" prop="ip"/>
      <el-table-column align="center" label="限流剩余时间(秒)" prop="time"/>
      <el-table-column align="center" label="限流次数" prop="value"/>
      <el-table-column align="center" label="方法名称" prop="method"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
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

    <!-- 添加或修改黑名单拦截日志对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="IP地址" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入IP地址"/>
        </el-form-item>
        <el-form-item label="限流时间(小时)" prop="time">
          <el-input v-model.number="form.time" placeholder="请输入时间"/>
        </el-form-item>
        <el-form-item label="限流次数" prop="value">
          <el-input v-model.number="form.value" placeholder="请输入限流次数"/>
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
import { addBlackList, delBlackList, listBlackList } from '@/api/system/blackList'

export default {
  name: 'Log',
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
      // 黑名单拦截日志表格数据
      logList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ip: null,
        method: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ip: [
          { required: true, message: 'IP地址不能为空', trigger: 'blur' },
          { pattern: /^\d+\.\d+\.\d+\.\d+$/, message: '请输入正确格式的IP地址', trigger: 'blur' }
        ],
        time: [
          { required: true, message: '限流时间不能为空', trigger: 'blur' },
          { type: 'number', message: '限流时间必须为数字值', trigger: 'blur' }
        ],
        value: [
          { required: true, message: '限流次数不能为空', trigger: 'blur' },
          { type: 'number', min: 4, message: '限流次数应为大于3的数字值', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询黑名单拦截日志列表 */
    getList() {
      this.loading = true
      listBlackList(this.queryParams).then(response => {
        this.logList = response.rows
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
        ip: null,
        time: null,
        value: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加黑名单'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getBlackList(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改黑名单拦截日志'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLog(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            console.log(this.form, '参数')
            addBlackList(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.ip
      this.$modal.confirm('是否确认删除黑名单拦截日志编号为"' + ids + '"的数据项？').then(function() {
        return delBlackList({ ip: row.ip })
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('monitor/log/export', {
        ...this.queryParams
      }, `log_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
