<template>
  <div class="menu-container">
    <el-dropdown
      placement="top-start"
      trigger="click"
      :popper-append-to-body="false"
      :class="{'dropdown-bg': showMenu}"
      @visible-change="visibleChange"
    >
      <el-button class="dark-trigger">
        {{$t('footer.friendLink')}}
        <i :class="showMenu ? 'el-icon-caret-bottom' : 'el-icon-caret-top'"></i>
      </el-button>

      <el-dropdown-menu
        slot="dropdown"
        class="top-dark-menu"
      >
        <el-dropdown-item
          v-for="(item, index) in menuItems"
          :key="index"
          class="menu-item"
        >
          <a
            :href="item.url"
            target="_blank"
            rel="noopener noreferrer"
            class="title"
          >
            {{ item.name }}
          </a>
          <!-- <div class="title">{{ item.name }}</div> -->
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuItems: [
        { name: 'ARCFOX', url: 'https://www.arcfox.com.cn/home_en.html' },
        { name: 'BAIC CHINA', url: 'https://www.beijingauto.com.cn/' },
        { name: 'BAIC GROUP', url: 'https://www.baicgroup.com.cn/en' },
      ],
      showMenu: false
    }
  },
  methods: {
    visibleChange(val) {
      console.log(val);
      this.showMenu = val
      if (val) {
        this.$nextTick(() => {
          const trigger = document.querySelector('.dark-trigger')
          const dropdown = document.querySelector('.top-dark-menu')
          if (trigger && dropdown) {
            dropdown.style.width = `${trigger.offsetWidth}px`
          }
        })
      }
    }
  }
}
</script>

<style scoped lang="scss">
::v-deep .el-dropdown {
  min-width: unset;
  background-color: transparent;
  transition: all .2s;
  border-radius: 0 0 10px 10px;
}

::v-deep .dropdown-bg {
  background-color: #35363b;
}

.dark-trigger {
  background: #424348;
  border: 1px solid #404040;
  color: #c7c7c7;
  border-radius: 10px;
  padding: 2px 12px;
  white-space: normal;
}

/* 菜单容器样式 */
.top-dark-menu {
  background: #35363b;
  border: none;
}

/* 菜单项样式 */
.menu-item {
  position: relative;
  display: flex;
  justify-content: center;
  color: #c7c7c7;
  padding: 0;
  text-align: center;
  &:not(:last-of-type)::after {  /* 除最后一项外添加分割线 */
    content: '';
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    width: 50%;
    height: 1px;
    background: rgba(255,255,255,0.1);
  }
}
.title {
  color: #c7c7c7;
}

/* 隐藏默认箭头 */
::v-deep .popper__arrow {
  display: none;
}

/* 菜单顶部圆角 */
.top-dark-menu {
  border-radius: 10px 10px 0 0;
}

.el-dropdown-menu {
  padding: 0;
  margin: 0;
}

.el-dropdown-menu__item:focus,
.el-dropdown-menu__item:not(.is-disabled):hover {
  background-color: transparent;
  a {
    color: #fff;
  }
}

@media screen and (max-width: 768px) {
  .el-dropdown {
    margin: 0 5px;
    .el-button {
      white-space: normal;
    }
  }
  .dark-trigger {
    color: #fff;
    padding: 2px 5px;
  }
  .menu-item {
    padding: 0;
  }
}
</style>