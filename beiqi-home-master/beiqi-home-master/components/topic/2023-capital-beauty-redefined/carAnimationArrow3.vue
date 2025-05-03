<template>
  <div class="carAnimation_arrow">
    <img src="../../../static/topic/2023-capital-beauty-redefined/images/image11.jpg" />
    <div class="zhezhao"></div>
    <img src="../../../static/topic/2023-capital-beauty-redefined/images/shadow_door.png" class="car_line" />
    <div class="text">"Leisurely" surface. The simple and pure curved design, combined with the strong wheel arch surface, creates a safe body image, with speed, and cutting sense of the line, reflecting the young fashion, as well as a sense of controlled movement.</div>
  </div>
</template>
<style scoped lang="scss">
.carAnimation_arrow {
  width: 100%;
  height: 100%;
  position: relative;
  .text {
    width: 55%;
    position: absolute;
    text-align: right;
    top: 25vw;
    color: #fff;
    font-size: 1.6vw;
    right: 5vw;
  }
  .car_line {
    width: 35%;
    position: absolute;
    top: 17vw;
    left: 20vw;
    right: 0;
  }
  .zhezhao {
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.5);
    position: absolute;
  }
}
@media only screen and (max-width: 768px) {
  .carAnimation_arrow {
    font-size: 1px;
    .text {
      width: 90%;
      font-size: 1px;
      text-align: left;
      top: 26vw;
      display: -webkit-box; /* 需要添加浏览器私有前缀 */
      -webkit-line-clamp: 2; /* 最多显示两行 */
      -webkit-box-orient: vertical; /* 垂直方向排列内容 */
      overflow: hidden; /* 超出部分隐藏 */
      text-overflow: ellipsis; /* 超出部分显示省略号 */
    }
  }
}
</style>
<i18n lang="json">
{
  "en": {
    "describe": {}
  },
  "ar": {},
  "es": {}
}
</i18n>
