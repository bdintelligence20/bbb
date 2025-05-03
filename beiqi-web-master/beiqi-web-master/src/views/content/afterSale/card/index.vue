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
          v-hasPermi="['system:intro:add']"
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
          v-hasPermi="['system:intro:edit']"
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
          v-hasPermi="['system:intro:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="subTitle" />
      <el-table-column prop="imageUrl" label="图片地址" align="center" width="150"  >
        <template slot-scope="scope">
          <el-image :src="fileUploadHost + scope.row.imageUrl"  @click="previewPicture(fileUploadHost + scope.row.imageUrl)" />
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span>
            {{ typeFormat(scope.row.status) }}
          </span>
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
            v-hasPermi="['system:intro:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:intro:remove']"
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

    <!-- 添加或修改card对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
        <el-row :gutter="40">
          <el-col :span="7">
            <el-form-item label="标题（英语）" prop="titleEn">
              <el-input v-model="form.titleEn" placeholder="请输入标题（英语）" />
            </el-form-item>
            <el-form-item label="标题描述（英语）" prop="titleDescribeEn">
              <el-input type="textarea" v-model="form.titleDescribeEn" placeholder="请输入标题描述（英语）" />
            </el-form-item>
            <el-form-item label="图片标题（英语）" prop="subTitleEn">
              <el-input v-model="form.subTitleEn" placeholder="请输入图片标题（英语）" />
            </el-form-item>
            <el-form-item label="图片描述（英语）" prop="subTitleDescribeEn">
              <el-input type="textarea" v-model="form.subTitleDescribeEn" placeholder="请输入图片描述（英语）" maxlength="200" show-word-limit/>
            </el-form-item>
            <el-form-item label="英语图片" prop="imageUrlEn">
              <imageUpload :limit="1" v-model="form.imageUrlEn"/>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="标题（西班牙语）" prop="titleEs">
              <el-input v-model="form.titleEs" placeholder="请输入标题（西班牙语）" />
            </el-form-item>
            <el-form-item label="标题描述（西班牙语）" prop="titleDescribeEs">
              <el-input type="textarea" v-model="form.titleDescribeEs" placeholder="请输入标题描述（西班牙语）" />
            </el-form-item>
            <el-form-item label="图片标题（西班牙语）" prop="subTitleEs">
              <el-input v-model="form.subTitleEs" placeholder="请输入图片标题（西班牙语）" />
            </el-form-item>
            <el-form-item label="图片描述（西班牙语）" prop="subTitleDescribeEs">
              <el-input type="textarea" v-model="form.subTitleDescribeEs" placeholder="请输入图片描述（西班牙语）" maxlength="200" show-word-limit/>
            </el-form-item>
            <el-form-item label="西班牙图片" prop="imageUrlEs">
              <imageUpload :limit="1" v-model="form.imageUrlEs"/>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="标题（阿拉伯语）" prop="titleAr">
              <el-input v-model="form.titleAr" placeholder="请输入标题（阿拉伯语）" />
            </el-form-item>
            <el-form-item label="标题描述（阿拉伯语）" prop="titleDescribeAr">
              <el-input type="textarea" v-model="form.titleDescribeAr" placeholder="请输入标题描述（阿拉伯语）" />
            </el-form-item>
            <el-form-item label="图片标题（阿拉伯语）" prop="subTitleAr">
              <el-input v-model="form.subTitleAr" placeholder="请输入图片标题（阿拉伯语）" />
            </el-form-item>
            <el-form-item label="图片描述（阿拉伯语）" prop="subTitleDescribeAr">
              <el-input type="textarea" v-model="form.subTitleDescribeAr" placeholder="请输入图片描述（阿拉伯语）" maxlength="200" show-word-limit/>
            </el-form-item>
            <el-form-item label="阿拉伯图片" prop="imageUrlAr">
              <imageUpload :limit="1" v-model="form.imageUrlAr"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="0">显示</el-radio>
            <el-radio :label="1">隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="显示排序" prop="sort">
          <el-input-number v-model="form.sort" controls-position="right" :min="0" />
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
import { listCard, getCard, delCard, addCard, updateCard } from "@/api/afterSale/card";
import { titleCase } from '@/utils/index'
import { parseTime } from '@/utils/ruoyi'
export default {
  name: "Card",
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
      // card表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        subTitle: null,
        imageUrl: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      // 表单校验
      rules: {
        imageUrlEn: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrlEs: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrlAr: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询card列表 */
    getList() {
      this.loading = true;
      listCard(this.queryParams).then(response => {
        this.cardList = response.rows;
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
        saleIntroLangId: null,
        titleEn: null,
        titleEs: null,
        titleAr: null,
        titleDescribeEn: null,
        titleDescribeEs: null,
        titleDescribeAr: null,
        subTitleEn: null,
        subTitleEs: null,
        subTitleAr: null,
        subTitleDescribeEn: null,
        subTitleDescribeEs: null,
        subTitleDescribeAr: null,
        imageUrlEn: null,
        imageUrlEs: null,
        imageUrlAr: null,
        lanuage: '',
        status: 0,
        sort: 0,
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
      this.ids = selection.map(item => item.bannerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加售后card内容";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ids = row.id || this.ids;
      getCard(ids).then(response => {
        const res = response.data
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { imageUrl, title, titleDescribe, subTitle, subTitleDescribe, ...newObj } = obj
        // let obj = JSON.parse(JSON.stringify(newObj))
        res.forEach( data => {
          let lang = titleCase(data.language)
          newObj['imageUrl' + lang ] = data.imageUrl
          newObj['title' + lang ] = data.title
          newObj['titleDescribe' + lang ] = data.titleDescribe
          newObj['subTitle' + lang ] = data.subTitle
          newObj['subTitleDescribe' + lang ] = data.subTitleDescribe
        })
        newObj.langData = response.data
        this.form = newObj
        this.open = true;
        this.title = "修改售后card内容";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateCard(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCard(formArray).then(response => {
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
      let { langData, imageUrlEs, imageUrlEn, imageUrlAr, titleDescribeEs, titleDescribeEn, titleDescribeAr, titleEs, titleEn, titleAr, subTitleEs, subTitleEn, subTitleAr, subTitleDescribeEs, subTitleDescribeEn, subTitleDescribeAr, ...newObj } = this.form
      if(langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.imageUrl = this.form['imageUrl' + lang]
          obj.title = this.form['title' + lang]
          obj.titleDescribe = this.form['titleDescribe' + lang]
          obj.subTitle = this.form['subTitle' + lang]
          obj.subTitleDescribe = this.form['subTitleDescribe' + lang]
          reqArray.push(obj)
        })
      } else {
        langData.forEach( (data, i) => {
          let lang = titleCase(data.language)
          langData[i].imageUrl = this.form['imageUrl' + lang]
          langData[i].title = this.form['title' + lang]
          langData[i].titleDescribe = this.form['titleDescribe' + lang]
          langData[i].subTitle = this.form['subTitle' + lang]
          langData[i].subTitleDescribe = this.form['subTitleDescribe' + lang]
          langData[i].sort = this.form.sort
          langData[i].status = this.form.status
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除card编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCard(ids);
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
