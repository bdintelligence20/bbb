<template>
  <div class="layout-footer">
    <mq-layout
v-if="webConfig && webConfig.title && footerLinkList && footerLinkList.length>0" :mq="['lg', 'xl']"
               class="pc">
      <div class="logo">
        <h3>
          <nuxt-link :to="localePath('/', $i18n.locale)">
            <img alt="" src="~/assets/image/logo.png" srcset=""/>
          </nuxt-link>
        </h3>
      </div>
      <el-divider class="title-divider"/>
      <div class="main-container">
        <div class="footer-list max-1680">
          <div v-for="link in footerLinkList" :key="link.name" class="footer-item">
            <FooterLink :link-list="link.children" :title="link.name"></FooterLink>
          </div>
          <!--          <div class="footer-item">-->
          <!--            <FooterLinkLogo></FooterLinkLogo>-->
          <!--          </div>-->
        </div>
        <el-divider class="content-divider"/>
        <div class="footer-filing max-1680">
          <div class="filling-left">
            <div class="left-info">
              <div class="copy-right">Copyright@2023 Beijing Automotive Group Co., Ltd.</div>
              <a class="text-color icp-num" href="http://beian.miit.gov.cn" target="_blank">{{ webConfig.icpNum }}</a>
            </div>
            <a
class="record-num" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11011302005196"
               target="_blank">
              <img
v-lazy="require('~/assets/image/code.png')" alt="" srcset=""
                   style="display: inline-block; width: 20px; vertical-align: middle; margin-right: 5px"/>
              {{ webConfig.recordNum }}
            </a>
          </div>
          <div class="hot-link">
            <ul>
              <li>
                <nuxt-link :to="localePath('/footer/privacyStatement')">Privacy Statement</nuxt-link>
              </li>
              <li>
                <a @click="showCookies = true">Cookie Settings</a>
              </li>
              <li class="friendship-link">
                <footer-friendship-link />
              </li>
            </ul>
          </div>
        </div>
      </div>
    </mq-layout>

    <mq-layout
v-if="webConfig && webConfig.title && footerLinkList && footerLinkList.length>0" :mq="['md', 'xs', 'sm']"
               class="mobile">
      <h3>
        <nuxt-link :to="localePath('/', $i18n.locale)" class="logo">
          <img alt="" src="~/assets/image/logo.png" srcset=""/>
        </nuxt-link>
      </h3>
      <el-collapse v-model="activeNames" accordion class="footer-collapse">
        <el-collapse-item v-for="(link, index) in footerLinkList" :key="link.name" :name="index" :title="link.name">
          <FooterLink :link-list="link.children"></FooterLink>
        </el-collapse-item>
      </el-collapse>
      <!--      <FooterLogo :webCnnfig="webConfig"></FooterLogo>-->
      <div class="hot-link">
        <ul>
          <li>
            <nuxt-link :to="localePath('/footer/privacyStatement')">Privacy Statement</nuxt-link>
          </li>
          <li>
            <a @click="showCookies = true">Cookie Settings</a>
          </li>
          <li class="friendship-link">
            <footer-friendship-link />
          </li>
        </ul>
      </div>
      <div class="filling-left">
        <div class="left-info">
          <div class="copy-right">Copyright@2023 Beijing Automotive Group Co., Ltd.</div>
          <a class="text-color icp-num" href="http://beian.miit.gov.cn" target="_blank">{{ webConfig.icpNum }}</a>
          <a
class="record-num" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11011302005196"
             target="_blank">
            <img
alt="" src="~/assets/image/code.png" srcset=""
                 style="display: inline-block; width: 12px; vertical-align: middle; margin-right: 3px"/>
            {{ webConfig.recordNum }}
          </a>
        </div>
      </div>
    </mq-layout>

    <footer-set-cookies :show-cookies="showCookies" @changeMask="changeMask"/>
  </div>
</template>

<script>
import Vue from 'vue'
import FooterLogo from '~/components/footer/FooterLogo.vue'
import FooterLink from '~/components/footer/FooterLink.vue'
import FooterLinkLogo from '~/components/footer/FooterLinkLogo.vue'

export default Vue.extend({
  components: {
    FooterLogo,
    FooterLink,
    FooterLinkLogo
  },
  props: {
    webConfig: {
      type: Object,
      default: {},
    },
    footerLinkList: {
      type: Array,
      default: [],
    },
  },
  data() {
    return {
      activeNames: '0',
      showCookies: false
    }
  },
  created() {
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll)
  },
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll)
  },
  methods: {
    handleScroll() {
      const scrollTop = document.documentElement.scrollTop || document.body.scrollTop
      if (localStorage.getItem('cookies')) {
        return
      }
      if (scrollTop > 200) {
        this.showCookies = true
      }
    },
    changeMask() {
      this.showCookies = !this.showCookies
    }
  },
})
</script>

<style lang="scss" scoped>
.layout-footer {
  position: relative;
  background: #26292d;
  padding-top: 60px;
  margin-top: 0px;
  z-index: 10;
  .logo {
    margin: 0 120px;
  }

  .el-divider {
    background: #474c52;
  }

  .title-divider {
    margin: 40px 0;
  }

  .content-divider {
    width: 95%;
    margin: 0 auto;
  }


  .footer-list {
    padding-bottom: 30px;
    margin: 0 120px;
    display: flex;

    .footer-item {
      flex: 1;
    }

    .footer-item:last-child {
      flex: 0 1 188px;
    }
  }

  .footer-filing {
    display: flex;
    justify-content: space-between;
    margin: 0 120px;
    padding: 48px 0 30px;
    text-align: center;
    flex-wrap: wrap;

    .filling-left {
      display: flex;
      color: #fff;

      .left-info {
        text-align: left;

        .copy-right {
          margin-bottom: 10px;
        }
      }

      .record-num {
        margin-left: 24px;
      }
    }

    img {
      max-width: 130px;
    }

    a {
      padding-right: 30px;
      //color: #c7c7c7;
      color: #fff;

      &:hover {
        color: #fff;
      }
    }
  }

  .hot-link {
    flex: 1;
    display: flex;
    justify-content: flex-end;
    .friendship-link {
      padding: 0 15px;
    }


    ul {
      display: flex;
      justify-content: space-evenly;
      align-items: center;

      a {
        color: #c7c7c7;
        cursor: pointer;
        border-right: 2px solid #393a3e;
        padding: 0 15px;
        display: flex;
      }

      li:last-child {
        a {
          border-right: none;
        }
      }
    }
  }

  ::v-deep .el-collapse-item__header {
    padding: 0 15px;
    font-size: 14px;
    font-weight: 700;
    height: 50px;
    line-height: 50px;
  }

  ::v-deep .el-collapse-item__content {
    padding-bottom: 15px;
  }

  //移动端
  .mobile {
    background: #25292e;

    .logo {
      display: inline-block;
      width: 20%;
      margin: 25px 15px;
    }

    :deep .el-collapse {
      border-top: 1px solid #34393e;
    }

    :deep .el-collapse-item__header {
      background: #25292e;
      color: #fff;
      border-bottom: 1px solid #34393e;
    }

    // :deep .aside-bar {
    //   position: static;

    //   .aside-list {
    //     display: flex;
    //     justify-content: center;

    //     li {
    //       margin: 28px 0;
    //       margin-right: 26px;

    //       &:last-child {
    //         margin-right: 0;
    //       }
    //     }
    //   }
    // }

    .filling-left {
      //display: flex;
      color: #fff;
      font-size: 10px;
      line-height: 1.8;
      padding-bottom: 20px;
      text-align: center;

      .left-info {
        text-align: center;
      }

      .icp-num {
        margin-right: 10px;
      }

      a {
        color: #fff;
      }
    }

    .tablist {
      border-top: none;
    }

    .layout-footer {
      padding-top: 70px;
    }

    .logo-box {
      padding: 15px;
    }

    .link-box {
      padding: 15px 15px 0 15px;
      text-align: left;

      .link-list {
        li {
          margin-bottom: 10px;
        }
      }
    }
  }
}

.md,
.xs,
.sm {
  .layout-footer {
    padding-top: 25px;
    margin-top: 0;
    background-color: rgba($color: #000000, $alpha: 0);

    .hot-link {
      display: flex;
      justify-content: center;
      padding: 15px 15px;
      .friendship-link {
        padding: 2px 5px;
      }

      a {
        color: #fff;
        text-align: center;
        padding: 2px 5px;
      }
    }
  }
}

.lang___ar {
  .layout-footer .hot-link ul a {
    border-left: 2px solid #393a3e;
    border-right: none;
  }
}
</style>
