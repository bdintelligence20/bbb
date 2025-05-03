<!--
  * 描述：汽车子系统详情展示页，动态文字展示；
  * 参数：characteristic文字数据
 -->
<template>
  <div class="image-text">
  <!-- position可配置：
      textLeftTop: '文字居左上',
      textLeft: '文字居左',
      textLeftBottom: '文字居左下',
            textRightTop: '文字居右上',
            textRight: '文字居右',
            textRightBottom: '文字居右下'
        -->
    <div :class="characteristic.viewType">
      <pre class="h1" v-if="characteristic.title && characteristic.title.length > 2 ">{{ characteristic.title | strReplace }}</pre>
      <pre class="h3" v-if="characteristic.subTitle && characteristic.subTitle.length > 2 ">{{ characteristic.subTitle | strReplace }}</pre>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    characteristic: {
      type: Object,
      default: {
      }
    }
  },
  data() {
    return {
    };
  },
  filters: {
    strReplace(str) {
      if(str == '0'){
        return ''
      }
      return str && str.replaceAll("<br/>", "\n")
    }
  }
}
</script>

<style scoped lang="scss">
.image-text {
  color: white;
  letter-spacing: 1px;

  .textLeftTop,
  .textLeft,
  .textLeftBottom {
    position: absolute;
    left: 0;
    padding-left: 6%;
    transform-origin: 6% 50%;
  }

  .textRightTop,
  .textRight,
  .textRightBottom {
    position: absolute;
    right: 0;
    padding-right: 6%;
    transform: scale(1);
    transform-origin: 94% 50%;
  }



  .textLeftTop {
    transform-origin: 5% 5%;
  }

  .textLeft {
    transform-origin: 5% 50%;
  }

  .textLeftBottom {
    transform-origin: 5% 95%;
  }

  .textRightTop {
    transform-origin: 95% 5%;
  }

  .textRight {
    transform-origin: 95% 50%;
  }

  .textRightBottom {
    transform-origin: 95% 95%;
  }

  .textLeftTop,
  .textRightTop {
    top: 10%;
  }

  .textLeft,
  .textRight {
    top: 35%;
  }

  .textLeftBottom,
  .textRightBottom {
    bottom: 10%;
  }

  .h1 {
    font-size: 44px;
    font-weight: bold;
    line-height: 1.2;
    font-family: Arial, Helvetica, sans-serif;
  }

  .h3 {
    font-size: 24px;
    line-height: 1.6;
    padding-top: 10px;
    font-weight: normal;
    font-family: Arial, Helvetica, sans-serif;
  }

  @media only screen and (min-width:992px) and (max-width:1199px) {

    .textRightTop,
    .textRight,
    .textRightBottom,
    .textLeftTop,
    .textLeft,
    .textLeftBottom {
      transform: scale(0.6);
      -moz-transform: scale(0.6);
      -webkit-transform: scale(0.6);
      -ms-transform: scale(0.6);
    }

    .textLeftTop,
    .textRightTop {
      top: 6%;
    }

    .textLeft,
    .textRight {
      top: 21%;
    }

    .textLeftBottom,
    .textRightBottom {
      bottom: 6%;
    }

  }

  // 平板样式
  @media only screen and (min-width:768px) and (max-width:991px) {

    .textRightTop,
    .textRight,
    .textRightBottom,
    .textLeftTop,
    .textLeft,
    .textLeftBottom {
      transform: scale(0.35);
      -moz-transform: scale(0.35);
      -webkit-transform: scale(0.35);
      -ms-transform: scale(0.35);
    }

    .textLeftTop,
    .textRightTop {
      top: 3.5%;
    }

    .textLeft,
    .textRight {
      top: 14%;
    }

    .textLeftBottom,
    .textRightBottom {
      bottom: 3.5%;
    }

    .textLeftTop {
      transform-origin: 5% 8%;
    }

    .textRightTop {
      transform-origin: 95% 8%;
    }
  }

  // 手机
  @media only screen and (max-width:767px) {

    .textRightTop,
    .textRight,
    .textRightBottom,
    .textLeftTop,
    .textLeft,
    .textLeftBottom {
      transform: scale(0.2);
      -moz-transform: scale(0.2);
      -webkit-transform: scale(0.2);
      -ms-transform: scale(0.2);
    }

    .textLeftTop,
    .textRightTop {
      top: 2%;
    }

    .textLeft,
    .textRight {
      top: 7%;
    }

    .textLeftBottom,
    .textRightBottom {
      bottom: 2%;
    }

    .textLeftTop {
      transform-origin: 5% 8%;
    }

    .textRightTop {
      transform-origin: 95% 8%;
    }

    .textRight {
      transform-origin: 95% 35%;
    }

    .textLeft {
      transform-origin: 5% 35%;
    }
  }
}</style>
