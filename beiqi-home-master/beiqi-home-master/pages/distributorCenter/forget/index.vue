<template>
  <div class="forget-container">
    <div class="forget-content">
      <div class="forget-title">{{ $t('distributor.reset') }}</div>
      <!--      <div class="forget-title">{{ $t('distributor.changePassword') }}</div>-->
      <section class="desc">
        <div class="title">{{ $t('distributor.inOrderTo') }}</div>
        <ul class="content">
          <li>
            <span>{{ $t('distributor.characters') }}</span>
          </li>
          <li>
            <span>{{ $t('distributor.DoesNot') }}</span>
          </li>
        </ul>
      </section>
      <el-form ref="form" :hide-required-asterisk="true" :model="form" :rules="formRules" :status-icon="true" class="form" label-position="top">
        <el-form-item :label="$t('distributor.yourAccount')" prop="userName">
          <el-input v-model="form.userName" :placeholder="$t('distributor.account')" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item :label="$t('distributor.NewPassword')" prop="password">
          <el-input v-model="form.password" :placeholder="$t('distributor.enterPassword')" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item :label="$t('distributor.ReEnterPassword')" prop="repassword">
          <el-input v-model="form.repassword" :placeholder="$t('distributor.repeatPassword')" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item :label="$t('distributor.emailAddress')" prop="email">
          <el-input ref="input" v-model="form.email" :placeholder="$t('distributor.email')" :suffix-icon="showRight ? 'el-icon-check' : ''"></el-input>
        </el-form-item>
        <el-form-item :label="$t('distributor.verificationCode')" prop="code">
          <div class="code">
            <el-input v-model="form.code" :placeholder="$t('distributor.verification')"></el-input>
            <el-button :disabled="countdown > 0" class="btn" @click="sendVerificationCode">
              <span v-if="countdown > 0">{{ $t('distributor.obtainVerificationCode') }}{{ `(${convertSecondsToMinutes(countdown).minutes}m${convertSecondsToMinutes(countdown).seconds}s)` }}</span>
              <span v-else>{{ $t('distributor.obtainVerificationCode') }}</span>
            </el-button>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button :loading="loading" class="btn submit-btn" type="primary" @click="changePassword">
            {{ $t('distributor.changePassword') }}
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { removeToken } from '~/utils/token'
import { removeStorage } from '~/utils/storage'

export default {
  name: 'DistributorForget',
  layout: 'main',
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(this.$t('distributor.enterPassword')))
      } else {
        // 密码长度至少为8个字符
        if (value.length < 7) {
          callback(new Error(this.$t('distributor.passwordLength')))
          return
        }
        // 密码包含至少一个大写字母、一个小写字母和一个数字
        // if (!/[A-Z]/.test(value) || !/[a-z]/.test(value) || !/[0-9]/.test(value)) {
        //   callback(new Error('密码必须包含至少一个大写字母、一个小写字母和一个数字'));
        //   return;
        // }
        // 密码可以包含特殊字符，例如 !@#$%^&*
        // if (!/[!@#$%^&*]/.test(value)) {
        //   callback(new Error('密码可以包含特殊字符，例如 !@#$%^&*'));
        //   return;
        // }
        if (this.form.repassword !== '') {
          this.$refs.form.validateField('repassword')
        }
        callback()
      }
    }
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(this.$t('distributor.repeatPassword')))
      } else if (value !== this.form.password) {
        callback(new Error(this.$t('distributor.passwordMatch')))
      } else {
        callback()
      }
    }
    return {
      form: {
        userName: '',
        email: '',
        password: '',
        repassword: '',
        code: '',
      },
      countdown: 0,
      formRules: {
        userName: [{ required: true, trigger: 'blur', message: this.$t('distributor.account') }],
        email: [
          { required: true, trigger: 'blur', message: this.$t('distributor.email') },
          {
            pattern: '^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$',
            trigger: 'blur',
            message: this.$t('distributor.validEmail'),
          },
        ],
        password: [{ validator: validatePass, trigger: 'blur' }],
        repassword: [{ validator: validatePass2, trigger: 'blur' }],
        code: [{ required: true, trigger: 'blur', message: this.$t('distributor.verification') }],
      },
      captchaOnOff: true,
      codeUrl: '',
      showError: false,
      showRight: false,
      remain: {},
      loading: false,
    }
  },

  computed: {},

  mounted() {},

  methods: {
    changePassword() {
      // 更改成功,清除cookie和用户信息,跳到首页
      this.$refs.form.validate(valid => {
        if (valid) {
          this.loading = true
          // 重置密码
          this.$api.distributor
            .RetrievePassword(this.form)
            .then(res => {
              const prePath = this.$i18n.locale === this.$i18n.defaultLocale ? '' : '/' + this.$i18n.locale
              // 重置成功，清除本地token和用户信息，跳到首页
              if (res.code === 200) {
                this.$message({
                  message: this.$t('distributor.updateSuccess'),
                  type: 'success',
                })
                removeToken()
                // localStorage.removeItem('user')
                removeStorage('user')
                // 重置表单
                this.$refs.form.resetFields()
                // 重置倒计时时间
                this.countdown = 0
                // 跳转到登录页面
                this.$router.push(prePath + '/distributorCenter/login')
              } else {
                this.$message.error(res.msg)
                return false
              }
            })
            .catch(error => {
              console.log(error, 'error')
            })
            .finally(() => {
              this.loading = false
            })
        }
      })
    },
    // 获取验证码
    sendVerificationCode() {
      const fields = ['userName', 'password', 'repassword', 'email']
      const promises = fields.map(
        field =>
          new Promise((resolve, reject) => {
            this.$refs.form.validateField(field, errorMessage => {
              if (errorMessage) {
                reject(errorMessage)
              } else {
                resolve()
              }
            })
          })
      )
      Promise.all(promises)
        .then(() => {
          // 发送验证码的逻辑
          this.$api.distributor
            .sendCode({ email: this.form.email })
            .then(res => {
              // 开始倒计时
              this.countdown = 60 * 3
              const timer = setInterval(() => {
                if (this.countdown > 0) {
                  this.countdown--
                } else {
                  clearInterval(timer)
                }
              }, 1000)
            })
            .catch(error => {
              console.log(error)
            })
        })
        .catch(errorMessage => {
          // 有字段没有通过校验
          console.error(errorMessage)
        })
      this.$refs.form.clearValidate(['code']) // 清除不需要的校验结果
    },
    convertSecondsToMinutes(seconds) {
      const minutes = Math.floor(seconds / 60)
      const remainingSeconds = seconds % 60
      return { minutes, seconds: remainingSeconds }
    },
  },
}
</script>

<style lang="scss" scoped>
.forget-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  //height: 100vh;
  background: url('~assets/image/distributor-center/login.jpg') no-repeat center center;
  background-size: cover;
  //position: relative;
  //z-index: -1;

  .forget-content {
    margin: 10% 0;
    //width: 30%;
    padding: 30px 20px;
    background: #ffffff;
    border-radius: 20px;
  }

  .forget-title {
    text-align: center;
    font-size: 18px;
    font-weight: 700;
  }

  .desc {
    margin-top: 20px;
    //width: 100%;
    font-size: 16px;

    .content {
      margin: 15px 0;

      li {
        position: relative;
        color: #6e7275;
        padding-left: 30px;

        &:after {
          content: '';
          display: inline-block;
          position: absolute;
          left: 18px;
          top: 50%;
          margin-top: -2.5px;
          width: 5px;
          height: 5px;
          border-radius: 50%;
          background: #6e7275;
        }
      }
    }
  }

  :deep .form {
    margin-top: 18px;
    width: 100%;

    .el-form-item--feedback {
      .el-icon-circle-check {
        color: #6ae631;
        font-weight: 700;
        font-size: 18px;

        &:before {
          content: '\e6da';
        }
      }
    }

    .is-error {
      .el-input__inner {
        border-color: #f56c6c !important;
      }

      .el-input__suffix {
        display: none;
      }
    }

    .el-icon-check {
      color: #6ae631;
      font-weight: 700;
      font-size: 18px;
    }

    .el-form-item {
      margin-bottom: 10px;

      .el-input__inner {
        background: none;
        border: 1px solid #cacaca;
      }

      .code {
        display: flex;

        .el-button {
          margin-left: 15px;
        }
      }

      .btn {
        width: 100%;
        padding: 16px 20px;
        background-color: #d12619;
        border-radius: 12px;
        font-size: 18px;
        color: #ffffff;
        border: none;
        cursor: pointer;
      }

      .submit-btn {
        margin-top: 21px;
      }
    }

    .el-form-item__label {
      padding: 0;
    }
  }
  
  @media only screen and (max-width: 768px) {
    height: auto;
    background-size: auto !important;
    padding: 0;
    .forget-content {
      //width: 90%;
      margin: 50px 30px;
    }
    :deep .el-form {
      .el-input__inner {
        width: 100%;
        padding: 3px;
        font-size: 12px;
      }

      .code {
        .el-input {
          width: 86%;
        }

        .el-button.btn {
          width: 40% !important;
          padding: 0;
          font-size: 3vw;
          word-break: break-word;
          margin-left: 3px !important;
          white-space: normal;
        }
      }
    }
  }
}
</style>
