<template>
  <div class="breadcumb">
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">{{ defaultText.home }}</el-breadcrumb-item>
      <el-breadcrumb-item
        v-for="(item,index) in breadList"
        :key="index"
        :to="{ path: item.path }">
        {{ item.name }}
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        breadList: [],
        defaultText: {
          home: 'Home',
        },
        about: {
          en: {
            Home: 'Home',
            about: 'About',
            overview: 'Overview',
            ourVision: 'Vision',
            history: 'History',
            socialResponsibility: 'Social Responsibility',
          },
          es: {
            Home: 'Hogar',
            about: 'Sobre',
            overview: 'Nuestra Visión',
            ourVision: 'Vision',
            history: 'Historia',
            socialResponsibility: 'Responsabilidad Social',
          },
          ar: {
            Home: 'مسكن',
            about: 'حول',
            overview: 'نظرة عامة',
            ourVision: 'رؤية',
            history: 'التاريخ',
            socialResponsibility: 'مسؤولية اجتماعية',
          },
        },
        news: {
          en: {
            Home: 'Home',
            news: 'Newsroom',
            mediaReport: 'Media Report',
            newsRelease: 'News Release',
            baicInX: 'BAIC In X'
          },
          es: {
            Home: 'Hogar',
            news: 'Sala De Prensa',
            mediaReport: 'Informe De Los Medios',
            newsRelease: 'Comunicado De Prensa',
            baicInX: 'BAIC en X'
          },
          ar: {
            Home: 'مسكن',
            news: 'الإخبارية',
            mediaReport: 'وسائل الإعلام',
            newsRelease: 'اصدار جديد',
            baicInX: 'بايك على منصة  X'
          },
        },
      };
    },

    mounted() {
      const to = this.$route
      const lang = this.$i18n.locale

      this.breadList = to.matched.map(item => {
        return {
          path: item.path,
          name: item.name.split('___')[0].split('-').slice(0, 2).map(item => {
            this.defaultText.home = this.about[ lang ].Home;
            return to.name.includes('about') ? this.about[ lang ][ item ] : this.news[ lang ][ item ];
          }).join('  /  '),
        };
      });
    },
  };
</script>
<style lang="scss">
  .breadcumb {
    //margin-top    : 30px;
    margin-bottom : 20px;

    .el-breadcrumb-item,
    .el-breadcrumb__item:last-child .el-breadcrumb__inner {
      pointer-events : none;
      cursor         : default;
    }
  }
</style>
