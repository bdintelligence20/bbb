<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="icon 名称" prop="title">
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
          v-hasPermi="['system:item:add']"
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
          v-hasPermi="['system:item:edit']"
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
          v-hasPermi="['system:item:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="iconList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="80" />
      <el-table-column label="icon 名称" align="center" prop="name" />
      <el-table-column prop="imageUrl" label="icon 图片" align="center" width="150" >
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
            v-hasPermi="['system:item:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:item:remove']"
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

    <!-- 添加或修改icon对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="icon文案（英语）" prop="nameEn">
              <el-input v-model="form.nameEn" placeholder="请输入名称（英语）" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="icon文案（西班牙）" prop="nameEs">
              <el-input v-model="form.nameEs" placeholder="请输入名称（西班牙）" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="icon文案（阿拉伯）" prop="nameAr">
              <el-input v-model="form.nameAr" placeholder="请输入名称（阿拉伯）" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="icon图片（英语）" prop="imageUrlEn">
              <imageUpload :limit="1" v-model="form.imageUrlEn"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="icon图片（西班牙）" prop="imageUrlEs">
              <imageUpload :limit="1" v-model="form.imageUrlEs"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="icon图片（阿拉伯）" prop="imageUrlAr">
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
import { listIcon, getIcon, delIcon, addIcon, updateIcon, exportIcon } from "@/api/afterSale/icon";
import { titleCase } from '@/utils/index'
import { parseTime } from '@/utils/ruoyi'
export default {
  name: "Icon",
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
      // 首页icon表格数据
      iconList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        imageUrl: null,
        status: null,
        sort: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      // 表单校验
      rules: {
        nameEn: [
          { required: true, message: "icon name不能为空", trigger: "blur" }
        ],
        nameEs: [
          { required: true, message: "icon name不能为空", trigger: "blur" }
        ],
        nameAr: [
          { required: true, message: "icon name不能为空", trigger: "blur" }
        ],
        imageUrlEn: [
          { required: true, message: "icon 图片不能为空", trigger: "blur" }
        ],
        imageUrlEs: [
          { required: true, message: "icon 图片不能为空", trigger: "blur" }
        ],
        imageUrlAr: [
          { required: true, message: "icon 图片不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询icon列表 */
    getList() {
      this.loading = true;
      listIcon(this.queryParams).then(response => {
        this.iconList = response.rows;
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
        nameEn: null,
        nameEs: null,
        nameAr: null,
        imageUrlEn: null,
        imageUrlEs: null,
        imageUrlAr: null,
        saleItemLangId: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加售后页面icon";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const iconId = row.id || this.ids;
      getIcon(iconId).then(response => {
        const res = response.data
        this.reset()
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { name, ...newObj } = obj
        // let obj = JSON.parse(JSON.stringify(newObj))
        res.forEach( data => {
          let lang = titleCase(data.language)
          newObj['name' + lang ] = data.name
          newObj['imageUrl' + lang ] = data.imageUrl
        })
        newObj.langData = response.data
        this.form = newObj
        this.open = true;
        this.title = "修改售后页面icon";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateIcon(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addIcon(formArray).then(response => {
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
      let { langData, nameEs, nameEn, nameAr, imageUrlEn, imageUrlEs, imageUrlAr, ...newObj } = this.form
      if(langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.name = this.form['name' + lang]
          obj.imageUrl = this.form['imageUrl' + lang]
          reqArray.push(obj)
        })
      } else {
        langData.forEach( (data, i) => {
          let lang = titleCase(data.language)
          langData[i].name = this.form['name' + lang]
          langData[i].imageUrl = this.form['imageUrl' + lang]
          langData[i].sort = this.form.sort
          langData[i].status = this.form.status
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const iconIds = row.id || this.ids;
      this.$modal.confirm('是否确认删除icon编号为"' + iconIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delIcon(iconIds);
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
