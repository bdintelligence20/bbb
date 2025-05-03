<template>
  <el-drawer
    class="nav-drawer"
    title=""
    :visible.sync="drawer"
    :before-close="handleClose"
    :size= 350
    direction="rtl">
    <el-collapse accordion>
      <el-collapse-item v-for="(link, index) in mainMenu" :key="link.id" :disabled="link.children.length == 0 || link.menuType == 1" :class="link.menuType == 1 ? 'el-collapse-item__car': ''">
        <template slot="title">
          <a v-if="link.menuType == 1" class="link" @click="innerDrawer = true">{{ link.name }}</a>
          <nuxt-link
            v-else :to="localePath(link.path, $i18n.locale)"
            :class="{'site-nav__dis': link.children && link.children.length > 0}"
            class="link">{{ link.name }}</nuxt-link>
        </template>
        <el-collapse v-if="link.children && link.children.length > 0 && link.menuType != 1">
          <el-collapse-item v-for="(childlink, index) in link.children" :key="childlink.id" :disabled="childlink.children.length == 0">
            <template slot="title">
              <nuxt-link :to="localePath({path:childlink.path,query:{id:childlink.id},params:{id:childlink.id}}, $i18n.locale)" class="link" @click="handleClose">{{ childlink.name }}</nuxt-link>
            </template>
            <el-collapse v-if="childlink.children && childlink.children.length > 0">
              <el-collapse-item v-for="(grandchildlink, index) in link.children" :key="grandchildlink.id" :disabled="grandchildlink.children.length == 0"></el-collapse-item>
              <template slot="title">
                <nuxt-link :to="localePath(grandchildlink.path, $i18n.locale)" @click="handleClose">{{ grandchildlink.name }}</nuxt-link>
              </template>
            </el-collapse>
          </el-collapse-item>
        </el-collapse>
      </el-collapse-item>
    </el-collapse>
    <LangSelectorVue />
    <el-drawer
      :size="350"
      :append-to-body="true"
      :visible.sync="innerDrawer"
      :class="$mq"
      class="inner-drawer" @click="handleClose">
      <template slot="title">
        <span @click="innerDrawer = false" class="closeBtn"><i class="el-icon-back"></i></span>
      </template>
      <ProductMenuVue :type="'drawer'" @closeInnerDrawer="closeInnerDrawer" :productMenu="productMenu"></ProductMenuVue>
    </el-drawer>
  </el-drawer>
  
</template>
<script>
import ProductMenuVue from './ProductMenu.vue';
import LangSelectorVue from './LangSelector'
export default {
  components: { ProductMenuVue, LangSelectorVue },
  props: {
    drawer: {
      type: Boolean,
      default: false
    },
    mainMenu: {
      type: Array,
      default: []
    },
    productMenu: {
      type: Array,
      default: []
    }
  },
  data() {
    return {
      innerDrawer: false,
    }
  },
  methods: {
    handleClose() {
      this.$emit('closeDrawer', false)
    },
    closeInnerDrawer() {
      this.innerDrawer = false
    },
  },
}
</script>
<style scoped lang="scss">
.link {
  width: calc(100% - 30px);
  display: inline-block;
}
.closeBtn {
  font-size: 24px;
  display: block;
  color: #000;
}

</style>
