<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-wvehicleTypeIdth="68px">
      <el-form-item label="商店车型编码" prop="vehicleTypeId">
        <el-input
          v-model="queryParams.vehicleTypeId"
          placeholder="请输入商店车型编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商店车型名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入商店车型名称"
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
          v-hasPermi="['testdrive:vehicle-class:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['testdrive:vehicle-class:update']"
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
          v-hasPermi="['testdrive:vehicle-class:delete']"
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
          v-hasPermi="['testdrive:vehicle-class:delete']"
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
      <el-table-column label="车型编号" align="center" prop="id" />
      <el-table-column label="车型名称" align="center" prop="name">
        <template slot-scope="scope">
          {{ scope.row.langs[0].name }}
        </template>
      </el-table-column>
      <el-table-column label="车辆图片" align="center" prop="imgUrl" width="120">
        <template slot-scope="scope">
          <el-image :src="fileUploadHost + scope.row.imgUrl" lazy @click="previewPicture(fileUploadHost + scope.row.imgUrl)" />
        </template>
      </el-table-column>
      <el-table-column label="车型排序" align="center" prop="sort" />
      <el-table-column label="创建时间" align="center" prop="createTime" wvehicleTypeIdth="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-wvehicleTypeIdth">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['testdrive:vehicle-class:update']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            v-hasPermi="['testdrive:vehicle-class:delete']"
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

    <!-- 添加或修改商店车型对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="600px">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="店铺英语车型标题" prop="nameen">
          <el-input v-model="form.nameen" placeholder="英语车型标题" />
        </el-form-item>
        <el-form-item label="店铺西语车型标题" prop="namees">
          <el-input v-model="form.namees" placeholder="英语车型标题" />
        </el-form-item>
        <el-form-item label="店铺阿语车型标题" prop="namear">
          <el-input v-model="form.namear" placeholder="英语车型标题" />
        </el-form-item>
        <el-form-item label="车辆图片" prop="imgUrl">
          <imageUpload v-model="form.imgUrl" :limit="1" />
        </el-form-item>
        <el-form-item label="店铺车型顺序" prop="sort">
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
        <el-table-column label="商店车型id" align="center" prop="id" />
        <el-table-column label="商店车型名称" align="center" prop="name" />
        <el-table-column label="商店车型地址" align="center" prop="address" />
        <el-table-column label="商店车型邮件" align="center" prop="mails" />
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
import { listclass, getclass, delclass, addclass, updateclass } from '@/api/testDrive/areaVehicle'
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
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 总条数
      itemTotal: 0,
      // 商店车型表格数据
      typeList: [],
      // 详情弹框商店车型表格数据
      typeItemList: [],
      // 弹框标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹出层
      openView: false,
      // 店铺车型地址下拉option
      addressOption: [],
      // 店铺车型地址 addressProps
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
        id: undefined,
        name: undefined
      },
      // 表单参数
      form: {},
      // 修改元数据记录
      editResData: {},
      // 表单校验
      rules: {
        sort: [
          { required: true, message: '店铺车型顺序', trigger: 'blur' }
        ],
        nameen: [
          { required: true, message: '店铺英语车型标题不能为空', trigger: 'blur' }
        ],
        namees: [
          { required: true, message: '店铺西语车型标题不能为空', trigger: 'blur' }
        ],
        namear: [
          { required: true, message: '店铺阿语车型标题不能为空', trigger: 'blur' }
        ],
        imgUrl: [
          { required: true, message: '车辆图片', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询商店车型列表 */
    getList() {
      this.loading = true
      listclass(this.queryParams).then(response => {
        console.log(response.data)
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
        imgUrl: undefined,
        namear: undefined,
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
      this.title = '新增商店车型'
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
      newObj.imgUrl = data.imgUrl
      newObj.sort = data.sort
      newObj.id = data.id
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
      data.imgUrl = this.form.imgUrl
      data.sort = this.form.sort
      return data
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.id
      getclass(id).then(response => {
        this.form = this.editButtonFormatData(response.data)
        this.editResData = response.data
        this.open = true
        this.title = '修改车型'
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.id !== undefined & this.form.createTime !== undefined) {
            // 数据处理
            this.form = this.editSubtimeFormatData(this.editResData)
            updateclass(this.form).then(response => {
              this.$modal.msgSuccess(response.msg)
              this.open = false
              this.getList()
            })
          } else {
            // 数据处理
            this.addFormatData()
            addclass(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除商店车型编号为"' + row.id + '"的数据项？').then(function() {
        return delclass(id)
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
