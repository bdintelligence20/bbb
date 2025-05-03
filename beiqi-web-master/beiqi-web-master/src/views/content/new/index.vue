<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入文章标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章简介" prop="summary">
        <el-input
          v-model="queryParams.summary"
          placeholder="请输入文章简介"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章分类" prop="categoryId">
        <el-cascader v-model="queryParams.categoryId"
          :options="categoryOptions"
          :props="{
            checkStrictly: true,
            multiple: false,
            value: 'id',
            label: 'name',
            children: 'children',
            emitPath: false
          }"
          placeholder="请选择文章分类"
          clearable></el-cascader>
      </el-form-item>
      <el-form-item label="状态" prop="isPublish">
        <el-select v-model="queryParams.status" placeholder="请选择状态" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_publish_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="推荐" prop="recommend">
        <el-select v-model="queryParams.recommend" placeholder="请选择是否推荐" @change="handleQuery" clearable size="small">
          <el-option key="0" value="0" label="否"/>
          <el-option key="1" value="1" label="是"/>
        </el-select>
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
          v-hasPermi="['system:news:add']"
        >新增</el-button>
      </el-col>
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAddNewVersion"
          v-hasPermi="['system:news:add']"
        >新增(新版)</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:news:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdateNewVersion"
          v-hasPermi="['system:news:edit']"
        >修改(新版)</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:news:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['blog:article:export']"
        >导出Excel</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table ref="tables" v-loading="loading" :data="articleList" :default-sort="defaultSort"  @sort-change="handleSortChange"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" width="100" />
      <el-table-column label="标题图" align="center" prop="imageUrl" width="120">
        <template slot-scope="scope">
          <el-image :src="fileUploadHost + scope.row.imageUrl" lazy @click="previewPicture(fileUploadHost + scope.row.imageUrl)" />
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="新闻分类" align="center"  prop="categoryId">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.categoryId">
            {{ categoryFormat(scope.row.categoryId) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" width="100" />
      <el-table-column label="发布状态" align="center" prop="isPublish">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_publish_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="是否推荐位" align="center" prop="recommend">
        <template slot-scope="scope">
          <span>{{ scope.row.recommend ? '是' : '否'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" sortable="custom" :sort-orders="['descending', 'ascending']" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" align="center" prop="publishTime" sortable="custom" :sort-orders="['descending', 'ascending']" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:news:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateNewVersion(scope.row)"
            v-hasPermi="['system:news:edit']"
          >修改(新版)</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:news:remove']"
          >删除</el-button>
          <!-- <el-button
            type="text"
            size="small"
            icon="el-icon-view"
            @click="previewArticle(scope.row)"
          >预览</el-button> -->
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

    <!-- 添加或修改博客文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" fullscreen append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="200px">
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="文章标题（英语）" prop="titleen">
              <el-input v-model="form.titleen" placeholder="请输入博客标题"/>
            </el-form-item>
            <el-form-item label="文章简介（英语）" prop="summarizeen">
              <el-input v-model="form.summarizeen" placeholder="请输入文章简介"/>
            </el-form-item>
            <el-form-item label-width="200px" label="标题图片（英国）" prop="imageUrlen">
              <imageUpload :limit="1" v-model="form.imageUrlen"/>
            </el-form-item>
            <el-form-item label="文章内容（英语）" prop="contenten">
              <el-input v-if="hideEditor(form.contenten)" v-model="form.contenten" type="textarea" :rows="18" />
              <editor v-else ref="editor" v-model="form.contenten" :min-height="192" :height="360" />
            </el-form-item>
            <el-form-item label="显示状态" prop="enableen">
              <el-radio-group v-model="form.enableen">
                <el-radio label="1">显示</el-radio>
                <el-radio label="0">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="文章标题（西班牙语）" prop="titlees">
              <el-input v-model="form.titlees" placeholder="请输入博客标题"/>
            </el-form-item>
            <el-form-item label="文章简介（西班牙语）" prop="summarizees">
              <el-input v-model="form.summarizees" placeholder="请输入文章简介"/>
            </el-form-item>
            <el-form-item label-width="200px" label="标题图片（西班牙）" prop="imageUrles">
              <imageUpload :limit="1" v-model="form.imageUrles"/>
            </el-form-item>
            <el-form-item label="文章内容（西班牙语）" prop="contentes">
              <el-input v-if="hideEditor(form.contentes)" v-model="form.contentes" type="textarea" :rows="18" />
              <editor v-else ref="editor" v-model="form.contentes" :min-height="192" :height="360" />
            </el-form-item>
            <el-form-item label="显示状态" prop="enablees">
              <el-radio-group v-model="form.enablees">
                <el-radio label="1">显示</el-radio>
                <el-radio label="0">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="文章标题（阿拉伯语语）" prop="titlear">
              <el-input v-model="form.titlear" placeholder="请输入博客标题"/>
            </el-form-item>
            <el-form-item label="文章简介（阿拉伯语语）" prop="summarizear">
              <el-input v-model="form.summarizear" placeholder="请输入文章简介"/>
            </el-form-item>
            <el-form-item label-width="200px" label="标题图片（阿拉伯）" prop="imageUrlar">
              <imageUpload :limit="1" v-model="form.imageUrlar"/>
            </el-form-item>
            <el-form-item label="文章内容（阿拉伯语）" prop="contentar">
              <el-input v-if="hideEditor(form.contentar)" v-model="form.contentar" type="textarea" :rows="18" />
              <editor v-else class="editor-ar" ref="editor" v-model="form.contentar" :min-height="192" :height="360" />
            </el-form-item>
            <el-form-item label="显示状态" prop="enablear">
              <el-radio-group v-model="form.enablear">
                <el-radio label="1">显示</el-radio>
                <el-radio label="0">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-row :gutter="40" type="flex">
              <el-col :span="4">
                <el-form-item label="发布时间" prop="publishTime">
                  <el-date-picker v-model="form.publishTime" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="分类" prop="categoryId">
                  <el-cascader v-model="form.categoryId"
                  :options="categoryOptions"
                  :props="{
                    value: 'id',
                    label: 'name',
                    children: 'children',
                    emitPath: false
                  }"
                  placeholder="请选择文章分类"
                  clearable></el-cascader>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="发布状态" prop="status">
                  <el-radio-group v-model="form.status">
                    <el-radio
                      v-for="dict in dict.type.article_publish_status"
                      :key="dict.value"
                      :label="parseInt(dict.value)"
                    >{{dict.label}}</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="4">
                <el-form-item label="是否推荐位" prop="recommend">
                  <el-radio-group v-model="form.recommend">
                    <el-radio :label="0">否</el-radio>
                    <el-radio :label="1">是</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="4" v-if="form.recommend">
                <el-form-item label="显示排序" prop="sort">
                  <el-input-number style="width: 100%"  v-model="form.sort" controls-position="right" :min="0" />
                </el-form-item>
              </el-col>
            </el-row>
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
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from "@/api/blog/new";
import { treeselect } from "@/api/website/navigation";
import { getToken } from "@/utils/auth";
export default {
  name: "Article",
  dicts: [ 'article_publish_status' ],
  data() {
    return {
      changeCount: 0, //文章编辑次数
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
      // 博客文章表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 标签字典
      tagOptions: [],
      //分类字典
      categoryOptions: [],
      // 自定义
      imageUploader: {
        custom: false,
        fileType: 'file',
        fileNameType: '',
        imagePrefix: this.fileUploadHost, // 图片上传成功后，预览地址前缀
        type: 'server',
        url: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pageType: 0,
        title: null,
        summarize: null,
        status: null,
        categoryId: null
      },
      // 默认排序
      defaultSort: {prop: 'updateTime', order: 'descending'},
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        titleen: [
          { required: true, message: "文章标题不能为空", trigger: "blur" }
        ],
        titlees: [
          { required: true, message: "文章标题不能为空", trigger: "blur" }
        ],
        titlear: [
          { required: true, message: "文章标题不能为空", trigger: "blur" }
        ],
        summarizeen: [
          { required: true, message: "文章简介不能为空", trigger: "blur" }
        ],
        summarizees: [
          { required: true, message: "文章简介不能为空", trigger: "blur" }
        ],
        summarizear: [
          { required: true, message: "文章简介不能为空", trigger: "blur" }
        ],
        imageUrlen: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrles: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        imageUrlar: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        contenten: [
          { required: true, message: "文章内容不能为空", trigger: "blur" }
        ],
        contentes: [
          { required: true, message: "文章内容不能为空", trigger: "blur" }
        ],
        contentar: [
          { required: true, message: "文章内容不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        publishTime: [
          { required: true, message: "请选择日期", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序字段不能为空", trigger: "blur" }
        ],
        categoryId: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ],
        recommend: [
          { required: true, message: "推荐位不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    // 查询列表
    this.getList();
    // 获取新闻类型
    this.getNewsType();
  },
  methods: {
    /** 查询博客文章列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //标签翻译
    getTagArray(tagId) {
      if (!tagId) {
        return []
      }
      return tagId.split(',');
    },
    //分类翻译
    categoryFormat(categoryId) {
      const id = categoryId
      const tree =  this.categoryOptions;
      let res = ""
      let state = false
      function readTree(tree, id) {
        if(state)return
        for (let i = 0; i < tree.length; i++) {
          if (tree[i].id == id) {
            state  = true
            res = tree[i]
          } else {
            if (tree[i].children != null) {
              readTree(tree[i].children, id)
            }
          }
        }
      }
      readTree(tree,id)
      return res.name
    },
    previewArticle(row) {
      if(row.isPublish == 0) {
        this.$modal.msgError("文章暂未发布，无法进行浏览")
        return;
      }
      window.open( process.env.VUE_APP_WEB_HOST + '/article/' + row.articleId);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.changeCount = 0;
      this.form = {
        id: null,
        titleen: null,
        titlees: null,
        titlear: null,
        summarizeen: null,
        summarizees: null,
        summarizear: null,
        contenten: null,
        contentes: null,
        contentar: null,
        imageUrlen: null,
        imageUrles: null,
        imageUrlar: null,
        categoryId: null,
        sort: null,
        status: 1,
        remark: null,
        params: {},
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
        recommend: 0,
        publishTime: '',
        newsLangs: [],
        enableen: '1',
        enablees: '1',
        enablear: '1',
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
      this.$refs.tables.sort(this.defaultSort.prop, this.defaultSort.order)
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 排序触发事件 */
    handleSortChange(column, prop, order) {
      this.queryParams.orderByColumn = column.prop;
      this.queryParams.isAsc = column.order;
      this.getList();
    },
    /** 新闻类型获取 */
    getNewsType() {
      let query = {
        menuType: '2'
      }
      treeselect(query).then(response => {
        this.categoryOptions = this.getTreeData(response.data)
      });
    },
    // 递归判断列表，把最后的children设为null
    getTreeData(data) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].children.length < 1) {
                // children若为空数组，则将 children 设为null
                data[i].children = null;
            } else {
                // children若不为空数组，则继续 递归调用 本方法
                this.getTreeData(data[i].children);
            }
        }
        return data;
    },
    resolveFormData() {
      const languageList = ['en', 'es', 'ar']
      let newsLangs = []
      if(this.form.newsLangs.length < 1) {
        let obj = {
          createBy: '',
          createTime: '',
          id: null,
          params: {},
          remark: '',
          updateBy: '',
          updateTime: '',
        }
        languageList.forEach( lang => {
          let newObj = JSON.parse(JSON.stringify(obj))
          newObj.language = lang
          newObj.title = this.form['title' + lang]
          newObj.summarize = this.form['summarize' + lang]
          newObj.imageUrl = this.form['imageUrl' + lang]
          newObj.content = this.form['content' + lang]
          newObj.enable = this.form['enable' + lang]
          newsLangs.push(newObj)
        })

      } else {
        newsLangs = this.form.newsLangs
        newsLangs.forEach( (langData, index) => {
          newsLangs[index].title = this.form['title' + langData.language]
          newsLangs[index].summarize = this.form['summarize' + langData.language]
          newsLangs[index].imageUrl = this.form['imageUrl' + langData.language]
          newsLangs[index].content = this.form['content' + langData.language]
          newsLangs[index].enable = this.form['enable' + langData.language]
        })
      }
      let { titlees, titleen, titlear, summarizees, summarizeen, summarizear, imageUrles, imageUrlen, imageUrlar, contentes, contenten, contentar, enableen, enablees, enablear, ...req } = this.form
      req.newsLangs = newsLangs
      // req.categoryId = categoryId[categoryId.length - 1]
      return req
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.title = "添加新闻文章";
      this.reset();
      this.open = true;
    },
    /** 新增-新版本 按钮操作 */
    handleAddNewVersion() {
      const url = '/manage-panel-path/editor/'
      const w = window.innerWidth * 0.98
      const h = window.innerHeight * 0.99
      const t = window.innerWidth * 0.01
      const l = window.innerHeight * 0.01
      const childWindow = window.open(url, '可视化新闻编辑器', `width=${w},height=${h},top=50,left=${l},popup`);
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      const loading = this.$loading({
        lock: true,
        text: '正在加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      this.reset();
      const articleId = row.id || this.ids
      getArticle(articleId).then(response => {
        loading.close();
        // 数据处理;
        let newObj = response.data
        let newsLangs = response.data.newsLangs
        newsLangs.map( data => {
          newObj['imageUrl' + data.language] = data.imageUrl
          newObj['content' + data.language] = data.content
          newObj['title' + data.language] = data.title
          newObj['summarize' + data.language] = data.summarize
          newObj['enable' + data.language] = data.enable + ''
        })
        this.form = newObj
        this.open = true
        this.title = "修改新闻文章"
      });
    },

    /** 修改-新版本 按钮操作 */
    handleUpdateNewVersion(row) {
      const url = '/manage-panel-path/editor/?id=' + (row.id || this.ids)
      const w = window.innerWidth * 0.98
      const h = window.innerHeight * 0.99
      const t = window.innerWidth * 0.01
      const l = window.innerHeight * 0.01
      const childWindow = window.open(url, '可视化新闻编辑器', `width=${w},height=${h},top=50,left=${l},popup`);
    },
    /** 提交按钮 */
    submitForm() {
      let that = this;
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formData = this.resolveFormData()
          // console.log(formData, 'formData')
          if (this.form.id != null) {
            updateArticle(formData).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(formData).then(response => {
              localStorage.removeItem("articleForm");
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
      const articleIds = row.id || this.ids;
      this.$modal.confirm('是否确认删除新闻文章编号为"' + articleIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delArticle(articleIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    // handleExport() {
    //     this.download('blog/article/export', {
    //     ...this.queryParams
    //     }, `article_${new Date().getTime()}.xlsx`)
    // },
    // 自定义图片上传
    // uploadImg: function ($vm, file, fileName) {
    //   console.log($vm)
    //   console.log(file)
    //   console.log(fileName)
    //   // 添加图片
    //   // 两个参数 第一个是图片访问路径 第二个是文件名
    //   $vm.insertImg(`${$vm.config.imageUploader.imagePrefix}${fileName}`, fileName)
    // },
    save: function (val) {
      // 获取预览文本
      this.form.content = val
    },
    // 是否隐藏editor
    hideEditor(content) {
      if (content) {
        try {
          JSON.parse(content)
          return true
        } catch (e) {
          console.log(e)
          return false
        }
      }
    }
  }
};
</script>
<style scoped>
  .tipBox {
    margin-bottom: 30px;
  }

  #editor-main {
    color: #2c3e50;
    width: 100%;
    height: 600px;
  }
</style>
