<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="福利名称" prop="name">
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

    <el-table v-loading="loading" :data="welfareList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="80" />
      <el-table-column label="招聘福利" align="center" prop="name" />
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <span>
            {{ scope.row.status }}
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

    <!-- 添加或修改招聘福利对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
          <el-form-item label="招聘福利名称（英语）" prop="nameEn">
            <el-input v-model="form.nameEn" placeholder="请输入英文名称" />
          </el-form-item>
          <el-form-item label="招聘福利名称（西班牙）" prop="nameEs">
            <el-input v-model="form.nameEs" placeholder="请输入西班牙名称" />
          </el-form-item>                   
          <el-form-item label="招聘福利名称（阿拉伯）" prop="nameAr">
            <el-input v-model="form.nameAr" placeholder="请输入阿拉伯名称" />
          </el-form-item>
          <el-form-item label="福利icon图片" prop="imageUrl">
            <imageUpload :limit="1" v-model="form.imageUrl"/>
          </el-form-item>         
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
import { listWelfare, getWelfare, delWelfare, addWelfare, updateWelfare } from "@/api/joinUs/welfare";
import { titleCase } from '@/utils/index'
import { parseTime } from '@/utils/ruoyi'
export default {
  name: "Area",
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
      welfareList: [],
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
        nameEn: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        nameEs: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        nameAr: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "顺序不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
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
      listWelfare(this.queryParams).then(response => {
        this.welfareList = response.rows;
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
        imageUrl: '',
        sort: 0,
        status: 0,
        welfareLangId: null,
        lanuage: '',
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
      this.title = "添加招聘福利";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ids = row.id || this.ids;
      getWelfare(ids).then(response => {
        const res = response.data
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { name, ...newObj } = obj
        res.forEach( data => {
          let lang = titleCase(data.language)
          newObj['name' + lang ] = data.name
        })
        newObj.langData = response.data
        this.form = newObj
        // console.log(this.form,'this.form')
        this.open = true;
        this.title = "修改招聘福利";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateWelfare(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWelfare(formArray).then(response => {
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
      let { langData, nameEs, nameEn, nameAr, ...newObj } = this.form
      if(langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.name = this.form['name' + lang]
          obj.sort = this.form.sort
          obj.status = this.form.status
          obj.imageUrl = this.form.imageUrl
          reqArray.push(obj)
        })
      } else {
        langData.forEach( (data, i) => {
          let lang = titleCase(data.language)
          langData[i].name = this.form['name' + lang]
          langData[i].sort = this.form.sort
          langData[i].status = this.form.status
          langData[i].imageUrl = this.form.imageUrl
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除招聘福利编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delWelfare(ids);
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
