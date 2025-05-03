import es from '../locales/es'
import en from '../locales/en'
import ar from '../locales/ar'


// 加载element-ui语言包
import enLocale from 'element-ui/lib/locale/lang/en'
import esLocale from 'element-ui/lib/locale/lang/es'
import arLocale from 'element-ui/lib/locale/lang/ar'

const mergeEN = Object.assign(enLocale, en)
const mergeES = Object.assign(esLocale, es)
const mergeAR = Object.assign(arLocale, ar)

const I18N = {
  locales: [
    {
      code: 'en',
      iso: 'en-US',
      name: 'EN'
    },
    {
      code: 'es',
      iso: 'es-ES',
      name: 'ES'
    },
    {
      code: 'ar',
      iso: 'ar-AE',
      name: 'AR'
    }
  ],
  defaultLocale: 'en',
  seo: true,
  vueI18nLoader: true,
  vueI18n: {
    fallbackLocale: 'en',
    messages: {
      en: mergeEN,
      es: mergeES,
      ar: mergeAR
    }
  }
}

export default I18N