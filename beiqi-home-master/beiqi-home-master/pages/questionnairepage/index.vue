<template>
  <div class="media-center-page">
      <!-- <iframe class="frame"  src='https://www.wjx.cn/vm/PpKMRYF.aspx?width=760&source=iframe&s=t' width='799' height='750' frameborder='0' style='overflow:auto'></iframe> -->
      <!-- en英语问答卷 -->
      <iframe ref="ifr"  v-if="'en' === $i18n.locale" class="frame" src='https://www.wjx.cn/vm/PpKMRYF.aspx?width=760&source=iframe&s=t'
      onload="this.style.display='block'" width='799' height='400' frameborder='0' style='overflow:auto'></iframe>
      <!-- es西班牙问答卷 -->
      <iframe ref="ifr"  v-if="'es' === $i18n.locale" class="frame" src='https://www.wjx.cn/vm/eUnFAta.aspx?width=760&source=iframe&s=t'
      onload="this.style.display='block'" width='799' height='800' frameborder='0' style='overflow:auto'></iframe>
      <!-- ar阿拉伯问答卷 -->
      <iframe ref="ifr"  v-if="'ar' === $i18n.locale" class="frame" src='https://www.wjx.cn/vm/tG4DoaX.aspx?width=760&source=iframe&s=t'
      onload="this.style.display='block'" width='799' height='800' frameborder='0' style='overflow:auto'></iframe>
    </div>
</template>

<script>
export default {
  name: 'questionnairepage',
  data() {
    return {
    }
  },
  
}
</script>

<style lang="scss" scoped>
  .media-center-page {
    height:100vh;
    overflow: hidden;
    -webkit-overflow-scrolling: touch; 
    .frame {
      width: 100%;
      height: 100%;
    }
  }
</style>