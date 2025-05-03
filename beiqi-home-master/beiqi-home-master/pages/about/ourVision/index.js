/**
 * Author: SHI
 * Date: 2022/6/20
 * Remark: ourVision
 */
import detail from '~/pages/news/newsRelease/detail/_id';

export default {
  extends: detail,
  name: 'ourVision',
  data() {
    return {
      className: true,
      imgType: 'vision',
      showOther: false,
    };
  },
};
