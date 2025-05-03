<template>
  <div class="maintenance">
    <div class="title">BAIC always be with you.</div>
    <img src="@/assets/image/maintenance/image.png" />
    <div class="text1">The official website of BAIC brand is being revised and upgraded in order to obtain better browsing experience and system security.</div>
    <div class="text2">So please wait patiently for a while......</div>
  </div>
</template>
<script>
export default {
  props: {
    showHtml: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    toTop() {
      document.body.scrollTop = document.documentElement.scrollTop = 0
    },
  },
}
</script>
<style scoped lang="scss">
.maintenance {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: white;
  z-index: 1000;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  overflow-y: scroll;
  padding: 0 20px;
  .title {
    font-size: 32px;
    color: #d1291a;
    font-weight: 600;
    font-style: italic;
    text-align: center;
    margin-bottom: 5px;
  }
  & > img {
    max-width: 600px;
    margin-bottom: 20px;
  }
  .text1 {
    font-size: 22px;
    color: #000;
    max-width: 700px;
    text-align: center;
    margin-top: 20px;
  }

  .text2 {
    font-size: 28px;
    color: #000;
    text-align: center;
    margin-top: 20px;
  }
}

.md,
.xs,
.sm {
  .maintenance {
    
    .title {
      font-size: 24px;
      color: #d1291a;
      margin-bottom: 5px;
      font-weight: 600;
      font-style: italic;
    }
    & > img {
      max-width: 100%;
      margin-bottom: 20px;
    }
    .text1 {
      font-size: 16px;
      color: #000;
      max-width: 700px;
      text-align: center;
      margin-top: 20px;
    }

    .text2 {
      font-size: 20px;
      color: #000;
      margin-top: 20px;
    }
  }
}
</style>
