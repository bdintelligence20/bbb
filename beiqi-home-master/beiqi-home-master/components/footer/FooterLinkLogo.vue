<template>
  <div class="link-box">
    <h3>
    <nuxt-link :to="localePath('/', $i18n.locale)">
      <img src="~/assets/image/logo.png" alt="" srcset="" />
    </nuxt-link>
    </h3>
    <div class="social-list">
      <a href="https://www.facebook.com/baicglobal" target="_blank">
        <svg version="1.1" id="图层_1" x="0px" y="0px" viewBox="0 0 36.099998 36.200001" xml:space="preserve" sodipodi:docname="facebook.svg" width="36.099998" height="36.200001" inkscape:version="1.2.1 (9c6d41e410, 2022-07-14)" xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape" xmlns:sodipodi="http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg">
          <defs id="defs10" />
          <sodipodi:namedview id="namedview8" pagecolor="#ffffff" bordercolor="#000000" borderopacity="0.25" inkscape:showpageshadow="2" inkscape:pageopacity="0.0" inkscape:pagecheckerboard="0" inkscape:deskcolor="#d1d1d1" showgrid="false" inkscape:zoom="4.3703704" inkscape:cx="18.076271" inkscape:cy="18.076271" inkscape:window-width="2560" inkscape:window-height="1494" inkscape:window-x="2869" inkscape:window-y="-11" inkscape:window-maximized="1" inkscape:current-layer="图层_1" />
          <g id="社媒" transform="translate(1300.1,80.1)">
            <g id="facebook-square" transform="translate(0,1)"><path id="路径" class="st0" d="m -1270.8,-81 c 1.9,0 3.4,0.7 4.8,2 1.4,1.3 2,2.9 2,4.8 v 22.5 c 0,1.9 -0.7,3.4 -2,4.8 -1.3,1.4 -2.9,2 -4.8,2 h -4.4 v -13.9 h 4.7 l 0.7,-5.4 h -5.4 v -3.5 c 0,-0.9 0.2,-1.5 0.6,-2 0.4,-0.5 1.1,-0.7 2.1,-0.7 h 2.9 v -4.9 c -1,-0.1 -2.4,-0.2 -4.2,-0.2 -2.1,0 -3.8,0.6 -5.1,1.9 -1.3,1.3 -1.9,3 -1.9,5.3 v 4 h -4.7 v 5.4 h 4.7 V -45 h -12.5 c -1.9,0 -3.4,-0.7 -4.8,-2 -1.4,-1.3 -2,-2.9 -2,-4.8 v -22.5 c 0,-1.9 0.7,-3.4 2,-4.8 1.3,-1.4 2.9,-2 4.8,-2 h 22.5 z" /></g>
          </g>
        </svg>
      </a>
      <a href="https://www.instagram.com/baic_intl/?igshid=YmMyMTA2M2Y%3D" target="_blank">
        <svg version="1.1" id="图层_1" x="0px" y="0px" viewBox="0 0 37 37" xml:space="preserve" sodipodi:docname="instagram.svg" width="37" height="37" inkscape:version="1.2.1 (9c6d41e410, 2022-07-14)" xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape" xmlns:sodipodi="http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg">
          <defs id="defs11" />
          <sodipodi:namedview id="namedview9" pagecolor="#ffffff" bordercolor="#000000" borderopacity="0.25" inkscape:showpageshadow="2" inkscape:pageopacity="0.0" inkscape:pagecheckerboard="0" inkscape:deskcolor="#d1d1d1" showgrid="false" inkscape:zoom="4.2909091" inkscape:cx="18.411017" inkscape:cy="18.411017" inkscape:window-width="2560" inkscape:window-height="1494" inkscape:window-x="2869" inkscape:window-y="-11" inkscape:window-maximized="1" inkscape:current-layer="图层_1" />
          <g id="社媒" transform="translate(1300,80)">
            <g id="instagram-fill-_x28_1_x29_" transform="translate(66)">
              <path id="路径" class="st0" d="m -1347.5,-67.3 c -3.2,0 -5.8,2.6 -5.8,5.8 0,3.2 2.6,5.8 5.8,5.8 3.2,0 5.8,-2.6 5.8,-5.8 0,-3.2 -2.6,-5.8 -5.8,-5.8 z" />
              <path id="形状" class="st0" d="m -1329,-61.5 c 0,-2.6 0,-5.1 -0.1,-7.6 -0.1,-3 -0.8,-5.6 -3,-7.8 -2.2,-2.2 -4.8,-2.8 -7.8,-3 -2.6,-0.1 -5.1,-0.1 -7.6,-0.1 -2.6,0 -5.1,0 -7.6,0.1 -3,0.1 -5.6,0.8 -7.8,3 -2.2,2.2 -2.8,4.8 -3,7.8 -0.1,2.6 -0.1,5.1 -0.1,7.6 0,2.6 0,5.1 0.1,7.6 0.1,3 0.8,5.6 3,7.8 2.2,2.2 4.8,2.8 7.8,3 2.6,0.1 5.1,0.1 7.6,0.1 2.6,0 5.1,0 7.6,-0.1 3,-0.1 5.6,-0.8 7.8,-3 2.2,-2.2 2.8,-4.8 3,-7.8 0.1,-2.5 0.1,-5 0.1,-7.6 z m -18.5,9.5 c -5.3,0 -9.5,-4.2 -9.5,-9.5 0,-5.3 4.2,-9.5 9.5,-9.5 5.3,0 9.5,4.2 9.5,9.5 0,5.3 -4.3,9.5 -9.5,9.5 z m 9.9,-17.2 c -1.2,0 -2.2,-1 -2.2,-2.2 0,-1.2 1,-2.2 2.2,-2.2 1.2,0 2.2,1 2.2,2.2 0,1.3 -1,2.2 -2.2,2.2 z" />
            </g>
          </g>
        </svg>
      </a>
      <a href="https://twitter.com/baicglobal" target="_blank">
        <svg version="1.1" id="图层_1" x="0px" y="0px" viewBox="0 0 40.799999 33" xml:space="preserve" sodipodi:docname="twitter.svg" width="40.799999" height="33" inkscape:version="1.2.1 (9c6d41e410, 2022-07-14)" xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape" xmlns:sodipodi="http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg">
          <defs id="defs185" />
          <sodipodi:namedview id="namedview183" pagecolor="#ffffff" bordercolor="#000000" borderopacity="0.25" inkscape:showpageshadow="2" inkscape:pageopacity="0.0" inkscape:pagecheckerboard="0" inkscape:deskcolor="#d1d1d1" showgrid="false" inkscape:zoom="5.7560976" inkscape:cx="20.326271" inkscape:cy="16.504237" inkscape:window-width="2560" inkscape:window-height="1494" inkscape:window-x="2869" inkscape:window-y="-11" inkscape:window-maximized="1" inkscape:current-layer="图层_1" />
          <g id="社媒" transform="translate(1305.3,85)">
            <g id="twitter" transform="translate(134,3)"><path id="路径" class="st0" d="m -1398.5,-84.1 c -1.5,0.6 -3.1,1.1 -4.8,1.3 1.7,-1 3.1,-2.7 3.7,-4.6 -1.6,1 -3.4,1.7 -5.3,2 -1.5,-1.6 -3.7,-2.6 -6.1,-2.6 -4.7,0 -8.4,3.7 -8.4,8.3 0,0.6 0.1,1.3 0.2,1.9 -7,-0.4 -13.2,-3.7 -17.3,-8.7 -0.7,1.2 -1.1,2.7 -1.1,4.2 0,2.9 1.5,5.4 3.7,6.9 -1.4,-0.1 -2.7,-0.4 -3.8,-1.1 v 0.1 c 0,4 2.9,7.4 6.7,8.2 -0.7,0.2 -1.5,0.3 -2.2,0.3 -0.5,0 -1.1,-0.1 -1.6,-0.1 1.1,3.3 4.2,5.7 7.9,5.8 -2.9,2.2 -6.5,3.6 -10.4,3.6 -0.7,0 -1.4,0 -2,-0.1 3.7,2.4 8.1,3.7 12.9,3.7 15.5,0 23.9,-12.7 23.9,-23.7 0,-0.4 0,-0.7 0,-1.1 1.4,-1.2 2.9,-2.6 4,-4.3 z" /></g>
          </g>
        </svg>
      </a>
      <a href="https://www.tiktok.com/@baic_global" target="_blank">
        <svg version="1.1" id="图层_1" x="0px" y="0px" viewBox="0 0 32.08741 37.036762" xml:space="preserve" sodipodi:docname="tiktok.svg" width="32.08741" height="37.036762" inkscape:version="1.2.1 (9c6d41e410, 2022-07-14)" xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape" xmlns:sodipodi="http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg">
          <defs id="defs10" />
          <sodipodi:namedview id="namedview8" pagecolor="#ffffff" bordercolor="#000000" borderopacity="0.25" inkscape:showpageshadow="2" inkscape:pageopacity="0.0" inkscape:pagecheckerboard="0" inkscape:deskcolor="#d1d1d1" showgrid="false" inkscape:zoom="5.2444444" inkscape:cx="16.016949" inkscape:cy="20.59322" inkscape:window-width="2880" inkscape:window-height="1514" inkscape:window-x="-11" inkscape:window-y="-11" inkscape:window-maximized="1" inkscape:current-layer="图层_1" />
          <g id="社媒" transform="translate(1304.9874,87)">
            <g id="tiktok" transform="translate(205)"><path id="路径" class="st0" d="m -1478,-71.8 c -3.1,0 -6.2,-1 -8.8,-2.8 v 12.9 c 0,4.9 -3,9.3 -7.5,11 -4.5,1.7 -9.6,0.4 -12.8,-3.3 -3.2,-3.7 -3.8,-9 -1.5,-13.3 2.3,-4.3 7,-6.7 11.8,-6 v 6.5 c -2.2,-0.7 -4.6,0.1 -5.9,2 -1.3,1.9 -1.3,4.5 0,6.3 1.4,1.9 3.8,2.7 6,1.9 2.2,-0.7 3.7,-2.8 3.7,-5.1 V -87 h 6.3 c 0,0.5 0,1.1 0.1,1.6 0.4,2.4 1.8,4.5 3.9,5.8 1.4,0.9 3.1,1.5 4.8,1.5 v 6.3 z" /></g>
          </g>
        </svg>
      </a>
      <a href="https://www.youtube.com/c/BAICglobal" target="_blank">
        <svg version="1.1" id="图层_1" x="0px" y="0px" viewBox="0 0 49.799999 35" xml:space="preserve" sodipodi:docname="youtube.svg" width="49.799999" height="35" inkscape:version="1.2.1 (9c6d41e410, 2022-07-14)" xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape" xmlns:sodipodi="http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg">
          <defs id="defs10" />
          <sodipodi:namedview id="namedview8" pagecolor="#ffffff" bordercolor="#000000" borderopacity="0.25" inkscape:showpageshadow="2" inkscape:pageopacity="0.0" inkscape:pagecheckerboard="0" inkscape:deskcolor="#d1d1d1" showgrid="false" inkscape:zoom="4.4528302" inkscape:cx="24.70339" inkscape:cy="17.292373" inkscape:window-width="2560" inkscape:window-height="1494" inkscape:window-x="2869" inkscape:window-y="-11" inkscape:window-maximized="1" inkscape:current-layer="图层_1" />
          <g id="社媒" transform="translate(1299.8,79.9)">
            <g id="youtube" transform="translate(268,1)"><path id="形状" class="st0" d="m -1519,-75.5 c -0.6,-2.2 -2.3,-3.9 -4.4,-4.4 -3.9,-1 -19.5,-1 -19.5,-1 0,0 -15.6,0 -19.5,1 -2.2,0.6 -3.8,2.3 -4.4,4.4 -1,3.9 -1,12.1 -1,12.1 0,0 0,8.2 1,12.1 0.6,2.2 2.3,3.8 4.4,4.4 3.9,1 19.5,1 19.5,1 0,0 15.6,0 19.5,-1 2.2,-0.6 3.8,-2.2 4.4,-4.4 1,-3.9 1,-12.1 1,-12.1 0,0 0,-8.2 -1,-12.1 z m -29.1,19.4 v -14.8 l 13.1,7.4 z" /></g>
          </g>
        </svg>
      </a>
    </div>
  </div>
</template>
<script>
export default {
  name: 'FooterLink',
  props: {
    title: {
      type: String,
    },
    linkList: {
      type: Array,
    },
  },
  data() {
    return {}
  },
}
</script>

<style scoped lang="scss">
.link-box {
  h3 {
    height: 52px;
    border-bottom: 1px solid #505050;
    margin-bottom: 20px;
    color: #fff;
    text-align: center;
    img {
      display: inline-block;
      width: 126px;
    }
  }
  .social-list {
    text-align: center;
    vertical-align: middle;
    a {
      display: inline-block;
      width: 24px;
      height: 24px;
      margin: 0 5px;
      svg {
        width: 100%;
        height: 100%;
        path {
          fill: #fff;
        }
      }

      &:nth-child(3) {
        width: 27px;
        height: 27px;
        padding-top: 4px;
      }

      &:nth-child(4) {
        margin-left: 1px;
      }

      &:nth-child(5) {
        width: 27px;
        height: 27px;
        padding-top: 4px;
      }
    }
  }
}
</style>
