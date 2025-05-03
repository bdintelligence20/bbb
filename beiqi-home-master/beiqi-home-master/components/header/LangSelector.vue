<template>
  <div class="lang-item">
    <el-dropdown class="lang-switcher" @click="langGoogle" trigger="click" @command="handleCommand">
      <span class="el-dropdown-link">
        {{ $i18n.localeProperties.name }}<i class="el-icon-caret-bottom"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item v-for="(locale, i) in $i18n.locales" :key="i">
          <nuxt-link :to="switchLocalePath(locale.code)" class="link">
            {{ locale.name }}
          </nuxt-link>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  data () {
    return {}
  },
  methods: {
    // 调查问卷弹框  切换语言初始化
    handleCommand() {
      localStorage.setItem('dialogQuestionnaire',false);
    },
    langGoogle() {
      this.$gtag.event('LanguageClick', {
        'event_category': 'LanguageClick',
        'event_label': 'LanguageClick'
      })
    }
  }
}
</script>

<style scoped lang="scss">
.lang-switcher {
  z-index: 8;
  .el-dropdown-link {
    cursor: pointer;
    color: rgba($color: #252525, $alpha: 0.9);
    font-weight: 600;
  }
  
}
</style>

<style lang="scss">
.el-popper .el-dropdown-menu__item {
  padding: 0;
  a {
    display: block;
    padding: 0 20px;
  }
}
</style>
