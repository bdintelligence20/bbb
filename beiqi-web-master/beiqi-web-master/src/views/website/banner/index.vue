<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item
        label="标题"
        prop="title"
      >
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
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
        >搜索</el-button>
        <el-button
          icon="el-icon-refresh"
          size="mini"
          @click="resetQuery"
        >重置</el-button>
      </el-form-item>
    </el-form>

    <el-row
      :gutter="10"
      class="mb8"
    >
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
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['website:banner:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="bannerList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      />
      <el-table-column
        label="ID"
        align="center"
        prop="bannerId"
      />
      <el-table-column
        label="标题"
        align="center"
        prop="title"
      />
      <el-table-column
        prop="imageUrl"
        label="图片地址"
        align="center"
        width="150"
      >
        <template slot-scope="scope">
          <el-image
            :src="fileUploadHost + scope.row.imageUrl"
            @click="previewPicture(fileUploadHost + scope.row.imageUrl)"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="链接地址"
        align="center"
        prop="linkUrl"
      />
      <el-table-column
        label="排序"
        align="center"
        prop="sort"
      />
      <el-table-column
        label="Banner类型"
        align="center"
        prop="type"
      >
        <template slot-scope="scope">
          <span>
            {{ typeFormat(scope.row.type) }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="备注"
        align="center"
        prop="remark"
      />
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

    <!-- 添加或修改首页banner对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1100px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-position="top"
        label-width="150px"
      >
        <el-form-item
          label="类型"
          prop="type"
        >
          <el-radio-group v-model="form.type">
            <el-radio :label="0">首页</el-radio>
            <el-radio :label="1">新闻页</el-radio>
            <el-radio :label="5">新闻详情页</el-radio>
            <el-radio :label="6">BAIC In X</el-radio>
            <el-radio :label="3">Join us</el-radio>
            <el-radio :label="4">Multimedia</el-radio>
            <el-radio :label="2">其他</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item
              label="标题（英语）"
              prop="titleEn"
            >
              <el-input
                v-model="form.titleEn"
                placeholder="请输入标题（英语）"
              />
            </el-form-item>
            <el-form-item
              label="英语图片"
              prop="imageUrlEn"
            >
              <imageUpload
                :limit="1"
                v-model="form.imageUrlEn"
                :isBanner="true"
                @aspectRatio="getAspectRatio"
              />
            </el-form-item>
            <el-form-item
              label="图片描述（英语）"
              prop="describeEn"
            >
              <el-input
                v-model="form.describeEn"
                placeholder="图片描述（英语）"
              />
            </el-form-item>
            <el-form-item
              v-if="form.type === 0"
              label="上传视频（英语）"
              prop="videoUrlEn"
            >
              <VideoUpload
                v-model="form.videoUrlEn"
                :fileSize="30"
              ></VideoUpload>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item
              label="标题（西班牙）"
              prop="titleEs"
            >
              <el-input
                v-model="form.titleEs"
                placeholder="请输入标题（西班牙）"
              />
            </el-form-item>
            <el-form-item
              label="西班牙图片"
              prop="imageUrlEs"
            >
              <imageUpload
                :limit="1"
                v-model="form.imageUrlEs"
              />
            </el-form-item>
            <el-form-item
              label="图片描述（西班牙）"
              prop="describeEs"
            >
              <el-input
                v-model="form.describeEs"
                placeholder="图片描述（西班牙）"
              />
            </el-form-item>
            <el-form-item
              v-if="form.type === 0"
              label="上传视频（西班牙）"
              prop="videoUrlEs"
            >
              <VideoUpload
                v-model="form.videoUrlEs"
                :fileSize="30"
              ></VideoUpload>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item
              label="标题（阿拉伯）"
              prop="titleAr"
            >
              <el-input
                v-model="form.titleAr"
                placeholder="请输入标题（阿拉伯）"
              />
            </el-form-item>
            <el-form-item
              label="阿拉伯图片"
              prop="imageUrlAr"
            >
              <imageUpload
                :limit="1"
                v-model="form.imageUrlAr"
              />
            </el-form-item>
            <el-form-item
              label="图片描述（阿拉伯）"
              prop="describeAr"
            >
              <el-input
                v-model="form.describeAr"
                placeholder="图片描述（阿拉伯）"
              />
            </el-form-item>
            <el-form-item
              v-if="form.type === 0"
              label="上传视频（阿拉伯）"
              prop="videoUrlAr"
            >
              <VideoUpload
                v-model="form.videoUrlAr"
                :fileSize="30"
              ></VideoUpload>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item
          label="链接地址"
          prop="linkUrl"
        >
          <el-input
            v-model="form.linkUrl"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item
          label="备注"
          prop="remark"
        >
          <el-input
            v-model="form.remark"
            placeholder="请输入备注"
          />
        </el-form-item>
        <el-row type="flex">
          <el-col :span="8">
            <el-form-item
              label="显示排序"
              prop="sort"
            >
              <el-input-number
                v-model="form.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item
              label="显示状态"
              prop="enable"
            >
              <el-radio-group v-model="form.enable">
                <el-radio :label="1">显示</el-radio>
                <el-radio :label="2">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="submitForm"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBanner, getBanner, delBanner, addBanner, updateBanner, exportBanner } from "@/api/website/banner";
import { titleCase } from '@/utils/index'
export default {
  name: "Banner",
  components: {
  },
  data () {
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
      bannerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        imageUrl: null,
        linkUrl: null,
        sort: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      // 表单校验
      rules: {
        type: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ],
        imageUrlEn: [
          // { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrlEs: [
          // { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrlAr: [
          // { required: true, message: "图片不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created () {
    this.getList();
  },
  methods: {
    getAspectRatio (val) {
      const as = val.split('/')
      this.form.aspectRatio = (as[1] / as[0] * 100).toFixed(2)
    },
    /** 查询首页banner列表 */
    getList () {
      this.loading = true;
      listBanner(this.queryParams).then(response => {
        this.bannerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 分类处理
    typeFormat (type) {
      const typeOptions = [
        { label: '首页', type: 0 },
        { label: '新闻', type: 1 },
        { label: '新闻详情页', type: 5 },
        { label: 'BAIC In X', type: 6 },
        { label: '其他', type: 2 },
        { label: 'Join us', type: 3 },
        { label: 'Multimedia', type: 4 }
      ]
      let res = typeOptions.filter(i => {
        return i.type == type
      })
      return res[0].label
    },
    // 取消按钮
    cancel () {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset () {
      this.form = {
        bannerId: null,
        titleEn: null,
        titleEs: null,
        titleAr: null,
        imageUrlEn: null,
        imageUrlEs: null,
        imageUrlAr: null,
        describeEn: null,
        describeEs: null,
        describeAr: null,
        videoUrlEn: null,
        videoUrlEs: null,
        videoUrlAr: null,
        linkUrl: null,
        lanuage: '',
        type: 0,
        sort: 0,
        delFlag: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        remark: null,
        params: {},
        aspectRatio: null,
        langData: [],
        enable: null
      }
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map(item => item.bannerId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset();
      this.open = true;
      this.title = "添加首页banner";
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset();
      const bannerId = row.bannerId || this.ids;
      getBanner(bannerId).then(response => {
        const res = response.data
        this.reset()
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { imageUrl, title, describe, videoUrl, ...newObj } = obj
        // let obj = JSON.parse(JSON.stringify(newObj))
        res.forEach(data => {
          let lang = titleCase(data.language)
          newObj['imageUrl' + lang] = data.imageUrl
          newObj['title' + lang] = data.title
          newObj['describe' + lang] = data.describe
          newObj['videoUrl' + lang] = data.videoUrl
        })
        newObj.langData = response.data
        this.form = newObj
        this.open = true;
        this.title = "修改首页banner";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          console.log(formArray, 'formArray')
          if (this.form.bannerId != null) {
            updateBanner(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBanner(formArray).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    resolveFormData () {
      const languageList = ['en', 'es', 'ar']
      var reqArray = []
      let { langData, imageUrlEs, imageUrlEn, imageUrlAr, describeEs, describeEn, describeAr, titleEs, titleEn, titleAr, videoUrlEn, videoUrlEs, videoUrlAr, ...newObj } = this.form
      if (langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.imageUrl = this.form['imageUrl' + lang]
          obj.videoUrl = this.form['videoUrl' + lang]
          obj.title = this.form['title' + lang]
          obj.describe = this.form['describe' + lang]
          reqArray.push(obj)
        })
      } else {
        langData.forEach((data, i) => {
          let lang = titleCase(data.language)
          langData[i].imageUrl = this.form['imageUrl' + lang]
          langData[i].title = this.form['title' + lang]
          langData[i].describe = this.form['describe' + lang]
          langData[i].videoUrl = this.form['videoUrl' + lang]
          langData[i].linkUrl = this.form.linkUrl
          langData[i].enable = this.form.enable
          langData[i].remark = this.form.remark
          langData[i].sort = this.form.sort
          langData[i].type = this.form.type
          langData[i].aspectRatio = this.form.aspectRatio
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const bannerIds = row.bannerId || this.ids;
      this.$modal.confirm('是否确认删除首页banner编号为"' + bannerIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delBanner(bannerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport () {
      this.download('website/banner/export', {
        ...this.queryParams
      }, `banner_${new Date().getTime()}.xlsx`)
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
