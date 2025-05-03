<template>
  <div>
    <div class="pie-color2" 
    :style="`width:${radius}px;height:${radius}px;background:linear-gradient(-45deg, transparent 50%, ${rgbCode[0]} 50%),linear-gradient(135deg, transparent 50%, ${rgbCode[1]} 50%);`">
    </div>
  </div>
</template>
<script>
export default {
  name: 'pieColor2',
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
.pie-color2 {
  /* width: 35px;
  height: 35px; */
  margin: 0 auto;
  border-radius: 50%;
  transform: translate(0%, 0%);
  /* background:
    linear-gradient(-45deg, transparent 50%, yellow 50%),
    linear-gradient(135deg, transparent 50%, red 50%); */

}
</style>