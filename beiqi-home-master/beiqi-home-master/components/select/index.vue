<template>
  <div class="select__wrapper">
    <mq-layout mq="md+">
      <el-select v-model="value" :placeholder="placeholder" @change="selectChange" clearable>
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
    </mq-layout>
    <mq-layout :mq="['xs','sm']">
      <el-select v-model="value" :placeholder="placeholder" @change="selectChange" clearable>
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
    </mq-layout>
  </div>
</template>
<script>
export default {
  props: {
    options: {
      type: Array,
      default: () => []
    },
    placeholder: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      value: null
    }
  },
  methods: {
    selectChange() {
      this.$emit('changeValue', this.value)
    }
  }
}
</script>

<style scoped lang="scss">
.select {
  width: 100%;
  padding: 10px 18px 10px 10px;
}
</style>
