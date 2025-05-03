<template>
  <div class="body-main" :class="[$i18n.locale, mobileClass, directionStr]">
    <div class="bg-mask"></div>
    <img :src="defaultBg" alt="" class="bg" />
    <img :src="defaultBg" alt="" class="bg" :class="bgtype==1?'show':'hide'" />
    <img :src="page6Bg" alt="" class="bg" :class="bgtype==2?'show':'hide'" />

    <div class="scroll-container">
      <!-- loading -->
      <div :class="loadingProgress>=100?'loading-box hide':'loading-box'">
        <img src="~/assets/image/vehicleModel/bj40/loading/loading-txt-border.png" class="border-img" alt="" />
        <div class="inside-img" :style="'--imgwidth:'+loadingProgress+'%;'">
          <img src="~/assets/image/vehicleModel/bj40/loading/loading-txt-inside.png" class="img" alt="" />
        </div>
        <img src="~/assets/image/vehicleModel/bj40/loading/loading-txt-2.png" class="txt-img" alt="" />
      </div>
      <!-- loading的时候撑页面的 -->
      <div class="page-container" style="pointer-events: none;"
        v-if="!hideLoading||sequenceFrameIndex>=180&&mobileClass==''"></div>
      <!-- 第一屏的标题图片，使用了css混合模式 -->
      <!-- 这个图片因为用了混合模式所以必须和背景图同层外面不能套div -->
      <img src="~/assets/image/vehicleModel/bj40/page1/title.png" v-show="hideLoading==true" class="page1-title-img"
        alt="" :class="showpagenum==1&&pagenum==1?'show':'hide'" />
      <img src="~/assets/image/vehicleModel/bj40/page1/title.png" v-show="hideLoading==true" class="page1-title-img"
        alt="" :class="showpagenum==1&&pagenum==1?'show':'hide'" />
      <img src="~/assets/image/vehicleModel/bj40/page1/title.png" v-show="hideLoading && mobileClass!=''"
        class="page1-title-img" alt="" :class="showpagenum==1&&pagenum==1?'show':'hide'" />
      <!-- 假山和石头（仅岩石模式） -->
      <img src="~/assets/image/vehicleModel/bj40/ats/rockery.png" class="rockery"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==0?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/stone_vista.png" class="stone_vista"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==0?'show':'hide'" alt="">
      <!-- 假山和石头（仅雪地模式） -->
      <img src="~/assets/image/vehicleModel/bj40/ats/snow_rockery.png" class="rockery"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==1?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/snow_vista.png" class="stone_vista"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==1?'show':'hide'" alt="">
      <!-- 假山和石头（仅沙漠模式） -->
      <img src="~/assets/image/vehicleModel/bj40/ats/shamo_rockery.png" class="rockery"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==2?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/shamo_vista.png" class="stone_vista"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==2?'show':'hide'" alt="">
      <!-- 序列帧容器 -->

      <div class="sequence-container" ref="sequenceContainer" v-show="pagenum<10">
        <!-- <img ref="sequenceFrame" src="/bj40/frame/0000.png" alt="Sequence Frame" class="sequence-frame"
            :class="{hide:loadingProgress<=98||hideSequence}"> -->
        <canvas ref="canvas" class="sequence-frame" :class="{hide:loadingProgress<=98||hideSequence}"></canvas>
      </div>
      <!-- 碎石（仅岩石模式） -->
      <img src="~/assets/image/vehicleModel/bj40/ats/gravel1.png" class="gravel1"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==0?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/gravel2.png" class="gravel2"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==0?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/gravel3.png" class="gravel3"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==0?'show':'hide'" alt="">
      <!-- 碎石（仅雪地模式） -->
      <img src="~/assets/image/vehicleModel/bj40/ats/snow1.png" class="snow1 gravel1"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==1?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/snow2.png" class="snow2 gravel2"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==1?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/snow3.png" class="snow3 gravel3"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==1?'show':'hide'" alt="">
      <!-- 碎石（仅沙漠模式） -->
      <img src="~/assets/image/vehicleModel/bj40/ats/shamo1.png" class="shamo1 gravel1"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==2?'show':'hide'" alt="">
      <img src="~/assets/image/vehicleModel/bj40/ats/shamo2.png" class="shamo2 gravel2"
        :class="showpagenum==3&&pagenum==3&&ats_current_index ==2?'show':'hide'" alt="">
      <!-- 第一屏 -->
      <div class="page-container page1" v-show="hideLoading" :class="showpagenum==1&&pagenum==1?'show':'hide'">
        <div class="page1-btn-group" :class="$i18n.locale=='ar'?'revers':''">
          <nuxt-link :to="localePath('/testDrive', $i18n.locale)" class="btn btn1">
            {{page1_btn[$i18n.locale][0].txt}}
          </nuxt-link>
          <a :href="vrViewUrl" target="_blank" class="btn btn2">
            {{page1_btn[$i18n.locale][1].txt}}
          </a>
        </div>
        <div class="to-next-icon" @click="fixBtnFun" data-type="down">
          <img src="~/assets/image/vehicleModel/bj40/page1/triangle-icon.png" alt="" class="icon i3">
          <img src="~/assets/image/vehicleModel/bj40/page1/triangle-icon.png" alt="" class="icon i2">
          <img src="~/assets/image/vehicleModel/bj40/page1/triangle-icon.png" alt="" class="icon i1">
        </div>
      </div>
      <!-- 四驱 -->
      <div class="page-container page2" :class="showpagenum==2&&pagenum==2?'show':'hide'">
        <!-- <div class="point-main bright-spot" v-if="mobileClass==''">
                    <img src="~/assets/image/vehicleModel/bj40/point.png" class="point" alt="">
                    <img src="~/assets/image/vehicleModel/bj40/point-line1.png" class="line1"
                        :style="showpagenum==2&&pagenum==2?'--topClip:0vw':'--topClip:12vw'" alt="">
                    <img src="~/assets/image/vehicleModel/bj40/point-line2.png" class="line2"
                        :style="showpagenum==2&&pagenum==2?'--rightClip:0vw':'--rightClip:26.32vw'" alt="">
                </div>
                <div class="point-main bright-spot top" :class="showpagenum==2&&pagenum==2?'show':'hide'"
                    v-if="mobileClass!=''">
                    <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
                    <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
                        :style="showpagenum==2&&pagenum==2?'--topClip:0vw':'--topClip:100%'" alt="">
                    <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
                        :style="showpagenum==2&&pagenum==2?'--rightClip:0vw':'--rightClip:100%'" alt="">
                </div> -->
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="dialog-main" :class="showpagenum==2&&pagenum==2?'show':'hide'">
          <img src="~/assets/image/vehicleModel/bj40/page2_cover.png" class="img" alt="">
          <div class="text-view">
            <div class="title">{{page2_text[$i18n.locale].title}}</div>
            <div class="memo">{{page2_text[$i18n.locale].memo}}</div>
          </div>
        </div>
      </div>
      <!-- ats -->
      <div class="page-container page3" :class="showpagenum==3&&pagenum==3?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="dialog-main">
          <img src="~/assets/image/vehicleModel/bj40/page3_cover.png" class="img" alt="">
          <div class="text-view">
            <div class="title">{{ats_text[$i18n.locale].title}}</div>
            <div class="memo">{{ats_text[$i18n.locale].memo}}</div>
          </div>
        </div>
        <!-- <div class="point-main bright-spot top" :class="showpagenum==3&&pagenum==3?'show':'hide'"
                    v-if="mobileClass!=''">
                    <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
                    <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
                        :style="showpagenum==3&&pagenum==3?'--topClip:0vw':'--topClip:100%'" alt="">
                    <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
                        :style="showpagenum==3&&pagenum==3?'--rightClip:0vw':'--rightClip:100%'" alt="">
                </div> -->
        <div class="tap-group">
          <div class="tap-item" :class="ats_current_index == index ? 'choose' : ''"
            v-for="(item,index) in ats_btn[$i18n.locale]" :key="index" @click="changeAtsIdx(index)"
            @tap="changeAtsIdx(index)">
            <img :src="item.icon" alt="" class="img unimg">
            <img :src="item.choose_icon" alt="" class="img chimg">
            <div class="txt">{{item.txt}}</div>
          </div>
        </div>
      </div>
      <!-- 越野 pc -->
      <div class="page-container page4" v-if="mobileClass==''" :class="showpagenum==4&&pagenum==4?'show':'hide'">
        <div class="title-main">
          <!-- <img src="~/assets/image/vehicleModel/bj40/page4/title-line.png" v-if="mobileClass==''"
                        class="line-img" alt="">
                    <img src="~/assets/image/vehicleModel/bj40/page4/mob-title-line.png" v-else class="line-img" alt=""> -->
          <div class="title-img">{{page4_title[$i18n.locale].title}}</div>
          <!-- <img src="~/assets/image/vehicleModel/bj40/page4/title-txt.png" class="title-img" alt=""> -->
        </div>
        <div class="argument-img" v-if="$i18n.locale=='en'"
          :class="showpagenum==4&&pagenum==4&&page4StopNum<2?'show':'hide'">
          <!-- <div class="txt">{{page4_title[$i18n.locale].txt}}</div> -->
          <img src="~/assets/image/vehicleModel/bj40/page4/750mm.png"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'" class="img-750mm" alt="">
          <img src="~/assets/image/vehicleModel/bj40/page4/220mm.png" class="img-220mm"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/page4/argument-img.png" class="img"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==1?'show':'hide'" alt="">
        </div>
        <div class="argument-img" v-if="$i18n.locale=='es'"
          :class="showpagenum==4&&pagenum==4&&page4StopNum<2?'show':'hide'">
          <!-- <div class="txt">{{page4_title[$i18n.locale].txt}}</div> -->
          <img src="~/assets/image/vehicleModel/bj40/page4/es750mm.png"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'" class="img-750mm" alt="">
          <img src="~/assets/image/vehicleModel/bj40/page4/es220mm.png" class="img-220mm"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/page4/esargument-img.png" class="img"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==1?'show':'hide'" alt="">
        </div>
        <div class="argument-img" v-if="$i18n.locale=='ar'"
          :class="showpagenum==4&&pagenum==4&&page4StopNum<2?'show':'hide'">
          <!-- <div class="txt">{{page4_title[$i18n.locale].txt}}</div> -->
          <img src="~/assets/image/vehicleModel/bj40/page4/ar750mm.png"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'" class="img-750mm" alt="">
          <img src="~/assets/image/vehicleModel/bj40/page4/ar220mm.png" class="img-220mm"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/page4/arargument-img.png" class="img"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==1?'show':'hide'" alt="">
        </div>
        <div class="argument-table" :class="showpagenum==4&&pagenum==4&&page4StopNum==2?'show':'hide'">
          <div class="line l1"></div>
          <div class="line l2"></div>
          <div class="table-item" v-for="(item,index) in page4_table[$i18n.locale]" :key="index">
            <div class="name">{{item.title}}</div>
            <div class="num">{{item.num}}</div>
          </div>
        </div>
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
      </div>
      <!-- 越野移动 -->
      <div class="page-container page4" v-if="mobileClass!=''" :class="showpagenum==4&&pagenum==4?'show':'hide'">
        <div class="title-main">
          <!-- <img src="~/assets/image/vehicleModel/bj40/page4/title-line.png" class="line-img" alt=""> -->
          <div class="title-img">{{page4_title[$i18n.locale].title}}</div>
          <!-- <img src="~/assets/image/vehicleModel/bj40/page4/title-txt.png" class="title-img" alt=""> -->
        </div>
        <div class="argument-img" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
          <img src="~/assets/image/vehicleModel/bj40/page4/mob-angle-img.png" class="angle-img" alt="">
          <!-- 接近角 -->
          <div class="point-main bright-spot p1" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
            <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--topClip:0vw':'--topClip:100%'" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--rightClip:0vw':'--rightClip:100%'" alt="">
            <div class="bright-main" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
              <div class="title">{{page4_angle[$i18n.locale].p1}}</div>
              <div class="num">37°</div>
            </div>
          </div>
          <!-- 涉水深度 -->
          <div class="point-main bright-spot bottom p2"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
            <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--topClip:0vw':'--topClip:100%'" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--rightClip:0vw':'--rightClip:100%'" alt="">
            <div class="bright-main" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
              <div class="title">{{page4_angle[$i18n.locale].p2}}</div>
              <div class="num">750 <span class="small">MM</span> </div>
            </div>
          </div>
          <!-- 纵向通过角 -->
          <div class="point-main bright-spot p3" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
            <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--topClip:0vw':'--topClip:100%'" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--rightClip:0vw':'--rightClip:100%'" alt="">
            <div class="bright-main" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
              <div class="title">{{page4_angle[$i18n.locale].p3}}</div>
              <div class="num">23°</div>
            </div>
          </div>
          <!-- 离地间隙 -->
          <div class="point-main bright-spot bottom p4"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
            <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--topClip:0vw':'--topClip:100%'" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--rightClip:0vw':'--rightClip:100%'" alt="">
            <div class="bright-main" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
              <div class="title">{{page4_angle[$i18n.locale].p4}}</div>
              <div class="num">220 <span class="small">MM</span> </div>
            </div>
          </div>
          <!-- 离去角 -->
          <div class="point-main bright-spot left p5"
            :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
            <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--topClip:0vw':'--topClip:100%'" alt="">
            <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
              :style="showpagenum==4&&pagenum==4&&page4StopNum==0?'--rightClip:0vw':'--rightClip:100%'" alt="">
            <div class="bright-main" :class="showpagenum==4&&pagenum==4&&page4StopNum==0?'show':'hide'">
              <div class="title">{{page4_angle[$i18n.locale].p5}}</div>
              <div class="num">31°</div>
            </div>
          </div>
        </div>
        <div class="argument-table-main" :class="showpagenum==4&&pagenum==4&&page4StopNum==1?'show':'hide'">
          <div class="argument-table">
            <div class="line l1"></div>
            <div class="line l2"></div>
            <div class="table-item">
              <div class="name">{{page4_table[$i18n.locale][0].title}}</div>
              <div class="num">{{page4_table[$i18n.locale][0].num}}</div>
            </div>
            <div class="table-item">
              <div class="name">{{page4_table[$i18n.locale][1].title}}</div>
              <div class="num">{{page4_table[$i18n.locale][1].num}}</div>
            </div>
          </div>
          <div class="argument-table">
            <div class="line l1"></div>
            <div class="line l2"></div>
            <div class="table-item">
              <div class="name">{{page4_table[$i18n.locale][2].title}}</div>
              <div class="num">{{page4_table[$i18n.locale][2].num}}</div>
            </div>
            <div class="table-item">
              <div class="name">{{page4_table[$i18n.locale][3].title}}</div>
              <div class="num">{{page4_table[$i18n.locale][3].num}}</div>
            </div>
          </div>
        </div>

      </div>
      <!-- 车架 -->
      <div class="page-container page5" :class="showpagenum==5&&pagenum==5?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <img src="~/assets/image/vehicleModel/bj40/chejia.png" class="chejia">
        <div class="point-main bright-spot" v-if="mobileClass==''">
          <img src="~/assets/image/vehicleModel/bj40/point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line1.png" class="line1"
            :style="showpagenum==5&&pagenum==5?'--topClip:0vw':'--topClip:12vw'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line2.png" class="line2"
            :style="showpagenum==5&&pagenum==5?'--rightClip:0vw':'--rightClip:26.32vw'" alt="">
        </div>
        <div class="point-main bright-spot top" :class="showpagenum==5&&pagenum==5?'show':'hide'"
          v-if="mobileClass!=''">
          <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
            :style="showpagenum==5&&pagenum==5?'--topClip:0vw':'--topClip:100%'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
            :style="showpagenum==5&&pagenum==5?'--rightClip:0vw':'--rightClip:100%'" alt="">
        </div>
        <div class="dialog-main" :class="showpagenum==5&&pagenum==5?'show':'hide'">
          <img src="~/assets/image/vehicleModel/bj40/page5_cover.png" class="img" alt="">
          <div class="text-view">
            <div class="title">{{page5_text[$i18n.locale].title}}</div>
            <div class="memo">{{page5_text[$i18n.locale].memo}}</div>
          </div>
        </div>
      </div>
      <!-- 底盘 -->
      <div class="page-container page6" :class="showpagenum==6&&pagenum==6?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <img src="~/assets/image/vehicleModel/bj40/dipan.png" class="dipan">
        <div class="point-main bright-spot" v-if="mobileClass==''">
          <img src="~/assets/image/vehicleModel/bj40/point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line1.png" class="line1"
            :style="showpagenum==6&&pagenum==6?'--topClip:0vw':'--topClip:12vw'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line2.png" class="line2"
            :style="showpagenum==6&&pagenum==6?'--rightClip:0vw':'--rightClip:26.32vw'" alt="">
        </div>
        <div class="point-main bright-spot top" :class="showpagenum==6&&pagenum==6?'show':'hide'"
          v-if="mobileClass!=''">
          <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
            :style="showpagenum==6&&pagenum==6?'--topClip:0vw':'--topClip:100%'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
            :style="showpagenum==6&&pagenum==6?'--rightClip:0vw':'--rightClip:100%'" alt="">
        </div>
        <div class="dialog-main" :class="showpagenum==6&&pagenum==6?'show':'hide'">
          <img src="~/assets/image/vehicleModel/bj40/page6_cover.png" class="img" alt="">
          <div class="text-view">
            <div class="title">{{page6_text[$i18n.locale].title}}</div>
            <div class="memo">{{page6_text[$i18n.locale].memo}}</div>
          </div>
        </div>
      </div>

      <!-- 车门 -->
      <div class="page-container page7" :class="showpagenum==7&&pagenum==7?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="point-main bright-spot" v-if="mobileClass==''">
          <img src="~/assets/image/vehicleModel/bj40/point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line1.png" class="line1"
            :style="showpagenum==7&&pagenum==7?'--topClip:0vw':'--topClip:12vw'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line2.png" class="line2"
            :style="showpagenum==7&&pagenum==7?'--rightClip:0vw':'--rightClip:26.32vw'" alt="">
        </div>
        <div class="point-main bright-spot top" :class="showpagenum==7&&pagenum==7?'show':'hide'"
          v-if="mobileClass!=''">
          <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
            :style="showpagenum==7&&pagenum==7?'--topClip:0vw':'--topClip:100%'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
            :style="showpagenum==7&&pagenum==7?'--rightClip:0vw':'--rightClip:100%'" alt="">
        </div>
        <div class="dialog-main" :class="showpagenum==7&&pagenum==7?'show':'hide'">
          <img src="~/assets/image/vehicleModel/bj40/page7_cover.png" class="img" alt="">
          <div class="text-view">
            <div class="title">{{page7_text[$i18n.locale].title}}</div>
            <div class="memo">{{page7_text[$i18n.locale].memo}}</div>
          </div>
        </div>
      </div>
      <!-- 外观-家族 -->
      <div class="page-container page8" :class="showpagenum==8&&pagenum==8?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="point-main bright-spot" v-if="mobileClass==''">
          <img src="~/assets/image/vehicleModel/bj40/point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line1.png" class="line1"
            :style="showpagenum==8&&pagenum==8?'--topClip:0vw':'--topClip:12vw'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line2.png" class="line2"
            :style="showpagenum==8&&pagenum==8?'--rightClip:0vw':'--rightClip:26.32vw'" alt="">
        </div>
        <div class="point-main bright-spot top" :class="showpagenum==8&&pagenum==8?'show':'hide'"
          v-if="mobileClass!=''">
          <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
            :style="showpagenum==8&&pagenum==8?'--topClip:0vw':'--topClip:100%'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
            :style="showpagenum==8&&pagenum==8?'--rightClip:0vw':'--rightClip:100%'" alt="">
        </div>
        <div class="dialog-main" :class="showpagenum==8&&pagenum==8?'show':'hide'">

          <img src="~/assets/image/vehicleModel/bj40/page8_cover.png" class="img" alt="">
          <div class="text-view">
            <div class="title">{{page8_text[$i18n.locale].title}}</div>
            <div class="memo">{{page8_text[$i18n.locale].memo}}</div>
          </div>
        </div>
      </div>
      <!-- 车尾 -->
      <div class="page-container page9" :class="showpagenum==9&&pagenum==9?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="point-main bright-spot" v-if="mobileClass==''">
          <img src="~/assets/image/vehicleModel/bj40/point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line1.png" class="line1"
            :style="showpagenum==9&&pagenum==9?'--topClip:0vw':'--topClip:12vw'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/point-line2.png" class="line2"
            :style="showpagenum==9&&pagenum==9?'--rightClip:0vw':'--rightClip:26.32vw'" alt="">
        </div>
        <div class="point-main bright-spot top" :class="showpagenum==9&&pagenum==9?'show':'hide'"
          v-if="mobileClass!=''">
          <img src="~/assets/image/vehicleModel/bj40/mob-point.png" class="point" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line1.png" class="line1"
            :style="showpagenum==9&&pagenum==9?'--topClip:0vw':'--topClip:100%'" alt="">
          <img src="~/assets/image/vehicleModel/bj40/mob-point-line2.png" class="line2"
            :style="showpagenum==9&&pagenum==9?'--rightClip:0vw':'--rightClip:100%'" alt="">
        </div>
        <div class="dialog-main" :class="showpagenum==9&&pagenum==9?'show':'hide'">
          <img src="~/assets/image/vehicleModel/bj40/page9_cover.png" class="img" alt="">
          <div class="text-view">
            <div class="title">{{page9_text[$i18n.locale].title}}</div>
            <div class="memo">{{page9_text[$i18n.locale].memo}}</div>
          </div>
        </div>

      </div>
    </div>
    <!-- 两个轮播一个配置表一个留资 -->
    <div class="blur-page" v-if="showpagenum==10&&pagenum==10">
      <!--  -->
      <div class="page-container interior-page" :class="showpagenum==10&&pagenum==10?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="hideBlur">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="carousel-title">{{interior_title[$i18n.locale]}}</div>
        <div class="carousel-main swiper" v-swiper:interiorSwiper="interior_swiperOption"
          @slideChange="handleInteriorSlideChange" v-if="showInterior">
          <div class="swiper-wrapper">
            <div class="carousel-item swiper-slide" v-for="(item,index) in interior_list[$i18n.locale]" :key="index">
              <img :src="item.cover" class="carousel-item-img" alt="" @click="handleSwiperClick"
                @tap="handleSwiperClick" :data-item="JSON.stringify(item)" :data-type="1" />
              <div class="carousel-item-text">
                <div class="carousel-item-text-title">{{item.title}}</div>
                <div class="carousel-item-text-content" v-html="item.intro"></div>
              </div>
              <div class="mob-line" v-if="mobileClass!=''"></div>
            </div>
          </div>
          <div slot="pagination" v-if="mobileClass==''" class="swiper-pagination swiper-pagination-bullets">
          </div>
          <div class="swiper-scrollbar" v-if="mobileClass!=''" slot="scrollbar"></div>
          <div class="swiper-button-prev" v-if="mobileClass!=''">
            <img src="~/assets/image/vehicleModel/bj40/orange-prev.png" alt="">
          </div>
          <div class="swiper-button-next" v-if="mobileClass!=''">
            <img src="~/assets/image/vehicleModel/bj40/orange-next.png" alt="">
          </div>
        </div>
      </div>
      <div class="page-container appearance-page" :class="showpagenum==10&&pagenum==10?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="carousel-title">{{appearance_title[$i18n.locale]}}</div>
        <div class="carousel-main swiper" v-swiper:appearanceSwiper="appearance_swiperOption"
          @slideChange="handleAppearanceSlideChange" v-if="showAppearance">
          <div class="swiper-wrapper">
            <div class="carousel-item swiper-slide" v-for="(item,index) in appearance_list[$i18n.locale]" :key="index">
              <img :src="item.cover" class="carousel-item-img" alt="" @click="handleSwiperClick"
                @tap="handleSwiperClick" :data-item="JSON.stringify(item)" :data-type="2" />
              <div class="carousel-item-text">
                <div class="carousel-item-text-title">{{item.title}}</div>
                <div class="carousel-item-text-content" v-html="item.intro"></div>
              </div>
              <div class="mob-line" v-if="mobileClass!=''"></div>
            </div>
          </div>
          <div slot="pagination" v-if="mobileClass==''" class="swiper-pagination swiper-pagination-bullets">
          </div>
          <div class="swiper-scrollbar" v-if="mobileClass!=''" slot="scrollbar"></div>
          <div class="swiper-button-prev" v-if="mobileClass!=''">
            <img src="~/assets/image/vehicleModel/bj40/orange-prev.png" alt="">
          </div>
          <div class="swiper-button-next" v-if="mobileClass!=''">
            <img src="~/assets/image/vehicleModel/bj40/orange-next.png" alt="">
          </div>
        </div>
      </div>
      <!-- 美图 -->
      <!-- <div class="page-container" :class="showpagenum==10&&pagenum==10?'show':'hide'">
                <div class="carousel-title">GLEAMING IMPRESSIONS</div>
                <div class="meitu-view swiper" v-swiper:meituSwiper="meitu_swiperOption" v-if="mobileClass==''">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide meitu-img-view" v-for="(item,index) in meitu_list" :key="index">
                            <img :src="item.colorImgUrl" class="img" alt="">
                            <div class="carousel-item-text">
                                <div class="carousel-item-text-title">{{item.title}}</div>
                                <div class="carousel-item-text-content">{{item.intro}}</div>
                            </div>
                        </div>
                        <div class="swiper-slide meitu-img-view"></div>
                    </div>
                </div>
                <div class="carousel-main meitu-swiper swiper" v-swiper:mobMeituSwiper="mob_meitu_swiperOption"
                    v-if="mobileClass!=''">
                    <div class="swiper-wrapper">
                        <div class="carousel-item swiper-slide" v-for="(item,index) in meitu_list" :key="index">
                            <img :src="item.colorImgUrl" class="carousel-item-img" alt="" />
                            <div class="carousel-item-text hide">
                                <div class="carousel-item-text-title">1111111111111111</div>
                                <div class="carousel-item-text-content">11111111111111111</div>
                            </div>
                            <div class="mob-line" v-if="mobileClass!=''"></div>
                        </div>
                    </div>
                    <div class="swiper-scrollbar" slot="scrollbar"></div>
                </div>
            </div> -->
      <div class="page-container" :class="showpagenum==10&&pagenum==10?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <img src="~/assets/image/vehicleModel/bj40/fix-next.png" alt="" v-if="mobileClass==''" class="fix-btn next"
          @click="fixBtnFun" data-type="down">
        <div class="carousel-title config-table">BJ40 SPECIFICATIONS</div>
        <div class="config-table-container" :class="{ 'ar': 'ar' == $i18n.locale }">
          <ConfigTable id="36"></ConfigTable>
        </div>
      </div>
      <div class="page-container" :class="showpagenum==10&&pagenum==10?'show':'hide'">
        <img src="~/assets/image/vehicleModel/bj40/fix-prev.png" alt="" v-if="mobileClass==''" class="fix-btn prev"
          @click="fixBtnFun" data-type="up">
        <div class="carousel-title config-table">BJ40 SPECIFICATIONS</div>
        <div class="test-drive-container" :class="{ 'ar': 'ar' == $i18n.locale }">
          <CommonTestDrive></CommonTestDrive>
        </div>
      </div>
    </div>
    <!-- 全屏图片弹窗 -->
    <div class="full-dialog" v-if="showFullDialog">
      <img :src="full_dialog_obj.cover" alt="" class="img">
      <img src="~/assets/image/vehicleModel/bj40/guanbi2.png" alt="" class="close-icon" @click="handleCloseFullDialog"
        @tap="handleCloseFullDialog">
      <div class="full-text-main">
        <div class="carousel-item-text-title">{{full_dialog_obj.title}}</div>
        <div class="carousel-item-text-content" v-html="full_dialog_obj.intro"></div>
      </div>
      <div class="swiper-button-prev">
        <img src="~/assets/image/vehicleModel/bj40/orange-prev.png" alt="" @click="changeFullImg" @tap="changeFullImg"
          data-type="prev">
      </div>
      <div class="swiper-button-next">
        <img src="~/assets/image/vehicleModel/bj40/orange-next.png" alt="" @click="changeFullImg" @tap="changeFullImg"
          data-type="next">
      </div>
    </div>
  </div>
</template>

<script>
  import AnyTouch from 'any-touch';
  import { gsap } from 'gsap/dist/gsap';
  import { ScrollTrigger } from 'gsap/dist/ScrollTrigger';
  import ConfigTable from '~/components/vehicleModel/ConfigTable.vue'
  import CommonTestDrive from '~/components/vehicleModel/common/CommonTestDrive.vue'

  gsap.registerPlugin(ScrollTrigger);
  export default {
    name: 'VehicleBJ40',
    layout: 'main',
    components: { ConfigTable, CommonTestDrive },
    data() {
      return {
        vrViewUrl: '',
        // 序列帧相关
        allPageCount: 10,
        aniPageNum: 30,
        sequenceFrameIndex: 0,
        sequenceFrameCount: 270,
        sequenceFramePrefix: '/bj40/frame/',
        sequenceFrameSuffix: '.webp',
        autoPlayFrameCount: 31,
        hideSequence: false,
        //
        loadingProgress: 200,
        hideLoading: false,
        bgtype: 1,
        bgsrc: require('~/assets/image/vehicleModel/bj40/bg1.png'),
        defaultBg: require('~/assets/image/vehicleModel/bj40/bg1.png'),
        page6Bg: require('~/assets/image/vehicleModel/bj40/bg2.png'),
        ats_bg_arr: [
          { url: require('~/assets/image/vehicleModel/bj40/bg2.png'), },
          { url: require('~/assets/image/vehicleModel/bj40/bg2.png'), },
          { url: require('~/assets/image/vehicleModel/bj40/bg2.png'), },
        ],
        page1_btn: {
          "en": [
            { txt: 'TEST DRIVE' },
            { txt: 'VIEW VR' },
          ],
          "es": [
            { txt: 'Prueba de conducción' },
            { txt: 'Ver VR' },
          ],
          "ar": [
            { txt: 'تجربة القيادة' },
            { txt: 'عرض VR' },
          ],
        },
        page2_text: {
          "en": { title: "Part-time 4WD system", memo: `Provide the timely four-wheel drive system, support electronic knob switching, including four modes of 2H, 4H, 4L and 4A to meet different driving needs. `, },
          "es": { title: "Sistema de 4WD a tiempo parcial", memo: `Equipado con un sistema de tracción integral inteligente, soporta el cambio de cuatro modos de 2H, 4H, 4L, 4A mediante un selector electrónico, adaptándose a las diferentes necesidades de conducción.`, },
          "ar": { title: "نظام الدفع الرباعي بدوام جزئي", memo: `مزوّدة بنظام الدفع الرباعي الموقوت، وهو يدعم المقبض الإلكتروني للتبديل بين الأوضاع الأربعة 2H و4H و4L و4A لتلبية احتياجات القيادة المختلفة.`, },
        },
        ats_text: {
          "en": { title: "ATS system", memo: `The all-terrain control system （ATS ） supports up to 11 driving modes. The system provides the optimal power distribution for the vehicle through intelligent switching, so as to optimize the driving stability, easily cope with various complex terrains.`, },
          "es": { title: "Sistema de ATS", memo: `El sistema de control todo terreno de ATS soporta hasta 11 modos de conducción, incluidos arena, nieve, roca, etc. Mediante un cambio inteligente, el sistema ofrece la mejor distribución de potencia al vehículo, optimizando la estabilidad y mejorando la diversión al conducir en terrenos complejos.`, },
          "ar": { title: "نظام ATS", memo: `يدعم نظام ATS للتحكم في جميع التضاريس ما يصل إلى 11 وضعاً للقيادة، بما في ذلك الرمال والثلوج والصخور وإلخ. من خلال التبديل الذكي، يوفر النظام للسيارة أفضل توزيع للقوة ويحسّن ثبات القيادة ويتأقلم بسهولة مع مختلف التضاريس المعقدة ويعزز متعة القيادة.` },
        },
        ats_current_index: 0,
        ats_btn: {
          "en": [
            { txt: 'Rocky terrain', icon: require('~/assets/image/vehicleModel/bj40/ats/yanshi_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/yanshi_orange.png') },
            { txt: 'Snowy terrain', icon: require('~/assets/image/vehicleModel/bj40/ats/xuehua_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/xuehua_orange.png') },

            { txt: 'Desert terrain', icon: require('~/assets/image/vehicleModel/bj40/ats/shamo_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/shamo_orange.png') },
          ],
          "es": [
            { txt: 'Terreno rocoso', icon: require('~/assets/image/vehicleModel/bj40/ats/yanshi_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/yanshi_orange.png') },
            { txt: 'Terreno nevado', icon: require('~/assets/image/vehicleModel/bj40/ats/xuehua_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/xuehua_orange.png') },

            { txt: 'Terreno desértico', icon: require('~/assets/image/vehicleModel/bj40/ats/shamo_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/shamo_orange.png') },
          ],
          "ar": [
            { txt: 'التضاريس الصخرية', icon: require('~/assets/image/vehicleModel/bj40/ats/yanshi_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/yanshi_orange.png') },
            { txt: 'التضاريس الثلجية', icon: require('~/assets/image/vehicleModel/bj40/ats/xuehua_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/xuehua_orange.png') },

            { txt: 'التضاريس الصحراوية', icon: require('~/assets/image/vehicleModel/bj40/ats/shamo_grey.png'), choose_icon: require('~/assets/image/vehicleModel/bj40/ats/shamo_orange.png') },
          ],
        },
        page4_title: {
          "en": { title: 'Excellent off-road geometric data', txt: "fording depth" },
          "es": { title: 'Excelentes datos geométricos todoterreno', txt: "profundidad de vadeo" },
          "ar": { title: 'بيانات هندسية متميزة على الطرقات الوعرة', txt: "الأرضي، وعمق" },
        },
        page4_angle: {
          "en": {
            // 接近角
            "p1": "approach angle",
            // 涉水深度
            "p2": "fording depth",
            // 纵向通过角
            "p3": "ramp angle",
            // 离地间隙
            "p4": "ground clearance",
            // 离去角
            "p5": "departure angle",
          },
          "es": {
            // 接近角
            "p1": "ángulo de aproximación",
            // 涉水深度
            "p2": "profundidad de vadeo",
            // 纵向通过角
            "p3": "Ángulo de paso longitudinal",
            // 离地间隙
            "p4": "altura al suelo",
            // 离去角
            "p5": "ángulo de salida",
          },
          "ar": {
            // 接近角
            "p1": "وزاوية الاقتراب",
            // 涉水深度
            "p2": "عمق الخوض في المياه",
            // 纵向通过角
            "p3": "زاوية المرور الطولية",
            // 离地间隙
            "p4": "الخلوص الأرضي",
            // 离去角
            "p5": "وزاوية الانطلاق",
          },
        },
        page4_table: {
          "en": [
            {
              title: this.$t('home.POWER'),
              num: '241.38 HP'
            },
            {
              title: this.$t('home.TORQUE'),
              num: '305 N·M'
            },
            {
              title: this.$t('bj30.ENGINE'),
              num: '2.0 T'
            },
            {
              title: 'Driving mode',
              num: '11'
            },
          ],
          "es": [
            {
              title: this.$t('home.POWER'),
              num: '241.38 HP'
            },
            {
              title: this.$t('home.TORQUE'),
              num: '305 N·M'
            },
            {
              title: this.$t('bj30.ENGINE'),
              num: '2.0 T'
            },
            {
              title: 'Modos de conducción',
              num: '11'
            },
          ],
          "ar": [
            {
              title: this.$t('home.POWER'),
              num: '241.38 HP'
            },
            {
              title: this.$t('home.TORQUE'),
              num: '305 N·M'
            },
            {
              title: this.$t('bj30.ENGINE'),
              num: '2.0 T'
            },
            {
              title: 'أوضاع القيادة',
              num: '11'
            },
          ],
        },
        // 内嵌式防滚架
        page5_text: {
          "en": { title: "cage body", memo: `Military-grade cage body design can effectively reduce collision deformation;The application ratio of the thermal forming steel is 79.8%;The lightweight coefficient is 4.34.`, },
          "es": { title: "cuerpo de jaula", memo: `Diseño de jaula de cuerpo tipo militar, reduciendo eficazmente las deformaciones por colisión.Proporción de acero conformado en caliente: 79.8%Coeficiente de ligereza: 4.34`, },
          "ar": {
            title: "جسم القفص", memo: `تصميم هيكل قفص من الدرجة العسكرية، مما يقلل بشكل فعال من تشوه التصادم
79.8% من نسبة تطبيق الفولاذ المشكل على الساخن
عامل خفة الوزن 4.34`},
        },
        // 军工级越野底盘
        page6_text: {
          "en": {
            title: "Military-grade off-road body-on-frame", memo: `Two longitudinal and eight transverse integrated crossbeam is made up to combine the high strength steel with ultra-high strength steel.
The steel ratio is up to 52%, which greatly improves the resistances to distortion and impact of the body structure, to ensure stability and reliability in the extreme off-road environment.`,
          },
          "es": {
            title: "Chasis todoterreno de grado militar", memo: `Diseño de chasis con viga integral de dos vigas longitudinales y ocho transversales, combinando acero de alta resistencia y acero ultra alto.
Proporción de acero de hasta el 52%, mejorando significativamente la capacidad del vehículo para resistir torsión e impactos, asegurando estabilidad incluso en condiciones extremas de conducción off-road.`,
          },
          "ar": {
            title: "هيكل من الدرجة العسكرية للطرق الوعرة", memo: `عارضتان طوليتان وثماني عوارض عرضية مدمجة مكوّنة من 52% من الفولاذ عالي القوة و48% من الفولاذ فائق القوة الذي يوفر صلابة استثنائية للهيكل تمتص طاقة الاصطدام وتقلل من تشوه السيارة، مما يضمن السلامة.`
          },
        },
        // 外观-家族
        page7_text: {
          "en": {
            title: "Family design heritage", memo: `Full-LED surrounding technology five-hole grille.`,
          },
          "es": {
            title: "Herencia del diseño familiar", memo: `Parrilla envolvente LED de cinco orificios de estilo tecnológico`,
          },
          "ar": {
            title: "تراث التصميم العائلي", memo: `شبكة محيطية كاملة بتقنية LED ذات 5 فتحات`,
          }
        },
        // 车门
        page8_text: {
          "en": {
            title: "Innovative frameless door design", memo: `Strong body support frameless door design provides a broad side-window view.`,
          },
          "es": {
            title: "Diseño innovador de puertas sin marco", memo: `Diseño de puertas sin marco con estructura robusta, ofreciendo una vista lateral más amplia.`,
          },
          "ar": {
            title: "تصميم مبتكر للأبواب بدون إطار", memo: `تصميم قوي للأبواب بدون إطار داعم للهيكل، مما يوفر رؤية أوسع للنوافذ الجانبية`,
          }
        },
        // 车尾
        page9_text: {
          "en": {
            title: "Split tail door", memo: `With damping, beautiful and convenient features, the upper and lower sectional design enhances the practicality of the tail door.`,
          },
          "es": {
            title: "Puerta trasera dividida", memo: `Diseño dividido en secciones superiores e inferiores, con amortiguación integrada para combinar belleza y conveniencia, mejorando la funcionalidad de la puerta trasera.`,
          },
          "ar": {
            title: "باب خلفي منقسم", memo: `تصميم علوي وسفلي مجزأ، مع تخميد ذاتي، يجمع بين الجمالية والراحة، ويعزز الطابع العملي للباب الخلفي`,
          }
        },
        showFullDialog: false,
        full_dialog_type: null,
        full_dialog_obj: { cover: require('~/assets/image/vehicleModel/bj40/trim/img1.jpg'), title: 'Technology trim molding', intro: `Top broad vision, wide comfortable space.`, },
        interior_title: {
          en: "INTERIOR DESIGN",
          es: "DISEÑO DE INTERIORES",
          ar: "تصميم المقصورة",
        },
        interior_list: {
          en: [
            // 科技内饰
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img1.jpg'), title: 'Technology trim molding', intro: `Top broad vision, wide comfortable space.`, },
            // 越野五连屏
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img2.jpg'), title: 'Five-Screen Linkage', intro: `12.8-inch front passenger seat entertainment screen </br> Global initiative 1.92-inch start key screen Provide the most advanced 815 engine chip.`, },
            // 全景天窗
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img3.jpg'), title: 'Large panoramic skylight', intro: `Intelligent switch, to enjoy transparent driving experience.`, },
            // 音响系统
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img4.jpg'), title: 'INFINITY audio system', intro: `12 speakers +1 external power amplifier, automatically adjust the volume and sound with the speed.`, },
            // 载物空间
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img5.jpg'), title: 'Large trunk space', intro: `The rear seat features a one-button fold-down function, effortlessly converting into a flat trunk space to accommodate large items with ease. Whether for travel essentials or leisurely reclining, it offers seamless convenience.`, },
            // 无线充电
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img6.jpg'), title: 'Wireless charging', intro: `Built-in 50W wireless charger, and 10 in-car charging ports.`, },
          ],
          es: [
            // 科技内饰
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img1.jpg'), title: 'Diseño interior tecnológico', intro: `Visibilidad panorámica de primera calidad, con un espacio amplio y cómodo.`, },
            // 越野五连屏
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img2.jpg'), title: 'Primera pantalla quíntuple para todoterreno', intro: `Pantalla de entretenimiento de 12.8” para el copiloto <br/> Pantalla de botón de inicio de 1.92” única en el mundo <br/> Equipado con el chip de vehículo más avanzado, 815`, },
            // 全景天窗
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img3.jpg'), title: 'Techo panorámico ultra amplio', intro: `Interruptores inteligentes para una experiencia de conducción fluida.`, },
            // 音响系统
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img4.jpg'), title: 'Sistema de audio de INFINITY', intro: `12 altavoces + 1 amplificador externo, ajustando automáticamente el volumen y el sonido con la velocidad del vehículo.`, },
            // 载物空间
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img5.jpg'), title: 'Gran espacio del maletero', intro: `El asiento trasero se abate con único botón, convirtiéndose fácilmente en un maletero plano para guardar los ítems grandes con facilidad. No sólo para viajar esencialmente sino también para reclinarse al azar, a usted le ofrecerá la comodidad y la conveniencia.`, },
            // 无线充电
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img6.jpg'), title: 'Carga inalámbrica', intro: `Cargador inalámbrico de 50W integrado, con 10 puertos de carga en el vehículo.`, },
          ],
          ar: [
            // 科技内饰
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img1.jpg'), title: 'التصميم الداخلي التكنولوجي', intro: `منظر مفتوح عالي الجودة ومساحة واسعة ومريحة.`, },
            // 越野五连屏
            {
              cover: require('~/assets/image/vehicleModel/bj40/trim/img2.jpg'), title: 'أول خمس شاشات على الطرق الوعرة', intro: `شاشة ترفيه للركاب مقاس 12.8 بوصة<br/>
أول شاشة زر تشغيل بقياس 1.92 بوصة في العالم<br/>
مزودة بشريحة 815 الأكثر تطوراً في السيارة`,
            },
            // 全景天窗
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img3.jpg'), title: 'فتحة سقف بانورامية كبيرة', intro: `مفتاح ذكي، استمتع بتجربة قيادة شفافة`, },
            // 音响系统
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img4.jpg'), title: 'نظام يانفيليكس الصوتي', intro: `12 مكبر صوت + 1 مضخم صوت خارجي، يضبط مستوى الصوت وتأثير الصوت تلقائياً مع سرعة السيارة`, },
            // 载物空间
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img5.jpg'), title: 'مساحة صندوق الأمتعة الكبيرة', intro: `تحقق السيارة نصف قطر دوران صغير من خلال قفل العجلة الخلفية الداخلية وتدويرها قليلاً من خلال المكابح الإلكترونية، بينما تدور بقية الإطارات بشكل طبيعي. يضمن كفاءة مناورات الانعطاف في المساحات الضيقة.`, },
            // 无线充电
            { cover: require('~/assets/image/vehicleModel/bj40/trim/img6.jpg'), title: 'الشحن اللاسلكي', intro: `شاحن لاسلكي مدمج بقوة 50 واط و10 منافذ شحن داخل السيارة`, },
          ]
        },
        interior_swiperOption: {
          speed: 1000,
          slidesPerView: 'auto',
          loop: true,
          centeredSlides: true,
          // autoplay: {
          //     delay: 6000,
          // },
          navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
          },
          pagination: {
            el: '.swiper-pagination',
            clickable: true,
            renderBullet(index, className) {
              return `<div class="${className} swiper-pagination-bullet-custom"></div>`

            },
          },
          scrollbar: {
            el: '.swiper-scrollbar',
            hide: true
          },
        },
        showInterior: false,
        appearance_current_index: 0,
        appearance_title: {
          en: "DRIVING EXPERIENCE",
          es: "EXPERIENCIA DE CONDUCCIÓN",
          ar: "تجربة القيادة",
        },
        appearance_list: {
          en: [
            //  多功能方向盘
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img1.jpg'), title: 'Multi-functional steering wheel', intro: `Three-gear steering feel: three modes of driving experience, including light, standard, and sports;<br/>Steering wheel memory: intelligent memory of the current position, automatic adjustment.`, },
            // 战术掉头
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img2.jpg'), title: 'Tactical U-turn', intro: `The vehicle locks the inside rear wheel by the electronic brakes and rotates slightly, while the rest of the tires rotate normally to achieve a small turning radius, and ensure the completion of the efficient turn-around in narrow spaces.`, },
            // 拖车拖拽资质
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img3.jpg'), title: 'Towing qualification', intro: `Towing port + power taking port, maximum traction up to 2.5T.`, },
          ],
          es: [
            //  多功能方向盘
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img1.jpg'), title: 'Volante multifunción', intro: `Sensación de dirección con tres modos: ligero, estándar y deportivo.<br/>Ajuste eléctrico de dirección de 4 direcciones: arriba, abajo, adelante y atrás.`, },
            // 战术掉头
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img2.jpg'), title: 'U-giro táctico', intro: `El vehículo bloquea electrónicamente la rueda trasera interna y realiza una ligera rotación, mientras que las demás ruedas giran normalmente, permitiendo un radio de giro reducido y asegurando una maniobra eficiente en espacios reducidos.`, },
            // 拖车拖拽资质
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img3.jpg'), title: 'Capacidad de remolque', intro: `Toma de remolque y toma de electricidad, con capacidad máxima de tracción de 2.5T, más conveniente para actividades off-road y camping.`, },
          ],
          ar: [
            //  多功能方向盘
            {
              cover: require('~/assets/image/vehicleModel/bj40/driving/img1.jpg'), title: 'عجلة قيادة متعددة الوظائف', intro: `ثلاثة أوضاع للتوجيه: خفيف، وقياسي، ورياضي بثلاثة  أوضاع لتجربة القيادة<br/>
للتحكم الإلكتروني في 4 اتجاهات: دعم تعديل كهربائي لأعلى ولأسفل، وأمامي وخلفي`,
            },
            // 战术掉头
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img2.jpg'), title: 'الانعطاف التكتيكي', intro: `تحقق السيارة نصف قطر دوران صغير من خلال قفل العجلة الخلفية الداخلية وتدويرها قليلاً من خلال المكابح الإلكترونية، بينما تدور بقية الإطارات بشكل طبيعي. يضمن كفاءة مناورات الانعطاف في المساحات الضيقة.`, },
            // 拖车拖拽资质
            { cover: require('~/assets/image/vehicleModel/bj40/driving/img3.jpg'), title: 'مؤهلات القطر', intro: `منفذ مربع للمقطورة + منفذ التقاط الطاقة، قطر أقصى سحب يصل إلى 2.5 طن، وعلى الطرق الوعرة والتخييم أكثر راحة`, },
          ],
        },
        appearance_swiperOption: {
          speed: 1000,
          slidesPerView: 'auto',
          loop: true,
          centeredSlides: true,
          // autoplay: {
          //     delay: 8000,
          // },
          navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
          },
          pagination: {
            el: '.swiper-pagination',
            clickable: true,
            renderBullet(index, className) {
              return `<div class="${className} swiper-pagination-bullet-custom"></div>`

            },
          },
          scrollbar: {
            el: '.swiper-scrollbar',
            hide: true
          },
        },
        showAppearance: false,
        // meitu_list: [
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        //     { colorImgUrl: require('~/assets/image/vehicleModel/bj60/img_04.jpg') },
        // ],
        // meitu_swiperOption: {
        //     slidesPerView: "auto",
        //     // slidesPerView: 3,
        //     slidesPerColumn: 2,
        //     spaceBetween: 10,
        // },
        // mob_meitu_swiperOption: {
        //     speed: 1000,
        //     slidesPerView: 1.5,
        //     loop: true,
        //     centeredSlides: true,
        //     spaceBetween: 10,
        //     effect: 'coverflow',
        //     grabCursor: true,
        //     coverflowEffect: {
        //         rotate: 50,
        //         stretch: 0,
        //         depth: 100,
        //         modifier: 1,
        //         slideShadows: true
        //     },
        //     scrollbar: {
        //         el: '.swiper-scrollbar',
        //         hide: true
        //     },
        // },
        lastScrollTop: 0,     // 上次滚动位置
        scrollDirection: null, // 滚动方向（'up'/'down'）
        pausing: true,
        currentPauseFrame: 0,
        scrollEnabled: false,// 是否允许页面滚动
        autoPlayInterval: null,
        page4StopNum: 0,
        pagenum: 0,
        showpagenum: 0,
        startY: 0,
        mobileClass: 999,
        reqId: null,
        ctx: null,
        images: [],
        directionStr: 'down',
        loadedTotal: 0,
        loadingProgress: 0,
        animationId: null,
        frameTiming: 30,

      };
    },
    head() {
      return {
        meta: [
          { name: 'x5-page-mode', content: 'no-elastic' },
          { name: "x5-orientation", content: "portrait", },
          { name: "apple-mobile-web-app-capable", content: "yes" },
          { name: "full-screen", content: "yes" }
        ]
      }
    },
    watch: {
      $mq: {
        immediate: true,
        handler(n, o) {
          console.log('mounted', this.$mq)
          if (['xs', 'md', 'sm'].includes(this.$mq)) {
            this.mobileClass = 'mobile';
            this.interior_swiperOption.pagination = {};
            this.appearance_swiperOption.pagination = {};
            this.sequenceFramePrefix = '/bj40/mob_frame/';
          } else {
            this.mobileClass = ''

            this.interior_swiperOption.scrollbar = {};
            this.appearance_swiperOption.scrollbar = {};
          }
        },
      },
    },
    created() {
      // 从别的页面回来的时候再次关闭滚动
      this.$router.afterEach((to, from) => {
        console.log('路由从', from.path, '切换到', to.path);

        if (to.path.includes('models/36')) {
          if (this.pagenum < 10) {
            this.scrollTop();
            setTimeout(() => {
              this.disableScroll();
            }, 500);
          }

        }

        // 在这里执行你想要的操作
      });
      if (process.client) {
        this.disableScroll();

      }
    },
    mounted() {
      this.setVh(); // 初始化
      if (['xs', 'md', 'sm'].includes(this.$mq)) {
        this.mobileClass = 'mobile';
        this.interior_swiperOption.pagination = {};
        this.appearance_swiperOption.pagination = {};
        this.sequenceFramePrefix = '/bj40/mob_frame/';
      } else {
        this.mobileClass = ''

        this.interior_swiperOption.scrollbar = {};
        this.appearance_swiperOption.scrollbar = {};
      }
      this.chunkedPreload();
      // window.addEventListener('resize', this.setVh); // 视窗变化时更新
      // console.log('$i18n.locale', this.$i18n.locale)
      this.setupSequenceAnimation()
      this.mobTouchFun();
      this.preventPullToRefresh();
      this.$nextTick(() => {
        this.getModelData();
        /*微信浏览器特殊处理*/
        if (window.navigator.userAgent.toLowerCase().match(/MicroMessenger/i) == 'micromessenger') {
          document.body.classList.add('index_body');//添加禁止滚动的样式

        } else {
          document.body.classList.remove('index_body');//去除禁止滚动的样式
        }
        const ctx = this.$refs.canvas.getContext('2d');
        // 初始化Canvas尺寸
        this.$refs.canvas.width = window.innerWidth;
        this.$refs.canvas.height = window.innerHeight;
        this.ctx = ctx;
        this.loadingProgress = 0;
        this.scrollTop();
      })
      // 配置 ScrollTrigger
      this.setupScrollTrigger();
    },
    beforeRouteLeave(to, from, next) {
      // 导航离开该组件的对应路由时调用
      // 可以访问 `this`
      // console.log('即将离开路由', from.path);
      this.enableScroll();
      this.clearAutoPlayInterval()
      document.body.classList.remove('index_body');//去除禁止滚动的样式
      next();
    },
    beforeDestroy() {
      this.autoPlayInterval = null;
      // 清理 ScrollTrigger
      ScrollTrigger.getAll().forEach(t => t.kill());
      this.images.forEach(img => (img.src = '')); // 解除引用
      this.images = [];
      this.$refs.canvas.width = 0; // 释放Canvas内存
    },
    methods: {
      hrefFun(e) {
        console.log(e)
      },
      fixBtnFun(e) {
        console.log(e)
        let type = e.target.dataset.type;
        if (this.pagenum < 10) {
          this.changePageFun(type)
        } else if (this.pagenum == 10) {
          switch (type) {
            case 'up':
              this.scrollBy(-1)
              break
            case 'down':
              this.scrollBy(1)
              break
          }
        }
      },
      scrollBy(delta) {
        this.pausing = true

        const currentY = window.scrollY
        const viewportHeight = window.innerHeight
        const maxScroll = document.documentElement.scrollHeight - viewportHeight

        // 计算目标位置并限制范围
        const targetY = Math.max(0, Math.min(
          currentY + delta * viewportHeight,
          maxScroll
        ))

        window.scrollTo({
          top: targetY,
          behavior: 'smooth'
        })

        setTimeout(() => {
          this.pausing = false
        }, 800)
      },
      setVh() {
        let allh = window.innerHeight;
        let vh = allh / 100;
        document.documentElement.style.setProperty('--vh', `${vh}px`);
      },
      preventPullToRefresh() {
        let startY = 0
        const isTop = () => window.scrollY <= 0

        const touchStart = (e) => {
          startY = e.touches[0].clientY;
        }

        const touchMove = (e) => {
          const currentY = e.touches[0].clientY;
          if (isTop() && currentY > startY) { // 顶部且下拉
            e.preventDefault()
          }
        }

        // 使用非passive事件确保preventDefault生效
        document.addEventListener('touchstart', touchStart, { passive: false })
        document.addEventListener('touchmove', touchMove, { passive: false })

        // 组件销毁时移除监听
        this.$once('hook:beforeDestroy', () => {
          document.removeEventListener('touchstart', touchStart)
          document.removeEventListener('touchmove', touchMove)
        })
      },
      mobTouchFun() {
        // if (this.mobileClass == '') { return false; }
        const scrollContainer = document.querySelector('.body-main');
        const preventDefault = (e) => {
          let direction = e.direction;
          try {
            let touchEl = e.changedTouches[0].target.nodeName;
            if (touchEl == 'A') {
              // console.log('点击了链接')
              return false;
            }

          } catch (error) {
            return false;
          }
          if (this.pagenum < 10) {
            return true;
          } else {
            if (direction == 'down' && this.pagenum == 10 && this.scrollEnabled) {
              return true;
            } else {
              return false;
            }
          }

        }
        const at = new AnyTouch(scrollContainer, { preventDefault });
        //  销毁
        this.$on('hook:destroyed', () => {
          at.destroy();
        });
        at.on('swipe', (e) => {
          // e包含位移/速度/方向等信息
          // console.log('swipe', e);
          let direction = e.direction;
          if (direction == 'down' && this.pagenum == 10 && this.scrollEnabled && window.scrollY === 0) {
            this.hideBlur();
          }
          if (this.scrollEnabled) return;

          if (this.pausing) { return false; }
          this.puasingFun();
          switch (direction) {
            case "down":
              console.log('触摸事件下滑')
              this.changePageFun('up')
              break;
            case "up":
              console.log('触摸事件上滑')
              this.changePageFun('down')
              break;
          }
        });
      },

      disableScroll() {
        document.body.style.overflow = 'hidden';

      },
      enableScroll() {
        document.body.style.overflow = 'auto'; // 明确指定恢复值

      },
      scrollTop() {
        // 检查滚动条是否不在顶部
        // console.log('检查滚动条是否不在顶部', window.scrollY)
        if (window.scrollY > 0) {
          // 直接瞬移到顶部
          window.scrollTo(0, 0);
        }
      },
      chunkedPreload() {
        const chunkSize = this.aniPageNum; // 每批加载20张
        const total = this.sequenceFrameCount;

        for (let start = 0; start < total; start += chunkSize) {
          this.loadChunk(start, Math.min(start + chunkSize, total))

        }
      },

      loadChunk(start, end) {
        return new Promise(resolve => {
          let loaded = 0
          for (let i = start; i < end; i++) {
            const img = new Image()
            let frameNumber = String(i).padStart(4, '0');
            let src = `${this.sequenceFramePrefix}${frameNumber}${this.sequenceFrameSuffix}`;
            img.src = src
            img.onload = () => {
              loaded++
              if (i == end - 1) {
                if (img && img.complete && img.naturalWidth) {
                  let total = this.sequenceFrameCount;
                  let numerator = Math.min(end, total);
                  let progress = (numerator / total * 100).toFixed(2);
                  console.log(progress)
                  this.updateLoadingProgress(progress);


                }
              }
              if (++loaded === end - start) resolve()
            }
            this.images.push(img);

          }
        })
      },
      // this.updateLoadingProgress(); // 调用更新 loadingProgress 的方法
      // 检查是否恢复滚动
      checkScroll() {
        if (this.sequenceFrameIndex >= (this.sequenceFrameCount - 1)) {
          this.scrollEnabled = true;
          this.enableScroll();
          console.log("所有动画完成，恢复滚动");
        }


      },
      showBlur() {
        this.scrollEnabled = true;
        this.enableScroll();
        this.pagenum = 10;
        this.showpagenum = 10;
        console.log("移动端=>所有动画完成，恢复滚动");
      },
      hideBlur() {
        gsap.to('.bg-mask', {
          "backdrop-filter": "blur(0vw)", // 动画效果：透明度变为1
          duration: 1, // 动画持续时间
          ease: 'none', // 缓动效果
        });
        this.scrollEnabled = false;
        this.disableScroll();
        this.aniPage(9)
        this.pagenum = 9;
        this.showpagenum = 9;
        this.directionStr = 'up'
        console.log("再次回到页面，关闭滚动");
      },
      changePageFun(txt) {
        this.directionStr = txt;

        switch (txt) {
          case 'down':
            if (this.mobileClass == '') {
              // pc
              if (this.pagenum == 4 && this.page4StopNum >= 0 && this.page4StopNum < 3) {
                this.page4StopNum++;
              }

              if (this.page4StopNum != 1 && this.page4StopNum != 2) {
                this.pagenum++;
                if (this.pagenum >= this.allPageCount) {
                  this.pagenum = this.allPageCount;
                }
                this.aniPage()

              }
            } else {
              // 移动端
              if (this.pagenum == 4 && this.page4StopNum >= 0 && this.page4StopNum < 2) {
                this.page4StopNum++;
              }

              if (this.page4StopNum != 1) {
                this.pagenum++;
                if (this.pagenum >= this.allPageCount) {
                  this.pagenum = this.allPageCount;
                }
                this.aniPage()
              }
            }

            break;
          case 'up':
            if (this.mobileClass == '') {
              // pc
              if (this.pagenum == 5 && this.page4StopNum == 3) {
                this.page4StopNum--;
              }
              if (this.pagenum != 4 || (this.page4StopNum != 1 && this.page4StopNum != 2)) {
                this.pagenum--;
                if (this.pagenum < 1) {
                  this.pagenum = 1;
                }
                this.aniPage()
              } else if (this.pagenum == 4 && this.page4StopNum == 1 || this.pagenum == 4 && this.page4StopNum == 2) {
                this.page4StopNum--;
              }
            } else {
              // 移动端
              if (this.pagenum == 5 && this.page4StopNum == 2) {
                this.page4StopNum--;
              }
              if (this.pagenum != 4 || this.page4StopNum != 1) {
                this.pagenum--;
                if (this.pagenum < 1) {
                  this.pagenum = 1;
                }
                this.aniPage()

              } else if (this.pagenum == 4 && this.page4StopNum == 1) {
                this.page4StopNum--;
              }
            }


            break;
          default:
            break;
        }
      },
      puasingFun() {
        this.pausing = true;
        let aninum = this.pagenum * this.aniPageNum;
        let downTiming = (aninum - 10) * this.frameTiming;
        if (this.pagenum == 4 && this.page4StopNum >= 0 && this.page4StopNum < 3) {
          downTiming = 500;
        } else if (this.pagenum == 5 || this.pagenum == 6) {
          downTiming = 500;
        }
        setTimeout(() => {
          this.pausing = false;
          // console.log('恢复滚动', this.pausing)
        }, this.directionStr == 'down' ? downTiming : 500);
      },
      setupSequenceAnimation() {
        // 监听滚轮事件
        window.addEventListener("wheel", (event) => {

          // 原有逻辑代码
          if (event.deltaY < 0 && this.pagenum == 10 && this.scrollEnabled && window.scrollY === 0) {
            this.hideBlur();
            setTimeout(() => {
              this.pausing = false;
            }, 500);
            return false;
          }


          if (this.scrollEnabled) return; // 如果滚动已启用，忽略动画逻辑

          if (this.pausing == true || (this.autoPlayInterval != null && this.pagenum < 10)) {
            // console.log('ban回')
            return false;
          }
          this.puasingFun();


          // 判断滚轮方向（向下滚动：deltaY > 0）
          if (event.deltaY > 0 && this.pagenum <= this.allPageCount) {
            this.changePageFun('down')
          } else if (event.deltaY < 0 && this.pagenum <= this.allPageCount) {
            // 向上
            this.changePageFun('up')
          }
        }, { passive: true });


      },
      changePageStyle() {
        if (this.showpagenum == 3) {
          this.bgtype = 2;
        } else if (this.showpagenum == 6) {
          if (this.mobileClass == '') {
            this.bgtype = 2;
          }

        } else {
          this.bgtype = 1;
        }
        if (this.showpagenum == 4) {
          gsap.to(".page4 .title-main", {
            duration: self.direction > 0 ? 0.5 : 1, // 动画持续时间
            "--line-width": "100%", // 目标值
            ease: 'none', // 缓动效果
          });
        } else {
          // 在这里添加进入视口时的操作
          gsap.to(".page4 .title-main", {
            duration: 0.5, // 动画持续时间
            "--line-width": "0%", // 目标值
            ease: 'none', // 缓动效果
          });
        }

      },
      aniPage(val) {
        let pagenum = this.pagenum;
        if (val) {
          if (this.pausing) { return false; }
          this.puasingFun();
          this.pagenum = val;
          if (this.directionStr == 'down') {
            setTimeout(() => {
              this.showpagenum = val;
            }, 500);
          } else {
            this.showpagenum = val;
          }
          return false;
        }
        let aninum = pagenum * this.aniPageNum;
        let frameIndex = this.sequenceFrameIndex;
        if (pagenum < this.allPageCount) {

          if (this.pagenum == 5 || this.pagenum == 6) {
            this.showpagenum = pagenum;
            this.changePageStyle();
            this.hideSequence = true;
            this.sequenceFrameIndex = aninum;
            return false;
          } else {
            this.hideSequence = false;
          }
          console.log(frameIndex, aninum)
          if (this.directionStr == 'down') {
            this.autoPlayInterval = setInterval(() => {
              if (frameIndex < aninum) {
                // console.log("frameIndex", frameIndex);
                this.updateSequenceFrame(frameIndex);
                frameIndex++;
              } else if (frameIndex == aninum) {

                this.clearAutoPlayInterval();
                this.showpagenum = pagenum;
                this.changePageStyle();
              } else if (frameIndex > aninum) {
                // console.log("frameIndex", frameIndex);
                this.updateSequenceFrame(frameIndex);
                frameIndex--;

              }
            }, this.frameTiming); // 每100毫秒切换一帧
          } else {
            this.updateSequenceFrame(aninum);
            if (this.pagenum != 5 && this.pagenum != 6) {
              this.showpagenum = pagenum;
              this.changePageStyle();
            }
          }


        } else {
          if (this.mobileClass == '') {
            this.showpagenum = this.allPageCount;
            this.checkScroll();
          } else {
            this.showBlur();
          }
        }

      },
      // 确保在类中定义clearAutoPlayInterval方法
      clearAutoPlayInterval() {
        if (this.autoPlayInterval) {
          clearInterval(this.autoPlayInterval);
          this.autoPlayInterval = null;
        }
      },
      updateSequenceFrame(progress) {
        var ctx = this.ctx;
        var index = progress;

        if (index !== this.sequenceFrameIndex && this.images[index]) {
          if (index >= (this.sequenceFrameCount - 1)) index = (this.sequenceFrameCount - 1);
          if (this.images[index] && this.images[index].complete && this.images[index].naturalWidth) {
            this.sequenceFrameIndex = index;
            ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
            ctx.drawImage(this.images[index], 0, (ctx.canvas.height * 0.03));
          } else {
            console.error("图片未加载", index);
          }
        }

      },
      updateLoadingProgress(progress) {
        // 使用 GSAP 动画将 loadingProgress 从 0 更新到 100
        gsap.to(this, {
          duration: 2, // 动画持续时间
          loadingProgress: progress, // 目标值
          onComplete: () => {
            if (progress == 100) {
              // 动画完成时执行的操作
              console.log('Loading complete!');
              this.$refs.canvas.width = this.images[0].width;
              this.$refs.canvas.height = this.images[0].height;
              setTimeout(() => {
                console.log(this.images.length)
                this.hideLoading = true;
              }, 1800);
              this.pausing = false;
              this.pagenum = 1;
              this.aniPage();
            }
          }
        });
      },
      getModelData() {
        this.$api.product
          .productInfo({ id: 36 })
          .then(res => {
            if (res && res.data) {
              this.vrViewUrl = res.data.vrViewUrl
              // this.swiperList = res.data.characteristics.find(item => item.type === 'img_and_text_list')?.list
            }
            // 轮播图
          })
          .catch(() => {
            this.$router.push('/')
          })
      },
      changeAtsIdx(idx) {
        console.log(idx)
        this.bgtype = 2;
        this.ats_current_index = idx;
      },
      handleInteriorSlideChange() {
        console.log(this.interiorSwiper.realIndex)
        this.interior_current_index = this.interiorSwiper.realIndex
      },
      handleAppearanceSlideChange() {
        console.log(this.appearanceSwiper.realIndex)
        this.appearance_current_index = this.appearanceSwiper.realIndex
      },
      handleSwiperClick(e) {
        this.showFullDialog = true;
        // console.log(e)
        let item = JSON.parse(e.target.dataset.item);
        this.full_dialog_obj = item;
        let type = e.target.dataset.type;
        this.full_dialog_type = type;
      },
      changeFullImg(e) {
        // console.log(e)
        let type = e.target.dataset.type;
        let objtype = Number(this.full_dialog_type);
        let item;
        switch (objtype) {
          case 1:

            // 内饰
            if (type == 'prev') {
              this.interiorSwiper.slidePrev();

            } else {
              this.interiorSwiper.slideNext();
            }
            this.interior_current_index = this.interiorSwiper.realIndex;
            item = this.interior_list[this.$i18n.locale][this.interior_current_index];
            this.full_dialog_obj = item;
            break;

          case 2:
            // 外观
            if (type == 'prev') {
              this.appearanceSwiper.slidePrev();
            } else {
              this.appearanceSwiper.slideNext();
            }
            this.appearance_current_index = this.appearanceSwiper.realIndex;
            item = this.appearance_list[this.$i18n.locale][this.appearance_current_index];
            this.full_dialog_obj = item;
            break;
        }
      },
      handleCloseFullDialog() {
        this.showFullDialog = false;
      },
      setupScrollTrigger() {
        gsap.to('.appearance-page', {
          scrollTrigger: {
            trigger: '.appearance-page', // 触发动画的元素
            start: '-30% center', // 动画开始的位置
            end: '130% center', // 动画结束的位置
            scrub: true, // 平滑滚动效果
            toggleActions: "play reset restart reset", // 重新开始动画
            onToggle: (self) => {
              // console.log("toggled to active:", self.isActive, self.direction)
              if (self.isActive) {
                this.showAppearance = true
                // this.sequenceFrameIndex = 180;
              } else {
                this.showAppearance = false

              }
            },
          },
        });
        gsap.to('.interior-page', {
          scrollTrigger: {
            trigger: '.interior-page', // 触发动画的元素
            start: 'top center', // 动画开始的位置
            end: '130% center', // 动画结束的位置
            scrub: true, // 平滑滚动效果
            toggleActions: "play reset restart reset", // 重新开始动画
            onToggle: (self) => {
              // console.log("toggled to active:", self.isActive, self.direction)
              if (self.isActive) {
                this.showInterior = true
                if (this.mobileClass != '') {
                  this.checkScroll();

                }
              } else {
                this.showInterior = false
              }
            },
          },
        });
      },

    },
  };
</script>

<style scoped>
  .index_body {
    overflow-y: hidden;
    /*为了兼容普通PC的浏览器*/
    height: 100%;
    position: fixed;
    touch-action: none;
  }

  canvas {
    will-change: transform;
    image-rendering: crisp-edges;
    /* 保持清晰度 */
  }

  .body-main {
    padding: 0;
    margin: 0;
    width: 100vw;
    height: 410vh;
    /* overflow: hidden; */
    position: relative;
    --mainColor: #F1AB16;
    --pc-smaller-font-size: 0.92vw;
    --pc-small-font-size: 1vw;
    --pc-normal-font-size: 1.2vw;
    --pc-big-font-size: 1.43vw;
    --pc-bigger-font-size: 2vw;
    --mob-smaller-font-size: 2.23vw;
    --mob-small-font-size: 2.89vw;
    --mob-normal-font-size: 4vw;
    --mob-big-font-size: 5vw;
    --mob-bigger-font-size: 7vw;
  }

  .body-main.mobile {
    /* height: 600vh; */
    height: calc(var(--vh, 1vh) * 400);
    /* Safari备用方案 */
    min-height: -webkit-fill-available;
    box-sizing: border-box;
  }

  .show {
    opacity: 1 !important;
    transition: opacity 1s;
    pointer-events: all;
  }

  .hide {
    opacity: 0 !important;
    transition: opacity 0.3s;
    pointer-events: none;
  }

  .fix-btn {
    width: 2vw;
    height: auto;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    z-index: 100;
    cursor: pointer;
    padding: 0.6vw;
    box-sizing: content-box;
  }

  .fix-btn.prev {
    top: 66px;
    animation: p1-prev-move 3s linear infinite;
  }

  .fix-btn.next {
    bottom: 1vw;
    animation: p1-next-move 3s linear infinite;
  }

  .page3 .fix-btn.next {
    bottom: 11%;
  }

  .body-main .bg {
    width: 100vw;
    height: 100vh;
    object-fit: cover;
    position: fixed;
    top: 0;
    left: 0;
    isolation: isolate;
    z-index: -1;
    background-color: rgb(0, 0, 0);
  }

  .body-main .bg-mask {
    width: 100vw;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1;
    pointer-events: none;
  }

  .sequence-container {
    width: 100vw;
    /* 默认使用CSS变量，由JavaScript动态设置 */
    height: calc(var(--vh, 1vh) * 100);

    /* Safari备用方案 */
    height: -webkit-fill-available;
    min-height: -webkit-fill-available;
    box-sizing: border-box;
    /* position: relative; */
    position: fixed;
    top: 0;
    left: 0;
    z-index: 2;
    transform: translateY(0%);
    /* overflow: hidden; */
  }

  .sequence-frame {
    width: 100%;
    height: 100%;
    object-fit: cover;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 2;
    transform: translateZ(0);
    backface-visibility: hidden;
  }

  .mobile .sequence-container {
    width: 100vw;
    height: calc(var(--vh, 1vh) * 100);
    box-sizing: border-box;
    top: calc(var(--vh, 1vh) * 48);
    transform: translateY(-50%);
  }

  .mobile .sequence-frame {
    width: 100%;
    height: auto;
  }

  .loading-box {
    --loadingAllWidth: 41.66vw;
    width: var(--loadingAllWidth);
    height: auto;
    min-height: 23vw;
    position: fixed;
    top: 50%;
    left: 50%;
    opacity: 1;
    transform: translate(-50%, -50%);
    transition: opacity 0.8s;
    transition-delay: 0.6s;
  }

  .loading-box img {
    width: 100%;
    height: auto;
    display: block;
    position: relative;
    z-index: 1;
  }

  .loading-box .inside-img {
    width: var(--imgwidth);
    height: 17vw;
    position: absolute;
    top: 0;
    left: 0;
    transition: width 0.2s;
    overflow: hidden;
  }

  .loading-box .inside-img img {
    width: var(--loadingAllWidth);
    height: 17vw;
    max-width: var(--loadingAllWidth);
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1;
  }

  .loading-box .txt-img {
    margin-top: 0vw;
    position: absolute;
    left: 0;
    top: 76%;
    transform: translateY(-50%);
  }

  .mobile .loading-box {
    --loadingAllWidth: 83vw;
  }

  .mobile .loading-box .inside-img,
  .mobile .loading-box .inside-img img {
    height: 34vw;
  }

  .mobile .loading-box .txt-img {
    top: 100%;
  }

  /* 第一屏 */
  .page-container {
    width: 100vw;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 2;
    opacity: 0;
    box-sizing: border-box;
  }

  .mobile .page-container {
    /* 默认使用CSS变量，由JavaScript动态设置 */
    height: calc(var(--vh, 1vh) * 100);

    /* Safari备用方案 */
    height: -webkit-fill-available;
    min-height: -webkit-fill-available;
  }

  .up:not(.mobile) * {
    transition-duration: 0s !important;
    transition-delay: 0s !important;
  }

  .page1 {
    opacity: 1;
  }

  .page5.show,
  .page6.show {
    transition: opacity 1s;
  }

  @keyframes show {
    0% {
      opacity: 0;
    }

    100% {
      opacity: 1;
    }
  }

  .page1-title-img {
    width: 43.67vw;
    height: auto;
    position: fixed;
    top: 9.7vw;
    right: 9.01vw;
    z-index: 2;
    animation: show 0.5s ease-in-out;
    animation-delay: 1.2s;
    animation-fill-mode: backwards;
    mix-blend-mode: overlay;
  }

  .mobile .page1-title-img {
    width: 86vw;
    top: 34%;
    right: auto;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .page1 {
    z-index: 2;
    animation: show 0.5s ease-in-out;
    animation-delay: 1.2s;
    animation-fill-mode: backwards;
  }

  .page1-btn-group {
    width: auto;
    height: auto;
    display: flex;
    align-items: center;
    position: absolute;
    top: 22vw;
    right: 9.15vw;
  }

  .page1-btn-group.revers {
    flex-direction: row-reverse;
  }

  .page1-btn-group .btn {
    min-width: 11.59vw;
    width: auto;
    height: 2.26vw;
    border-radius: 1.13vw;
    margin: 0 0.66vw;
    cursor: pointer;
    text-align: center;
    font-size: var(--pc-small-font-size);
    font-weight: normal;
    font-stretch: normal;
    letter-spacing: 0.1vw;
    display: inline-block;
    position: relative;
    z-index: 100;
    transition: background 0.3s, color 0.3s;
  }

  .mobile .page1-btn-group {
    top: calc(var(--vh, 1vh) * 86);
    right: auto;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .mobile .page1-btn-group .btn {
    min-width: 33.87vw;
    width: auto;
    height: 6.67vw;
    border-radius: 3.13vw;
    font-size: var(--mob-small-font-size);
    margin: 0 2.26vw;
    line-height: 1.5;
  }

  .mobile.es .page1-btn-group .btn.btn1 {
    width: 41vw;
  }

  .page1-btn-group .btn.btn1 {
    background: transparent;
    border: 0.1vw solid #fff;
    color: #fff;
  }

  .page1-btn-group .btn.btn2 {
    background: #fff;
    border: 0.1vw solid #fff;
    color: #606161;
  }

  .page1-btn-group .btn:hover {
    background: var(--mainColor);
    border: 0.1vw solid var(--mainColor);
    color: #fff;
  }

  .page1 .to-next-icon {
    position: absolute;
    bottom: 2.16vw;
    left: 50%;
    transform: translateX(-50%);
    --aniSpeed: 2s;
    animation: p1-next-move 2s linear infinite;
    cursor: pointer;
    /* border: 1vw solid red; */
  }

  @keyframes p1-next-move {
    0% {
      transform: translateX(-50%) translateY(20%);
    }

    50% {
      transform: translateX(-50%) translateY(0%);

    }

    100% {
      transform: translateX(-50%) translateY(20%);
    }
  }

  @keyframes p1-prev-move {
    0% {
      transform: translateX(-50%) translateY(0%);
    }

    50% {
      transform: translateX(-50%) translateY(20%);

    }

    100% {
      transform: translateX(-50%) translateY(0%);
    }
  }

  .page1 .to-next-icon .icon {
    width: 0.86vw;
    height: auto;
    display: block;
    margin-bottom: 0.2vw;
    transition: opacity 0.3s;
    pointer-events: none;
  }

  .change {
    width: 10vw;
    height: 10vw;
    object-fit: cover;
    /* background-color: var(--mainColor); */
    z-index: 10;
    position: fixed;
    bottom: 10%;
    right: 1%;
  }

  .mobile .page1 .to-next-icon {
    bottom: 6.16vw;
    pointer-events: none;
  }

  .mobile .page1 .to-next-icon .icon {
    width: 2.86vw;
    margin-bottom: 1vw;
  }

  .page1 .to-next-icon .icon.i1 {
    opacity: 1;
    animation: i1ani var(--aniSpeed) linear infinite;
  }

  @keyframes i1ani {
    0% {
      opacity: 1;
    }

    50% {
      opacity: 0.41;
    }

    100% {
      opacity: 0.21;
    }
  }

  .page1 .to-next-icon .icon.i2 {
    opacity: 0.41;
    animation: i2ani var(--aniSpeed) linear infinite;
  }

  @keyframes i2ani {
    0% {
      opacity: 0.41;
    }

    50% {
      opacity: 0.21;
    }

    100% {
      opacity: 1;
    }
  }

  .page1 .to-next-icon .icon.i3 {
    opacity: 0.21;
    animation: i3ani var(--aniSpeed) linear infinite;
  }

  @keyframes i3ani {
    0% {
      opacity: 0.21;
    }

    50% {
      opacity: 1;
    }

    100% {
      opacity: 0.41;
    }
  }

  .page4 .title-main {
    width: 49.99vw;
    height: auto;
    min-height: 5vw;
    text-transform: capitalize;
    position: absolute;
    top: 6.8vw;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    align-items: center;
    justify-content: center;
    --line-width: 0%;
  }

  .page4 .title-main .line-img {
    width: var(--line-width);
    height: auto;
    display: block;
    transition: width 0.2s;
    position: relative;
    top: 5%;
  }

  .page4 .title-main .title-img {
    width: 100%;
    text-align: center;
    font-size: var(--pc-bigger-font-size);
    font-weight: 600;
    font-stretch: normal;
    line-height: 1.3;
    letter-spacing: 0vw;
    background: linear-gradient(to bottom, #ffffff, #7A797A);
    /* 从白色渐变到红色 */
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    position: absolute;
    top: 0%;
    left: 0%;
    transform: translat(-50%, -50%);
  }

  .mobile .page4 .title-main {
    width: 112%;
    top: 22.27vw;
    min-height: 10vw;
    align-items: flex-end;
  }

  .mobile .page4 .title-main .title-img {
    line-height: 1.8;
    font-size: var(--mob-big-font-size);
  }

  .mobile.es .page4 .title-main .title-img {
    transform: scale(0.9);
  }

  .page4 img:not(.fix-btn) {
    pointer-events: none;
  }

  .page4 .argument-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    position: absolute;
    bottom: 0;
    left: 0;
  }

  .page4 .argument-img .img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transform: translateY(4.8%);
  }

  .page4 .argument-img .img-750mm {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transform: translateY(3%);
    position: absolute;
    bottom: 0;
    left: 0;
  }

  .page4 .argument-img .img-220mm {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transform: translateY(3.8%) translateX(-0.5%);
    position: absolute;
    bottom: 0;
    left: 0;
  }

  .page4 .argument-img .img.show {
    /* transition-delay: 1.5s; */
  }

  .page4 .argument-img .img-220mm.show {
    transition-delay: 0.3s;
  }

  .page4 .argument-img .txt {
    color: #ffffff;
    font-size: var(--pc-smaller-font-size);
    transform: scale(0.8);
    text-transform: uppercase;
    position: absolute;
    bottom: 22.2vh;
    left: 4%;
  }

  .mobile .page4 .argument-img {
    width: 100vw;
    height: calc(var(--vh, 1vh) * 100);
    position: absolute;
    bottom: calc(var(--vh, 1vh) * 2);
    left: 0;

  }

  .mobile .page4 .angle-img {
    width: 98vw;
    height: auto;
    position: absolute;
    top: 32rem;
    left: 11.3rem;
    transform: translate(-50%, -50%);
  }

  .mobile .page4 .argument-img .small {
    font-size: var(--mob-small-font-size);
    font-weight: 600;
  }

  .mobile .page4 .argument-img .p1 {
    --height: 10rem;
    position: absolute;
    top: 29rem;
    left: 4rem;
    transform: translate(-50%, -50%);
  }

  .mobile .page4 .argument-img .p2 {
    position: absolute;
    top: 33rem;
    left: 1.43rem;
  }

  .mobile .page4 .argument-img .p3 {
    position: absolute;
    top: 27rem;
    left: 10.6rem;
    transform: translate(-50%, -50%);
    --height: 14rem;
  }

  .mobile .page4 .argument-img .p4 {
    position: absolute;
    top: 34.2rem;
    left: 9rem;
    --height: 3.8rem;
  }

  .mobile .page4 .argument-img .p5 {
    position: absolute;
    top: 26.6rem;
    left: 24.3rem;
    --height: 15rem;
  }

  .mobile .page4 .argument-img .p2.show,
  .mobile .page4 .argument-img .p4.show {
    transition-delay: 2.5s;
  }

  .page4 .argument-table {
    width: auto;
    height: 4.6vw;
    min-width: 70vw;
    position: absolute;
    bottom: 2.6vw;
    left: 50%;
    transform: translateX(-50%);
    background-color: rgba(134, 134, 134, 0.2);
    padding: 0.6vw 5.9vw;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }

  .page4 .argument-table .line {
    width: 0.14vw;
    height: 3.35vw;
    background-color: #ea5c04;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
  }

  .page4 .argument-table .line.l1 {
    left: 0;
  }

  .page4 .argument-table .line.l2 {
    right: -1px;
  }

  .page4 .argument-table .table-item {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .page4 .argument-table .table-item .name {
    font-size: var(--pc-small-font-size);
    color: #ffffff;
    text-transform: uppercase;
    transform: scale(0.9) translateY(20%);
  }

  .page4 .argument-table .table-item .num {
    font-size: var(--pc-bigger-font-size);
    transform: scale(0.8);
    color: #ea5c04;
    font-weight: 600;
  }

  .mobile .page4 .argument-table-main {
    position: absolute;
    bottom: 58%;
    left: 50%;
    transform: translateX(-50%);
  }

  .mobile .page4 .argument-table-main .argument-table .table-item .name {
    font-size: var(--mob-small-font-size);
    color: #ffffff;
    text-transform: uppercase;
    transform: scale(1.0) translateY(20%);
  }

  .mobile .page4 .argument-table-main .argument-table .table-item .num {
    font-size: var(--mob-normal-font-size);
    transform: scale(1.0);
    color: #ea5c04;
    font-weight: 600;
  }

  .mobile .page4 .argument-table-main .argument-table {
    position: relative;
    bottom: auto;
    left: auto;
    transform: none;
    height: 11.6vw;
    padding: 2.4vw 5.7vw;
  }

  .mobile .page4 .argument-table-main .argument-table:first-child {
    width: 56vw;
    min-width: 56vw;
    margin: 0 auto 4vw;
  }

  .mobile .page4 .argument-table-main .argument-table .line {
    width: 0.4vw;
    height: 6.8vw;
  }

  .blur-page {
    position: relative;
    top: 0vh;
    backdrop-filter: blur(0.15vw);
  }

  .mobile .blur-page {
    top: 0vh;
    backdrop-filter: blur(0.8vw);
  }

  /* 轮播页 */

  .carousel-title {
    font-size: var(--pc-bigger-font-size);
    font-weight: 600;
    font-stretch: normal;
    line-height: 1.3;
    letter-spacing: 0vw;
    background: linear-gradient(to bottom, #ffffff, #7A797A);
    /* 从白色渐变到红色 */
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    position: absolute;
    top: 17%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .mobile .carousel-title {
    width: 100%;
    text-align: center;
    font-size: var(--mob-bigger-font-size);
    top: calc(var(--vh, 1vh) * 8);
    transform: translate(-50%, -50%);
  }

  .carousel-main {
    width: 100%;
    display: flex;
    align-items: flex-start;
    position: relative;
    top: 60%;
    transform: translateY(-50%);
  }

  .mobile .carousel-main {
    top: calc(var(--vh, 1vh) * 50);
    padding-top: 3rem;
    transform: translateY(-50%);
  }

  .blur-page ::v-deep .carousel-item {
    width: 46.45vw;
    height: auto;
    margin: 0 1vw;
    flex-shrink: 0;
  }

  .mobile .blur-page ::v-deep .carousel-item {
    width: 100%;
    height: auto;
    margin: 0 0vw;
    flex-shrink: 0;
  }

  .mobile .blur-page .meitu-swiper {
    overflow: visible;
  }

  .mobile .blur-page .meitu-swiper ::v-deep .carousel-item {
    width: 100%;
    height: 100.93vw;
    margin: 0 0vw;
    flex-shrink: 0;
  }

  .mobile .blur-page .meitu-swiper ::v-deep .swiper-scrollbar-drag {
    opacity: 1;
    width: 13.73vw;
    height: 0.8vw;
    background-color: #ffffff;
  }

  .mobile .blur-page .meitu-swiper .swiper-scrollbar {
    bottom: -16%;
  }

  .mobile .blur-page .meitu-swiper .carousel-item .mob-line {
    display: none;
  }

  .blur-page .page-container {
    opacity: 1;
    position: relative;
    top: auto;
    left: auto;
  }

  .mobile .blur-page .page-container {
    height: auto;
    min-height: calc(var(--vh, 1vh) * 100);
    box-sizing: border-box;
  }

  .mobile .blur-page .page-container.appearance-page {
    height: auto;
    min-height: calc(var(--vh, 1vh) * 80);
  }

  .carousel-item.small {
    width: 21.85vw;
  }

  .carousel-item.small:last-child {
    width: 19.85vw;
  }

  .carousel-item-img {
    width: 46.45vw;
    height: 24.11vw;
    object-fit: cover;
    border-radius: 0.52vw;
    cursor: pointer;
    position: relative;
    z-index: 10;
  }

  .mobile .carousel-item-img {
    width: 80.53vw;
    height: 100.93vw;
    object-fit: cover;
    border-radius: 2.27vw;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
  }

  .mobile.ar .carousel-item-img {
    left: 41%;
    margin: 0 9.7vw;
  }

  .mobile.ar .meitu-swiper .carousel-item-img {
    left: 65%;
  }

  .carousel-item-text {
    width: 40vw;
    margin: 0 auto;
    text-align: center;
    opacity: 0;
    transition: opacity 1s;
  }

  .mobile .carousel-item-text {
    width: 95vw;
    margin: 0vw auto 4vw;
    text-align: center;
    opacity: 0;
    transition: opacity 1s;
  }

  .blur-page ::v-deep .swiper-slide-active .carousel-item-text {
    opacity: 1;
  }

  .carousel-item-text-title {
    text-transform: capitalize;
    font-size: var(--pc-big-font-size);
    color: #ffffff;
    margin: 2.6vw auto 0.6vw;
  }

  .mobile .carousel-item-text-title {
    text-transform: capitalize;
    font-size: var(--mob-big-font-size);
    color: #ffffff;
    margin: 0vw auto 8.6vw;
  }

  .mobile.ar .carousel-item-text-title {
    line-height: 2.8;
  }

  .carousel-item-text-content {
    font-size: var(--pc-smaller-font-size);
    color: #ffffff;
  }

  .mobile .carousel-item-text-content {
    font-size: var(--mob-small-font-size);
    color: #ffffff;
  }

  /* 配置表 */
  .page-container ::v-deep .params-table__title {
    display: none;
  }

  .page-container ::v-deep .params-div .params-div__block .params-div__line {
    min-height: 51px;
    height: auto;
  }

  .page-container ::v-deep .params-table,
  .page-container ::v-deep .params-div {
    width: 95%;
    margin: 0 0 0;
    background-color: #ffffff;
  }

  .page-container ::v-deep .params-table__remark,
  .page-container ::v-deep .params-div__remark {
    margin-left: 0;
    margin-right: 0;
    color: #ffffff;
  }

  .config-table-container {
    width: 76.39vw;
    height: 30vw;
    overflow-y: scroll;
    position: absolute;
    top: 58%;
    left: 50%;
    transform: translate(-50%, -50%);

  }

  .mobile .config-table-container {
    width: 80.39vw;
    height: 70vh;
    top: calc(var(--vh, 1vh) * 70);
  }

  .carousel-title.config-table {
    top: 18%;
  }

  .config-table-container.ar {
    left: 50%;
  }

  .mobile .config-table-container.ar {
    left: 50%;
  }

  .config-table-container::-webkit-scrollbar {
    width: 0.6vw;
  }

  .config-table-container::-webkit-scrollbar-track {
    background: transparent;
    /* 隐藏默认轨道背景 */
    /* 用左边框模拟一条细线 */
    border: 1px solid #ddd;
    border-radius: 0.4vw;
    /* 调整细线位置 */
  }

  .config-table-container::-webkit-scrollbar-thumb {
    border-radius: 0.2vw;
    background-color: #ea5c04;
  }

  .config-table-container::-webkit-scrollbar-track-piece {
    background: transparent;
  }

  /* 预约试驾 */
  .test-drive-container {
    width: 76.39vw;
    height: auto;
    padding-bottom: 5vw;
    /* position: relative; */
    position: absolute;
    top: 66%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .mobile .test-drive-container {
    top: calc(var(--vh, 1vh) * 70);
  }

  .test-drive-container.ar {
    left: 50%;
  }

  .page-container ::v-deep .drive_title,
  .page-container ::v-deep .title_content {
    display: none;
  }

  .page-container ::v-deep .drive_from {
    width: 100%;
    padding: 3% 5% 0%;
    border-radius: 1vw;
    background-color: #ffffff;
  }

  .page-container ::v-deep .from_btn {
    background-color: rgba(255, 255, 255, 0.2);
    backdrop-filter: blur(1vw);
    padding: 0.8vw 6vw;
    letter-spacing: 0.2vw;
    border-radius: 0.2vw;
    font-size: var(--pc-small-font-size);
    transition: all 0.3s;
  }

  .mobile .page-container ::v-deep .from_btn {
    background-color: rgba(255, 255, 255, 0.2);
    backdrop-filter: blur(1vw);
    padding: 1.8vw 6vw;
    letter-spacing: 0.2vw;
    border-radius: 0.2vw;
    font-size: var(--mob-small-font-size);
    transition: all 0.3s;
    margin-top: 5vw;
  }

  .page-container ::v-deep .from_btn:hover {
    background-color: #ffffff;
    color: #1a1a1a;
  }



  .page2 .bright-spot {
    position: absolute;
    left: 50%;
    top: 35%;
    transform: translate(-50%, -50%);

  }

  .mobile .page2 .bright-spot {
    left: 20rem;
    top: 26.8rem;
  }

  .mobile .page3 .bright-spot {
    --height: 5.3rem;
    position: absolute;
    left: 17rem;
    top: 26.5rem;
    transform: translate(-50%, -50%);
  }

  .page5 .bright-spot {
    position: absolute;
    left: 48%;
    top: 38%;
    transform: translate(-50%, -50%);
    z-index: 3;
  }

  .mobile .page5 .bright-spot {
    --height: 8rem;
    left: 15rem;
    top: 27.8rem;
  }

  .page5 .chejia {
    width: 85vw;
    height: auto;
    position: absolute;
    left: 0%;
    top: 0%;
    z-index: 2;
    transform: translateX(-15%) translateY(5%);
  }

  .mobile .page5 .chejia {
    width: 100vw;
    height: auto;
    position: absolute;
    left: 11rem;
    top: 31.8rem;
    z-index: 2;
    transform: translateY(-50%) translateX(-50%) scale(1.5);
  }

  .page6 .bright-spot {
    position: absolute;
    left: 47%;
    top: 32%;
    transform: translate(-50%, -50%);
    z-index: 3;
  }

  .mobile .page6 .bright-spot {
    --height: 6rem;
    left: 16rem;
    top: 29.5rem;
  }

  .mobile .page6 .dialog-main {
    top: 15.6rem;
  }

  .page6 .dipan {
    width: 70vw;
    height: auto;
    position: absolute;
    left: 0%;
    top: 0%;
    z-index: 2;
    transform: translateX(-9%) translateY(15%);
  }

  .mobile .page6 .dipan {
    width: 100vw;
    height: auto;
    position: absolute;
    left: 45%;
    top: 35rem;
    z-index: 2;
    transform: translateY(-50%) translateX(-50%) scale(1.3);
  }

  .page7 .bright-spot {
    position: absolute;
    left: 50%;
    top: 35%;
    transform: translate(-50%, -50%);

  }

  .mobile .page7 .bright-spot {
    --height: 10rem;
    left: 20rem;
    top: 29.5rem;
  }

  .mobile .page7 .dialog-main {
    top: 16.5rem;
  }

  .page8 .bright-spot {
    position: absolute;
    left: 52%;
    top: 35%;
    transform: translate(-50%, -50%);

  }

  .mobile .page8 .bright-spot {
    --height: 9.5rem;
    left: 19rem;
    top: 27.5rem;
  }

  .page9 .bright-spot {
    position: absolute;
    left: 50%;
    top: 30%;
    transform: translate(-50%, -50%);

  }

  .mobile .page9 .bright-spot {
    left: 21.6rem;
    top: 26rem;

  }

  .page3 {
    height: 110vh;
    z-index: 4;
  }

  .rockery,
  .stone_vista {
    width: 100%;
    height: calc(var(--vh, 1vh) * 100);
    object-fit: cover;
    transform: translateY(-3%);
    position: absolute;
    top: 0;
    left: 0;
    pointer-events: none;
    z-index: 1;
    opacity: 0;
  }

  .stone_vista {
    transform: translateY(3%);
  }

  .stone_vista.hide {
    transition: opacity 0s, transform 0s;
    transition-delay: 0s;
  }

  .stone_vista.show {
    transition: opacity 0.5s;
  }

  .rockery.hide {
    /* opacity: 0;
        transform: translateY(5%);
        clip-path: inset(0px 100% 0px 0px);
        transition: opacity 0s, clip-path 0s; */
    transition-delay: 0s;
  }

  .rockery.show {
    /* opacity: 1;
        clip-path: inset(0px 0 0px 0px);
        transform: translateY(3%);
        transition: opacity 0.4s, transform 0.4s, clip-path 6s linear; */
    transition: opacity 1.2s;
    transition-delay: 0.5s;
  }

  .gravel1 {
    width: 6.48vw;
    height: auto;
    position: fixed;
    bottom: 15.09%;
    left: 0.5%;
    z-index: 3;
    pointer-events: none;
    opacity: 0;
  }

  .snow1.gravel1 {
    width: 10.48vw;
    left: 0%;
    z-index: 3;
  }

  .shamo1.gravel1 {
    width: 10.48vw;
    left: 0%;
    bottom: 0%;
    z-index: 3;
  }

  .gravel2 {
    width: 16.22vw;
    height: auto;
    position: fixed;
    bottom: -1%;
    left: 40.91%;
    z-index: 3;
    pointer-events: none;
    opacity: 0;
  }

  .snow2.gravel2 {
    width: 30.21vw;
    /* left: 0%; */
    bottom: 0;
    z-index: 3;
  }

  .shamo2.gravel2 {
    width: 7vw;
    /* left: 0%; */
    left: 52%;
    bottom: 22%;
    z-index: 3;
  }


  .gravel3 {
    width: 5.63vw;
    height: auto;
    position: fixed;
    bottom: 24.4%;
    right: 0.6%;
    pointer-events: none;
    opacity: 0;
    z-index: 3;
  }

  .mobile .rockery,
  .mobile .stone_vista {
    width: 100%;
    height: auto;
    top: calc(var(--vh, 1vh) * 46);
    left: 0%;
    transform: translateY(-50%);
  }

  .mobile .rockery {
    transform: translateY(-45.5%) translateX(40%) scale(1.8);
  }

  .mobile .gravel1 {
    width: 8.48vw;
    height: auto;
    position: fixed;
    bottom: calc(var(--vh, 1vh) * 20.09);
    left: 0.5%;
  }

  .mobile .shamo1.gravel1 {
    width: 12.48vw;
    left: 0%;
    bottom: calc(var(--vh, 1vh) * 15.09);
  }
  .mobile .snow1.gravel1 {
    width: 12.48vw;
    left: 0%;
  }
  .mobile .gravel2 {
    width: 18vw;
    height: auto;
    position: fixed;
    bottom: calc(var(--vh, 1vh) * 15);
    left: auto;
    right: 6.6%;
  }

  .mobile .snow2.gravel2 {
    width: 22.22vw;
    bottom: 0;
  }

  .mobile .gravel3 {
    width: 7.63vw;
    height: auto;
    position: fixed;
    bottom: calc(var(--vh, 1vh) * 30.4);
    right: 4.6%;
  }

  .gravel1.show,
  .gravel2.show,
  .gravel3.show {
    transition-delay: 1.2s;
  }

  .tap-group {
    width: 27.58vw;
    height: auto;
    position: absolute;
    left: 50%;
    top: 75%;
    transform: translateX(-50%);
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .tap-item {
    min-width: 2.76vw;
    height: auto;
    position: relative;
    cursor: pointer;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .tap-item .img {
    width: 2.76vw;
    height: auto;
    transition: opacity 0.5s;
  }

  .tap-item .txt {
    font-size: var(--pc-small-font-size);
    font-weight: normal;
    font-stretch: normal;
    line-height: 1.2;
    letter-spacing: 0.04vw;
    color: #ffffff;
    opacity: 0.6;
    margin-top: 0.5vw;
    text-shadow: 0 0 0.2vw rgba(0, 0, 0, 1);
    text-transform: capitalize;
  }

  .tap-item.choose .txt {
    color: var(--mainColor);
    opacity: 1;
  }

  .tap-item .img.unimg {
    opacity: 0.6;
  }

  .tap-item .img.chimg {
    position: absolute;
    top: 0;
    left: 50%;
    transform: translateX(-50%);
    opacity: 0;
  }

  .tap-item.choose .img.unimg {
    opacity: 0;
  }

  .tap-item.choose .img.chimg {
    opacity: 1;
  }

  .mobile .tap-group {
    width: 100vw;
    top: calc(var(--vh, 1vh) * 90);
    transform: translate(-50%, -50%);
    justify-content: center;
  }

  .mobile .tap-item {
    margin: 0 2.5vw;
  }

  .mobile.es .tap-item,
  .mobile.ar .tap-item {
    margin: 0 1.5vw;
  }

  .mobile .tap-item .img {
    width: 6.76vw;
    height: auto;
    transition: opacity 0.5s;
  }

  .mobile .tap-item .txt {
    font-size: var(--mob-normal-font-size);
    margin-top: 1.5vw;
  }

  .page-container .dialog-main.show {
    clip-path: inset(0px 0px 0px 0px);
    transition: opacity 0.6s, clip-path 1s;
  }

  .page-container .dialog-main.hide {
    clip-path: inset(0px 0px 100% 0px);
    transition: opacity 0.3s, clip-path 1s;
  }

  .mobile .page-container .dialog-main.hide {
    clip-path: inset(100% 0px 0px 0px);
    transition: opacity 0.3s, clip-path 1s;
  }

  .dialog-main {
    background-color: hsla(0, 0%, 5%, .4);
    min-height: 26.88vw;
    position: absolute;
    transition: all .3s;
    width: 31.6vw;
    z-index: 21;
  }

  .dialog-main .img {
    width: 100%;
    height: auto;
  }

  .dialog-main .text-view {
    width: 100%;
    height: auto;
    padding: 1.5vw 2vw;
    box-sizing: border-box;
    color: #fff;
    text-align: center;
    letter-spacing: 0.1vw;
  }

  .es .dialog-main .text-view {
    padding: 1.5vw 0.3vw;
  }

  .dialog-main .text-view .title {
    /* text-transform: uppercase; */
    text-transform: capitalize;
    font-size: var(--pc-normal-font-size);
    line-height: 1.2;
  }

  .dialog-main .text-view .memo {
    font-size: var(--pc-smaller-font-size);
    line-height: 1.5;
    /* text-align-last: justify; */
    text-align: center;
    margin-top: 1vw;
  }

  .mobile .dialog-main .text-view {
    padding: 4vw 2vw;
  }

  .mobile .dialog-main .text-view .title {
    font-size: var(--mob-normal-font-size);
  }

  .mobile .dialog-main .text-view .memo {
    font-size: var(--mob-small-font-size);
    margin-top: 2vw;
  }

  .page2 .dialog-main {
    left: auto;
    right: 10.35vw;
    top: 49%;
    transform: translateY(-50%);
    bottom: auto;
  }



  .page3 .dialog-main {
    left: auto;
    right: 9vw;
    top: 40%;
    transform: translateY(-50%);
    bottom: auto;
  }

  .page5 .dialog-main {
    left: auto;
    right: 10.35vw;
    top: 53%;
    transform: translateY(-50%);
    bottom: auto;
  }

  .page5 .dialog-main.show {
    transition-delay: 2s;
  }

  .page6 .dialog-main {
    left: auto;
    right: 10.35vw;
    top: 51%;
    transform: translateY(-50%);
    bottom: auto;
  }

  .page6 .dialog-main.show {
    transition-delay: 2s;
  }

  .page7 .dialog-main {
    left: auto;
    right: 8.8vw;
    top: 50%;
    transform: translateY(-50%);
    bottom: auto;
  }

  .page7 .dialog-main.show {
    transition-delay: 2s;
  }

  .page8 .dialog-main {
    left: auto;
    right: 7.35vw;
    top: 50%;
    transform: translateY(-50%);
    bottom: auto;
  }

  .page8 .dialog-main.show {
    transition-delay: 2s;
  }

  .page9 .dialog-main {
    left: auto;
    right: 10.35vw;
    top: 45%;
    transform: translateY(-50%);
    bottom: auto;
  }

  .page9 .dialog-main.show {
    transition-delay: 2s;
  }

  .mobile .dialog-main {
    width: 80.6vw;
    min-height: 46.88vw;
    left: auto;
    right: 10%;
    top: 13rem;
  }

  .page-container .dialog-main.show {
    transition-delay: 2s;
  }

  .page-container.page2 .dialog-main.show {
    transition-delay: 0.3s;
  }

  .mobile .page-container .dialog-main.show {
    transition-delay: 1.2s;
    pointer-events: none;
  }

  .mobile .page-container.page2 .dialog-main.show {
    transition-delay: 0.6s;
  }

  .body-main ::v-deep .swiper-button-next,
  .body-main ::v-deep .swiper-button-prev {
    width: 50px;
    height: 50px;
    background-size: 150%;
    border-radius: 100%;
    top: 38%;
    margin-top: -25px;
    background-color: transparent;
    background-image: none;
  }

  .mobile.body-main ::v-deep .swiper-button-next,
  .mobile.body-main ::v-deep .swiper-button-prev {
    display: none;
  }

  .body-main ::v-deep .swiper-button-next {
    right: 5%;
  }

  .body-main ::v-deep .swiper-button-prev {
    left: 5%;
  }

  .full-dialog ::v-deep .swiper-button-next,
  .full-dialog ::v-deep .swiper-button-prev {
    top: 48%;
  }

  .mobile .full-dialog ::v-deep .swiper-button-next,
  .mobile .full-dialog ::v-deep .swiper-button-prev {
    top: 52%;
    z-index: 2;
    display: block;
  }

  .full-dialog ::v-deep .swiper-button-next {
    right: 1%;
  }

  .full-dialog ::v-deep .swiper-button-prev {
    left: 1%;
  }

  .page-container ::v-deep .swiper-pagination-bullet-custom {
    width: 30px;
    height: 2px;
    border-radius: 0;
    background-color: #fff;
    opacity: 0.3;
    transition: 0.3s;

  }

  .mobile .blur-page .carousel-item .mob-line {
    width: 13.73vw;
    height: 0.8vw;
    background-color: #ffffff;
    position: absolute;
    bottom: 15.4%;
    left: 49%;
    transform: translate(-50%, -50%);
  }

  .appearance-page ::v-deep .swiper-pagination-bullets {
    bottom: 23%;
  }

  .interior-page ::v-deep .swiper-pagination-bullets {
    bottom: 20%;
  }

  .ar .appearance-page ::v-deep .swiper-pagination-bullets {
    bottom: 11%;
  }

  .ar .interior-page ::v-deep .swiper-pagination-bullets {
    bottom: 14%;
  }

  .ar .interior-page .carousel-item-text-title {
    margin: 1vw auto 1.6vw;
  }

  .ar .appearance-page .carousel-item-text-title {
    margin: 1vw auto 1.6vw;
  }

  .mobile.ar .interior-page .carousel-item-text-title {
    margin: 0vw auto 2.8rem;
  }

  .mobile.ar .appearance-page .carousel-item-text-title {
    margin: 0vw auto 2.8rem;
  }

  .page-container ::v-deep .swiper-pagination-bullet-active {
    opacity: 1;
    transition: 0.3s;
  }

  .point-main {
    width: 28.22vw;
    height: 16.29vw;
    transform: translate(-50%, -50%);
  }

  .point-main .point {
    width: 4vw;
    height: auto;
    position: absolute;
    left: 0;
    bottom: 0;
  }

  .point-main .line1 {
    --topClip: 12vw;
    width: auto;
    min-width: 0.2vw;
    height: 12vw;
    position: absolute;
    left: 1.86vw;
    top: 0.13vw;
    clip-path: inset(var(--topClip) 0px 0px 0px);
    transition: clip-path 1s;
  }

  .point-main .line2 {
    --rightClip: 26.32vw;
    width: 26.32vw;
    height: auto;
    position: absolute;
    left: 1.86vw;
    top: 0;
    clip-path: inset(0px var(--rightClip) 0px 0px);
    transition: clip-path 1s;
    transition-delay: 1s;
  }

  .mobile .point-main {
    --height: 7.2rem;
    width: 32vw;
    height: var(--height);
    /* position: absolute;
        top: 100%;
        left: 5vw; */
    --line1-height: calc(var(--height) - 7.07vw);
    transform: translate(-50%, -50%);
  }

  .mobile .point-main .point {
    width: 6.67vw;
    height: auto;
    position: absolute;
    left: 0;
    bottom: 0;
  }

  .mobile .point-main .line1 {
    --topClip: 40.13vw;
    width: auto;
    height: var(--line1-height);
    position: absolute;
    left: 3.12vw;
    top: 0.4vw;
    clip-path: inset(var(--topClip) 0px 0px 0px);
    transition: clip-path 1s;
  }

  .mobile .point-main .line2 {
    --rightClip: 5.6vw;
    width: 5.6vw;
    height: auto;
    position: absolute;
    left: 3.12vw;
    top: 0;
    clip-path: inset(0px var(--rightClip) 0px 0px);
    transition: clip-path 1s;
    transition-delay: 1s;
  }

  .mobile .point-main.left .line2 {
    left: -2.42vw;
    clip-path: inset(0px 0px 0px var(--rightClip));
  }

  .mobile .point-main.bottom {
    --height: 32.27vw;
    transform: translate(-50%, -50%) rotate(180deg);
  }

  .mobile .point-main.bottom.show .line1 {
    transition-delay: 2.2s;
  }

  .mobile .point-main.bottom .line2,
  .mobile .point-main.top .line2 {
    display: none;
  }

  .mobile .bright-main {
    position: absolute;
    top: -10%;
    left: 33%;
    color: #fff;
  }

  .mobile .bright-main.show {
    transition-delay: 2s;
  }

  .mobile .point-main.left .bright-main {
    left: -50%;
    text-align: right;
  }

  .mobile .point-main.bottom .bright-main {
    left: -20%;
    bottom: 100%;
    top: auto;
    transform: rotate(-180deg);
  }

  .mobile.es .point-main.bottom.p2 .bright-main {
    left: -33%;
    bottom: 100%;
    top: auto;
    transform: rotate(-180deg);
  }

  .mobile .bright-main .title {
    text-transform: capitalize;
    font-size: var(--mob-small-font-size);
  }

  .mobile .bright-main .num {
    font-size: var(--mob-bigger-font-size);
    font-weight: 600;
    line-height: 1;
  }

  .mobile .blur-page ::v-deep .swiper-scrollbar {
    left: 0;
    bottom: 16%;
    width: 100vw;
    height: 0.27vw;
    background: rgba(255, 255, 255, 0.2);
    opacity: 1 !important;
  }

  .mobile .blur-page ::v-deep .swiper-scrollbar-drag {
    opacity: 0;

  }

  .meitu-view {
    width: 100%;
    height: 33vw;
    padding: 0 2vw 0;
    position: relative;
    top: 70%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .meitu-view .swiper-wrapper {
    width: 180% !important;

  }

  .meitu-view .swiper-wrapper .meitu-img-view {
    width: 24.65vw;
    height: 15.99vw;
    border-radius: 0.52vw;
  }

  .meitu-view .swiper-wrapper .meitu-img-view:nth-child(6n+2),
  .meitu-view .swiper-wrapper .meitu-img-view:nth-child(6n+3) {
    width: 31.42vw;
    height: 15.99vw;
    border-radius: 0.52vw;
  }

  .meitu-img-view .img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .full-dialog {
    width: 100%;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10000;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgba(0, 0, 0, 0.6);
    backdrop-filter: blur(10px);
  }

  .full-text-main {
    width: 100%;
    height: 50vh;
    padding: 28vh 50% 0 2.8vw;
    box-sizing: border-box;
    background: linear-gradient(to bottom, transparent, rgba(0, 0, 0, 0.8));
    position: absolute;
    bottom: 0;
    left: 0;
    z-index: 10002;
  }

  .full-dialog .img {
    width: 100%;
    height: auto;
    position: relative;
  }

  .full-dialog .close-icon {
    width: 2.6vw;
    height: auto;
    position: absolute;
    top: 1vw;
    right: 2vw;
    cursor: pointer;
  }

  .mobile .full-dialog .close-icon {
    width: 10vw;
    top: 14.5vw;
    right: 4vw;
  }

  .mobile .full-text-main {
    width: 100%;
    height: 50vh;
    padding: 28vh 2.8vw 0 2.8vw;
    z-index: 1;
  }

  .mobile .full-text-main .carousel-item-text-title {
    text-transform: capitalize;
    font-size: var(--mob-big-font-size);
    color: #ffffff;
    margin: 0vw auto 2.6vw;
  }
</style>