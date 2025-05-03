<template>
  <transition name="fade">
    <div v-show="showCookies" class="d-mask agree">
      <div class="privacy">
        <div class="privacy-con">
          <div class="title">{{ $t('Your Privacy') }}</div>
          <div class="privacy-content">
            <span>{{
                $t('When accessing our website, we use cookies to ensure an optimal browsing experience for you. The use of cookies on this website may involve the collection of information pertaining to you, including your preferences and device details. This information typically does not directly identify you, but it can provide you a more tailored and personalized web browsing experience. Because we respect your right to privacy, we provide the option to decline certain types of cookies. Please click on the different category headings to find out more about or modify our default settings. However, please be aware that disabling certain cookies may affect your browsing experience on our website and limit the services we can provide.')
              }}</span>
            <svg class="privacy_all" height="200" p-id="2080" t="1634627278513" version="1.1" viewBox="0 0 1024 1024"
                 width="200" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                 @click="showDialog('privacy')">
              <path
                d="M512.6 136.4c-206.4 0-373.7 167.3-373.7 373.7s167.3 373.7 373.7 373.7 373.7-167.3 373.7-373.7S719 136.4 512.6 136.4z m35.2 573c0 8.8-7.2 15.9-15.9 15.9h-38.5c-8.8 0-15.9-7.2-15.9-15.9V453.3c0-8.8 7.2-15.9 15.9-15.9h38.5c8.8 0 15.9 7.2 15.9 15.9v256.1z m-35.2-344.2c-19.4 0-35.2-15.8-35.2-35.2 0-19.4 15.8-35.2 35.2-35.2 19.4 0 35.2 15.8 35.2 35.2 0 19.4-15.8 35.2-35.2 35.2z"
                fill="#999999" p-id="2081"/>
            </svg>
          </div>

          <div class="privacy_btn">
            <a class="privacy_agree option-btn" @click="agreePrivacy">{{ $t('Accept only necessary') }}</a>
            <a class="set_up option-btn" @click="showDialog('set')">{{ $t('Settings') }}</a>
            <a class="privacy_agree option-btn" @click="agreePrivacy">{{ $t('Accept all') }}</a>
          </div>
          <div class="paivacy_bottom">
            <a class="privacy-btn" href="./footer/privacyStatement">{{ $t('Privacy Statement') }}</a>
          </div>
        </div>
      </div>

      <cookie-dialog :dialogVisible="dialogVisible" @closeDialog="closeDialog">
        <!-- privacy-->
        <div v-show="currentType === 'privacy'" slot="content">
          <div class="title">{{ $t('Your Privacy') }}</div>
          <p class="privacy-content">
            {{
              $t('When accessing our website, we use cookies to ensure an optimal browsing experience for you. The use of cookies on this website may involve the collection of information pertaining to you, including your preferences and device details. This information typically does not directly identify you, but it can provide you a more tailored and personalized web browsing experience. Because we respect your right to privacy, we provide the option to decline certain types of cookies. Please click on the different category headings to find out more about or modify our default settings. However, please be aware that disabling certain cookies may affect your browsing experience on our website and limit the services we can provide.')
            }}
          </p>
          <p>
            {{
              $t('We will need to use a cookie to retain the preferences you have selected within our cookie privacy manager. This action will lead to several outcomes:')
            }}
          </p>
          <ul>
            <li class="bold">
              •
              {{ $t('If you delete all cookies, you will have to update your preferences with us again.') }}
            </li>
            <li class="bold">
              •
              {{ $t('If you use a different device or web browser, you will have to tell us your preferences again.') }}
            </li>
          </ul>
          <div class="data-notice">
            <div class="title-wrap">
              <div class="title">{{ $t('Data Protection Notice') }}</div>
              <div class="website">https://www.baicglobal.com</div>
            </div>
            <div class="container">
              <div class="theme-title">{{ $t('Data Controller') }}</div>
            </div>
            <p>
              {{
                $t('Located at 99 Shuanghe Street, Shunyi District, Beijing, China, BAIC (“we” or “our”) acts as the controller for the processing of your personal data. In this privacy statement, we will provide a detailed description of how we handle your personal data. We highly value your privacy and are committed to protecting your privacy.')
              }}
            </p>
            <div class="container">
              <div class="theme-title">{{ $t('Purpose and legal basis for processing') }}</div>
            </div>
            <p>
              {{
                $t('When accessing our website, we collect and process your IP address and browsing activity. The purpose of our processing is to identify you as a returning visitor to our website, analyze visitor behavior to improve website communication and structure, and create a profile of your interests for displaying relevant advertisements of our products and services on other websites. When possible, we may merge your online browsing activity data with the personal information you have previously shared with us.')
              }}
            </p>
            <p>
              {{
                $t('The legal basis for our processing of your personal data, as outlined above, is based on our legitimate interests, except for instances where we merge your online browsing activity data with the personal information you have previously provided, which requires your consent. You are entitled to decline our use of these cookies by clicking on “Change Cookie Settings” located within the Your Privacy section at the top of this web page. Select the “Advanced Analytics” cookie category and deactivate them. If you have any inquiries regarding this data or any other personal data that we may process concerning you, please refer to the “Your Rights and Contact Information” section below.')
              }}
            </p>
            <p>
              {{
                $t('We process your geolocation data to facilitate the provision of services or dealership options closest to your current location. The aforementioned functionality will be used across our entire website. Your consent for this is obtained through your browser and can be modified within the browser settings.')
              }}
            </p>
            <div class="container">
              <div class="theme-title">{{ $t('Disclosure / Recipients of your personal data') }}</div>
            </div>
            <p>{{
                $t('Your personal data will be disclosed to and processed by our affiliates and business partners for the above purposes.')
              }}</p>
            <div class="container">
              <div class="theme-title">{{ $t('Retention time') }}</div>
            </div>
            <p>
              {{
                $t('Your personal data will be retained for a maximum of two (2) years after your most recent visit to our website.')
              }}
            </p>
          </div>
        </div>
        <!-- 设置-->
        <div v-show="currentType === 'set'" slot="content">
          <div class="to_all">
            <ul>
              <li class="Agree">
                <label>
                  <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange">
                    {{ $t('Select all') }}
                  </el-checkbox>
                </label>
              </li>
              <el-checkbox-group v-model="checkedOptions" class="check-list" @change="handleCheckedChange">
                <li class="category-item">
                  <label>
                    <el-checkbox :key="options[0]" :label="options[0]" disabled>{{ options[0] }}</el-checkbox>
                  </label>
                  <p>
                    {{
                      $t('These cookies are necessary for our website to function and cannot be switched off in our systems. They are usually only set in response to actions made by you which amount to a request for services, such as setting your privacy preferences, logging in or filling in forms. You can set your browser to block or alert you about these cookies, but some parts of the site will not then work. These cookies do not store any personally identifiable information.')
                    }}
                  </p>
                  <ul class="consent-list hide">
                    <li class="consent-item">
                      <span>{{ $t('International website of BAIC') }}</span>
                      <button class="cookie_introduction" data-name="hongqi" type="button"
                              @click="showDialog('cookie')">i
                      </button>
                    </li>
                  </ul>
                </li>
                <li class="category-item google">
                  <label>
                    <el-checkbox :key="options[1]" :label="options[1]">{{ options[1] }}</el-checkbox>
                  </label>
                  <p>
                    {{
                      $t('BAIC uses Google Analytics, which gives us insights in to how you use our web sites. This data consists of; e.g., Which pages you viewed; which links you clicked on; how long you spent on a page; cars that you have built in our configurator; what device you have used; We use this data to help us understand how people use our web site and to ensure that it functions correctly. This data is not used for marketing or advertising and is not shared with third parties.')
                    }}
                  </p>
                  <ul class="consent-list hide">
                    <li class="consent-item">
                      <div class="content">
                        <span>{{ $t('Google Analytics') }}</span>
                        <span class="cookie_introduction" data-name="google" @click="showDialog('google')">i</span>
                      </div>
                    </li>
                  </ul>
                </li>
              </el-checkbox-group>
            </ul>
            <div class="button-wrapper setting">
              <button class="save_up option-btn" @click="saveSetting">
                {{ $t('Save settings') }}
              </button>
              <button class="privacy_agree option-btn" @click="agreePrivacy">
                {{ $t('Agree to all') }}
              </button>
            </div>
          </div>
        </div>
        <!-- cookie表格 -->
        <div v-show="currentType === 'cookie'" slot="content">
          <table class="cookie_table">
            <tbody>
            <tr>
              <td>{{ $t('sl-session') }}</td>
            </tr>
            <tr>
              <td>{{ $t('type:cookie') }}</td>
            </tr>
            <tr>
              <td>{{ $t('24 Hours') }}</td>
            </tr>
            </tbody>
          </table>
          <table class="cookie_table">
            <tbody>
            <tr>
              <td>{{ $t('i18n_redirected') }}</td>
            </tr>
            <tr>
              <td>{{ $t('type:cookie') }}</td>
            </tr>
            <tr>
              <td>{{ $t('1 Year') }}</td>
            </tr>
            </tbody>
          </table>
          <table class="cookie_table">
            <tbody>
            <tr>
              <td>{{ $t('isIe') }}</td>
            </tr>
            <tr>
              <td>{{ $t('type:cookie') }}</td>
            </tr>
            <tr>
              <td>{{ $t('Session') }}</td>
            </tr>
            </tbody>
          </table>
        </div>
        <!-- goole表格 -->
        <div v-show="currentType === 'google'" slot="content">
          <div class="google-table-wrap">
            <table class="cookie_table">
              <tbody>
              <tr>
                <td>{{ $t('_gid') }}</td>
              </tr>
              <tr>
                <td>{{ $t('type:cookie') }}</td>
              </tr>
              <tr>
                <td>1 {{ $t('Days') }}</td>
              </tr>
              </tbody>
            </table>
            <table class="cookie_table">
              <tbody>
              <tr>
                <td>{{ $t('_ga') }}</td>
              </tr>
              <tr>
                <td>{{ $t('type:cookie') }}</td>
              </tr>
              <tr>
                <td>729 {{ $t('Days') }}</td>
              </tr>
              </tbody>
            </table>
            <table class="cookie_table">
              <tbody>
              <tr>
                <td>{{ $t('_gat') }}</td>
              </tr>
              <tr>
                <td>{{ $t('type:cookie') }}</td>
              </tr>
              <tr>
                <td>1 {{ $t('Minute') }}</td>
              </tr>
              </tbody>
            </table>
            <table class="cookie_table">
              <tbody>
              <tr>
                <td>{{ $t('_dc_gtm_xxx') }}</td>
              </tr>
              <tr>
                <td>{{ $t('type:cookie') }}</td>
              </tr>
              <tr>
                <td>1 {{ $t('Minute') }}</td>
              </tr>
              </tbody>
            </table>
            <table class="cookie_table">
              <tbody>
              <tr>
                <td>{{ $t('_gat_gtag_xxx') }}</td>
              </tr>
              <tr>
                <td>{{ $t('type:cookie') }}</td>
              </tr>
              <tr>
                <td>1 {{ $t('Minute') }}</td>
              </tr>
              </tbody>
            </table>
            <table class="cookie_table">
              <tbody>
              <tr>
                <td>{{ $t('_gac_xx') }}</td>
              </tr>
              <tr>
                <td>{{ $t('type:cookie') }}</td>
              </tr>
              <tr>
                <td>3 {{ $t('Minute') }}</td>
              </tr>
              </tbody>
            </table>
            <table class="cookie_table">
              <tbody>
              <tr>
                <td>{{ $t('IDE') }}</td>
              </tr>
              <tr>
                <td>{{ $t('type:cookie') }}</td>
              </tr>
              <tr>
                <td>366 {{ $t('Days') }}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </cookie-dialog>
    </div>
  </transition>
</template>

<script>
import CookieDialog from './cookieDialog'

export default {
  name: 'SetCookies',
  props: {
    showCookies: Boolean,
  },
  components: {CookieDialog},
  data() {
    return {
      dialogVisible: false,
      isIndeterminate: true,
      checkAll: false,
      checkedOptions: ['Strictly Necessary Cookies'],
      options: ['Strictly Necessary Cookies', 'Statistics Cookies'],
      currentType: null,
    }
  },

  computed: {},

  mounted() {
  },

  methods: {
    showDialog(type) {
      this.currentType = type
      this.dialogVisible = true
    },
    closeDialog() {
      this.dialogVisible = false
      // 如果当前类型是cookie或google,打开setting弹框
      if (this.currentType === 'cookie' || this.currentType === 'google') {
        this.currentType = 'set'
        this.dialogVisible = true
      }
    },
    // 保存设置
    saveSetting() {
      // 获取同意的项目
      const setCookieData = {google: !!this.checkedOptions[1]}
      localStorage.setItem('cookies', JSON.stringify(setCookieData))
      this.$emit('changeMask', false)
      this.dialogVisible = false
    },
    // 同意全部隐私政策
    agreePrivacy() {
      // 同意全部cookie设置
      localStorage.setItem(
        'cookies',
        JSON.stringify({
          google: true,
        })
      )
      // 隐藏弹层
      this.$emit('changeMask', false)
      this.dialogVisible = false
    },
    // 全选
    handleCheckAllChange(val) {
      this.checkedOptions = val ? this.options : ['Strictly Necessary Cookies']
      this.isIndeterminate = false
    },
    // 选项的选中状态
    handleCheckedChange(val) {
      let checkedCount = val.length
      this.checkAll = checkedCount === this.options.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.options.length
    },
  },
}
</script>

<style lang="scss" scoped>
.fade-enter-active {
  transition: opacity 0.5s;
}

.fade-enter {
  opacity: 0;
}

.privacy-content {
  margin-top: 2%;
  font-size: 1em;
}

.title {
  font-size: 1.5em;
  font-weight: 600;
}

.d-mask {
  height: 100%;
  left: 0;
  width: 100%;
  top: 0;
  background-color: rgba(0, 0, 0, 0.4);
  box-sizing: border-box;
  font-size: 16px;
}

.agree .privacy .privacy-con {
  padding-right: 2%;
  box-sizing: border-box;
}

.agree {
  z-index: 1001;
  position: fixed;
  display: block;
}

.agree .privacy {
  width: 71%;
  position: absolute;
  background-color: #f1f1f1;
  left: 0;
  right: 0;
  bottom: 5%;
  box-sizing: border-box;
  margin: auto;
  max-height: 30vw;
  overflow-y: auto;
  box-shadow: rgba(0, 0, 0, 0.2) 0 2px 9px;
  border-radius: 10px;
  border: 1px solid #fff;
}

.agree .privacy .privacy-con {
  width: 100%;
  padding: 2%;
  line-height: 1.6rem;
}

.agree .privacy .privacy_all {
  display: inline-block;
  width: 1.5em;
  height: 1.5em;
  color: #fff;
  font-style: normal;
  text-align: center;
  margin-left: 0.5%;
  vertical-align: middle;
  cursor: pointer;
  position: relative;
  top: -0.1rem;
}

.agree .privacy .privacy-con .privacy_btn {
  display: flex;
  justify-content: flex-end;
  width: 100%;
  margin-top: 3%;
}

.option-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 11%;
  background-color: #000;
  color: #fff;
  cursor: pointer;
  text-align: center;
  margin-right: 2%;
  border-radius: 8px;
  font-size: 0.9em;
  padding: 6px 8px;
  line-height: 1.3;

  &:last-child {
    margin-right: 0;
  }
}

.agree .privacy .privacy-con .paivacy_bottom {
  width: 100%;
  text-align: right;
  margin-top: 2%;
  font-size: 1em;
}

.agree .privacy .privacy-con .paivacy_bottom a {
  text-decoration: underline;
  font-size: 0.85em;
}

.agree .privacy .privacy-con .paivacy_bottom a:first-child {
  box-sizing: border-box;
}

.check-list {
  font-size: inherit;
}

.agree .privacy .set_up_mask .to_all {
  width: 100%;
  padding: 3% 3% 0 2%;
  box-sizing: border-box;
}

li {
  margin-top: 2%;
}

//设置弹框
.Agree label {
  display: flex;
  align-items: center;
  font-size: 1em;
}

.category-item label {
  display: flex;
  align-items: center;
  font-size: 1em;
}

//多选框
:deep .el-checkbox__label {
  font-size: 1em;
  font-weight: 600;
  color: #000;
}

:deep .el-checkbox__inner {
  width: 1.3em;
  height: 1.3em;

  &:after {
    width: 5px;
    height: 12px;
    left: 6px;
    border-width: 2px;
  }
}

// 设置禁用复选框：对号颜色
:deep .el-checkbox__input.is-disabled.is-checked .el-checkbox__inner::after {
  border-color: #fff;
}

// 禁用复选框、el-input框、el-date-picker时：字体的颜色
el-checkbox__input.is-disabled + span.el-checkbox__label,
.el-input.is-disabled .el-input__inner,
.el-range-editor.is-disabled input {
  color: #3f4656 !important;
}

:deep .el-checkbox__input.is-indeterminate .el-checkbox__inner::before {
  top: 8px;
}

//选中
:deep .el-checkbox__input.is-checked .el-checkbox__inner,
:deep .el-checkbox__input.is-indeterminate .el-checkbox__inner {
  background-color: #26539d;
  border-color: #26539d;
}

//禁止选中
:deep .el-checkbox__input.is-disabled.is-checked .el-checkbox__inner {
  background-color: #bfbfbf;
  border-color: #bfbfbf;
}

//禁止选中的label
:deep .el-checkbox__input.is-disabled + span.el-checkbox__label {
  color: #000;
}

//选中的label
:deep .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #000;
}

.category-item.google {
  border-top: 1px solid #999;
  margin-top: 0;
  padding-top: 4%;
}

.category-item p {
  margin-top: 1.5%;
}

.consent-item {
  display: flex;
  align-items: center;
  margin-top: 1%;

  .content {
    display: flex;
    align-items: center;
    width: 100%;
  }
}

.category-item .consent-list .consent-item .cookie_introduction {
  padding: 0;
  border: none;
  outline: 0;
  display: -ms-inline-flexbox;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  font-size: 1rem;
  width: 1.1em;
  height: 1.1em;
  background: #999;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  margin-left: 0.8%;
}

.button-wrapper {
  display: flex;
  justify-content: flex-end;
  padding-bottom: 2%;
  margin-top: 3%;
}

.button-wrapper button {
  padding: 1em 2em;
  background-color: #000;
  margin-right: 1%;
  color: #fff;
}

.setting button {
  width: 18%;
  margin-right: 3%;
}

.bold {
  font-weight: 600;
}

.container {
  display: flex;
  justify-content: center;
}

.theme-title {
  position: relative;
  font-size: 1.1em;
  background: #000;
  display: inline-block;
  color: #fff;
  padding: 8px 45px;
  border-radius: 10px;

  &:after,
  &:before {
    position: absolute;
    content: '';
    display: inline-block;
    width: 8px;
    height: 8px;
    background: #fff;
    top: 50%;
    transform: translateY(-50%);
    border-radius: 50%;
  }

  &:after {
    right: 5%;
  }

  &:before {
    left: 5%;
  }
}

.data-notice {
  border-top: 1px solid #f3f3f3;
  padding-top: 1em;

  .title-wrap {
    text-align: center;

    .title {
      font-weight: 600;
    }

    .website {
      font-size: 1em;
      text-decoration: underline;
      margin-bottom: 1.8em;
    }
  }
}

.agree .d-flag .dlog .d-body p {
  line-height: 2em;
  font-size: 1em;
}

.agree .d-flag .dlog .d-body ul li {
  line-height: 2em;
  font-size: 1em;
  margin-bottom: 2%;
}

.cookie_table td,
.cookie_table th {
  word-break: break-all;
  word-wrap: break-word;
  border: 1px solid #939598;
  text-align: left;
}

.cookie_table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 80%;
  margin: 2% auto 0;
}

.cookie_table th {
  padding: 30px;
}

.cookie_table td {
  font: 500 14px Arial;
  padding: 10px 30px;
}

@media screen and (max-width: 1200px) {
  .option-btn {
    width: 18%;
  }
}

@media screen and (max-width: 768px) {
  .button-wrapper {
    justify-content: center;

    .option-btn {
      width: 36%;
    }
  }
  .privacy-content {
    font-size: 1em;
    line-height: 1.5;
    margin-top: 5%;

    .privacy_all {
      width: 6vw;
      height: 6vw;
    }
  }

  .theme-title {
    font-size: 1em;
  }

  .agree .d-flag .dlog .d-body p {
    line-height: 1.6em;
    font-size: 1em;
  }

  .agree .d-flag .dlog .d-body ul {
    margin-bottom: 1.5em;
  }

  .agree .d-flag .dlog .d-body ul li {
    line-height: 2em;
    font-size: 1em;
    margin-bottom: 1em;
  }

  .agree .privacy {
    width: 90%;
    padding-bottom: 2%;
    max-height: 112vw;
  }

  .agree .privacy .privacy-con {
    width: 100%;
    padding-top: 5%;
    padding-left: 4%;
    padding-right: 4%;
  }

  .agree .privacy .privacy-con .privacy_btn {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    text-align: center;
    margin-top: 3%;
  }
  .option-btn {
    width: 75%;
  }
  .agree .privacy .privacy-con .privacy_btn a {
    background-color: #000;
    color: #fff;
    padding: 0.6em 2em;
    font-size: 1em;
    margin-right: 0;
    display: block;
    margin-bottom: 3%;
  }

  .agree .privacy .privacy-con .paivacy_bottom {
    text-align: left;
    padding-top: 3%;
    padding-bottom: 3%;
    box-sizing: border-box;
    margin-top: 3%;
  }

  .agree .privacy .privacy-con .paivacy_bottom a:first-child {
    padding-right: 1%;
  }

  .Agree label span,
  .category-item label span {
    font-size: 3vw;
  }

  .category-item {
    width: 100%;
  }
}
</style>
