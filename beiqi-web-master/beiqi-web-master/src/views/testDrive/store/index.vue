/* eslint-disable vue/no-parsing-error */
<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-wvehicle-type-idth="68px">
      <el-form-item label="商店编码" prop="vehicleTypeId">
        <el-input
          v-model="queryParams.vehicleTypeId"
          placeholder="请输入商店编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商店名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入商店名称"
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
          v-hasPermi="['testDrive:store:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['testDrive:store:edit']"
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
          v-hasPermi="['testDrive:store:remove']"
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
          v-hasPermi="['testDrive:store:export']"
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
      <el-table-column type="selection" wvehicle-type-idth="55" align="center" />
      <el-table-column label="商店编号" align="center" prop="id" />
      <el-table-column label="商店名称" align="center" prop="name" />
      <el-table-column label="商店地址" align="center" prop="address" />
      <el-table-column label="商店排序" align="center" prop="sort" />
      <el-table-column label="创建时间" align="center" prop="createTime" wvehicle-type-idth="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-wvehicleTypeIdth">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['testDrive:store:edit']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            v-hasPermi="['testDrive:store:edit']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleBind(scope.row)"
          >绑定车辆型号</el-button>
          <el-button
            v-hasPermi="['testDrive:store:remove']"
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

    <!-- 添加或修改商店对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="600px">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="店铺名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入店铺名称" />
        </el-form-item>
        <el-form-item label="店铺邮箱" prop="mails">
          <el-input v-model="form.mails" placeholder="请输入店铺邮箱" />
        </el-form-item>
        <el-form-item label="店铺详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入店铺详细地址" />
        </el-form-item>
        <el-form-item label="店铺地址" prop="areaId">
          <el-cascader v-model="form.areaId" :options="addressOption" :props="addressProps" placeholder="请选择店铺地址" clearable />
        </el-form-item>
        <el-form-item label="店铺顺序" prop="sort">
          <el-input-number v-model="form.sort" controls-position="right" :min="0" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 绑定车型对话框 -->
    <el-dialog :title="title" :visible.sync="openBind" append-to-body width="600px">
      <el-form ref="bindForm" :model="form" label-width="150px">
        <el-form-item label="车型列表" prop="model">
          <el-select v-model="bindForm" multiple placeholder="请选择车型">
            <el-option
              v-for="item in bindOptions"
              :key="item.label"
              :value="item.value"
              :label="item.label"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addbindSubmitForm">确 定</el-button>
        <el-button @click="cancel('bind')">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="任务详细" :visible.sync="openView" wvehicle-class-idth="700px" append-to-body>
      <el-table v-loading="itemLoading" :data="typeItemList">
        <el-table-column label="商店id" align="center" prop="id" />
        <el-table-column label="商店名称" align="center" prop="name" />
        <el-table-column label="商店地址" align="center" prop="address" />
        <el-table-column label="商店邮件" align="center" prop="mails" />
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
import { liststore, getstore, delstore, addstore, updatestore, getarea, getAreClass, addAreClass, getAllCartModel } from '@/api/testDrive/store'
export default {
  name: 'Store',
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
      // 商店表格数据
      typeList: [],
      // 详情弹框商店表格数据
      typeItemList: [],
      // 弹框标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹出层
      openView: false,
      // 是否显示绑定车型弹出层
      openBind: false,
      // 店铺地址下拉option
      addressOption: [],
      // 绑定车型  option
      bindOptions: [],
      // 店铺地址 addressProps
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
      // 绑定车型表单
      bindForm: [],
      bindStorId: null,
      // 表单校验
      rules: {
        sort: [
          { required: true, message: '商店顺序不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '商店名称不能为空', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '商店详细地址不能为空', trigger: 'blur' }
        ],
        areaId: [
          { required: true, message: '地区ID不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getAreaTree()
    this.getAllCartModelList()
  },
  methods: {
    /** 查询商店列表 */
    getList() {
      this.loading = true
      liststore(this.queryParams).then(response => {
        this.typeList = response.data.rows
        this.total = response.data.total
        this.loading = false
      })
    },
    // 获取区域树
    getAreaTree() {
      getarea().then(response => {
        this.addressOption = this.getTreeData(response.data)
      })
    },
    // 获取所有车型
    getAllCartModelList() {
      getAllCartModel().then(response => {
        this.bindOptions = this.bindFormatData(response.data)
      })
    },
    // 取消按钮
    cancel(type) {
      if (type === 'bind') {
        this.openBind = false
      } else {
        this.open = false
      }
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        name: undefined,
        address: undefined,
        mails: undefined,
        areaId: undefined,
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
    getTreeData(data) {
      for (let i = 0; i < data.length; i++) {
        if (data[i].nodes.length <= 0) {
          // children若为空数组，则将 children 设为null
          data[i].nodes = null
        } else {
          // children若不为空数组，则继续 递归调用 本方法
          this.getTreeData(data[i].nodes)
        }
      }
      return data
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '新增商店'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.id
      getstore(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改车型'
      })
    },
    /** 详情按钮操作 */
    handleView(row) {
      this.itemLoading = true
      this.openView = true
      const id = row.id || this.id
      getstore(id).then(response => {
        this.typeItemList = response.data.rows
        this.itemTotal = response.data.total
        this.itemLoading = false
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.vehicleTypeId !== undefined & this.form.createTime !== undefined) {
            updatestore(this.form).then(response => {
              this.$modal.msgSuccess(response.msg)
              this.open = false
              this.getList()
            })
          } else {
            addstore(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 绑定数据重置 */
    bindFormatData(data) {
      let newObj = []
      data.map((value) => {
        let _index = value
        value.langs.map((val) => {
          if (val.lang === 'en') {
            newObj.push(
              {
                value: _index.id,
                label: val.name
              }
            )
          }
        })
      })
      return newObj
    },
    /** 绑定数据重置 */
    bindEditFormatData(data) {
      let newObj = []
      data.map((value) => {
        value.langs.map((val) => {
          if (val.lang === 'en') {
            newObj.push(val.testDriveVehicleClassId)
          }
        })
      })
      return newObj
    },
    /** 绑定车型按钮操作 */
    handleBind(row) {
      this.bindStorId = row.id
      getAreClass(row.id).then(response => {
        this.bindForm = this.bindEditFormatData(response.data)
        console.log(this.bindForm)
        this.openBind = true
        this.title = '绑定车型'
      })
    },
    /** 绑定车型查看 */
    addbindFormatData() {
      const newObj = []
      this.bindForm.map((value) => {
        newObj.push(
          {
            testDriveVehicleClassId: value,
            storeId: this.bindStorId
          }
        )
      })
      return newObj
    },
    /** 绑定车型新增 */
    addbindSubmitForm() {
      this.bindForm = this.addbindFormatData()
      addAreClass(this.bindForm).then(() => {
        this.$modal.msgSuccess('绑定成功')
        this.openBind = false
        this.getList()
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id
      this.$modal.confirm('是否确认删除商店编号为"' + row.name + '"的数据项？').then(function() {
        return delstore(id)
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
