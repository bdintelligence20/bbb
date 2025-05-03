<!--
 * @Author: Liu
 * @Date: 2023-05-23 10:25:36
 * @LastEditTime: 2023-09-08 18:50:21
-->
<template>
  <div>
    <mq-layout mq="lg+">
      <a :class="['hover_box',!noTop && 'hove_top']" :href="href">
        <slot name="hover_href">
          <div class="hover_title">
            <slot name="title"></slot>
          </div>

          <div class="hover_content">
            <slot name="content"></slot>
          </div>
        </slot>
      </a>
    </mq-layout>

    <mq-layout :mq="['xs', 'sm', 'md']">

      <div class="hover_mobile" @click="openContent">

        <div class="title">
          <slot name="title"></slot>
        </div>
        <div :class="['content',isOpen &&'open_height']"
        :style="{
            maxHeight: this.max_height,
            '-webkit-line-clamp': lineClamp
           }"
        ref="my_height">
          <slot name="content"></slot>
        </div>
      </div>


    </mq-layout>
  </div>
</template>

<script>
export default {
  name: 'hover',
  props: {
    href: {
      // 定义接收的类型 还可以定义多种类型 [String, Undefined, Number]
      // 如果required为true,尽量type允许undefined类型，因为传递过来的参数是异步的。或者设置默认值。
      type: String,
      // 定义是否必须传
      required: false,
      // 定义默认值
      default: 'javascript:;',
    },
    noTop:{
      type:Boolean,
      default:false
    }
  },
  data() {
    return {
      max_height:"10vw",
      isOpen:false,
      lineClamp:2,

    }
  },
  methods:{
    openContent(){

            if (!this.isOpen && this.$refs.my_height.scrollHeight>45) {
              this.lineClamp = "unset";
              this.max_height = this.$refs.my_height.scrollHeight + 'px'
            } else {
                this.max_height = "10vw"
                  setTimeout(()=>{
                    this.lineClamp = 2;
                  },400)
            }
            this.isOpen = !this.isOpen


    }

  }
}
</script>
<style scoped>
 p{
  margin: 0;
  padding: 0;
 }
.hover_box {
  width: 100%;
  height: 100%;
  position: absolute;
  color: #fff;
  top: 0;
  left: 0;
  cursor: pointer;
  transition: all 0.4s;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  /* pointer-events: none; */
}

.hover_box:hover {
  background-color: rgba(0, 0, 0, 0.5);

}

.hover_title {
  /* background: linear-gradient(to top, rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0)); */
  box-sizing: border-box;
  padding: 0.5vw 1vw;
  font-weight: 700;
  transition: all 0.4s;
  text-transform:capitalize;
  font-size: 1.3vw;
}
.hover_title p{
  margin: 0 !important;
}

.hover_content {
  box-sizing: border-box;
  padding: 0vw 1vw;
  transition: all 0.4s;
  max-height: 0;

  font-size: 1vw;
  opacity: 0.6;
  /* text-align: left; */
  /* text-transform:capitalize; */
}

.hove_top:hover .hover_title {
  transform: translateY(-2vw);
  background: none;
}

.hove_top:hover .hover_content {
  transform: translateY(-2vw);
  max-height: 80%;
}

.mobile .hover_title {
  line-height: 7vw !important;
  font-size: 3.5vw !important;
  text-transform: none;
}

.mobile .hover_box .hover_content {
  max-height: 10vw !important;
  font-size: 3vw;
  overflow: hidden;
  line-height: 5vw;
  margin-bottom: 3vw;
  -webkit-line-clamp: 2;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.mobile .hove_top:hover .hover_content {
  transform: translateY(0);
  max-height: 0;
}

.mobile .hove_top:hover .hover_title {
  transform: translateY(0);
  background: none;
}

.mobile .hove_top:hover {
  background: none;
}

.mobile .hover_content {
  display: none;
}

.hover_mobile{
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: auto;
  background: linear-gradient(to top, rgba(0, 0, 0, 1), rgba(0, 0, 0, 0));

  box-sizing: border-box;
  padding: 2vw 3vw;
  color: #fff;
}
.title{
  line-height: 5vw;
  font-size: 4vw;

}
.content{
  max-height: 10vw;
  overflow: hidden;
  /* height: 10vw; */
  font-size: 3vw;
  line-height: 5vw;

  display: -webkit-box;
  -webkit-line-clamp:2;
  -webkit-box-orient: vertical;
  text-overflow: ellipsis;
  transition: all 0.4s;

}
.open_height{
  -webkit-line-clamp:unset;

  /* transition: all 0.4s; */
}
</style>
