<template>
  <client-only>
    <div class="max-1200" style="margin-top: 80px; margin-bottom: 60px">
      <h2 class="section-title">{{ jobDetail.jobTitle }}</h2>
      <div class="location"><i class="el-icon-location"></i>{{ jobDetail.areaName }}</div>
      <el-divider></el-divider>
      <div class="g-html__wrapper">
        <h3>{{ $t('job.detail.contentTitle') }}</h3>
        <div v-html="jobDetail.describe"></div>
      </div>
      <button class="btn" @click="dialogVisible = true">{{ $t('job.detail.btnText') }}</button>
      <el-dialog class="join_us" :visible.sync="dialogVisible" width="80%">
        <el-form ref="form" :model="form" :rules="rules" :label-position="labelPosition">
          <el-form-item :label="$t('form.label.FullName')" prop="fullName">
            <el-input v-model="form.fullName" :maxlength="90" :placeholder="$t('placeholder.FullName')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.Nationality')" prop="nationality">
            <el-input v-model="form.nationality" :maxlength="90" :placeholder="$t('placeholder.Nationality')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.Email')" prop="email">
            <el-input v-model="form.email" type="email" :maxlength="50" :placeholder="$t('placeholder.Email')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.Phone')" prop="phone">
            <vue-tel-input v-model="form.phone" v-bind="bindProps" />
          </el-form-item>
          <el-form-item :label="$t('form.label.CurrentCountry')" prop="currCountry">
            <el-input v-model="form.currCountry" :maxlength="50" :placeholder="$t('placeholder.CurrentCountry')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.CurrentCompany')" prop="currCompany">
            <el-input v-model="form.currCompany" :maxlength="90" :placeholder="$t('placeholder.CurrentCompany')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('job.form.uploadfile')" prop="resumeUrl">
            <FileUpload v-model="form.resumeUrl" :limit="1" />
          </el-form-item>
          <el-form-item class="el-btn--submit">
            <el-button class="btn btn--small submit_btn" :loading="btnLoading" @click="submitForm('form')">{{ $t('form.btn.SUBMIT') }}</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <successDialog :show="open" @dialog-close="open = false" />
    </div>
  </client-only>
</template>

<script>
import FileUpload from '~/components/fileUpload'
import successDialog from '@/components/successDialog'
const VueTelInput = () =>
    Promise.all([
      import('vue-tel-input'),
      import('vue-tel-input/dist/vue-tel-input.css'),
    ]).then(([{ VueTelInput }]) => VueTelInput)

export default {
  components: { FileUpload, successDialog,VueTelInput },
  layout: 'main',
  data() {
    return {
      bindProps: {
        mode: 'international',
        maxLen: 25,
        inputOptions: {
          placeholder: this.$t('placeholder.Phone'),
          showDialCode: true,
          maxlength: 20,
          type: 'tel',
          name: 'telephone',
        },
        dropdownOptions: {
          showSearchBox: true,
          showFlags: true,
          showDialCodeInSelection: true,
          showDialCodeInList: true,
        },
      },
      btnLoading: false,
      jobDetail: {},
      open: false,
      dialogVisible: false,
      form: {
        fullName: '',
        nationality: '',
        email: '',
        phone: '',
        currCountry: '',
        currCompany: '',
        resumeUrl: '',
      },
      fileList: [],
      rules: {
        fullName: [{ required: true, message: this.$t('form.label.FullName') + this.$t('form.required.same'), trigger: 'blur' }],
        nationality: [{ required: true, message: this.$t('form.label.Nationality') + this.$t('form.required.same'), trigger: 'blur' }],
        email: [
          { required: true, message: this.$t('form.label.Email') + this.$t('form.required.same'), trigger: 'blur' },
          { pattern: /^([\w-.]+)@([\w-.]+)(\.[a-zA-Z0-9]+)$/, message: this.$t('form.label.Email') + this.$t('form.error.same') },
        ],
        phone: [
          { required: true, message: this.$t('form.label.Phone') + this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.phoneValidator, trigger: 'blur' },
        ],
        currCountry: [{ required: true, message: this.$t('form.label.CurrentCountry') + this.$t('form.required.same'), trigger: 'blur' }],
        currCompany: [{ required: true, message: this.$t('form.label.CurrentCompany') + this.$t('form.required.same'), trigger: 'blur' }],
        resumeUrl: [{ required: true, message: this.$t('job.form.fileRequired'), trigger: 'blur' }],
      },
      labelPosition: 'top',
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(value, oldValue) {
        if (value === 'xs') {
          this.labelPosition = 'top'
        } else {
          this.labelPosition = 'top'
        }
      },
    },
  },
  mounted() {
    const { id } = this.$route.params
    if (!id) {
      this.$router.push('/')
    } else {
      this.$api.job
        .jobDetail({ id })
        .then(res => {
          this.jobDetail = Object.assign({}, this.jobDetail, res.data)
        })
        .catch(() => {
          this.$router.push('/')
        })
    }
  },
  methods: {
    phoneValidator(rule, value, callback) {
      if (value) {
        const reg = /^[0-9+\s]*$/
        if (!reg.test(value)) {
          callback(new Error(this.$t('form.label.Phone') + this.$t('form.error.same')))
        } else {
          callback()
        }
      } else {
        callback(new Error(this.$t('form.label.Phone') + this.$t('form.error.same')))
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.btnLoading = true
          const params = {
            ...this.form,
            jobTitle: this.jobDetail.jobTitle,
            areaName: this.jobDetail.areaName,
            categoryName: this.jobDetail.categoryName,
          }
          this.$api.job
            .jobSubmit(params)
            .then(res => {
              this.dialogVisible = false
              this.open = true
            })
            .catch(err => {
              this.$message.error(err.msg || 'Request failed')
            })
            .finally(() => {
              this.btnLoading = false
            })
        } else {
          return false
        }
      })
    },
    handleChange(file, fileList) {
      this.fileList = fileList.slice(-3)
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
  },
}
</script>
<style lang="scss" scope>
.el-icon-location {
  font-size: 30px;
}
.location {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
}

.join_us {
  .el-dialog {
    max-width: 600px;
  }
}

@media only screen and (max-width: 768px) {
  .el-dialog {
    width: 90% !important;
  }
  .el-dialog__body {
    padding: 10px;
  }
  .el-input__inner {
    height: 40px;
    line-height: 40px;
  }

}
</style>
