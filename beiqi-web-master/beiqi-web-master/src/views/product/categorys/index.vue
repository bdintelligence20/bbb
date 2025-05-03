<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <!-- <el-form-item label="分类编码" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入分类编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="分类名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入分类名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['vehicle:type:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['vehicle:type:update']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['vehicle:type:delete']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['product:category:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="typeList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分类编号" align="center" prop="id" />
      <el-table-column label="车型名称" align="center">
        <template slot-scope="scope">
          {{ scope.row.langs[0].name }}
        </template>
      </el-table-column>
      <el-table-column label="分类排序" align="center" prop="sort" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['vehicle:type:update']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleView(scope.row)"
            v-hasPermi="['vehicle:type:query']"
            >详情</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['vehicle:type:delete']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <!-- <el-form-item label="分类编码" prop="id">
          <el-input v-model="form.id" placeholder="请输入分类编码" />
        </el-form-item> -->
        <el-form-item label="英语分类名称" prop="enName">
          <el-input v-model="form.enName" placeholder="请输入英文分类名称" />
        </el-form-item>
        <el-form-item label="西班牙语分类名称" prop="esName">
          <el-input
            v-model="form.esName"
            placeholder="请输入西班牙语分类名称"
          />
        </el-form-item>
        <el-form-item label="阿拉伯语分类名称" prop="arName">
          <el-input
            v-model="form.arName"
            placeholder="请输入阿拉伯语分类名称"
          />
        </el-form-item>
        <el-form-item label="分类顺序" prop="sort">
          <el-input-number
            v-model="form.sort"
            controls-position="right"
            :min="0"
          />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="显示状态" prop="enable">
          <el-radio-group v-model="form.enable">
            <el-radio :label="1">显示</el-radio>
            <el-radio :label="2">隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="任务详细"
      :visible.sync="openView"
      width="700px"
      append-to-body
    >
      <el-table v-loading="itemLoading" :data="typeItemList">
        <el-table-column label="分类ID" align="center" prop="id" />
        <el-table-column label="分类编号" align="center" prop="vehicleTypeId" />
        <el-table-column label="分类语言" align="center" prop="lang" />
        <el-table-column label="分类名称" align="center" prop="name" />
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          width="180"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import {
  listtype,
  gettype,
  deltype,
  addtype,
  updatetype,
} from "@/api/product/category";
export default {
  name: "CartCategorys",
  dicts: ["sys_normal_disable"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 遮罩层
      itemLoading: true,
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
      // 总条数
      itemTotal: 0,
      // 分类表格数据
      typeList: [],
      // 详情弹框分类表格数据
      typeItemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹出层
      openView: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: undefined,
        name: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [{ required: true, message: "分类编码不能为空", trigger: "blur" }],
        enName: [
          { required: true, message: "英语分类名称不能为空", trigger: "blur" },
        ],
        esName: [
          {
            required: true,
            message: "西班牙语分类名称不能为空",
            trigger: "blur",
          },
        ],
        arName: [
          {
            required: true,
            message: "阿拉伯语分类名称不能为空",
            trigger: "blur",
          },
        ],
        sort: [
          { required: true, message: "分类顺序不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询分类列表 */
    getList() {
      this.loading = true;
      listtype(this.queryParams).then((response) => {
        // console.log(response)
        this.typeList = response.data;
        this.total = response.data.length;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        enName: undefined,
        esName: undefined,
        arName: undefined,
        id: undefined,
        sort: 0,
        remark: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      gettype(id).then((response) => {
        response.data.langs.map((value) => {
          if (value.lang === "en") {
            response.data.enName = value.name;
          } else if (value.lang === "es") {
            response.data.esName = value.name;
          } else {
            response.data.arName = value.name;
          }
        });
        this.form = response.data;
        this.open = true;
        this.title = "修改分类";
      });
    },
    /** 详情按钮操作 */
    handleView(row) {
      this.itemLoading = true;
      this.openView = true;
      const id = row.id || this.ids;
      gettype(id).then((response) => {
        this.typeItemList = response.data.langs;
        this.itemTotal = response.data.length;
        this.itemLoading = false;
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (
            (this.form.id !== undefined) &
            (this.form.createTime !== undefined)
          ) {
            this.form.langs.map((value, key) => {
              if (value.lang === "en") {
                this.form.langs[key].name = this.form.enName;
              } else if (value.lang === "es") {
                this.form.langs[key].name = this.form.esName;
              } else {
                this.form.langs[key].name = this.form.arName;
              }
              this.form.langs[key].enable = this.form.enable
            });
            updatetype(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form = {
              langs: [
                {
                  name: this.form.enName,
                  lang: "en",
                },
                {
                  name: this.form.esName,
                  lang: "es",
                },
                {
                  name: this.form.arName,
                  lang: "ar",
                },
              ],
              sort: this.form.sort,
              id: this.form.id,
            };
            addtype(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除分类编号为"' + ids + '"的数据项？')
        .then(function () {
          return deltype(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/post/export",
        {
          ...this.queryParams,
        },
        `post_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
