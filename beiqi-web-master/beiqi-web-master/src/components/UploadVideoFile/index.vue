<template>
  <el-upload
    class="upload-demo"
    :action="uploadVideoUrl"
    :on-preview="handlePreview"
    :on-success="handleVideoSuccess"
    :before-upload="beforeVideoUpload"
    :headers="headers"
    :file-list="fileList"
    :on-exceed="handleExceed"
    :limit="limit">
    <el-button size="small" type="primary"><i class="el-icon-upload"></i>点击上传</el-button>
    <!-- <div class="el-upload__tip" slot="tip">只能上传mp4文件，且不超过1G</div> -->
    <div class="el-upload__tip" slot="tip" v-if="showTip">
      <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </template>
      <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b> </template>
      的文件
    </div>
  </el-upload>
</template>
<script>
import { getToken } from "@/utils/auth";

export default {
  name: "VideoUpload",
  props: {
    // 值
    value: [String, Object, Array],
    // 大小限制(MB)
    fileSize: {
      type: Number,
      default: 20,
    },
    fileType: {
      type: Array,
      default: () => ["mp4"],
    },
    // 是否显示提示
    isShowTip: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      uploadVideoUrl: null,
      limit: 1,
      fileList: []
    }
  },
  watch: {
    value: {
      handler(val) {
        if (val) {
          let temp = 1;
          // 首先将值转为数组
          const list = Array.isArray(val) ? val : this.value.split(',');
          // 然后将数组转为对象数组
          this.fileList = list.map(item => {
            if (typeof item === "string") {
              item = { name: item, url: item };
            }
            item.uid = item.uid || new Date().getTime() + temp++;
            return item;
          });
        } else {
          this.fileList = [];
          return [];
        }
      },
      deep: true,
      immediate: true
    }
  },
  computed: {
    // 是否显示提示
    showTip() {
      return this.isShowTip && (this.fileType || this.fileSize);
    },
  },
  created() {
    this.uploadVideoUrl =process.env.VUE_APP_BASE_API + '/media/movie/upload/video'
  },
   methods: {
    handleVideoSuccess(res, file) {
      const code = res.code;
      if (code === 200) {
        // console.log(res, 'resVIDEO')
        this.$emit("input", res.data.url);
        this.$modal.msgSuccess("上传成功！");
      } else {
        this.$modal.msgError(res.msg);
      }
    },
    beforeVideoUpload(file) {
      // console.log(file.type);
      const isLt1G = file.size / 1024 / 1024  < this.fileSize;

      if (this.fileType) {
        let fileExtension = ""
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1)
        }
        const isTypeOk = this.fileType.some((type) => {
          if (file.type.indexOf(type) > -1) return true;
          if (fileExtension && fileExtension.indexOf(type) > -1) return true
          return false
        });
        if (!isTypeOk) {
          this.$modal.msgError(`文件格式不正确, 请上传${this.fileType.join("/")}格式文件!`)
          return false
        }
      }

      if (!isLt1G) {
        this.$modal.msgError(`上传视频文件大小不能超过${this.fileSize}MB!`)
        return false
      }
      return true;
    },
    // 文件个数超出
    handleExceed() {
      this.$modal.msgError(`上传文件数量不能超过 ${this.limit} 个!`);
    },
     handlePreview(file) {
       window.open(process.env.VUE_APP_BASE_API+'/file'+file.url,'_blank')
     }
   },
}
</script>
<style>
  .upload-demo .el-upload-list__item-name{
    margin-right: 110px;
  }
</style>
