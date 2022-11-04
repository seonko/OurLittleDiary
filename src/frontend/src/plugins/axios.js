import axios from 'axios'
import store from '../store/index.js'
import router from '../router/index.js'

const instance = axios.create()

instance.interceptors.response.use(
  (res) => {
    if (res.headers.authorization) {
      store.dispatch('setToken', res.headers.authorization)
    } else if (res.headers.get('LoginInvaildate')) {
      alert('로그인 정보가 만료되었습니다.')
      router.push('/login')
    }
    return res
  },
  (err) => {
    store.dispatch('setToken', err.response.headers.authorization)
    return instance.request(err.config)
  }
)

export default instance
