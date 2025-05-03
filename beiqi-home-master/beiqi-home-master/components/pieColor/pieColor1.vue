<template>
  <div class="pie-color1" :style="`width:${radius}px;height:${radius}px;background-color: ${rgbCode[0]};`"></div>
</template>
<script>

export default {
  name: 'colorPie1',
  props: {
    rgbCode: {
      type: Array,
    },
    radius: {
      type: [Number, String],
      default: "10"
    }
  },
  data() {
    return {
    };
  },
};
</script>
<style>
.pie-color1 {
  /* width: 35px; */
  /* height: 35px; */
  border-radius: 50%;
  margin: 0 auto;
  /* background-color: red; */
}


</style>
