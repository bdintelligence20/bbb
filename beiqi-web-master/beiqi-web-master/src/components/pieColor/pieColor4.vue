<template>
  <div>
    <div class="pie-color4" :style="`width:${radius / 2}px;height:${radius / 2}px;
    border-right: ${radius / 2}px solid ${rgbCode[0]};
    border-left: ${radius / 2}px solid ${rgbCode[1]};
    border-top: ${radius / 2}px solid ${rgbCode[2]};
    border-bottom: ${radius / 2}px solid ${rgbCode[3]}`"></div>
  </div>
</template>
<script>
export default {
  name: 'pieColor4',
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
.pie-color4 {
  /* width: 17.5px;
  height: 17.5px; */
  border-radius: 50%;
  margin: 0 auto;
  /* border-right: 17.5px solid red;
  border-left: 17.5px solid green;
  border-top: 17.5px solid yellow;
  border-bottom: 17.5px solid blue; */
}
</style>