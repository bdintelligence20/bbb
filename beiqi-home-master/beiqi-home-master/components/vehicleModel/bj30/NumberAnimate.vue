<template>
    <animate-number
      ref="animateNumber"
      :duration="800"
      from="0"
      :to="number"
      :formatter="formatterValue"
    />
</template>

<script>
export default {
  props: {
    number: {
      type: Number,
      default: 0
    },
    // 小数位数
    decimal: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {};
  },
  mounted() {},
  methods: {
    formatterValue(num) {
      return num.toFixed(this.decimal)
    },
    startAnimate() {
      this.$refs.animateNumber.start()
    }
  },
};
</script>

<style scoped lang="scss"></style>
