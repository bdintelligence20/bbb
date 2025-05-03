<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:history:add']"
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
          v-hasPermi="['system:history:edit']"
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
          v-hasPermi="['system:history:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="historyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="100" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column prop="imageUrl" label="图片地址" align="center" width="150">
        <template slot-scope="scope">
          <el-image :src="fileUploadHost + scope.row.imageUrl"  @click="previewPicture(fileUploadHost + scope.row.imageUrl)" />
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:history:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:history:remove']"
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

    <!-- 添加或修改历史内容对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="标题（英语）" prop="titleEn">
              <el-input v-model="form.titleEn" placeholder="请输入标题（英语）" />
            </el-form-item>
            <el-form-item label="图片（英语）" prop="imageUrlEn">
              <imageUpload :limit="1" v-model="form.imageUrlEn"/>
            </el-form-item>
            <el-form-item label="图片描述（英语）" prop="describeEn">
              <el-input type="textarea" v-model="form.describeEn" placeholder="请输入图片描述（英语）" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题（西班牙）" prop="titleEs">
              <el-input v-model="form.titleEs" placeholder="请输入标题（西班牙）" />
            </el-form-item>
            <el-form-item label="图片（西班牙）" prop="imageUrlEs">
              <imageUpload :limit="1" v-model="form.imageUrlEs"/>
            </el-form-item>
            <el-form-item label="图片描述（西班牙）" prop="describeEs">
              <el-input type="textarea" v-model="form.describeEs" placeholder="请输入图片描述（西班牙）" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题（阿拉伯）" prop="titleAr">
              <el-input v-model="form.titleAr" placeholder="请输入标题（阿拉伯）" />
            </el-form-item>
            <el-form-item label="图片（阿拉伯）" prop="imageUrlAr">
              <imageUpload :limit="1" v-model="form.imageUrlAr"/>
            </el-form-item>
            <el-form-item label="图片描述（阿拉伯）" prop="describeAr">
              <el-input type="textarea" v-model="form.describeAr" placeholder="请输入图片描述（阿拉伯）" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="6">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker v-model="form.startTime" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker v-model="form.endTime" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="6">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number style="width: 100%"  v-model="form.sort" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
        <el-col :span="8">
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listHistory, getHistory, delHistory, addHistory, updateHistory } from "@/api/content/history";
import { titleCase } from '@/utils/index'
export default {
  name: "History",
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
      historyList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null
      },
      // 表单参数
      form: {},
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
        imageUrlEn: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrlEs: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrlAr: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "开始日期不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "显示排序不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询历史列表 */
    getList() {
      this.loading = true;
      listHistory(this.queryParams).then(response => {
        this.historyList = response.rows;
        this.total = response.total;
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
        historyId: null,
        titleEn: null,
        titleEs: null,
        titleAr: null,
        imageUrlEn: null,
        imageUrlEs: null,
        imageUrlAr: null,
        describeEn: null,
        describeEs: null,
        describeAr: null,
        startTime: '',
        endTime: '',
        id: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        remark: null,
        params: {},
        sort: 0,
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
      this.title = "添加历史内容";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const historyId = row.id || this.ids;
      getHistory(historyId).then(response => {
        const res = response.data
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { imageUrl, title, describe, ...newObj } = obj
        // let obj = JSON.parse(JSON.stringify(newObj))
        res.forEach( data => {
          let lang = titleCase(data.language)
          newObj['imageUrl' + lang ] = data.imageUrl
          newObj['title' + lang ] = data.title
          newObj['describe' + lang ] = data.describe
        })
        newObj.langData = response.data
        this.form = newObj
        this.open = true;
        this.title = "修改历史内容";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.historyId != null) {
            updateHistory(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHistory(formArray).then(response => {
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
      let { langData, imageUrlEs, imageUrlEn, imageUrlAr, describeEs, describeEn, describeAr, titleEs, titleEn, titleAr, ...newObj } = this.form
      if(langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.imageUrl = this.form['imageUrl' + lang]
          obj.title = this.form['title' + lang]
          obj.describe = this.form['describe' + lang]
          reqArray.push(obj)
        })
      } else {
        langData.forEach( (data, i) => {
          let lang = titleCase(data.language)
          langData[i].imageUrl = this.form['imageUrl' + lang]
          langData[i].title = this.form['title' + lang]
          langData[i].describe = this.form['describe' + lang]
          langData[i].startTime = this.form.startTime
          langData[i].endTime = this.form.endTime
          langData[i].remark = this.form.remark
          langData[i].sort = this.form.sort
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const historyIds = row.id || this.ids;
      this.$modal.confirm('是否确认删除历史编号为"' + historyIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delHistory(historyIds);
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
