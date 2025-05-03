<template>
  <div class="app-container">
    <el-form
      v-show="showSearch"
      ref="queryForm"
      :inline="true"
      :model="queryParams"
      label-width="68px"
      size="small"
    >
      <el-form-item label="素材类型" prop="fileType">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择素材类型"
          style="width: 100%"
        >
          <el-option
            v-for="item in typeOptions"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="文件名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          clearable
          placeholder="请输入文件名称"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车型" prop="model">
        <el-select
          v-model="queryParams.model"
          placeholder="请选择车型"
          style="width: 100%"
        >
          <el-option
            v-for="item in modelList"
            :key="item.vehicleClassId"
            :label="item.title"
            :value="item.vehicleClassId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="内容介绍" prop="content">
        <el-input
          v-model="queryParams.content"
          clearable
          placeholder="请输入内容介绍"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片类型" prop="materialPicsType">
        <el-input
          v-model="queryParams.materialPicsType"
          clearable
          placeholder="请输入文件类型"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下载链接" prop="downloadLink">
        <el-input
          v-model="queryParams.downloadLink"
          clearable
          placeholder="请输入下载链接"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态"
          style="width: 100%"
        >
          <el-option
            v-for="item in statusList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          icon="el-icon-search"
          size="mini"
          type="primary"
          @click="handleQuery"
          style="margin-left: 20px"
        >搜索
        </el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置
        </el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:material:add']"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:material:edit']"
          :disabled="single"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:material:remove']"
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="materialList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="素材类型" prop="type"/>
      <el-table-column align="center" label="车型" prop="model">
        <template slot-scope="scope">
          <div>{{ getLabelByValue(scope.row.model) }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="图片类型" prop="materialPicsType"/>
      <el-table-column align="center" label="文件名称" prop="fileName" width="280" />
      <el-table-column align="center" label="内容介绍" prop="content" width="280" />
      <el-table-column align="center" label="下载链接" prop="downloadLink" width="200">
        <template slot-scope="scope">
          <a :href="scope.row.downloadLink" target="_blank">{{ scope.row.downloadLink }}</a>
        </template>
      </el-table-column>
      <el-table-column align="center" label="数量" prop="qty"/>
      <el-table-column
        align="center"
        class-name="small-padding fixed-width"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['system:material:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            v-hasPermi="['system:material:remove']"
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
      v-show="total > 0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改素材对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="40%">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="素材类型" prop="type">
          <el-select
            v-model="form.type"
            placeholder="请选择素材类型"
            style="width: 100%"
          >
            <el-option
              v-for="item in typeOptions"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车型" prop="model" v-if="form.type !== 'BasicBtand' && form.type !== 'Event'">
          <el-select
            v-model="form.model"
            placeholder="请选择车型"
            style="width: 100%"
          >
            <el-option
              v-for="item in modelList"
              :key="item.vehicleClassId"
              :label="item.title"
              :value="item.vehicleClassId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="0">公共</el-radio>
            <el-radio :label="1">专属</el-radio>
          </el-radio-group>
          <el-button
            icon="el-icon-edit"
            style="margin-left: 20px"
            type="primary"
            @click="handleOpenUserList"
          >名单
          </el-button
          >
        </el-form-item>

        <el-form-item label="文件名称" prop="fileName" v-if="form.type === 'BasicBtand'">
          <el-input
            v-model="form.fileName"
            placeholder="请输入文件名称"
            type="textarea"
          />
        </el-form-item>
        <el-form-item label="文件类型" prop="fileType" v-if="form.type === 'BasicBtand'">
          <el-input v-model="form.fileType" placeholder="请输入文件类型"/>
        </el-form-item>
        <el-form-item label="文件格式" prop="format" v-if="form.type === 'BasicBtand'">
          <el-input v-model="form.format" placeholder="请输入文件类型"/>
        </el-form-item>

        <el-form-item label="时间" prop="date" v-if="form.type === 'Social'">
          <el-input v-model="form.date" placeholder="请输入时间"/>
        </el-form-item>

        <el-form-item label="图片类型" prop="materialPicsType" v-if="form.type !== 'BasicBtand'">
          <el-input
            v-model="form.materialPicsType"
            placeholder="请输入图片类型"
          />
        </el-form-item>
        <el-form-item label="下载链接" prop="downloadLink">
          <el-input v-model="form.downloadLink" placeholder="请输入下载链接"/>
        </el-form-item>
        <el-form-item label="内容介绍" prop="content" v-if="form.type !== 'BasicBtand'">
          <el-input
            v-model="form.content"
            placeholder="请输入内容介绍"
            type="textarea"
          />
        </el-form-item>
        <!-- <el-form-item label="市场" prop="market">
          <el-input v-model="form.market" placeholder="请输入市场"/>
        </el-form-item> -->
        <el-form-item label="数量" prop="qty" v-if="form.type !== 'BasicBtand'">
          <el-input v-model="form.qty" placeholder="请输入数量"/>
        </el-form-item>
        <el-form-item label="第一张截图" v-if="form.type !== 'BasicBtand'">
          <image-upload v-model="form.firstScreenshot"/>
        </el-form-item>
        <el-form-item label="第二张截图" v-if="form.type !== 'BasicBtand'">
          <image-upload v-model="form.secondScreenshot"/>
        </el-form-item>
        <el-form-item label="第三张截图" v-if="form.type !== 'BasicBtand'">
          <image-upload v-model="form.thirdScreenshot"/>
        </el-form-item>
        <el-form-item label="更多图片" v-if="form.type !== 'BasicBtand'">
          <image-upload v-model="form.viewMore"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户列表对话框 -->
    <el-dialog
      :before-close="handleCloseOpenUserList"
      :visible.sync="openUserList"
      append-to-body
      title="用户列表"
      width="60%"
    >
      <el-table
        ref="multipleTable"
        :data="tableData"
        max-height="600"
        style="width: 100%"
        tooltip-effect="dark"
        @selection-change="handleSelectionChangeUser"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column label="ID" prop="userId"></el-table-column>
        <el-table-column label="姓名">
          <template slot-scope="scope">{{ scope.row.nickName }}</template>
        </el-table-column>
        <el-table-column label="email" prop="email" show-overflow-tooltip>
        </el-table-column>
      </el-table>

      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCloseOpenUserList">取 消</el-button>
        <el-button type="primary" @click="setSelectId">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addMaterial, delMaterial, getMaterial, listMaterial, listUser, updateMaterial } from '@/api/system/material'
import { listclass } from '@/api/product/list'

export default {
  name: 'Material',
  data() {
    return {
      typeOptions: [
        'Photos',
        'Videos',
        'Training',
        'Designs',
        'Social',
        'Event',
        'BasicBtand'
      ],
      statusList: [
        {
          value: 0,
          label: '公共'
        },
        {
          value: 1,
          label: '专属'
        }
      ],
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
      // 素材表格数据
      materialList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        model: null,
        fileName: null,
        fileType: null,
        picsType: null,
        downloadLink: null,
        baiduNetdisk: null,
        date: null,
        content: null,
        market: null,
        qty: null,
        firstScreenshot: null,
        secondScreenshot: null,
        thirdScreenshot: null,
        viewMore: null,
        deleteFlag: null,
        deleteTime: null,
        creatTime: null,
        materialPicsType: '', // 图片类型
        status: null
      },
      form: {
        status: '0'
      },
      // 表单校验
      rules: {},
      modelList: [],
      baseURL: process.env.VUE_APP_BASE_API,
      // 是否显示用户列表
      openUserList: false,
      tableData: [],
      multipleSelection: [],
      selectID: [],
      userParams: {
        pageNum: 1,
        pageSize: 999,
      }
    }
  },
  created() {
    this.getList()
    this.getModels()
    this.getUserList()
  },
  mounted() {
  },
  methods: {
    getLabelByValue(id) {
      return this.modelList.find((obj) => {
        return obj.vehicleClassId == id
      })?.title
    },
    /** 查询素材列表 */
    getList() {
      this.loading = true
      listMaterial(this.queryParams).then((response) => {
        this.materialList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    getModels() {
      listclass({ presentation: true }).then((res) => {
        res.data.forEach((item) => {
          this.modelList.push(item.langs[0])
        })
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
        materialId: null,
        type: '',
        model: null,
        fileName: null,
        fileType: null,
        picsType: null,
        downloadLink: null,
        baiduNetdisk: null,
        date: null,
        content: null,
        market: null,
        qty: null,
        firstScreenshot: null,
        secondScreenshot: null,
        thirdScreenshot: null,
        viewMore: null,
        deleteFlag: null,
        deleteTime: null,
        creatTime: null,
        status: 0,
        materialPicsType: '', //图片类型
        format: '' //文件格式
      }
      // this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.queryParams.type = null
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.materialId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加素材'
      this.selectID = []
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const materialId = row.materialId || this.ids
      getMaterial(materialId).then((response) => {
        this.form = response.data[0]
        this.form.status = Number(this.form.status)
        this.form.model = Number(this.form.model)
        this.selectID = this.form.userIds
        this.open = true
        this.title = '修改素材'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          // 如果类型是私有1，携带userIds
          if (this.form.status == 1) {
            this.form.userIds = this.selectID
          } else {
            this.form.userIds = []
          }
          if (this.form.materialId != null) {
            updateMaterial(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addMaterial(this.form).then((response) => {
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
      const materialIds = row.materialId || this.ids
      this.$modal
        .confirm('是否确认删除素材编号为"' + materialIds + '"的数据项？')
        .then(function() {
          return delMaterial(materialIds)
        })
        .then(() => {
          this.getList()
          this.$modal.msgSuccess('删除成功')
        })
        .catch(() => {
        })
    },

    handleOpenUserList(e) {
      this.openUserList = true

      if (this.form.materialId != null) {
        // 表示修改 需要将之前的提取覆盖
        this.selectID = this.form.userIds
      }

      setTimeout(() => {
        this.toggleSelection()
      }, 100)
    },
    handleCloseOpenUserList() {
      this.$refs.multipleTable.clearSelection()
      this.openUserList = false
    },

    // 根据id取出数组中的数据
    getMaterial() {
      // 要取出的 id 列表
      let idsToRetrieve = this.selectID
      // 使用 filter 方法筛选出指定 id 的数据
      let filteredData = this.tableData.filter((item) =>
        idsToRetrieve.includes(item.userId)
      )
      return filteredData
    },
    toggleSelection() {
      let rows = this.getMaterial()
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      }
    },
    handleSelectionChangeUser(val) {
      this.multipleSelection = val
    },

    setSelectId() {
      let selectID = []
      this.multipleSelection.forEach((item) => {
        selectID.push(item.userId)
      })
      this.selectID = selectID
      this.handleCloseOpenUserList()
    },

    // 获取用户列表
    getUserList() {
      listUser(this.userParams).then((res) => {
        this.tableData = res.rows
      })
    }
  }
}
</script>
