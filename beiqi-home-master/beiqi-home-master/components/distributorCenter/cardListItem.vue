<template>
  <div :class="{ 'card-list-item-ar': langAr }" class="card-list-item">
    <!--    {{ item }}-->
    <div class="large-image">
      <img :src="baseUrl + item.firstScreenshot" alt="" />
    </div>
    <div class="content">
      <div class="title-wrap">
        {{ item.materialPicsType }}
        <!--    {{item.newLabelFlag}}-->
        <div class="new-icon" v-if="item.materialPicsType && item.newLabelFlag != 1"> {{ $t('distributor.NEW') }} </div>
      </div>
      <div class="desc">
        {{ item.content || item.fileName }}
        <div class="new-icon" v-if="item.type === 'Training' && item.newLabelFlag != 1"> {{ $t('distributor.NEW') }} </div>
      </div>
      <ul class="label-list">
        <li class="label-item view-more">{{ $t('distributor.viewMore') }}</li>
        <li class="label-item qty">{{ $t('distributor.QTY') }}:{{ item.qty }}</li>
        <li class="label-item download">{{ $t('distributor.downloadLink') }}</li>
      </ul>
      <div class="content-bottom">
        <div class="thumb-list" @mouseenter="handleClick('openPreview', item.materialId)" @mouseleave="handleClick('closePreview', item.materialId)">
          <div class="thumb-image-wrap">
            <img v-if="item.secondScreenshot" :src="baseUrl + item.secondScreenshot" alt="" />
            <img v-if="item.thirdScreenshot" :src="baseUrl + item.thirdScreenshot" alt="" />
          </div>
          <!--          <div class="image-wrap">-->
          <!--          </div>-->
        </div>
        <a :href="item.downloadLink" class="icon" target="_blank" @click="handleClick('handleDownload', item)">
          <i class="icon-download"></i>
        </a>
        <!--              预览-->
        <mq-layout mq="lg+">
          <div v-show="item.viewMore.length > 0 && item.showPreview" class="dialog" @mouseenter="handleClick('openPreview', item.materialId)" @mouseleave="handleClick('closePreview', item.materialId)">
            <div class="image-wrap">
              <img v-for="item in item.viewMore" :src="item ? baseUrl + item : ''" alt />
            </div>
          </div>
        </mq-layout>
      </div>
    </div>
    <!--              预览-->
    <mq-layout :mq="['md', 'xs', 'sm']">
      <div v-show="item.viewMore.length > 0 && item.showPreview" class="dialog" @mouseenter="handleClick('openPreview', item.materialId)" @mouseleave="handleClick('closePreview', item.materialId)">
        <div class="image-wrap">
          <img v-for="item in item.viewMore" :src="item ? baseUrl + item : ''" alt />
        </div>
      </div>
    </mq-layout>
  </div>
</template>

<script>
export default {
  name: 'socialCard',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
    }
  },

  props: {
    item: {
      type: Object,
      default() {
        return {}
      },
    },
  },

  computed: {
    langAr() {
      return this.$i18n.locale === 'ar'
    },
  },

  mounted() {},

  methods: {
    handleClick(type, e) {
      console.log(e, '下载')
      this.$emit(type, e)
    },
  },
}
</script>

<style lang="scss" scoped>
img {
  width: 100%;
  object-fit: cover;
}
.card-list-item {
  display: flex;
  align-items: center;
  position: relative;
  padding: 1.5625vw;
  font-size: 16px;
  border: 1px solid #787878;

  .large-image {
    display: flex;
    align-items: center;
    width: 43.5%;
    img {
      height: 11.5vw;
    }
  }

  .content {
    flex: 1;
    padding-left: 1.5625vw;

    .title-wrap {
      display: flex;
      align-items: center;
      font-size: 1.5vw;
      font-weight: 500;
    }

    .desc {
      //display: flex;
      //align-items: center;
      min-height: 2.5vw;
      font-size: 13px;
      line-height: 1.2;
    }

    .label-list {
      display: flex;
      margin-top: 8px;

      .label-item {
        height: 1.6vw;
        line-height: 1.6vw;
        margin-right: 5px;
        background: #e89f95;
        color: #fff;
        font-size: 13px;
        text-align: center;
      }

      .view-more {
        width: 39%;
        font-size: 12px;
      }

      .qty {
        width: 24%;
      }

      .download {
        width: 32.5%;
      }
    }

    .content-bottom {
      position: relative;
      display: flex;
      align-items: center;
      margin-top: 1vw;

      .thumb-list {
        display: flex;
        width: 72%;
        cursor: pointer;
      }

      .thumb-image-wrap {
        display: flex;
        flex: 1;
        img {
          width: 45%;
          margin-right: 5px;
          height: 3.3vw;
        }
      }

      .icon {
        margin-left: 12px;
      }

      .icon-download {
        display: block;
        width: 3.3vw;
        height: 2.4vw;
        background: url('~/assets/image/icon/download.png') no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
      }
    }
  }

  .dialog {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    left: -100%;
    top: 85%;
    width: 220%;
    padding: 30px;
    background-color: #f7f7f7;
    box-shadow: 3px 3px 8px rgba(27, 27, 27, 0.21);
    border-radius: 8px;
    z-index: 99;

    .image-wrap {
      width: 100%;
      height: 100%;
    }
  }
}

.card-list-item-ar {
  .content {
    padding-right: 20px;
    padding-left: 0;

    .icon {
      margin-right: 12px;
      margin-left: 0;
    }
    .dialog {
      right: -100%;
    }
  }
}

.new-icon {
  display: inline-block;
  padding: 2px 6px;
  margin: 0 12px;
  background: #ee502f;
  color: #fff;
  border-radius: 18px 18px 18px 0;
  text-align: center;
  font-size: 12px;
}

@media only screen and (max-width: 1199px) {
  .card-list-item {
    position: relative;
    .large-image {
      width: 43.5%;
      img {
        height: 22.3vw;
      }
    }
    .content {
      .title-wrap {
        font-size: 3.083vw;
      }

      .desc {
        font-size: 17px;
      }
      .label-list {
        .label-item {
          height: 2.2vw;
          line-height: 2.2vw;
        }
      }
      .content-bottom {
        margin-top: 2.042vw;
        .thumb-image-wrap {
          img {
            height: 8.8vw;
          }
        }
        .icon-download {
          width: 6.646vw;
          height: 4.708vw;
        }
      }
    }
    .dialog {
      left: 0;
      width: 100%;
    }
  }
  .new-icon {
    padding: 2px 5px;
    font-size: 10px;
  }
  .card-list-item-ar {
    .dialog {
      right: 0;
    }
  }
}

@media only screen and (max-width: 768px) {
  .large-image {
    height: 30.3vw !important;
  }
  .card-list-item {
    position: static;
    padding: 3.5vw;
    font-size: 12px;

    .content {
      padding-left: 3vw;

      .title-wrap {
        font-size: 16px;
      }

      .desc {
        //margin: 0;
        font-size: 12px;
      }

      .label-list {
        margin-top: 5px;
        .label-item {
          height: 3.5vw;
          line-height: 3.5vw;
          font-size: 1vw;
          margin-right: 3px;
          text-align: center;

          &:last-child {
            margin-right: 0;
          }
        }

        .download {
          width: auto;
        }
      }
      .qty {
        width: 26% !important;
      }

      .content-bottom {
        margin-top: 5px;

        .icon {
          margin-left: 5px;

          .icon-download {
            width: 24px;
            height: 18px;
          }
        }

        .thumb-image-wrap {
          img {
            height: 30px;
          }
        }
      }
    }

    .dialog {
      position: fixed;
      transform: translate(-50%);
      top: 25%;
      left: 50%;
      width: 90%;
      border-radius: 10px;
      padding: 15px;
    }
  }
  .card-list-item-ar {
    .content {
      padding-right: 20px;
      padding-left: 0;

      .icon {
        margin-right: 5px;
        margin-left: 0;
      }
    }
  }
}
</style>
