<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-wvehicleTypeIdth="68px">
<!--      <el-form-item label="媒体编码" prop="vehicleTypeId">-->
<!--        <el-input-->
<!--          v-model="queryParams.vehicleTypeId"-->
<!--          placeholder="请输入媒体编码"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="媒体名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入媒体名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="媒体状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择媒体线索状态" clearable size="small">
          <el-option
            v-for="dict in statusOption"
            :key="dict.value + dict.label"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
      <el-table-column label="媒体编号" align="center" prop="id" />
      <el-table-column label="媒体名称" align="center" prop="mediaName" />
      <el-table-column label="姓" align="center" prop="lastName" />
      <el-table-column label="名" align="center" prop="firstName" />
      <el-table-column label="媒体邮箱" align="center" prop="email" />
      <el-table-column label="订阅语言" align="center" prop="lang" />
      <el-table-column label="媒体介绍" align="center" prop="mediaIntroduction" show-overflow-tooltip/>
      <el-table-column prop="status" label="联系状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" type="warning">已提交</el-tag>
          <el-tag v-else-if="scope.row.status === 1" type="success">已通过</el-tag>
          <el-tag v-else type="danger">已拒绝</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核原因" align="center" prop="reason" wvehicleTypeIdth="180">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.reason" placement="top">
            <div style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">{{scope.row.reason}}</div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>
            {{ parseTime(scope.row.createTime) }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-wvehicleTypeIdth">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['contactus:subscribe:update']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >更新状态</el-button>
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

    <!-- 添加或修改媒体对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="600px">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="媒体状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择媒体线索状态" clearable size="small">
            <el-option
              v-for="dict in statusOption"
              :key="dict.value + dict.label"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="审核原因" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入审核原因" />
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
import { listSubscribe, updateSubscribe } from '@/api/mediaCenter/mediaClues'
export default {
  name: 'MediaClues',
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 遮罩层
      itemLoading: true,
      // 选中数组
      vehicleTypeIds: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      typeList: [],
      // 弹框标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: undefined,
        name: undefined
      },
      // 表单参数
      form: {},
      statusOption: [
        {
          value: 0,
          label: '已提交'
        },
        {
          value: 1,
          label: '已通过'
        },
        {
          value: 2,
          label: '已拒绝'
        }
      ],
      // 表单校验
      rules: {
        status: [
          { required: true, message: '审核状态不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询媒体列表 */
    getList() {
      this.loading = true
      listSubscribe(this.queryParams).then(response => {
        // console.log(response.data)
        this.typeList = response.data.rows
        this.total = response.data.total
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
        status: null,
        id: null,
        reason: null
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
      this.id = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '新增媒体'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.form = row
      this.open = true
      this.title = '更新媒体'
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined & this.form.createTime !== undefined) {
            let { id, status, reason, ...obj } = this.form
            updateSubscribe(this.form).then(response => {
              this.$modal.msgSuccess(response.msg)
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/post/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
