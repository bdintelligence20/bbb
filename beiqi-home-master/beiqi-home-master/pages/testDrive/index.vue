<template>
  <div class="media-center-page" :class="$mq" style="margin-bottom: 60px;">
    <div class="banner">
      <img src="~/assets/image/testDrice/banner.jpg" alt="" srcset="">
    </div>
    <div class="page-main max-1200">
      <div class="header">
        <h1 class="h1 title">{{ $t('testDrive.header.title') }}</h1>
        <p>
          {{ $t('testDrive.header.content') }} <br/>
        </p>
      </div>
      <div class="dotted-line">
        <svg width="1200px" height="1px" viewBox="0 0 1200 1" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
          <g id="Homepage" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" stroke-dasharray="2,9" stroke-linecap="square">
            <g id="test-drive-填写信息" transform="translate(-360.000000, -1210.000000)" stroke="#808080">
              <line id="直线-6备份-3" x1="360" y1="1210.5" x2="1560" y2="1210.5"></line>
            </g>
          </g>
        </svg>
      </div>
      <div class="form-box">
        <el-form ref="testDriceForm" :model="testDriceForm" status-icon :rules="rules" class="demo-testDriceForm" label-position=left>
          <el-row :gutter="30">
            <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6">
              <div class="h3 title">{{$t('testDrive.form.step1')}}</div>
              <div class="desc">{{$t('testDrive.form.step1Des')}}</div>
            </el-col>
            <el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="18">
              <el-row :gutter="30">
                <el-col :span="24">
                  <el-form-item prop="areaId">
                    <el-select v-model="testDriceForm.areaId" clearable :placeholder="$t('placeholder.CountryRegion')" type="string">
                      <el-option
                        v-for="item in countryList"
                        :key="item.name"
                        :label="item.name"
                        :value="item.id">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="gender">
                    <el-select v-model="testDriceForm.gender" clearable :placeholder="$t('placeholder.Gender')">
                      <el-option
                        v-for="item in genderOption"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="name">
                    <el-input v-model="testDriceForm.name" :placeholder="$t('placeholder.FullName')" :maxlength="90"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12" class="margin-0">
                  <el-form-item prop="telephone">
<!--                    <el-input v-model="testDriceForm.telephone" :placeholder="$t('placeholder.Phone')" :maxlength="20"></el-input>-->
                    <vue-tel-input
                      v-model="testDriceForm.telephone"
                      v-bind="bindProps" />
                  </el-form-item>
                </el-col>
                <el-col :span="12" class="margin-0">
                  <el-form-item prop="mailbox">
                    <el-input v-model="testDriceForm.mailbox" :placeholder="$t('placeholder.Email')" :maxlength="50"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-form>
        <div class="dotted-line">
          <svg width="1200px" height="1px" viewBox="0 0 1200 1" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <g id="Homepage" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" stroke-dasharray="2,9" stroke-linecap="square">
              <g id="test-drive-填写信息" transform="translate(-360.000000, -1210.000000)" stroke="#808080">
                <line id="直线-6备份-3" x1="360" y1="1210.5" x2="1560" y2="1210.5"></line>
              </g>
            </g>
          </svg>
        </div>
        <div class="test-drice">
          <el-row :gutter="30">
            <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6">
              <div class="h3 title">{{$t('testDrive.form.step2')}}</div>
              <div class="desc">{{$t('testDrive.form.step2Des')}}</div>
            </el-col>
            <el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="18">
              <div class="car-list">
                 <div v-for="(car, index) in carList" ref="carList" :key="car.id" :column="$mq | mq({ sm: 2, md: 2, lg: 4 })" class="car-item" :class="{active: isActive === index }" @click="handleSelectCar(car,index)">
                  <img v-lazy="`${baseUrl}${car.imgUrl}`" alt="" srcset="" />
                  <div class="item-title">
                    {{ car.name }}
                    <div class="item-empty-select">
                      <svg t="1654092504974" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3135" xmlns:xlink="http://www.w3.org/1999/xlink">
                        <defs>
                          <style type="text/css">@font-face { font-family: feedback-iconfont; src: url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff2?t=1630033759944") format("woff2"), url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff?t=1630033759944") format("woff"), url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.ttf?t=1630033759944") format("truetype"); }</style>
                        </defs>
                        <path d="M512.465455 0.116364C230.609455 0.116364 1.210182 229.492364 1.210182 511.371636S230.586182 1022.650182 512.465455 1022.650182s511.278545-229.376 511.278545-511.278546C1023.720727 229.492364 794.344727 0.116364 512.465455 0.116364z m286.091636 413.230545L466.013091 754.222545a42.519273 42.519273 0 0 1-30.254546 12.753455h-0.232727a42.589091 42.589091 0 0 1-30.114909-12.427636l-178.711273-178.827637a42.565818 42.565818 0 0 1 0-60.253091 42.565818 42.565818 0 0 1 60.253091 0l148.363637 148.247273 302.312727-310.062545a42.682182 42.682182 0 0 1 60.253091-0.698182 42.821818 42.821818 0 0 1 0.674909 60.392727z m0 0" p-id="3136" fill="#31be0d"></path>
                      </svg>
                    </div>
                  </div>
                 </div>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="dotted-line">
          <svg width="1200px" height="1px" viewBox="0 0 1200 1" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <g id="Homepage" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" stroke-dasharray="2,9" stroke-linecap="square">
              <g id="test-drive-填写信息" transform="translate(-360.000000, -1210.000000)" stroke="#808080">
                <line id="直线-6备份-3" x1="360" y1="1210.5" x2="1560" y2="1210.5"></line>
              </g>
            </g>
          </svg>
        </div>
        <el-row :gutter="30">
          <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6">
            <div class="h3 title">{{$t('testDrive.form.step3')}}</div>
            <div class="desc">{{$t('testDrive.form.step3Des')}}</div>
          </el-col>
          <el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="18">
            <div class="bottom-btn">
              <div class="btn" @click="submitForm('testDriceForm')">{{$t('form.btn.SUBMIT')}}</div>
            </div>
          </el-col>
        </el-row>
      </div>
      <successDialog :show="open" @dialog-close="open = false" />
    </div>
  </div>
</template>

<script>
import successDialog from '@/components/successDialog'

const VueTelInput = () =>
    Promise.all([
      import('vue-tel-input'),
      import('vue-tel-input/dist/vue-tel-input.css'),
    ]).then(([{ VueTelInput }]) => VueTelInput)

export default {
  name: 'TestDrive',
  components: {
    successDialog,
    VueTelInput
  },
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
      countryList: [],
      carList: [],
      baseUrl: process.env.imgBaseUrl,
      testDriceForm: {
      },
      genderOption: [
        {
          value: this.$t('genderOption.Mr'),
          label: this.$t('genderOption.Mr')
        },
        {
          value: this.$t('genderOption.Miss'),
          label: this.$t('genderOption.Miss')
        },
        {
          value: this.$t('genderOption.Ms'),
          label: this.$t('genderOption.Ms')
        },
        {
          value: this.$t('genderOption.Dr'),
          label: this.$t('genderOption.Dr')
        },
        {
          value: this.$t('genderOption.others'),
          label: this.$t('genderOption.others')
        }
      ],
      rules: {
        areaId: [
          { type: 'number', required:true, message: this.$t('form.label.CountryRegion')+this.$t('form.required.same'), trigger: ['blur', 'change'] }
        ],
        name: [
          { required:true, message: this.$t('form.label.FullName')+this.$t('form.required.same'), trigger: 'blur' }
        ],
        gender: [
          { required:true, message: this.$t('form.label.Gender')+this.$t('form.required.same'), trigger: 'blur' }
        ],
        mailbox: [
          { required:true, message: this.$t('form.label.Email')+this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.emailValidator, trigger: 'blur' }
        ],
        telephone: [
          { required:true, message: this.$t('form.label.Phone')+this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.phoneValidator, trigger: 'blur' }
        ]
      },
      open: false,
      isActive: 0,
      testDriveVehicleClassId: '',
    }
  },
  mounted(){
    this.$api.testDrive.countryList({type:1}).then( res => {
      // 按字母排序
      this.countryList = res.data.sort((a, b) => a.name.localeCompare(b.name))
    })
    this.$api.testDrive.carList().then( res => {
      this.carList = res.data;
    })
  },
  methods: {
    phoneValidator(rule, value, callback) {
      if ( value ) {
        const reg = /^[0-9+\s]*$/;
        if ( !reg.test(value) ) {
          callback(new Error(this.$t('form.label.Phone')+this.$t('form.error.same')));
        } else {
          callback();
        }
      } else {
        callback(new Error(this.$t('form.label.Phone')+this.$t('form.error.same')));
      }
    },
    emailValidator(rule, value, callback) {
      if (value && value !== '') {
        const reg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (reg.test(value)) {
          callback()
        } else {
          callback(this.$t('form.label.Email')+this.$t('form.error.same'))
        }
      } else {
        callback(this.$t('form.label.Email')+this.$t('form.error.same'))
      }
    },
    submitForm() {
      this.$refs.testDriceForm.validate((valid) => {
        if (valid) {
          this.testDriceForm.lang = this.$i18n.locale
          this.testDriceForm.testDriveVehicleClassId = this.testDriveVehicleClassId || this.carList[0].id
          const loading  = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })
          this.$api.testDrive.submitForm(this.testDriceForm).then((res) => {
            this.open = true
            loading.close()
          }).catch(() => {
            loading.close()
          })
        } else {
          return false;
        }
      });
    },
    handleSelectCar(car,index) {
      this.isActive = index
      this.testDriveVehicleClassId = car.id
    }
  }
}
</script>

<style lang="scss" scoped>
.banner {
  img {
    width: 100%;
    margin: 0 auto;
  }
}
.page-main {

  .h3 {
    margin-bottom: 10px;
  }
  .header {
    text-align: center;
    margin-top: 100px;
    .h1 {
      margin-bottom: 35px;
      font-size: 24px;
    }
    p {
      font-size: 16px;
      line-height: 28px;
    }
  }
  .form-box {
    .form-header {
      margin-top: 150px;
      text-align: center;
      margin-bottom: 50px;
      .form-title {
        font-size: 24px;
        margin-bottom: 15px;
      }
      p {
        font-size: 16px;
        line-height: 30px;
      }
    }
    .margin-0 {
      ::v-deep .el-form-item {
        margin-bottom: 0;
      }
    }
    .bottom-btn {
      ::v-deep .el-form-item__content {
        text-align:  center;
      }
      .btn {
        cursor: pointer;
      }
    }
  }
  .dotted-line {
    margin-top: 50px;
    margin-bottom: 50px;
  }
  .car-list {
    display: flex;
    flex-wrap: wrap;
    background-color: $input-bgColor;
    padding: 30px;
    .car-item {
      &.active {
        .item-title {
          .item-empty-select {
            border: 0;
            svg {
              display: block;
            }
          }
        }
      }
      cursor: pointer;
      flex: 0 1 25%;
      margin-bottom: 2.5%;
      align-items: center;

      img {
        width: 100%;
        height: 100%;
        object-fit: contain;
        padding: 0 5px;
      }
      .item-title {
        display: flex;
        justify-content: center;
        align-items: center;
        .item-empty-select {
          display: inline-block;
          width: 15px;
          height: 15px;
          border: 1.5px solid #000;
          border-radius: 50%;
          margin-left: 10px;
          svg {
            display: none;
          }
        }
      }

    }
  }
}
.xs, .sm {
  .page-main {
    .header {
      margin-top: 40px;
      .h1 {
        margin-bottom: 35px;
        font-size: 45px;
      }
    }
    .h3 {
      font-size: 18px;
      margin-bottom: 10px;
      text-align: center;
    }
    .desc {
      text-align: center;
      margin-bottom: 10px;
    }
    .dotted-line {
      margin-top: 15px;
      margin-bottom: 15px;
    }
    ::v-deep .el-form-item {
      margin-bottom: 15px;
    }
    .car-list {
      padding: 20px 15px;
      .car-item {
        flex: 0 50%;
        margin-bottom: 3%;
      }
    }
    .bottom-btn {
      text-align: center;
    }
  }
}
</style>
