<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入状态"
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

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="80" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column prop="coverImageUrl" label="封面图片" align="center" width="150" >
        <template slot-scope="scope">
          <el-image :src="fileUploadHost + scope.row.coverImageUrl" @click="previewPicture(fileUploadHost + scope.row.coverImageUrl)"/>
        </template>
      </el-table-column>
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

    <!-- 添加或修改社会责任content对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
        <el-form-item label="封面图片" prop="coverImageUrl">
          <imageUpload :limit="1" v-model="form.coverImageUrl"/>
        </el-form-item>
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="标题（英语）" prop="titleEn">
              <el-input v-model="form.titleEn" placeholder="请输入英文标题" />
            </el-form-item>
            <el-form-item label="英语PDF文件" prop="reportUrlEn">
              <fileUpload v-model="form.reportUrlEn" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题（西班牙）" prop="titleEs">
              <el-input v-model="form.titleEs" placeholder="请输入西班牙标题" />
            </el-form-item>
            <el-form-item label="西班牙PDF文件" prop="reportUrlEs">
              <fileUpload v-model="form.reportUrlEs" :limit="1" />
            </el-form-item>            
          </el-col>
          <el-col :span="8">           
            <el-form-item label="标题（阿拉伯）" prop="titleAr">
              <el-input v-model="form.titleAr" placeholder="请输入阿拉伯标题" />
            </el-form-item>
            <el-form-item label="阿拉伯PDF文件" prop="reportUrlAr">
              <fileUpload v-model="form.reportUrlAr" :limit="1" />
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
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/social/report";
import { titleCase } from '@/utils/index'
import { parseTime } from '@/utils/ruoyi'
export default {
  name: "report",
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
      // 首页banner表格数据
      reportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单参数
      form: {},
      // 表单校验
      // 表单校验
      rules: {
        titleEn: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        titleEs: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        titleAr: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        reportUrlEn: [
          { required: true, message: "文件不能为空", trigger: "blur" }
        ],
        reportUrlEs: [
          { required: true, message: "文件不能为空", trigger: "blur" }
        ],
        reportUrlAr: [
          { required: true, message: "文件不能为空", trigger: "blur" }
        ],
        coverImageUrl: [
          { required: true, message: "封面图片不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询售后banner列表 */
    getList() {
      this.loading = true;
      listReport(this.queryParams).then(response => {
        this.reportList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        titleEn: null,
        titleEs: null,
        titleAr: null,
        reportUrlEn: '',
        reportUrlEs: '',
        reportUrlAr: '',
        coverImageUrl: '',
        lanuage: '',
        socialReportLangId: null,
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
      this.reset();
      this.open = true;
      this.title = "添加社会责任报告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ids = row.id || this.ids;
      getReport(ids).then(response => {
        const res = response.data
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { title, reportUrl, ...newObj } = obj
        res.forEach( data => {
          let lang = titleCase(data.language)
          newObj['title' + lang ] = data.title
          newObj['reportUrl' + lang ] = data.reportUrl
        })
        newObj.langData = response.data
        this.form = newObj
        this.open = true;
        this.title = "修改社会责任报告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateReport(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReport(formArray).then(response => {
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
      let { langData, titleEs, titleEn, titleAr, reportUrlEn, reportUrlEs, reportUrlAr, coverImageUrlEn, coverImageUrlEs, coverImageUrlAr, ...newObj } = this.form
      if(langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.title = this.form['title' + lang]
          obj.reportUrl = this.form['reportUrl' + lang]
          obj.coverImageUrl = this.form.coverImageUrl
          reqArray.push(obj)
        })
      } else {
        langData.forEach( (data, i) => {
          let lang = titleCase(data.language)
          langData[i].title = this.form['title' + lang]
          langData[i].reportUrl = this.form['reportUrl' + lang]
          langData[i].coverImageUrl = this.form.coverImageUrl
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除社会责任报告编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delReport(ids);
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
