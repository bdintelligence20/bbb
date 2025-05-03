<!--
 * Author: SHI
 * Date: 2022/6/23
 * Remark: history
 -->

<template>
  <div class="history__page" v-loading="fullLoading">
    <title-section img-type="history" :title="$t('about.history.title')" :sub-title="$t('about.history.subTitle')" />
    <div ref="timeline" class="timeline">
      <div class="timeline-bg">
        <span>{{ hYears.length > 0 ? hYears[aYearIndex] : '' }}</span>
      </div>
      <div ref="timeline-line" class="timeline-line"><span style="height: 417.062px"></span></div>
      <div class="timeline-content">
        <div v-for="(year, index) of hYears" :key="year" class="timeline-pannel" :class="index <= aYearIndex ? 'active' : ''">
          <div class="timeline-pannel-year">{{ year }}</div>
          <div v-for="(item, index) of hData[year]" :key="index" class="timeline-pannel-item">
            <div class="timeline-left">
<!--              {{ `${item.startTime.split('-')[1]}/${item.startTime.split('-')[2]}` }}-->
            </div>
            <div class="timeline-right">
              <span>{{ item.title }}</span>
              <div class="timeline-info">{{ item.describe }}</div>
              <div class="timeline-pic">
                <img :src="baseUrl + item.imageUrl" alt="" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="timeline-pos">
        <div class="timeline-pos-content">
          <span v-for="(year, index) of hYears" :key="year" :class="aYearIndex === index ? 'active' : ''" @click="clickTimelineYear(index)">
            {{ year }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import mix from '~/utils/Mix'

export default {
  name: 'history',
  layout: 'main',
  data() {
    return {
      baseUrl: process.env.imgBaseUrl,
      fullLoading: false,
      // 组件参数
      monthName: ['jan', 'feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'oct', 'nov', 'dec'],
      // 数据参数
      activities: [],
      hData: {},
      hYears: [],
      aYearIndex: 0, // 当前查看的年份
    }
  },
  created() {
    this.fullLoading = true
    this.$api.news
      .getAboutSubMenuHistory()
      .then(res => {
        if (res.code === 200) {
          const hData = {}
          const hYears = []
          res.data.forEach(item => {
            const year = item.startTime.split('-')[0]
            if (!hYears.includes(year)) {
              hYears.push(year)
              hData[year] = []
            }
            hData[year].push(item)
          })

          this.hData = hData
          this.hYears = hYears
          this.activities = res.data
          this.scrollWithWindow()
        }
      })
      .catch(err => {
        this.$message.error(err.msg || 'Request failed')
      })
      .finally(() => {
        this.fullLoading = false
      })
  },
  activated() {
    window.addEventListener('scroll', this.scrollWithWindow)
  },
  deactivated() {
    window.removeEventListener('scroll', this.scrollWithWindow)
  },
  methods: {
    scrollWithWindow() {
      const tl = this.$refs.timeline
      const tlBg = tl.querySelector('.timeline-bg>span')
      const tlLine = tl.querySelector('.timeline-line>span')
      const tlContent = tl.querySelector('.timeline-content')
      const tlPannels = tlContent.querySelectorAll('.timeline-pannel')
      const tlPos = tl.querySelector('.timeline-pos-content')

      const windowHeight = document.documentElement.clientHeight || document.body.clientHeight
      const { top: contentTop, bottom: contentBottom } = tlContent.getBoundingClientRect()

      const { top: lineTop } = tlLine.getBoundingClientRect()
      tlLine.style = `height: ${Math.max(0, windowHeight / 2 - lineTop)}px;`

      const { height: posHeight } = tlPos.getBoundingClientRect()
      tlPos.style = `top: ${Math.min(Math.max(Math.max((windowHeight - posHeight) / 2, 70), contentTop), contentBottom - posHeight)}px`

      if (contentTop < windowHeight / 3 && contentBottom > (windowHeight * 2) / 3) {
        tlBg.style = 'opacity: 1;'
      } else {
        tlBg.style = 'opacity: 0;'
      }

      let maxIndex = 0
      tlPannels.forEach((pannel, index) => {
        const { top } = pannel.getBoundingClientRect()
        if (top < windowHeight / 2) {
          maxIndex = Math.max(maxIndex, index)
        }
      })
      this.aYearIndex = maxIndex
    },

    clickTimelineYear(index) {
      const tl = this.$refs.timeline
      const tlPannels = tl.querySelectorAll('.timeline-content>.timeline-pannel')
      const scrollTop = document.documentElement.scrollTop
      document.documentElement.scrollTop = scrollTop + tlPannels[index].getBoundingClientRect().top - 100
    },
  },
  mixins: [mix],
}
</script>

<style lang="scss">
.history__page {
  min-height: 100vh;
  .timeline {
    display: flex;
    flex-direction: row;
    width: 100%;
    margin: 80px 0;
    padding: 0 50px;
    position: relative;
    overflow: hidden;
    .timeline-bg {
      position: fixed;
      width: 100%;
      height: 100vh;
      line-height: 100vh;
      text-align: center;
      font-size: 540px;
      color: #f7f7f7;
      overflow: hidden;
      left: 0px;
      top: 0px;
      z-index: -100;

      span {
        opacity: 0;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
      }
    }
    .timeline-line {
      width: 2px;
      height: calc(100% - 23px);
      position: absolute;
      left: calc(50px + 11px);
      top: 18px;
      z-index: 2;
      background: url(assets/image/icon-63.png) repeat-y;
      overflow: hidden;
      span {
        width: 100%;
        height: 0px;
        position: absolute;
        left: 0px;
        top: 0px;
        background: #d2281b;
      }
    }
    .timeline-content {
      flex: 1;
      overflow: hidden;
      position: relative;
      z-index: 20;
      .timeline-pannel {
        overflow: hidden;
        position: relative;
        padding-bottom: 110px;

        &.active {
          .timeline-pannel-year {
            color: #d2281b;
            &::before {
              border: 2px #d2281b solid;
            }
            &::after {
              background: #d2281b;
            }
          }
        }

        .timeline-pannel-year {
          height: 36px;
          line-height: 36px;
          font-size: 36px;
          color: #1a1a1a;
          overflow: hidden;
          padding: 0 50px;
          position: relative;
          z-index: 12;

          &::before {
            width: 24px;
            height: 24px;
            content: '';
            position: absolute;
            left: 0px;
            top: 6px;
            box-sizing: border-box;
            border: 2px #1a1a1a solid;
            border-radius: 50%;
          }

          &::after {
            width: 12px;
            height: 12px;
            content: '';
            position: absolute;
            box-sizing: border-box;
            border-radius: 50%;
            background: #1a1a1a;
            left: 6px;
            top: 12px;
          }
        }
        .timeline-pannel-item {
          display: flex;
          flex-direction: row;
          width: 100%;
          overflow: hidden;
          padding-left: 50px;
          box-sizing: border-box;
          margin-top: 80px;
          position: relative;
          z-index: 12;

          .timeline-left {
            width: 14%;
            overflow: hidden;
            font-size: 24px;
            color: #868583;
            box-sizing: border-box;
            margin-top: 1px;
          }
          .timeline-right {
            width: 70%;
            overflow: hidden;
            span {
              width: 100%;
              overflow: hidden;
              font-size: 27px;
              color: #1a1a1a;
              font-weight: bold;
            }
            .timeline-info {
              width: 100%;
              overflow: hidden;
              font-size: 18px;
              color: #1a1a1a;
              line-height: 36px;
              margin-top: 25px;
            }
            .timeline-pic {
              width: 100%;
              overflow: hidden;
              margin-top: 40px;
              img {
                width: 100%;
              }
            }
          }
        }
      }
    }
    .timeline-pos {
      width: 64px;
      position: relative;
      .timeline-pos-content {
        position: fixed;
        top: 0;
        width: 60px;
        border-left: 1px solid #e6e6e6;

        span {
          display: block;
          overflow: hidden;
          text-align: right;
          font-size: 18px;
          line-height: 22px;
          color: #868583;
          padding: 5px 0;
          position: relative;
          cursor: pointer;

          &.active {
            color: #d1291a;

            &::before {
              content: '';
              width: 4px;
              height: 22px;
              left: 0px;
              top: 5px;
              background: #d1291a;
              position: absolute;
            }
          }
        }
      }
    }
  }
}

.lang___ar {
  .history__page {
    .timeline {
      .timeline-line {
        left: 0;
        right: calc(50px + 11px);
      }
      .timeline-content {
        .timeline-pannel {
          .timeline-pannel-year {
            &::before {
              left: auto;
              right: 0px;
            }
            &::after {
              left: auto;
              right: 6px;
            }
          }
          .timeline-pannel-item {
            padding-left: 0px;
            padding-right: 50px;
          }
        }
      }
      .timeline-pos {
        width: 64px;
        position: relative;
        .timeline-pos-content {
          border-left: 0px solid #e6e6e6;
          border-right: 1px solid #e6e6e6;

          span {
            text-align: left;
            &.active {
              &::before {
                content: '';
                left: auto;
                right: 0px;
              }
            }
          }
        }
      }
    }
  }

  &.md,
  &.sm {
    .history__page {
      .timeline {
        .timeline-line {
          left: 0;
          right: calc(15px + 11px);
        }
        .timeline-content {
          .timeline-pannel {
            .timeline-pannel-item {
              flex-direction: column;
              padding-left: 15px;
              padding-right: 50px;
            }
          }
        }
      }
    }
  }

  &.xs {
  .timeline {
    padding: 0 10px 0 5px;

    .timeline-line {
      right: calc(10px + 8px);
    }
    .timeline-content {
      .timeline-pannel {
        .timeline-pannel-year {
          &::after {
            right: 4px;
          }
        }
        .timeline-pannel-item {
          padding-right: 25px;
          padding-left: 10px;
        }
      }
    }
    .timeline-pos {
      width: 40px;
      .timeline-pos-content {
        width: 36px;
        span {
          font-size: 12px;
          line-height: 18px;

          &.active {
            &::before {
              content: '';
              width: 2px;
              height: 18px;
            }
          }
        }
      }
    }
  }
}
}

.lg {
  .history__page {
    .timeline {
      .timeline-bg {
        font-size: 440px;
      }
    }
  }
}

.md {
  .history__page {
    .timeline {
      .timeline-bg {
        font-size: 380px;
      }
    }
  }
}

.sm {
  .history__page {
    .timeline {
      .timeline-bg {
        font-size: 240px;
      }
    }
  }
}

.xs {
  .timeline {
    margin: 50px 0 0 0;
    padding: 0 5px 0 10px;
    .timeline-bg {
      font-size: 120px;
    }
    .timeline-line {
      top: 12px;
      height: calc(100% - 17px);
      left: calc(10px + 8px);
    }
    .timeline-content {
      .timeline-pannel {
        padding-bottom: 50px;
        .timeline-pannel-year {
          height: 24px;
          line-height: 24px;
          font-size: 24px;
          padding: 0 25px;

          &::before {
            width: 18px;
            height: 18px;
            top: 3px;
          }

          &::after {
            width: 10px;
            height: 10px;
            left: 4px;
            top: 7px;
          }
        }
        .timeline-pannel-item {
          flex-direction: column;
          padding-left: 25px;
          padding-right: 10px;
          margin-top: 30px;
          .timeline-left {
            width: 100%;
            overflow: hidden;
            font-size: 16px;
            margin-top: 0px;
          }
          .timeline-right {
            width: 100%;
            span {
              width: 100%;
              font-size: 18px;
            }
            .timeline-info {
              width: 100%;
              font-size: 16px;
              line-height: 24px;
              margin-top: 15px;
            }
            .timeline-pic {
              width: 100%;
              overflow: hidden;
              margin-top: 20px;
              img {
                width: 100%;
              }
            }
          }
        }
      }
    }
    .timeline-pos {
      width: 40px;
      .timeline-pos-content {
        width: 36px;
        span {
          font-size: 12px;
          line-height: 18px;

          &.active {
            &::before {
              content: '';
              width: 2px;
              height: 18px;
            }
          }
        }
      }
    }
  }
}

.md,
.sm {
  .history__page {
    .timeline {
      margin: 50px 0 0 0;
      padding: 0 15px;
      .timeline-line {
        left: calc(15px + 11px);
      }
      .timeline-content {
        .timeline-pannel {
          .timeline-pannel-item {
            flex-direction: column;
            padding-right: 15px;
            margin-top: 50px;

            .timeline-left {
              width: 100%;
              margin-top: 0px;
              margin-bottom: 10px;
            }
            .timeline-right {
              width: 100%;
            }
          }
        }
      }
    }
  }
}
</style>
