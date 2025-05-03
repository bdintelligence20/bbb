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

    <el-table v-loading="loading" :data="contentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" width="80px" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <span>
            {{ typeOptions[scope.row.type] }}
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

    <!-- 添加或修改首页内容 -->
    <el-dialog :title="title" :visible.sync="open" fullscreen append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
        <el-form-item label="版块位置" prop="type">
          <el-radio-group v-model="form.type">
            <el-radio :label="0">视频位</el-radio>
            <el-radio :label="1">Overview</el-radio>
            <el-radio :label="2">Design</el-radio>
            <el-radio :label="3">下载中心</el-radio>
            <el-radio :label="4">Vision</el-radio>
            <el-radio :label="5">经销商中心</el-radio>
            <el-radio :label="6">研究和开发</el-radio>
            <el-radio :label="7">Vehicle</el-radio>
            <el-radio :label="8">活动</el-radio>
            <el-radio :label="9">关于</el-radio>
            <el-radio :label="10">更多</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="标题（英语）" prop="titleEn">
              <el-input v-model="form.titleEn" placeholder="请输入标题（英语）" />
            </el-form-item>
            <el-form-item v-if="form.type === 0" label="上传视频（英语）" prop="videoUrlEn">
              <VideoUpload v-model="form.videoUrlEn" :fileSize="30"></VideoUpload>
            </el-form-item>
            <el-form-item v-else  label="图片（英语）" prop="imageUrlEn">
              <imageUpload :limit="1" v-model="form.imageUrlEn" :isBanner="true" @aspectRatio="getAspectRatio"/>
            </el-form-item>
            <el-form-item label="图片描述（英语）" prop="describeEn">
              <el-input v-model="form.describeEn" placeholder="图片描述（英语）" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题（西班牙）" prop="titleEs">
              <el-input v-model="form.titleEs" placeholder="请输入标题（西班牙）" />
            </el-form-item>
            <el-form-item v-if="form.type === 0" label="上传视频（西班牙）" prop="videoUrlEs">
              <VideoUpload v-model="form.videoUrlEs" :fileSize="30"></VideoUpload>
            </el-form-item>
            <el-form-item v-else label="图片（西班牙）" prop="imageUrlEs">
              <imageUpload :limit="1" v-model="form.imageUrlEs"/>
            </el-form-item>
            <el-form-item label="图片描述（西班牙）" prop="describeEs">
              <el-input v-model="form.describeEs" placeholder="图片描述（西班牙）" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题（阿拉伯）" prop="titleAr">
              <el-input v-model="form.titleAr" placeholder="请输入标题（阿拉伯）" />
            </el-form-item>
            <el-form-item v-if="form.type === 0" label="上传视频（阿拉伯）" prop="videoUrlAr">
              <VideoUpload v-model="form.videoUrlAr" :fileSize="30"></VideoUpload>
            </el-form-item>
            <el-form-item v-else label="图片（阿拉伯）" prop="imageUrlAr">
              <imageUpload :limit="1" v-model="form.imageUrlAr"/>
            </el-form-item>
            <el-form-item label="图片描述（阿拉伯）" prop="describeAr">
              <el-input v-model="form.describeAr" placeholder="图片描述（阿拉伯）" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="链接地址" prop="linkUrl">
          <el-input v-model="form.linkUrl" type="textarea" placeholder="请输入内容" />
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
import { listSection, getSection, getSectionDetail, delSection, addSection, updateSection } from "@/api/home";
import { titleCase } from '@/utils/index'
import { parseTime } from '@/utils/ruoyi'
import { getToken } from "@/utils/auth";

export default {
  name: "Home",
  components: {
  },
  data() {
    return {
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      uploadVideoUrl: null,
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
      // 社会责任内容表格数据
      contentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: null
      },
      typeOptions: {},
      // 表单参数
      form: {},
      // 表单校验
      // 表单校验
      rules: { }
    };
  },
  created() {
    this.uploadVideoUrl =process.env.VUE_APP_BASE_API + '/media/movie/upload/video';
    this.getType()
    this.getList();
  },
  methods: {
     getAspectRatio(val) {
      const as = val.split('/')
      this.form.aspectRatio = (as[1] / as[0] * 100).toFixed(2)
    },
    /** 查询售后banner列表 */
    getList() {
      this.loading = true;
      listSection(this.queryParams).then(response => {
        this.contentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });

    },
    getType() {
      getSection().then(response => {
        this.typeOptions = response.data;
      })
    },
    // 分类处理
    // typeFormat(type) {
    //   let res = this.typeOptions.filter( i => {
    //     return i.type == type
    //   })
    //   return res[0].label
    // },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        indexLangId: null,
        titleEn: null,
        titleEs: null,
        titleAr: null,
        describeEn: null,
        describeEs: null,
        describeAr: null,
        imageUrlEn: null,
        imageUrlEs: null,
        imageUrlAr: null,
        type: 0,
        linkUrl: '',
        lanuage: '',
        id: null,
        createTime: null,
        aspectRatio: '',
        videoUrlEn: null,
        videoUrlEs: null,
        videoUrlAr: null,
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
      this.title = "添加首页内容";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      // console.log(this.form, 'form')
      const ids = row.id || this.ids;
      getSectionDetail(ids).then(response => {
        const res = response.data
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { imageUrl, videoUrl, title, describe, ...newObj } = obj
        // let obj = JSON.parse(JSON.stringify(newObj))
        res.forEach( data => {
          let lang = titleCase(data.language)
          newObj['imageUrl' + lang ] = data.imageUrl
          newObj['title' + lang ] = data.title
          newObj['describe' + lang ] = data.describe
          newObj['videoUrl' + lang ] = data.videoUrl
        })
        newObj.langData = response.data
        this.form = newObj
        this.open = true;
        this.title = "修改首页内容";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateSection(formArray).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSection(formArray).then(response => {
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
      let { langData, videoUrlEn, videoUrlEs, videoUrlAr, imageUrlEs, imageUrlEn, imageUrlAr, describeEs, describeEn, describeAr, titleEs, titleEn, titleAr, ...newObj } = this.form
      if(langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          if(this.form.type === 0){
            obj.imageUrl = ''
            obj.videoUrl = this.form['videoUrl' + lang]
          } else {
            obj.videoUrl = ''
            obj.imageUrl = this.form['imageUrl' + lang]
          }
          obj.title = this.form['title' + lang]
          obj.describe = this.form['describe' + lang]
          reqArray.push(obj)
        })
      } else {
        langData.forEach( (data, i) => {
          let lang = titleCase(data.language)
          if(this.form.type === 0){
            langData[i].imageUrl = ''
            langData[i].videoUrl = this.form['videoUrl' + lang]
          } else {
            langData[i].videoUrl = ''
            langData[i].imageUrl = this.form['imageUrl' + lang]
          }
          langData[i].title = this.form['title' + lang]
          langData[i].describe = this.form['describe' + lang]
          langData[i].linkUrl = this.form.linkUrl
          langData[i].type = this.form.type
          langData[i].aspectRatio = this.form.aspectRatio
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除首页内容编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSection(ids);
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
