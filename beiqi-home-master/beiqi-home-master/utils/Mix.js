/**
 * Author: SHI
 * Date: 2022/7/11
 * Remark: Mix
 */

export default {
  data() {
    return {
      lang: 'en', // 语言
      defaultText: {},
      allLanguages: {},
      isObjAssign: true, // 是否需要合并对象
    };
  },
  watch: {
    '$i18n.locale': {
      immediate: true,
      deep: true,
      handler(newName, oldName) {
        this.lang = newName;
        if ( this.isObjAssign ) {
          this.defaultText = Object.assign({}, this.defaultText, this.allLanguages[ newName ]);
        }
      },
    }
  },
};
