<template>
  <div class="text-wrap">
    <ul v-if="textList.length" class="title-list">
      <li v-for="(item, index) in textList" :key="index" :class="{ active: index == activeIndex, 'text-cursor': textList.length > 1 }" class="text-item" @click="itemClick(index)">
        {{ item?.title }}
      </li>
    </ul>
    <div class="describe">{{ textList?.[activeIndex]?.subTitle }}</div>
  </div>
</template>

<script>
export default {
  props: {
    textList: {
      type: Array,
      default() {
        return []
      },
    },
    activeIndex: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {}
  },
  mounted() {},
  methods: {
    itemClick(index) {
      this.$emit('itemClick', index)
    },
  },
}
</script>

<style scoped lang="scss">
.text-wrap {
  color: #636363;
  font-size: 18px;
  width: 78%;
  margin: 0 auto;

  .title-list {
    display: flex;
    justify-content: center;
  }

  .text-item {
    padding: 18px 30px 10px;
    border-bottom: 1px solid #ccc;

    &:last-child {
      margin-right: 0;
    }
  }

  .text-cursor {
    cursor: pointer;
  }

  .active {
    font-weight: 500;
    border-color: #000;
    color: #000;
  }

  .describe {
    padding: 18px 0;
    text-align: center;
  }
}
@media screen and (max-width: 1200px) {
  .text-wrap {
    width: 100%;
    font-size: 15px;
    .title-list {
      justify-content: flex-start;
    }
    .text-item {
      flex: 1;
      padding: 18px 0 8px;
      text-align: center;
      &:first-child {
        padding-right: 10px;
      }
    }
    .describe-wrap {
      position: absolute;
      bottom: 0;
      padding: 15px;
      background: linear-gradient(0deg, #000, transparent);
    }
    .describe {
      font-size: 14px;
      text-align: left;
    }
  }
}
</style>
