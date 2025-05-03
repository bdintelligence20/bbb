<template>
  <div>
    <div class="pie-color3" :style="`width:${radius}px;height:${radius}px;};`">
      <div class="part bg-blue pos01" :style="`background-color: ${rgbCode[0]};`"></div>
      <div class="part bg-red pos02" :style="`background-color: ${rgbCode[1]};`"></div>
      <div class="part bg-green pos03" :style="`background-color: ${rgbCode[2]};`"></div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'pieColor3',
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
<style >
.pie-color3{
  margin: 0 auto;
  border-radius: 50%;
  /* width: 35px;
  height: 35px; */
  color: white;
  overflow: hidden;
  position: relative;
}

.part {
  width: 58%;
  height: 58%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform-origin: 0% 0%;
}

.pos01 {
  transform: rotate(0deg) skewX(-30deg);
}

.pos02 {
  transform: rotate(120deg) skewX(-30deg);
}

.pos03 {
  transform: rotate(240deg) skewX(-30deg);
}
/* 
.bg-blue {
  background-color: blue;
}

.bg-red {
  background-color: red;
}

.bg-green {
  background-color: green;
} */
</style>