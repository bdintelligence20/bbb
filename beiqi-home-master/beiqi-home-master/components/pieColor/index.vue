<template>
  <div>
    <colorPie1 v-if="rgbCode.length==1" :radius="radius" :rgbCode = "rgbCode"></colorPie1>
    <colorPie2 v-else-if="rgbCode.length==2" :radius="radius" :rgbCode = "rgbCode"></colorPie2>
    <colorPie3 v-else-if="rgbCode.length==3" :radius="radius" :rgbCode = "rgbCode"></colorPie3> 
    <colorPie4 v-else-if="rgbCode.length==4" :radius="radius" :rgbCode = "rgbCode"></colorPie4>
  </div>
</template>
<script>
import colorPie1 from '@/components/pieColor/pieColor1.vue';
import colorPie2 from '@/components/pieColor/pieColor2.vue';
import colorPie3 from '@/components/pieColor/pieColor3.vue';
import colorPie4 from '@/components/pieColor/pieColor4.vue';
export default {
  name: 'colorPie',
  components: {
    colorPie1,
    colorPie2,
    colorPie3,
    colorPie4 
  },
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

</style>