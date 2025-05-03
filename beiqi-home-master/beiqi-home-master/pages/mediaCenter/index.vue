<template>
  <div class="media-center-page page-main">
    <div class="banner">
      <img src="~/assets/image/mediaCenter/banner.jpg" alt="" srcset="" />
    </div>
    <div class="page-main max-1200">
      <div class="header">
        <h1 class="h1 title">{{ $t('mediaCenter.header.title') }}</h1>
        <p>
          {{ $t('mediaCenter.header.content1') }} <br />
          {{ $t('mediaCenter.header.content2') }}
        </p>
      </div>
      <div class="form-box">
        <el-form :model="mdeiaContactForm" :rules="rules" ref="mdeiaContactForm" :label-position="labelPosition">
          <el-form-item :label="$t('form.label.Gender')" prop="gender">
            <el-select v-model="mdeiaContactForm.gender" :placeholder="$t('placeholder.Gender')">
              <el-option v-for="item in genderOption" :key="item.value" :label="item.label" :value="item.value"> </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :label="$t('form.label.FirstName')" prop="firstName">
            <el-input v-model="mdeiaContactForm.firstName" :maxlength="50" :placeholder="$t('placeholder.FirstName')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.LastName')" prop="lastName">
            <el-input v-model="mdeiaContactForm.lastName" :maxlength="50" :placeholder="$t('placeholder.LastName')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.MediaName')" prop="mediaName">
            <el-input v-model="mdeiaContactForm.mediaName" :maxlength="90" :placeholder="$t('placeholder.MediaName')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.Email')" prop="email">
            <el-input type="email" v-model="mdeiaContactForm.email" :maxlength="50" :placeholder="$t('placeholder.Email')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.Message')" prop="msg">
            <el-input type="textarea" maxlength="2000" v-model="mdeiaContactForm.msg" rows="10" :placeholder="$t('placeholder.Message')"></el-input>
          </el-form-item>
          <el-form-item class="form-desc">
            <p>{{ $t('mediaCenter.form.desc') }}</p>
          </el-form-item>
        </el-form>
        <div class="bottom-btn">
          <div class="btn" @click="submitForm('contactUsForm')">{{ $t('form.btn.SEND') }}</div>
        </div>
      </div>
      <successDialog :show="open" @dialog-close="open = false" />
    </div>
  </div>
</template>

<script>
import successDialog from '@/components/successDialog'
export default {
  name: 'MediaCenter',
  components: { successDialog },
  layout: 'main',
  data() {
    return {
      mdeiaContactForm: {
        gender: this.$t('genderOption.Mr'),
      },
      genderOption: [
        {
          value: this.$t('genderOption.Mr'),
          label: this.$t('genderOption.Mr'),
        },
        {
          value: this.$t('genderOption.Miss'),
          label: this.$t('genderOption.Miss'),
        },
        {
          value: this.$t('genderOption.Ms'),
          label: this.$t('genderOption.Ms'),
        },
        {
          value: this.$t('genderOption.Dr'),
          label: this.$t('genderOption.Dr'),
        },
        {
          value: this.$t('genderOption.others'),
          label: this.$t('genderOption.others'),
        },
      ],
      rules: {
        firstName: [{ required: true, message: this.$t('form.label.FirstName') + this.$t('form.required.same'), trigger: 'blur' }],
        lastName: [{ required: true, message: this.$t('form.label.LastName') + this.$t('form.required.same'), trigger: 'blur' }],
        mediaName: [{ required: true, message: this.$t('form.label.MediaName') + this.$t('form.required.same'), trigger: 'blur' }],
        email: [
          { required: true, message: this.$t('form.label.Email') + this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.emailValidator, trigger: 'blur' },
        ],
        msg: [{ required: true, message: this.$t('form.label.Message') + this.$t('form.required.same'), trigger: 'blur' }],
      },
      open: false,
      labelPosition: 'right',
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(value, oldValue) {
        if (value === 'xs') {
          this.labelPosition = 'top'
        } else {
          this.labelPosition = 'right'
        }
      },
    },
  },
  methods: {
    emailValidator(rule, value, callback) {
      if (value && value !== '') {
        const reg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (reg.test(value)) {
          callback()
        } else {
          callback(this.$t('form.label.Email') + this.$t('form.error.same'))
        }
      } else {
        callback(this.$t('form.label.Email') + this.$t('form.error.same'))
      }
    },
    submitForm() {
      this.$refs['mdeiaContactForm'].validate(valid => {
        if (valid) {
          this.mdeiaContactForm.lang = this.$i18n.locale
          let loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)',
          })
          this.$api.contact
            .mediaContact(this.mdeiaContactForm)
            .then(() => {
              this.open = true
              loading.close()
            })
            .catch(() => {
              loading.close()
            })
        } else {
          return false
        }
      })
    },
  },
}
</script>

<style lang="scss" scoped>
.media-center-page {
  margin-bottom: 60px;
  .banner {
    //position: relative;
    //padding-bottom: 36.46%;
    img {
      //position: absolute;
      //top: 0;
      //left: 0;
      //object-fit:cover;
      width: 100%;
      margin: 0 auto;
    }
  }
}
.page-main {
  .header {
    text-align: center;
    margin-top: 100px;
    .h1 {
      margin-bottom: 35px;
      font-size: 28px;
    }
    p {
      font-size: 16px;
      line-height: 28px;
    }
  }
  .form-box {
    margin-top: 100px;
  }
  .bottom-btn {
    text-align: center;
    .btn {
      cursor: pointer;
    }
  }
}
.xs,
.sm,
.md {
  .page-main {
    .header {
      text-align: center;
      margin-top: 30px;
      .h1 {
        margin-bottom: 15px;
        font-size: 24px;
      }
      p {
        font-size: 14px;
        line-height: 24px;
      }
    }
    .form-box {
      margin-top: 30px;
    }
    .bottom-btn {
      text-align: center;
      p {
        margin: 30px 0 10px;
      }
      .btn {
        cursor: pointer;
      }
    }
  }
}
</style>
