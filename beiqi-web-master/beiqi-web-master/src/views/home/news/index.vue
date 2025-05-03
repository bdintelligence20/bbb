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
          v-hasPermi="['website:banner:add']"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['website:banner:edit']"
          :disabled="single"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['website:banner:remove']"
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contentList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="ID" prop="id" width="80px"/>
      <el-table-column align="center" label="标题" prop="title"/>
      <el-table-column align="center" label="链接地址" prop="type">
        <template slot-scope="scope">
          <span>
            {{ scope.row.linkUrl }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="排序" prop="sort"/>
      <el-table-column align="center" label="创建时间" prop="createTime">
        <template slot-scope="scope">
          <span>
            {{ parseTime(scope.row.createTime) }}
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['website:banner:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            v-hasPermi="['website:banner:remove']"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改首页新闻 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body fullscreen>
      <el-form ref="form" :model="form" :rules="rules" label-position="top" label-width="150px">
        <el-row :gutter="40">
          <el-col :span="8">
            <el-form-item label="标题（英语）" prop="titleEn">
              <el-input v-model="form.titleEn" placeholder="请输入标题（英语）"/>
            </el-form-item>
            <el-form-item label="pc端图片（英语）" prop="imageUrlEn">
              <imageUpload v-model="form.imageUrlEn" :isBanner="true" :limit="1" @aspectRatio="getAspectRatio"/>
            </el-form-item>
            <el-form-item label="移动端图片（英语）" prop="imageUrlEn">
              <imageUpload v-model="form.mobileImageUrlEn" :isBanner="true" :limit="1" @aspectRatio="getAspectRatio"/>
            </el-form-item>
            <el-form-item label="图片描述（英语）" prop="describeEn">
              <el-input v-model="form.describeEn" placeholder="图片描述（英语）"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题（西班牙）" prop="titleEs">
              <el-input v-model="form.titleEs" placeholder="请输入标题（西班牙）"/>
            </el-form-item>
            <el-form-item label="pc端图片（西班牙）" prop="imageUrlEs">
              <imageUpload v-model="form.imageUrlEs" :limit="1"/>
            </el-form-item>
            <el-form-item label="移动端图片（西班牙）" prop="imageUrlEn">
              <imageUpload v-model="form.mobileImageUrlEs" :isBanner="true" :limit="1" @aspectRatio="getAspectRatio"/>
            </el-form-item>
            <el-form-item label="图片描述（西班牙）" prop="describeEs">
              <el-input v-model="form.describeEs" placeholder="图片描述（西班牙）"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题（阿拉伯）" prop="titleAr">
              <el-input v-model="form.titleAr" placeholder="请输入标题（阿拉伯）"/>
            </el-form-item>
            <el-form-item label="pc端图片（阿拉伯）" prop="imageUrlAr">
              <imageUpload v-model="form.imageUrlAr" :limit="1"/>
            </el-form-item>
            <el-form-item label="移动端图片（阿拉伯）" prop="imageUrlEn">
              <imageUpload v-model="form.mobileImageUrlAr" :isBanner="true" :limit="1" @aspectRatio="getAspectRatio"/>
            </el-form-item>
            <el-form-item label="图片描述（阿拉伯）" prop="describeAr">
              <el-input v-model="form.describeAr" placeholder="图片描述（阿拉伯）"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="链接地址" prop="linkUrl">
          <el-input v-model="form.linkUrl" placeholder="请输入内容" type="textarea"/>
        </el-form-item>
        <el-row type="flex">
          <el-col :span="8">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="form.sort" controls-position="right" :min="0" />
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
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addNews, delNews, getNews, getNewsDetail, listNews, updateNews } from '@/api/home/news'
import { titleCase } from '@/utils/index'
import { getToken } from '@/utils/auth'

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
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
      title: '',
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
      rules: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getAspectRatio(val) {
      const as = val.split('/')
      this.form.aspectRatio = (as[1] / as[0] * 100).toFixed(2)
    },
    /** 查询banner列表 */
    getList() {
      this.loading = true
      listNews(this.queryParams).then(response => {
        this.contentList = response.rows
        this.total = response.total
        this.loading = false
      })

    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
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
        mobileImageUrlEn: null, //移动端图片
        imageUrlEs: null,
        mobileImageUrlEs: null,
        imageUrlAr: null,
        mobileImageUrlAr: null,
        linkUrl: '',
        language: '',
        id: null,
        createTime: null,
        aspectRatio: '',
        sort: null,
        enable: "1",
        langData: []
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加首页新闻'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const ids = row.id || this.ids
      getNewsDetail(ids).then(response => {
        const res = response.data
        let obj = JSON.parse(JSON.stringify(res[0]))
        let { imageUrl, mobileImageUrl, title, describe, ...newObj } = obj
        res.forEach(data => {
          let lang = titleCase(data.language)
          newObj['imageUrl' + lang] = data.imageUrl
          newObj['mobileImageUrl' + lang] = data.mobileImageUrl
          newObj['title' + lang] = data.title
          newObj['describe' + lang] = data.describe
        })
        newObj.langData = response.data
        this.form = newObj
        this.open = true
        this.title = '修改首页新闻'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          const formArray = this.resolveFormData()
          if (this.form.id != null) {
            updateNews(formArray).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addNews(formArray).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    resolveFormData() {
      const languageList = ['en', 'es', 'ar']
      var reqArray = []
      let {
        langData,
        imageUrlEs,
        imageUrlEn,
        imageUrlAr,
        mobileImageUrlEn,
        mobileImageUrlEs,
        mobileImageUrlAr,
        describeEs,
        describeEn,
        describeAr,
        titleEs,
        titleEn,
        titleAr,
        ...newObj
      } = this.form
      if (langData.length < 1) {
        languageList.forEach(i => {
          let obj = JSON.parse(JSON.stringify(newObj))
          obj.language = i
          let lang = titleCase(i)
          obj.imageUrl = this.form['imageUrl' + lang]
          obj.mobileImageUrl = this.form['mobileImageUrl' + lang]
          obj.title = this.form['title' + lang]
          obj.describe = this.form['describe' + lang]
          reqArray.push(obj)
        })
      } else {
        langData.forEach((data, i) => {
          let lang = titleCase(data.language)
          langData[i].imageUrl = this.form['imageUrl' + lang]
          langData[i].mobileImageUrl = this.form['mobileImageUrl' + lang]
          langData[i].title = this.form['title' + lang]
          langData[i].describe = this.form['describe' + lang]
          langData[i].linkUrl = this.form.linkUrl
          langData[i].aspectRatio = this.form.aspectRatio
          langData[i].sort = this.form.sort
          langData[i].enable = this.form.enable
        })
        reqArray = langData
      }
      return reqArray
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除首页新闻编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delNews(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      })
    }
  }
}
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
