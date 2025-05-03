<template>
  <div :class="mobileClass">
    <mq-layout mq="lg+">
    <div class="drive_title">
      <div class="xian"></div>
      <div class="title_content">{{ title[locale] }}</div>
      <div class="xian"></div>
    </div>
    </mq-layout>

    <mq-layout :mq="['xs', 'sm', 'md']">
        <div class="title_content">{{ title[locale] }}</div>
    </mq-layout>



    <el-form ref="testDriceForm" class="demo-ruleForm" status-icon :rules="rules" :model="testDriceForm">
      <div class="drive_from">

        <div class="form_item">
          <el-form-item prop="areaId">
            <el-select v-model="testDriceForm.areaId" clearable :placeholder="$t('placeholder.CountryRegion')" type="string">
              <el-option v-for="item in countryList" :key="item.name" :label="item.name" :value="item.id"> </el-option>
            </el-select>
          </el-form-item>
        </div>

        <div class="form_item">
          <el-form-item prop="gender">
            <el-select v-model="testDriceForm.gender" clearable :placeholder="$t('placeholder.Gender')">
              <el-option v-for="item in genderOption" :key="item.value" :label="item.label" :value="item.value"> </el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="form_item">
          <el-form-item prop="name">
            <el-input v-model="testDriceForm.name" :placeholder="$t('placeholder.FullName')" :maxlength="90"></el-input>
          </el-form-item>
        </div>

        <div class="form_item">
          <el-form-item prop="mailbox">
            <el-input v-model="testDriceForm.mailbox" :placeholder="$t('placeholder.Email')" :maxlength="50"></el-input>
          </el-form-item>
        </div>
        <div class="form_item">
          <el-form-item prop="telephone">
            <vue-tel-input v-model="testDriceForm.telephone" v-bind="bindProps" />
          </el-form-item>
        </div>
        <div class="form_item">
          <el-form-item >
            <el-select v-model="testDriveVehicleClassId" placeholder="Please select Vehicle model">
              <el-option v-for="car in carList" :key="car.id" :value="car.id" :label="car.name"></el-option>
            </el-select>
          </el-form-item>
        </div>
      </div>

      <div class="from_btn" @click="submitForm('testDriceForm')">{{ $t('form.btn.SUBMIT') }}</div>
    </el-form>
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
  name: 'CommonTestDrive',

  components: {
    successDialog,
    VueTelInput
  },
  data() {
    return {
      mobileClass:"",
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
      testDriceForm: {},
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
        areaId: [{ type: 'number', required: true, message: this.$t('form.label.CountryRegion') + this.$t('form.required.same'), trigger: ['blur', 'change'] }],
        name: [{ required: true, message: this.$t('form.label.FullName') + this.$t('form.required.same'), trigger: 'blur' }],
        gender: [{ required: true, message: this.$t('form.label.Gender') + this.$t('form.required.same'), trigger: 'blur' }],
        mailbox: [
          { required: true, message: this.$t('form.label.Email') + this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.emailValidator, trigger: 'blur' },
        ],
        telephone: [
          { required: true, message: this.$t('form.label.Phone') + this.$t('form.required.same'), trigger: 'blur' },
          { validator: this.phoneValidator, trigger: 'blur' },
        ],
      },
      locale: this.$i18n.locale,
      open: false,
      isActive: 0,
      testDriveVehicleClassId: null,
      title:{
        en:"APPOINTMENT FOR A TEST DRIVE",
        es:"RESERVA PARA PROBAR LA CONDUCCIÓN",
        ar:"احجز موعد لتجربة القيادة",
      }
    }
  },
  watch: {
    $mq: {
      immediate: true,
      handler(n, o) {
        console.log('mounted', this.$mq)
        if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'

        } else {
          this.mobileClass = ''

        }
      },
    },
  },
  mounted() {
    if (['xs', 'md', 'sm'].includes(this.$mq)) {
          this.mobileClass = 'mobile'

        } else {
          this.mobileClass = ''

        }
    this.$api.testDrive.countryList({ type: 1 }).then(res => {
      this.countryList = res.data
    })
    this.$api.testDrive.carList().then(res => {
      this.carList = res.data

    })
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
          callback(this.$t('form.label.Email') + this.$t('form.error.same'))
        }
      } else {
        callback(this.$t('form.label.Email') + this.$t('form.error.same'))
      }
    },
    submitForm() {
      this.$refs.testDriceForm.validate(valid => {
        if (valid) {
          this.testDriceForm.lang = this.$i18n.locale
          this.testDriceForm.testDriveVehicleClassId = this.testDriveVehicleClassId || this.carList[0].id
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)',
          })
          this.$api.testDrive
            .submitForm(this.testDriceForm)
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
    handleSelectCar(car, index) {
      this.isActive = index
      this.testDriveVehicleClassId = car.id
    },
  },
}
</script>
<style  scoped lang="scss">
.lang___ar{
  .from_btn{
    right: 50%;
    transform: translateX(50%);
  }
}
.drive_title {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 5vw 0;
  & > div {
    display: flex;
  }
  .title_content {
    margin: 0 2vw;
    font-size: 1.5vw;
    font-weight: 900;
    text-transform: uppercase;
  }
  .xian {
    height: 1px;
    background-color: #b1b1b1;
    flex: 1;
  }
}
.drive_from {
  width: 80%;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .form_item ::v-deep {
    width: 48%;
    margin-bottom: 2vw;
    display: flex;
    justify-content: space-between;
    background-color: #f4f4f4;
    .el-form-item {
      margin-bottom: 0 !important;
      display: flex;
      align-items: center;
      width: 100%;
      //   .el-form-item__label {
      //     width: 30%;
      //   }

      .el-form-item__content {
        margin-left: 0;
        width: 100%;
      }
    }
    .from_item_l {
      flex: 1;
    }
    .from_item_r {
      width: 30%;
      display: flex;
      align-items: center;
      justify-content: flex-end;
    }
    .el-input__inner {
      background: transparent;
    }
    .el-form-item__label {
      text-align: left;
      font-size: 14px;
      color: #ccc;
      white-space: nowrap;
      padding-left: 1em;
    }
  }
  .bg_f ::v-deep {
    background-color: #fff;
    .from_item_l {
      background: #f4f4f4;
    }
  }
}

.from_btn {
  padding: 1vw 5vw;
  display: inline-block;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  background-color: #3d3d3d;
  color: #fff;
  margin: 3vw 0;
  cursor: pointer;
}

.mobile{
    .drive_from{
        width: 90%;
        .form_item{
            width: 100%;
            margin-bottom: 5vw;
        }


    }
    .from_btn{
        width: 90%;
        font-size: 4vw;
        text-align: center;
        height: auto;
        padding: 2vw 0;
    }
    .title_content{
            text-transform: uppercase;
            text-align: center;
            margin: 5vw;
            font-weight: bolder;
            font-size: 5vw;
        }
}

</style>