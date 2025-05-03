<template>
  <h1 class="logo-container" @click="logoGoogle">
    <nuxt-link :to="localePath('/', $i18n.locale)" class="site-header__logo-link">
      <img
        class="small--hide"
        src="~/assets/image/logo.png"
        alt=""
        itemprop="logo">
    </nuxt-link>
    <span class="visually-hidden">BASIC</span>
  </h1>
</template>

<script>

export default {
  data () {
    return {}
  },
  methods: {
    logoGoogle () {
      this.$gtag.event('BaicClick', {
        'event_category': 'BaicClick',
        'event_label': 'logoClick'
      })
    }
  }
}
</script>

<style scoped lang="scss">
.site-header__logo-link {
  display: block;
  width: 80px;
  height: 33.5px;
  position: relative;
  z-index: 10;
}
</style>
