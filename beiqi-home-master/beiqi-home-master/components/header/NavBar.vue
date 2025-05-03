<template>
  <ul class="site-nav" role="navigation" aria-label="Primary">
    <!-- site-nav--show-dropdown -->
    <li v-for="(link, index) in mainMenu" :key="link.id" class="site-nav__item" :class="[link.children && link.children.length > 0 ? 'site-nav--has-dropdown' : '', currentIndex === index ? 'site-nav--show-dropdown' : '', link.menuType === '1' ? 'site-nav--first' : '']" aria-haspopup="true" @click.stop="mennuGoogle(link.name)" @mouseenter="mouseenter(index)" @mouseleave="mouseleave">
      <div class="site-nav__item-container">
        <nuxt-link :to="localePath(link.path, $i18n.locale)" :class="{ 'site-nav__dis': link.children && link.children.length > 0 }" class="site-nav__link site-nav__link--has-dropdown site-nav__link--f">
          {{ link.name }}
        </nuxt-link>

        <div v-if="link.children && link.children.length > 0 && link.menuType !== '1'" class="site-nav__dropdown--menu">
          <div class="megamenu__cols">
            <div v-for="(childlink, index) in link.children" :key="childlink.id" class="megamenu__col appear-animation appear-delay-1">
              <div class="" @click.stop="mennuChildrenGoogle(childlink.name)" @click="handleHideMenu">
                <nuxt-link :to="localePath({ path: childlink.path, query: { id: childlink.id }, params: { id: childlink.id } }, $i18n.locale)" class="site-nav__dropdown-link site-nav__dropdown-link--top-level site-nav__dropdown-link--mega">{{ childlink.name }}</nuxt-link>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="link.children && link.children.length > 0" class="site-nav__dropdown megamenu text-left">
        <div v-if="link.menuType === '1' && currentIndex === index" class="max-1680">
          <!--          <ProductMenuVue :type="'bar'" :productMenu="productMenu" @handleHideMenu="handleHideMenu"></ProductMenuVue>-->
          <CarMenu v-if="productMenu && productMenu.length > 0" ref="carMenu" :key="menuKey" :product-menu="productMenu" @hideMenu="handleHideMenu" />
        </div>
        <div v-else class="megamenu__cols">
          <div v-for="(childlink, index) in link.children" :key="childlink.id" class="megamenu__col appear-animation appear-delay-1">
            <div class="" @click.stop="mennuChildrenGoogle(childlink.name)" @click="handleHideMenu">
              <nuxt-link :to="localePath({ path: childlink.path, query: { id: childlink.id }, params: { id: childlink.id } }, $i18n.locale)" class="site-nav__dropdown-link site-nav__dropdown-link--top-level site-nav__dropdown-link--mega">{{ childlink.name }}</nuxt-link>
            </div>
            <ul v-if="childlink.children && childlink.children.length > 0">
              <li v-for="(grandchildlink, index) in childlink.children" :key="grandchildlink.id" class="" @click="handleHideMenu">
                <nuxt-link :to="localePath(grandchildlink.path, $i18n.locale)" class="site-nav__dropdown-link">{{ grandchildlink.name }}</nuxt-link>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </li>
  </ul>
</template>

<script>
import ProductMenuVue from './ProductMenu.vue'
import CarMenu from './CarMenu'

export default {
  components: { ProductMenuVue, CarMenu },
  props: {
    drawer: {
      type: Boolean,
      default: false,
    },
    mainMenu: {
      type: Array,
    },
    productMenu: {
      type: Array,
    },
  },
  data() {
    return {
      activeDropdown: false,
      currentIndex: null,
      menuKey: 0,
    }
  },
  created() {},
  methods: {
    handleSelect(key, keyPath) {},
    mennuGoogle(name) {
      this.$gtag.event('menuClick', {
        event_category: 'menuClick',
        event_label: name,
      })
    },
    mennuChildrenGoogle(name) {
      this.$gtag.event('menuChildrenClick', {
        event_category: 'menuChildrenClick',
        event_label: name,
      })
    },
    mouseenter(index) {
      this.currentIndex = index
    },
    mouseleave() {
      // 清除选中状态
      this.menuKey++
      this.currentIndex = null
    },
    handleHideMenu() {
      this.currentIndex = null
    },
  },
}
</script>
<style scoped lang="scss">
.site-nav {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;

  .site-nav__item {
    position: static;

    &:hover {
      z-index: 7;
    }

    &.site-nav--has-dropdown:hover {
      .site-nav__link:before {
        content: '';
        position: absolute;
        left: 30px;
        right: 30px;
        bottom: -5px;
        display: block;
        background-color: #d1291a;
        height: 2px;
        z-index: 6;
      }
    }

    .site-nav__link {
      display: inline-block;
      vertical-align: middle;
      text-decoration: none;
      padding: 10px 32px;
      white-space: nowrap;
    }

    .site-nav__item-container {
      position: relative;
      display: flex;
      flex-direction: column;
      align-items: center;
      z-index: 10;

      .site-nav__dropdown--menu {
        position: absolute;
        padding: 5px 0 0px;
        margin-top: 58px;
        visibility: hidden;
        transform: translate3d(0px, -25px, 0px);
        .site-nav__dropdown-link {
          opacity: 1;
        }
      }
    }

    &.site-nav--show-dropdown {
      .site-nav__dropdown--menu {
        visibility: visible;
        transform: translateZ(0px);
        transition: all 500ms cubic-bezier(0.2, 0.06, 0.05, 0.95);
      }
    }
  }
}

.site-nav__dropdown {
  position: absolute;
  left: 0;
  top: 0;
  margin: 0;
  z-index: 5;
  display: block;
  visibility: hidden;
  background-color: rgba($color: #fff, $alpha: 0.9);
  min-width: 100%;
  padding: 10px 0 5px;
  //box-shadow:0px 10px 20px rgba(0, 0, 0, 0.09);
  transform: translate3d(0px, -25px, 0px);
}

.site-nav--show-dropdown {
  .site-nav__dropdown {
    display: block;
    visibility: visible;
    transform: translateZ(0px);
    transition: all 500ms cubic-bezier(0.2, 0.06, 0.05, 0.95);
  }
}
.site-nav--first {
  .site-nav__dropdown {
    background-color: rgba($color: #fff, $alpha: 0.9);
  }
}
.is-focused > .site-nav__dropdown li,
.site-nav--has-dropdown:hover .site-nav__dropdown li {
  opacity: 1;
  transition: opacity 0.5s ease;
}

.site-nav__dropdown li {
  margin: 0;
  opacity: 0;
  transition: none;
}

.site-nav__dropdown > li {
  position: relative;
}

.site-nav__dropdown > li > a {
  position: relative;
  z-index: 6;
}

.site-nav__dropdown a:not(.megamenu__promo-link) {
  // background-color: #fff;
  padding-right: 40px;
}

.megamenu {
  padding: 80px 50px 0 50px;
  line-height: 1.8;
}
.megamenu__cols {
  display: flex;
  justify-content: center;
}
.site-nav__dropdown-link {
  display: block;
  white-space: nowrap;
  padding: 16px 20px;
  font-size: 17px;
  transition: padding 0.2s ease;
  opacity: 0;
}
.site-nav__link:hover,
.site-nav__dropdown-link:hover {
  color: #d1291a;
}

.site-nav__link--f {
  z-index: 100 !important;
  position: relative;
  color: rgba($color: #252525, $alpha: 0.9);
  font-weight: bold;
}

.site-nav__dropdown {
  z-index: 5 !important;
}
</style>
<style lang="scss">
.site-header {
  &:hover {
    background: #fff;
    .site-nav--is-megamenu {
      .site-nav__link--f {
        color: #000;
      }
    }
    .lang-switcher {
      .el-dropdown-link {
        color: #000;
      }
    }
  }
  .site-nav--is-megamenu {
    &:not(.site-nav--has-dropdown) {
      &:hover {
        .site-nav__link--f {
          color: #d1291a;
        }
      }
    }
  }
}
</style>
