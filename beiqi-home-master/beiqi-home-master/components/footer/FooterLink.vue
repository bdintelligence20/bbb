<template>
  <mq-layout :mq="['md', 'lg', 'xl', 'xs', 'sm']">
    <div class="link-box">
      <h3 v-if="title !== ' '">{{ title }}</h3>
      <h3 v-else style="opacity: 0">1</h3>
      <div class="line"></div>
      <ul class="link-list">
        <li v-for="link in linkList" :key="link.name">
          <nuxt-link :to=" localePath( { path: link.path, query: { id: link.id }, params: { id: link.id }, }, $i18n.locale ) " >
            {{ link.name }}
          </nuxt-link>
        </li>
      </ul>
    </div>
  </mq-layout>
</template>
<script >
export default {
  name: "FooterLink",
  props: {
    title: {
      type: String,
      default: ''
    },
    linkList: {
      type: Array,
      default: []
    },
  },
  data() {
    return {};
  },
};
</script>

<style scoped lang="scss">

.link-box {
  h3 {
    height: 52px;
    padding-top: 5px;
    font-size: 20px;
    color: #fff;
  }
  .line {
    width: 18px;
    height: 5px;
    background: #ff3a1e;
    margin-bottom: 20px;
  }
  .link-list {
    li {
      a {
        color: #c7c7c7;
        &:hover {
          color: #fff;
        }
      }
      font-size: 14px;
      margin-bottom: 10px;
      &:last-child {
        margin-bottom: 0;
      }
    }
  }
}

.md, .xs, .sm {
  .link-box {
    width: 100%;
    padding: 15px 15px 0 15px;

    h3 {
      display: none;
    }

    .link-list {
      li {
        a {
          display: block;
          color: #505050;
        }
      }
    }
  }
}
</style>
