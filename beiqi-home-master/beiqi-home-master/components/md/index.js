
import MdNewsImage from './news-image'
import MdNewsImageFull from './news-image-full'
import MdNewsImageLarge from './news-image-large'
import MdNewsImageText from './news-image-text'
import MdNewsImageText3Columns from './news-image-text-3columns'
import MdNewsImageTextInvertedL from './news-image-text-inverted-l'
import MdNewsTextZ from './news-text-z'
import MdNewsText from './news-text'
import MdNewsTitle from './news-title'
import MdNewsTextOutput from './style-text-output'

export default {
  install (Vue) {
    Vue.component('MdNewsImage', MdNewsImage)
    Vue.component('MdNewsImageFull', MdNewsImageFull)
    Vue.component('MdNewsImageLarge', MdNewsImageLarge)
    Vue.component('MdNewsImageText', MdNewsImageText)
    Vue.component('MdNewsImageText3columns', MdNewsImageText3Columns)
    Vue.component('MdNewsImageTextInvertedL', MdNewsImageTextInvertedL)
    Vue.component('MdNewsTextZ', MdNewsTextZ)
    Vue.component('MdNewsText', MdNewsText)
    Vue.component('MdNewsTitle', MdNewsTitle)
    Vue.component('MdStyleTextOutput', MdNewsTextOutput)
  }
}
