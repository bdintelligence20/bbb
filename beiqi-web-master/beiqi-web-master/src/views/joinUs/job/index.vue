<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="岗位名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['website:banner:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['website:banner:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['website:banner:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="jobList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="80" />
      <el-table-column label="招聘职位" align="center" prop="jobTitle" />
      <el-table-column label="所属部门" align="center" prop="department" />
      <el-table-column label="数量" align="center" prop="numb" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span>
            {{ typeFormat(scope.row.status) }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>
            {{ parseTime(scope.row.createTime) }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['website:banner:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['website:banner:remove']"
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

    <!-- 添加或修改招聘岗位对话框 -->
    <el-dialog :title="title" :visible.sync="open" fullscreen append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
      <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="招聘职位名称（英语）" prop="jobTitleEn">
              <el-input v-model="form.jobTitleEn" placeholder="请输入英文名称" />
            </el-form-item>
            <el-form-item label="职位所属部门（英语）" prop="departmentEn">
              <el-input v-model="form.departmentEn" placeholder="请输入英文名称" />
            </el-form-item>
            <el-form-item label="职位类型（英语）" prop="typeEn">
              <el-input v-model="form.typeEn" placeholder="请输入英文名称" />
            </el-form-item>
            <el-form-item label="招聘职位详情（英语）" prop="describeEn">
              <editor ref="editor" v-model="form.describeEn" :min-height="192" :height="360" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="招聘职位名称（西班牙）" prop="jobTitleEs">
              <el-input v-model="form.jobTitleEs" placeholder="请输入西班牙名称" />
            </el-form-item>
            <el-form-item label="职位所属部门（西班牙）" prop="departmentEs">
              <el-input v-model="form.departmentEs" placeholder="请输入西班牙名称" />
            </el-form-item>
            <el-form-item label="职位类型（西班牙）" prop="typeEs">
              <el-input v-model="form.typeEs" placeholder="请输入西班牙名称" />
            </el-form-item>
            <el-form-item label="招聘职位详情（西班牙）" prop="describeEs">
              <editor ref="editor" v-model="form.describeEs" :min-height="192" :height="360" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="招聘职位名称（阿拉伯）" prop="jobTitleAr">
              <el-input v-model="form.jobTitleAr" placeholder="请输入阿拉伯名称" />
            </el-form-item>
            <el-form-item label="职位所属部门（阿拉伯）" prop="departmentAr">
              <el-input v-model="form.departmentAr" placeholder="请输入阿拉伯名称" />
            </el-form-item>
            <el-form-item label="职位类型（阿拉伯）" prop="typeAr">
              <el-input v-model="form.typeAr" placeholder="请输入阿拉伯名称" />
            </el-form-item>
            <el-form-item label="招聘职位详情（阿拉伯）" prop="describeAr">
              <editor ref="editor" v-model="form.describeAr" :min-height="192" :height="360" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="4">
            <el-form-item label="工作区域" prop="areaId">
              <el-select v-model="form.areaId" placeholder="请选择工作区域">
                <el-option
                  v-for="item in jobAreaList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="岗位类别" prop="categoryId">
              <el-select v-model="form.categoryId" placeholder="请选择岗位类别">
                <el-option
                  v-for="item in jobCategoryList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio :label="0">显示</el-radio>
                <el-radio :label="1">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="招聘数量" prop="numb">
              <el-input-number v-model="form.numb" controls-position="right" :min="1" />
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="form.sort" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listJob, getJob, delJob, addJob, updateJob } from "@/api/joinUs/job";
import { listJobCategory } from "@/api/joinUs/jobCategory";
import { listArea } from "@/api/joinUs/area";

import { titleCase } from '@/utils/index'
import { parseTime } from '@/utils/ruoyi'
export default {
  name: "Job",
  components: {
  },
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
      // 招聘岗位数据
      jobList: [],
      jobCategoryList: [],
      jobAreaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: null,
        name: null
      },
      // 表单参数
      form: {},
      // 表单校验
      // 表单校验
      rules: {
        jobTitleEn: [
          { required: true, message: "职位名称不能为空", trigger: "blur" }
        ],
        jobTitleEs: [
          { required: true, message: "职位名称不能为空", trigger: "blur" }
        ],
        jobTitleAr: [
          { required: true, message: "职位名称不能为空", trigger: "blur" }
        ],
        describeEn: [
          { required: true, message: "职位内容不能为空", trigger: "blur" }
        ],
        describeEs: [
          { required: true, message: "职位内容不能为空", trigger: "blur" }
        ],
        describeAr: [
          { required: true, message: "职位内容不能为空", trigger: "blur" }
        ],
        departmentEn: [
          { required: true, message: "职位所属部门不能为空", trigger: "blur" }
        ],
        departmentEs: [
          { required: true, message: "职位所属部门不能为空", trigger: "blur" }
        ],
        departmentAr: [
          { required: true, message: "职位所属部门不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "顺序不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
        numb: [
          { required: true, message: "招聘数量不能为空", trigger: "blur" }
        ],
        areaId: [
          { required: true, message: "工作区域不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "岗位分类不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "工作分类不能为空", trigger: "blur" }
        ],
        typeEn: [
          { required: true, message: "职位类型不能为空", trigger: "blur" }
        ],
        typeEs: [
          { required: true, message: "职位类型不能为空", trigger: "blur" }
        ],
        typeAr: [
          { required: true, message: "职位类型不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList()
    this.getListJobCategory()
    this.getListArea()
  },
  methods: {
    /** 查询售后banner列表 */
    getList() {
      this.loading = true;
      listJob(this.queryParams).then(response => {
        this.jobList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getListJobCategory() {
      const params = {}
      listJobCategory(params).then(response => {
        this.jobCategoryList = response.rows;
      });
    },
    getListArea() {
      const params = {}
      listArea(params).then(response => {
        this.jobAreaList = response.rows;
      });
    },
    // 分类处理
    typeFormat(type) {
      const typeOptions = [
        { label: '显示', type: 0 },
        { label: '隐藏', type: 1 },
      ]
      let res = typeOptions.filter( i => {
        return i.type == type
      })
      return res[0].label
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        jobTitleEn: null,
        jobTitleEs: null,
        jobTitleAr: null,
        departmentEn: null,
        departmentEs: null,
        departmentAr: null,
        describeEn: null,
        describeEs: null,
        describeAr: null,
        typeEn: null,
        typeEs: null,
        typeAr: null,
        sort: 0,
        status: 0,
        numb: 1,
        categoryId: null,
        areaId: null,
        jobTitleDetailLangId: null,
        jobTitleLangId: null,
        language: null,
        createTime: null,
        langData: []
      }
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加招聘职位"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const ids = row.id || this.ids
      getJob(ids).then(response => {
        const res = response.data
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { jobTitle, describe, department, type, ...newObj } = obj
        res.forEach( data => {
          let lang = titleCase(data.language)
          newObj['jobTitle' + lang ] = data.jobTitle
          newObj['describe' + lang ] = data.describe
          newObj['department' + lang ] = data.department
          newObj['type' + lang ] = data.type
        })
        newObj.langData = response.data
        this.form = newObj
        // console.log(this.form,'this.form')
        this.open = true;
        this.title = "修改招聘职位";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateJob(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addJob(formArray).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    resolveFormData() {
      const languageList = ['en', 'es', 'ar']
      var reqArray = []
      let { langData, jobTitleEs, jobTitleEn, jobTitleAr, describeEn, describeEs, describeAr, departmentEn, departmentEs, departmentAr, typeEn, typeEs, typeAr, ...newObj } = this.form
      if(langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.jobTitle = this.form['jobTitle' + lang]
          obj.department = this.form['department' + lang]
          obj.describe = this.form['describe' + lang]
          obj.type = this.form['type' + lang]
          obj.sort = this.form.sort
          obj.status = this.form.status
          obj.numb = this.form.numb
          obj.categoryId = this.form.categoryId
          obj.areaId = this.form.areaId
          reqArray.push(obj)
        })
      } else {
        langData.forEach( (data, i) => {
          let lang = titleCase(data.language)
          langData[i].jobTitle = this.form['jobTitle' + lang]
          langData[i].department = this.form['department' + lang]
          langData[i].describe = this.form['describe' + lang]
          langData[i].sort = this.form.sort
          langData[i].status = this.form.status
          langData[i].type = this.form['type' + lang]
          langData[i].numb = this.form.numb
          langData[i].categoryId = this.form.categoryId
          langData[i].areaId = this.form.areaId
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除招聘职位编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delJob(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
    }
  }
};
</script>
<style lang="scss">
.image-inline {
  margin-left: 80px;
  .el-form-item__label {
    float: none;
  }
  .el-form-item__content {
    margin-left: 0 !important;
  }
}
</style>
