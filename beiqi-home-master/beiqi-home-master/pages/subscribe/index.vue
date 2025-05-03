<template>
  <div class="media-center-page">
    <div class="banner">
      <img src="~/assets/image/subscribe/banner.jpg" alt="" srcset="" />
    </div>
    <div class="page-main max-1200">
      <div class="header">
        <h1 class="h1 title">{{ $t('subscribe.header.title') }}</h1>
        <p>{{ $t('subscribe.header.content1') }}</p>
      </div>
      <div class="form-box">
        <div class="form-header">
          <h1 class="h1 form-title">{{ $t('subscribe.form.header.title') }}</h1>
        </div>
        <el-form :model="subscribeForm" :rules="rules" ref="subscribeForm" :label-position="labelPosition">
          <el-form-item :label="$t('form.label.FirstName')" prop="firstName">
            <el-input v-model="subscribeForm.firstName" :maxlength="50" :placeholder="$t('placeholder.FirstName')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.LastName')" prop="lastName">
            <el-input v-model="subscribeForm.lastName" :maxlength="50" :placeholder="$t('placeholder.LastName')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.MediaName')" prop="mediaName">
            <el-input v-model="subscribeForm.mediaName" :maxlength="90" :placeholder="$t('placeholder.MediaName')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.Email')" prop="email">
            <el-input type="email" v-model="subscribeForm.email" :maxlength="50" :placeholder="$t('placeholder.Email')"></el-input>
          </el-form-item>
          <el-form-item :label="$t('form.label.MediaIntroduction')" prop="mediaIntroduction">
            <el-input type="textarea" maxlength="600" v-model="subscribeForm.mediaIntroduction" rows="10" :placeholder="$t('placeholder.MediaIntroduction')"></el-input>
          </el-form-item>
        </el-form>
        <div class="bottom-btn">
          <div class="btn" @click="submitForm('contactUsForm')">{{ $t('form.btn.SUBSCRIBE') }}</div>
        </div>
      </div>
      <successDialog :show="open" @dialog-close="open = false" :msg="$t('successDialog.Text2')" />
    </div>
  </div>
</template>

<script>
import successDialog from '@/components/successDialog'
export default {
  name: 'SubscribeName',
  components: { successDialog },
  layout: 'main',
  data() {
    return {
      subscribeForm: {},
      rules: {
        firstName: [{ required: true, message: this.$t('form.label.FirstName') + this.$t('form.required.same'), trigger: 'blur' }],
        lastName: [{ required: true, message: this.$t('form.label.LastName') + this.$t('form.required.same'), trigger: 'blur' }],
        mediaName: [{ required: true, message: this.$t('form.label.MediaName') + this.$t('form.required.same'), trigger: 'blur' }],
        email: [
          { required: true, message: this.$t('form.label.Email') + this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.emailValidator, trigger: 'blur' },
        ],
        mediaIntroduction: [{ required: true, message: this.$t('form.label.MediaIntroduction') + this.$t('form.required.same'), trigger: 'blur' }],
      },
      open: false,
      labelPosition: 'left',
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
        callback(this.$t('form.error.Email') + this.$t('form.error.same'))
      }
    },
    submitForm() {
      this.$refs['subscribeForm'].validate(valid => {
        if (valid) {
          this.subscribeForm.lang = this.$i18n.locale
          let loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)',
          })
          this.$api.contact
            .subscribeContact(this.subscribeForm)
            .then(res => {
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
// 这个表单标题比别的长一些
::v-deep .el-form--label-right .el-form-item .el-form-item__label {
  flex: 0 0 200px;
}

.media-center-page {
  margin-bottom: 60px;
  .banner {
    img {
      width: 100%;
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
      white-space: pre-line;
    }
  }
  .form-box {
    margin-top: 80px;
    .form-header {
      margin-top: 80px;
      text-align: center;
      margin-bottom: 50px;
      .form-title {
        font-size: 28px;
        margin-bottom: 15px;
      }
      p {
        font-size: 16px;
        line-height: 30px;
      }
    }
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
        line-height: 20px;
      }
    }
    .form-box {
      margin-top: 30px;
      .form-header {
        margin-top: 30px;
        text-align: center;
        margin-bottom: 30px;

        .form-title {
          font-size: 24px;
          margin-bottom: 15px;
        }
      }
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
