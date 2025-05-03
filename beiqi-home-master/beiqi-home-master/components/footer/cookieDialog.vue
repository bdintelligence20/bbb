<template>
  <div v-show="dialogVisible" class="d-flag">
    <div class="dlog">
      <div class="close-btn" @click="closeDialog">X</div>
      <div class="d-body">
        <div ref="setMask" class="set_up_mask">
          <slot name="content"></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'cookieDialog',
  props: {
    dialogVisible: Boolean,
    type: String,
  },
  data() {
    return {}
  },

  mounted() {},

  methods: {
    closeDialog() {
      this.$emit('closeDialog')
    },
  },
}
</script>

<style lang="scss" scoped>
.d-flag {
  width: 100%;
  height: 100%;
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 2;
}

.dlog {
  width: 71%;
  position: absolute;
  background-color: #fff;
  bottom: 5%;
  left: 0;
  right: 0;
  margin: auto;
  border-radius: 10px;
  overflow-y: auto;
  max-height: 86%;

  .close-btn {
    position: absolute;
    right: 3%;
    width: 3%;
    padding: 1.3% 0 0;
    font-size: 1.5em;
    cursor: pointer;
  }

  .d-body {
    width: 100%;
    padding: 0 10% 2%;
    box-sizing: border-box;
    margin-top: 5%;
  }
}

@media screen and (max-width: 768px) {
  .d-flag {
    .dlog {
      width: 90%;
    }

    .d-body {
      width: 100%;
      padding: 1% 8% 8%;
      box-sizing: border-box;
      margin-top: 5%;
    }
  }
}
</style>
