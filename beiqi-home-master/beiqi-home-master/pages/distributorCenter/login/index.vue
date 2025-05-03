<template>
  <div v-show="isVisible" class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" :placeholder="$t('distributor.username')" class="user-input"
                  prefix-icon="iconfont icon-24gf-portraitMale3"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" :placeholder="$t('distributor.password')" class="password-input"
                  prefix-icon="iconfont icon-taocan-suoxiao" type="password"></el-input>
      </el-form-item>
      <el-form-item v-if="captchaOnOff" class="code-form-item" prop="code">
        <el-input ref="codeInput" v-model="loginForm.code" :placeholder="$t('distributor.verificationResult')"
                  class="code-input" prefix-icon="iconfont icon-yanzhengmashibie"
                  @keyup.enter.native="handleLogin"></el-input>
        <div class="login-code" :class="{'code-ar': $i18n.locale === 'ar'}">
          <img v-if="codeUrl" :src="codeUrl" class="login-code-img" @click="getCode"/>
          <div v-else class="load-text">{{ $t('distributor.loadingCode') }}</div>
          <i :title="$t('distributor.change')" class="el-icon-refresh-right" @click="getCode"></i>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button :icon="loading ? 'el-icon-loading' : 'iconfont icon-yuechi icon-key'" class="submit-btn"
                   type="primary" @click="handleLogin">
          {{ $t('distributor.logIn') }}
        </el-button>
<!--        <nuxt-link :to="localePath('/distributorCenter/forget')" class="forget">-->
<!--          {{ $t('distributor.forgotPassword') }}-->
<!--        </nuxt-link>-->
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {setToken} from '~/utils/token'
import {checkToken} from '~/middleware/auth'
import {setStorage} from "~/utils/storage"

export default {
  name: 'distributorLogin',
  layout: 'main',
  middleware: 'auth',
  meta: {
    requireAuth: true,
  },
  data() {
    const validateCode = (rule, value, callback) => {
      if (value === '' || /^[0-9]*$/.test(value)) {
        callback();
      } else {
        callback(new Error(this.$t('distributor.enterNumber')));
      }
    }
    return {
      loginForm: {
        username: '',
        password: '',
        code: null,
        uuid: '',
      },
      loginRules: {
        username: [{required: true, trigger: 'blur', message: this.$t('distributor.account')}],
        password: [{required: true, trigger: 'blur', message: this.$t('distributor.enterPassword')}],
        code: [{required: true, trigger: 'blur', message: this.$t('distributor.tipResult')},{validator: validateCode}],
      },
      captchaOnOff: true,
      codeUrl: '',
      loading: false,
      isVisible: false, //默认不显示
    }
  },

  mounted() {
    console.log(this.loginForm, 'mounted跳转')
    this.getCode()
    this.isVisible = checkToken()
  },

  activated() {
    console.log(this.$route)
    console.log(this.$route.params.page, 'params参数')
    // 重置密码页跳转到登录页，保留用户名
    if (!this.$route.params?.page) {
      this.loginForm.username = ''
    }
    // 重置表单
    this.loginForm.password = ''
    this.loginForm.code = ''
    this.loginForm.uuid = ''
    // 移除校验规则
    this.$refs.loginForm.clearValidate()
    console.log(this.loginForm, '进入页面')
    this.getCode()
    this.isVisible = checkToken()
  },

  methods: {
    async getCode() {
      this.$api.distributor.getCode().then(res => {
        this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff
        if (this.captchaOnOff) {
          this.codeUrl = 'data:image/gif;base64,' + res.img
          this.loginForm.uuid = res.uuid
        }
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.loginForm.username = this.loginForm.username.trim()
          this.$api.distributor
            .login(this.loginForm)
            .then(async res => {
              if (res.code == 200) {
                this.loading = false
                setToken(res.token)
                // 用户信息，存到本地
                this.getUserInfo(res.token)
              } else {
                this.loading = false
              }
            })
            .catch(() => {
              this.loading = false
              if (this.captchaOnOff) {
                this.getCode()
              }
            })
        }
      })
    },
    /**
     * 获取用户信息
     * @param token 登录令牌
     */
    getUserInfo(token) {
      const prePath = this.$i18n.locale == this.$i18n.defaultLocale ? '' : '/' + this.$i18n.locale
      this.$api.distributor.getInfo().then(res => {
        // 将token保存到本地存储
        // localStorage.setItem('user', JSON.stringify(res.user))
        setStorage('user', res.user)
        // 重新设置过期时间
        setToken(token)
        this.$router.push(prePath + `/distributorCenter`)
        // 首次，重置密码
        // if (!res.user?.pwdUpdateTime) {
        //   this.$router.push(prePath + `/distributorCenter/reset`)
        // } else {
        //   this.$router.push(prePath + '/distributorCenter')
        // }
      })
    },
  },
}
</script>

<style lang="scss" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 6% 0;
  background: url('~assets/image/distributor-center/login.jpg') no-repeat center center;
  background-size: cover;

  :deep .login-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    // width: 22.6%;
    border-radius: 10px;
    max-width: 30%;

    .el-form-item {
      width: 100%;
      margin: 0;
    }

    .el-input {
      // width: 434px;
      font-size: 18px;
      color: #d1d1d1;
      border-radius: 12px;

      .el-input__inner {
        font-size: 16px;
        padding-left: 50px;
        background: #fff;

        &::placeholder {
          text-align: center;
        }
      }
    }

    .el-form-item__error {
      top: 61%;
      left: 8%;
      z-index: 9;
    }

    .user-input {
      border-bottom: 2px solid #fff;
      border-image: linear-gradient(to right, #fff, #b0b0b0, #fff) 1;

      .el-input__inner {
        border-radius: 12px 12px 0 0;
      }
    }

    .password-input {
      .el-input__inner {
        border-radius: 0 0 12px 12px;
      }
    }

    .code-input {
      width: 60%;
    }

    .code-form-item {
      .el-form-item__content {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
      }
    }

    .login-code {
      display: flex;
      align-items: center;
      width: 50%;
      margin-left: 20px;

      .load-text {
        width: 78%;
        text-align: center;
        color: #606266;
      }

      img {
        width: 70%;
        height: 50px;
        cursor: pointer;
        vertical-align: middle;
      }

      .el-icon-refresh-right {
        margin-left: 15px;
        font-size: 23px;
        color: #fff;
        cursor: pointer;
      }
    }

    .code-ar {
      margin-left: 0;
      margin-right: 20px;
    }

    .submit-btn {
      width: 100%;
      //padding: 16px 20px;
      margin-top: 21px;
      background-color: #d12619;
      border-radius: 12px;
      border: none;
      cursor: pointer;
      font-size: 26px;
      color: #ffffff;
    }

    .forget {
      display: inline-block;
      color: #d32d4a;
      border-bottom: 1px solid #d32d4a;
      line-height: 20px;
      cursor: pointer;
    }

    .iconfont,
    .icon {
      margin-left: 5px;
      font-size: 28px;
      color: #000;
    }

    .el-input__icon {
      line-height: 50px;
    }

    .icon-key {
      color: #ffffff;
      opacity: 0.82;
      margin-right: 12px;
    }
  }

  @media only screen and (max-width: 1199px) {
    .login-form {
      max-width: 40%;
    }
  }

  @media only screen and (max-width: 768px) {
    :deep .login-form {
      max-width: 78%;

      .el-input__inner {
        padding-left: 35px !important;
        font-size: 14px !important;
      }

      .code-form-item {
        .el-form-item__error {
          &:last-child {
            top: 100%;
            left: 0;
          }
        }
      }

      .submit-btn {
        padding: 10px 15px;
        font-size: 28px;
      }

      .code-input {
        width: 65%;
      }

      .iconfont,
      .icon {
        font-size: 18px;
      }

      .login-code {
        margin-left: 10px;

        img {
          width: 85%;
        }

        .el-icon-refresh-right {
          margin-left: 3px;
        }
      }

      .icon-key {
        margin-right: 0;
      }

      .el-form-item__error {
        top: 65%;
        left: 3% !important;
      }

      .forget {
        padding-top: 10px;
      }
    }
  }
}
</style>
