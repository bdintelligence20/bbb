<template>
  <div class="app-container">
    <el-form
      v-show="showSearch"
      ref="queryForm"
      :model="queryParams"
      :inline="true"
      label-wvehicleTypeIdth="68px"
    >
      <!-- <el-form-item label="车辆编码" prop="vehicleTypeId">
        <el-input
          v-model="queryParams.vehicleTypeId"
          placeholder="请输入车辆编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="车辆名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入车辆名称"
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
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['vehicle:class:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['vehicle:class:update']"
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['vehicle:class:delete']"
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['product:list:export']"
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          >导出</el-button
        >
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row>

    <el-table
      v-loading="loading"
      :data="typeList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车辆编号" align="center" prop="id" />
      <el-table-column label="车辆名称" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.langs[0] !== null">{{
            scope.row.langs[0].title
          }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆分类" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type !== null">{{
            scope.row.type.langs[0].name
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="车辆图片"
        align="center"
        prop="imgUrl"
        width="120"
      >
        <template slot-scope="scope">
          <el-image
            :src="fileUploadHost + scope.row.imgUrl"
            lazy
            @click="previewPicture(fileUploadHost + scope.row.imgUrl)"
          />
        </template>
      </el-table-column>
      <el-table-column label="车辆排序" align="center" prop="sort" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-with"
      >
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['vehicle:class:update']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            >修改</el-button
          >
          <el-button
            v-hasPermi="['specification:img-and-text:query']"
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleImageAndTextListInfo(scope.row)"
            >图文轮播板块</el-button
          >
          <el-button
            v-hasPermi="['characteristic:img-and-text:query']"
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleImageAndTextInfo(scope.row)"
            >特性图文板块</el-button
          >
          <el-button
            v-hasPermi="['characteristic:text-block:query']"
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleTextBlockInfo(scope.row)"
            >文本块板块</el-button
          >
          <el-button
            v-hasPermi="['characteristic:gallery:query']"
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleGalleryCharacteristicInfo(scope.row)"
            >相册特性板块</el-button
          >
          <el-button
            v-hasPermi="['vehicle:gallery:query']"
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleGalleryInfo(scope.row)"
            >相册板块</el-button
          >
          <el-button
            v-hasPermi="['vehicle:color:query']"
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleColorInfo(scope.row)"
            >车辆颜色</el-button
          >
          <el-button
            v-hasPermi="['vehicle:class:delete']"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改车辆对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body fullscreen>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-row :gutter="20">
          <el-col :span="10">
            <el-form-item label="车辆图片" prop="imgUrl">
              <imageUpload v-model="form.imgUrl" :limit="1" />
            </el-form-item>
            <el-form-item label="经销商下载-车辆图片" prop="imgVehicleUrl">
              <imageUpload v-model="form.imgVehicleUrl" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="车辆分类" prop="vehicleTypeId">
              <el-select
                v-model="form.vehicleTypeId"
                placeholder="请选择车辆分类"
                clearable
                size="small"
              >
                <el-option
                  v-for="type in cartModleOption"
                  :key="type.vehicleTypeId"
                  :label="type.name"
                  :value="type.vehicleTypeId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="车辆顺序" prop="sort">
              <el-input-number
                v-model="form.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="英语车辆名称" prop="titleen">
              <el-input
                v-model="form.titleen"
                placeholder="请输入英文车辆名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语车辆名称" prop="titlees">
              <el-input
                v-model="form.titlees"
                placeholder="请输入西语车辆名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语车辆名称" prop="titlear">
              <el-input
                v-model="form.titlear"
                placeholder="请输入阿语车辆名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英语车辆副标题" prop="subTitleen">
              <el-input
                v-model="form.subTitleen"
                placeholder="请输入英文车辆标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语车辆副标题" prop="subTitlees">
              <el-input
                v-model="form.subTitlees"
                placeholder="请输入西语车辆标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语车辆副标题" prop="subTitlear">
              <el-input
                v-model="form.subTitlear"
                placeholder="请输入阿语车辆标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英语车辆VR" prop="vrViewUrlen">
              <el-input
                v-model="form.vrViewUrlen"
                placeholder="请输入英文车辆AR"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语车辆VR" prop="vrViewUrles">
              <el-input
                v-model="form.vrViewUrles"
                placeholder="请输入西语车辆AR"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语车辆VR" prop="vrViewUrlar">
              <el-input
                v-model="form.vrViewUrlar"
                placeholder="请输入阿语车辆AR"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英语PDF文件" prop="fileListen">
              <fileUpload v-model="form.fileListen" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语PDF文件" prop="fileListes">
              <fileUpload v-model="form.fileListes" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语PDF文件" prop="fileListar">
              <fileUpload v-model="form.fileListar" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英语文件标题" prop="specificationsPDFTitleen">
              <el-input
                v-model="form.specificationsPDFTitleen"
                placeholder="请输入阿语文件标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语文件标题" prop="specificationsPDFTitlees">
              <el-input
                v-model="form.specificationsPDFTitlees"
                placeholder="请输入阿语文件标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语文件标题" prop="specificationsPDFTitlear">
              <el-input
                v-model="form.specificationsPDFTitlear"
                placeholder="请输入阿语文件标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英语文件副标题" prop="specificationsPDFDescen">
              <el-input
                v-model="form.specificationsPDFDescen"
                placeholder="请输入阿语文件副标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语文件副标题" prop="specificationsPDFDesces">
              <el-input
                v-model="form.specificationsPDFDesces"
                placeholder="请输入阿语文件副标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语文件副标题" prop="specificationsPDFDescar">
              <el-input
                v-model="form.specificationsPDFDescar"
                placeholder="请输入阿语文件副标题"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="英语车辆详情" prop="descen">
              <editor
                ref="editor"
                v-model="form.descen"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语车辆详情" prop="deses">
              <editor
                ref="editor"
                v-model="form.desces"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语车辆详情" prop="descar">
              <editor
                ref="editor"
                v-model="form.descar"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="显示状态" prop="enableen">
              <el-radio-group v-model="form.enableen">
                <el-radio label="1">显示</el-radio>
                <el-radio label="0">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="显示状态" prop="enablees">
              <el-radio-group v-model="form.enablees">
                <el-radio label="1">显示</el-radio>
                <el-radio label="0">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="显示状态" prop="enablear">
              <el-radio-group v-model="form.enablear">
                <el-radio label="1">显示</el-radio>
                <el-radio label="0">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel('module')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 图文特性列表对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openImageAndText"
      fullscreen
      append-to-body
    >
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['characteristic:img-and-text:add']"
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleImageAndTextAdd"
            >新增</el-button
          >
        </el-col>
      </el-row>
      <el-table v-loading="imgAndTextLoading" :data="imgAndTextList">
        <el-table-column label="车辆编号" align="center" prop="id" />
        <el-table-column label="车辆图文特性排序" align="center" prop="sort" />
        <el-table-column label="车辆图文特性标题" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.langs.length > 0">{{
              scope.row.langs[0].title
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="车辆图文特性副标题" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.langs.length > 0">{{
              scope.row.langs[0].subTitle
            }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="车辆图文特性图片"
          align="center"
          prop="imgUrl"
          width="120"
        >
          <template slot-scope="scope">
            <el-image
              :src="fileUploadHost + scope.row.imgUrl"
              lazy
              @click="previewPicture(fileUploadHost + scope.row.imgUrl)"
            />
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-with"
        >
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['characteristic:img-and-text:update']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleImageAndTextUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              v-hasPermi="['characteristic:img-and-text:delete']"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleImageAndTextDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改车辆图文特性对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openImageAndTextInfo"
      append-to-body
      fullscreen
    >
      <el-form
        ref="imageAndTextForm"
        :model="imageAndTextForm"
        :rules="rules"
        label-width="150px"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="图文特性顺序" prop="sort">
              <el-input-number
                v-model="imageAndTextForm.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="文字位置" prop="viewType">
              <el-select
                v-model="imageAndTextForm.viewType"
                placeholder="请选择图文特性结构"
                clearable
                size="small"
              >
                <el-option
                  v-for="type in imgAndTextOption"
                  :key="type.label"
                  :label="type.label"
                  :value="type.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="英语图文特性图片" prop="imgUrlen">
              <imageUpload v-model="imageAndTextForm.imgUrlen" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语图文特性图片" prop="imgUrles">
              <imageUpload v-model="imageAndTextForm.imgUrles" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语图文特性图片" prop="imgUrlar">
              <imageUpload v-model="imageAndTextForm.imgUrlar" :limit="1" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="英语图文特性名称" prop="titleen">
              <el-input
                v-model="imageAndTextForm.titleen"
                placeholder="请输入英文图文特性名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语图文特性名称" prop="titlees">
              <el-input
                v-model="imageAndTextForm.titlees"
                placeholder="请输入西语图文特性名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语图文特性名称" prop="titlear">
              <el-input
                v-model="imageAndTextForm.titlear"
                placeholder="请输入阿语图文特性名称"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="英语图文特性标题" prop="subTitleen">
              <el-input
                v-model="imageAndTextForm.subTitleen"
                type="textarea"
                :rows="3"
                placeholder="请输入英文图文特性标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语车图文特性标题" prop="subTitlees">
              <el-input
                v-model="imageAndTextForm.subTitlees"
                type="textarea"
                :rows="3"
                placeholder="请输入西语图文特性标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语车图文特性标题" prop="subTitlear">
              <el-input
                v-model="imageAndTextForm.subTitlear"
                type="textarea"
                :rows="3"
                placeholder="请输入阿语图文特性标题"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="英语图文特性详情" prop="descen">
              <editor
                ref="editor"
                v-model="imageAndTextForm.descen"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语图文特性详情" prop="deses">
              <editor
                ref="editor"
                v-model="imageAndTextForm.desces"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语图文特性详情" prop="descar">
              <editor
                ref="editor"
                v-model="imageAndTextForm.descar"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitImageAndTextForm"
          >确 定</el-button
        >
        <el-button @click="cancel('imgAndText')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 图文轮播列表对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openImageAndTextList"
      fullscreen
      append-to-body
    >
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['specification:img-and-text:add']"
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleImageAndTextListAdd"
            >新增</el-button
          >
        </el-col>
      </el-row>
      <el-table v-loading="imgAndTextListLoading" :data="imgAndTextListList">
        <el-table-column label="车辆编号" align="center" prop="id" />
        <el-table-column label="车辆图文轮播排序" align="center" prop="sort" />
        <el-table-column label="车辆图文轮播标题" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.langs.length > 0">{{
              scope.row.langs[0].title
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="车辆图文轮播副标题" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.langs.length > 0">{{
              scope.row.langs[0].subTitle
            }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="车辆图文轮播图片"
          align="center"
          prop="imgUrl"
          width="120"
        >
          <template slot-scope="scope">
            <el-image
              :src="fileUploadHost + scope.row.imgUrl"
              lazy
              @click="previewPicture(fileUploadHost + scope.row.imgUrl)"
            />
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-with"
        >
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['specification:img-and-text:update']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleImageAndTextListUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              v-hasPermi="['specification:img-and-text:delete']"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleImageAndTextListDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改车辆图文轮播对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openImageAndTextListInfo"
      append-to-body
      fullscreen
    >
      <el-form
        ref="imageAndTextListForm"
        :model="imageAndTextListForm"
        :rules="rules"
        label-width="150px"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="图文轮播图片" prop="imgUrl">
              <imageUpload v-model="imageAndTextListForm.imgUrl" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="图文轮播顺序" prop="sort">
              <el-input-number
                v-model="imageAndTextListForm.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="英语图文轮播名称" prop="titleen">
              <el-input
                v-model="imageAndTextListForm.titleen"
                placeholder="请输入英文图文轮播名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语图文轮播名称" prop="titlees">
              <el-input
                v-model="imageAndTextListForm.titlees"
                placeholder="请输入西语图文轮播名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语图文轮播名称" prop="titlear">
              <el-input
                v-model="imageAndTextListForm.titlear"
                placeholder="请输入阿语图文轮播名称"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="英语图文轮播详情" prop="descen">
              <editor
                ref="editor"
                v-model="imageAndTextListForm.descen"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语图文轮播详情" prop="deses">
              <editor
                ref="editor"
                v-model="imageAndTextListForm.desces"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语图文轮播详情" prop="descar">
              <editor
                ref="editor"
                v-model="imageAndTextListForm.descar"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitImageAndTextListForm"
          >确 定</el-button
        >
        <el-button @click="cancel('imageAndTextList')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 文本块列表对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openTextBlock"
      fullscreen
      append-to-body
    >
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['characteristic:text-block:add']"
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleTextBlockAdd"
            >新增</el-button
          >
        </el-col>
      </el-row>
      <el-table v-loading="openTextBlockLoading" :data="textBlockList">
        <el-table-column label="车辆编号" align="center" prop="id" />
        <el-table-column
          label="车辆文本块特性排序"
          align="center"
          prop="sort"
        />
        <el-table-column label="车辆文本块特性标题" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.langs.length > 0">{{
              scope.row.langs[0].title
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-with"
        >
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['characteristic:text-block:update']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleTextBlockUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              v-hasPermi="['characteristic:text-block:delete']"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleTextBlockDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改车辆文本块特性对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openTextBlockInfo"
      append-to-body
      fullscreen
    >
      <el-form
        ref="textBlockForm"
        :model="textBlockForm"
        :rules="rules"
        label-width="150px"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="图文特性顺序" prop="sort">
              <el-input-number
                v-model="textBlockForm.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="英语文本块特性名称" prop="titleen">
              <el-input
                v-model="textBlockForm.titleen"
                placeholder="请输入英文图文特性名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语文本块特性名称" prop="titlees">
              <el-input
                v-model="textBlockForm.titlees"
                placeholder="请输入西语图文特性名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语文本块特性名称" prop="titlear">
              <el-input
                v-model="textBlockForm.titlear"
                placeholder="请输入阿语图文特性名称"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <!-- <el-row>
          <el-col :span="8">
            <el-form-item label="英语文本块特性顺序" prop="sorten">
              <el-input-number v-model="textBlockForm.sorten" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="文本块特性顺序" prop="sortes">
              <el-input-number v-model="textBlockForm.sortes" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="文本块特性顺序" prop="sortar">
              <el-input-number v-model="textBlockForm.sortar" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
        </el-row> -->
        <el-row>
          <el-col :span="8">
            <el-form-item label="英语文本块特性详情" prop="descen">
              <editor
                ref="editor"
                v-model="textBlockForm.descen"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语文本块特性详情" prop="deses">
              <editor
                ref="editor"
                v-model="textBlockForm.desces"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语文本块特性详情" prop="descar">
              <editor
                ref="editor"
                v-model="textBlockForm.descar"
                :min-height="192"
                :height="360"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitTextBlockForm">确 定</el-button>
        <el-button @click="cancel('textBlock')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 相册列表对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openGallery"
      fullscreen
      append-to-body
    >
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['vehicle:gallery:add']"
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleGalleryAdd"
            >新增</el-button
          >
        </el-col>
      </el-row>
      <el-table v-loading="openGalleryLoading" :data="galleryList">
        <el-table-column label="相册编号" align="center" prop="id" />
        <el-table-column label="相册排序" align="center" prop="sort" />
        <el-table-column label="相册图片" align="center" width="150px">
          <template slot-scope="scope">
            <span v-if="scope.row.imgUrl.length > 0">
              <span v-for="img in scope.row.imgUrl" :key="img">
                <el-image
                  :src="fileUploadHost + img"
                  lazy
                  @click="previewPicture(fileUploadHost + scope.row.imgUrl)"
                />
              </span>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-with"
        >
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['vehicle:gallery:update']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleGalleryUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              v-hasPermi="['vehicle:gallery:delete']"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleGalleryDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改车辆相册对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openGalleryInfo"
      append-to-body
      fullscreen
    >
      <el-form
        ref="galleryForm"
        :model="galleryForm"
        :rules="rules"
        label-width="150px"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="相册图片组" prop="imgUrl">
              <imageUpload v-model="galleryForm.imgUrl" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="相册特性顺序" prop="sort">
              <el-input-number
                v-model="galleryForm.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitGalleryForm">确 定</el-button>
        <el-button @click="cancel('gallery')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 相册特性列表对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openGalleryCharacteristic"
      fullscreen
      append-to-body
    >
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['characteristic:gallery:add']"
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleGalleryCharacteristicAdd"
            >新增</el-button
          >
          <el-button
            v-hasPermi="['characteristic:gallery:add']"
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleGalleryTitleAdd"
            >新增标题</el-button
          >
          <el-button
            v-hasPermi="['characteristic:gallery:update']"
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            @click="handleGalleryTitleUpdate"
            >修改标题</el-button
          >
        </el-col>
      </el-row>
      <el-table
        v-loading="openGalleryCharacteristicLoading"
        :data="galleryCharacteristicList"
      >
        <el-table-column label="相册特性编号" align="center" prop="id" />
        <el-table-column label="相册特性标题" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.langs.length > 0">{{
              scope.row.langs[0].title
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="相册特性图片" align="center" width="150px">
          <template slot-scope="scope">
            <span v-if="scope.row.imgUrl.length > 0">
              <span v-for="img in scope.row.imgUrl" :key="img">
                <el-image
                  :src="fileUploadHost + img"
                  lazy
                  @click="previewPicture(fileUploadHost + scope.row.imgUrl)"
                />
              </span>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-with"
        >
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['characteristic:gallery:update']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleGalleryCharacteristicUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              v-hasPermi="['characteristic:gallery:delete']"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleGalleryCharacteristicDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改车辆相册特性对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openGalleryCharacteristicInfo"
      append-to-body
      fullscreen
    >
      <el-form
        ref="galleryCharacteristicForm"
        :model="galleryCharacteristicForm"
        :rules="rules"
        label-width="150px"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="相册图片组" prop="imgUrl">
              <imageUpload
                v-model="galleryCharacteristicForm.imgUrl"
                :limit="1"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="相册特性顺序" prop="sort">
              <el-input-number
                v-model="galleryCharacteristicForm.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="英语文本块特性名称" prop="titleen">
              <el-input
                v-model="galleryCharacteristicForm.titleen"
                placeholder="请输入英文相册特性名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语文本块特性名称" prop="titlees">
              <el-input
                v-model="galleryCharacteristicForm.titlees"
                placeholder="请输入西语相册特性名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语文本块特性名称" prop="titlear">
              <el-input
                v-model="galleryCharacteristicForm.titlear"
                placeholder="请输入阿语相册特性名称"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitGalleryCharacteristicForm"
          >确 定</el-button
        >
        <el-button @click="cancel('galleryCharacteristic')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改车辆相册特性标题对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openGalleryTitle"
      append-to-body
      fullscreen
    >
      <el-form
        ref="galleryTitleForm"
        :model="galleryTitleForm"
        :rules="rules"
        label-width="150px"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="英语文本块特性标题" prop="titleen">
              <el-input
                v-model="galleryTitleForm.titleen"
                placeholder="请输入英文相册特性标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语文本块特性标题" prop="titlees">
              <el-input
                v-model="galleryTitleForm.titlees"
                placeholder="请输入西语相册特性标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语文本块特性标题" prop="titlear">
              <el-input
                v-model="galleryTitleForm.titlear"
                placeholder="请输入阿语相册特性标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="英语文副标题" prop="descen">
              <el-input
                v-model="galleryTitleForm.descen"
                placeholder="请输入英文副标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="西语文副标题" prop="desces">
              <el-input
                v-model="galleryTitleForm.desces"
                placeholder="请输入西语副标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="阿语文副标题" prop="descar">
              <el-input
                v-model="galleryTitleForm.descar"
                placeholder="请输入阿语副标题"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitGalleryTitleForm"
          >确 定</el-button
        >
        <el-button @click="cancel('galleryTitle')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 车辆颜色对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openColor"
      fullscreen
      append-to-body
    >
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            v-hasPermi="['vehicle:color:add']"
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleColorAdd"
            >新增</el-button
          >
        </el-col>
      </el-row>
      <el-table v-loading="openColorLoading" :data="colorList">
        <el-table-column label="颜色编号" align="center" prop="id" />
        <el-table-column label="颜色" align="center">
          <template slot-scope="scope">
            <pieColor
              radius="20"
              :rgbCode="scope.row.rgbCode && scope.row.rgbCode.split('/')"
            ></pieColor>
            <!-- <el-tag :color="scope.row.rgbCode">
              颜色
            </el-tag> -->
          </template>
        </el-table-column>
        <el-table-column label="颜色图片" align="center">
          <template slot-scope="scope" prop="imgUrl">
            <el-image
              :src="fileUploadHost + scope.row.imgUrl"
              lazy
              @click="previewPicture(fileUploadHost + scope.row.imgUrl)"
            />
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-with"
        >
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['vehicle:color:update']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleColorUpdate(scope.row)"
              >修改</el-button
            >
            <el-button
              v-hasPermi="['vehicle:color:delete']"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleColorDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改车辆颜色颜色对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openColorInfo"
      append-to-body
      fullscreen
    >
      <el-form
        ref="colorForm"
        :model="colorForm"
        :rules="rules"
        label-width="150px"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="颜色图片组" prop="imgUrl">
              <imageUpload v-model="colorForm.imgUrl" :limit="1" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="颜色图片组1" prop="rgbCode1">
              <el-color-picker
                key="rgbCode1"
                v-model="colorForm.rgbCode1"
                @change="colorChange()"
              />
            </el-form-item>
            <el-form-item label="颜色图片组2" prop="rgbCode2">
              <el-color-picker
                key="rgbCode2"
                v-model="colorForm.rgbCode2"
                @change="colorChange()"
              />
            </el-form-item>
            <el-form-item label="颜色图片组3" prop="rgbCode3">
              <el-color-picker
                key="rgbCode3"
                v-model="colorForm.rgbCode3"
                @change="colorChange()"
              />
            </el-form-item>
            <el-form-item label="颜色图片组4" prop="rgbCode4">
              <el-color-picker
                key="rgbCode4"
                v-model="colorForm.rgbCode4"
                @change="colorChange()"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="颜色特性顺序" prop="sort">
              <el-input-number
                v-model="colorForm.sort"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitColorForm">确 定</el-button>
        <el-button @click="cancel('color')">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  updateGalleryTitle,
  getGalleryTitle,
  listclass,
  getclass,
  delclass,
  addclass,
  updateclass,
  listImgAndText,
  addImgAndText,
  getImgAndText,
  updateImgAndText,
  delImgAndText,
  listImgAndTextList,
  addImgAndTextList,
  getImgAndTextList,
  updateImgAndTextList,
  delImgAndTextList,
  listTextBlock,
  addTextBlock,
  getTextBlock,
  updateTextBlock,
  delTextBlock,
  listGallery,
  addGallery,
  getGallery,
  updateGallery,
  delGallery,
  listGalleryCharacteristic,
  addGalleryCharacteristic,
  getGalleryCharacteristic,
  updateGalleryCharacteristic,
  delGalleryCharacteristic,
  listColor,
  addColor,
  getColor,
  updateColor,
  delColor,
} from "@/api/product/list";
import { listtype } from "@/api/product/category";
import pieColor from "@/components/pieColor";
export default {
  name: "ProductList",
  dicts: ["sys_normal_TextBlock"],
  components: { pieColor },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 遮罩层
      itemLoading: true,
      // 图文特性遮罩层
      imgAndTextLoading: true,
      // 图文轮播遮罩层
      imgAndTextListLoading: true,
      // 文本块特性遮罩层
      openTextBlockLoading: true,
      // 相册特性遮罩层
      openGalleryLoading: true,
      // 相册特性遮罩层
      openGalleryCharacteristicLoading: true,
      // 颜色遮罩层
      openColorLoading: true,
      // 选中数组
      vehicleTypeIds: [],
      // 车辆下拉框option
      cartModleOption: [],
      // 车辆下拉框option
      imgAndTextOption: [
        // {
        //   label: '文字框居上',
        //   value: 'top'
        // },
        // {
        //   label: '图片居左',
        //   value: 'left'
        // },
        // {
        //   label: '图片居右',
        //   value: 'right'
        // },
        {
          label: "文字居左",
          value: "textLeft",
        },
        {
          label: "文字居右",
          value: "textRight",
        },
        {
          label: "文字居左上",
          value: "textLeftTop",
        },
        {
          label: "文字居左下",
          value: "textLeftBottom",
        },
        {
          label: "文字居右上",
          value: "textRightTop",
        },
        {
          label: "文字居右下",
          value: "textRightBottom",
        },
      ],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 图文特性总条数
      imgAndTextTotal: 0,
      // 车辆表格数据
      typeList: [],
      // 图文特性表格数据
      imgAndTextList: [],
      // 图文轮播表格数据
      imgAndTextListList: [],
      // 文本块特性表格数据
      textBlockList: [],
      // 相册特性表格数据
      galleryList: [],
      // 相册特性表格数据
      galleryCharacteristicList: [],
      // 颜色特性表格数据
      colorList: [],
      // 车辆ID
      vehicleClassId: "",
      // 弹框标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示图文特性弹框
      openImageAndText: false,
      // 是否显示图文特性编辑框弹框
      openImageAndTextInfo: false,
      // 是否显示图文轮播弹框
      openImageAndTextList: false,
      // 是否显示图文轮播编辑框弹框
      openImageAndTextListInfo: false,
      // 是否显示文本块特性弹框
      openTextBlock: false,
      // 是否显示文本块特性弹框
      openTextBlockInfo: false,
      // 是否显示相册弹框
      openGallery: false,
      // 是否显示相册弹框
      openGalleryInfo: false,
      // 是否显示相册特性弹框
      openGalleryCharacteristic: false,
      // 是否显示相册特性弹框
      openGalleryCharacteristicInfo: false,
      // 是否显示颜色弹框
      openColor: false,
      // 是否显示颜色详情弹框
      openColorInfo: false,
      // 是否显示相册特性标题弹框
      openGalleryTitle: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleTypeId: undefined,
        name: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 图文特性表单
      imageAndTextForm: {},
      // 图文轮播表单
      imageAndTextListForm: {},
      // 文本块特性表单
      textBlockForm: {},
      // 相册表单
      galleryForm: {},
      // 相册特性表单
      galleryCharacteristicForm: {},
      // 相册特性标题表单
      galleryTitleForm: {},
      // 相册表单
      colorForm: {},
      // 表单校验
      rules: {
        vehicleTypeId: [
          { required: true, message: "车辆编码不能为空", trigger: "blur" },
        ],
        titleen: [
          { required: true, message: "英语车辆名称不能为空", trigger: "blur" },
        ],
        titlees: [
          { required: true, message: "西语车辆名称不能为空", trigger: "blur" },
        ],
        titlear: [
          { required: true, message: "阿语车辆名称不能为空", trigger: "blur" },
        ],
        sort: [
          { required: true, message: "车辆顺序不能为空", trigger: "blur" },
        ],
        imgUrlen: [
          { required: true, message: "英语车辆图片不能为空", trigger: "blur" },
        ],
        imgUrles: [
          { required: true, message: "西语车辆图片不能为空", trigger: "blur" },
        ],
        imgUrlar: [
          { required: true, message: "阿语车辆图片不能为空", trigger: "blur" },
        ],
        subTitleen: [
          {
            required: true,
            message: "英语车辆副标题不能为空",
            trigger: "blur",
          },
        ],
        subTitlees: [
          {
            required: true,
            message: "西语车辆副标题不能为空",
            trigger: "blur",
          },
        ],
        subTitlear: [
          {
            required: true,
            message: "阿语车辆副标题不能为空",
            trigger: "blur",
          },
        ],
        fileListen: [
          // { required: true, message: '英语PDF文件不能为空', trigger: 'blur' }
        ],
        fileListes: [
          // { required: true, message: '西语PDF文件不能为空', trigger: 'blur' }
        ],
        fileListar: [
          // { required: true, message: '阿语PDF文件不能为空', trigger: 'blur' }
        ],
        desc: [
          { required: true, message: "车辆描述不能为空", trigger: "blur" },
        ],
        viewType: [
          { required: true, message: "图文特性结构不能为空", trigger: "blur" },
        ],
      },
      languageList: ["en", "es", "ar"],
    };
  },
  created() {
    this.getList();
    this.getListType();
  },
  methods: {
    /** 查询车辆列表 */
    getList() {
      this.loading = true;
      listclass(this.queryParams).then((response) => {
        this.typeList = response.data;
        this.total = response.data.length;
        this.loading = false;
      });
    },
    // 查询车辆型号
    getListType() {
      listtype().then((response) => {
        const newObj = [];
        response.data.map((value) => {
          value.langs.map((val) => {
            if (val.lang === "en") {
              newObj.push({ vehicleTypeId: val.vehicleTypeId, name: val.name });
            }
          });
        });
        this.cartModleOption = newObj;
      });
    },
    // 取消按钮
    cancel(type) {
      if (type === "module") {
        this.open = false;
      } else if (type === "imgAndText") {
        this.openImageAndTextInfo = false;
      } else if (type === "imageAndTextList") {
        this.openImageAndTextListInfo = false;
      } else if (type === "textBlock") {
        this.openTextBlockInfo = false;
      } else if (type === "gallery") {
        this.openGalleryInfo = false;
      } else if (type === "galleryCharacteristic") {
        this.openGalleryCharacteristicInfo = false;
      } else if (type === "color") {
        this.openColorInfo = false;
      } else if (type === "galleryTitle") {
        this.openGalleryTitle = false;
      }
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        titleen: undefined,
        titlees: undefined,
        titlear: undefined,
        subTitleen: undefined,
        subTitlees: undefined,
        subTitlear: undefined,
        descen: undefined,
        desces: undefined,
        descar: undefined,
        vehicleTypeId: undefined,
        imgUrl: undefined,
        sort: 0,
        remark: undefined,
        enableen: "1",
        enablees: "1",
        enablear: "1",
      };
      this.resetForm("form");
      this.imageAndTextForm = {
        titleen: undefined,
        titlees: undefined,
        titlear: undefined,
        subTitleen: undefined,
        subTitlees: undefined,
        subTitlear: undefined,
        descen: undefined,
        desces: undefined,
        descar: undefined,
        sort: 0,
        sorten: 0,
        sortes: 0,
        sortar: 0,
        remark: undefined,
      };
      this.resetForm("imageAndTextForm");
      this.textBlockForm = {
        titleen: undefined,
        titlees: undefined,
        titlear: undefined,
        descen: undefined,
        desces: undefined,
        descar: undefined,
        sort: 0,
        sorten: 0,
        sortes: 0,
        sortar: 0,
        remark: undefined,
      };
      this.resetForm("imageAndTextListForm");
      this.textBlockForm = {
        titleen: undefined,
        titlees: undefined,
        titlear: undefined,
        descen: undefined,
        desces: undefined,
        descar: undefined,
        sort: 0,
        remark: undefined,
      };
      this.resetForm("textBlockForm");
      this.galleryForm = {
        imgUrl: undefined,
        sort: 0,
      };
      this.resetForm("galleryForm");
      this.galleryCharacteristicForm = {
        titleen: undefined,
        titlees: undefined,
        titlear: undefined,
        sort: 0,
      };
      this.resetForm("galleryCharacteristicForm");
      this.galleryCharacteristicForm = {
        color: undefined,
        titlees: undefined,
        titlear: undefined,
        sort: 0,
      };
      this.resetForm("galleryCharacteristicForm");
      this.colorForm = {
        imgUrl: undefined,
        rgbCode1: null,
        rgbCode2: null,
        rgbCode3: null,
        rgbCode4: null,
        sort: 0,
      };
      this.resetForm("colorForm");
      this.galleryTitleForm = {
        titleen: undefined,
        titlees: undefined,
        titlear: undefined,
        desceen: undefined,
        desces: undefined,
        descar: undefined,
      };
      this.resetForm("galleryTitleForm");
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
      // this.vehicleTypeIds = selection.map(item => item.vehicleTypeId)
      this.vehicleTypeIds = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vehicleTypeId = row.id || this.vehicleTypeIds;
      getclass(vehicleTypeId).then((response) => {
        this.form = this.moduleButtonFormatData(response.data, "module");
        this.open = true;
        this.title = "修改车辆";
      });
    },
    /** 详情按钮操作 */
    handleView(row) {
      this.itemLoading = true;
      this.openView = true;
      const vehicleTypeId = row.vehicleTypeId || this.vehicleTypeIds;
      getclass(vehicleTypeId).then((response) => {
        this.typeItemList = response.data.langs;
        this.itemTotal = response.data.length;
        this.itemLoading = false;
      });
    },
    /** 修改按钮数据重置 */
    editButtonFormatData(data) {
      const newObj = {};
      data.langs.map((value) => {
        newObj["name" + value.lang] = data.name;
      });
      newObj.imgUrl = data.imgUrl;
      newObj.sort = data.sort;
      newObj.id = data.id;
      newObj.createTime = data.createTime;
      return newObj;
    },
    /** 修改表单提交数据重置 */
    moduleSubmmitFormatData(data, type) {
      this.languageList.map((value) => {
        data.langs.map((vue) => {
          if (vue.lang === value) {
            if (type === "module") {
              vue.title = this.form[`title${value}`];
              vue.subTitle = this.form[`subTitle${value}`];
              vue.vrViewUrl = this.form[`vrViewUrl${value}`];
              vue.specificationsPDFUrl = this.form[`fileList${value}`];
              vue.specificationsPDFTitle =
                this.form[`specificationsPDFTitle${value}`];
              vue.specificationsPDFDesc =
                this.form[`specificationsPDFDesc${value}`];
              vue.desc = this.form[`desc${value}`];
              vue.enable = this.form[`enable${value}`];
            } else if (type === "imgAndText") {
              vue.title = this.imageAndTextForm[`title${value}`];
              vue.subTitle = this.imageAndTextForm[`subTitle${value}`];
              vue.desc = this.imageAndTextForm[`desc${value}`];
              vue.imgUrl = this.imageAndTextForm[`imgUrl${value}`];
            } else if (type === "imgAndTextList") {
              vue.title = this.imageAndTextListForm[`title${value}`];
              vue.subTitle = this.imageAndTextListForm[`subTitle${value}`];
              vue.desc = this.imageAndTextListForm[`desc${value}`];
            } else if (type === "textBlock") {
              vue.title = this.textBlockForm[`title${value}`];
              vue.desc = this.textBlockForm[`desc${value}`];
            } else if (type === "galleryCharacteristic") {
              vue.title = this.galleryCharacteristicForm[`title${value}`];
            } else if (type === "galleryTitle") {
              vue.title = this.galleryTitleForm[`title${value}`];
              vue.desc = this.galleryTitleForm[`desc${value}`];
            }
          }
        });
      });
      return data;
    },
    /** 修改表单回显数据重置 */
    moduleButtonFormatData(data, type) {
      this.languageList.map((value) => {
        data.langs.map((vue) => {
          if (vue.lang === value) {
            if (type === "module") {
              data[`title${value}`] = vue.title;
              data[`subTitle${value}`] = vue.subTitle;
              data[`vrViewUrl${value}`] = vue.vrViewUrl;
              data[`fileList${value}`] = vue.specificationsPDFUrl;
              data[`specificationsPDFTitle${value}`] =
                vue.specificationsPDFTitle;
              data[`specificationsPDFDesc${value}`] = vue.specificationsPDFDesc;
              data[`desc${value}`] = vue.desc;
              data[`enable${value}`] = vue.enable + "";
            } else if (type === "imgAndText") {
              data[`title${value}`] = vue.title;
              data[`subTitle${value}`] = vue.subTitle;
              data[`desc${value}`] = vue.desc;
              data[`imgUrl${value}`] = vue.imgUrl;
            } else if (type === "imgAndTextList") {
              data[`title${value}`] = vue.title;
              data[`desc${value}`] = vue.desc;
            } else if (type === "textBlock") {
              data[`title${value}`] = vue.title;
              data[`desc${value}`] = vue.desc;
            } else if (type === "galleryCharacteristic") {
              data[`title${value}`] = vue.title;
            } else if (type === "galleryTitle") {
              data[`title${value}`] = vue.title;
              data[`desc${value}`] = vue.desc;
            }
          }
        });
      });
      return data;
    },
    // 车辆新增数据重置
    moduleAddFormatData(type) {
      const langs = [];
      this.languageList.map((value) => {
        if (type === "module") {
          langs.push({
            title: this.form[`title${value}`],
            subTitle: this.form[`subTitle${value}`],
            vrViewUrl: this.form[`vrViewUrl${value}`],
            specificationsPDFUrl: this.form[`fileList${value}`],
            specificationsPDFTitle: this.form[`specificationsPDFTitle${value}`],
            specificationsPDFDesc: this.form[`specificationsPDFDesc${value}`],
            desc: this.form[`desc${value}`],
            enable: this.form[`enable${value}`],
            lang: value,
          });
        } else if (type === "imgAndText") {
          langs.push({
            title: this.imageAndTextForm[`title${value}`],
            subTitle: this.imageAndTextForm[`subTitle${value}`],
            desc: this.imageAndTextForm[`desc${value}`],
            imgUrl: this.imageAndTextForm[`imgUrl${value}`],
            lang: value,
          });
        } else if (type === "imgAndTextList") {
          langs.push({
            title: this.imageAndTextListForm[`title${value}`],
            subTitle: this.imageAndTextListForm[`subTitle${value}`],
            desc: this.imageAndTextListForm[`desc${value}`],
            lang: value,
          });
        } else if (type === "textBlock") {
          langs.push({
            title: this.textBlockForm[`title${value}`],
            desc: this.textBlockForm[`desc${value}`],
            sort: this.textBlockForm[`sort${value}`],
            lang: value,
          });
        } else if (type === "galleryCharacteristic") {
          langs.push({
            title: this.galleryCharacteristicForm[`title${value}`],
            lang: value,
          });
        } else if (type === "galleryTitle") {
          langs.push({
            title: this.galleryTitleForm[`title${value}`],
            desc: this.galleryTitleForm[`desc${value}`],
            lang: value,
          });
        }
      });
      if (type === "module") {
        this.form.langs = langs;
      } else if (type === "imgAndText") {
        this.imageAndTextForm.langs = langs;
        this.imageAndTextForm.vehicleClassId = this.vehicleClassId;
      } else if (type === "imgAndTextList") {
        this.imageAndTextListForm.langs = langs;
        this.imageAndTextListForm.vehicleClassId = this.vehicleClassId;
      } else if (type === "textBlock") {
        this.textBlockForm.langs = langs;
        this.textBlockForm.vehicleClassId = this.vehicleClassId;
      } else if (type === "galleryCharacteristic") {
        this.galleryCharacteristicForm.langs = langs;
        this.galleryCharacteristicForm.vehicleClassId = this.vehicleClassId;
      } else if (type === "galleryTitle") {
        this.galleryTitleForm.langs = langs;
        this.galleryTitleForm.vehicleClassId = this.vehicleClassId;
      }
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (
            (this.form.vehicleTypeId !== undefined) &
            (this.form.createTime !== undefined)
          ) {
            updateclass(this.moduleSubmmitFormatData(this.form, "module")).then(
              (response) => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            );
          } else {
            this.moduleAddFormatData("module");
            addclass(this.form).then((response) => {
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
      const vehicleTypeIds = row.id || this.vehicleTypeIds;
      this.$modal
        .confirm('是否确认删除车辆编号为"' + vehicleTypeIds + '"的数据项？')
        .then(function () {
          return delclass(vehicleTypeIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/post/export",
        {
          ...this.queryParams,
        },
        `post_${new Date().getTime()}.xlsx`
      );
    },
    /**
     * 图文特性操作部分
     */
    handleImageAndTextInfo(row) {
      this.imgAndTextLoading = true;
      this.vehicleClassId = row.id;
      listImgAndText({ vehicleClassId: row.id }).then((response) => {
        this.imgAndTextList = response.data;
        this.openImageAndText = true;
        this.title = "图文特性";
        this.imgAndTextLoading = false;
      });
    },
    handleImageAndTextUpdate(row) {
      this.reset();
      const vehicleTypeId = row.id || this.vehicleTypeIds;
      getImgAndText(vehicleTypeId).then((response) => {
        this.imageAndTextForm = this.moduleButtonFormatData(
          response.data,
          "imgAndText"
        );
        this.openImageAndTextInfo = true;
        this.title = "修改图文特性";
      });
    },
    handleImageAndTextAdd() {
      this.reset();
      this.openImageAndTextInfo = true;
      this.title = "添加图文特性";
    },
    getImageAndTextList() {
      listImgAndText({ vehicleClassId: this.vehicleClassId }).then(
        (response) => {
          this.imgAndTextList = response.data;
        }
      );
    },
    submitImageAndTextForm() {
      this.$refs["imageAndTextForm"].validate((valid) => {
        if (valid) {
          if (
            (this.imageAndTextForm.vehicleClassId !== undefined) &
            (this.imageAndTextForm.createTime !== undefined)
          ) {
            updateImgAndText(
              this.moduleSubmmitFormatData(this.imageAndTextForm, "imgAndText")
            ).then(() => {
              this.$modal.msgSuccess("修改成功");
              this.openImageAndTextInfo = false;
              this.getImageAndTextList();
            });
          } else {
            this.moduleAddFormatData("imgAndText");
            addImgAndText(this.imageAndTextForm).then(() => {
              this.$modal.msgSuccess("新增成功");
              this.openImageAndTextInfo = false;
              this.getImageAndTextList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleImageAndTextDelete(row) {
      const id = row.id;
      this.$modal
        .confirm('是否确认删除车辆图文特性编号为"' + row.id + '"的数据项？')
        .then(function () {
          return delImgAndText(id);
        })
        .then(() => {
          this.getImageAndTextList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /**
     * 图文轮播操作部分
     */
    handleImageAndTextListInfo(row) {
      this.imgAndTextListLoading = true;
      this.vehicleClassId = row.id;
      listImgAndTextList({ vehicleClassId: row.id }).then((response) => {
        this.imgAndTextListList = response.data;
        this.openImageAndTextList = true;
        this.title = "图文轮播";
        this.imgAndTextListLoading = false;
      });
    },
    handleImageAndTextListUpdate(row) {
      this.reset();
      const vehicleTypeId = row.id || this.vehicleTypeIds;
      getImgAndTextList(vehicleTypeId).then((response) => {
        this.imageAndTextListForm = this.moduleButtonFormatData(
          response.data,
          "imgAndTextList"
        );
        this.openImageAndTextListInfo = true;
        this.title = "修改图文轮播";
      });
    },
    handleImageAndTextListAdd() {
      this.reset();
      this.openImageAndTextListInfo = true;
      this.title = "添加图文轮播";
    },
    getImageAndTextListList() {
      listImgAndTextList({ vehicleClassId: this.vehicleClassId }).then(
        (response) => {
          this.imgAndTextListList = response.data;
        }
      );
    },
    submitImageAndTextListForm() {
      this.$refs["imageAndTextListForm"].validate((valid) => {
        if (valid) {
          if (
            (this.imageAndTextListForm.vehicleClassId !== undefined) &
            (this.imageAndTextListForm.createTime !== undefined)
          ) {
            updateImgAndTextList(
              this.moduleSubmmitFormatData(
                this.imageAndTextListForm,
                "imgAndTextList"
              )
            ).then(() => {
              this.$modal.msgSuccess("修改成功");
              this.openImageAndTextListInfo = false;
              this.getImageAndTextListList();
            });
          } else {
            this.moduleAddFormatData("imgAndTextList");
            addImgAndTextList(this.imageAndTextListForm).then(() => {
              this.$modal.msgSuccess("新增成功");
              this.openImageAndTextListInfo = false;
              this.getImageAndTextListList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleImageAndTextListDelete(row) {
      const id = row.id;
      this.$modal
        .confirm('是否确认删除车辆图文轮播编号为"' + row.id + '"的数据项？')
        .then(function () {
          return delImgAndTextList(id);
        })
        .then(() => {
          this.getImageAndTextListList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /**
     * 文本块操作部分
     */
    handleTextBlockInfo(row) {
      this.openTextBlockLoading = true;
      this.vehicleClassId = row.id;
      listTextBlock({ vehicleClassId: row.id }).then((response) => {
        this.textBlockList = response.data;
        this.openTextBlock = true;
        this.title = "文本块特性";
        this.openTextBlockLoading = false;
      });
    },
    handleTextBlockUpdate(row) {
      this.reset();
      const vehicleTypeId = row.id || this.vehicleTypeIds;
      getTextBlock(vehicleTypeId).then((response) => {
        this.textBlockForm = this.moduleButtonFormatData(
          response.data,
          "textBlock"
        );
        this.openTextBlockInfo = true;
        this.title = "修改文本块特性";
      });
    },
    handleTextBlockAdd() {
      this.reset();
      this.openTextBlockInfo = true;
      this.title = "添加文本块特性";
    },
    getTextBlockList() {
      listTextBlock({ vehicleClassId: this.vehicleClassId }).then(
        (response) => {
          this.textBlockList = response.data;
        }
      );
    },
    submitTextBlockForm() {
      this.$refs["textBlockForm"].validate((valid) => {
        if (valid) {
          if (
            (this.textBlockForm.vehicleClassId !== undefined) &
            (this.textBlockForm.createTime !== undefined)
          ) {
            updateTextBlock(
              this.moduleSubmmitFormatData(this.textBlockForm, "textBlock")
            ).then(() => {
              this.$modal.msgSuccess("修改成功");
              this.openTextBlockInfo = false;
              this.getTextBlockList();
            });
          } else {
            this.moduleAddFormatData("textBlock");
            addTextBlock(this.textBlockForm).then(() => {
              this.$modal.msgSuccess("新增成功");
              this.openTextBlockInfo = false;
              this.getTextBlockList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleTextBlockDelete(row) {
      const id = row.id;
      this.$modal
        .confirm('是否确认删除车辆文本块特性编号为"' + row.id + '"的数据项？')
        .then(function () {
          return delTextBlock(id);
        })
        .then(() => {
          this.getTextBlockList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /**
     * 相册操作部分
     */
    handleGalleryInfo(row) {
      this.openGalleryLoading = true;
      this.vehicleClassId = row.id;
      listGallery({ vehicleClassId: row.id }).then((response) => {
        response.data.map((value, key) => {
          const imgUrl = value.imgUrl.split(",");
          if (imgUrl.length > 0) {
            response.data[key].imgUrl = imgUrl;
          }
        });
        this.galleryList = response.data;
        this.openGallery = true;
        this.title = "相册";
        this.openGalleryLoading = false;
      });
    },
    handleGalleryUpdate(row) {
      this.reset();
      const vehicleTypeId = row.id || this.vehicleTypeIds;
      getGallery(vehicleTypeId).then((response) => {
        this.galleryForm = response.data;
        this.openGalleryInfo = true;
        this.title = "修改相册";
      });
    },
    handleGalleryAdd() {
      this.reset();
      this.openGalleryInfo = true;
      this.title = "添加相册";
    },
    getGalleryList() {
      listGallery({ vehicleClassId: this.vehicleClassId }).then((response) => {
        response.data.map((value, key) => {
          const imgUrl = value.imgUrl.split(",");
          if (imgUrl.length > 0) {
            response.data[key].imgUrl = imgUrl;
          }
        });
        this.galleryList = response.data;
      });
    },
    submitGalleryForm() {
      this.$refs["galleryForm"].validate((valid) => {
        if (valid) {
          if (
            (this.galleryForm.vehicleClassId !== undefined) &
            (this.galleryForm.createTime !== undefined)
          ) {
            updateGallery(this.galleryForm).then(() => {
              this.$modal.msgSuccess("修改成功");
              this.openGalleryInfo = false;
              this.getGalleryList();
            });
          } else {
            this.moduleAddFormatData("gallery");
            this.galleryForm.vehicleClassId = this.vehicleClassId;
            addGallery(this.galleryForm).then(() => {
              this.$modal.msgSuccess("新增成功");
              this.openGalleryInfo = false;
              this.getGalleryList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleGalleryDelete(row) {
      const id = row.id;
      this.$modal
        .confirm('是否确认删除车辆文本块特性编号为"' + row.id + '"的数据项？')
        .then(function () {
          return delGallery(id);
        })
        .then(() => {
          this.getGalleryList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /**
     * 相册特性操作部分
     */
    handleGalleryCharacteristicInfo(row) {
      this.openGalleryCharacteristicLoading = true;
      this.vehicleClassId = row.id;
      listGalleryCharacteristic({ vehicleClassId: row.id }).then((response) => {
        response.data.map((value, key) => {
          const imgUrl = value.imgUrl.split(",");
          if (imgUrl.length > 0) {
            response.data[key].imgUrl = imgUrl;
          }
        });
        this.galleryCharacteristicList = response.data;
        this.openGalleryCharacteristic = true;
        this.title = "相册特性";
        this.openGalleryCharacteristicLoading = false;
      });
    },
    handleGalleryCharacteristicUpdate(row) {
      this.reset();
      const vehicleTypeId = row.id;
      getGalleryCharacteristic(vehicleTypeId).then((response) => {
        this.galleryCharacteristicForm = this.moduleButtonFormatData(
          response.data,
          "galleryCharacteristic"
        );
        this.openGalleryCharacteristicInfo = true;
        this.title = "修改相册特性";
      });
    },
    handleGalleryCharacteristicAdd() {
      this.reset();
      this.openGalleryCharacteristicInfo = true;
      this.title = "添加相册特性";
    },
    handleGalleryTitleAdd() {
      this.reset();
      this.openGalleryTitle = true;
      this.title = "添加相册特性标题";
    },
    handleGalleryTitleUpdate() {
      this.reset();
      getGalleryTitle({ vehicleClassId: this.vehicleClassId }).then(
        (response) => {
          this.galleryTitleForm = this.moduleButtonFormatData(
            response.data,
            "galleryTitle"
          );
          this.openGalleryTitle = true;
          this.title = "添加相册特性标题";
        }
      );
    },
    submitGalleryTitleForm() {
      this.$refs["galleryTitleForm"].validate((valid) => {
        if (valid) {
          if (
            (this.galleryTitleForm.vehicleClassId !== undefined) &
            (this.galleryTitleForm.createTime !== undefined)
          ) {
            updateGalleryTitle(
              this.moduleSubmmitFormatData(
                this.galleryTitleForm,
                "galleryTitle"
              )
            ).then(() => {
              this.$modal.msgSuccess("操作成功");
              this.openGalleryTitle = false;
            });
          } else {
            this.moduleAddFormatData("galleryTitle");
            updateGalleryTitle(this.galleryTitleForm).then(() => {
              this.$modal.msgSuccess("操作成功");
              this.openGalleryTitle = false;
            });
          }
        }
      });
    },
    getGalleryCharacteristicList() {
      listGalleryCharacteristic({ vehicleClassId: this.vehicleClassId }).then(
        (response) => {
          response.data.map((value, key) => {
            const imgUrl = value.imgUrl.split(",");
            if (imgUrl.length > 0) {
              response.data[key].imgUrl = imgUrl;
            }
          });
          this.galleryCharacteristicList = response.data;
        }
      );
    },
    submitGalleryCharacteristicForm() {
      this.$refs["galleryCharacteristicForm"].validate((valid) => {
        if (valid) {
          if (
            (this.galleryCharacteristicForm.vehicleClassId !== undefined) &
            (this.galleryCharacteristicForm.createTime !== undefined)
          ) {
            updateGalleryCharacteristic(
              this.moduleSubmmitFormatData(
                this.galleryCharacteristicForm,
                "galleryCharacteristic"
              )
            ).then(() => {
              this.$modal.msgSuccess("修改成功");
              this.openGalleryCharacteristicInfo = false;
              this.getGalleryCharacteristicList();
            });
          } else {
            this.moduleAddFormatData("galleryCharacteristic");
            addGalleryCharacteristic(this.galleryCharacteristicForm).then(
              () => {
                this.$modal.msgSuccess("新增成功");
                this.openGalleryCharacteristicInfo = false;
                this.getGalleryCharacteristicList();
              }
            );
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleGalleryCharacteristicDelete(row) {
      const id = row.id;
      this.$modal
        .confirm('是否确认删除车辆文本块特性编号为"' + row.id + '"的数据项？')
        .then(function () {
          return delGalleryCharacteristic(id);
        })
        .then(() => {
          this.getGalleryCharacteristicList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /**
     * 车辆颜色操作部分
     */
    handleColorInfo(row) {
      this.openColorLoading = true;
      this.vehicleClassId = row.id;
      listColor({ vehicleClassId: row.id }).then((response) => {
        this.colorList = response.data;
        this.openColor = true;
        this.title = "车辆颜色";
        this.openColorLoading = false;
      });
    },
    handleColorUpdate(row) {
      this.reset();
      getColor(row.id).then((res) => {
        this.colorForm = res.data;
        let colorList = res.data.rgbCode && res.data.rgbCode.split("/");
        let fields = ["rgbCode1", "rgbCode2", "rgbCode3", "rgbCode4"];
        fields.forEach((element, index) => {
          this.colorForm[element] = colorList[index];
        });
        this.openColorInfo = true;
        this.title = "修改车辆颜色";
      });
    },
    handleColorAdd() {
      this.reset();
      this.openColorInfo = true;
      this.title = "添加车辆颜色";
    },
    getColorList() {
      listColor({ vehicleClassId: this.vehicleClassId }).then((response) => {
        this.colorList = response.data;
      });
    },
    colorChange() {
      this.$nextTick(() => {
        this.$forceUpdate();
      });
    },
    handleRgbCode(from) {
      let rgbList = [];
      let fields = ["rgbCode1", "rgbCode2", "rgbCode3", "rgbCode4"];
      fields.forEach((field) => {
        from[field] && rgbList.push(from[field]);
      });
      return rgbList.join("/");
    },
    submitColorForm() {
      this.$refs["colorForm"].validate((valid) => {
        if (valid) {
          if (
            (this.colorForm.vehicleClassId !== undefined) &
            (this.colorForm.createTime !== undefined)
          ) {
            this.colorForm.rgbCode = this.handleRgbCode(this.colorForm);
            updateColor(this.colorForm).then(() => {
              this.$modal.msgSuccess("修改成功");
              this.openColorInfo = false;
              this.getColorList();
            });
          } else {
            this.colorForm.vehicleClassId = this.vehicleClassId;
            this.colorForm.rgbCode = this.handleRgbCode(this.colorForm);
            addColor(this.colorForm).then(() => {
              this.$modal.msgSuccess("新增成功");
              this.openColorInfo = false;
              this.getColorList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleColorDelete(row) {
      const id = row.id;
      this.$modal
        .confirm('是否确认删除车辆文本块特性编号为"' + row.id + '"的数据项？')
        .then(function () {
          return delColor(id);
        })
        .then(() => {
          this.getColorList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
  },
};
</script>
