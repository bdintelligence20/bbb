<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-wvehicleTypeIdth="68px">
      <el-form-item label="区域编码" prop="vehicleTypeId">
        <el-input
          v-model="queryParams.vehicleTypeId"
          placeholder="请输入区域编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区域名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入区域名称"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['area:manage:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['area:manage:update']"
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['area:manage:delete']"
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['testDrive:storeCartModel:export']"
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" wvehicleTypeIdth="55" align="center" />
      <el-table-column label="区域编号" align="center" prop="id" />
      <el-table-column label="区域标题" align="center">
        <template slot-scope="scope">
          {{scope.row.langs[0].name}}
        </template>
      </el-table-column>
      <el-table-column label="区域顺序" align="center" prop="sort" />
      <el-table-column label="邮箱" align="center" prop="emails" />
<!--      <el-table-column label="预约类型" align="center" prop="imgUrl" width="130">-->
<!--        <template slot-scope="scope">-->
<!--          <el-tag v-if="scope.row.type === 1" type="success">预约试驾</el-tag>-->
<!--          <el-tag v-else type="danger">不需要预约试驾</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <!-- <el-table-column label="区域排序" align="center" prop="sort" /> -->
      <el-table-column label="创建时间" align="center" prop="createTime" wvehicleTypeIdth="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-wvehicleTypeIdth">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['area:manage:update']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            v-hasPermi="['area:manage:delete']"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
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

    <!-- 添加或修改区域对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="600px">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="英语区域标题" prop="nameen">
          <el-input v-model="form.nameen" placeholder="英语区域标题" />
        </el-form-item>
        <el-form-item label="西语区域标题" prop="namees">
          <el-input v-model="form.namees" placeholder="英语区域标题" />
        </el-form-item>
        <el-form-item label="阿语区域标题" prop="namear">
          <el-input v-model="form.namear" placeholder="英语区域标题" />
        </el-form-item>
        <el-form-item label="邮箱" prop="emails">
          <el-input v-model="form.emails" placeholder="邮箱" />
        </el-form-item>
<!--        <el-form-item label="状态">-->
<!--          <el-radio-group v-model="form.type">-->
<!--            <el-radio-->
<!--              v-for="dict in typeOption"-->
<!--              :key="dict.value"-->
<!--              :label="dict.value"-->
<!--            >{{ dict.label }}</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
        <el-form-item label="区域顺序" prop="sort">
          <el-input-number v-model="form.sort" controls-position="right" :min="0" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="任务详细" :visible.sync="openView" wvehicle-class-idth="700px" append-to-body>
      <el-table v-loading="itemLoading" :data="typeItemList">
        <el-table-column label="区域id" align="center" prop="id" />
        <el-table-column label="区域名称" align="center" prop="name" />
        <el-table-column label="区域地址" align="center" prop="address" />
        <el-table-column label="区域邮件" align="center" prop="mails" />
        <el-table-column label="创建时间" align="center" prop="createTime" wvehicle-class-idth="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { listArea, getArea, delArea, addArea, updateArea } from '@/api/mediaCenter/area'
export default {
  name: 'StoreCartModel',
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
      // type字段
      typeOption: [
        {
          value: 1,
          label: '预约试驾'
        },
        {
          value: 0,
          label: '不需要预约试驾'
        }
      ],
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 总条数
      itemTotal: 0,
      // 区域表格数据
      typeList: [],
      // 详情弹框区域表格数据
      typeItemList: [],
      // 弹框标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹出层
      openView: false,
      // 区域区域地址下拉option
      addressOption: [],
      // 区域区域地址 addressProps
      addressProps: {
        value: 'id',
        label: 'name',
        children: 'nodes',
        emitPath: false
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: this.$route.path.includes('country/Area') ? 2 : 1,
        id: undefined,
        name: undefined
      },
      // 表单参数
      form: {
        type: 1
      },
      // 修改元数据记录
      editResData: {},
      // 表单校验
      rules: {
        sort: [
          { required: true, message: '区域顺序', trigger: 'blur' }
        ],
        nameen: [
          { required: true, message: '英语区域标题不能为空', trigger: 'blur' }
        ],
        namees: [
          { required: true, message: '西语区域标题不能为空', trigger: 'blur' }
        ],
        namear: [
          { required: true, message: '阿语区域标题不能为空', trigger: 'blur' }
        ],
        imgUrl: [
          { required: true, message: '特征车辆图片', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  watch: {
    $route () {
      this.getList()
    }
  },
  methods: {
    /** 查询区域列表 */
    getList() {
      this.loading = true
      listArea(this.queryParams).then(response => {
        // console.log(response.data)
        this.typeList = response.data
        this.total = response.data.length
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
        nameen: undefined,
        namees: undefined,
        emails: undefined,
        namear: undefined,
        type: undefined,
        sort: 0,
        remark: undefined
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
      this.title = '新增区域'
    },
    /** 数据重置 */
    addFormatData() {
      const languageList = ['en', 'ar', 'es']
      const langs = []
      languageList.map((value) => {
        langs.push(
          {
            name: this.form['name' + value],
            lang: value
          }
        )
      })
      this.form.langs = langs
    },
    /** 修改按钮数据重置 */
    editButtonFormatData(data) {
      const newObj = {}
      data.langs.map((value) => {
        newObj['name' + value.lang] = value.name
      })
      newObj.emails = data.emails
      newObj.sort = data.sort
      newObj.id = data.id
      newObj.type = data.type
      newObj.createTime = data.createTime
      return newObj
    },
    /** 提交按钮修改表单数据重置 */
    editSubtimeFormatData(data) {
      const languageList = ['en', 'ar', 'es']
      languageList.map((value) => {
        data.langs.map((vue) => {
          if (vue.lang === value) {
            vue.name = this.form['name' + value]
          }
        })
      })
      data.emails = this.form.emails
      data.sort = this.form.sort
      data.type = this.form.type
      return data
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.id
      getArea(id).then(response => {
        this.form = this.editButtonFormatData(response.data)
        this.editResData = response.data
        this.open = true
        this.title = '修改区域'
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined & this.form.createTime !== undefined) {
            // 数据处理
            this.form = this.editSubtimeFormatData(this.editResData)
            this.form.type = this.$route.path.includes('country/Area') ? 2 : 1
            updateArea(this.form).then(response => {
              this.$modal.msgSuccess(response.msg)
              this.open = false
              this.getList()
            })
          } else {
            // 数据处理
            this.addFormatData()
            this.form.type = this.$route.path.includes('country/Area') ? 2 : 1
            addArea(this.form).then(response => {
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
      const id = row.id
      this.$modal.confirm('是否确认删除区域编号为"' + row.id + '"的数据项？').then(function() {
        return delArea(id)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {})
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
