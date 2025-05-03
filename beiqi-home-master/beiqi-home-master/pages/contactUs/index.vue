<template>
  <client-only>
    <div class="media-center-page page-main" :class="$mq">
      <div class="banner">
        <img src="~/assets/image/contactUs/banner.jpg" alt="" srcset="" />
      </div>
      <div class="page-main max-1200">
        <div class="header">
          <h1 class="h1 title">{{ $t('contactUs.header.title') }}</h1>
          <p>
            {{ $t('contactUs.header.content1') }} <br />
            {{ $t('contactUs.header.content2') }}
          </p>
        </div>
        <div class="form-box">
          <el-form ref="contactUsForm" :label-position="labelPosition" :model="contactUsForm" :rules="rules">
            <el-form-item :label="$t('form.label.Gender')" prop="gender">
              <el-select v-model="contactUsForm.gender" :placeholder="$t('placeholder.Gender')">
                <el-option v-for="item in genderOption" :key="item.value" :label="item.label" :value="item.value"> </el-option>
              </el-select>
            </el-form-item>
            <el-form-item :label="$t('form.label.FullName')" prop="name">
              <el-input v-model="contactUsForm.name" :maxlength="90" :placeholder="$t('placeholder.FullName')"></el-input>
            </el-form-item>
            <el-form-item :label="$t('form.label.Nationality')" prop="country">
              <div class="country-item">
                <el-select v-model="contactUsForm.country" :maxlength="90" :placeholder="$t('placeholder.Nationality')" clearable filterable ref="countryRef" class="country-select" :popper-append-to-body="false">
                  <el-option v-for="item in countryOptions" :key="item.name" :label="item.name" :value="item.name"> </el-option>
                </el-select>

                <div v-if="!dialogVisible" class="location" @click="locationClick" :class="{ relocate: isConfirmLocation }">
                  <i slot="suffix" class="el-icon-location-outline"></i>
                  <div class="location-describe">
                    <div v-if="isConfirmLocation" class="relocate-text">{{ $t('contactUs.location.incorrectLocation') }}</div>
                    <div v-else @click="dialogVisible = true">{{ $t('contactUs.location.clickToRelocate') }}</div>
                  </div>
                </div>
              </div>
            </el-form-item>
            <el-form-item :label="$t('form.label.Phone')" prop="telephone">
              <vue-tel-input v-model="contactUsForm.telephone" v-bind="bindProps" :key="telKey" />
            </el-form-item>
            <el-form-item :label="$t('form.label.Email')" prop="email">
              <el-input v-model="contactUsForm.email" type="email" :maxlength="50" :placeholder="$t('placeholder.Email')"></el-input>
            </el-form-item>
            <el-form-item :label="$t('form.label.ProblemType')" prop="msgType">
              <el-select v-model="contactUsForm.msgType" clearable :placeholder="$t('placeholder.ProblemType')">
                <el-option v-for="item in problemTypeOption" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item v-if="contactUsForm.msgType === $t('contactUs.form.typeAfterSales')" :label="$t('form.label.Vin')" prop="vin" class="vin-form-item">
              <el-input v-model="contactUsForm.vin" :placeholder="$t('placeholder.VIN')"></el-input>
            </el-form-item>
            <div v-if="contactUsForm.msgType === $t('contactUs.form.typeAfterSales')" class="vin" :class="{ 'vin-ar': $i18n.locale === 'ar' }">
              <img :src="require(`~/assets/image/contactUs/vin-${$i18n.locale}.jpg`)" alt="" />
              <span class="desc">{{ $t('contactUs.vinDes') }}</span>
            </div>
            <el-form-item :label="$t('form.label.Questions')" prop="msg">
              <el-input v-model="contactUsForm.msg" type="textarea" maxlength="2000" rows="10" :placeholder="$t('placeholder.Questions')"></el-input>
            </el-form-item>
          </el-form>
          <div class="bottom-btn">
            <p style="">
              {{ $t('contactUs.form.desc') }}
              <a style="text-decoration: underline" href="mailto:BAICVIP@baicintl.com">{{ $t('contactUs.form.emailAdd') }}</a>
            </p>
            <div class="btn" @click="submitForm('contactUsForm')">{{ $t('form.btn.SUBMIT') }}</div>
          </div>
        </div>
        <successDialog :show="open" @dialog-close="open = false" />
      </div>
      <el-dialog :visible.sync="dialogVisible" :title="$t('contactUs.location.requestAuth')" class="location-tips" :show-close="false" center :width="dialogWidth">
        <div class="location-body">
          <span class="content"> {{ $t('contactUs.location.needAccess') }} </span>

          <div class="ignore-prompt">
            <div class="item-empty-select" :class="{ 'item-select': isIgnorePrompt }" @click="isIgnorePrompt = !isIgnorePrompt">
              <svg t="1654092504974" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3135" xmlns:xlink="http://www.w3.org/1999/xlink">
                <defs>
                  <style type="text/css">
                    @font-face {
                      font-family: feedback-iconfont;
                      src: url('//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff2?t=1630033759944') format('woff2'), url('//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff?t=1630033759944') format('woff'), url('//at.alicdn.com/t/font_1031158_u69w8yhxdu.ttf?t=1630033759944') format('truetype');
                    }
                  </style>
                </defs>
                <path d="M512.465455 0.116364C230.609455 0.116364 1.210182 229.492364 1.210182 511.371636S230.586182 1022.650182 512.465455 1022.650182s511.278545-229.376 511.278545-511.278546C1023.720727 229.492364 794.344727 0.116364 512.465455 0.116364z m286.091636 413.230545L466.013091 754.222545a42.519273 42.519273 0 0 1-30.254546 12.753455h-0.232727a42.589091 42.589091 0 0 1-30.114909-12.427636l-178.711273-178.827637a42.565818 42.565818 0 0 1 0-60.253091 42.565818 42.565818 0 0 1 60.253091 0l148.363637 148.247273 302.312727-310.062545a42.682182 42.682182 0 0 1 60.253091-0.698182 42.821818 42.821818 0 0 1 0.674909 60.392727z m0 0" p-id="3136" fill="#31be0d"></path>
              </svg>
            </div>
            <div class="text">{{ $t('contactUs.location.dontShowAgain') }}</div>
          </div>
        </div>

        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="cancleTips">{{ $t('contactUs.location.cancel') }}</el-button>
          <el-button type="text" @click="confirmTips">{{ $t('contactUs.location.confirm') }}</el-button>
        </div>
      </el-dialog>
    </div>
  </client-only>
</template>

<script>
import successDialog from '@/components/successDialog'
import { COUNTRIES } from '@/constants/countries.js'
import { setCookie, getCookie } from '@/utils/cookie'

//  组件延迟加载：https://github.com/iamstevendao/vue-tel-input#installation
const VueTelInput = () => Promise.all([import('vue-tel-input'), import('vue-tel-input/dist/vue-tel-input.css')]).then(([{ VueTelInput }]) => VueTelInput)

export default {
  name: 'ContactUs',
  components: { successDialog, VueTelInput },
  layout: 'main',
  data() {
    return {
      dialogVisible: null,
      dialogWidth: null,
      telKey: Date.now(),
      isIgnorePrompt: false,
      isConfirmLocation: null,
      countryOptions: [],
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
      contactUsForm: { gender: this.$t('genderOption.Mr'), country: '' },
      genderOption: [
        { value: this.$t('genderOption.Mr'), label: this.$t('genderOption.Mr') },
        { value: this.$t('genderOption.Miss'), label: this.$t('genderOption.Miss') },
        { value: this.$t('genderOption.Ms'), label: this.$t('genderOption.Ms') },
        { value: this.$t('genderOption.Dr'), label: this.$t('genderOption.Dr') },
        { value: this.$t('genderOption.others'), label: this.$t('genderOption.others') },
      ],
      problemTypeOption: [
        { value: this.$t('contactUs.form.typeOther'), label: this.$t('contactUs.form.typeOther') },
        { value: this.$t('contactUs.form.typeProduct'), label: this.$t('contactUs.form.typeProduct') },
        { value: this.$t('contactUs.form.typeAfterSales'), label: this.$t('contactUs.form.typeAfterSales') },
        { value: this.$t('contactUs.form.typeSales'), label: this.$t('contactUs.form.typeSales') },
        { value: this.$t('contactUs.form.typeBusinessCooperation'), label: this.$t('contactUs.form.typeBusinessCooperation') },
        { value: this.$t('contactUs.form.typeUsedCar'), label: this.$t('contactUs.form.typeUsedCar') },
      ],
      rules: {
        name: [{ required: true, message: this.$t('form.label.FullName') + this.$t('form.required.same'), trigger: 'blur' }],
        country: [{ required: true, message: this.$t('form.label.Nationality') + this.$t('form.required.same'), trigger: 'blur' }],
        telephone: [
          { required: true, message: this.$t('form.label.Phone') + this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.phoneValidator, trigger: 'blur' },
        ],
        email: [
          { required: true, message: this.$t('form.label.Email') + this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.emailValidator, trigger: 'blur' },
        ],
        msgType: [{ required: true, message: this.$t('form.label.ProblemType') + this.$t('form.required.same'), trigger: 'blur' }],
        vin: [{ required: true, message: this.$t('form.label.Vin') + this.$t('form.required.same'), trigger: 'blur' }],
        msg: [{ required: true, message: this.$t('form.label.Questions') + this.$t('form.required.same'), trigger: 'blur' }],
      },
      open: false,
      labelPosition: 'right',
    }
  },
  head() {
    return {
      script: [
        {
          // innerHTML: `
          //   !(function (e, t) {
          //     "object" == typeof exports && "object" == typeof module
          //       ? (module.exports = t())
          //       : "function" == typeof define && define.amd
          //       ? define([], t)
          //       : "object" == typeof exports
          //       ? (exports.install = t())
          //       : (e.install = t());
          //   })(window, function () {
          //     return (function (e) {
          //       var t = {};
          //       function n(o) {
          //         if (t[o]) return t[o].exports;
          //         var r = (t[o] = { i: o, l: !1, exports: {} });
          //         return e[o].call(r.exports, r, r.exports, n), (r.l = !0), r.exports;
          //       }
          //       return (
          //         (n.m = e),
          //         (n.c = t),
          //         (n.d = function (e, t, o) {
          //           n.o(e, t) || Object.defineProperty(e, t, { enumerable: !0, get: o });
          //         }),
          //         (n.r = function (e) {
          //           "undefined" != typeof Symbol &&
          //             Symbol.toStringTag &&
          //             Object.defineProperty(e, Symbol.toStringTag, { value: "Module" }),
          //             Object.defineProperty(e, "__esModule", { value: !0 });
          //         }),
          //         (n.t = function (e, t) {
          //           if ((1 & t && (e = n(e)), 8 & t)) return e;
          //           if (4 & t && "object" == typeof e && e && e.__esModule) return e;
          //           var o = Object.create(null);
          //           if (
          //             (n.r(o),
          //             Object.defineProperty(o, "default", { enumerable: !0, value: e }),
          //             2 & t && "string" != typeof e)
          //           )
          //             for (var r in e)
          //               n.d(
          //                 o,
          //                 r,
          //                 function (t) {
          //                   return e[t];
          //                 }.bind(null, r)
          //               );
          //           return o;
          //         }),
          //         (n.n = function (e) {
          //           var t =
          //             e && e.__esModule
          //               ? function () {
          //                   return e.default;
          //                 }
          //               : function () {
          //                   return e;
          //                 };
          //           return n.d(t, "a", t), t;
          //         }),
          //         (n.o = function (e, t) {
          //           return Object.prototype.hasOwnProperty.call(e, t);
          //         }),
          //         (n.p = ""),
          //         n((n.s = 0))
          //       );
          //     })([
          //       function (e, t, n) {
          //         "use strict";
          //         var o =
          //           (this && this.__spreadArray) ||
          //           function (e, t, n) {
          //             if (n || 2 === arguments.length)
          //               for (var o, r = 0, i = t.length; r < i; r++)
          //                 (!o && r in t) ||
          //                   (o || (o = Array.prototype.slice.call(t, 0, r)),
          //                   (o[r] = t[r]));
          //             return e.concat(o || Array.prototype.slice.call(t));
          //           };
          //         Object.defineProperty(t, "__esModule", { value: !0 });
          //         var r = function (e, t, n) {
          //           var o,
          //             i = e.createElement("script");
          //           (i.type = "text/javascript"),
          //             (i.async = !0),
          //             (i.src = t),
          //             n &&
          //               (i.onerror = function () {
          //                 r(e, n);
          //               });
          //           var a = e.getElementsByTagName("script")[0];
          //           null === (o = a.parentNode) || void 0 === o || o.insertBefore(i, a);
          //         };
          //         !(function (e, t, n) {
          //           e.KwaiAnalyticsObject = n;
          //           var i = (e[n] = e[n] || []);
          //           i.methods = [
          //             "page",
          //             "track",
          //             "identify",
          //             "instances",
          //             "debug",
          //             "on",
          //             "off",
          //             "once",
          //             "ready",
          //             "alias",
          //             "group",
          //             "enableCookie",
          //             "disableCookie",
          //           ];
          //           var a = function (e, t) {
          //             e[t] = function () {
          //               for (var n = [], r = 0; r < arguments.length; r++)
          //                 n[r] = arguments[r];
          //               var i = o([t], n, !0);
          //               e.push(i);
          //             };
          //           };
          //           i.methods.forEach(function (e) {
          //             a(i, e);
          //           }),
          //             (i.instance = function (e) {
          //               var t,
          //                 n = (null === (t = i._i) || void 0 === t ? void 0 : t[e]) || [];
          //               return (
          //                 i.methods.forEach(function (e) {
          //                   a(n, e);
          //                 }),
          //                 n
          //               );
          //             }),
          //             (i.load = function (e, o) {
          //               var a = "https://s1.kwai.net/kos/s101/nlav11187/pixel/events.js";
          //               (i._i = i._i || {}),
          //                 (i._i[e] = []),
          //                 (i._i[e]._u = a),
          //                 (i._t = i._t || {}),
          //                 (i._t[e] = +new Date()),
          //                 (i._o = i._o || {}),
          //                 (i._o[e] = o || {});
          //               var c = "?sdkid=".concat(e, "&lib=").concat(n);
          //               r(
          //                 t,
          //                 a + c,
          //                 "https://s16-11187.ap4r.com/kos/s101/nlav11187/pixel/events.js" +
          //                   c
          //               );
          //             });
          //         })(window, document, "kwaiq");
          //       },
          //     ]);
          //   });
          // `,
        },
        {
          // innerHTML: `
          //   kwaiq.load("249712951718500");
          //   kwaiq.page();
          // `,
        },
      ],
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(value, oldValue) {
        if (value === 'xs') {
          this.labelPosition = 'top'
          this.dialogWidth = '90%'
        } else {
          this.labelPosition = 'right'
          this.dialogWidth = '31%'
        }
      },
    },
  },
  activated() {
    this.resetForm()
    this.isConfirmLocation = getCookie('isConfirmLocation') === 'true'
    const isIgnorePrompt = getCookie('isIgnorePrompt') === 'true'
    this.isIgnorePrompt = isIgnorePrompt
    this.dialogVisible = !isIgnorePrompt

    // 如果忽略提示且已经授权定位，则获取ip
    if (isIgnorePrompt && this.isConfirmLocation) {
      this.getCountry()
    }
  },
  mounted() {
    this.countryOptions = COUNTRIES
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
    emailValidator(rule, value, callback) {
      if (value && value !== '') {
        const reg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (reg.test(value)) {
          callback()
        } else {
          console.log('这里1')
          callback(this.$t('form.label.Email') + this.$t('form.error.same'))
        }
      } else {
        console.log('这里2')
        callback(this.$t('form.label.Email') + this.$t('form.error.same'))
      }
    },

    // 获取位置点击
    locationClick() {
      // 未授权位置，弹出提示
      if (!this.isConfirmLocation) {
        this.dialogVisible = true
      } else {
        // 已经授权位置,点击弹出选择框
        this.$refs.countryRef.focus()
      }
    },

    // 获取用户所在国家
    getCountry() {
      this.$axios
        .get('/geoip')
        .then(res => {
          // res.country = 'SA' // 沙特阿拉伯-测试代码
          // res.countryName = 'Saudi Arabia'
          this.contactUsForm.country = res.countryName
        })
        .catch(err => {
          console.error('获取国家信息失败:', err)
        })
    },

    // 取消定位
    cancleTips() {
      this.isConfirmLocation = false
      this.dialogVisible = false
      setCookie('isIgnorePrompt', this.isIgnorePrompt)
      setCookie('isConfirmLocation', 'false')
    },

    // 确认定位
    confirmTips() {
      this.isConfirmLocation = true
      this.getCountry()
      this.dialogVisible = false
      setCookie('isIgnorePrompt', this.isIgnorePrompt)
      setCookie('isConfirmLocation', 'true')
    },

    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.contactUsForm.lang = this.$i18n.locale
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)',
          })
          // 筛选出当前选中国家的iso代码
          const obj = this.countryOptions.find(obj => obj.name === this.contactUsForm.country)
          if (obj) {
            this.contactUsForm.countryIsoCode = obj.iso2
          }

          this.$api.contact
            .contactUs(this.contactUsForm)
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

    // 重置表单
    resetForm() {
      this.contactUsForm = {
        gender: this.$t('genderOption.Mr'),
        name: '',
        country: '',
        countryIsoCode: '',
        telephone: '',
        email: '',
        msgType: '',
        vin: '',
        msg: '',
      }
      this.$nextTick(() => {
        this.$refs.contactUsForm.clearValidate()
      })
      // 刷新tel组件，显示默认的国际电话区号
      this.telKey = Date.now()
    },
  },
}
</script>

<style lang="scss" scoped>
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
    }
  }

  .form-box {
    margin-top: 100px;

    .vin-form-item {
      margin-bottom: 18px;
    }

    .vin {
      width: 60%;
      text-align: center;
      margin-left: 150px;
      margin-bottom: 25px;
      .desc {
        color: #8990a1;
      }
    }
    .vin-ar {
      margin-right: 150px;
    }
  }

  .bottom-btn {
    text-align: center;

    p {
      margin: 60px 0 40px;
    }

    .btn {
      cursor: pointer;
    }
  }
}

.country-item {
  display: flex;
  align-items: center;
  .el-icon-location-outline {
    font-size: 20px;
    cursor: pointer;
  }
  .location {
    display: flex;
    align-items: center;
    margin-left: 5px;
    cursor: pointer;
    .location-describe {
      line-height: normal;
      margin-left: 5px;
    }
    .country-card {
      position: absolute;
      width: 31%;
      z-index: 9;
    }
  }
  .relocate {
    width: 28%;
  }
}

::v-deep .el-input__suffix {
  display: flex;
  align-items: center;
}
::v-deep .location-tips {
  .el-dialog__body {
    padding: 0 20px 10px;
    text-align: center;
    word-break: keep-all;
  }
  .location-body {
    font-size: 16px;
  }
  .ignore-prompt {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 8px;
  }
  .item-empty-select {
    display: inline-block;
    width: 15px;
    height: 15px;
    border: 1.5px solid #000;
    border-radius: 50%;
    margin-right: 5px;
    cursor: pointer;
    svg {
      display: none;
    }
  }
  .item-select {
    border: 0;
    svg {
      display: block;
    }
  }
  .el-dialog__footer {
    text-align: center;
    padding: 0;
  }
  .dialog-footer {
    display: flex;
    .el-button {
      background: none;
      color: #000;
      border: 1px solid #d9d9d9;
      flex: 1;
      border-radius: 0;
      font-size: 16px;
      &:last-child {
        border-left: none;
        color: #d1291a;
      }
    }
    .el-button + .el-button {
      margin-left: 0;
    }
  }
  .location-tips {
    padding: 10px 20px;
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

.xs {
  .page-main {
    .vin {
      width: 100%;
      margin-left: 0;
      margin-bottom: 0;
    }
    .vin-ar {
      margin-right: 0;
    }
    .country-item {
      display: block;
      .location {
        margin-top: 10px;
        margin-left: 0;
      }
      .relocate {
        width: 100%;
      }
    }
  }
}
</style>

<style lang="scss">
.el-form-item__error {
  left: 0;
  right: 0;
}
.el-form-item__label {
  font-size: 16px;
  color: #000;
}

.el-form--label-right {
  .el-form-item {
    display: flex;
    flex-direction: row;
    margin-bottom: 35px;

    .el-form-item__label {
      flex: 0 0 150px;
    }

    .el-form-item__content {
      flex: 1;
    }
  }
}

.el-form--label-top {
  .el-form-item {
    margin-bottom: 15px;
    .el-form-item__label {
      padding: 0;
    }
  }
}

.lang___ar {
  .el-form-item {
    .el-form-item__label {
      text-align: left;
    }

    .el-form-item__content {
      .el-input--suffix .el-input__inner {
        padding-left: 30px;
        padding-right: 15px;
      }
      .el-input__suffix {
        left: 5px;
        right: auto;
      }
    }
  }
}
</style>
