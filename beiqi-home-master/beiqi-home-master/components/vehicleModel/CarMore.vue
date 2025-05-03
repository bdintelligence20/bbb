<!--
 * @Author: Liu
 * @Date: 2023-05-31 17:11:34
 * @LastEditTime: 2023-09-05 10:36:22
-->
<template>
  <div :class="['car_more', isOpen && 'car_more_off']">
    <div class="more_top" @click="handleMore" ref="more_top">
      <img src="~/assets/image/vehicleModel/downward_40.png" alt="" class="img_40" />
      <img src="~/assets/image/vehicleModel/downward_40.png" alt="" class="img_40" />
      <img src="~/assets/image/vehicleModel/downward_white.png" alt="" class="img_white" />
      <img src="~/assets/image/vehicleModel/downward_white.png" alt="" class="img_white" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isOpen: true,
    }
  },

  methods: {
    handleMore() {
      console.log('111')
      this.isOpen = !this.isOpen
      this.$emit('handleClickMore')
      return
      if (!this.isOpen) {
        // 让页面平滑滚动到该元素的位置
        console.log("open")
        // setTimeout(() => {
        //     this.animateScroll(this.$refs.more_top, 1000)
        // }, 500)
 
      }
    },
    animateScroll(element, speed) {
      let rect = element.getBoundingClientRect()
      //获取元素相对窗口的top值，此处应加上窗口本身的偏移
      let top = window.pageYOffset + rect.top
      let currentTop = 0
      let requestId
      //采用requestAnimationFrame，平滑动画
      function step(timestamp) {
        currentTop += speed
        if (currentTop <= top) {
          window.scrollTo(0, currentTop)
          requestId = window.requestAnimationFrame(step)
        } else {
          window.cancelAnimationFrame(requestId)
        }
      }
      window.requestAnimationFrame(step)
    },
  },
}
</script>
<style  scoped>
.car_more {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: absolute;
  width: 5vw;
  left: 50%;
  transform: translateX(-50%);
  bottom: 0px;
  color: #fff;
  cursor: pointer;
  transition: all 0.4s;
  z-index: 9;
}

.more_top {
  width: 3vw;
  height: 3vw;
  background-color: rgba(255, 255, 255, .85);
  border-radius: 50%;
  margin-bottom: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.4s;
  position: relative;
}

.more_top::after {
  position: absolute;
  content: '';
  width: 100%;
  height: 100%;
  border-radius: 50%;
  top: 0;
  left: 0;
  box-shadow: 5px 5px 15px #000;
}

.more_bottom {
  opacity: 0.8;
  white-space: nowrap;
}

.more_top > img {
  position: absolute;
  width: 50%;
  top: 15%;
  transition: all 0.4s;
  -webkit-animation: shake-vertical-2 2s linear infinite alternate-reverse forwards;
  animation: shake-vertical-2 2s linear infinite alternate-reverse forwards;
}
.more_top .img_white {
  /* display: none; */
  opacity: 0;
}

.more_top > img:nth-child(2) {
  top: 36%;
}
.more_top > img:nth-child(4) {
  top: 34%;
}
.car_more_off {
  bottom: -3vw;
}
.car_more_off .more_top {
  background: #bfbfbf;
}
.car_more_off .img_40 {
  /* display: none; */
  opacity: 0;
}
.car_more_off .img_white {
  /* display: block; */
  opacity: 1;
}
.car_more_off .more_top {
  transform: rotate(180deg);
  /* transform-origin: center; */
}
.car_more_off .more_top::after {
  transform: rotate(180deg);
}
.car_more_off .more_bottom {
  opacity: 0;
}

/* 移动端 */

.mobile .car_more {
  bottom: -6vw !important;
}
.mobile .car_more .more_top {
  width: 8vw;
  height: 8vw;
}

.mobile .car_more .more_top > img {
  width: 50%;
}
.mobile .more_bottom {
  font-size: 2vw;
}

@keyframes shake-vertical-2 {
  0%,
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }

  10%,
  30%,
  50%,
  70% {
    -webkit-transform: translateY(-0.1vw);
    transform: translateY(-0.1vw);
  }

  20%,
  40%,
  60% {
    -webkit-transform: translateY(0.4vw);
    transform: translateY(0.4vw);
  }

  80% {
    -webkit-transform: translateY(0.2vw);
    transform: translateY(0.2vw);
  }

  90% {
    -webkit-transform: translateY(-0.2vw);
    transform: translateY(-0.2vw);
  }
}


</style>