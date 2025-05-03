<!--
 * Author: SHI
 * Date: 2022/6/23
 * Remark: afterSale
 -->

<template>
  <div class="afterSale__page" v-loading="fullLoading">
    <div class="banner">
      <img src="~/assets/image/afterSale/after-sale-banner.jpg" alt="after-sale" />
    </div>
    <div class="max-1680">
      <description v-if="detailData.content" :html-str="detailData.content" />
      <div class="content-text" v-if="detailData.title">
        <h1 v-if="detailData.title" :title="detailData.title">{{ detailData.title }}</h1>
        <p class="pre-text" v-if="detailData.titleDescribe" :title="detailData.titleDescribe">{{ detailData.titleDescribe }}</p>
      </div>
      <div class="content-service" v-if="detailData.intros.length > 1">
        <el-row type="flex" :gutter="11">
          <el-col v-for="(item, index) in detailData.intros" :key="index" :span="index ? 15 : 9">
            <el-row type="flex" v-if="item instanceof Array" style="flex-direction: column">
              <el-col v-for="(item, index) in item" :key="index" :span="index ? 13 : 11">
                <el-row type="flex" :gutter="11" v-if="item instanceof Array">
                  <el-col v-for="(item, index) in item" :key="index" :span="index ? 12 : 12">
                    <div class="service-item">
                      <div class="service-item__icon">
                        <img :src="baseUrl + item.imageUrl" alt="" />
                      </div>
                      <div class="service-item__text">
                        <h3>{{ item.subTitle }}</h3>
                        <p class="ellipsis5line" :title="item.subTitleDescribe">{{ item.subTitleDescribe }}</p>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-col v-else>
                  <div class="service-item">
                    <div class="service-item__icon">
                      <img :src="baseUrl + item.imageUrl" alt="" />
                    </div>
                    <div class="service-item__text">
                      <h3>{{ item.subTitle }}</h3>
                      <p class="ellipsis5line" :title="item.subTitleDescribe">{{ item.subTitleDescribe }}</p>
                    </div>
                  </div>
                </el-col>
              </el-col>
            </el-row>
            <el-col v-else>
              <div class="service-item">
                <div class="service-item__icon">
                  <img :src="baseUrl + item.imageUrl" alt="" />
                </div>
                <div class="service-item__text">
                  <h3>{{ item.subTitle }}</h3>
                  <p class="ellipsis5line" :title="item.subTitleDescribe">{{ item.subTitleDescribe }}</p>
                </div>
              </div>
            </el-col>
          </el-col>
        </el-row>
      </div>
      <div class="content-maps">
        <h3>{{ defaultText.a }}</h3>
        <p>{{ defaultText.b }}</p>
        <div>
          <img src="~/assets/image/afterSale/after-sale-map2.png" alt="" />
        </div>

      </div>
      <!-- <img v-if="'en' === $i18n.locale" src="~/assets/image/afterSale/after-sale-banner3_en.jpg" alt="" srcset=""/>
      <img v-if="'es' === $i18n.locale" src="~/assets/image/afterSale/after-sale-banner3_es.png" alt="" srcset=""/>
      <img v-if="'ar' === $i18n.locale" src="~/assets/image/afterSale/after-sale-banner3_ar.png" alt="" srcset=""/> -->
      <!-- <div class="content-item">
        <h3>{{ defaultText.g }}</h3>
        <AboutImageBanner :imageList="detailData.items" />
      </div> -->
    </div>
    <div class="content-questionnaire">
      <h3>{{ defaultText.h }}</h3>
      <p>{{ defaultText.i }}</p>
      <button class="btn btn--small" @click="getCountryList">{{ defaultText.j }}</button>
    </div>
    <el-dialog width="650px" custom-class="after-sale-dialog" destroy-on-close :close-on-click-modal="false" :show-close="false" :visible.sync="dialogVisible" :before-close="handleDialogClose">
      <div slot="title">
        <i class="el-icon-close"></i>
      </div>
      <div class="dialog-content">
        <div class="dialog-content__title">
          <img src="~/assets/image/logo.png" alt="BAIC" />
          <i class="el-icon-close" @click="handleDialogClose"></i>
        </div>
        <div class="dialog-content__text">
          <span>{{ defaultText.k }}</span>
          <span>{{ defaultText.l }}</span>
        </div>
      </div>
      <el-descriptions :colon="false">
        <el-descriptions-item v-for="(item, index) in countryList" :key="index">
          <el-badge is-dot type="info" />
          <a :href="item.linkUrl" target="_blank">{{ item.name }}</a>
        </el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script>
import mix from '~/utils/Mix'

export default {
  name: 'afterSale',
  layout: 'main',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      fullLoading: false,
      detailData: {
        content: '',
        title: '',
        titleDescribe: '',
        intros: [],
        intros1: [],
        intros2: [],
        intros3: [],
        items: [],
      },
      dialogVisible: false, // 弹窗显示隐藏
      countryList: [], // 国家列表
      // 其他
      defaultText: {
        a: 'Multiple After-Sales Service Points Around The World',
        b: '45 after-sales service channels and 189 service dealers',
        c: 'Differentiated Featured Services',
        d: 'According to the needs of customers in different countries, with the concept of “',
        e: 'Reliability and Temperature',
        f: '", provide differentiated services',
        g: 'After-sales Service Business',
        h: 'Please Click Yo Fill In The Questionnaire',
        i: '45 after-sales service channels and 189 service dealers',
        j: 'SUBMIT',
        k: 'Welcome to our customer satisfaction survey.',
        l: 'Please choose the survey according to your region.',
      },
      allLanguages: {
        en: {
          a: 'Multiple After-Sales Service Points Around The World',
          b: '45 after-sales service channels and 189 service dealers',
          c: 'Differentiated Featured Services',
          d: 'According to the needs of customers in different countries, with the concept of “',
          e: 'Reliability and Temperature',
          f: '", provide differentiated services',
          g: 'After-Sales Service Business',
          h: 'Please Click To Fill In The Questionnaire',
          i: '45 after-sales service channels and 189 service dealers',
          j: 'SUBMIT',
          k: 'Welcome to our customer satisfaction survey.',
          l: 'Please choose the survey according to your region.',
        },
        es: {
          a: 'Múltiples puntos de postventa en todo el mundo',
          b: '45 canales de postventa y 189 puntos de atención',
          c: 'Servicios destacados diferenciados',
          d: 'De acuerdo con las necesidades de los clientes en diferentes países, con el concepto de "',
          e: 'Confiabilidad y Temperatura',
          f: '", brindar servicios diferenciados',
          g: 'Negocio de posventa',
          h: 'Por favor haga clic para completar el cuestionario',
          i: '45 canales de postventa y 189 puntos de atención',
          j: 'ENVIAR',
          k: 'Bienvenido a nuestra encuesta de satisfacción del cliente.',
          l: 'Por favor elige el encuesta de acuerdo a su región.',
        },
        ar: {
          a: 'نقاط متعددة لما بعد البيع حول العالم',
          b: '45 قناة ما بعد البيع و 189 منفذ خدمة',
          c: 'خدمات مميزة مميزة',
          d: 'وفقًا لاحتياجات العملاء في مختلف البلدان ، بمفهوم "',
          e: 'الموثوقية ودرجة الحرارة',
          f: '"، تقديم خدمات متميزة',
          g: 'أعمال ما بعد البيع',
          h: 'الرجاء الضغط لملء الاستبيان',
          i: '45 قناة ما بعد البيع و 189 منفذ خدمة',
          j: 'إرسال',
          k: 'مرحبا بك في استبيان رضا العملاء.',
          l: 'الرجاء اختيار مسح وفقًا لمنطقتك.',
        },
      },
    }
  },
  created() {
    this.fullLoading = true
    this.$api.news
      .getAboutSubMenuAfterSale()
      .then(res => {
        if (res.code === 200) {
          this.detailData.content = res.data.content
          this.detailData.title = res.data.title
          this.detailData.titleDescribe = res.data.titleDescribe
          this.detailData.items = []
          if (res.data.items) {
            res.data.items.map(item => {
              let image = {}
              image.imgUrl = `${this.baseUrl}${item.imageUrl}`
              image.alt = item.name
              this.detailData.items.push(image)
            })
          }
          this.detailData.intros1 = JSON.parse(JSON.stringify(res.data.intros?.length ? res.data.intros : [])).slice(0, 1)
          this.detailData.intros2 = JSON.parse(JSON.stringify(res.data.intros?.length ? res.data.intros : [])).slice(1, 3)
          this.detailData.intros3 = JSON.parse(JSON.stringify(res.data.intros?.length ? res.data.intros : [])).slice(-1)
          this.detailData.intros = [...this.detailData.intros1, [[...this.detailData.intros2], ...this.detailData.intros3]]
        }
      })
      .catch(err => {
        this.$message.error(err.msg || 'Request failed')
      })
      .finally(() => {
        this.fullLoading = false
      })
  },
  mixins: [mix],
  methods: {
    handleDialogClose() {
      this.dialogVisible = false
    },
    getCountryList() {
      this.$api.news
        .getAboutSubMenuAfterSaleCountryList()
        .then(res => {
          if (res.code === 200) {
            this.dialogVisible = true
            this.countryList = res.data
          }
        })
        .catch(err => {
          this.$message.error(err.msg || 'Request failed')
        })
    },
  },
}
</script>
<style lang="scss">
.afterSale__page {
  background-color: #222833;
  .banner {
    margin: 0 auto;

    img {
      width: 100%;
      margin: 0 auto;
    }
  }

  h1,
  h3 {
    font-size: 42px;
    line-height: 48px;
    color: #fff;
  }

  p {
    font-size: 18px;
    line-height: 36px;
    color: #fff;
  }
  .pre-text{
    white-space: pre;
  }

  .max-1680 {
    text-align: center;

    .content-text {
      margin-top: 100px;
      word-break: break-word;
      padding: 0 10%;

      h1 {
        margin-bottom: 24px;
      }

      p {
        line-height: 28px;
      }
    }

    .content-service {
      margin-top: 70px;

      .el-col-9 {
        .service-item {
          height: 870px;

          > div {
            height: 870px;
          }
        }
      }

      .el-col-11 {
        width: 100%;
      }

      .el-col-12 {
        .service-item {
          height: 410px;

          > div {
            height: 410px;
          }
        }
      }

      .el-col-13 {
        width: 100%;
        margin-top: 11px;

        .service-item {
          height: 450px;

          > div {
            height: 450px;
          }
        }
      }

      .el-col {
        .service-item {
          position: relative;

          .service-item__icon {
            img {
              width: 100%;
              height: 100%;
              object-fit: cover;
            }
          }

          .service-item__text {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            z-index: 1;
            color: #fff;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-end;
            padding: 0 20%;

            h3 {
              font-size: 26px;
              line-height: 28px;
              margin-bottom: 0px;
            }

            p {
              line-height: 24px;
              margin-bottom: 20px;
              margin-top: 10px;
            }
          }
        }
      }
    }

    .content-maps {
      margin-top: 100px;
      & > div {
        display: flex;
        align-items: center;
        justify-content: center;

        img {
          width: 100%;
          max-width: 1100px;
        }
      }
      h3 {
        margin: 30px 0;
      }

      p {
        margin-bottom: 120px;
      }

      div {
        margin-bottom: 100px;
      }
    }
  }

  .content-item {
    margin-top: 70px;
    text-align: center;

    h3 {
      margin-bottom: 70px;
    }

    //el-carousel__item is-active is-in-stage is-animating"
    .el-carousel__item-card {
      //transform: scale(1) !important;
    }

    .el-carousel__container {
      > div {
        &:first-of-type {
          //margin-left : 7%;
        }
      }
    }

    .el-carousel__item {
      &:first-of-type,
      &:last-of-type {
        //margin-left: 7%;
      }

      &.is-animating {
        //margin-left: -7%;
      }

      &.is-active {
        //transform: scale(.83) !important;
        //width       : 80%;
        //margin-left : 10%;

        & + .el-carousel__item {
          //margin-left : -7%;
        }

        //&-.el-carousel__item{
        //  margin-left: 7%;
        //}
      }

      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
  }

  .content-questionnaire {
    height: 650px;
    margin-top: 100px;
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background: url('../../../assets/image/afterSale/after-sale-questionnaire.png') 0 0 no-repeat;
    background-size: 100% 100%;
    color: #fff;

    h3 {
      margin-bottom: 30px;
    }

    p {
      margin-bottom: 44px;
    }
  }
}

.after-sale-dialog {
  .el-dialog__header {
    display: none;
  }

  .el-dialog__body {
    padding: 0;
  }

  .dialog-content {
    background: url('../../../assets/image/afterSale/after-sale-dialog.jpg') 0 0 no-repeat;
    background-size: 100% 100%;
  }

  .dialog-content__title {
    padding: 20px 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    > img{
      width: 130px;
    }

    > i {
      font-size: 30px;
      color: #fff;
      font-weight: bold;
      cursor: pointer;
    }
  }

  .dialog-content__text {
    padding: 100px 0 145px 0;
    text-align: center;

    > span {
      display: block;
      font-size: 18px;
      font-weight: bold;
      color: #fff;
      text-shadow: 0 0 15px rgba(0, 0, 0, 0.71);
    }
  }

  .el-descriptions{
    margin: 60px 0;
  }
  .el-badge__content.is-dot {
    top: 50%;
    margin: -2px 5px 0 40px;
  }
}

.xs {
  .afterSale__page {
    h1,
    h3 {
      font-size: 24px;
      line-height: 34px;
    }

    p {
      font-size: 18px;
      line-height: 28px;
    }

    .max-1680 {
      text-align: center;

      .content-text {
        margin-top: 50px;
        word-break: break-word;
        padding: 0 10%;

        h1 {
          margin-bottom: 24px;
        }

        p {
          line-height: 24px;
        }
      }

      .content-service {
        margin-top: 50px;

        .el-col-9 {
          .service-item {
            height: 435px;

            > div {
              height: 435px;
            }
          }
        }

        .el-col-11 {
          width: 100%;
        }

        .el-col-12 {
          .service-item {
            height: 200px;

            > div {
              height: 200px;
            }
          }
        }

        .el-col-13 {
          width: 100%;
          margin-top: 11px;

          .service-item {
            height: 225px;

            > div {
              height: 225px;
            }
          }
        }

        .el-col {
          .service-item {
            position: relative;

            .service-item__icon {
              img {
                width: 100%;
                height: 100%;
                object-fit: cover;
              }
            }

            .service-item__text {
              width: 100%;
              height: 100%;
              position: absolute;
              top: 0;
              left: 0;
              z-index: 1;
              color: #fff;
              display: flex;
              flex-direction: column;
              align-items: center;
              justify-content: flex-end;
              padding: 0 5%;

              h3 {
                font-size: 16px;
                line-height: 22px;
                margin-bottom: 15px;
              }

              p {
                line-height: 16px;
                margin: 0px 0 15px;
                display: none;
              }
            }
          }
        }
      }

      .content-maps {
        margin-top: 50px;

        h3 {
          margin: 30px 0;
        }

        p {
          margin-bottom: 60px;
        }

        div {
          margin-bottom: 50px;
        }
      }
    }

    .content-item {
      margin-top: 70px;
      text-align: center;

      h3 {
        margin-bottom: 50px;
      }

      .el-carousel__container {
        height: 250px !important;
      }

      //el-carousel__item is-active is-in-stage is-animating"
      .el-carousel__item-card {
        //transform: scale(1) !important;
      }

      .el-carousel__container {
        > div {
          &:first-of-type {
            //margin-left : 7%;
          }
        }
      }

      .el-carousel__item {
        &:first-of-type,
        &:last-of-type {
          //margin-left: 7%;
        }

        &.is-animating {
          //margin-left: -7%;
        }

        &.is-active {
          //transform: scale(.83) !important;
          //width       : 80%;
          //margin-left : 10%;

          & + .el-carousel__item {
            //margin-left : -7%;
          }

          //&-.el-carousel__item{
          //  margin-left: 7%;
          //}
        }

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }
    }

    .content-questionnaire {
      height: 250px;
      margin-top: 30px;
      text-align: center;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      background: url('../../../assets/image/afterSale/after-sale-questionnaire.png') 0 0 no-repeat;
      background-size: 100% 100%;
      color: #fff;

      h3 {
        margin-bottom: 0px;
        line-height: 30px;
      }

      p {
        margin-top: 10px;
        margin-bottom: 20px;
      }
    }
  }

  .after-sale-dialog {
    width: 90% !important;

    .dialog-content__title {
      padding: 10px;
      > img {
        width: 110px;
      }
      > i {
        font-size: 22px;
      }
    }

    .dialog-content__text {
      width: 100%;
      padding: 40px 10px 60px 10px;
      text-align: center;

      > span {
        font-size: 12px;
      }
    }

    .el-descriptions{
      margin: 30px 0;

      a{
        font-size: 12px;
      }
    }

    .el-badge__content.is-dot {
      top: 50%;
      margin: -2px 5px 0 10px;
    }


  }
}


</style>
