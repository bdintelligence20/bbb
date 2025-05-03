<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['contact:country:add']"
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
          v-hasPermi="['contact:country:update']"
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
          v-hasPermi="['contact:country:delete']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="countryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="80" />
      <el-table-column label="国家经销商名称" align="center" prop="langs[0].name" />
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
            v-hasPermi="['contact:country:update']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['contact:country:delete']"
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

    <!-- 添加或修改经销商国家对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
        <el-form-item label="国家经销商区域" prop="areaId">
          <el-select v-model="form.areaId" placeholder="请选择国家经销商大洲" clearable size="small" width="100%">
            <el-option
              v-for="dict in areaOption"
              :key="dict.id"
              :value="dict.id"
              :label="dict.langs[0].name"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="国家经销商名称（英语）" prop="nameEn">
          <el-input v-model="form.nameEn" placeholder="请输入英文名称" />
        </el-form-item>
        <el-form-item label="国家经销商名称（西班牙）" prop="nameEs">
          <el-input v-model="form.nameEs" placeholder="请输入西班牙名称" />
        </el-form-item>
        <el-form-item label="国家经销商名称（阿拉伯）" prop="nameAr">
          <el-input v-model="form.nameAr" placeholder="请输入阿拉伯名称" />
        </el-form-item>
        <el-form-item label="国旗" prop="flagImgUrl">
          <imageUpload :limit="1" v-model="form.flagImgUrl"/>
        </el-form-item>
        <el-form-item label="Facebook">
          <el-input v-model="form.facebook" placeholder="请输入Facebook链接" />
        </el-form-item>
        <el-form-item label="Tiktok">
          <el-input v-model="form.tiktok" placeholder="请输入Tiktok链接" />
        </el-form-item>
        <el-form-item label="Instagram">
          <el-input v-model="form.instagram" placeholder="请输入Instagram链接" />
        </el-form-item>
        <el-form-item label="Twitter">
          <el-input v-model="form.twitter" placeholder="请输入Twitter链接" />
        </el-form-item>
        <el-form-item label="Youtube">
          <el-input v-model="form.youtube" placeholder="请输入Youtube链接" />
        </el-form-item>
        <el-form-item label="官网">
          <el-input v-model="form.site" placeholder="请输入官网链接" />
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
import { listCountry, getCountry, delCountry, addCountry, updateCountry, listArea } from "@/api/website/distributor";
import { titleCase } from '@/utils/index'
export default {
  name: "JobCategory",
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
      countryList: [],
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
      areaOption: [],
      // 表单参数
      form: {},
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
        flagImgUrl: [
          { required: true, message: "国旗图片不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList()
    this.getAreaList()
  },
  methods: {
    /** 查询售后banner列表 */
    getList() {
      this.loading = true;
      listCountry(this.queryParams).then(response => {
        this.countryList = response.data.rows;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    getAreaList() {
      listArea({type: 2}).then(res => {
        this.areaOption = res.data
      })
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
        countryId: null,
        nameEn: null,
        nameEs: null,
        nameAr: null,
        sort: 0,
        createTime: null,
        updateTime: null,
        langs: [],
        facebook: '',
        tiktok: '',
        instagram: '',
        youtube: '',
        twitter: '',
        site: '',
        flagImgUrl: '',
        areaId: null
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
      this.title = "添加国家经销商";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ids = row.id || this.ids;
      getCountry(ids).then(response => {
        const res = response.data
        console.log(res)
        let { langs, urls, ...newObj } = res
        langs.forEach( data => {
          let lang = titleCase(data.lang)
          newObj['name' + lang ] = data.name
        })
        response.data.urls.forEach( item => {
          newObj[item.name] = item.url
        })
        newObj.langs = response.data.langs
        this.form = newObj
        this.open = true;
        this.title = "修改国家经销商";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateCountry(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCountry(formArray).then(response => {
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
      let linksList = ['facebook', 'tiktok', 'instagram', 'youtube', 'twitter', 'site']
      let urls = []
      let { langs, countryId, facebook, tiktok, instagram, youtube, twitter, site, nameEs, nameEn, nameAr, ...newObj } = this.form
      if(langs.length < 1) {
        languageList.forEach(i => {
          let obj = {}, lang = titleCase(i)
          obj.lang = i
          obj.name = this.form['name' + lang]
          obj.createTime = ''
          obj.updateTime = ''
          obj.id = null
          obj.countryId = this.form.countryId
          langs.push(obj)
        })
        newObj.langs = langs
      } else {
        langs.forEach( (data, i) => {
          let lang = titleCase(data.lang)
          langs[i].name = this.form['name' + lang]
        })
        newObj.langs = langs
      }
      linksList.forEach(item => {
        let objItem = {}
        objItem.name = item
        objItem.url = this.form[item]
        objItem.sort = this.form.sort
        objItem.createTime = this.form.createTime
        objItem.updateTime = this.form.updateTime
        objItem.id = this.form.id
        objItem.countryId = this.form.countryId
        urls.push(objItem)
      })
      newObj.urls = urls
      return newObj
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除国家经销商编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delCountry(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      })
    }
  }
};
</script>
<style lang="scss" scoped>
::v-deep .el-select {
  width: 100%;
}
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
