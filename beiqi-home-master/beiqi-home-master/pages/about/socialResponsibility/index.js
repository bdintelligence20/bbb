/**
 * Author: SHI
 * Date: 2022/6/23
 * Remark: socialResponsibility
 */
import detail from '~/pages/news/newsRelease/detail/_id';

export default {
  extends: detail,
  name: 'socialResponsibility',
  data() {
    return {
      className: true,
      imgType: 'csr',
      showOther: false,
    };
  },
};
