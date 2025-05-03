<template>
  <div v-show="isVisible" class="reset-container">
    <div class="reset-content">
      <div class="reset-title">{{ $t('distributor.changePassword') }}</div>
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
        <el-form-item :label="$t('distributor.NewPassword')" prop="password">
          <el-input v-model="form.password" :placeholder="$t('distributor.enterPassword')" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item :label="$t('distributor.ReEnterPassword')" prop="repeatPassword">
          <el-input v-model="form.repeatPassword" :placeholder="$t('distributor.repeatPassword')" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <el-form-item :label="$t('distributor.emailAddress')" prop="email">
          <el-input v-model="form.email" :placeholder="$t('distributor.email')" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item :label="$t('distributor.verificationCode')" prop="code">
          <div class="code">
            <el-input v-model="form.code" :placeholder="$t('distributor.verification')"></el-input>
            <el-button :disabled="countdown > 0" @click="sendVerificationCode">
              <span v-if="countdown > 0">{{ $t('distributor.obtainVerificationCode') }}{{ `(${convertSecondsToMinutes(countdown).minutes}m${convertSecondsToMinutes(countdown).seconds}s)` }}</span>
              <span v-else>{{ $t('distributor.obtainVerificationCode') }}</span>
            </el-button>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button :loading="loading" class="change-btn" type="primary" @click="changePassword">
            {{ $t('distributor.changePassword') }}
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { removeToken } from '~/utils/token'
import { checkToken } from '~/middleware/auth'
import {removeStorage} from "~/utils/storage";

export default {
  name: 'DistributorReset',
  layout: 'main',
  middleware: 'auth',
  meta: {
    requireAuth: true,
  },
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
        if (this.form.repeatPassword !== '') {
          this.$refs.form.validateField('repeatPassword')
        }
        callback()
      }
    }
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(this.$t('distributor.repeatPassword')))
      } else if (value !== this.form.password) {
        callback(new Error(this.$t('distributor.DoesNot')))
      } else {
        callback()
      }
    }
    return {
      form: {
        email: '',
        password: '',
        repeatPassword: '',
      },
      formRules: {
        email: [
          { required: true, trigger: 'blur', message: this.$t('distributor.email') },
          {
            pattern: '^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$',
            trigger: 'blur',
            message: this.$t('distributor.validEmail'),
          },
        ],
        password: [{ validator: validatePass, trigger: 'blur' }],
        repeatPassword: [{ validator: validatePass2, trigger: 'blur' }],
        code: [{ required: true, trigger: 'blur', message: this.$t('distributor.verification') }],
      },
      loading: false,
      countdown: 0,
      isVisible: false,
    }
  },

  mounted() {
    this.isVisible = checkToken()
  },

  activated() {
    this.isVisible = checkToken()
  },

  methods: {
    changePassword() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const params = {
            newPassword: this.form.password,
            repassword: this.form.repeatPassword,
            email: this.form.email,
            code: this.form.code,
          }
          this.loading = true
          // 重置密码
          this.$api.distributor
            .resetPassword(params)
            .then(res => {
              this.loading = false
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
                // 跳转到登录页
                this.$router.push({ name: `distributorCenter-login___${this.$i18n.locale}`, params: { page: 'reset' } })
              } else {
                this.$message.error(res.msg)
                return false
              }
            })
            .catch(error => {
              console.log(error, '错误')
            })
            .finally(() => {
              this.loading = false
            })
        }
      })
    },
    sendVerificationCode() {
      const fields = ['password', 'repeatPassword', 'email']
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
          this.$api.distributor.sendCode({ email: this.form.email }).then(res => {
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
        })
        .catch(errorMessage => {
          // 有字段没有通过校验
          console.error(errorMessage)
        })
      this.$refs.form.clearValidate(['code']) // 清除不需要的校验结果
      // 发送验证码的逻辑
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
.reset-container {
  display: flex;
  justify-content: center;
  align-items: center;
  //min-height: 100vh;
  padding: 6% 0;
  background: url('~assets/image/distributor-center/login.jpg') no-repeat center center;
  background-size: cover;

  .reset-content {
    margin-top: 6%;
    //width: 40%;
    padding: 30px 20px;
    background: #ffffff;
    border-radius: 20px;
  }

  .reset-title {
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
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 20px;

    .is-error {
      .el-input__inner {
        border-color: #f56c6c !important;
      }

      .el-input__suffix {
        display: none;
      }
    }

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

    .code {
      display: flex;

      .el-button {
        margin-left: 15px;
      }
    }

    .el-form-item {
      width: 100%;
      margin-bottom: 10px;
    }

    .el-button {
      width: 100%;
      padding: 16px 20px;
      background-color: #d12619;
      border-radius: 12px;
      font-size: 18px;
      color: #ffffff;
      border: none;
      cursor: pointer;
      //border: none;
      //&:hover {
      //  background: #D1291A;
      //  color: #fff;
      //}
    }

    .change-btn {
      width: 100%;
      border: none;
      margin-top: 25px;

      &:hover {
        background: #d1291a;
        color: #fff;
      }
    }

    .el-form-item__label {
      padding: 0;
    }
  }

  @media only screen and (max-width: 768px) {
    height: auto;
    .reset-content {
      width: 90%;
      margin: 10% 0;
    }
    :deep .el-form {
      .el-input__inner {
        padding: 0 5px;
        font-size: 12px;
        background: none;
        border: 1px solid #cacaca;
      }

      .code {
        .el-input {
          width: 86%;
        }

        .el-button {
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
