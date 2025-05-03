<template>
  <div class="content-row">
    <div class="content-img">
      <nuxt-link class="content-text__summarize" :to="localePath({ name: toLocalePath, params: { id: item.id } })">
        <ImgFixRatio :url="baseUrl + item.imageUrl" :ratio="56.45161290322581" />
      </nuxt-link>
    </div>

    <div class="content-text">
      <nuxt-link class="content-text__title" :to="localePath({ name: toLocalePath, params: { id: item.id } })">
        <h3 class="ellipsis2line text-uppercase" :title="item.title">{{ item.title }}</h3>
      </nuxt-link>
      <nuxt-link class="content-text__summarize" :to="localePath({ name: toLocalePath, params: { id: item.id } })">
        <p class="ellipsis3line" :title="item.summarize">{{ item.summarize }}</p>
      </nuxt-link>
      <div class="content-text__more">
        <span class="content-text__more__time">{{ item.publishTime ? item.publishTime.split('T')[0] : '' }}</span>

        <nuxt-link :to="localePath({ name: toLocalePath, params: { id: item.id } })">
          <el-button class="border-el-button">{{ $t('news.readMore') }}</el-button>
        </nuxt-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
    },
  },
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      toLocalePath: 'news-newsRelease-detail-id',
    }
  },
}
</script>

<style scoped lang="scss">
.content-row {
  display: flex;
  margin-bottom: 60px;
  .content-img {
    flex: 1;
    img {
      width: 100%;
    }
  }
  .content-text {
    flex: 2;
    padding-left: 50px;
    display: flex;
    flex-direction: column;

    .content-text__title {
      & > h3 {
        font-size: 22px;
        &:hover {
          color: #d1291a;
        }
      }
    }

    .content-text__summarize {
      flex: 1;
      margin-top: 10px;
    }

    .content-text__more {
      flex: 0;
      padding-top: 10px;
      border-top: 1px #c6c6c6 solid;
      display: flex;
      flex-direction: row;
      align-items: center;

      .content-text__more__time {
        flex: 1;
        font-weight: 600;
      }

      .el-button {
        padding: 10px 20px;
      }
    }
  }


}

.lang___ar{
  .content-text{
    padding-left: 0;
    padding-right: 50px;
  }
}

.md,
.sm,
.xs {
  .content-row {
    margin-bottom: 25px;
    .content-text {
      padding-left: 20px;
      .content-text__summarize {
        & > p {
          margin-top: 10px;
        }
        .ellipsis3line {
            -webkit-line-clamp: 2;
            line-clamp: 2;
          }
      }

      .content-text__more {
        padding-top: 8px;

        .content-text__more__time {
          font-weight: 500;
          font-size: 0.9em;
        }
        .el-button {
          padding: 8px 16px;
          font-size: 12px;
        }
      }
    }
  }

  &.lang___ar{
  .content-text{
    padding-left: 0;
    padding-right: 20px;
  }
}
}

.sm,
.xs {
  .content-row {
    .content-text {
      .content-text__summarize {
        display: none;
      }

      .content-text__more {
        padding-top: 8px;
        border: none;
        .content-text__more__time {
          font-weight: 500;
          font-size: 0.9em;
        }
        .el-button {
          display: none;
        }
      }
    }
  }
}

.xs {
  .content-row {
    .content-text {
      .content-text__title {
        margin-top: -4px;
        & > h3 {
          font-size: 14px;
        }
      }

      .content-text__summarize {
        & > p {
          display: none;
          margin-top: 10px;
          &.ellipsis3line {
            -webkit-line-clamp: 2;
            line-clamp: 2;
          }
        }
      }

      .content-text__more {
        padding-top: 8px;
        border: none;

        .content-text__more__time {
          font-weight: 500;
          font-size: 0.9em;
        }
        .el-button {
        }
      }
    }
  }
}
</style>
